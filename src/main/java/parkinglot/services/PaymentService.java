package parkinglot.services;

import parkinglot.entities.Bill;
import parkinglot.entities.Ticket;
import parkinglot.services.Strategy.PaymentStrategy;

import java.time.LocalTime;

public class PaymentService {
    PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public Bill getBill(Ticket ticket, LocalTime exitTime){
        Integer payment = paymentStrategy.calculate(ticket, exitTime);
        Bill bill = new Bill(ticket.getEntryTime(), exitTime, ticket.getVehicleType(), payment);
        return bill;
    }
}
