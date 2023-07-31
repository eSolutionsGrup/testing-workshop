package ro.esolutions.testingworkshop;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import ro.esolutions.testingworkshop.config.CommonPostgresqlContainer;

@SpringBootTest
class TestingworkshopApplicationTests {

	@ClassRule
	public static PostgreSQLContainer<CommonPostgresqlContainer> postgreSQLContainer = CommonPostgresqlContainer.getInstance();

	@Test
	void contextLoads() {
		System.out.println("application context is actually created. huh.. good stuff!");
	}

}
