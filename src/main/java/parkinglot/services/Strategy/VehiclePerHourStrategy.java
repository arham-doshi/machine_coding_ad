package parkinglot.services.Strategy;

import parkinglot.entities.Ticket;
import parkinglot.entities.enums.VehicleType;

import java.time.Duration;
import java.time.LocalTime;

public class VehiclePerHourStrategy implements PaymentStrategy {
    final private double hourMultiplier = 1; // we can adjust this to increase overall rent for example on weekends
    final private  double perCar = 50;
    final private  double perBike = 20;
    final private  double perTruck = 100;
    @Override
    public Integer calculate(Ticket ticket, LocalTime endTime) {

        Duration duration = Duration.between(ticket.getEntryTime(), endTime);
        Double hour = duration.toMinutes() / 60.0;
        Double payment = hour * hourMultiplier;
        switch (ticket.getVehicleType()){
            case CAR:
                payment *= perCar;
                break;
            case BIKE:
                payment *= perBike;
                break;
            case TRUCK:
                payment *= perTruck;
                break;
            default:
                payment *= perTruck;
        }
        return Math.toIntExact(Math.round(payment));


    }


}
