package se.metricspace.flex

import groovy.transform.CompileStatic

@CompileStatic
class SessionUser {
  private String itsDisplayName
  private String itsEppn
  private  Role itsRole
  List<String> itsAffiliations

  SessionUser(String theEppn, Role theRole, List<String> theAffiliations, String theDisplayName) {
    itsDisplayName = theDisplayName?.trim()
    itsEppn = theEppn?.trim()
    itsRole = theRole
    itsAffiliations = theAffiliations?.sort()
  }

  String getDisplayName() {
    return itsEppn
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
    return itsAffiliations?.contains('employee')
  }
}
