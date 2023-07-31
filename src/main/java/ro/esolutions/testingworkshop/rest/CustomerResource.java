package ro.esolutions.testingworkshop.rest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.esolutions.testingworkshop.entities.Customer;
import ro.esolutions.testingworkshop.models.CustomerModel;
import ro.esolutions.testingworkshop.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerResource {

    @NonNull
    CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @PostMapping("/save")
    public void saveCustomer(@RequestBody final CustomerModel customerModel) {
        customerService.saveCustomer(customerModel);
    }

    @PostMapping("/edit")
    public void editCustomer(@RequestBody final CustomerModel customerModel) {
        customerService.editCustomer(customerModel);
    }

    @DeleteMapping
    public void deleteAllCustomers() {
        customerService.deleteAllCustomers();
    }

}
