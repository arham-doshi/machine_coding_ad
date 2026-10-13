package Uber.services;

import Uber.Dao.LocationDao;
import Uber.Dao.PassengerDao;
import Uber.Dao.RideDao;
import Uber.Dao.RiderDao;
import Uber.Entities.Enum.CabType;
import Uber.Entities.Enum.PaymentStatus;
import Uber.Entities.Enum.RideStatus;
import Uber.Entities.Loc;
import Uber.Entities.Ride;
import Uber.services.fareStategy.FareCalculationStrategy;
import Uber.services.rideStrategies.RideMatchingStrategy;
import parkinglot.entities.enums.VehicleType;

import java.util.List;

public class RideService {
    RideMatchingStrategy rideMatchingStrategy;
    LocationDao locationDao;
    RiderDao riderDao;
    PassengerDao passengerDao;
    FareCalculationStrategy fareCalculationStrategy;
    RideDao rideDao;

    public RideService(RideMatchingStrategy rideMatchingStrategy, LocationDao locationDao, RiderDao riderDao, PassengerDao passengerDao, FareCalculationStrategy fareCalculationStrategy, RideDao rideDao) {
        this.rideMatchingStrategy = rideMatchingStrategy;
        this.locationDao = locationDao;
        this.riderDao = riderDao;
        this.passengerDao = passengerDao;
        this.fareCalculationStrategy = fareCalculationStrategy;
        this.rideDao = rideDao;
    }

    public Ride createRide(String userId, CabType cabType, Loc start, Loc dest){
        String riderId = this.rideMatchingStrategy.matchRide(cabType, start, dest);
        Ride ride = new Ride();
        ride.buildUnmatchedRide(userId, start, dest, cabType);
        if(riderId.equals("")){
            System.out.println("Ride Unmatched, stopping search");
            return ride;
        }
        ride.setRiderId(riderId);
        ride.setRideStatus(RideStatus.STARTED);
        riderDao.setRiderBusy(riderId);
        Integer fare = this.fareCalculationStrategy.getFare(ride);
        ride.setFare(fare);
        ride.setPaymentStatus(PaymentStatus.UNPAID);
        rideDao.addNewRide(ride);

        return ride;
    }
    public Ride getRide(Integer rideId){
        Ride ride = this.rideDao.getRide(rideId);
        return ride;
    }
    public Ride cancelRide(Integer rideId) {
        Ride ride = this.rideDao.getRide(rideId);
        if(ride == null){
            throw new IllegalArgumentException("no rides with ride Id : "+rideId);
        }
        if(ride.getRideStatus() == RideStatus.CANCELLED){
            System.out.println("Ride with Id : "+rideId+" already cancled");
            return ride;
        }
        if(ride.getRideStatus() == RideStatus.UNMATCHED || ride.getRideStatus() == RideStatus.COMPLETED) {
            throw new IllegalStateException("cannot cancelle ride due to illegal state transion : "+rideId);
        }
        ride.setRideStatus(RideStatus.CANCELLED);
        this.rideDao.updateRide(ride);
        this.riderDao.setRiderIdle(ride.getRiderId());

        return ride;
    }
    public Integer getRidePayment(Integer rideId){
        Ride ride = this.rideDao.getRide(rideId);
        return ride.getFare();
    }
    public Integer updatePayment(Integer rideId){
        Ride ride = this.rideDao.getRide(rideId);
        if(ride.getRideStatus() == RideStatus.UNMATCHED || ride.getRideStatus() == RideStatus.COMPLETED || ride.getRideStatus() == RideStatus.CANCELLED){
            throw new IllegalStateException("Cannot process payment for Ride, illegal state : "+rideId );
        }
        if(ride.getPaymentStatus() == PaymentStatus.PAID) {
            throw new IllegalStateException("Ride already paid : "+rideId);
        }
        ride.setPaymentStatus(PaymentStatus.PAID);
        this.rideDao.updateRide(ride);
        return ride.getFare();
    }
    public Boolean completeRide(Integer rideId){
        Ride ride = this.rideDao.getRide(rideId);
        if(ride.getRideStatus() == RideStatus.COMPLETED){
            System.out.println("Ride already Completed : "+rideId);
            return true;
        }
        if(ride.getRideStatus() == RideStatus.UNMATCHED || ride.getRideStatus() == RideStatus.CANCELLED){
            throw new IllegalStateException("Cannot complete Ride, Illegal state : "+ rideId + ride.getRideStatus().toString());
        }
        if(ride.getPaymentStatus() == PaymentStatus.UNPAID){
            throw new IllegalStateException("Cannot complete unpaid ride : "+rideId );

        }
        ride.setRideStatus(RideStatus.COMPLETED);
        this.rideDao.updateRide(ride);
        this.riderDao.completeRide(ride.getRiderId());
        this.riderDao.setRiderIdle(ride.getRiderId());
        this.passengerDao.completeRide(ride.getPassengerId());
        this.locationDao.updateLocation(ride.getRiderId(), ride.getDestination());

        return true;
    }
    public List<Ride> getAllRidesForPassenger(String userId){
        return this.rideDao.getAllPassengerRides(userId);
    }
    public void addRatingForUser(Integer rideId, Double rating){
        Ride ride = this.getRide(rideId);
        this.passengerDao.addRating(ride.getPassengerId(), rating);
    }
    public void addRatingForRider(Integer rideId, Double rating){
        Ride ride = this.getRide(rideId);
        this.riderDao.addRating(ride.getRiderId(), rating);
    }
}
