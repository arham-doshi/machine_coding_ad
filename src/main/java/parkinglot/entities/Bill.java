package parkinglot.entities;

import parkinglot.entities.enums.VehicleType;

import java.time.LocalTime;

public class Bill {
    Integer billId;
    LocalTime startTime;
    LocalTime endTime;
    VehicleType vehicleType;
    Integer payment;
    static Integer billIdCounter = 0;

    public Bill(LocalTime startTime, LocalTime endTime, VehicleType vehicleType, Integer payment) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.vehicleType = vehicleType;
        this.payment = payment;
        this.billId = billIdCounter++;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", vehicleType=" + vehicleType +
                ", payment=" + payment +
                '}';
    }
}
