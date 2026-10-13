package parkinglot.services.Strategy;

import parkinglot.entities.Ticket;

import java.time.LocalTime;

public interface PaymentStrategy {
    public Integer calculate(Ticket ticket, LocalTime endtime);
}
