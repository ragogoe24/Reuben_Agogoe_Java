package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepo;

    @BeforeEach
    public void setUp(){
        customerRepo.deleteAll();
    }

    @Test
    public void shouldCreateCustomer(){
        Customer customer = new Customer("Reuben", "Agogoe", "ragogoe@princeton.edu",
                "+16092123905");
        customer.setAddress1("2004 Frist Campus Center");
        customer.setCity("Princeton");
        customer.setCompany("Princeton University");
        customer.setState("New Jersey");
        customer.setPostalCode("08540");
        customer.setCountry("US");
        customer = customerRepo.save(customer);

        Optional<Customer> cust = customerRepo.findById(customer.getId());
        assertEquals(cust.get(), customer);

    }

    @Test
    public void shouldUpdateCustomerRecord(){
        Customer customer = new Customer("Reuben", "Agogoe", "ragogoe@princeton.edu",
                "+16092123905");
        customer.setAddress1("2004 Frist Campus Center");
        customer.setCity("Princeton");
        customer.setCompany("Princeton University");
        customer.setState("New Jersey");
        customer.setPostalCode("08540");
        customer.setCountry("US");
        customer = customerRepo.save(customer);

        customer.setState("California");
        customer.setCity("San Francisco");

        customer = customerRepo.save(customer);
        Optional<Customer> cust = customerRepo.findById(customer.getId());
        assertEquals(cust.get(), customer);

    }

    @Test
    public void shouldDeleteCustomerById(){
        Customer customer = new Customer("Reuben", "Agogoe", "ragogoe@princeton.edu",
                "+16092123905");
        customer.setAddress1("2004 Frist Campus Center");
        customer.setCity("Princeton");
        customer.setCompany("Princeton University");
        customer.setState("New Jersey");
        customer.setPostalCode("08540");
        customer.setCountry("US");

        customer = customerRepo.save(customer);
        Optional<Customer> cust1 = customerRepo.findById(customer.getId());
        assertEquals(cust1.get(), customer);

        customerRepo.deleteById(customer.getId());
        cust1 = customerRepo.findById(customer.getId());
        assertTrue(cust1.isEmpty());

    }


    @Test
    public void shouldFindCustomerById(){
        Customer customer = new Customer("Reuben", "Agogoe", "ragogoe@princeton.edu",
                "+16092123905");
        customer.setAddress1("2004 Frist Campus Center");
        customer.setCity("Princeton");
        customer.setCompany("Princeton University");
        customer.setState("New Jersey");
        customer.setPostalCode("08540");
        customer.setCountry("US");

        customer = customerRepo.save(customer);
        Optional<Customer> cust1 = customerRepo.findById(customer.getId());
        assertEquals(cust1.get(), customer);

        Customer customer2 = new Customer("Janelle", "Umana", "jumana@princeton.edu",
                "+16572671133");
        customer2.setAddress1("2004 Frist Campus Center");
        customer2.setCity("Princeton");
        customer2.setCompany("Princeton University");
        customer2.setState("New Jersey");
        customer2.setPostalCode("08540");
        customer2.setCountry("US");

        customer2 = customerRepo.save(customer2);
        Optional<Customer> cust2 = customerRepo.findById(customer2.getId());
        assertEquals(cust2.get(), customer2);


    }

    @Test
    public void shouldFindAllCustomersByState(){
        Customer customer = new Customer("Reuben", "Agogoe", "ragogoe@princeton.edu",
                "6092123905");
        customer.setAddress1("2004 Frist Campus Center");
        customer.setCity("Princeton");
        customer.setCompany("Princeton University");
        customer.setState("New Jersey");
        customer.setPostalCode("08540");
        customer.setCountry("US");

        customer = customerRepo.save(customer);

        Customer customer2 = new Customer("Janelle", "Umana", "jumana@princeton.edu",
                "6572671133");
        customer2.setAddress1("2004 Frist Campus Center");
        customer2.setCity("Princeton");
        customer2.setCompany("Princeton University");
        customer2.setState("New Jersey");
        customer2.setPostalCode("08540");
        customer2.setCountry("US");
        customer2 = customerRepo.save(customer2);

        Customer customer3 = new Customer("Solo", "Akyea", "soloakyea@gmail.com",
                "0504442654");
        customer3.setAddress1("Accra");
        customer3.setCity("Accra");
        customer3.setCompany("Princeton University");
        customer3.setPostalCode("00233");
        customer3.setCountry("Ghana");

        customer3 = customerRepo.save(customer3);
        List<Customer> customers = customerRepo.findAllCustomersByState("New Jersey");
        assertEquals(customers.size(), 2);

        customers = customerRepo.findAllCustomersByState("California");
        assertEquals(customers.size(), 0);


    }

}
