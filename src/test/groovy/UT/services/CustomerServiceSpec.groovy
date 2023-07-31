package UT.services

import ro.esolutions.testingworkshop.repositories.CustomerRepository
import ro.esolutions.testingworkshop.services.CustomerService
import spock.lang.Specification
import spock.lang.Subject

import static testData.CustomerGenerator.aCustomer

class CustomerServiceSpec extends Specification {

    def mockCustomerRepository = Mock(CustomerRepository)

    @Subject
    def customerService = new CustomerService(mockCustomerRepository)

    def 'Find all customers service UT'(){
        given:
        def aCustomerList = [aCustomer()]

        when:
        def result = customerService.findAllCustomers()

        then:
        1 * mockCustomerRepository.findAll() >> aCustomerList
        0 * _

        and:
        result == aCustomerList
    }

}
