package se.metricspace.flex

class LoadFromOldSystemJob {

    boolean volatility = true
    SysAdminService sysAdminService

    static triggers = {
    }

    String group = "GRAILS_JOBS"
    String description = "Initial Load From Old System"

    void execute(org.quartz.JobExecutionContext context) {
        sysAdminService.loadDatesFromOldSystem()
        sysAdminService.loadUsersFromOldSystem()
        sysAdminService.loadWorkRatesFromOldSystem()
        sysAdminService.loadReportedTimesFromOldSystem()
        sysAdminService.loadAbsencesFromOldSystem()
        sysAdminService.loadTimeAdjustmentsFromOldSystem()
    }
}
