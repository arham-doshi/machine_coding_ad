package Uber.services.fareStategy;

import Uber.Entities.Ride;

public interface FareCalculationStrategy {
    public Integer getFare(Ride ride);

}
