package se.metricspace.flex

import grails.util.Environment
import groovy.transform.CompileStatic

@CompileStatic
class LoginInterceptor {
  int order = 30
  UserService userService

  LoginInterceptor() {
    matchAll().excludes(controller: 'public')
  }

  boolean before() {
    String eppn = (request.getAttribute('eppn') as String)?.trim()
    if(eppn && !session.getAttribute('sessionUser')) {
      String givenName = request.getAttribute('givenName')
      String sn = request.getAttribute('sn')
      String displayName = request.getAttribute('displayName') ?: "${givenName} ${sn}"
      String affiliation = request.getAttribute('affiliation')
      if(eppn.endsWith("@su.se")) {
        boolean isEmployee = affiliation.contains("employee@")
        boolean isStudent = affiliation.contains("employee@")
        if(isEmployee) {
          final List<String> hacksySysAdminsUntilHandledBySukatOrIdp = ["jqvar@su.se", "mano3567@su.se", "pama7242@su.se"]
          if(hacksySysAdminsUntilHandledBySukatOrIdp.contains(eppn)) {
            SessionUser sessionUser = new SessionUser(eppn, Role.SYSADMIN, true, displayName)
            session.setAttribute('sessionUser', sessionUser)
          } else if(userService.isCalenderAdmin(eppn.substring(0, eppn.indexOf('@')))) {
            SessionUser sessionUser = new SessionUser(eppn, Role.CALADMIN, true, displayName)
            session.setAttribute('sessionUser', sessionUser)
          } else {
            SessionUser sessionUser = new SessionUser(eppn, Role.EMPLOYEE, true, displayName)
            session.setAttribute('sessionUser', sessionUser)
          }
        } else if (isStudent) {
          SessionUser sessionUser = new SessionUser(eppn, Role.STUDENT, false, displayName)
          session.setAttribute('sessionUser', sessionUser)
        } else {
          SessionUser sessionUser = new SessionUser(eppn, Role.PUBLIC, false, displayName)
          session.setAttribute('sessionUser', sessionUser)
        }
      } else {
        SessionUser sessionUser = new SessionUser(eppn, Role.PUBLIC, false, displayName)
        session.setAttribute('sessionUser', sessionUser)
      }
    }
    if(null==eppn && (Environment.current == Environment.DEVELOPMENT || Environment.current == Environment.TEST)) {
      if(!session.getAttribute('realUser')) {
        SessionUser sessionUser = new SessionUser('fakeuser@su.se', Role.SYSADMIN, true, "Donald Duck")
        session.setAttribute('sessionUser', sessionUser)
      }
      return true
    }

    if (!eppn) {
      session.setAttribute('sessionUser', null)
      return true
    }

    return true
  }

  boolean after() {
    true
  }

  void afterView() {
    // no-op
  }
}