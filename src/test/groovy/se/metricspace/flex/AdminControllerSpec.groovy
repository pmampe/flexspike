package se.metricspace.flex

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class AdminControllerSpec extends Specification implements ControllerUnitTest<AdminController> {

    def setup() {
        controller.sysAdminService = Mock(SysAdminService)
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
