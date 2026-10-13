package Uber.database;

import Uber.database.TableSchema.RiderTableSchema;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RiderDBInmemory {

    Map<String , RiderTableSchema> riderTable;
    Map<String, Integer> totalRatings;
    Map<String, Double> ratingSum;
    Map<String, Integer> totalRides;

    public RiderDBInmemory() {

        this.riderTable = new HashMap<>();
        this.totalRides = new HashMap<>();
        this.ratingSum = new HashMap<>();
        this.totalRatings = new HashMap<>();
    }

    public Map<String, Integer> getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(Map<String, Integer> totalRatings) {
        this.totalRatings = totalRatings;
    }

    public Map<String, Double> getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(Map<String, Double> ratingSum) {
        this.ratingSum = ratingSum;
    }

    public Map<String, Integer> getTotalRides() {
        return totalRides;
    }

    public void setTotalRides(Map<String, Integer> totalRides) {
        this.totalRides = totalRides;
    }



    public Map<String, RiderTableSchema> getRiderTable() {
        return riderTable;
    }

    public void setRiderTable(Map<String, RiderTableSchema> riderTable) {
        this.riderTable = riderTable;
    }
}
