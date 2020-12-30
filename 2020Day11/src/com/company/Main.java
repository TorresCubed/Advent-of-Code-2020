package com.company;

import javafx.scene.layout.BorderPane;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] input = getInput();
        ArrayList<ArrayList<Boolean>> floorPlan = new ArrayList<>();

        for (int y = 0; y <input.length;y++){
            ArrayList<Boolean> temp = new ArrayList<>();
            for (int x = 0; x<input[0].length(); x++){
                if (input[y].charAt(x) == '.'){
                    temp.add(null);
                } else {
                    temp.add(false);
                }
            }
            floorPlan.add(temp);
        }

        ArrayList<ArrayList<Boolean>> backup = new ArrayList<>();

        for (ArrayList<Boolean> each: floorPlan){
            backup.add((ArrayList<Boolean>) each.clone());
        }
        int change;
        do {

//            for (ArrayList<Boolean> y: floorPlan){
//                for (Boolean x: y) {
//                    if (x == null){
//                        System.out.print(".");
//                    } else if (x) {
//                        System.out.print("#");
//                    } else {
//                        System.out.print("L");
//                    }
//                }
//                System.out.println();
//            }

            change = 0;
            for (int y = 0; y < floorPlan.size(); y++) {
                for (int x = 0; x < floorPlan.get(0).size(); x++) {
                    int count = 0;
                    if (floorPlan.get(y).get(x) != null) {
                        for (int yChange = -1; yChange <= 1; yChange++) {
                            for (int xChange = -1; xChange <= 1; xChange++) {
                                if ((yChange != 0 || xChange != 0)) {
                                    int tempx = x + xChange;
                                    int tempy = y + yChange;
                                    while (true) {
                                        if ((tempy < 0) || (tempy >= floorPlan.size()) || (tempx < 0) || (tempx >= floorPlan.get(0).size())) {
                                            break;
                                        }
                                        if (floorPlan.get(tempy).get(tempx) == null) {
                                            tempx += xChange;
                                            tempy += yChange;
//                                            System.out.println(tempx + ", and " + tempy);
                                        } else {
                                            if (floorPlan.get(tempy).get(tempx)) {
//                                          System.out.println((y+yChange) + " " + (x+xChange) + " is taken" );
                                                count++;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    if (floorPlan.get(y).get(x) && count > 4) {
                        backup.get(y).set(x, false);
                        change++;
                    } else if (!floorPlan.get(y).get(x) && count == 0) {
                        backup.get(y).set(x, true);
                        change++;
                    }
                }
            }
        }


//            System.out.println();
            for (int i = 0; i <floorPlan.size(); i++){
                floorPlan.set(i, (ArrayList<Boolean>) backup.get(i).clone());
            }
        }while (change>0);

        int count = 0;
        for (ArrayList<Boolean> y: floorPlan){
            for (Boolean x: y) {
                if (x != null) {
                    if (x) {
                        count++;
                    }
                }
            }
        }
        System.out.println( count);

    }

    public static String[] getInput(){
        String[] floorPlan = new String[0];
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay11.txt"));
            String newInput = input.replaceAll("\r","");
            floorPlan = newInput.split("[\n]");


        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return floorPlan;
    }
}
