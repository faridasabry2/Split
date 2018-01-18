package com.smith.split;

/**
 * Created by Farida Sabry on 1/16/18.
 */

public class Person {

    private String name;
    private float moneyOwed;
    
    public Person(String name) {
        this.name = name;
        this.moneyOwed = 0;
    }

    public String getName() {
        return name;
    }

    public float getMoneyOwed() {
        return moneyOwed;
    }

    public void addToMoneyOwed(float newAmount) {
        moneyOwed += newAmount;
    }

}
