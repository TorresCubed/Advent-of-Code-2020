package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<Integer,Map<Integer,Map<Integer,Map<Integer,Boolean>>>> wAxis = getInput(inactiveState());
        Map<Integer,Map<Integer,Map<Integer,Map<Integer,Boolean>>>> backup = getInput(inactiveState());

        int count = 0;
        while (true) {

            for (int w = -wAxis.size() / 2; w < wAxis.size() / 2 - 1; w++) {
                for (int z = -wAxis.get(w).size() / 2; z < wAxis.get(w).size() / 2 - 1; z++) {
                    for (int y = -wAxis.get(w).get(z).size() / 2; y < wAxis.get(w).get(z).size() / 2 - 1; y++) {
                        for (int x = -wAxis.get(w).get(z).get(y).size()/2;x < wAxis.get(w).get(z).get(y).size()/2-1; x++) {
                            boolean state = wAxis.get(w).get(z).get(y).get(x);
                            int activeCells = checkNeighbors(x, y, z, w, wAxis);
                            if (state) {
//                                System.out.print('#');
                                if (!(activeCells == 2 || activeCells == 3)) {
                                    backup.get(w).get(z).get(y).replace(x,false);
                                } else {
                                    backup.get(w).get(z).get(y).replace(x,true);
                                }
                            } else {
//                                System.out.print('.');
                                if ((activeCells == 3)) {
                                    backup.get(w).get(z).get(y).replace(x,true);
                                } else {
                                    backup.get(w).get(z).get(y).replace(x,false);
                                }
                            }
                        }
//                        System.out.println();
                    }
//                    System.out.print("z = " + z + "  w = " + w);
                }
//                System.out.println("w = " + w);
            }
            if (count ==6) {
                System.out.println(count(wAxis));
                break;
            }
            count++;
            for (int w = -backup.size() / 2; w < backup.size() / 2 - 1; w++) {
                for (int z = -backup.get(w).size() / 2; z < backup.get(w).size() / 2 - 1; z++) {
                    for (int y = -backup.get(w).get(z).size() / 2; y < backup.get(w).get(z).size() / 2 - 1; y++) {
                        for (int x = -backup.get(w).get(z).get(y).size()/2;x < backup.get(w).get(z).get(y).size()/2-1; x++) {
                            boolean state = backup.get(w).get(z).get(y).get(x);
                            int activeCells = checkNeighbors(x, y, z, w, backup);
                            if (state) {
                                if (!(activeCells == 2 || activeCells == 3)) {
                                    wAxis.get(w).get(z).get(y).replace(x,false);
                                } else {
                                    wAxis.get(w).get(z).get(y).replace(x,true);
                                }
                            } else {
                                if ((activeCells == 3)) {
                                    wAxis.get(w).get(z).get(y).replace(x,true);
                                } else {
                                    wAxis.get(w).get(z).get(y).replace(x,false);
                                }
                            }
                        }
                    }
                }
            }
            if (count ==6){
                System.out.println(count(backup));
                break;
            }
            count++;
        }

    }

    public static int count(Map<Integer,Map<Integer,Map<Integer,Map<Integer,Boolean>>>> wAxis){
        int count = 0;
        for (int w = -wAxis.size() / 2; w < wAxis.size() / 2 - 1; w++) {
            for (int z = -wAxis.get(w).size() / 2; z < wAxis.get(w).size() / 2 - 1; z++) {
                for (int y = -wAxis.get(w).get(z).size() / 2; y < wAxis.get(w).get(z).size() / 2 - 1; y++) {
                    for (int x = -wAxis.get(w).get(z).get(y).size()/2;x < wAxis.get(w).get(z).get(y).size()/2-1; x++) {
                        boolean state = wAxis.get(w).get(z).get(y).get(x);
                        if (state){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }




    public static Map<Integer,Map<Integer,Map<Integer,Map<Integer,Boolean>>>> inactiveState (){
        Map<Integer,Map<Integer,Map<Integer,Map<Integer,Boolean>>>> wAxis = new HashMap<>();
        for (int w = -13; w <=13; w++) {
            Map<Integer,Map<Integer,Map<Integer,Boolean>>> tempZ = new HashMap<>();
            for (int z = -13; z <= 13; z++) {
                Map<Integer, Map<Integer, Boolean>> tempY = new HashMap<>();
                for (int y = -13; y <= 13; y++) {
                    Map<Integer, Boolean> tempX = new HashMap<>();
                    for (int x = -13; x <= 13; x++) {
                        tempX.put(x, false);
                    }
                    tempY.put(y, tempX);
                }
                tempZ.put(z, tempY);
            }
            wAxis.put(w,tempZ);
        }

        return wAxis;
    }


    public static int checkNeighbors(int x, int y, int z, int w, Map<Integer,Map<Integer,Map<Integer,Map<Integer,Boolean>>>> wAxis) {
        int count = 0;
        for (int xChange = -1; xChange<=1;xChange++){
            for (int yChange = -1; yChange<=1;yChange++){
                for (int zChange = -1; zChange<=1;zChange++){
                    for (int wChange = -1; wChange<=1; wChange++) {
                        int xTarget = x + xChange;
                        int yTarget = y + yChange;
                        int zTarget = z + zChange;
                        int wTarget = w + wChange;
                        if (!(zChange == 0 && yChange == 0 && xChange == 0 && wChange ==0) && boundsCheck(xTarget, yTarget, zTarget, wTarget, wAxis)) {
                            if (wAxis.get(wTarget).get(zTarget).get(yTarget).get(xTarget)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }


    public static boolean boundsCheck(int x, int y, int z, int w, Map<Integer,Map<Integer,Map<Integer,Map<Integer,Boolean>>>> wAxis) {
        if (wAxis.containsKey(w)){
            if (wAxis.get(w).containsKey(z)){
                if (wAxis.get(w).get(z).containsKey(y)){
                    if (wAxis.get(w).get(z).get(y).containsKey(x))
                    return true;
                }
            }
        }
        return false;
    }




    public static Map<Integer,Map<Integer,Map<Integer,Map<Integer,Boolean>>>> getInput(Map<Integer,Map<Integer,Map<Integer,Map<Integer,Boolean>>>> wAxis){
        int w =0;
        int z=0;
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay17.txt"));
            Scanner scanner = new Scanner(input);
            int count = -4;
            while (scanner.hasNextLine()){
                String y1 = scanner.nextLine();
                int charLoc =0;
                for (int i = -y1.length()/2; i <=y1.length()/2-1; i ++){
                    if (y1.charAt(charLoc)=='.'){
                        wAxis.get(w).get(z).get(count).replace(i,false);
                    } else {
                        wAxis.get(w).get(z).get(count).replace(i,true);
                    }
                    charLoc++;
                }
                count++;
            }



        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return wAxis;
    }
}
