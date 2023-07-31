package UT.resources

import org.springframework.http.ResponseEntity
import ro.esolutions.testingworkshop.rest.CustomerResource
import ro.esolutions.testingworkshop.services.CustomerService
import spock.lang.Specification
import spock.lang.Subject

import static testData.CustomerGenerator.aCustomer

class CustomerResourceSpec extends Specification{

    def customerService = Mock(CustomerService)

    @Subject
    def customerResource = new CustomerResource(customerService)

    def 'Find all customers rest UT'(){
        given:
        def aCustomerList = [aCustomer()]

        when:
        def result = customerResource.findAllCustomers()

        then:
        1 * customerService.findAllCustomers() >> aCustomerList
        0 * _

        and:
        result == ResponseEntity.ok(aCustomerList)
    }

}
