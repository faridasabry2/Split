package com.smith.split.Models;

/**
 * Created by demouser on 1/16/18.
 */

public class Item {

    private String label;
    private int price;

    public Item(String label, int price) {
        this.label = label;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getLabel() {
        return label;
    }
}
