package se.metricspace.flex

class User {
  Date dateCreated
  String uid

  static constraints = {
    dateCreated(nullable: true)
    uid(nullable: false, blank: false, unique: true)
  }

  String getEppn() { // uid + domain
    return uid+"@su.se"
  }
}
