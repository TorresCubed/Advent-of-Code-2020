package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongBinaryOperator;

public class Main {

    public static void main(String[] args) {
        part1();
        part2();


    }

    private static void part2() {

        Map<Long,String> memory = new HashMap<>();
        Map<Integer,ArrayList<String>> bitMasker = getMasks();

        for (int i = 1; i<=bitMasker.size(); i++){
            for (int string = 1; string < bitMasker.get(i).size(); string++) {
                String temp = bitMasker.get(i).get(0);
                String[] input = bitMasker.get(i).get(string).split(" = ");
                String binaryMemory = String.format("%36s",Long.toBinaryString(Long.parseLong(input[0].substring(1,input[0].length()-1)))).replaceAll(" ","0");
                char[] binarySplit = binaryMemory.toCharArray();
                ArrayList<Integer> floatingMemoryBits = new ArrayList<>();
                for (int bin = 0; bin<binaryMemory.length(); bin++){
                    if (temp.charAt(bin) =='1'){
                        binarySplit[bin] = '1';
                    } else {
                        if (temp.charAt(bin) == 'X'){
                            floatingMemoryBits.add(bin);
                            binarySplit[bin] = '0';
                        }
                    }
                }
                Collections.sort(floatingMemoryBits,Collections.reverseOrder());

                for (int permutation = 0; permutation < Math.pow(2,floatingMemoryBits.size());permutation++){
                    String binaryPermutations = Integer.toBinaryString(permutation);
                    for (int c = 0; c < binaryPermutations.length(); c++) {
                        binarySplit[floatingMemoryBits.get(binaryPermutations.length()-1-c)] = binaryPermutations.charAt(c);
                    }
                    StringBuilder maskedMemory = new StringBuilder();
                    for (int bin = 0; bin<binaryMemory.length(); bin++){
                        maskedMemory.append(binarySplit[bin]);
                    }
                    long number = Long.parseLong(input[1]);
                    if (memory.containsKey(maskedMemory.toString())) {
                        memory.replace(Long.parseLong(maskedMemory.toString(),2),Long.toBinaryString(number));
                    } else {
                        memory.put(Long.parseLong(maskedMemory.toString(),2),Long.toBinaryString(number));
                    }
                }
            }
        }

        long sum = 0;
        for (Long each:memory.keySet()){
//            System.out.println(Long.parseLong(memory.get(each),2) + " at " + each);
            sum+=Long.parseLong(memory.get(each),2);
        }
        System.out.println(sum);

    }

    public static void part1() {
        Map<Integer,String> memory = new HashMap<>();
        Map<Integer,ArrayList<String>> bitMasker = getMasks();

        for (int i = 1; i<=bitMasker.size(); i++){
            for (int string = 1; string < bitMasker.get(i).size(); string++) {
                String temp = bitMasker.get(i).get(0);
                String[] input = bitMasker.get(i).get(string).split(" = ");
                String binary = String.format("%36s",Long.toBinaryString(Long.parseLong(input[1]))).replaceAll(" ","0");
                char[] binarySplit = binary.toCharArray();
                for (int bin = 0; bin<binary.length(); bin++){
                    if (temp.charAt(bin) !='X'){
                        binarySplit[bin] = temp.charAt(bin);
                    }
                }
                StringBuilder finalized = new StringBuilder();
                for (int bin = 0; bin<binary.length(); bin++){
                    finalized.append(binarySplit[bin]);
                }
                int spaceInMemory = Integer.parseInt(input[0].substring(1,input[0].length()-1));
                if (memory.containsKey(spaceInMemory)) {
                    memory.replace(spaceInMemory,finalized.toString());
                } else {
                    memory.put(spaceInMemory, finalized.toString());
                }
            }
        }

        long sum = 0;
        for (Integer each:memory.keySet()){
//            System.out.println(Long.parseLong(memory.get(each),2));
            sum+=Long.parseLong(memory.get(each),2);
        }
        System.out.println(sum);

    }

    public static Map<Integer, ArrayList<String>> getMasks() {
         Map<Integer, ArrayList<String>> bitMasker = new HashMap<>();
         int order = 0;
         ArrayList<String> maskSet = new ArrayList<>();
         for (String each: getInput()){
             if (each.substring(0,4).equals("mask")){
                 bitMasker.put(order,maskSet);
                 order++;
                 maskSet =  new ArrayList<>();
                 maskSet.add(each.substring(7));
             } else {
                maskSet.add(each.substring(3));
             }
         }
        bitMasker.put(order,maskSet);
         bitMasker.remove(0);
         return bitMasker;
    }


    public static String[] getInput(){
        String[] bitMasker = new String[0];
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay14.txt"));
            String newInput = input.replaceAll("\r","");
            bitMasker = newInput.split("[,\n]");


        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return bitMasker;
    }
}
