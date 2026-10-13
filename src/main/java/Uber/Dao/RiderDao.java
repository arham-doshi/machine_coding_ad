package Uber.Dao;

import Uber.Entities.Rider;

public interface RiderDao {
    public Rider getRider(String riderId);
    public void setRiderBusy(String riderId);
    public void setRiderIdle(String riderId);
    public void setRiderTerminated(String riderId);
    public void setRiderNotWorking(String riderId);
    public Boolean hasRider(String riderId);
    public Rider createRider(Rider rider);
    public void completeRide(String riderId);
    public void addRating(String riderId, Double rating);

}
