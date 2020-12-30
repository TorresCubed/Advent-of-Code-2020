package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> cups = getInput();

        for (int i = 10;i<1000000;i++){
            cups.add(i);
        }

        int max = cups.size();
        int current = cups.get(0);
        int count = 0;
        while(count<10000){
            System.out.println(count + " " + cups.get((cups.indexOf(1)+1)%max) + " " + cups.get((cups.indexOf(1)+2)%max));
            boolean fudge = false;
            int fudgeNum = 0;
            if (cups.size()-cups.indexOf(current)-1 <3){
                fudgeNum = cups.size()-cups.indexOf(current)-1;
                fudge = true;
            }
            ArrayList<Integer> three = new ArrayList<>();
            int threeTemp = cups.indexOf(current)+1;
            for ( int i = 0; i<3; i++){
                int temp = threeTemp;
                if (threeTemp >= cups.size()){
                    temp = 0;
                }
                three.add(cups.get(temp));
                cups.remove(temp);
            }
            int targetCup = current;
            boolean target = false;
            int destinationCup = 0;
            do {
                targetCup--;
                if (targetCup <0){
                    targetCup = 9;
                }
                if (cups.contains(targetCup)){
                    destinationCup = cups.indexOf(targetCup);
                    target = true;
                }
            }while (!target);
            ArrayList<Integer> temp = new ArrayList<>();
            if (destinationCup ==0 && !fudge){
                cups.add(cups.get(0));
                cups.remove(0);
                destinationCup = cups.size()-1;
                temp.add(three.get(2));
                three.remove(2);
            }
            for (int i = 0; i <=destinationCup; i++){
                temp.add(cups.get(i));
            }
            temp.addAll(three);
            for (int i = destinationCup+1; i<cups.size(); i++){
                temp.add(cups.get(i));
            }
            cups = temp;
            if (fudge){
                for (int i = 0; i<fudgeNum;i++){
                    cups.add(cups.get(0));
                    cups.remove(0);
                }
            }
            current = cups.get((cups.indexOf(current)+1)%max);
            count++;
        }
        System.out.println();

        for (int i = cups.indexOf(1)+1; i < cups.indexOf(1)+3; i++){
            System.out.println(cups.get(i%max));
        }

//        System.out.println();
//        for (int i = 0; i < cups.size(); i++){
//            System.out.print(cups.get(i));
//        }


    }

    public static ArrayList<Integer>  getInput(){
        ArrayList<Integer> cups = new ArrayList<>();
        String nums = "389125467";
        for (int i = 0; i<nums.length();i++){
            cups.add(Integer.parseInt(String.valueOf(nums.charAt(i))));
        }
        return cups;
    }
}
