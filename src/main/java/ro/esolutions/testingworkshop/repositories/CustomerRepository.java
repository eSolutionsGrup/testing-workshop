package ro.esolutions.testingworkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.testingworkshop.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
