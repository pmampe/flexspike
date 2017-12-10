package se.metricspace.flex

import groovy.transform.CompileStatic

@CompileStatic
class DashboardInterceptor {
    int order = 40

    boolean before() {
        SessionUser sessionUser = session.getAttribute('sessionUser') as SessionUser

        if(null==sessionUser || sessionUser.getRole()==Role.PUBLIC  || sessionUser.getRole()==Role.STUDENT ) {
            redirect(controller: 'public', action: 'index')
            return true
        }
        return true
    }

    boolean after() {
        return true
    }

    void afterView() {
        // no-op
    }
}
