package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<String,Character> myMap = getInput();
        ArrayList<int[]> checksets =  new ArrayList<>();
        int[] first = {1,1}; checksets.add(first);
        int[] second = {3,1}; checksets.add(second);
        int[] third = {5,1};checksets.add(third);
        int[] fourth = {7,1};checksets.add(fourth);
        int[] fifth = {1,2};checksets.add(fifth);

        long total = 1;
        for (int[] check: checksets) {
            boolean land = false;
            int x =0;
            int y = 0;
            int count = 0;
            while (!land) {
                String location = Integer.toString(y).concat(Integer.toString(x % 31));
                if (myMap.keySet().contains(location)) {
                    if (myMap.get(location) == '#') {
                        count++;
                    } else {
                        myMap.replace(location, '.', 'O');
                    }
                } else {
                    land = true;
                }

                y += 10*check[1];
                x += check[0];
            }
            System.out.println(count);
            total*=count;
        }
        System.out.println(total);
//        System.out.println(count);
//        int j =0;
//        while (true){
//            for (int i = 0; i <31; i++){
//                String location = Integer.toString(j).concat(Integer.toString(i));
//                System.out.print(myMap.get(location));
//            }
//            System.out.println();
//            j+=10;
//        }
    }


    public static Map<String,Character> getInput(){
        Map<String,Character> map = new HashMap<>();
        try {
            File day3 = new File("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay3.txt");
            Scanner scanner = new Scanner(day3);
            int y = 0;
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                for (int i = 0; i <line.length(); i++){
                    map.put(Integer.toString(y).concat(Integer.toString(i)),line.charAt(i));
                }
                y+=10;
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return map;
    }
}
