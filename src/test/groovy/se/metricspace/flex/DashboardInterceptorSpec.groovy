package se.metricspace.flex

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class DashboardInterceptorSpec extends Specification implements InterceptorUnitTest<DashboardInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test dashboard interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"dashboard")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
