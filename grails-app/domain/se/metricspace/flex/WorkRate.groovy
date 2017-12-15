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

  static WorkRate findForUserAndDate(User user, Date date) {
    WorkRate workRate = null
    FlexDate flexDate = FlexDate.findByDate(date)
    WorkRate.findAllByUser(user).each { WorkRate wr ->
      if(wr.startDate && wr.startDate.before(flexDate)) {
        if(wr.endDate) {
          if(wr.endDate.after(flexDate)) {
            workRate = wr
          }
        } else {
          workRate = wr
        }
      } else {
        if(wr.endDate) {
          if(wr.endDate.after(flexDate)) {
            workRate = wr
          }
        } else {
          workRate = wr
        }
      }
    }
    return workRate
  }

  int getAverageRate() {
      int workRate = rate/100
      int avgRate = (rateMonday+rateTuesday+rateWednesday+rateThursday+rateFriday+499)/500
      return (avgRate>0) ? avgRate : workRate
  }
}
