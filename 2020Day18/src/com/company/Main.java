package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<ArrayList<String >> functions = getInput();
        long sum = 0;
        for (ArrayList<String> function: functions){
            function = reduce(function);
            long val =  doMath(function);
            sum += val;
            System.out.println(val);
        }
        System.out.println(sum);




    }

    public static long doMath(List<String> function) {
        ArrayList<Long> values = new ArrayList<>();
        long first = Long.parseLong(function.get(0));

        for (int i = 1; i <function.size()-1; i++){
            if (function.get(i).equals("+")){
                first += Long.parseLong(function.get(i+1));
                i++;
            } else {
                values.add(first);
                i++;
                first = Long.parseLong(function.get(i));
            }
        }
        values.add(first);

        long value = 1;

        for (Long each: values){
            value *= each;
        }

        return value;
    }

    public static ArrayList<String> reduce(ArrayList<String> function) {
        long value = 0;
        int start = 0;
        int end = 0;
        if (function.contains("(")){
            for (int i = 0; i <function.size(); i++){
                if (function.get(i).equals("(")){
                    start =i+1;
                } else if (function.get(i).equals(")")){
                    end = i;
                    break;
                }
            }
            List<String> tempList = function.subList(start,end);
            System.out.println(tempList.toString());
            value = doMath(tempList);
            for (int i = start-1; i< end; i++){
                function.remove(start-1);
            }
            function.set(start-1,String.valueOf(value));
        }
        if (function.contains("(")) {
            function = reduce(function);
        }
        return function;
    }




    public static ArrayList<ArrayList<String>>  getInput(){
        ArrayList<ArrayList<String>> disected = new ArrayList<>();
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay18.txt"));
            String rRemoved = input.replaceAll("\r","");
            String cleared = rRemoved.replaceAll(" ","");
            String[] functions = cleared.split("[\n]");

            for (String each: functions){
                ArrayList<String> function = new ArrayList<>();
                for (int i = 1; i <=each.length();i++){
                    function.add(each.substring(i-1,i));
                }
                disected.add(function);
            }

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return disected;
    }
}
