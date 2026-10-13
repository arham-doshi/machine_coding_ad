package Uber.services.fareStategy;

import Uber.Entities.Enum.CabType;
import Uber.Entities.Loc;
import Uber.Entities.Ride;

import static Uber.helpers.findDistance;

public class NormalFareStrategy implements FareCalculationStrategy{
    static double distanceMultiplier = 1.0;
    static double autoMultiplier = 1.0;
    static double sedanMultiplier = 1.5;
    static double suvMultiplier = 2.0;
    static double minFare = 20;



    @Override
    public Integer getFare(Ride ride) {
        Loc start = ride.getStart();
        Loc destination = ride.getDestination();
        double distance = findDistance(start, destination);
        double fare = distance*distanceMultiplier;
        if(ride.getCabType() == CabType.AUTO){
            fare *= autoMultiplier;
        }
        else if( ride.getCabType() == CabType.SEDAN){
            fare *= sedanMultiplier;
        }
        else {
            fare *= suvMultiplier;
        }
        if(fare <minFare){
            fare = minFare;
        }
        return (int) fare;
    }
}
