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

  String formatEndTime() {
    return se.metricspace.flex.TimeFormatHelper.toHHMMString(endTime)
  }

  String formatLunchLength() {
    return se.metricspace.flex.TimeFormatHelper.toHHMMString(lunchLength)
  }

  String formatStartTime() {
    return se.metricspace.flex.TimeFormatHelper.toHHMMString(startTime)
  }

  List<TimeAdjustment> getAdjustments() {
      Date endOfDay = Date.parse("yyyy-MM-dd HH:mm:ss", flexDate.date.format("yyyy-HH-dd")+" 23:59:59")
      return TimeAdjustment.findAllByUserAndDateCreatedGreaterThanEqualsAndDateCreatedLessThanEquals(user, flexDate.date, endOfDay)
  }
}
