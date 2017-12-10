package se.metricspace.flex

class AdminController {
  SysAdminService sysAdminService
  UserService userService

  def index() {
      String uid = params.uid?.trim()
      [uid: uid]
  }

  def userOverview() {
      String uid = params.uid?.trim()
      LdapObject ldapUser = (uid) ? userService.findUserInLdapByUid(uid) : null
      
      [ldapUser: ldapUser, uid: uid]
  }
}
