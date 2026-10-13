package Uber;

import Uber.Entities.Loc;

public class helpers {
    public static double findDistance(Loc point1, Loc point2){
        return Math.sqrt( Math.pow(point1.getX()-point2.getX(), 2) +  Math.pow(point1.getY()-point2.getY(), 2));
    }
}
