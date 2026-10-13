package Uber.Dao;

import Uber.Entities.PassengerRatingView;
import Uber.Entities.RiderRatingView;
import Uber.database.RatingsDbInmemory;
import Uber.database.TableSchema.RatingsTableSchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingsDaoInmemory implements RatingsDao {
    RatingsDbInmemory ratingsDbInmemory;

    public RatingsDaoInmemory(RatingsDbInmemory ratingsDbInmemory) {
        this.ratingsDbInmemory = ratingsDbInmemory;
    }
    public void rateRideByRider(Integer rideId, String passengerId,  double rideRating, double passengerRating){
        RatingsTableSchema ratingsTableSchema = ratingsDbInmemory.getRatings().get(rideId);
        if(ratingsTableSchema == null){
            ratingsTableSchema = new RatingsTableSchema(rideId);
        }
        // what if ride already rated by rider ?
        // in that case should we update the ratings ? for now no rider can rate user only once.
//        System.out.println("printing rating :"+ratingsTableSchema);
        if(ratingsTableSchema.getPassengerRating() != null){
            throw new IllegalStateException("This ride is already rated for user ride id : "+rideId+" and passenger id : "+passengerId);
        }
        ratingsTableSchema.setRideRatingByRider(rideRating);
        ratingsTableSchema.setPassengerRating(passengerRating);
        this.ratingsDbInmemory.getRatings().put(rideId, ratingsTableSchema);
        if(this.ratingsDbInmemory.getPassengerMappings().get(passengerId) == null){
            this.ratingsDbInmemory.getPassengerMappings().put(passengerId, new HashMap<>());
        }
        this.ratingsDbInmemory.getPassengerMappings().get(passengerId).put(rideId, ratingsTableSchema);
    }
    public void rateRideByPassenger(Integer rideId, String riderId, double rideRating, double riderRating) {
        RatingsTableSchema ratingsTableSchema = ratingsDbInmemory.getRatings().get(rideId);
        if(ratingsTableSchema == null){
            ratingsTableSchema = new RatingsTableSchema(rideId);
        }
        if(ratingsTableSchema.getRiderRating() != null){
            throw new IllegalStateException("This ride is already rated for rider ride id : "+rideId+" and passenger id : "+riderId);
        }
        ratingsTableSchema.setRideRatingByPassenger(rideRating);
        ratingsTableSchema.setRiderRating(riderRating);
        this.ratingsDbInmemory.getRatings().put(rideId, ratingsTableSchema);
        if(this.ratingsDbInmemory.getRiderMappings().get(riderId) == null){
            this.ratingsDbInmemory.getRiderMappings().put(riderId, new HashMap<>());
        }
        this.ratingsDbInmemory.getRiderMappings().get(riderId).put(rideId, ratingsTableSchema);
    }
    public List<RiderRatingView> getRatingsForRider(Integer riderId){
        Map<Integer, RatingsTableSchema> riderRatings = this.ratingsDbInmemory.getRiderMappings().get(riderId);
        if(riderRatings == null){
            System.out.println("found no ratings for rider : "+riderId);
            return new ArrayList<>();
        }
        List<RiderRatingView> result = new ArrayList<>();
        for(Map.Entry<Integer, RatingsTableSchema> entry : riderRatings.entrySet()){
            RiderRatingView riderRatingView = new RiderRatingView(entry.getKey(), entry.getValue().getRideRatingByPassenger(), entry.getValue().getRiderRating());
            result.add(riderRatingView);
        }
        return result;
    }
    public List<PassengerRatingView> getRatingsForUser(Integer userId){
        Map<Integer, RatingsTableSchema> riderRatings = this.ratingsDbInmemory.getPassengerMappings().get(userId);
        if(riderRatings == null){
            System.out.println("found no ratings for rider : "+userId);
            return new ArrayList<>();
        }
        List<PassengerRatingView> result = new ArrayList<>();
        for(Map.Entry<Integer, RatingsTableSchema> entry : riderRatings.entrySet()){
            PassengerRatingView passengerRatingView = new PassengerRatingView(entry.getKey(), entry.getValue().getRideRatingByRider(), entry.getValue().getPassengerRating());
            result.add(passengerRatingView);
        }
        return result;
    }
}
