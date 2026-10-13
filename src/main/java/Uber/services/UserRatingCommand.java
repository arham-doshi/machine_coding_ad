package Uber.services;

public interface UserRatingCommand {
    public void rateRideUser(Integer rideId, Double rideRating, Double riderRating);
}
