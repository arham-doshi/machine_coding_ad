package parkinglot.entities;

import java.util.*;

public class ParkingFloor {
    List<ParkingSlot> slots;
    Set<ParkingSlot> emptySlot;
    Integer floorNumber;
    Map<Integer, ParkingSlot> slotMap;

    public ParkingFloor(List<ParkingSlot> slots, Integer floorNumber) {
        this.slots = slots;
        this.floorNumber = floorNumber;
        this.emptySlot = new TreeSet<>();
        for(ParkingSlot parkingSlot : slots){
            emptySlot.add(parkingSlot);
        }
        this.slotMap = new HashMap<>();
    }
    public void addNewSlot(ParkingSlot parkingSlot){
        slots.add(parkingSlot);
        emptySlot.add(parkingSlot);
        slotMap.put(parkingSlot.getSlotId(), parkingSlot);
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public Set<ParkingSlot> getEmptySlot() {
        return emptySlot;
    }

    public Map<Integer, ParkingSlot> getSlotMap() {
        return slotMap;
    }
}
