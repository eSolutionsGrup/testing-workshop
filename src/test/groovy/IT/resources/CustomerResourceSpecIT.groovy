package IT.resources

import org.junit.ClassRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.testcontainers.containers.PostgreSQLContainer
import ro.esolutions.testingworkshop.TestingworkshopApplication
import ro.esolutions.testingworkshop.config.CommonPostgresqlContainer
import ro.esolutions.testingworkshop.entities.Customer
import ro.esolutions.testingworkshop.repositories.CustomerRepository
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import static testData.CustomerGenerator.aCustomer
import static testData.CustomerGenerator.aCustomerForIt
import static testData.CustomerGenerator.aCustomerModel

@ContextConfiguration
@SpringBootTest(classes = TestingworkshopApplication.class, webEnvironment = RANDOM_PORT)
class CustomerResourceSpecIT extends Specification{

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CommonPostgresqlContainer.getInstance()

    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    CustomerRepository customerRepository

    @SqlGroup([
            @Sql(value = '/sql/insert_customers.sql', executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = '/sql/clean_up.sql', executionPhase = AFTER_TEST_METHOD)
    ])
    def 'Find all customers rest IT'() {
        given:
        def url = '/customer/all'
        def customer = aCustomerForIt()

        when:
        def result = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {})

        then:
        result.getStatusCode() == HttpStatus.OK
        result.getBody() == [customer]
    }

    @SqlGroup([
            @Sql(value = '/sql/clean_up.sql', executionPhase = AFTER_TEST_METHOD)
    ])
    def 'Save new customer rest IT'() {
        given:
        def customerModel = aCustomerModel(id: -2, type: Customer.Type.NEW, name: 'Gigi Masinuta')

        when:
        def result = restTemplate.postForEntity('/customer/save', customerModel, null)

        then:
        customerRepository.findAll().size() == 1
        result.statusCode == HttpStatus.OK
    }

}
