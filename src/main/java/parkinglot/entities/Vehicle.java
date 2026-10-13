package parkinglot.entities;

import parkinglot.entities.enums.VehicleType;

public class Vehicle {
    VehicleType type;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}
