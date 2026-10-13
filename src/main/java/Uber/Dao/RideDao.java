package Uber.Dao;

import Uber.Entities.Ride;

import java.util.List;

public interface RideDao {
    public void addNewRide(Ride ride);
    public List<Ride> getAllRaiderRides(String riderId);
    public List<Ride> getAllPassengerRides(String userId);
    public Ride getRide(Integer rideId);
    public void updateRide(Ride ride);
}
