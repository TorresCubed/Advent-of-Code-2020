package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Long> numList = getInput();
        ArrayList<Long> pullFrom = new ArrayList<>();

        for (int i=0; i<25; i++){
            pullFrom.add(numList.get(i));
        }

        boolean found = false;
        int target = 25;
        do {
            found = false;
            for (Long num:pullFrom){
                if (pullFrom.contains(numList.get(target)-num) && numList.get(target) != num*2){
                    found = true;
                    break;
                }
            }
            if (found){
                pullFrom.add(numList.get(target));
                pullFrom.remove(0);
                target++;
            }

        } while (found);
        System.out.println(numList.get(target));


        ArrayList<Long> contiguous = new ArrayList<>();
        int start = 0;
        long count = 0;
        for (int i = start; i<numList.size(); i++){
            int end = i+1;
            count = numList.get(i);
            for (int j = end;j<numList.size();j++){
                count+=numList.get(j);
                if (count==numList.get(target)){
                    long lowest = Long.MAX_VALUE;
                    long largest = 0;
                    for (int x = i;x<j+1;x++){
                        if (numList.get(x)>largest){
                            largest = numList.get(x);
                        }
                        if (numList.get(x)<lowest){
                            lowest = numList.get(x);
                        }
                    }
                    System.out.println(largest+lowest);
                }
                if (count>numList.get(target)){
                    break;
                }
            }
        }


    }

    public static ArrayList<Long> getInput(){
        ArrayList<Long> numbers = new ArrayList<>();
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay9.txt"));
            String newInput = input.replaceAll("\r","");
            String[] newInts = newInput.split("[\n]");
            for (String num: newInts){
                numbers.add(Long.parseLong(num));
            }

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return numbers;
    }
}
