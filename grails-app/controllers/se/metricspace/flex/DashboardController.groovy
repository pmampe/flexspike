package se.metricspace.flex

class DashboardController {
    DateService dateService
    UserService userService

    def index() {
        SessionUser sessionUser = session.sessionUser
        FlexDate flexDate = FlexDate.findByDate(Date.newInstance())
        List<FlexDate> dates = FlexDate.findAllByDateLessThanEqualsAndDateGreaterThanEquals(Date.newInstance(), Date.newInstance().minus(31),[sort: 'date', order: 'desc', max: 31])
        User user = User.findByUid(sessionUser.getUid())
        ReportedTime reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        int timeAdjustmentSum = (user) ? userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment') : 0
        int reportedTimeDelta = (user) ? userService.sumColFromTable(user.id, 'daily_delta', 'reported_time') : 0

        [dates: dates, flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum, user: user]
    }

    def selectOtherDate() {
        log.info "selectOtherDate: ${params}"
        FlexDate flexDate = (params.long('id')) ? FlexDate.get(params.long('id')) : null
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        int timeAdjustmentSum = (user) ? userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment') : 0
        int reportedTimeDelta = (user) ? userService.sumColFromTable(user.id, 'daily_delta', 'reported_time') : 0
        ReportedTime reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)

        return render(template: 'reportTime', model: [flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum])
         
    }

    def showAbsence() {
        log.info "showAbsence: ${params}"
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        List<Absent> absences = Absent.findAllByUser(user, [sort: 'flexDate', order: 'desc'])
        return render(template: "showAbsence", model: [absences: absences])
    }
    def show12Weeks() {
        log.info "show12Weeks: ${params}"
        return render(template: "show12Weeks", model: [])
    }

    def showLastMonth() {
        log.info "showLastMonth: ${params}"
        return render(template: "showLastMonth", model: [])
    }

    def showMonthly() {
        log.info "showMonthly: ${params}"
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        List<Expando> timeByMonth = dateService.getUserReportedTimeByMonth(user.id)
        return render(template: "showMonthly", model: [timeByMonth: timeByMonth])
    }

    def showTimeAdjustments() {
        log.info "showTimeAdjustments: ${params}"
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        List<TimeAdjustment> adjustments = TimeAdjustment.findAllByUser(user, [sort: 'dateCreated', order: 'desc'])
        return render(template: "showTimeAdjustments", model: [adjustments: adjustments])
    }

    def showWorkRates() {
        log.info "showWorkRates: ${params}"
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        List<WorkRate> workrates = WorkRate.findAllByUser(user, [sort: 'startDate', order: 'desc'])
        return render(template: "showWorkRates", model: [workrates: workrates])
    }
}
