package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tile {

    private Map<String,String> sides = new HashMap<>();
    private Map<String,Tile> adjacent = new HashMap<>();
    private int tileID;
    private boolean lock = false;
    private ArrayList<String> inner = new ArrayList<>();

    public Tile(ArrayList<String> list, int tileID) {
        setSides(list);
        this.adjacent.put("top",null);
        this.adjacent.put("bottom",null);
        this.adjacent.put("left",null);
        this.adjacent.put("right",null);
        this.tileID = tileID;
    }

    public ArrayList<String> getInner(){
        return this.inner;
    }

    public void setInner(ArrayList<String> setter){
        this.inner = setter;
    }

    public void setLock(){
        this.lock= true;
    }

    public Map<String, String> getSides() {
        return sides;
    }

    public Map<String, Tile> getAdjacent() {
        return adjacent;
    }

    private void setSides(ArrayList<String> list){
        this.sides.put("top", list.get(0));
        this.sides.put("bottom", list.get(1));
        this.sides.put("left", list.get(2));
        this.sides.put("right", list.get(3));
    }

    public void setAdjacent(String side, Tile target){
        this.adjacent.replace(side,target);

    }

    public int getTileID() {
        return tileID;
    }

    public void getConnections(){
        System.out.println("This tile ID is: " + this.tileID);
        if (this.adjacent.get("top") !=null) {
            System.out.println("Top is: " + this.adjacent.get("top").getTileID());
        } else {
            System.out.println("Top is Empty");
        }
        if (this.adjacent.get("bottom") !=null) {
            System.out.println("Bottom is: " + this.adjacent.get("bottom").getTileID());
        } else {
            System.out.println("Bottom is Empty");
        }
        if (this.adjacent.get("right") !=null) {
            System.out.println("Right is: " + this.adjacent.get("right").getTileID());
        } else {
            System.out.println("Right is Empty");
        }
        if (this.adjacent.get("left") !=null) {
            System.out.println("Left is: " + this.adjacent.get("left").getTileID());
        } else {
            System.out.println("Left is Empty");
        }
        System.out.println("=======================");
    }

    private void flipInnerHorizontal(){
        for (int i = 0; i <this.inner.size(); i++){
            this.inner.set(i,flipString(this.inner.get(i)));
        }
    }

    public void flipHorizontal(){
        if (!lock) {
            String temp;
            temp = this.sides.get("left");
            this.sides.replace("top", flipString(this.sides.get("top")));
            this.sides.replace("left", this.sides.get("right"));
            this.sides.replace("bottom", flipString(this.sides.get("bottom")));
            this.sides.replace("right", temp);
            flipInnerHorizontal();
        }
    }

    private void flipInnerVertical(){
        String temp = "";
        for (int i = 0; i <this.inner.size()/2; i++){
            temp = this.inner.get(i);
            this.inner.set(i,this.inner.get(this.inner.size()-i-1));
            this.inner.set(this.inner.size()-i-1, temp);
        }
    }


    public void flipVertical(){
        if (!lock) {
            String temp;
            temp = this.sides.get("top");
            this.sides.replace("left", flipString(this.sides.get("left")));
            this.sides.replace("top", this.sides.get("bottom"));
            this.sides.replace("right", flipString(this.sides.get("right")));
            this.sides.replace("bottom", temp);
            flipInnerVertical();
        }
    }


    private void rotateInner(){
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i <this.inner.get(0).length(); i++){
            temp.add("");
        }
        for (int row = this.inner.size()-1; row>=0;row--) {
            for (int o = 0; o < this.inner.get(0).length(); o++) {
                temp.set(o, temp.get(o) + this.inner.get(row).charAt(o));
            }
        }
        this.inner = temp;
    }

    public void rotate(){
        if (!lock) {
            String temp;
            temp = this.sides.get("top");
            this.sides.replace("top", flipString(this.sides.get("left")));
            this.sides.replace("left", this.sides.get("bottom"));
            this.sides.replace("bottom", flipString(this.sides.get("right")));
            this.sides.replace("right", temp);
            rotateInner();
        }
    }

    public String flipString(String toFlip){
        StringBuilder flipped = new StringBuilder();
        for (int i = toFlip.length()-1; i >=0 ; i--){
            flipped.append(toFlip.charAt(i));
        }
        return flipped.toString();
    }





}
