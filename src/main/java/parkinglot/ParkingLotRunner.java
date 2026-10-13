package parkinglot;

import parkinglot.controllers.FloorController;
import parkinglot.controllers.ParkingLotController;
import parkinglot.entities.Ticket;
import parkinglot.entities.Vehicle;
import parkinglot.entities.enums.VehicleType;
import parkinglot.services.ParkingLotService;
import parkinglot.services.PaymentService;
import parkinglot.services.Strategy.VehiclePerHourStrategy;

import java.util.List;

public class ParkingLotRunner extends Thread {
    public void run() {
        PaymentService paymentService = new PaymentService(new VehiclePerHourStrategy());
        // parking floor 1

        FloorController floorController = new FloorController();
        floorController.initialiseWithVehicleTypes(1, 1, 1, 1);

        FloorController floorController1 = new FloorController();
        floorController1.initialiseWithVehicleTypes(2, 2, 0, 2);

        ParkingLotController parkingLotController = new ParkingLotController(List.of(floorController, floorController1));

        ParkingLotService parkingLotService = new ParkingLotService(parkingLotController, paymentService);

        try {
            // truck comes
            Vehicle vehicle1 = new Vehicle(VehicleType.TRUCK);
            if(parkingLotService.isParkingAvailable(vehicle1)){
                    Ticket ticket1 = parkingLotService.entry(vehicle1, "07:00:00");
                    parkingLotService.exit(ticket1, "08:30:00");
            }

            // bike comes
            Vehicle vehicle2 = new Vehicle(VehicleType.BIKE);
            Ticket ticket2 = parkingLotService.entry(vehicle2, "08:00:00");

            // car comes
            Vehicle vehicle3 = new Vehicle(VehicleType.CAR);
            Ticket ticket3 = parkingLotService.entry(vehicle2, "09:00:00");

            parkingLotService.exit(ticket2, "10:00:00");
            parkingLotService.exit(ticket3, "11:00:00");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // bike comes



    }

}
