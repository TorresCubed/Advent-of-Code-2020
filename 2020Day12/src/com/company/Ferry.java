package com.company;

public class Ferry {

    private int shipX= 0;
    private int shipY = 0;
    private int wayPointX = 10;
    private int wayPointY = 1;
    private char direction = 'E';

    public Ferry() {
    }

    public int getX() {
        return shipX;
    }

    public int getY() {
        return shipY;
    }

    public void moveEast(int distance){
        this.wayPointX +=distance;
    }

    public void moveWest(int distance){
        this.wayPointX -=distance;
    }

    public void moveNorth(int distance){
        this.wayPointY +=distance;
    }

    public void moveSouth(int distance){
        this.wayPointY -=distance;
    }

    public void moveForward(int distance){
        int difX = wayPointX*distance;
        int difY = wayPointY*distance;
        this.shipX+=difX;
        this.shipY+=difY;
    }

    public void turnLeft(int degrees){
        degrees-=90;
        int tempx = -this.wayPointY;
        int tempy = this.wayPointX;
        this.wayPointY = tempy;
        this.wayPointX = tempx;
        if (degrees > 0){
            turnLeft(degrees);
        }
    }

    public void turnRight(int degrees){
        degrees-=90;
        int tempx = this.wayPointY;
        int tempy = -this.wayPointX;
        this.wayPointY = tempy;
        this.wayPointX = tempx;
        if (degrees > 0){
            turnRight(degrees);
        }
    }

}
