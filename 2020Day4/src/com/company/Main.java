package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Passport> dataSet = new ArrayList<>();

        for (String passportData: getInput()){
            Passport temp = new Passport(passportData);
            dataSet.add(temp);
        }


        ArrayList<String> dataFields = new ArrayList<>();
        dataFields.add("byr");
        dataFields.add("iyr");
        dataFields.add("eyr");
        dataFields.add("hgt");
        dataFields.add("hcl");
        dataFields.add("ecl");
        dataFields.add("pid");

        int validPassport = 0;
        for (Passport passport: dataSet){
            boolean valid = true;
            for (String datafield: dataFields){
                if (!passport.getPassport().containsKey(datafield) || !passport.testValidity(datafield)){
                    valid = false;
                    break;
                }
            }
            if (valid) validPassport++;
        }
        System.out.println(validPassport);
    }

    public static ArrayList<String> getInput(){
        ArrayList<String> passports = new ArrayList<>();
        try {
            File day4 = new File("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay4.txt");
            Scanner scanner = new Scanner(day4);


            String dataInput ="";
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.equals("")){
                    passports.add(dataInput);
                    dataInput = "";
                } else  {
                    dataInput = new String (dataInput + line + " ");
                }
            }

            passports.add(dataInput);

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return passports;
    }
}
