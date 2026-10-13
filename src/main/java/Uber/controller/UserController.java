package Uber.controller;

import Uber.Entities.Enum.CabType;
import Uber.Entities.Enum.PaymentStatus;
import Uber.Entities.Enum.RideStatus;
import Uber.Entities.Loc;
import Uber.Entities.Passenger;
import Uber.Entities.Ride;
import Uber.services.PassengerService;
import Uber.services.PaymentsService;
import Uber.services.RideService;
import Uber.services.UserRatingCommand;
import Uber.services.rideStrategies.RideMatchingStrategy;
import parkinglot.entities.enums.VehicleType;

import java.util.List;

// input and output validations in the controller
public class UserController {
    PassengerService passengerService;
    RideService rideService;
    PaymentsService paymentsService;
    UserRatingCommand userRatingCommand;

    public UserController(PassengerService passengerService, RideService rideService, PaymentsService paymentsService, UserRatingCommand userRatingCommand) {
        this.passengerService = passengerService;
        this.rideService = rideService;
        this.paymentsService = paymentsService;
        this.userRatingCommand = userRatingCommand;
    }

    public Passenger registerUser(String userid, String name, String email, String number) {
        Passenger passenger = new Passenger(userid, name, email, number);
        Boolean isPassengerCreated = passengerService.createPassenger(passenger);
        if(isPassengerCreated){
            System.out.println("Created Passenger" + passenger);
            return passenger;
        }
        throw new RuntimeException("Cannot create passenger");
    }
    public Passenger getPassenger(String userId) {
        Passenger passenger = this.passengerService.getPassenger(userId);
        System.out.println("getting passenger : "+passenger);
        return passenger;
    }
    public Ride getRide(String userId, CabType cabType, Loc start, Loc dest) {
        Passenger passenger = this.passengerService.getPassenger(userId);

        if(passenger == null){
            throw new IllegalStateException("user non existent");
        }
        if(start.equals(dest)){
            throw new IllegalStateException("start and destination cannot be same");
        }
        Ride ride = rideService.createRide(userId, cabType, start, dest);
        System.out.println("Ride created : "+ride);
        return ride;
    }
    public Ride cancleRide(Integer rideId ){
        Ride ride = rideService.cancelRide(rideId);

        System.out.println("Cancled ride : "+ride);
        if(ride.getPaymentStatus() == PaymentStatus.PAID){
            System.out.println("Refunding payment for cancelled ride : "+rideId+" payment "+ride.getFare());
            this.paymentsService.payRaider(ride.getRiderId(), -1 * ride.getFare());
        }
        return ride;
    }
    public Ride completeRideWithPayment(Integer rideId) {
        Integer fare = this.rideService.getRidePayment(rideId);
        this.rideService.updatePayment(rideId);
        this.rideService.completeRide(rideId);
        Ride ride = this.rideService.getRide(rideId);
        this.paymentsService.payRaider(ride.getRiderId(), fare);
        System.out.println("Ride completed "+ride);
        return ride;
    }
    public Ride completeRideWithoutPayment(Integer rideId) {
        Integer fare = this.rideService.getRidePayment(rideId);
        this.rideService.completeRide(rideId);
        Ride ride = this.rideService.getRide(rideId);
        System.out.println("Ride completed "+ride);
        return ride;
    }
    public Integer getRidePayment(Integer rideId){
        Integer fare = this.rideService.getRidePayment(rideId);
        return fare;
    }
    public Integer payForRide(Integer rideId) {

        Integer fare = this.rideService.getRidePayment(rideId);
        this.rideService.updatePayment(rideId);
        Ride ride = this.rideService.getRide(rideId);
        this.paymentsService.payRaider(ride.getRiderId(), fare);
        System.out.println("payment for ride completed : "+ride);

        return fare;
    }
    public void rateRide(Integer rideId, double rideRating, double riderRating) {
        Ride ride = this.rideService.getRide(rideId);
        if(ride.getRideStatus() != RideStatus.COMPLETED){
            throw new IllegalStateException("Cannot rate uncompleted ride "+ride);
        }
        this.userRatingCommand.rateRideUser(rideId, rideRating, riderRating);
        this.rideService.addRatingForRider(rideId, rideRating);

    }

    public List<Ride> getAllUserRides(String userId){
        return this.rideService.getAllRidesForPassenger(userId);
    }
}
