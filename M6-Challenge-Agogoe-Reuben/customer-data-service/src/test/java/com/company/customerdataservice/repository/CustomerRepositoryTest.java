package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepo;

    @BeforeEach
    public void setUp(){
        customerRepo.deleteAll();
    }

    @Test
    public void createCustomer(){
        Customer customer = new Customer("Reuben", "Agogoe", "ragogoe@princeton.edu",
                "6092123905");
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
    public void updateCustomerRecord(){
        Customer customer = new Customer("Reuben", "Agogoe", "ragogoe@princeton.edu",
                "6092123905");
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
    
}
