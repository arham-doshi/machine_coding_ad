package Uber.services;

import Uber.Dao.RatingsDao;
import Uber.Dao.RideDao;
import Uber.Dao.RiderDao;
import Uber.Entities.Ride;

public class RatingService implements UserRatingCommand, RiderRatingCommand {
    RatingsDao ratingsDao;
    RideDao rideDao;

    public RatingService(RatingsDao ratingsDao, RideDao rideDao) {
        this.ratingsDao = ratingsDao;
        this.rideDao = rideDao;
    }

    @Override
    public void rateRideRider(Integer rideId, Double rideRating, Double userRating) {
        if(!this.ratingsValid(rideRating) || !this.ratingsValid(userRating)){
            throw new IllegalArgumentException("cannot add rating out of range 0 to 5 "+rideId+" "+rideRating+" "+userRating);
        }
        // getting ride here will ensure that we are not updating rating for non exsistent rides.
        Ride ride = this.rideDao.getRide(rideId);
        this.ratingsDao.rateRideByRider(rideId, ride.getPassengerId(), rideRating, userRating);
    }

    @Override
    public void rateRideUser(Integer rideId, Double rideRating, Double riderRating) {
        if(!this.ratingsValid(rideRating) || !this.ratingsValid(rideRating)){
            throw new IllegalArgumentException("cannot add rating out of range 0 to 5 "+rideId+" "+rideRating+" "+riderRating);
        }
        Ride ride = this.rideDao.getRide(rideId);
        this.ratingsDao.rateRideByPassenger(rideId, ride.getRiderId(), rideRating, riderRating);
    }
    private Boolean ratingsValid(Double rating){
        Double error_rate = 1e-5;
        if(rating < 0){
            return Math.abs(rating)<=error_rate;
        }
        if(rating > 5){
            return Math.abs(rating - 5.0) <= error_rate;
        }
        return rating >= 0;
    }
}
