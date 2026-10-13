package Uber.Dao;

import Uber.Entities.Ride;
import Uber.database.RideDBInmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RideDaoInmemory implements RideDao{
    RideDBInmemory rideDBInmemory;

    public RideDaoInmemory(RideDBInmemory rideDBInmemory) {
        this.rideDBInmemory = rideDBInmemory;
    }

    public void addNewRide(Ride ride){
        if(ride.getRideId() == null){
            throw new IllegalArgumentException("Malformed ride, cannot be added to db");
        }
        if(this.rideDBInmemory.getRideMap().get(ride.getRideId()) != null){
            System.out.println("Ride already added to db cannot add new ride");
            return;
        }
        this.rideDBInmemory.getRideMap().put(ride.getRideId(), ride);
    }
    public List<Ride> getAllRaiderRides(String riderId){
        List<Ride> rides = new ArrayList<>();
        for(Map.Entry<Integer, Ride> ride : this.rideDBInmemory.getRideMap().entrySet()){
            Ride currentRide = ride.getValue();
            if(currentRide.getRiderId().equals(riderId)){
                rides.add(currentRide);
            }
        }
        return rides;
    }
    public List<Ride> getAllPassengerRides(String userId){
        List<Ride> rides = new ArrayList<>();
        for(Map.Entry<Integer, Ride> ride : this.rideDBInmemory.getRideMap().entrySet()){
            Ride currentRide = ride.getValue();
            if(currentRide.getPassengerId().equals(userId)){
                rides.add(currentRide);
            }
        }
        return rides;
    }

    @Override
    public Ride getRide(Integer rideId) {
        return this.rideDBInmemory.getRideMap().get(rideId);
    }

    @Override
    public void updateRide(Ride ride) {
        Integer rideId = ride.getRideId();
        if(this.rideDBInmemory.getRideMap().get(rideId) == null){
            throw new IllegalStateException("Non exsistent ride for update, abording : "+rideId);
        }
        this.rideDBInmemory.getRideMap().put(rideId, ride);
    }
}
