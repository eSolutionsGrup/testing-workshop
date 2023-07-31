package ro.esolutions.testingworkshop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.esolutions.testingworkshop.entities.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerModel {

    private Long id;

    private String name;

    private Customer.Type type;

}
