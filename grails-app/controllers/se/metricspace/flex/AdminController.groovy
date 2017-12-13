package se.metricspace.flex

class AdminController {
  SysAdminService sysAdminService
  UserService userService

  def index() {
      String uid = params.uid?.trim()
      int usersLastMonth = userService.uniqueUsersSince(-31)
      int usersLastQuarter = userService.uniqueUsersSince(-91)
      int usersLastWeek = userService.uniqueUsersSince(-7)
      int usersLastYear = userService.uniqueUsersSince(-366)
      [uid: uid, usersLastMonth: usersLastMonth, usersLastQuarter: usersLastQuarter, usersLastWeek: usersLastWeek, usersLastYear: usersLastYear]
  }

  def sudo() {
      String eppn = params.eppn?.trim()
      if(!eppn) {
          return redirect(action: 'index')
      }
      SessionUser sessionUser = userService.getSessionUserForUid(eppn)
      if(sessionUser) {
          SessionUser realUser = session.getAttribute('sessionUser') as SessionUser
          session.setAttribute('sessionUser', sessionUser)
          session.setAttribute('realUser', realUser)
          return redirect(controller: 'dashboard', action: 'index')
      }
      return redirect(action: 'index')
  }
}
