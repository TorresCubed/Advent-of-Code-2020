package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

        public static void main (String[]args){
            Map<Bag,String> initialization = initialize(splitRules(getInput()));
            initialization.forEach((k,v) -> makeMap(k,initialization));

            Map<String,Bag> bagRules = new HashMap<>();
            initialization.forEach(((k,v) -> bagRules.put(k.getName(),k)));

            System.out.println(totalBags("shiny gold",bagRules,1)-1);




        }

        public static int totalBags(String target, Map<String,Bag> bagrules, int count){
            Bag current = bagrules.get(target);
            int bags = 0;
            if (current.getCanHold().size() >0) {
                for (Bag bag : current.getCanHold().keySet()) {
                    bags += count * totalBags(bag.getName(), bagrules, current.getCanHold().get(bag));
                }
                return bags+count;
            }
            return count;
        }

        public static ArrayList<String[]> splitRules (String[]rules){
            ArrayList<String[]> rulesSplit = new ArrayList<>();
            for (String rule : rules) {
                String removed = rule.substring(0, rule.length() - 2);
                String removed2 = removed.replaceAll(",", " ,");
                String[] temp = removed2.split(" bags contain ");
                rulesSplit.add(temp);
            }
            return rulesSplit;
        }

        public static Map<Bag,String> initialize (ArrayList < String[]>rulesSplit){
            Map<Bag,String> bagRules = new HashMap<>();
            for (String[] rule : rulesSplit) {
                Bag newRule = new Bag(rule[0]);
                bagRules.put(newRule,rule[1]);
            }
            return bagRules;
        }

        public static void makeMap(Bag current, Map<Bag,String> rules){
            String holds = rules.get(current);
            String[] each = holds.split(" , ");
            try {
                for (String thisRule : each) {
                    int num = Integer.parseInt(String.valueOf(thisRule.charAt(0)));
                    String bagName;
                    if (num == 1) {
                        bagName = thisRule.substring(2, thisRule.length() - 4);
                    } else {
                        bagName = thisRule.substring(2, thisRule.length() - 5);
                    }
                    for (Bag bag:rules.keySet()){
                        if (bag.getName().equals(bagName)){
                            current.addBag(bag,num);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }


        public static String[] getInput(){
            String[] instructions = new String[0];
            try {
                String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay7.txt"));
                String newInput = input + "r";
                instructions = newInput.split("[\n\n]");


            } catch (Exception e) {
                System.out.println("IOException: " + e.getMessage());
            }
            return instructions;
        }

}