package parkinglot.entities;

import parkinglot.entities.enums.VehicleType;

import java.time.LocalTime;

public class Ticket {
    private Integer ticketId;
    private Integer slotId;
    private VehicleType vehicleType;
    private LocalTime entryTime;
    private Integer floorNumber;
    static Integer ticketIdCounter = 1;

    public Ticket(Integer slotId, VehicleType vehicleType, LocalTime entryTime, Integer floorNumber) {
        this.slotId = slotId;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.floorNumber = floorNumber;
        this.ticketId = ticketIdCounter++;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", slotId=" + slotId +
                ", vehicleType=" + vehicleType +
                ", entryTime=" + entryTime +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
