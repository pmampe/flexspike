package se.metricspace.flex

import grails.gorm.transactions.Transactional
import grails.transaction.NotTransactional

import javax.naming.Context
import javax.naming.NamingEnumeration
import javax.naming.directory.DirContext
import javax.naming.directory.InitialDirContext
import javax.naming.directory.SearchControls
import javax.naming.directory.SearchResult
import javax.sql.DataSource
import groovy.sql.Sql

@Transactional
class UserService {
    DataSource dataSource

    @NotTransactional
    LdapObject findUserInLdapByUid(String uid) {
        LdapObject ldapObject = null
        if(uid) {
            try {
                List<se.metricspace.flex.LdapObject> ldapObjects = rawLdapSearch(javax.naming.directory.SearchControls.SUBTREE_SCOPE, "dc=su,dc=se", "(uid=${uid})", 1, 3000)
                if(ldapObjects?.size()>0) {
                    ldapObject = ldapObjects[0]
                }
            } catch(Throwable exception) {
                log.warn "Some problem looking up user in ldap: ${exception.getMessage()}"
            }
        }
        return ldapObject
    }

    @NotTransactional
    SessionUser getSessionUserForUid(String uid) {
        SessionUser sessionUser
        if(uid?.trim()) {
            LdapObject ldapUser = findUserInLdapByUid((uid.indexOf("@")>0) ? uid.substring(0, uid.indexOf("@")) : uid)
            if(ldapUser) {
                String displayName = ldapUser.getFirstAttributeValue('displayName')
                List<String> affiliations = ldapUser.getAttributeValues('eduPersonAffiliation')
                if(affiliations?.contains('employee')) {
                    if(isCalenderAdmin(ldapUser.getFirstAttributeValue('uid'))) {
                        sessionUser = new SessionUser(uid, Role.CALADMIN, true, displayName)
                    } else {
                        sessionUser = new SessionUser(uid, Role.EMPLOYEE, true, displayName)
                    }
                } else if (affiliations?.contains('student')) {
                    sessionUser = new SessionUser(uid, Role.STUDENT, false, displayName)
                } else {
                    sessionUser = new SessionUser(uid, Role.PUBLIC, false, displayName)
                }
            } else {
                sessionUser = new SessionUser(uid, Role.PUBLIC, false, "")
            }
        } else {
            sessionUser = new SessionUser("", Role.PUBLIC, false, "")
        }
        return sessionUser
    }

    @NotTransactional
    boolean isCalenderAdmin(String uid) {
        boolean isCalAdm = false
        if(uid) {
            LdapObject ldapObject = findUserInLdapByUid(uid)
            if(ldapObject) {
                List<String> membersOf = ldapObject.getAttributeValues('memberOf')
                isCalAdm = membersOf.contains('cn=flex-calendar,ou=Flex,ou=Groups,dc=it,dc=su,dc=se')
            }
        }
        return isCalAdm
    }

    @NotTransactional
    int sumColFromTable(Long userId, String columnName, String tableName) {
        int sum = 0
        Sql sql
        try {
            sql = Sql.newInstance(dataSource)
            sql.rows("select sum(${columnName}) as summa from ${tableName} where user_id=?;", [userId]).each { row ->
                if(row.summa) {
                    sum = row.summa as Integer
                }
            }
        } catch(Throwable exception) {
            log.warn "Some problems getting sum for ${columnName} / ${tableName}:${exception.getMessage()} "
        } finally {
            if(sql) {
                try {
                    sql.close()
                } catch(Throwable exception) {
                }
                sql = null
            }
        }
        return sum
    }

    private DirContext getContext(int timeout=500) {
        Hashtable env = new Hashtable()
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory")
        env.put(Context.PROVIDER_URL, "ldap://ldap.su.se:389")
        env.put("com.sun.jndi.ldap.read.timeout", ""+timeout)
        env.put("com.sun.jndi.ldap.connect.timeout", "1000")
        DirContext ctx = new InitialDirContext(env)
        return ctx
    }

    private List<se.metricspace.flex.LdapObject> rawLdapSearch(int scope, String base, String condition, int maxSize = 100, int timeOut = 500) {
        List<se.metricspace.flex.LdapObject> resultSet = []
        DirContext ctx = null
        NamingEnumeration answer = null
        try {
            ctx = getContext(timeOut)
            SearchControls ctrl = new SearchControls()
            ctrl.setSearchScope(scope)
            ctrl.setCountLimit(maxSize)
            try {
                answer = ctx.search(base, condition, ctrl)
                while(answer.hasMore()) {
                    SearchResult result = (SearchResult)answer.next()
                    String name = result.nameInNamespace.toString()
                    se.metricspace.flex.LdapObject ldapObject = se.metricspace.flex.LdapObject.parseLdapEntry(name, result.attributes)
                    if(ldapObject) {
                        resultSet<<ldapObject
                    }
                }
            } catch (javax.naming.SizeLimitExceededException exception) {
            }
        } catch (Throwable exception) {
            log.info "Some problems doing ldap: ${exception.getMessage()}", exception
        } finally {
            if(answer) {
                try {
                    answer.close()
                } catch(Throwable exception) {
                }
                answer = null
            }
            if(ctx) {
                try {
                    ctx.close()
                } catch(Throwable exception) {
                }
                ctx = null
            }
        }
        return resultSet.sort()
    }
}
