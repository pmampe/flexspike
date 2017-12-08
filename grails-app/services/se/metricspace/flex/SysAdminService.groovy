package se.metricspace.flex

import grails.core.GrailsApplication
import java.sql.DriverManager
import java.sql.ResultSet

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
          statement = connection.prepareStatement("select * from calendar order by datum;", ResultSet.FETCH_FORWARD, ResultSet.CONCUR_READ_ONLY)
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

  void loadReportedTimesFromOldSystem() {
    String jdbcurl = grailsApplication.config.oldsuflex.jdbcurl
    String jdbcuser = grailsApplication.config.oldsuflex.jdbcuser
    String jdbcpassword = grailsApplication.config.oldsuflex.jdbcpassword
    java.sql.Connection connection = null
    java.sql.PreparedStatement statement = null
    java.sql.ResultSet resultSet = null
    try {
        connection = java.sql.DriverManager.getConnection(jdbcurl, jdbcuser, jdbcpassword)
        User.findAllByEppnIsNotNull([sort: 'eppn', order: 'asc']).each { User user ->
            try {
                statement = connection.prepareStatement("select * from reportedtime where uid = ? order by datum;", ResultSet.FETCH_FORWARD, ResultSet.CONCUR_READ_ONLY)
                statement.setString(1, user.getUid())
                resultSet = statement.executeQuery()
                while(resultSet.next()) {
                    Date date = resultSet.getDate('datum')
                    String uid = resultSet.getString('uid')
                    int startHour = resultSet.getInt('starthour')
                    int startMinute = resultSet.getInt('startminute')
                    int endHour = resultSet.getInt('endhour')
                    int endMinute = resultSet.getInt('endminute')
                    int lunchLength = resultSet.getInt('lunchlength')
                    int dailyDelta = resultSet.getInt('dailydelta')
                    int dailyTotal = resultSet.getInt('dailytotal')
                    int absentAllDay = resultSet.getInt('absentallday')
                    String comment = resultSet.getString('comment')
                    FlexDate flexDate = FlexDate.findByDate(date)
                    WorkRate workRate = WorkRate.findByUserAndStartDateLessThanEqualsAndEndDateGreaterThanEquals(user, date, date)
                    ReportedTime reportedTime = ReportedTime.findByUserAndFlexDate(user, flexDate)
                    if(!reportedTime) {
                        reportedTime = ReportedTime.newInstance(user: user, flexDate: flexDate)
                    }
                    reportedTime.absentAllDay(absentAllDay>0)
                    reportedTime.comment = comment
                    reportedTime.dailyDelta = dailyDelta
                    reportedTime.dailyTotal = dailyTotal
                    reportedTime.endTime = endHour*60+endMinute
                    reportedTime.startTime = startHour*60+startMinute
                    reportedTime.workRate = workRate
                    reportedTime.save(flush: true, failOnError)
                }
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
            }
        }
    } catch(Throwable exception) {
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
          statement = connection.prepareStatement("select distinct(uid) from reportedtime time where datum>=curdate()-731 order by uid;", ResultSet.FETCH_FORWARD, ResultSet.CONCUR_READ_ONLY)
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

  void loadWorkRatesFromOldSystem() {
      String jdbcurl = grailsApplication.config.oldsuflex.jdbcurl
      String jdbcuser = grailsApplication.config.oldsuflex.jdbcuser
      String jdbcpassword = grailsApplication.config.oldsuflex.jdbcpassword
      java.sql.Connection connection = null
      java.sql.PreparedStatement statement = null
      java.sql.ResultSet resultSet = null
      try {
          connection = java.sql.DriverManager.getConnection(jdbcurl, jdbcuser, jdbcpassword)
          User.findAll([sort: 'eppn', order: 'asc']).each { User user ->
            statement = connection.prepareStatement("select * from workrate where uid=?;", ResultSet.FETCH_FORWARD, ResultSet.CONCUR_READ_ONLY)
            statement.setString(1, user.eppn.substring(0, user.eppn.indexOf("@")))
            resultSet = statement.executeQuery()
            while(resultSet.next()) {
                Date startDate = resultSet.getDate('startdate')
                Date endDate = resultSet.getDate('enddate')
                int rate = resultSet.getInt('rate')
                int morate = resultSet.getInt('morate')
                int turate = resultSet.getInt('turate')
                int werate = resultSet.getInt('werate')
                int thrate = resultSet.getInt('thrate')
                int frrate = resultSet.getInt('thrate')
                WorkRate workRate
                if(!startDate) {
                    if(!endDate) {
                        workRate = WorkRate.findByUserAndStartDateIsNullAndEndDateIsNull(user)
                    } else {
                        workRate = WorkRate.findByUserAndStartDateIsNullAndEndDate(user, FlexDate.findByDate(endDate))
                    }
                } else {
                    if(!endDate) {
                        workRate = WorkRate.findByUserAndEndDateIsNullAndStartDate(user, FlexDate.findByDate(startDate))
                    } else {
                        workRate = WorkRate.findByUserAndEndDateAndStartDate(user, FlexDate.findByDate(endDate), FlexDate.findByDate(startDate))
                    }
                }
                if(!workRate) {
                    workRate = WorkRate.newInstance(user: user, startDate: FlexDate.findByDate(startDate), endDate: FlexDate.findByDate(endDate))
                }
                workRate.rate = rate
                workRate.rateMonday = morate
                workRate.rateTuesday = turate
                workRate.rateWednesday = werate
                workRate.rateThursday = thrate
                workRate.rateFriday = frrate
                workRate.save(flush: true, failOnError: true)
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
