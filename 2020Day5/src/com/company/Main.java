package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> seatID = new ArrayList<>();

        int maxSeatID =0;
        for (String boardingPass: getInput()) {
            String rowDirections = boardingPass.substring(0, 7);
            int row = getSeatRow(rowDirections);
            String columnDirections = boardingPass.substring(7, 10);
            int column = getSeatColumn(columnDirections);
            int seatId = row*8+column;
            seatID.add(seatId);
            if (seatId > maxSeatID) maxSeatID = seatId;
        }
        seatID.sort(Integer::compareTo);
        int temp = seatID.get(0);
        for (Integer seat: seatID){
            if (seat -temp >1){
                System.out.println(seat-1);
            }
            temp = seat;
        }



    }


    public static int getSeatRow(String directions){
        int max = 127;
        int min = 0;

        for (int i = 0; i <directions.length(); i++ ){
            if (directions.charAt(i) == 'F'){
                max -= Math.ceil((double)(max-min)/2);
            } else {
                min += Math.ceil((double)(max-min)/2);
            }
        }
        return min;
    }

    public static int getSeatColumn(String directions){
        int max = 7;
        int min = 0;

        for (int i = 0; i <directions.length(); i++ ){
            if (directions.charAt(i) == 'L'){
                max -= Math.ceil((double)(max-min)/2);
            } else {
                min += Math.ceil((double)(max-min)/2);
            }
        }
        return min;
    }


    public static ArrayList<String> getInput(){
        ArrayList<String> seats = new ArrayList<>();
        try {
            File day5 = new File("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay5.txt");
            Scanner scanner = new Scanner(day5);

            while (scanner.hasNextLine()){
                seats.add(scanner.nextLine());
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return seats;
    }
}
