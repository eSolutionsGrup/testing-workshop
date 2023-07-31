package testData

import ro.esolutions.testingworkshop.entities.Customer
import ro.esolutions.testingworkshop.models.CustomerModel

class CustomerGenerator {

    static Customer aCustomer(Map overrides = [:]) {
        Map defaultValues = [
                id      : 1,
                name    : 'Alex',
                isActive: true,
                type    : Customer.Type.LOYAL
        ]
        defaultValues << overrides
        new Customer(defaultValues)
    }

    static Customer aCustomerForIt(Map overrides = [:]) {
        Map defaultValues = [
                id      : 1,
                name    : 'customerName',
                isActive: true,
                type    : Customer.Type.LOYAL
        ]
        defaultValues << overrides
        new Customer(defaultValues)
    }

    static CustomerModel aCustomerModel(Map overrides = [:]) {
        Map defaultValues = [
                id      : 1,
                name    : 'Alex',
                type    : Customer.Type.LOYAL
        ]
        defaultValues << overrides
        new CustomerModel(defaultValues)
    }
}
