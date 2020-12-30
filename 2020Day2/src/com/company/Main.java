package com.company;

import javax.swing.plaf.IconUIResource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> instructions = getInput();
        int count = 0;
        for (String password: instructions) {
            String[] split = password.split(" ");
            String[] loc = split[0].split("-");
            char one = 0;
            char two = 0;
            int target = Integer.parseInt(loc[0])-1;
            if (target < split[2].length()) {
                 one = split[2].charAt(target);
            }
            target = Integer.parseInt(loc[1])-1;
            if (target < split[2].length()) {
                two = split[2].charAt(target);
            }
            char need = split[1].charAt(0);
            if (one == need && two != need) {
                count++;
            } else if (one != need && two == need){
                count++;
            }

        }

        System.out.println(count);
    }

    public static ArrayList<String> getInput(){
        ArrayList<String> instructions = new ArrayList<>();
        try {
            File day2 = new File("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay2.txt");
            Scanner scanner = new Scanner(day2);
            while (scanner.hasNextLine()){
                instructions.add((scanner.nextLine()));
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return instructions;
    }
}
