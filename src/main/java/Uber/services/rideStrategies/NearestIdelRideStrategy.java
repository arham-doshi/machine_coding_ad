package Uber.services.rideStrategies;

import Uber.Dao.LocationDao;
import Uber.Dao.RiderDao;
import Uber.Entities.DistanceRiderResponse;
import Uber.Entities.Enum.CabType;
import Uber.Entities.Loc;

import java.util.Dictionary;
import java.util.List;
import java.util.regex.MatchResult;

public class NearestIdelRideStrategy implements RideMatchingStrategy{
    LocationDao locationDao;
    RiderDao riderDao;
    Double maxDistance;
    public NearestIdelRideStrategy(LocationDao locationDao, RiderDao riderDao, Double maxDistance) {
        this.locationDao = locationDao;
        this.riderDao = riderDao;
        this.maxDistance = maxDistance;
    }



    @Override
    public String matchRide(CabType cabType, Loc start, Loc destination) {

        List<DistanceRiderResponse> riders = locationDao.matchLocationAndType(start, cabType, maxDistance);

        Integer ridersIdel = 0;
        if(riders.size() == 0){
            System.out.println("Could not find rider for ride");
            return "";
        }
        String currenRider = "";
        Double currentMinDis = Double.MAX_VALUE;
        for(DistanceRiderResponse response : riders){
            if(response.getDistance() < currentMinDis){
                currentMinDis = response.getDistance();
                currenRider = response.getRiderId();
            }
        }
        return currenRider;

    }
}
