package Uber.database.TableSchema;

import Uber.Entities.CabDetails;
import Uber.Entities.Enum.RiderStatus;

public class RiderTableSchema {
    public String riderId;
    public String name;
    public String email;
    public String number;
    public RiderStatus riderStatus;
    public CabDetails cabDetails;

    public RiderTableSchema(String riderId, String name, String email, String number, RiderStatus riderStatus, CabDetails cabDetails) {
        this.riderId = riderId;
        this.name = name;
        this.email = email;
        this.number = number;
        this.riderStatus = riderStatus;
        this.cabDetails = cabDetails;
    }

    public CabDetails getCabDetails() {
        return cabDetails;
    }

    public void setCabDetails(CabDetails cabDetails) {
        this.cabDetails = cabDetails;
    }

    public RiderStatus getRiderStatus() {
        return riderStatus;
    }

    public void setRiderStatus(RiderStatus riderStatus) {
        this.riderStatus = riderStatus;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }



}
