import org.junit.ClassRule
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.PostgreSQLContainer
import ro.esolutions.testingworkshop.TestingworkshopApplication
import ro.esolutions.testingworkshop.config.CommonPostgresqlContainer
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(classes = TestingworkshopApplication.class, webEnvironment = RANDOM_PORT)
class TestingworkshopApplicationSpecIT extends Specification {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CommonPostgresqlContainer.getInstance()

    def "check that application context actually starts"() {
        expect: 'application context is actually created. huh.. good stuff!'
    }
}
