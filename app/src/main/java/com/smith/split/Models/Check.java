package com.smith.split.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Farida Sabry on 1/16/18.
 */

public class Check {

    private float subTotal;
    private float tip;
    private int numberOfPeople;
    private List<Item> checkItems;
    private Map<Item, List<Person>> receiptItemMap;

    /* Constructor */
    public Check(float checkSubTotal, float tip, int numberOfPeople) {
        this.subTotal = checkSubTotal;
        this.tip = tip;
        this.numberOfPeople = numberOfPeople;
    }

    /**
     * called when a new receipt is scanned to initialize a list
     * of items given their respective labels and prices
     * @param itemLabels labels on receipt
     * @param prices price of item
     */
    public void initializeReceipt(ArrayList<String> itemLabels, ArrayList<Integer> prices) {
        receiptItemMap = new HashMap<>();
        checkItems = new ArrayList<>();

        for(int i = 0; i < itemLabels.size(); i++) {
            Item item = new Item(itemLabels.get(i), prices.get(i));
            checkItems.add(item);
        }
    }

    public float getTotal() {
        return subTotal + tip;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public List<Item> getCheckItems() {
        return checkItems;
    }

    /**
     * assigns an item to a given person
     * @param item receipt item
     * @param person person who bought said item
     */
    public void assignItem(Item item, Person person) {
        if(receiptItemMap.containsKey(item)) {
            List<Person> personList = receiptItemMap.get(item);
            personList.add(person);
            receiptItemMap.put(item, personList);
        } else {
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            receiptItemMap.put(item, personList);
        }
    }

    /**
     * splits check total equally based on number of people included
     * @return price each person should pay
     */
    public float splitEqually() {
        return getTotal() /numberOfPeople;
    }

    /**
     * splits check based on who bought which items
     * @return price each person should pay
     */
    public Set<Person> splitByItem() {

        Set<Person> personSet = new HashSet<>();

        for (Map.Entry<Item, List<Person>> entry : receiptItemMap.entrySet()) {
            int price = entry.getKey().getPrice();

            for(Person person: entry.getValue()) {
                person.addToMoneyOwed(price);
                personSet.add(person);
            }
        }

        // divide up tip equally
        float tipFraction = tip / numberOfPeople;

        for(Person p: personSet) {
            p.addToMoneyOwed(tipFraction);
        }

        return personSet;
    }

}
