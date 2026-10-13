package parkinglot.controllers;

import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSlot;
import parkinglot.entities.enums.ParkingStatus;
import parkinglot.entities.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FloorController {
    ParkingFloor parkingFloor;



    public FloorController(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }
    public FloorController() {
    }

    public void initialiseWithVehicleTypes(Integer carCount, Integer bikeCount, Integer truckCount,  Integer floorNumber){
        this.parkingFloor = new ParkingFloor(new ArrayList<>(), floorNumber);
        for(int i = 0; i < carCount; i++){

            this.parkingFloor.addNewSlot(new ParkingSlot(new ArrayList<>(List.of(VehicleType.CAR)), ParkingStatus.EMPTY));
        }
        for(int i = 0; i < bikeCount; i++){
            this.parkingFloor.addNewSlot(new ParkingSlot(new ArrayList<>(List.of(VehicleType.BIKE)), ParkingStatus.EMPTY));

        }
        for(int i = 0; i < truckCount; i++){
            this.parkingFloor.addNewSlot(new ParkingSlot(new ArrayList<>(List.of(VehicleType.TRUCK)), ParkingStatus.EMPTY));
        }

    }
    public Integer getFloorID(){
        return this.parkingFloor.getFloorNumber();
    }

    public Boolean hasEmpty(VehicleType vehicleType){
        for(ParkingSlot parkingSlot : parkingFloor.getEmptySlot()){
            if(parkingSlot.checkIfCompatible(vehicleType))return true;
        }
        return false;
    }
    public Integer getEmpty(VehicleType vehicleType) {
        for(ParkingSlot parkingSlot : parkingFloor.getEmptySlot()){
            if(parkingSlot.checkIfCompatible(vehicleType))return parkingSlot.getSlotId();
        }
        return -1;
    }

    public Integer fillSpot(VehicleType vehicleType){
        for(ParkingSlot parkingSlot : parkingFloor.getEmptySlot()){
            if(parkingSlot.getStatus() == ParkingStatus.EMPTY && parkingSlot.checkIfCompatible(vehicleType)){
                parkingSlot.setStatus(ParkingStatus.FILLED);
                parkingFloor.getEmptySlot().remove(parkingSlot);
                return parkingSlot.getSlotId();
            }
        }
        return -1;
    }

    public boolean exit(Integer slotId){
        Map<Integer, ParkingSlot> slotsMap = parkingFloor.getSlotMap();
        ParkingSlot parkingSlot = slotsMap.get(slotId);
//        System.out.println("printing parking slot for exit : "+parkingSlot.toString());
        if(parkingSlot == null || parkingSlot.getStatus().equals(ParkingStatus.EMPTY)){
            return false;
        }
        parkingSlot.setStatus(ParkingStatus.EMPTY);
        parkingFloor.getEmptySlot().add(parkingSlot);
        return true;
    }



}
