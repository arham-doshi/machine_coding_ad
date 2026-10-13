package Uber.Entities;

import Uber.Entities.Enum.CabType;
import Uber.Entities.Enum.PaymentStatus;
import Uber.Entities.Enum.RideStatus;
import parkinglot.entities.enums.VehicleType;

public class Ride {
    Integer rideId;
    String passengerId;
    String riderId;
    Integer fare;
    PaymentStatus paymentStatus;
    RideStatus rideStatus;
    Loc start;
    Loc destination;
    CabType cabType;
    static Integer rideCounter = 0;

    @Override
    public String toString() {
        return "Ride{" +
                "rideId=" + rideId +
                ", passengerId='" + passengerId + '\'' +
                ", riderId='" + riderId + '\'' +
                ", fare=" + fare +
                ", paymentStatus=" + paymentStatus +
                ", rideStatus=" + rideStatus +
                ", start=" + start +
                ", destination=" + destination +
                ", cabType=" + cabType +
                '}';
    }

    public Ride() {
        this.rideId = rideCounter++;
    }
    public void buildUnmatchedRide(String passengerId, Loc start, Loc destination, CabType cabType){

        this.start = start;
        this.destination = destination;
        this.cabType = cabType;
        this.rideStatus = RideStatus.UNMATCHED;
        this.passengerId = passengerId;

    }

    public CabType getCabType() {
        return cabType;
    }

    public void setCabType(CabType cabType) {
        this.cabType = cabType;
    }

    public Ride( String passengerId, String riderId, Integer fare, PaymentStatus paymentStatus, RideStatus rideStatus, Loc start, Loc destination, CabType cabType) {
        this.rideId = rideCounter++;
        this.passengerId = passengerId;
        this.riderId = riderId;
        this.fare = fare;
        this.paymentStatus = paymentStatus;
        this.rideStatus = rideStatus;
        this.start = start;
        this.destination = destination;
        this.cabType = cabType;
    }

    public Loc getStart() {
        return start;
    }

    public void setStart(Loc start) {
        this.start = start;
    }

    public Loc getDestination() {
        return destination;
    }

    public void setDestination(Loc destination) {
        this.destination = destination;
    }

    public Ride( String passengerId, String riderId, Integer fare, PaymentStatus paymentStatus, RideStatus rideStatus, Loc start, Loc destination) {
        this.rideId = rideCounter++;
        this.passengerId = passengerId;
        this.riderId = riderId;
        this.fare = fare;
        this.paymentStatus = paymentStatus;
        this.rideStatus = rideStatus;
        this.start = start;
        this.destination = destination;
    }

    public Ride( String passengerId, String riderId, Integer fare, PaymentStatus paymentStatus, RideStatus rideStatus) {
        this.rideId = rideCounter++;
        this.passengerId = passengerId;
        this.riderId = riderId;
        this.fare = fare;
        this.paymentStatus = paymentStatus;
        this.rideStatus = rideStatus;
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }
}
