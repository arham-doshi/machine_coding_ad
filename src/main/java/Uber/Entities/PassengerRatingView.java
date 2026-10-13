package Uber.Entities;

public class PassengerRatingView {
    Integer rideId;
    Double rideRating;
    Double passengerRating;

    @Override
    public String toString() {
        return "PassengerRatingView{" +
                "rideId='" + rideId + '\'' +
                ", rideRating=" + rideRating +
                ", passengerRating=" + passengerRating +
                '}';
    }

    public PassengerRatingView(Integer rideId, Double rideRating, Double passengerRating) {
        this.rideId = rideId;
        this.rideRating = rideRating;
        this.passengerRating = passengerRating;
    }
}
