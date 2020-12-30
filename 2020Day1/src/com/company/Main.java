package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> cashMoneys = getInput();
        boolean found = false;
        for (Integer lownum: cashMoneys) {
            if (lownum <=1010 && !found){
                for (Integer num: cashMoneys){
                    if (lownum+num <2020 && !found){
                        for (Integer lastnum: cashMoneys){
                            if (lownum+lastnum+num == 2020) {
                                System.out.println(num*lownum*lastnum);
                                found = true;
                            }
                        }
                    }
                }
            }
        }

    }
    public static ArrayList<Integer> getInput(){
        ArrayList<Integer> instructions = new ArrayList<>();
        try {
            File day19 = new File("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay1.txt");
            Scanner scanner = new Scanner(day19);
            while (scanner.hasNextLine()){
                instructions.add(Integer.parseInt(scanner.nextLine()));
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return instructions;
    }

}
