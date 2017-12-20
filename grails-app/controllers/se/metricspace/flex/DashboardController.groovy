package se.metricspace.flex

class DashboardController {
    DateService dateService
    FlexService flexService
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

    def reportComment() {
        FlexDate flexDate = (params.long('id')) ? FlexDate.get(params.long('id')) : null
        if(!flexDate) {
            response.status = 400
            return render(text: "Missing flex date")
        }
        SessionUser sessionUser = session.sessionUser
        User user = flexService.findOrCreateUser(sessionUser.uid)
        ReportedTime reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        if(!reportedTime) {
            reportedTime = ReportedTime.newInstance(user: user, flexDate: flexDate)
        }
        String comment = params.comment?.trim()
        reportedTime.comment = comment
        reportedTime.save(flush: true)
        int timeAdjustmentSum = (user) ? userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment') : 0
        int reportedTimeDelta = (user) ? userService.sumColFromTable(user.id, 'daily_delta', 'reported_time') : 0
        reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        List<TimeAdjustment> adjustments = reportedTime?.getAdjustments()

        return render(template: 'reportTime', model: [adjustments: adjustments, flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum])
    }

    def reportEndTime() {
        FlexDate flexDate = (params.long('id')) ? FlexDate.get(params.long('id')) : null
        if(!flexDate) {
            response.status = 400
            return render(text: "Missing flex date")
        }
        SessionUser sessionUser = session.sessionUser
        User user = flexService.findOrCreateUser(sessionUser.uid)
        ReportedTime reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        if(!reportedTime) {
            reportedTime = ReportedTime.newInstance(user: user, flexDate: flexDate)
        }
        String givenHHMM = (params.endtime?.trim()) ?: Date.newInstance().format("HH:mm")
        int end = Integer.parseInt(givenHHMM.substring(0,2)) * 60 + Integer.parseInt(givenHHMM.substring(3))
        if(end<361) {
            end=361
        }
        if(end>1199) {
            end=1200
        }
        reportedTime.endTime = end
        if(reportedTime.endTime>reportedTime.startTime) {
            reportedTime.dailyTotal = reportedTime.endTime-reportedTime.startTime -reportedTime.lunchLength
            reportedTime.dailyDelta = reportedTime.dailyTotal - flexDate.fullTime
        }
        reportedTime.save(flush: true)
        int timeAdjustmentSum = (user) ? userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment') : 0
        int reportedTimeDelta = (user) ? userService.sumColFromTable(user.id, 'daily_delta', 'reported_time') : 0
        reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        List<TimeAdjustment> adjustments = reportedTime?.getAdjustments()

        return render(template: 'reportTime', model: [adjustments: adjustments, flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum])
    }

    def reportFullDayAbsence() {
        log.info "reportFullDayAbsence: ${params}"
        FlexDate flexDate = (params.long('id')) ? FlexDate.get(params.long('id')) : null
        if(!flexDate) {
            response.status = 400
            return render(text: "Missing flex date")
        }
        boolean isChecked = params.boolean('isChecked')
        SessionUser sessionUser = session.sessionUser
        User user = flexService.findOrCreateUser(sessionUser.uid)
        ReportedTime reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        if(!reportedTime) {
            reportedTime = ReportedTime.newInstance(user: user, flexDate: flexDate)
        }
        reportedTime.absentAllDay = isChecked
        if(isChecked) {
            reportedTime.dailyTotal = 0
            reportedTime.startTime = 0
            reportedTime.lunchLength = 0
            reportedTime.endTime = 0
            reportedTime.dailyDelta = -flexDate.fullTime
            Absent.findAllByUserAndFlexDate(user, flexDate)*.delete(flush: true)
        } else {
            reportedTime.dailyDelta = 0
        }

        reportedTime.save(flush: true)
        int timeAdjustmentSum = (user) ? userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment') : 0
        int reportedTimeDelta = (user) ? userService.sumColFromTable(user.id, 'daily_delta', 'reported_time') : 0
        reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        List<TimeAdjustment> adjustments = reportedTime?.getAdjustments()

        return render(template: 'reportTime', model: [adjustments: adjustments, flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum])
    }

