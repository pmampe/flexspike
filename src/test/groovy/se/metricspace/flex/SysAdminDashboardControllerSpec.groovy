package se.metricspace.flex

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class SysAdminDashboardControllerSpec extends Specification implements ControllerUnitTest<SysAdminDashboardController> {

    def setup() {
        controller.sysAdminService = Mock(SysAdminService)
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
