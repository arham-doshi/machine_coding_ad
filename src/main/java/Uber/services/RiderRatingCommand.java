package Uber.services;

public interface RiderRatingCommand {
    public void rateRideRider(Integer rideId, Double rideRating, Double userRating);
}
