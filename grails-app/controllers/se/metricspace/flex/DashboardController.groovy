package se.metricspace.flex

class DashboardController {
    DateService dateService
    UserService userService

    def index() {
        SessionUser sessionUser = session.sessionUser

        FlexDate flexDate = FlexDate.getCurrentDay()
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

    def workRate() {
        log.info "workRate: ${params}"
        SessionUser sessionUser = session.sessionUser
        User user = User.findByUid(sessionUser.getUid())
        if(params.saveWorkRate) {
            String startdate = params.startdate?.trim()
            Date startDate = (startdate) ? Date.parse('yyyy-MM-dd', startdate) : null
            String enddate = params.enddate?.trim()
            Date endDate = (enddate) ? Date.parse('yyyy-MM-dd', enddate) : null
            String workrate = params.workrate?.trim()
            int workRate = 0
            try {
                workRate = (int) 100.0 *Double.parseDouble(workrate.replaceAll('%', ''))
                if(workRate<0) {
                    workRate = 0
                }
                if(workRate>10000) {
                    workRate = 10000
                }
            } catch(Throwable exception) {
                workRate = 0
                log.warn "Some problem parsing workrate: ${exception.getMessage()}"
            }

            String comment = params.comment?.trim()
            if(startDate && workRate>0) {
                if(!user) {
                    User.newInstance(uid:sessionUser.getUid()).save(flush:true, failOnError: true)
                    user = User.findByUid(sessionUser.getUid())
                }
                FlexDate fd1 = FlexDate.findByDate(startDate)
                FlexDate fd2 = FlexDate.findByDate(endDate)
                log.info "lala"
                WorkRate.newInstance(user: user, startDate: fd1, endDate: fd2, rate: workRate, rateMonday: workRate, rateTuesday: workRate, rateWednesday: workRate, rateThursday: workRate, rateFriday: workRate, comment: comment).save(flush: true, failOnError: true)
            }
        }
        List<WorkRate> workRates = WorkRate.findAllByUser(user, [sort: 'startDate', order: 'desc'])
        [workRates: workRates]
    }
}
