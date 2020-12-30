package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //part 1
        ArrayList<Bag> bagRules = initialize(splitRules(getInput()));
        ArrayList<Bag> master = new ArrayList<>();

        ArrayList<Bag> holding = new ArrayList<>();
        for (Bag bag: bagRules){
            if (bag.getName().equals("dull orange")){
                System.out.println("Do nothing");
            }
            if (bag.getCanHold().containsKey("shiny gold")){
                holding.add(bag);
            }
        }
        ArrayList<Bag> temp = new ArrayList<>();
        int count = 0;
        do {
            count = 0;
            for (Bag bag: holding){
                for (Bag bagRule:bagRules){
                    if (bagRule.getCanHold().containsKey(bag.getName())) {
                        temp.add(bagRule);
                        count++;
                    }
                }
            }
            for (Bag bag: holding){
                if (!master.contains(bag)){
                    master.add(bag);
                }
            }
            holding.clear();
            holding.addAll(temp);
            temp.clear();
        } while (count >0);

        //part 1 answer
        System.out.println(master.size());

        int counter =0;
        ArrayList<Bag> within = new ArrayList<>();
        for (Bag bag: bagRules){
            if (bag.getName().equals("shiny gold")) {
                within.add(bag);
                break;
            }
        }

        do {
            ArrayList<Bag> limbo = new ArrayList<>();
            for (Bag bag:within){
                for (Bag choice:bagRules){
                    if (bag.getCanHold().keySet().contains(choice.getName())){
                        limbo.add(choice);
                    }
                }
            }

        } while (counter !=0);


    }



    public static ArrayList<String[]> splitRules(String[] rules){
        ArrayList<String[]> rulesSplit = new ArrayList<>();
        for (String rule : rules) {
            String removed = rule.substring(0,rule.length()-2);
            String removed2 = removed.replaceAll(","," ,");
            String[] temp = removed2.split(" bags contain ");
            rulesSplit.add(temp);
        }
        return rulesSplit;
    }

    public static ArrayList<Bag> initialize(ArrayList<String[]> rulesSplit){
        ArrayList<Bag> bagRules = new ArrayList<>();
        for (String[] rule: rulesSplit){
            Map<String,Integer> temp = makeMap(rule[1]);
            Bag newRule = new Bag(rule[0],temp);
            bagRules.add(newRule);
        }
        return bagRules;
    }

    public static Map<String,Integer> makeMap(String holds){
        String[] each = holds.split(" , ");
        Map<String, Integer> temp = new HashMap<>();
        try {
            for (String thisRule : each) {
                int num = Integer.parseInt(String.valueOf(thisRule.charAt(0)));
                if (num == 1) {
                    temp.put(thisRule.substring(2, thisRule.length() - 4), num);
                } else {
                    temp.put(thisRule.substring(2, thisRule.length() - 5), num);
                }
            }
        } catch (Exception e){
            temp.put("No Bag",0);
        }
        return temp;
    }


    public static  String[] getInput(){
        String[] instructions = new String[0];
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay7.txt"));
            String newInput = input+"r";
            instructions = newInput.split("[\n\n]");


        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return instructions;
    }
}
