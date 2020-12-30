package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        ArrayList<Long> joltages = getInput();
        Collections.sort(joltages);

        System.out.println("Part 1");
        int count1 = 0;
        int count3 = 1;
        long previous = 0;
        for (Long each: joltages){
            if (each-previous ==1){
                count1++;
            } else if(each-previous ==3) {
                count3++;
        }
            previous = each;
        }

        System.out.println(count1 + " + " + count3 + " = " + count1*count3);


        joltages.add(joltages.get(joltages.size()-1)+3);
        System.out.println("Part 2");
        long permutations = 1;
        long temp = 0;
        int oneCounter = 0;
        for (Long each: joltages){
            if (each-temp ==1){
                oneCounter++;
            } else {
                permutations *= returnPermutations(oneCounter);
                oneCounter = 0;
            }
            temp = each;
        }
        System.out.println(permutations);

    }

    public static int returnPermutations(int oneCounter){
        if (oneCounter == 0 || oneCounter ==1){
            return 1;
        } else if (oneCounter == 2 || oneCounter == 3){
            return (oneCounter-1)*2;
        }

        return 4+3*(oneCounter-3);
    }

    public static ArrayList<Long> getInput(){
        ArrayList<Long> numbers = new ArrayList<>();
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay10.txt"));
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