    def reportLunchLength() {
        FlexDate flexDate = (params.long('id')) ? FlexDate.get(params.long('id')) : null
        if(!flexDate) {
            response.status = 400
            return render(text: "Missing flex date")
        }
        SessionUser sessionUser = session.sessionUser
        User user = flexService.findOrCreateUser(sessionUser.uid)
        ReportedTime reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        if(!reportedTime) {
            reportedTime = ReportedTime.newInstance(user: user, flexDate: flexDate)
        }
        String givenHHMM = (params.lunchlength?.trim()) ?: "00:30"
        int lunch = Integer.parseInt(givenHHMM.substring(0,2)) * 60 + Integer.parseInt(givenHHMM.substring(3))
        if(lunch>479) {
            lunch=480
        }
        reportedTime.lunchLength = lunch
        if(reportedTime.endTime>reportedTime.startTime) {
            reportedTime.dailyTotal = reportedTime.endTime-reportedTime.startTime -reportedTime.lunchLength
            reportedTime.dailyDelta = reportedTime.dailyTotal - flexDate.fullTime
        }
        reportedTime.save(flush: true)
        int timeAdjustmentSum = (user) ? userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment') : 0
        int reportedTimeDelta = (user) ? userService.sumColFromTable(user.id, 'daily_delta', 'reported_time') : 0
        reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        List<TimeAdjustment> adjustments = reportedTime?.getAdjustments()

        return render(template: 'reportTime', model: [adjustments: adjustments, flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum])
    }

    def reportStartTime() {
        FlexDate flexDate = (params.long('id')) ? FlexDate.get(params.long('id')) : null
        if(!flexDate) {
            response.status = 400
            return render(text: "Missing flex date")
        }
        SessionUser sessionUser = session.sessionUser
        User user = flexService.findOrCreateUser(sessionUser.uid)
        ReportedTime reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        if(!reportedTime) {
            reportedTime = ReportedTime.newInstance(user: user, flexDate: flexDate)
        }
        String givenHHMM = (params.starttime?.trim()) ?: Date.newInstance().format("HH:mm")
        int start = Integer.parseInt(givenHHMM.substring(0,2)) * 60 + Integer.parseInt(givenHHMM.substring(3))
        if(start<360) {
            start=360
        }
        if(start>1199) {
            start=1199
        }
        reportedTime.startTime = start
        if(reportedTime.endTime>reportedTime.startTime) {
            reportedTime.dailyTotal = reportedTime.endTime-reportedTime.startTime -reportedTime.lunchLength
            reportedTime.dailyDelta = reportedTime.dailyTotal - flexDate.fullTime
        }
        reportedTime.save(flush: true)
        int timeAdjustmentSum = (user) ? userService.sumColFromTable(user.id, 'adjustment', 'time_adjustment') : 0
        int reportedTimeDelta = (user) ? userService.sumColFromTable(user.id, 'daily_delta', 'reported_time') : 0
        reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
        List<TimeAdjustment> adjustments = reportedTime?.getAdjustments()

        return render(template: 'reportTime', model: [adjustments: adjustments, flexDate: flexDate, reportedTime: reportedTime, reportedTimeDelta: reportedTimeDelta, timeAdjustmentSum: timeAdjustmentSum])
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

    def timeAdjustment() {
        log.info "timeAdjustment: ${params}"
        SessionUser sessionUser = session.sessionUser
        if(params.saveTimeAdjustment && params.adjustment) {
            String adjustment = params.adjustment?.trim()
            int hh = Integer.parseInt(adjustment.substring(0, adjustment.indexOf(":")))
            int mm = Integer.parseInt(adjustment.substring(1+adjustment.indexOf(":")))
            int delta = (hh<0) ? (hh*60-mm) : (hh*60+mm)
            String comment = params.comment?.trim()
            TimeAdjustment.newInstance(user: flexService.findOrCreateUser(sessionUser.uid), adjustment: delta, comment: comment).save(flush: true, failOnError: true)
        }
        User user = User.findByUid(sessionUser.getUid())
        List<TimeAdjustment> timeAdjustments = TimeAdjustment.findAllByUser(user, [sort: 'dateCreated', order: 'desc'])
        [timeAdjustments: timeAdjustments]
    }

    def workRate() {
        SessionUser sessionUser = session.sessionUser
        if(params.saveWorkRate) {
            String startdate = params.startdate?.trim()
            Date startDate = (startdate) ? Date.parse('yyyy-MM-dd', "20"+startdate) : null
            String enddate = params.enddate?.trim()
            Date endDate = (enddate) ? Date.parse('yyyy-MM-dd', "20"+enddate) : null
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
            flexService.addWorkRate(sessionUser.getUid(), startDate, endDate, workRate, comment)
        }
        User user = User.findByUid(sessionUser.getUid())
        List<WorkRate> workRates = WorkRate.findAllByUser(user, [sort: 'startDate', order: 'desc'])
        [workRates: workRates]
    }
}
