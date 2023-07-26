package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repo;

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(){
        System.out.println("here 3");

        return repo.findAll();
    }

    @GetMapping("/customers/state/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomersByState(@PathVariable String name){
        System.out.println("here 2");

        return repo.findAllCustomersByState(name);
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable int id){
        System.out.println("here 1");

        Optional<Customer> returnVal = repo.findById(id);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer){
        System.out.println("customer created " + customer);
        return repo.save(customer);
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer, @PathVariable int id){
       Optional<Customer> returnVal =  repo.findById(id);
       if(returnVal.isPresent()){
           Customer updatedCustomer = returnVal.get();
           updatedCustomer.setFirstName(customer.getFirstName());
           updatedCustomer.setLastName(customer.getLastName());
           updatedCustomer.setEmail(customer.getEmail());
           updatedCustomer.setCompany(customer.getCompany());
           updatedCustomer.setPhone(customer.getPhone());
           updatedCustomer.setAddress1(customer.getAddress1());
           updatedCustomer.setAddress2(customer.getAddress2());
           updatedCustomer.setCity(customer.getCity());
           updatedCustomer.setState(customer.getState());
           updatedCustomer.setPostalCode(customer.getPostalCode());
           updatedCustomer.setCountry(customer.getCountry());
           repo.save(updatedCustomer);
       }

    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id){
        Optional<Customer> returnVal = repo.findById(id);
        if(returnVal.isPresent()){
            repo.deleteById(id);

        }
    }

}
