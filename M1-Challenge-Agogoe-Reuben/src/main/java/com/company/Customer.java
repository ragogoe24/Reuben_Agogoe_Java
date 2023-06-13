package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return charges.stream().mapToInt(charge-> charge.getCharge()).reduce(0, (total, next) -> total+next);
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    public boolean addCharge(AccountRecord record){
        charges.add(record);
        return charges.get(charges.size()-1) == record;
    }

    @Override
    public String toString() {
        //update this
        return "Id: " + getId() + "," + " Name: " + getName() + ","+ " Balance: " + getBalance();
    }
}
