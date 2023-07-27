package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

  @SpringBootTest
  @AutoConfigureMockMvc
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void shouldReturnCreatedStatus() throws Exception{
        Customer customer = new Customer("Reuben K", "Agogoe", "ragogoe@princeton.edu",
                "+16092123905");
        customer.setAddress1("2004 Frist Campus Center");
        customer.setCity("Princeton");
        customer.setCompany("Princeton University");
        customer.setState("New Jersey");
        customer.setPostalCode("08540");
        customer.setCountry("US");

        String inputJson = mapper.writeValueAsString(customer);
        mockMvc.perform(
                post("/customers")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldDeleteCustomerAndReturn204StatusCode() throws Exception{
        mockMvc.perform(
                delete("/customers/2")
        ).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnCustomersByStateAndOkResponse() throws Exception{

        mockMvc.perform(
                get("/customers/state/New Jersey")
        )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldReturnCustomerByIdAndOkResponse() throws Exception{
        mockMvc.perform(
                get("/customers/2")
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllCustomersAndOkResponse() throws Exception{
        mockMvc.perform(
                get("/customers")
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateWhenInDatabaseAndReturnNoContent() throws Exception{
        Customer customer = new Customer("Adam", "Johnson", "rjo@gmail.com",
                "+11111111111");
        customer.setAddress1("10 Skyport Drive");
        customer.setCity("San Jose");
        customer.setCompany("Marriott");
        customer.setState("California");
        customer.setPostalCode("33333");
        customer.setCountry("US");

        String inputJson = mapper.writeValueAsString(customer);
        mockMvc.perform(
                put("/customers/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isNoContent());

    }
}
