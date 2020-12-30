package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String[] busSchedule = getInput();
        ArrayList<long[]> busDepartTime = new ArrayList<>();
        int start = Integer.parseInt(busSchedule[0]);
        for (int i = 1; i <busSchedule.length; i++){
            if (!busSchedule[i].equals("x")){
                long[] temp = {Long.parseLong(busSchedule[i]),i-1};
                busDepartTime.add(temp);
            }
        }

        long timeStamp =1;
        long max = 0;
        long maxi = 0;
        long least = 800;
        long jumps = 1;
        long jumpi =0;
        long temp = 1;
        long tempi =0;
        for (long[] bus: busDepartTime){
//            System.out.println(bus[0] + " " + bus[1]);
            timeStamp*=bus[0];
            if (least>bus[0]) {
                least = bus[0];
            }
            if (max <bus[0]){
                max = bus[0];
                maxi = bus[1];
            }
            temp = bus[0];
            tempi = bus[1];
            for (long[] others: busDepartTime){
                if (others!=bus){
                    if (others[0]+others[1] == bus[1]){
                        System.out.println(others[0] + " " + bus[0]);
                        temp *= others[0];
                    }
                }
            }
            if (temp > jumps){
                jumpi = tempi;
                jumps = temp;
            }
        }
        System.out.println(jumps);
        if (jumps ==0){
            jumps = max;
            jumpi =maxi;
        }
        System.out.println(jumps);
        timeStamp /= least;
        timeStamp -= timeStamp%jumps;
        timeStamp -= jumpi;

        System.out.println(timeStamp);
        System.out.println(100000000000000.0);
        boolean found;
        do {
            timeStamp+=jumps;
            found = true;
            for (long[] bus: busDepartTime){
                if ((timeStamp+bus[1])%bus[0]!=0){
                    found = false;
                    break;
                }
            }
        } while (!found);

        System.out.println(timeStamp);

    }


    public static String[] getInput(){
        String[] floorPlan = new String[0];
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay13.txt"));
             String newInput = input.replaceAll("\r","");
            floorPlan = newInput.split("[,\n]");


        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return floorPlan;
    }
}
