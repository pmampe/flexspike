package se.metricspace.flex

class WorkRate {
  String comment
  Date dateCreated
  FlexDate endDate
  Date lastUpdated
  int rate = 10000 
  int rateMonday = 10000 
  int rateTuesday = 10000 
  int rateWednesday = 10000 
  int rateThursday = 10000 
  int rateFriday = 10000 
  FlexDate startDate
  User user

  static constraints = {
    comment(nullable: true)
    dateCreated(nullable: true)
    endDate(nullable: true)
    lastUpdated(nullable: true)
    startDate(nullable: true)
    user(nullable: false)
  }
}
