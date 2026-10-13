package Uber.Entities;

import Uber.Entities.Enum.CabType;
import Uber.Entities.Enum.RiderStatus;

public class Rider extends User {
    RiderStatus riderStatus;
    CabDetails cabDetails;
    Double rating;
    Integer ridesCompleted;

    public Rider(String userid, String name, String email, String number, RiderStatus riderStatus, CabDetails cabDetails, Double rating, Integer ridesCompleted) {
        super(userid, name, email, number);
        this.riderStatus = riderStatus;
        this.cabDetails = cabDetails;
        this.rating = rating;
        this.ridesCompleted = ridesCompleted;
    }

    public Rider(String userid, String name, String email, String number, CabDetails cabDetails) {
        super(userid, name, email, number);
        this.cabDetails = cabDetails;
        this.riderStatus = RiderStatus.IDLE;
        this.rating = 0.0;
        this.ridesCompleted = 0;
    }

    public RiderStatus getRiderStatus() {
        return riderStatus;
    }

    public void setRiderStatus(RiderStatus riderStatus) {
        this.riderStatus = riderStatus;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "riderStatus=" + riderStatus +
                ", cabDetails=" + cabDetails +
                ", rating=" + rating +
                ", ridesCompleted=" + ridesCompleted +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public CabDetails getCabDetails() {
        return cabDetails;
    }

    public void setCabDetails(CabDetails cabDetails) {
        this.cabDetails = cabDetails;
    }
    public CabType getCabType(){
        return this.cabDetails.getCabType();
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRidesCompleted() {
        return ridesCompleted;
    }

    public void setRidesCompleted(Integer ridesCompleted) {
        this.ridesCompleted = ridesCompleted;
    }
}
