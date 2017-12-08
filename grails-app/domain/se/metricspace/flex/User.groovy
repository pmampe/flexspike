package se.metricspace.flex

class User {
  Date dateCreated
  String eppn

  static constraints = {
    dateCreated(nullable: true)
    eppn(nullable: false, blank: false, unique: true)
  }

  String getUid() { // eppn without domain
    return eppn.substring(0, eppn.indexOf('@'))
  }
}
