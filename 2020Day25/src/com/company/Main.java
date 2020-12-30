package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] cardLoop = getLoop(8458505,7);
        int[] doorLoop = getLoop(16050997,7);
        System.out.println(cardLoop[0]+" " + cardLoop[1]);
        System.out.println(doorLoop[0]+" " + doorLoop[1]);
        System.out.println();

        System.out.println(getEncryption(cardLoop[1],doorLoop[0]));
        System.out.println(getEncryption(doorLoop[1],cardLoop[0]));



    }

    public static long getEncryption(int subjectNumber, int loop){
        long value = 1;
        for (int i = 0; i <loop; i++){
            value = value*subjectNumber;
            value = value%20201227;
        }
        return value;
    }

    public static int[] getLoop(int publicKey, int subjectNumber){
        int value = 1;
        int loop = 0;
        while (value !=publicKey){
            value = value*subjectNumber;
            value = value%20201227;
            loop++;
        }
        int[] set = {loop,value};
        return set;
    }

}
