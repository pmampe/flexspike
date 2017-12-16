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
        List<TimeAdjustment> adjustments = reportedTime?.getAdjustments()

        [adjustments: adjustments, dates: dates, flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum, user: user]
    }

    def selectOtherDate() {
        FlexDate flexDate = (params.long('id')) ? FlexDate.get(params.long('id')) : null
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        int timeAdjustmentSum = (user) ? userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment') : 0
        int reportedTimeDelta = (user) ? userService.sumColFromTable(user.id, 'daily_delta', 'reported_time') : 0
        ReportedTime reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        List<TimeAdjustment> adjustments = reportedTime?.getAdjustments()

        return render(template: 'reportTime', model: [adjustments: adjustments, flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum])
         
    }

    def show12Weeks() {
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        Map<String, Map<String, Integer>> timeByWeek = dateService.getUserReportedTimeByWeek(user.id, 12)
        return render(template: "show12Weeks", model: [timeByWeek: timeByWeek])
    }

    def showAbsence() {
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        List<Absent> absences = Absent.findAllByUser(user, [sort: 'flexDate', order: 'desc'])
        return render(template: "showAbsence", model: [absences: absences])
    }

    def showLastMonth() {
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        Date today = Date.newInstance()
        List<ReportedTime> lastMonth = ReportedTime.createCriteria().list {
            eq('user', user) 
            flexDate {
                gte('date', today.minus(31))
                lte('date', today)
            }
            order("flexDate","desc")
        }
        return render(template: "showLastMonth", model: [lastMonth: lastMonth])
    }

    def showMonthly() {
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        Map<String, Map<String, Integer>> timeByMonth = dateService.getUserReportedTimeByMonth(user.id)
        return render(template: "showMonthly", model: [timeByMonth: timeByMonth])
    }

    def showTimeAdjustments() {
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        List<TimeAdjustment> adjustments = TimeAdjustment.findAllByUser(user, [sort: 'dateCreated', order: 'desc'])
        return render(template: "showTimeAdjustments", model: [adjustments: adjustments])
    }

    def showWorkRates() {
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        List<WorkRate> workrates = WorkRate.findAllByUser(user, [sort: 'startDate', order: 'desc'])
        return render(template: "showWorkRates", model: [workrates: workrates])
    }
}
