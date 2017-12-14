package se.metricspace.flex

class DashboardController {
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
        return render(template: "showAbsence", model: [])
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
        return render(template: "showMonthly", model: [])
    }

    def showTimeAdjustments() {
        log.info "showTimeAdjustments: ${params}"
        return render(template: "showTimeAdjustments", model: [])
    }

    def showWorkRates() {
        log.info "showWorkRates: ${params}"
        return render(template: "showWorkRates", model: [])
    }
}
