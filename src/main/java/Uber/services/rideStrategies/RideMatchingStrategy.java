package Uber.services.rideStrategies;

import Uber.Entities.Enum.CabType;
import Uber.Entities.Loc;

public interface RideMatchingStrategy {
    public String matchRide(CabType cabType, Loc start, Loc destination); // returns the rider id
}
