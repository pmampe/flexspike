package se.metricspace.flex

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class PublicControllerSpec extends Specification implements ControllerUnitTest<PublicController> {

    def setup() {
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
