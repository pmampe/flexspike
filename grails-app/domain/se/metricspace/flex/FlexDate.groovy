package se.metricspace.flex

class FlexDate {
  Date date
  Date dateCreated
  String description
  int endHour = 15
  int fullTime = 0
  Date lastUpdated
  int startHour = 9
  String updatedByEppn

  static constraints = {
    date(nullable: false, unique: true)
    dateCreated(nullable: true)
    description(nullable: true, blank: true)
    endHour(nullable: false)
    fullTime(nullable: false)
    lastUpdated(nullable: true)
    startHour(nullable: false)
    updatedByEppn(nullable: true, blank: true)
  }
}
