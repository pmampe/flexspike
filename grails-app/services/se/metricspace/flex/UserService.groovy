package se.metricspace.flex

import grails.gorm.transactions.Transactional
import grails.transaction.NotTransactional

import javax.naming.Context
import javax.naming.NamingEnumeration
import javax.naming.directory.DirContext
import javax.naming.directory.InitialDirContext
import javax.naming.directory.SearchControls
import javax.naming.directory.SearchResult

@Transactional
class UserService {
    @NotTransactional
    LdapObject findUserInLdapByUid(String uid) {
        LdapObject ldapObject = null
        if(uid) {
            try {
                List<se.metricspace.flex.LdapObject> ldapObjects = rawLdapSearch(javax.naming.directory.SearchControls.SUBTREE_SCOPE, "dc=su,dc=se", "(uid=${uid})", 1, 3000)
                log.info "size: ${ldapObjects?.size()}"
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
        log.info "rawLdapSearch(${scope}, ${base}, ${condition}, ${maxSize}, ${timeOut})"
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
