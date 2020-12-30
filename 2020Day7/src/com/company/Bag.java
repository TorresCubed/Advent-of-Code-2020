package com.company;

import java.util.HashMap;
import java.util.Map;

public class Bag {

    private String name;
    private Map<String, Integer> canHold = new HashMap<>();

    public Bag(String name, Map<String, Integer> canHold) {
        this.name = name;
        this.canHold = canHold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getCanHold() {
        return canHold;
    }

    public void setCanHold(Map<String, Integer> canHold) {
        this.canHold = canHold;
    }
}
