package Uber.Entities;

public class RiderLocationResponse {
    String riderId;
    Loc riderLocation;

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public Loc getRiderLocation() {
        return riderLocation;
    }

    public void setRiderLocation(Loc riderLocation) {
        this.riderLocation = riderLocation;
    }

    public RiderLocationResponse(String riderId, Loc riderLocation) {
        this.riderId = riderId;
        this.riderLocation = riderLocation;
    }

    @Override
    public String toString() {
        return "RiderLocationResponse{" +
                "riderId='" + riderId + '\'' +
                ", riderLocation=" + riderLocation +
                '}';
    }
}
