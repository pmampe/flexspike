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
      User user = User.findByEppn(uid)
      List<Absent> absentList = Absent.findAllByUser(user, [sort: 'flexDate', order: 'desc'])
      int absSum = userService.sumColFromTable(user.id, 'length', 'absent')
      [absentList: absentList, absSum: absSum, ldapUser: ldapUser, uid: uid, user: user]
  }
}
