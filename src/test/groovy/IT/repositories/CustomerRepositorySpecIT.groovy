package IT.repositories

import org.junit.ClassRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.testcontainers.containers.PostgreSQLContainer
import ro.esolutions.testingworkshop.TestingworkshopApplication
import ro.esolutions.testingworkshop.config.CommonPostgresqlContainer
import ro.esolutions.testingworkshop.repositories.CustomerRepository
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import static testData.CustomerGenerator.aCustomerForIt

@ContextConfiguration
@SpringBootTest(classes = TestingworkshopApplication.class, webEnvironment = RANDOM_PORT)
class CustomerRepositorySpecIT extends Specification{

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CommonPostgresqlContainer.getInstance()

    @Autowired
    CustomerRepository customerRepository

    @SqlGroup([
            @Sql(value = '/sql/insert_customers.sql', executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = '/sql/clean_up.sql', executionPhase = AFTER_TEST_METHOD)
    ])
    def 'Find all customers repository IT'() {
        given:
        def customer = aCustomerForIt()

        when:
        def result = customerRepository.findAll()

        then:
        result == [customer]
        result.size() == 1
    }

}
