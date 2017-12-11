package se.metricspace.flex

class PublicController {
    def index() {
    }

    def unsudo() {
        SessionUser realUser = session.getAttribute('realUser')
        if(realUser) {
            session.setAttribute('sessionUser', realUser)
        }
        session.setAttribute('realUser', null)
        return redirect(controller: 'dashboard', action: 'index')
    }
}
