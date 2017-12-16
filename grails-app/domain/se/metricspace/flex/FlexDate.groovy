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

  String getDateAsString() {
    date.format('yyyy-MM-dd')    
  }

  boolean after(FlexDate flexDate) {
    return (date == flexDate.date || date.after(flexDate.date))
  }

  boolean before(FlexDate flexDate) {
    return (date == flexDate.date || date.before(flexDate.date))
  }

  static FlexDate getCurrentDay() {
    return FlexDate.findByDate(Date.parse('yyyy-MM-dd', Date.newInstance().format('yyyy-MM-dd')), [max: 1])
  }
}
