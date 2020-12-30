package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String[] directions = getInput();
        Ferry ferry = new Ferry();

        for (String direction: directions){
            char instruction = direction.charAt(0);
            int intensity = Integer.parseInt(direction.substring(1));
            switch (instruction){
                case 'L':
                    ferry.turnLeft(intensity);
                    break;
                case 'R':
                    ferry.turnRight(intensity);
                    break;
                case 'F':
                    ferry.moveForward(intensity);
                    break;
                case 'E':
                    ferry.moveEast(intensity);
                    break;
                case 'N':
                    ferry.moveNorth(intensity);
                    break;
                case 'S':
                    ferry.moveSouth(intensity);
                    break;
                case 'W':
                    ferry.moveWest(intensity);
                    break;
            }
        }
        System.out.println(ferry.getX());
        System.out.println(ferry.getY());

        System.out.println(Math.abs(ferry.getX()) + Math.abs(ferry.getY()));




    }


    public static String[] getInput(){
        String[] floorPlan = new String[0];
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay12.txt"));
            String newInput = input.replaceAll("\r","");
            floorPlan = newInput.split("[\n]");


        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return floorPlan;
    }
}
