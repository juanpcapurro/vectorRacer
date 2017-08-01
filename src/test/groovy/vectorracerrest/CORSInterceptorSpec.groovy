package vectorracerrest


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CORSInterceptor)
class CORSInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test CORS interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"CORS")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
