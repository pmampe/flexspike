package se.metricspace.flex

class SysAdminDashboardController {
  SysAdminService sysAdminService

  def index() {
  }

  def initAbsencesFromOldSystem()  {
      sysAdminService.loadAbsencesFromOldSystem()
      return redirect(action: 'index')
  }

  def initDatesFromOldSystem() {
      sysAdminService.loadDatesFromOldSystem()
      return redirect(action: 'index')
  }

  def initReportedTimesFromOldSystem() {
    sysAdminService.loadReportedTimesFromOldSystem()
    return redirect(action: 'index')
  }

  def initUsersFromOldSystem() {
      sysAdminService.loadUsersFromOldSystem()
      return redirect(action: 'index')
  }

  def initWorkRatesFromOldSystem() {
      sysAdminService.loadWorkRatesFromOldSystem()
      return redirect(action: 'index')
  }
}
