package Uber.Dao;

import Uber.Entities.Passenger;

public interface PassengerDao {

    public Boolean checkIfPresent(String userId);
    public Boolean createPassenger(Passenger passenger);
    public Passenger getPassenger(String userId);
    public void completeRide(String userId);
    public void addRating(String userId, Double rating);

}
