package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
Customer customer;
@BeforeEach
    public void setUp(){
customer = new Customer();
}


@Test
public void shouldAddCharge(){
    AccountRecord record = new AccountRecord();
    record.setCharge(10000);
    assertTrue(customer.addCharge(record));
}

@Test
public void shouldReturnZero(){
    assertEquals(0, customer.getBalance());
}

    @Test
    public void shouldGetPositiveBalance(){
        AccountRecord record1 = new AccountRecord();
        record1.setCharge(10000);
        customer.addCharge(record1);
        AccountRecord record2 = new AccountRecord();
        record2.setCharge(10000);
        customer.addCharge(record2);
        AccountRecord record3 = new AccountRecord();
        record3.setCharge(10000);
        customer.addCharge(record3);
        assertEquals(30000, customer.getBalance());
        record3.setCharge(50000000);
        assertEquals(50020000, customer.getBalance());

    }

    @Test
    public void shouldGetNegativeBalance(){
        AccountRecord record1 = new AccountRecord();
        record1.setCharge(-10000);
        customer.addCharge(record1);
        AccountRecord record2 = new AccountRecord();
        record2.setCharge(-10000);
        customer.addCharge(record2);
        AccountRecord record3 = new AccountRecord();
        record3.setCharge(-10000);
        customer.addCharge(record3);
        assertEquals(-30000, customer.getBalance());
        record3.setCharge(-50000000);
        assertEquals(-50020000, customer.getBalance());
    }

    @Test
    public void shouldGetPosAndNegBalance(){
        AccountRecord record1 = new AccountRecord();
        record1.setCharge(10000);
        customer.addCharge(record1);
        AccountRecord record2 = new AccountRecord();
        record2.setCharge(-10000);
        customer.addCharge(record2);
        AccountRecord record3 = new AccountRecord();
        record3.setCharge(-10000);
        customer.addCharge(record3);
        assertEquals(-10000, customer.getBalance());
        record3.setCharge(50000000);
        assertEquals(50000000, customer.getBalance());
    }

@Test
    public void shouldPrintIdNameBalance(){
    assertEquals("Id: 0, Name: null, Balance: 0", customer.toString());
    customer.setId(1);
    customer.setName("Reuben Industries");
    AccountRecord record = new AccountRecord();
    record.setCharge(10000000);
    customer.addCharge(record);
    assertEquals("Id: 1, Name: Reuben Industries, Balance: 10000000", customer.toString());
    customer.setName("Reuben Conglomerate");
    assertEquals("Id: 1, Name: Reuben Conglomerate, Balance: 10000000", customer.toString());
    AccountRecord newRecord = new AccountRecord();
    newRecord.setCharge(-15000000);
    customer.addCharge(newRecord);
    assertEquals("Id: 1, Name: Reuben Conglomerate, Balance: -5000000", customer.toString());


}


}
