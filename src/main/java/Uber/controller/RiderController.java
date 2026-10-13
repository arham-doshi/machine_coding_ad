package Uber.controller;

import Uber.Entities.CabDetails;
import Uber.Entities.Enum.CabType;
import Uber.Entities.Enum.RideStatus;
import Uber.Entities.Enum.RiderStatus;
import Uber.Entities.Loc;
import Uber.Entities.Ride;
import Uber.Entities.Rider;
import Uber.services.*;

import javax.swing.text.StyledEditorKit;

public class RiderController {
    RiderService riderService;
    PaymentsService paymentsService;
    RideService rideService;
    RiderRatingCommand riderRatingCommand;

    public RiderController(RiderService riderService, PaymentsService paymentsService, RiderRatingCommand riderRatingCommand, RideService rideService) {
        this.riderService = riderService;
        this.paymentsService = paymentsService;
        this.riderRatingCommand = riderRatingCommand;
        this.rideService = rideService;
    }

    public Rider registerRider(String riderId, String name, String email, String number, String cabNumber, CabType cabType){
        Rider rider = new Rider(riderId,name, email, number, new CabDetails(cabNumber, cabType));
        Boolean isRiderCreated = riderService.registerRider(rider);
        if(isRiderCreated){
            rider = this.riderService.getRider(riderId);
            System.out.println("Rider created : "+rider);
            return rider;
        }
        throw new RuntimeException("Rider not created : "+riderId);
    }
    public Rider getRider(String riderId){
        Rider rider = riderService.getRider(riderId);
        System.out.println("Getting rider : "+rider);
        return rider;
    }
    public Rider startWorking(String riderId, Loc loc){

        this.riderService.riderStartWorking(riderId, loc);
        Rider rider = this.riderService.getRider(riderId);
        return rider;
    }
    public Rider startStopWorking(String riderId){
        this.riderService.riderStopWorking(riderId);
        Rider rider = this.riderService.getRider(riderId);
        return rider;
    }
    public Rider stopWorkingPermanently(String riderId){
        this.riderService.riderTerminated(riderId);
        Rider rider = this.riderService.getRider(riderId);
        return rider;
    }
    public Integer getEarning(String riderId) {

        Rider rider = this.riderService.getRider(riderId);
        // in case of rider not found rider dao will throw error.
        if( rider.getRiderStatus() == RiderStatus.TERMINATED){

            return 0;
        }
        Integer earnings = this.paymentsService.getPendingPayment(riderId);
        System.out.println("Pending earnings for Rider :"+riderId+" is : "+earnings);
        return earnings;
    }
    public Integer withDrawEarnings(String riderId){
        Rider rider = this.riderService.getRider(riderId);
        // in case of rider not found rider dao will throw error.
        if( rider.getRiderStatus() == RiderStatus.TERMINATED){
            return 0;
        }
        Integer earnings = this.paymentsService.cashoutPayment(riderId);
        System.out.println("Cashingout earnings for Rider :"+riderId+" is : "+earnings);
        return earnings;

    }
    public void rateRide(Integer rideId, double rideRating, double passengerRating) {
        Ride ride = this.rideService.getRide(rideId);
        if(ride.getRideStatus() != RideStatus.COMPLETED){
            throw new IllegalStateException("Cannot rate uncompleted ride "+ride);
        }
        this.riderRatingCommand.rateRideRider(rideId, rideRating, passengerRating);
        this.rideService.addRatingForUser(rideId, passengerRating);
    }
    public void updateRiderLocation(String riderId, Loc loc){
        Rider rider = this.riderService.getRider(riderId);
        RiderStatus riderStatus = rider.getRiderStatus();
        if(riderStatus == RiderStatus.TERMINATED || riderStatus == RiderStatus.NOT_WORKING) {
            throw new IllegalStateException("Cannot update location for rider not working or terminated : "+riderId);
        }
        this.riderService.updateLocation(riderId, loc);

    }


}
