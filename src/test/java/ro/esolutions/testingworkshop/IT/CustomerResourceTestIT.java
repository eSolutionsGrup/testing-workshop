package ro.esolutions.testingworkshop.IT;

import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.testcontainers.containers.PostgreSQLContainer;
import ro.esolutions.testingworkshop.TestingworkshopApplication;
import ro.esolutions.testingworkshop.config.CommonPostgresqlContainer;
import ro.esolutions.testingworkshop.entities.Customer;
import ro.esolutions.testingworkshop.rest.CustomerResource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(classes = TestingworkshopApplication.class, webEnvironment = RANDOM_PORT)
public class CustomerResourceTestIT {

    @ClassRule
    public static PostgreSQLContainer<CommonPostgresqlContainer> postgreSQLContainer = CommonPostgresqlContainer.getInstance();

    @Autowired
    CustomerResource customerResource;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Find all customers rest IT")
    @SqlGroup({
            @Sql(value = "/sql/insert_customers.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "/sql/clean_up.sql", executionPhase = AFTER_TEST_METHOD)
    })
    public void findAllCustomers() {
        String url = "/customer/all";

        ResponseEntity<List<Customer>> result = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {
        });
        assertEquals(1, result.getBody().size());
        assertEquals(OK, result.getStatusCode());
    }

}
