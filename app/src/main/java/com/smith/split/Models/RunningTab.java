package com.smith.split.Models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Farida Sabry on 1/16/18.
 */

public class RunningTab {

    private Set<Person> peopleOwingMoney;

    public RunningTab() {
        peopleOwingMoney = new HashSet<>();
    }

    public Set<Person> getPeopleOwingMoney() {
        return peopleOwingMoney;
    }

    public void addPerson(Person person) {
        peopleOwingMoney.add(person);
    }



}
