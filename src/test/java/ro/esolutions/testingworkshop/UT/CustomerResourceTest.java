package ro.esolutions.testingworkshop.UT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import ro.esolutions.testingworkshop.entities.Customer;
import ro.esolutions.testingworkshop.rest.CustomerResource;
import ro.esolutions.testingworkshop.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ro.esolutions.testingworkshop.entities.Customer.Type.LOYAL;

@ExtendWith(MockitoExtension.class)
public class CustomerResourceTest {

    @Mock
    CustomerService service;

    @InjectMocks
    CustomerResource resource;

    @Test
    @DisplayName("Find all employees rest UT")
    @Sql()
    public void findAllEmployees() {

        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder()
                .id(1L)
                .name("Gigi Masinuta")
                .type(LOYAL)
                .isActive(true)
                .build());

        when(service.findAllCustomers()).thenReturn(customers);

        ResponseEntity<List<Customer>> foundCustomersResponse = resource.findAllCustomers();
        assertEquals(1, foundCustomersResponse.getBody().size());
        verify(service, times(1)).findAllCustomers();
    }
}
