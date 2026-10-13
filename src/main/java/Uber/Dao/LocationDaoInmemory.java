package Uber.Dao;

import Uber.Entities.Enum.CabType;
import Uber.Entities.Enum.RiderStatus;
import Uber.Entities.Loc;
import Uber.Entities.RiderLocationResponse;
import Uber.database.LocationDBInmemory;
import Uber.database.RiderDBInmemory;
import Uber.Entities.DistanceRiderResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Uber.helpers.findDistance;

public class LocationDaoInmemory implements LocationDao {
    LocationDBInmemory locationDBInmemory;
    RiderDBInmemory riderDBInmemory;
    public LocationDaoInmemory(LocationDBInmemory locationDBInmemory, RiderDBInmemory riderDBInmemory) {
        this.locationDBInmemory = locationDBInmemory;
        this.riderDBInmemory = riderDBInmemory;
    }

    public List<DistanceRiderResponse> matchLocationAndType(Loc point, CabType cabType, double maxDistance) {
        List<DistanceRiderResponse> rides = new ArrayList<>();
        for(Map.Entry<String, Loc> entry : locationDBInmemory.getRiders().entrySet()){
            String riderId = entry.getKey();
            Loc position = entry.getValue();
            // matched if rider exists, cab type matches, state idle, distance < max dist
            System.out.println("Matching : "+riderId+" "+this.riderDBInmemory.getRiderTable().get(riderId).getRiderStatus()+this.locationDBInmemory.getRiders().get(riderId));
            if(this.riderDBInmemory.getRiderTable().containsKey(riderId) && this.riderDBInmemory.getRiderTable().get(riderId).cabDetails.getCabType().equals(cabType) && this.riderDBInmemory.getRiderTable().get(riderId).riderStatus.equals(RiderStatus.IDLE) ){
                Double distance = findDistance(position, point);
                if(distance<=maxDistance){
                    DistanceRiderResponse distanceRiderResponse = new DistanceRiderResponse(riderId, distance);
                    rides.add(distanceRiderResponse);
                }
            }
        }
        return rides;
    }

    public RiderLocationResponse updateLocation(String riderId, Loc point) {
        locationDBInmemory.getRiders().put(riderId, point);
        return new RiderLocationResponse(riderId, point);
    }



}
