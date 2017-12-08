package se.metricspace.flex

class ReportedTime {
  boolean absentAllDay = false
  String comment
  int dailyDelta = 0 // diff from normtime in minutes
  int dailyTotal = 0 // in minutes
  Date dateCreated
  int endTime = 0 // minutes since midnight
  FlexDate flexDate
  Date lastUpdated
  int lunchLength
  int startTime = 0 // minutes since midnight
  User user
  WorkRate workRate

  static constraints = {
    comment(nullable: true, blank: true)
    dateCreated(nullable: true)
    flexDate(nullable: false)
    lastUpdated(nullable: true)
    user(nullable: false)
    workRate(nullable: true)
  }
}
