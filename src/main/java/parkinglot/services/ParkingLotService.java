package parkinglot.services;

import parkinglot.controllers.ParkingLotController;
import parkinglot.entities.Bill;
import parkinglot.entities.Ticket;
import parkinglot.entities.Vehicle;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotService {
        ParkingLotController parkingLotController;
        PaymentService paymentService;

    public ParkingLotService(ParkingLotController parkingLotController, PaymentService paymentService) {
        this.parkingLotController = parkingLotController;
        this.paymentService = paymentService;
    }


    public Boolean isParkingAvailable(Vehicle vehicle){

        boolean hasSlot = parkingLotController.hasEmpty(vehicle.getType());
        System.out.println("Parking lot service");
        System.out.println("Has slot for vehicle type: "+vehicle.getType().toString()+" = "+hasSlot);
        return hasSlot;
    }

    public Ticket entry(Vehicle vehicle, String startTime) throws Exception {
        LocalTime time = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        Ticket ticket = parkingLotController.fill(vehicle.getType(), time);
        System.out.println("Parking lot service");
        System.out.println("Generated ticket : "+ticket.toString());
        return ticket;
    }

    public Bill exit(Ticket ticket, String endTime) throws Exception {
        boolean exited = parkingLotController.exit(ticket);
        if(exited == false){
            throw new Exception("cannot exit for ticket : "+ticket.toString());
        }
        LocalTime exitTime =  LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        Bill bill = paymentService.getBill(ticket, exitTime);
        System.out.println("Parking lot service");
        System.out.println("generated bill :"+bill.toString());
        return bill;
    }
}
