package se.metricspace.flex

import grails.core.GrailsApplication
import java.sql.DriverManager

class SysAdminService {
  GrailsApplication grailsApplication

  void loadDatesFromOldSystem() {
      String jdbcurl = grailsApplication.config.oldsuflex.jdbcurl
      String jdbcuser = grailsApplication.config.oldsuflex.jdbcuser
      String jdbcpassword = grailsApplication.config.oldsuflex.jdbcpassword
      java.sql.Connection connection = null
      java.sql.PreparedStatement statement = null
      java.sql.ResultSet resultSet = null
      try {
          connection = java.sql.DriverManager.getConnection(jdbcurl, jdbcuser, jdbcpassword)
          statement = connection.prepareStatement("select * from calendar order by datum;")
          resultSet = statement.executeQuery()
          while(resultSet.next()) {
              Date date = resultSet.getDate("datum")
              int startHour = resultSet.getInt("mandstart")
              int endHour = resultSet.getInt("mandend")
              int fullTime = resultSet.getInt("fulltime")
              String description = resultSet.getString("description")?.trim()
              if(description?.endsWith("rkortad dag")) {
                  description = "Förkortad dag"
              }
              FlexDate flexDate = FlexDate.findByDate(date)
              if(!flexDate) {
                  flexDate = FlexDate.newInstance(flexDate: date)
              }
              flexDate.date = date
              flexDate.description = description
              flexDate.endHour = endHour
              flexDate.fullTime = fullTime
              flexDate.startHour = startHour
              flexDate.updatedByEppn = "flexinit@su.se"
              flexDate.save(flush: true, failOnError: true)
          }
      } catch (Throwable exception) {
          log.info "Problem accessing old database: ${exception.getMessage()}"
      } finally {
          if(resultSet) {
            try {
                resultSet.close()
            } catch (Throwable exception) {
            } 
            resultSet = null
          }
          if(statement) {
            try {
                statement.close()
            } catch (Throwable exception) {
            } 
            statement = null
          }
          if(connection) {
            try {
                connection.close()
            } catch (Throwable exception) {
            } 
            connection = null
          }
      }
  }

  void loadUsersFromOldSystem() {
      String jdbcurl = grailsApplication.config.oldsuflex.jdbcurl
      String jdbcuser = grailsApplication.config.oldsuflex.jdbcuser
      String jdbcpassword = grailsApplication.config.oldsuflex.jdbcpassword
      java.sql.Connection connection = null
      java.sql.PreparedStatement statement = null
      java.sql.ResultSet resultSet = null
      try {
          connection = java.sql.DriverManager.getConnection(jdbcurl, jdbcuser, jdbcpassword)
          statement = connection.prepareStatement("select distinct(uid) from reportedtime time where datum>=curdate()-550 order by uid;")
          resultSet = statement.executeQuery()
          while(resultSet.next()) {
              String uid = resultSet.getString("uid")
              if(!uid.contains("@")) {
                  uid += "@su.se"
              }
              User user = User.findByEppn(uid)
              if(!user) {
                  User.newInstance(eppn: uid).save(flush: true, failOnError: true)
              }
          }
      } catch (Throwable exception) {
          log.info "Problem accessing old database: ${exception.getMessage()}"
      } finally {
          if(resultSet) {
            try {
                resultSet.close()
            } catch (Throwable exception) {
            } 
            resultSet = null
          }
          if(statement) {
            try {
                statement.close()
            } catch (Throwable exception) {
            } 
            statement = null
          }
          if(connection) {
            try {
                connection.close()
            } catch (Throwable exception) {
            } 
            connection = null
          }
      }
    }
}
