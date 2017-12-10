package se.metricspace.flex

import groovy.transform.CompileStatic

@CompileStatic
class SessionUser {
  private String itsDisplayName
  private String itsEppn
  private  Role itsRole
  boolean itsEmployee

  SessionUser(String theEppn, Role theRole, boolean isEmployee, String theDisplayName) {
    itsDisplayName = theDisplayName?.trim()
    itsEppn = theEppn?.trim()
    itsRole = theRole
    itsEmployee = isEmployee
  }

  String getDisplayName() {
    return itsDisplayName
  }

  String getEppn() {
    return itsEppn
  }

  Role getRole() {
    return itsRole
  }

  String getUid() {
    return itsEppn?.substring(0, itsEppn?.indexOf("@"))
  }

  boolean isEmployee() {
    return itsEmployee
  }

  boolean isSuPerson() {
    return itsEppn?.endsWith("@su.se")
  }
}
