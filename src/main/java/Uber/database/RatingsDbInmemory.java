package Uber.database;

import Uber.database.TableSchema.RatingsTableSchema;

import java.util.HashMap;
import java.util.Map;

public class RatingsDbInmemory {
    Map<Integer, RatingsTableSchema> ratings;
    Map<String, Map<Integer, RatingsTableSchema>> riderMappings;
    Map<String, Map<Integer, RatingsTableSchema>> passengerMappings;

    public RatingsDbInmemory() {
        this.ratings = new HashMap<>();
        this.riderMappings = new HashMap<>();
        this.passengerMappings = new HashMap<>();
    }

    public Map<Integer, RatingsTableSchema> getRatings() {
        return ratings;
    }

    public void setRatings(Map<Integer, RatingsTableSchema> ratings) {
        this.ratings = ratings;
    }

    public Map<String, Map<Integer, RatingsTableSchema>> getRiderMappings() {
        return riderMappings;
    }

    public void setRiderMappings(Map<String, Map<Integer, RatingsTableSchema>> riderMappings) {
        this.riderMappings = riderMappings;
    }

    public Map<String, Map<Integer, RatingsTableSchema>> getPassengerMappings() {
        return passengerMappings;
    }

    public void setPassengerMappings(Map<String, Map<Integer, RatingsTableSchema>> passengerMappings) {
        this.passengerMappings = passengerMappings;
    }
}
