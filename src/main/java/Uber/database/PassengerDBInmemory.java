package Uber.database;

import Uber.database.TableSchema.PassengerTableSchema;

import java.util.HashMap;
import java.util.Map;

public class PassengerDBInmemory {
    Map<String, PassengerTableSchema> users;
    Map<String, Integer> totalRatings;
    Map<String, Double> ratingSum;
    Map<String, Integer> totalRides;

    public Map<String, Integer> getTotalRides() {
        return totalRides;
    }

    public void setTotalRides(Map<String, Integer> totalRides) {
        this.totalRides = totalRides;
    }

    public PassengerDBInmemory() {
        users = new HashMap<>();
        totalRatings = new HashMap<>();
        ratingSum = new HashMap<>();
        totalRides = new HashMap<>();
    }

    public void setUsers(Map<String, PassengerTableSchema> users) {
        this.users = users;
    }

    public Map<String, PassengerTableSchema> getUsers() {
        return users;
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
}
