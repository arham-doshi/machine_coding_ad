package Uber.Entities;

public class DistanceRiderResponse {
    String riderId;
    Double distance;

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public DistanceRiderResponse(String riderId, Double distance) {
        this.riderId = riderId;
        this.distance = distance;
    }
}
