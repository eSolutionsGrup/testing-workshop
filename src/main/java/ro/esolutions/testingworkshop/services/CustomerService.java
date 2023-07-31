package ro.esolutions.testingworkshop.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.testingworkshop.entities.Customer;
import ro.esolutions.testingworkshop.models.CustomerModel;
import ro.esolutions.testingworkshop.repositories.CustomerRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    @NonNull
    CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public void saveCustomer(final CustomerModel customerModel) {
        Customer customerToBeSaved = Customer.builder()
                .name(customerModel.getName())
                .type(customerModel.getType())
                .isActive(true)
                .build();
        customerRepository.save(customerToBeSaved);
    }

    public void editCustomer(final CustomerModel customerModel) {
        Customer customerToBeEdited = customerRepository
                .findById(customerModel.getId())
                .map(customerEntity -> {
                    customerEntity.setName(customerModel.getName());
                    customerEntity.setType(customerModel.getType());
                    return customerEntity;
                })
                .orElseThrow(() -> new RuntimeException("Failed to find customer with id " + customerModel.getId()));
        customerRepository.save(customerToBeEdited);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }
}
