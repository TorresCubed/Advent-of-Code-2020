package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Passport {

    private Map<String,String> passport = new HashMap<>();

    public Passport(String passportData) {
        producePassport(passportData);
    }

    private void producePassport(String passportData) {
        String[] data = passportData.split(" ");
        for (String input: data) {
            String[] temp = input.split(":");
            passport.put(temp[0],temp[1]);
        }
    }

    public boolean testValidity(String key){
        if (!this.passport.containsKey(key)){
            return false;
        }
        String value = this.passport.get(key);
        switch (key){
            case "byr":
                return birthYear(value);
            case "iyr":
                return issueYear(value);
            case "eyr":
                return expirationYear(value);
            case "hgt":
                return height(value);
            case "hcl":
                return hairColor(value);
            case "ecl":
                return eyeColor(value);
            case "pid":
                return passportID(value);
            case "cid":
                return true;
        }
        return false;
    }

    private boolean birthYear(String value){
        try {
            int year = Integer.parseInt(value);
            if (year < 2003 && year > 1919) {
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }

    private boolean issueYear(String value){
        try {
            int year = Integer.parseInt(value);
            if (year <2021 && year > 2009){
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }

    private boolean expirationYear(String value){
        try {
            int year = Integer.parseInt(value);
            if (year <2031 && year > 2019){
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }

    private boolean height(String value){
        try {
            String unit = value.substring(value.length()-2);
            int height =  Integer.parseInt(value.substring(0,value.length()-2));
            if (unit.equals("cm")){
                if (height < 194 && height > 149){
                    return true;
                }
            } else if (unit.equals("in")){
                if (height <77 && height> 58){
                    return true;
                }
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }

    private boolean hairColor(String value){
        try {
            if (value.charAt(0) == '#' && value.length() == 7){
                for (int i = 1; i <7; i++){
                    char cha = value.charAt(i);
                    if (!(Character.isAlphabetic(cha) || Character.isDigit(cha))){
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }

    private boolean eyeColor(String value){
        switch (value) {
            case "amb":
            case "gry":
            case "blu":
            case "hzl":
            case "brn":
            case "grn":
            case "oth":
                return true;
            default:
                return false;
        }
    }


    private boolean passportID(String value){
        if (value.length()==9){
            for (int i = 0; i <9;i++){
                if (!Character.isDigit(value.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }



    public Map<String, String> getPassport() {
        return passport;
    }
}
