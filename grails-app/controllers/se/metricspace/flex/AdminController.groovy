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
      List<TimeAdjustment> timeAdjustments = TimeAdjustment.findAllByUser(user, [sort: 'dateCreated', order: 'desc'])
      int timeAdjustmentSum = userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment')
      List<ReportedTime> latestReportedTimes = ReportedTime.findAllByUser(user, [sort: 'flexDate', order: 'desc', max: 50])
      int reportedTimeDelta = userService.sumColFromTable(user.id, 'daily_delta', 'reported_time')
      List<WorkRate> workRates = WorkRate.findAllByUser(user, [sort: 'startDate', order: 'desc'])
      [absentList: absentList, absSum: absSum, latestReportedTimes: latestReportedTimes, ldapUser: ldapUser, reportedTimeDelta: reportedTimeDelta, timeAdjustments: timeAdjustments, timeAdjustmentSum: timeAdjustmentSum, uid: uid, user: user, workRates: workRates]
  }
}
