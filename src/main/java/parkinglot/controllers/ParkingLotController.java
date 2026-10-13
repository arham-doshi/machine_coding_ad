package parkinglot.controllers;

import parkinglot.entities.Ticket;
import parkinglot.entities.enums.VehicleType;

import java.time.LocalTime;
import java.util.List;

public class ParkingLotController {
    List<FloorController> floorControllers;

    public ParkingLotController(List<FloorController> floorControllers) {
        this.floorControllers = floorControllers;
    }
    public Boolean hasEmpty(VehicleType vehicleType){
        for(FloorController floorController: floorControllers){
            if(floorController.hasEmpty(vehicleType)){
                return true;
            }
        }
        return false;
    }

    public Ticket fill(VehicleType vehicleType, LocalTime entryTime) throws Exception {
        for(FloorController floorController : floorControllers ){
            if(floorController.hasEmpty(vehicleType)){
                Integer spotId = floorController.fillSpot(vehicleType);
                if(spotId == -1){
                    continue;
                }
                Ticket ticket = new Ticket(spotId, vehicleType, entryTime, floorController.getFloorID());
                return ticket;
            }
        }
        throw new Exception("Cannot Fill Spot as No empty");
    }

    public boolean exit(Ticket ticket){
        System.out.println("exitign with floor : "+ticket.getFloorNumber()+" & slot : "+ticket.getSlotId());
        for(FloorController floorController : floorControllers){
//            System.out.println("current floor : "+floorController.getFloorID().toString());
            if(floorController.getFloorID().equals(ticket.getFloorNumber())){
                return floorController.exit(ticket.getSlotId());
            }
        }
        return false;
    }
}
