package Uber.services.rideStrategies;

import Uber.Dao.LocationDao;
import Uber.Dao.RiderDao;
import Uber.Entities.DistanceRiderResponse;
import Uber.Entities.Enum.CabType;
import Uber.Entities.Loc;

import java.util.List;

public class NearAndRatingStrategy implements RideMatchingStrategy{
    LocationDao locationDao;
    RiderDao riderDao;
    Double maxDistance;

    public NearAndRatingStrategy(LocationDao locationDao, RiderDao riderDao, Double maxDistance) {
        this.locationDao = locationDao;
        this.riderDao = riderDao;
        this.maxDistance = maxDistance;
    }

    @Override
    public String matchRide(CabType cabType, Loc start, Loc destination) {

        List<DistanceRiderResponse> riders = locationDao.matchLocationAndType(start, cabType, maxDistance);


        if(riders.size() == 0){
            System.out.println("Could not find rider for ride");
            return "";
        }
        String currentRider = "";
        Double currentScore = 0.0;
        for(DistanceRiderResponse response : riders){
            double totalScore =  riderDao.getRider(response.getRiderId()).getRating() - response.getDistance();
            if(currentRider.equals("") || totalScore > currentScore){
                currentScore = totalScore;
                currentRider = response.getRiderId();
            }
        }
        return currentRider;

    }
}
