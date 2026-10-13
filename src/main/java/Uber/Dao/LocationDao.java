package Uber.Dao;

import Uber.Entities.Enum.CabType;
import Uber.Entities.Loc;
import Uber.Entities.DistanceRiderResponse;
import Uber.Entities.RiderLocationResponse;

import java.util.List;

public interface LocationDao {
    public List<DistanceRiderResponse> matchLocationAndType(Loc point, CabType cabType, double distance);
    public RiderLocationResponse updateLocation(String riderId, Loc point);
}
