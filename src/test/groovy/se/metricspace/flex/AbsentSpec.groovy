package se.metricspace.flex

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AbsentSpec extends Specification implements DomainUnitTest<Absent> {

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
