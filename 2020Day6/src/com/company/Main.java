package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Character> list = new ArrayList<>();
        int total =0;
        for (ArrayList<String> group: getInput()){
            ArrayList<Character> masterList =  new ArrayList<>();
            for (Character cha: group.get(0).toCharArray()){
                masterList.add(cha);
            }
            if (group.size()>1){
                for (int i =1; i<group.size();i++){
                    ArrayList<Character> temp = new ArrayList<>();
                    for (Character cha: group.get(i).toCharArray()){
                        temp.add(cha);
                    }
                    masterList.retainAll(temp);
                }
            }
            total+=masterList.size();
        }
        System.out.println(total);
    }


    public static  ArrayList<ArrayList<String>> getInput(){
        ArrayList<ArrayList<String>> passports = new ArrayList<>();
        try {
            File day6 = new File("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay6.txt");
            Scanner scanner = new Scanner(day6);
            ArrayList<String> list= new ArrayList<>();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.equals("")){
                    passports.add(list);
                    list = new ArrayList<>();
                } else  {
                    list.add(line);
                }
            }

            passports.add(list);

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return passports;
    }
}
