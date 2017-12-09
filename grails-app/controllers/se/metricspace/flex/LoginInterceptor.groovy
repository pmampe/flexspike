package se.metricspace.flex

import grails.util.Environment
import groovy.transform.CompileStatic

@CompileStatic
class LoginInterceptor {
  int order = 30

  LoginInterceptor() {
    matchAll().excludes(controller: 'public')
  }

  boolean before() {
    String eppn = (request.getAttribute('eppn') as String)
    if(null==eppn && (Environment.current == Environment.DEVELOPMENT || Environment.current == Environment.TEST)) {
      SessionUser sessionUser = new SessionUser('fakeuser@su.se', Role.SYSADMIN, ['employee'], "Donald Duck")  
      session.setAttribute('sessionUser', sessionUser)
      session.setAttribute('role', Role.SYSADMIN)
      return true
    }

    if (!eppn) {
      /** If no eppn available we set role to public and return */
      session.setAttribute('role', Role.PUBLIC)
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