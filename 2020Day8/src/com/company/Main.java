package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String[] instructions = getInput();
        String[] loop = instructions.clone();
        for (int i = 0; i <loop.length;i++) {
            boolean attempt = true;
            if (instructions[i].substring(0, 3).equals("nop")) {
                String set = instructions[i].replace("nop", "jmp");
                instructions[i] = set;
            } else if (instructions[i].substring(0, 3).equals("jmp")) {
                String set = instructions[i].replace("jmp", "nop");
                instructions[i] = set;
            } else {
                attempt = false;
            }

            ArrayList<Integer> visit = new ArrayList<>();
            int targetInstruction =0;
            int accumulator = 0;
            try {
                while (attempt) {
                    if (visit.contains(targetInstruction)) {
//                        System.out.println(instructions[i]);
                        break;
                    }
                    visit.add(targetInstruction);
                    int[] result = executeInstruction(instructions[targetInstruction]);
                    if (result[0] > 0) {
                        accumulator += result[1];
                        targetInstruction++;
                    } else {
                        targetInstruction += result[1];
                    }
                }
//                System.out.println("ERROR");
            } catch (Exception e){
                System.out.println(accumulator);
            }

            if (instructions[i].substring(0, 3).equals("nop")) {
                String set = instructions[i].replace("nop", "jmp");
                instructions[i] = set;
            } else if (instructions[i].substring(0, 3).equals("jmp")) {
                String set = instructions[i].replace("jmp", "nop");
                instructions[i] = set;
            }
        }
    }

    public static int[] executeInstruction(String instruction){
        String[] sep = instruction.split(" ");
        int[] move;
        int num = 0;
        if (sep[1].charAt(0) == '-'){
            num -= Integer.parseInt(sep[1].substring(1));
        } else {
            num = Integer.parseInt(sep[1].substring(1));
        }
        if (sep[0].equals("nop")){
             move = new int[]{1, 0};
        } else if (sep[0].equals("acc")){
            move = new int[]{1,num};
        } else {
            move = new int[]{-1, num};
        }
        return move;
    }

    public static String[] getInput(){
        String[] instructions = new String[0];
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay8.txt"));
            String newInput = input.replaceAll("\r","");
            instructions = newInput.split("[\n]");


        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return instructions;
    }
}
