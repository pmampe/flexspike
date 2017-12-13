package se.metricspace.flex

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class LoginInterceptorSpec extends Specification implements InterceptorUnitTest<LoginInterceptor> {

    def setup() {
        interceptor.userService = Mock(UserService)
    }

    def cleanup() {

    }

    void "Test login interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"login")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
