package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public List<Customer> createCustomerRecords(List<String[]> customerData){
        List<Customer> customers = new ArrayList<>();
        customerData.forEach(data->{
            AccountRecord customerRecord = createAccountRecord(data);

            List<Customer> result = customers.stream().
                    filter(customer -> customer.getId()==Integer.parseInt(data[0]) &&
                            customer.getName().equals(data[1]))
                    .collect(Collectors.toList());

            // means there's no customer already created
            if(result.size() == 0){
                Customer newCustomer = createCustomer(data);
                newCustomer.addCharge(customerRecord);
                customers.add(newCustomer);
            } // customer has already been created so add new charge
            else{
                boolean added = result.get(0).addCharge(customerRecord);
                if(!added){
                    customers.remove(result.get(0));
                }

            }
        });

        return customers;
    }

    private AccountRecord createAccountRecord(String[] data){
        AccountRecord customerRecord = new AccountRecord();
        customerRecord.setCharge(Integer.parseInt(data[2]));
        customerRecord.setChargeDate(data[3]);
        return customerRecord;
    }
    private Customer createCustomer(String[] data){
        Customer newCustomer = new Customer();
        newCustomer.setId(Integer.parseInt(data[0]));
        newCustomer.setName(data[1]);
        return newCustomer;
    }

    public static void main(String[] args) {
        //Update this
        System.out.println("Positive accounts:");
        // System.out.println(Arrays.toString(customerData.get(0)));
        Main mainConstructor = new Main();
        List<Customer> customers = mainConstructor.createCustomerRecords(customerData);
        List<Customer> positiveAccounts = customers.stream().
                filter(customer -> customer.getBalance() > 0).
                collect(Collectors.toList());
        positiveAccounts.forEach(System.out::println);
        System.out.println("Negative accounts:");
        List<Customer> negativeAccounts = customers.stream().
                filter(customer -> customer.getBalance() < 0).
                collect(Collectors.toList());
        negativeAccounts.forEach(System.out::println);

    }
}
