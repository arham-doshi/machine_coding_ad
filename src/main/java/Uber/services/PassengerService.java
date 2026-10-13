package Uber.services;

import Uber.Dao.PassengerDao;
import Uber.Entities.Passenger;

public class PassengerService {
    PassengerDao passengerDao;

    public PassengerService(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }


    public Boolean createPassenger(Passenger passenger){
        Boolean isAlreadyPresent = passengerDao.checkIfPresent(passenger.getUserid());
        if(isAlreadyPresent){
            throw new IllegalArgumentException("user already present : "+passenger.getUserid());
        }
        passengerDao.createPassenger(passenger);

        return true;
    }

    public Passenger getPassenger(String userId){
        Passenger passenger = this.passengerDao.getPassenger(userId);
        return passenger;
    }
}
