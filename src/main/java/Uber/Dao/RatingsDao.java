package Uber.Dao;

import Uber.Entities.PassengerRatingView;
import Uber.Entities.RiderRatingView;

import java.util.List;

public interface RatingsDao {
    public void rateRideByRider(Integer rideId, String passengerId,  double rideRating, double passengerRating);
    public void rateRideByPassenger(Integer rideId, String riderId, double rideRating, double riderRating);
    public List<RiderRatingView> getRatingsForRider(Integer riderId);
    public List<PassengerRatingView> getRatingsForUser(Integer userId);
}
