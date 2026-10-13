package Uber.Dao;

import Uber.Entities.Passenger;
import Uber.database.PassengerDBInmemory;
import Uber.database.TableSchema.PassengerTableSchema;

public class PassengerDaoInmemory implements  PassengerDao{
    PassengerDBInmemory passengerDBInmemory;

    public PassengerDaoInmemory(PassengerDBInmemory passengerDBInmemory) {
        this.passengerDBInmemory = passengerDBInmemory;
    }

    @Override
    public Boolean checkIfPresent(String userId) {
        return this.passengerDBInmemory.getUsers().containsKey(userId);
    }

    @Override
    public void completeRide(String userId) {
        Integer totalRides = this.passengerDBInmemory.getTotalRides().get(userId);
        if(totalRides == null){
            throw new IllegalArgumentException("Completing ride for non existent user");
        }
        this.passengerDBInmemory.getTotalRides().put(userId, totalRides + 1);
    }


    @Override
    public Boolean createPassenger(Passenger passenger) {
        PassengerTableSchema passengerTableSchema = new PassengerTableSchema(passenger.getUserid(), passenger.getName(), passenger.getEmail(), passenger.getNumber());
        String passengerId = passenger.getUserid();
        this.passengerDBInmemory.getUsers().put(passengerId, passengerTableSchema);
        this.passengerDBInmemory.getTotalRatings().put(passengerId, 0);
        this.passengerDBInmemory.getRatingSum().put(passengerId, 0.0);
        this.passengerDBInmemory.getTotalRides().put(passengerId, 0);
        return true;
    }

    @Override
    public Passenger getPassenger(String userId) {

        PassengerTableSchema passengerTableSchemaEntry = this.passengerDBInmemory.getUsers().get(userId);
        if(passengerTableSchemaEntry == null){
            throw new IllegalArgumentException("User not present in db : "+userId);
        }
        Passenger passenger = this.getPassengerFromInmemorySchema(passengerTableSchemaEntry);
        Integer feedbackCount = this.passengerDBInmemory.getTotalRatings().getOrDefault(userId, 0);
        Double feedbackSum =  this.passengerDBInmemory.getRatingSum().getOrDefault(userId, 0.0);
        if(feedbackCount == 0){
            passenger.setRating(0.0);
        }
        else{
            passenger.setRating((1.0*feedbackSum)/feedbackCount);
        }
        passenger.setRidesCompleted(this.passengerDBInmemory.getTotalRides().getOrDefault(userId, 0));

        return passenger;
    }

    private Passenger getPassengerFromInmemorySchema(PassengerTableSchema passengerTableSchema){
        return new Passenger(passengerTableSchema.getUserId(), passengerTableSchema.getName(), passengerTableSchema.getEmail(), passengerTableSchema.getNumber());
    }

    public void addRating(String userId, Double rating){



        Integer allRating = this.passengerDBInmemory.getTotalRatings().get(userId);
        Double ratingSum = this.passengerDBInmemory.getRatingSum().get(userId);
        allRating+=1;
        ratingSum+=rating;
        this.passengerDBInmemory.getTotalRatings().put(userId, allRating);
        this.passengerDBInmemory.getRatingSum().put(userId, ratingSum);

    }
}
