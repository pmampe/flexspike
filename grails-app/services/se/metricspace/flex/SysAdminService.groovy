package se.metricspace.flex

import grails.core.GrailsApplication

class SysAdminService {
  GrailsApplication grailsApplication

  void loadDatesFromOldSystem() {
      String jdbcurl = grailsApplication.config.oldsuflex.jdbcurl
      String jdbcuser = grailsApplication.config.oldsuflex.jdbcuser
      String jdbcpassword = grailsApplication.config.oldsuflex.jdbcpassword
  }
}
