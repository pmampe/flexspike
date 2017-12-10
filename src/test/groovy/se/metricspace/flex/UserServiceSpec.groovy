package se.metricspace.flex

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class UserServiceSpec extends Specification implements ServiceUnitTest<UserService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test negative caladmin"() {
        given:

        when:
        boolean isCal = service.isCalenderAdmin("donald_duck")

        then:
        !isCal
    }

    void "test positive caladmin"() {
        given:

        when:
        boolean isCal = service.isCalenderAdmin("max")

        then:
        isCal
    }
}
