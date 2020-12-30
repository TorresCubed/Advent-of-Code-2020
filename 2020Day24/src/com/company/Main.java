package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> tileInstructions = getInput();
        Map<String,Integer> arangement = makeTileSet();

        arangement.put("0,0",1);
        for (String instructions:tileInstructions){
            String coord = getCoords(instructions);
            if (arangement.containsKey(coord)){
                arangement.replace(coord,arangement.get(coord)*-1);
            } else {
                arangement.put(coord,-1);
            }
        }
        int count = 0;

        for (String each: arangement.keySet()){
            if (arangement.get(each)==-1){
                count++;
            }
        }
        System.out.println(count);

        int days = 0;
        while (days<100) {
            ArrayList<String> change = new ArrayList<>();
            for (int v = -400; v < 400; v += 2) {
                for (int h = -200; h < 200; h++) {
                    int blackTiles = 0;
                    for (int vChange = -2; vChange <= 2; vChange += 2) {
                        if (vChange == 0){
                            for (int hChange = -2; hChange <= 2; hChange+=2) {
                                if (hChange != 0) {
                                    int vert = v + vChange;
                                    int horz = h + hChange;
                                    String coord = vert + "," + horz;
                                    if (arangement.get(coord) == -1) blackTiles++;
                                }
                            }
                        } else {
                            for (int hChange = -1; hChange <= 1; hChange++) {
                                if (hChange != 0) {
                                    int vert = v + vChange;
                                    int horz = h + hChange;
                                    String coord = vert + "," + horz;
                                    if (arangement.get(coord) == -1) blackTiles++;
                                }
                            }
                        }
                    }
                    String tileCoord = v + "," + h;
                    if (arangement.get(tileCoord) == 1) {
                        if (blackTiles == 2) {
                            change.add(tileCoord);
                        }
                    } else {
                        if (blackTiles == 0 || blackTiles > 2) {
                            change.add(tileCoord);
                        }
                    }
                }
            }

            for (String each : change) {
                arangement.replace(each, arangement.get(each) * -1);
            }
            days++;

            int blackTiles = 0;
            for (String key:arangement.keySet()){
                if (arangement.get(key) ==-1){
                    blackTiles++;
                }
            }

            System.out.println(days);
            System.out.println(blackTiles);
        }



    }

    public static Map<String,Integer> makeTileSet(){
        Map<String,Integer> tiles = new HashMap<>();
        for (int v = -500; v < 500; v +=2){
            for (int h = -250; h < 250; h++){
                String coord = v+","+h;
                tiles.put(coord,1);
            }
        }
        return tiles;
    }

    public static String getCoords(String instructions){
        int vertical = 0;
        int horizontal = 0;
        while(instructions.length()>0){
            switch (instructions.charAt(0)){
                case 'e':
                    horizontal +=2;
                    instructions = instructions.substring(1);
                    break;
                case 'w':
                    horizontal -=2;
                    instructions = instructions.substring(1);
                    break;
                case 'n':
                    vertical +=2;
                    if (instructions.charAt(1) == 'e'){
                        horizontal +=1;
                    } else {
                        horizontal -=1;
                    }
                    instructions = instructions.substring(2);
                    break;
                case 's':
                    vertical -=2;
                    if (instructions.charAt(1) == 'e'){
                        horizontal +=1;
                    } else {
                        horizontal -=1;
                    }
                    instructions = instructions.substring(2);
                    break;
            }
        }
        return vertical + "," + horizontal;
    }


    public static ArrayList<String>  getInput(){
        ArrayList<String> tiles = new ArrayList<>();
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay24.txt"));
            String[] lines = input.replaceAll("\r","").split("\n");
            tiles.addAll(Arrays.asList(lines));

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return tiles;
    }
}
