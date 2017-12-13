package se.metricspace.flex

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class DashboardControllerSpec extends Specification implements ControllerUnitTest<DashboardController> {

    def setup() {
        controller.userService = Mock(UserService)
    }

    def cleanup() {
    }

    void "test something useful"() {
        given:
        when:
        1+2
        then:
        1<2
    }
}
