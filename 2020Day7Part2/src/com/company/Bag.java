package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bag {

    private String name;
    private Map<Bag, Integer> canHold = new HashMap<>();

    public Bag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Bag, Integer> getCanHold() {
        return canHold;
    }

    public void addBag(Bag within, Integer count){
        this.canHold.put(within,count);
    }

    public void setCanHold(Map<Bag, Integer> canHold) {
        this.canHold = canHold;
    }
}
