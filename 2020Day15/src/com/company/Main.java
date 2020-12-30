package com.company;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<Integer,ArrayList<Integer>> numberList = getInput();
        int turn = numberList.size();

        int lastNumber = 0;
        for (Integer each: numberList.keySet()){
            if (numberList.get(each).get(0)==turn){
                lastNumber = each;
            }
        }


        while (turn <30000000){
            turn++;
            if (numberList.get(lastNumber).size() == 1){
                lastNumber = 0;
                handlelast(lastNumber,turn,numberList);
            } else {
                lastNumber = numberList.get(lastNumber).get(numberList.get(lastNumber).size()-1)-numberList.get(lastNumber).get(numberList.get(lastNumber).size()-2);
                handlelast(lastNumber,turn,numberList);
            }
        }
        System.out.println(lastNumber);
    }

    public static void handlelast(int lastNumber, int turn, Map<Integer, ArrayList<Integer>> numberList){
        if (numberList.containsKey(lastNumber)){
            numberList.get(lastNumber).add(turn);
        } else {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(turn);
            numberList.put(lastNumber, temp);
        }
    }


    public static Map<Integer,ArrayList<Integer>> getInput(){
        Map<Integer,ArrayList<Integer>> initialNumbers = new HashMap<>();
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay15.txt"));
            String[] split = input.split(",");
            for (int i = 0; i <split.length; i++){
                int temp = Integer.parseInt(split[i]);
                ArrayList<Integer> tempArray = new ArrayList<>();
                tempArray.add(i+1);
                initialNumbers.put(temp,tempArray);
            }


        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return initialNumbers;
    }
}
