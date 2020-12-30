package com.company;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Tile> tileBounds = defineSides(Objects.requireNonNull(getInput()));
        Map<Integer,ArrayList<String>> image = getImage();
        for (Tile tile:tileBounds){
            tile.setInner(image.get(tile.getTileID()));
        }
        ArrayList<Tile> completed = new ArrayList<>();
//        tileBounds = firstConnection(tileBounds);
        completed.add(tileBounds.get(0));

        do {
            for (Tile tile:tileBounds){
               continuedConnections(completed,tile);
            }
        }while (completed.size() != tileBounds.size());

        for (Tile each: completed){
            each.getConnections();
        }
        long total = 1;
        for (Tile each: completed){
            int count = 0;
            for (String side: each.getAdjacent().keySet()){
                if (each.getAdjacent().get(side) == null){
                    count++;
                }
            }
            if (count == 2){
                total *= each.getTileID();
            }
        }
        System.out.println(total);

        Tile start = null;
        for (Tile each: tileBounds){
            if (each.getAdjacent().get("top") == null && each.getAdjacent().get("left") == null) {
                start = each;
            }
        }

        ArrayList<String> finalImage = start.getInner();
        Tile target = start.getAdjacent().get("right");

        boolean direction = true;
        while (true) {
            if (direction) {
                direction = goRight(target, finalImage);
                if (direction){
                    target = target.getAdjacent().get("right");
                } else {
                    target = target.getAdjacent().get("bottom");
                    if (target == null){
                        break;
                    }
                    finalImage.addAll(target.getInner());
                    target = target.getAdjacent().get("left");
                }
            } else {
                direction = goLeft(target,finalImage);
                if (!direction){
                    target = target.getAdjacent().get("left");
                } else {
                    target = target.getAdjacent().get("bottom");
                    if (target == null){
                        break;
                    }
                    finalImage.addAll(target.getInner());
                    target = target.getAdjacent().get("right");
                }
            }
        }

        ArrayList<String> seaMonster = getSeaMonster();

        int seas = 0;
        int count = 0;
        for (int y = 0; y <finalImage.size(); y++) {
            for (int x = 0; x < finalImage.get(0).length(); x++) {
                count += seaMonsterRotations(seaMonster,finalImage,x,y);
            }
        }

        for (String each: finalImage){
            for (int x = 0; x <each.length(); x++){
                if (each.charAt(x)=='#') seas++;
            }
            System.out.println(each);
        }
        System.out.println(seas);


    }

    public static boolean seaMonsterCheck(ArrayList<String> seaMonster, ArrayList<String> image, int x, int y){
//        for (String each: seaMonster){
//            System.out.println(each);
//        }
        for (int seaY = 0; seaY<seaMonster.size(); seaY++) {
            for (int seaX = 0; seaX < seaMonster.get(seaY).length(); seaX++) {
                char seaMonsterChar = seaMonster.get(seaY).charAt(seaX);
                if (seaMonsterChar == '#') {
                    int imageX = x + seaX;
                    int imageY = y + seaY;
                    if (imageX >= 0 && imageX < image.get(0).length() && imageY >= 0 && imageY < image.size()) {
                        char imageChar = image.get(imageY).charAt(imageX);
                        if (imageChar != '#') {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        seaMonsterOccupy(seaMonster,image,x,y);
        return true;
    }

    public static void seaMonsterOccupy(ArrayList<String> seaMonster, ArrayList<String> image, int x, int y) {
        for (int seaY = 0; seaY<seaMonster.size(); seaY++) {
            for (int seaX = 0; seaX < seaMonster.get(seaY).length(); seaX++) {
                char seaMonsterChar = seaMonster.get(seaY).charAt(seaX);
                if (seaMonsterChar == '#') {
                    int imageX = x + seaX;
                    int imageY = y + seaY;
                    String first = image.get(imageY).substring(0,imageX);
                    String second = image.get(imageY).substring(imageX+1);
                    image.set(imageY,first+"O"+second);
                }
            }
        }
    }



    public static ArrayList<String> seaMosnterRotate(ArrayList<String> seaMonster){
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i <seaMonster.get(0).length(); i++){
            temp.add("");
        }
        for (int row = seaMonster.size()-1; row>=0;row--) {
            for (int o = 0; o < seaMonster.get(0).length(); o++) {
                temp.set(o, temp.get(o) + seaMonster.get(row).charAt(o));
            }
        }
        return temp;
    }

    public static void seaMosnterFlipHorizontal(ArrayList<String> seaMonster){
        for (int i = 0; i <seaMonster.size(); i++){
            seaMonster.set(i,flipString(seaMonster.get(i)));
        }
    }

    public static String flipString(String toFlip){
        StringBuilder flipped = new StringBuilder();
        for (int i = toFlip.length()-1; i >=0 ; i--){
            flipped.append(toFlip.charAt(i));
        }
        return flipped.toString();
    }

    public static void seaMosnterFlipVertical(ArrayList<String> seaMonster){
        String temp = "";
        for (int i = 0; i <seaMonster.size()/2; i++){
            temp = seaMonster.get(i);
            seaMonster.set(i,seaMonster.get(seaMonster.size()-i-1));
            seaMonster.set(seaMonster.size()-i-1, temp);
        }
    }

    public static int seaMonsterRotations(ArrayList<String> seaMonster, ArrayList<String> image, int x, int y){
        int count = 0;
        for (int r = 0; r <4; r++){
            if(seaMonsterCheck(seaMonster,image, x, y)) count++;
            count += seaMonsterFlips(seaMonster,image, x, y);
            seaMonster = seaMosnterRotate(seaMonster);
        }
        return count;
    }

    public static int seaMonsterFlips(ArrayList<String> seaMonster, ArrayList<String> image, int x, int y){
        seaMosnterFlipHorizontal(seaMonster);
        int count = 0;
        if (seaMonsterCheck(seaMonster,image, x, y)) {
            count++;
        }
        seaMosnterFlipVertical(seaMonster);
        if (seaMonsterCheck(seaMonster,image, x, y)) {
            count++;
        }
        seaMosnterFlipHorizontal(seaMonster);
        if (seaMonsterCheck(seaMonster,image, x, y)) {
            count++;
        }
        seaMosnterFlipVertical(seaMonster);
        return count;
    }


    public static boolean goRight(Tile current,ArrayList<String> finalImage){
        int start = finalImage.size()-current.getInner().size();
        if (current.getAdjacent().get("right") !=null) {
            for (int i = 0 ; i<current.getInner().size(); i++){
                finalImage.set(start+i,finalImage.get(start+i) +current.getInner().get(i));
            }
        } else {
            for (int i = 0 ; i<current.getInner().size(); i++){
                finalImage.set(start+i,finalImage.get(start+i) +current.getInner().get(i));
            }
            return false;
        }
        return true;
    }

    public static boolean goLeft(Tile current, ArrayList<String> finalImage){
        int start = finalImage.size()-current.getInner().size();
        if (current.getAdjacent().get("left") !=null) {
            for (int i = 0 ; i<current.getInner().size(); i++){
                finalImage.set(start+i,current.getInner().get(i) + finalImage.get(start+i));
            }
        } else {
            for (int i = 0 ; i<current.getInner().size(); i++){
                finalImage.set(start+i,current.getInner().get(i) + finalImage.get(start+i));
            }
            return true;
        }
        return false;
    }

    public static  void continuedConnections(ArrayList<Tile> completed,Tile original){
        ArrayList<Tile> temp = new ArrayList<>();
        for (Tile tile : completed) {
            if (original != tile) {
                if (rotations(tile, original)){
                    if (!completed.contains(original) && !temp.contains(original)) {
                        temp.add(original);
                    }
                }
            }
        }
        completed.addAll(temp);

    }

    public static ArrayList<Tile> firstConnection( ArrayList<Tile> tiles) {
        for (int i = 1; i <tiles.size(); i++){
            rotations(tiles.get(0),tiles.get(i));
        }
        return tiles;
    }

    public static boolean rotations(Tile original, Tile modifier){
        for (int r = 0; r <4; r++){
            if (configure(original,modifier).equals("no")) {
                if (flips(original,modifier)){
                    return true;
                }
                modifier.rotate();
            } else {
                return true;
            }
        }
        modifier.rotate();
        return false;
    }

    public static boolean flips(Tile original, Tile modifier){
        modifier.flipHorizontal();
        if (configure(original,modifier).equals("no")) {
            modifier.flipVertical();
        } else {
            return true;
        }
        if (configure(original,modifier).equals("no")) {
            modifier.flipHorizontal();
        } else {
            return true;
        }
        if (configure(original,modifier).equals("no")) {
            modifier.flipVertical();
        } else {
            return true;
        }
        return false;
    }

    public static String configure(Tile original, Tile modifier){
        if (original.getSides().get("top").equals(modifier.getSides().get("bottom"))){
            original.setAdjacent("top", modifier);
            modifier.setAdjacent("bottom", original);
            modifier.setLock();
            return "top";
        } else if (original.getSides().get("bottom").equals(modifier.getSides().get("top"))){
            original.setAdjacent("bottom", modifier);
            modifier.setAdjacent("top", original);
            modifier.setLock();
            return "bottom";
        } else if (original.getSides().get("right").equals(modifier.getSides().get("left"))){
            original.setAdjacent("right", modifier);
            modifier.setAdjacent("left", original);
            modifier.setLock();
            return "right";
        } else if (original.getSides().get("left").equals(modifier.getSides().get("right"))){
            original.setAdjacent("left", modifier);
            modifier.setAdjacent("right", original);
            modifier.setLock();
            return "left";
        }
        return "no";
    }



    public static Map<Integer,ArrayList<String>> getImage(){
        Map<Integer,ArrayList<String>> image = new HashMap<>();
        for (String tile:getInput()) {
            int tileID = getTileNumber(tile);
            tile = tile.substring(tile.indexOf("]") + 2);
            ArrayList<String> rows = new ArrayList<>(Arrays.asList(tile.split("]]")));
            rows.remove(0);
            rows.remove(rows.size()-1);
            ArrayList<String> temp = new ArrayList<>();
            for (String each:rows){
                temp.add(each.substring(1,each.length()-1));
            }
            image.put(tileID,temp);
        }
        return image;
    }





    public static ArrayList<Tile> defineSides(String[] tiles){
        ArrayList<Tile> set = new ArrayList<>();
        for (String tile:tiles){
            Tile temp = new Tile(getSides(tile),getTileNumber(tile));
            set.add(temp);
        }
        return set;
    }


    public static ArrayList<String> getSides(String tile){
        tile = tile.substring(tile.indexOf("]")+2);
        ArrayList<String> rows = new ArrayList<>(Arrays.asList(tile.split("]]")));
        ArrayList<String> sides = new ArrayList<>();
        sides.add(rows.get(0));
        sides.add(rows.get(rows.size()-1));
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        for (String row:rows){
            left.append(row.charAt(0));
            right.append(row.charAt(row.length() - 1));
        }
        sides.add(left.toString());
        sides.add(right.toString());
        return sides;
    }


    public static int getTileNumber(String tile){
        return Integer.parseInt(tile.substring(5,9));
    }


    public static String[]  getInput(){
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventDay20.txt"));
            String rRemoved = input.replaceAll("\r","");
            String resetting = rRemoved.replaceAll("\n","]]");

            return resetting.split("]]]]");

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return null;
    }


    public static ArrayList<String> getSeaMonster(){
        ArrayList<String> seaMonster = new ArrayList<>();
        try {
            String input = Files.readString(Paths.get("C:\\Users\\torre\\IdeaProjects\\adventText2020\\adventSeaMonster.txt"));
            String rRemoved = input.replaceAll("\r","");
            Scanner scanner = new Scanner(rRemoved);

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                seaMonster.add(line);
            }

        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return seaMonster;
    }
}
