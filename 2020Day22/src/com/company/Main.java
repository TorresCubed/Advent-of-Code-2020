package com.company;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> decks = getInput();
        playGame(decks);

//        System.out.println(decks.toString());

        if (decks.get(0).size()==0){
            System.out.println(score(decks.get(1)));
        } else {
            System.out.println(score(decks.get(0)));
        }
    }


    public static int playGame(ArrayList<ArrayList<Integer>> decks){
        ArrayList<String> history = new ArrayList<>();
        while (decks.get(0).size() > 0 && decks.get(1).size()>0){
            int playerOneCard = decks.get(0).get(0);
            int playerTwoCard = decks.get(1).get(0);
            String recent = tabulate(decks.get(0)) + tabulate(decks.get(1));
            if (recent.equals("28 37 18 46 , 2 47 20 39 30 48 8 , ")){
                System.out.println(decks.toString());
            }
            if (!history.contains(recent)){
                history.add(recent);
            } else {
                return -1;
            }
            decks.get(0).remove(0);
            decks.get(1).remove(0);
            if (decks.get(0).size()>=playerOneCard && decks.get(1).size()>=playerTwoCard){
                ArrayList<ArrayList<Integer>> subDeck = new ArrayList<>();
                ArrayList<Integer> subOne = new ArrayList<>();
                for (int one = 0; one <playerOneCard; one++){
                    subOne.add(decks.get(0).get(one));
                }
                subDeck.add(subOne);
                ArrayList<Integer> subTwo = new ArrayList<>();
                for (int two = 0; two <playerTwoCard; two++){
                    subTwo.add(decks.get(1).get(two));
                }
                subDeck.add(subTwo);
                if(playGame(subDeck) >0){
                    decks.get(1).add(playerTwoCard);
                    decks.get(1).add(playerOneCard);
                } else {
                    decks.get(0).add(playerOneCard);
                    decks.get(0).add(playerTwoCard);
                }
            } else {
                if (playerOneCard > playerTwoCard) {
                    decks.get(0).add(playerOneCard);
                    decks.get(0).add(playerTwoCard);
                } else {
                    decks.get(1).add(playerTwoCard);
                    decks.get(1).add(playerOneCard);
                }
            }
        }

        if (decks.get(0).size()==0){
            return  1;
        } else {
            return  -1;
        }
    }

    public static String  tabulate(ArrayList<Integer> deck){
        String tabulation = "";
        for (int i = deck.size()-1; i >=0;i--){
            tabulation = tabulation + deck.get(i) + " ";
        }
        tabulation = tabulation + ", ";
        return tabulation;
    }

    public static long score(ArrayList<Integer> deck){
        long score = 0;
        for (int i = deck.size()-1; i >=0;i--){
            score+= deck.get(i)*(deck.size()-i);
        }
        return score;
    }



    public static ArrayList<ArrayList<Integer>>  getInput(){
        ArrayList<ArrayList<Integer>> deckLists = new ArrayList<>();
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay22.txt"));
            String[] lines = input.replaceAll("\r","").replaceAll("\n","]").split("]]");
            for (String deck: lines){
                ArrayList<Integer> cards = new ArrayList<>();
                String[] card = deck.split("]");
                for (int i = 1; i <card.length;i++){
                    cards.add(Integer.parseInt(card[i]));
                }
                deckLists.add(cards);
            }

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return deckLists;
    }
}
