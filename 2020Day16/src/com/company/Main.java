package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        part1();
        part2();

    }

    public static ArrayList<Integer> getMyTicket(String[] fields){
        ArrayList<Integer> myTicketFields = new ArrayList<>();
        for (String field: fields){
            myTicketFields.add(Integer.parseInt(field));
        }
        return myTicketFields;
    }

    public static void part2(){
        Map<String,String[]> ticketInfo = mapedInput();
        Map<String,ArrayList<ArrayList<Integer>>> rules =  splitRules(ticketInfo);
        ArrayList<ArrayList<Integer>> otherTickets = getOtherTickets(ticketInfo);


        otherTickets = getFaultyTickets(otherTickets,rules);
        ArrayList<Integer> myTicketFields = getMyTicket(ticketInfo.get("my ticket"));

//        otherTickets.add(myTicketFields);
        ArrayList<String> ruleList = new ArrayList<>();
        ArrayList<Integer>  order = new ArrayList<>();

        do {
            for (int i = 0; i <otherTickets.get(0).size(); i++) {
                ArrayList<String> temp = new ArrayList<>();
                for (String rule:rules.keySet()){
                    boolean setRule = true;
                    for (ArrayList<Integer> ticket : otherTickets) {
                        if (!((ticket.get(i) >= rules.get(rule).get(0).get(0) && ticket.get(i) <= rules.get(rule).get(0).get(1)) || (ticket.get(i) >= rules.get(rule).get(1).get(0) && ticket.get(i) <= rules.get(rule).get(1).get(1)))) {
                            setRule = false;
                            break;
                        }
                    }
                    if (setRule) {
                        temp.add(rule);
                    }
                }
                if (temp.size()==1){
                    rules.remove(temp.get(0));
                    ruleList.add(temp.get(0));
                    order.add(i);
                }
            }
        } while (rules.keySet().size() >0);

        ArrayList<Integer> six = new ArrayList<>();

        for (Integer each:order){
            System.out.println(each);
        }
        long product = 1;
        for (int i = 0; i <ruleList.size();i++){
            if (ruleList.get(i).contains("departure")){
                System.out.println("===========================");
                System.out.println(order.get(i));
                System.out.println(myTicketFields.get(order.get(i)));
                System.out.println(ruleList.get(i));
                six.add(myTicketFields.get(order.get(i)));
                product*=myTicketFields.get(order.get(i));
            }
        }
        System.out.println(product);




    }

    public static ArrayList<ArrayList<Integer>> getFaultyTickets(ArrayList<ArrayList<Integer>> otherTickets, Map<String,ArrayList<ArrayList<Integer>>> rules){
        ArrayList<ArrayList<Integer>> faultyTickets = new ArrayList<>();
        for (ArrayList<Integer> ticket :otherTickets){
            for (Integer field: ticket){
                boolean real =false;
                if (field == 0){
                }
                for (String rule: rules.keySet()){
                    if (((field >= rules.get(rule).get(0).get(0) && field <= rules.get(rule).get(0).get(1)) || (field >= rules.get(rule).get(1).get(0) && field <= rules.get(rule).get(1).get(1)))) {
                        real = true;
                    }
                }
                if (!real) {
                    if (!faultyTickets.contains(ticket)) {
                        faultyTickets.add(ticket);
                        break;
                    }
                }
            }
        }
        otherTickets.removeAll(faultyTickets);
        return otherTickets;
    }

    public static void part1(){
        Map<String,String[]> ticketInfo = mapedInput();
        Map<String,ArrayList<ArrayList<Integer>>> rules =  splitRules(ticketInfo);
        ArrayList<ArrayList<Integer>> otherTickets = getOtherTickets(ticketInfo);

        int scanningErrorRate = 0;
        for (ArrayList<Integer> ticket :otherTickets){
            for (Integer field: ticket){
                boolean real =false;
                for (String rule: rules.keySet()){
                    if (((field >= rules.get(rule).get(0).get(0) && field <= rules.get(rule).get(0).get(1)) || (field >= rules.get(rule).get(1).get(0) && field <= rules.get(rule).get(1).get(1)))) {
                        real = true;
                    }
                }
                if (!real) {
                    scanningErrorRate += field;
                }
            }
        }
    }


    public static ArrayList<ArrayList<Integer>> getOtherTickets(Map<String,String[]> ticketInfo){
        ArrayList<ArrayList<Integer>> otherTickets = new ArrayList<>();
        for (String ticket: ticketInfo.get("other tickets")){
            ArrayList<Integer> fields = new ArrayList<>();
            for (String field: ticket.split(",")){
                fields.add(Integer.parseInt(field));
            }
            otherTickets.add(fields);
        }
        return otherTickets;
    }


    public static Map<String,ArrayList<ArrayList<Integer>>> splitRules(Map<String,String[]> ticketInfo){
        Map<String,ArrayList<ArrayList<Integer>>> rules = new HashMap<>();
        for (String rule:ticketInfo.get("rules")){
            String[] deff = rule.split(": ");
            String[] ranges = deff[1].split(" or ");
            ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
            for (String range: ranges){
                ArrayList<Integer> numbers = new ArrayList<>();
                for (String num:range.split("-"))
                    numbers.add(Integer.parseInt(num));
                temp.add(numbers);
            }
            rules.put(deff[0],temp);
        }
        return rules;
    }

    public static Map<String,String[]> mapedInput(){
        String[] input = getInput();
        Map<String,String[]> ticketRules = new HashMap<>();
        ticketRules.put("rules", input[0].split("[\n]"));
        String[] myTicket = input[1].split("[\n]");
        ticketRules.put("my ticket",myTicket[1].split(","));
        String[] otherTicket = input[2].split(":\n");
        ticketRules.put("other tickets",otherTicket[1].split("[\n]"));
        return ticketRules;
    }


    public static String[] getInput(){
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay16.txt"));
            String newInput = input.replaceAll("\r\n\r\n","::").replaceAll("\r", "");


            String[] parsedInput = newInput.split("::");


            return parsedInput;
        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return null;
    }
}
