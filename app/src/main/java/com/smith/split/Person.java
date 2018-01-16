package com.smith.split;

/**
 * Created by Farida Sabry on 1/16/18.
 */

public class Person {

    private String name;
    private int moneyOwed;
    
    public Person(String name) {
        this.name = name;
        this.moneyOwed = 0;
    }

    public String getName() {
        return name;
    }

    public int getMoneyOwed() {
        return moneyOwed;
    }

    public void addToMoneyOwed(int newAmount) {
        moneyOwed += newAmount;
    }

}
