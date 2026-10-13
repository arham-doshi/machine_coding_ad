package Uber.database.TableSchema;

public class RatingsTableSchema {
    Integer rideId;
    Double riderRating;
    Double passengerRating;
    Double rideRatingByRider;
    Double rideRatingByPassenger;

    public RatingsTableSchema(Integer rideId) {
        this.rideId = rideId;
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public Double getRiderRating() {
        return riderRating;
    }

    public void setRiderRating(Double riderRating) {
        this.riderRating = riderRating;
    }

    public Double getPassengerRating() {
        return passengerRating;
    }

    public void setPassengerRating(Double passengerRating) {
        this.passengerRating = passengerRating;
    }

    public Double getRideRatingByRider() {
        return rideRatingByRider;
    }

    public void setRideRatingByRider(Double rideRatingByRider) {
        this.rideRatingByRider = rideRatingByRider;
    }

    public Double getRideRatingByPassenger() {
        return rideRatingByPassenger;
    }

    @Override
    public String toString() {
        return "RatingsTableSchema{" +
                "rideId=" + rideId +
                ", riderRating=" + riderRating +
                ", passengerRating=" + passengerRating +
                ", rideRatingByRider=" + rideRatingByRider +
                ", rideRatingByPassenger=" + rideRatingByPassenger +
                '}';
    }

    public void setRideRatingByPassenger(Double rideRatingByPassenger) {
        this.rideRatingByPassenger = rideRatingByPassenger;
    }
}
