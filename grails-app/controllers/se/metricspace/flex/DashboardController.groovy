package se.metricspace.flex

class DashboardController {
    UserService userService

    def index() {
        SessionUser sessionUser = session.sessionUser
        List<FlexDate> dates = FlexDate.findAllByDateLessThanEqualsAndDateGreaterThanEquals(Date.newInstance(), Date.newInstance().minus(31),[sort: 'date', order: 'desc', max: 31])
        User user = User.findByUid(sessionUser.getUid())
        List<Absent> absences = (user) ? Absent.findAllByUser(user, [sort: 'flexDate', order: 'desc', max: 10]) : []
        List<ReportedTime> reportedTimes = (user) ? ReportedTime.findAllByUser(user, [sort: 'flexDate', order: 'desc', max: 15]) : []
        List<TimeAdjustment> timeAdjustments = (user) ? TimeAdjustment.findAllByUser(user, [sort: 'dateCreated', order: 'desc', max: 10]) : []
        List<WorkRate> workRates = (user) ? WorkRate.findAllByUser(user, [sort: 'startDate', order: 'desc', max: 5]) : []
        int timeAdjustmentSum = (user) ? userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment') : 0
        int reportedTimeDelta = (user) ? userService.sumColFromTable(user.id, 'daily_delta', 'reported_time') : 0

        [absences: absences, dates: dates, reportedTimeDelta: reportedTimeDelta, reportedTimes: reportedTimes, timeAdjustmentSum: timeAdjustmentSum, timeAdjustments: timeAdjustments, user: user, workRates: workRates]
    }
}
