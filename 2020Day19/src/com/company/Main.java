package com.company;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] input = getInput();

        assert input != null;
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(input[1].split("]]")));
        Map<Integer,String> rules = getRules(input[0]);

        ArrayList<String> options = new ArrayList<>();
        options.add(rules.get(0));
        int temp;
        boolean first = true;
        while (true) {
            temp = options.size();
            options = addRules(rules, options);
            System.out.println(options.size());
            if (temp == options.size()){
                if (!first) {
                    break;
                } else {
                    first = false;
                }
            } else {
                first = true;
            }
        }

        for (int i = 0; i <options.size(); i++){
            options.set(i,options.get(i).replaceAll("\\s",""));
        }

        for (int i = 0; i <options.size(); i++){
            System.out.println(options.get(i));
        }
        int count = 0;
        for (String each:strings){
            if (options.contains(each)){
                count++;
            }
        }
        System.out.println(count);




    }

    public static ArrayList<String> addRules(Map<Integer,String> rules, ArrayList<String> options){
        ArrayList<String> tempStrings = new ArrayList<>();
        for (int o = 0; o<options.size(); o++){
            String[] splitted = options.get(o).split(" ");
            for (int i = 0; i <splitted.length; i++){
                if (!(splitted[i].contains("a") || splitted[i].contains("b"))){
                    String implement = rules.get(Integer.parseInt(splitted[i]));
                    if (implement.contains("a") || implement.contains("b")){
                        implement = implement.substring(1,2);
                    }
                    if (implement.contains("|")){
                        String[] variants =splitVariants(implement);
                        splitted[i] =variants[1];
                        tempStrings.add(produceString(splitted));
                        splitted[i] = variants[0];
                    } else {
                        splitted[i] = implement;
                    }
                }
                options.set(o,produceString(splitted));
            }
        }
        options.addAll(tempStrings);
        return options;
    }

    public static String produceString(String[] splitted){
        String combined = "";
        for (String each:splitted){
            combined = combined + each.trim() + " ";
        }
        return combined.trim();
    }

    public static String[] splitVariants(String variants){
        String[] split = variants.split("[|]");
        for (String each: split){
            each = each.trim();
        }
        return split;
    }



    public static Map<Integer,String> getRules(String input){
        Map<Integer,String> rules = new HashMap<>();

        for (String rule: input.split("]]")){
            rules.put(Integer.parseInt(rule.substring(0,rule.indexOf(":"))),rule.substring(rule.indexOf(":")+2));
        }
        return rules;
    }


    public static String[]  getInput(){
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay19.txt"));
            String rRemoved = input.replaceAll("\r","");
            String resetting = rRemoved.replaceAll("\n","]]");

            return resetting.split("]]]]");

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return null;
    }
}
