package parkinglot.entities;

import parkinglot.entities.enums.ParkingStatus;
import parkinglot.entities.enums.VehicleType;

import java.util.List;

public class ParkingSlot implements Comparable<ParkingSlot> {
    Integer slotId;
    List<VehicleType> allowedVehicles;
    ParkingStatus status;

    @Override
    public int compareTo(ParkingSlot other) {
        return this.slotId.compareTo(other.slotId);
    }

    static Integer slotIdCounter = 1;

    public ParkingSlot(List<VehicleType> allowedVehicles, ParkingStatus status) {
        this.allowedVehicles = allowedVehicles;
        this.status = status;
        this.slotId = slotIdCounter++;
    }
    public ParkingStatus getStatus() {
        return status;
    }
    public boolean checkIfCompatible(VehicleType vehicleType){
        for(VehicleType compatibleType : allowedVehicles){
            if(compatibleType.equals(vehicleType)) return true;
        }
        return false;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setStatus(ParkingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "slotId=" + slotId +
                ", allowedVehicles=" + allowedVehicles +
                ", status=" + status +
                '}';
    }
}
