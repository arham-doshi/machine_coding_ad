package Uber.database;

import Uber.Entities.Ride;

import java.util.HashMap;
import java.util.Map;

public class RideDBInmemory {
    Map<Integer, Ride> rideMap;

    public RideDBInmemory() {
        this.rideMap = new HashMap<>();
    }

    public Map<Integer, Ride> getRideMap() {
        return rideMap;
    }

    public void setRideMap(Map<Integer, Ride> rideMap) {
        this.rideMap = rideMap;
    }
}
