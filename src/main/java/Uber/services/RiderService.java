package Uber.services;

import Uber.Dao.LocationDao;
import Uber.Dao.RiderDao;
import Uber.Entities.Enum.CabType;
import Uber.Entities.Enum.RiderStatus;
import Uber.Entities.Loc;
import Uber.Entities.Rider;
import Uber.Entities.RiderLocationResponse;

public class RiderService {
    RiderDao riderDao;
    LocationDao locationDao;

    public RiderService(RiderDao riderDao, LocationDao locationDao) {
        this.riderDao = riderDao;
        this.locationDao = locationDao;
    }

    public Boolean registerRider(Rider rider) {
        Boolean riderAlreadyPresent = riderDao.hasRider(rider.getUserid());
        if(riderAlreadyPresent){
            throw new IllegalStateException("rider already present : "+rider.getUserid());
        }
        riderDao.createRider(rider);
        return true;
    }

    public Rider getRider(String riderId){
        Rider rider = this.riderDao.getRider(riderId);
        return rider;
    }

    public void riderStartWorking(String riderId, Loc loc) {

        this.riderDao.setRiderIdle(riderId);
        this.locationDao.updateLocation(riderId, loc);
    }
    public void riderStopWorking(String riderId){
        this.riderDao.setRiderNotWorking(riderId);
    }
    public void riderTerminated(String riderId){
        this.riderDao.setRiderTerminated(riderId);
    }
    public void updateLocation(String riderId, Loc point){
        RiderLocationResponse riderLocationResponse = locationDao.updateLocation(riderId,point);
        System.out.println("Rider location updated : "+riderLocationResponse);
    }

}
