package Uber.Entities;

import Uber.Entities.Enum.CabType;

public class CabDetails {
    String numberplate;
    CabType cabType;

    @Override
    public String toString() {
        return "CabDetails{" +
                "numberplate='" + numberplate + '\'' +
                ", cabType=" + cabType +
                '}';
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

    public CabType getCabType() {
        return cabType;
    }

    public void setCabType(CabType cabType) {
        this.cabType = cabType;
    }

    public CabDetails(String numberplate, CabType cabType) {
        this.numberplate = numberplate;
        this.cabType = cabType;
    }
}
