import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
if(Environment.isDevelopmentMode() || Environment.currentEnvironment == Environment.TEST) {
  println("### Setting up logback for development/test mode ###")
  appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
      pattern = "%d %level [%thread] %logger - %msg%n"
    }
  }
  root(INFO, ['STDOUT'])
  logger("groovyx.net.http.ParserRegistry", ERROR, ['STDOUT'], false)
  logger("grails.app.services.se.su.it.vfu", DEBUG, ['STDOUT'], false)
  //logger("org.apache.http", DEBUG, ['STDOUT'], false) //HTTPClient 4.5.2 (REST) wire logging
  //logger("httpclient.wire", DEBUG, ['STDOUT'], false) ////HTTPClient 3.1 (Abdera client ie feed) wire logging
  //logger("org.apache.commons.httpclient", DEBUG, ['STDOUT'], false) ////HTTPClient 3.1 (Abdera client ie feed) wire logging
  println("### Finished setting up logback for development/test mode ###")
} else {
  println("### Setting up logback for production mode ###")
  appender('TIME_BASED_FILE', RollingFileAppender) {
    file = "${System.properties["catalina.home"]}/logs/vfuportalen.log"
    rollingPolicy(TimeBasedRollingPolicy) {
      fileNamePattern = "${System.properties["catalina.home"]}/logs/vfuportalen.log.%d{yyyy-MM-dd}"
      maxHistory = 365
    }
    encoder(PatternLayoutEncoder) {
      pattern = "%d %level [%thread] %logger - %msg%n"
    }
  }
  root(ERROR, ['TIME_BASED_FILE'])
  logger("org.grails.web.errors", DEBUG, ['TIME_BASED_FILE'], false)
  logger("grails", INFO, ['TIME_BASED_FILE'], false)
  logger("se.su.it.vfu", INFO, ['TIME_BASED_FILE'], false)
  logger("groovyx.net.http.ParserRegistry", ERROR, ['TIME_BASED_FILE'], false)
  println("### Finished setting up logback for production mode ###")
}

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
  appender("FULL_STACKTRACE", FileAppender) {
    file = "${targetDir}/stacktrace.log"
    append = true
    encoder(PatternLayoutEncoder) {
      pattern = "%d %level [%thread] %logger - %msg%n"
    }
  }
  logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}
