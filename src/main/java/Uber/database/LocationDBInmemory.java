package Uber.database;

import Uber.Entities.Loc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationDBInmemory {
    Map<String, Loc> riders;


    public LocationDBInmemory() {
        this.riders = new HashMap<>();
    }


    public Map<String, Loc> getRiders() {
        return riders;
    }

    public void setRider(Map<String, Loc> rider) {
        this.riders = rider;
    }
}
