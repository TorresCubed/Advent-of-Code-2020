package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Map<String,ArrayList<Set<String>>> foodList = getInput();
        Map<String,Set<String>> reduced = new HashMap<>();

        for (String allergen:foodList.keySet()){
            Set<String> retained = new HashSet<>(foodList.get(allergen).get(0));
            for (Set<String> list:foodList.get(allergen)){
                retained.retainAll(list);
            }
            reduced.put(allergen,retained);
        }
        int mult = 0;
        while (mult < reduced.size()){
            int count = 0;
            for (String findSingle:reduced.keySet()){
                if (reduced.get(findSingle).size()==1){
                    count++;
                    for (String findMult:reduced.keySet()){
                        if (reduced.get(findMult).size() >1){
                            reduced.get(findMult).removeAll(reduced.get(findSingle));
                        }
                    }
                }
            }
            mult = count;
        }

        ArrayList<ArrayList<String>> food = getFoodList();
        int count = 0;
        for (ArrayList<String> ingredients: food){
            for (String allergen:reduced.keySet()){
                ingredients.removeAll(reduced.get(allergen));
            }
            count += ingredients.size();
        }


        Map<String,String> dunno= new HashMap<>();
        for (String key:reduced.keySet()){
            dunno.put(key, (String) reduced.get(key).toArray()[0]);
        }
        ArrayList<String> dangerousFood = new ArrayList<>(reduced.keySet());

        Collections.sort(dangerousFood);
        for (String each:dangerousFood){
            System.out.print(dunno.get(each)+",");
        }
        System.out.println();


    }

    public static ArrayList<ArrayList<String>>  getFoodList(){
        ArrayList<ArrayList<String>> foodList = new ArrayList<>();
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay21.txt"));
            String[] lines = input.replaceAll("\r","").split("\n");
            for (String line: lines){
                int breaker = line.indexOf("(");
                ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(line.substring(0,breaker-1).split(" ")));
                foodList.add(ingredients);
            }

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return foodList;
    }


    public static Map<String, ArrayList<Set<String>>>  getInput(){
        Map<String, ArrayList<Set<String>>> foodList = new HashMap<>();
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay21.txt"));
            String[] lines = input.replaceAll("\r","").split("\n");
            for (String line: lines){
                int breaker = line.indexOf("(");
                Set<String> ingredients = new HashSet<>(Arrays.asList(line.substring(0,breaker-1).split(" ")));
                String[] allergens = line.substring(breaker).replaceAll("[()]","").replaceAll("contains ","").split(", ");
                for (String allergen:allergens){
                    Set<String> copy = new HashSet<>(ingredients);
                    if (foodList.containsKey(allergen)){
                        foodList.get(allergen).add(copy);
                    } else {
                        ArrayList<Set<String>> temp = new ArrayList<>();
                        temp.add(copy);
                        foodList.put(allergen,temp);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return foodList;
    }
}
