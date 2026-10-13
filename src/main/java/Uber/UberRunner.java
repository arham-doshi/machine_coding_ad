package Uber;

import Uber.Dao.*;
import Uber.Entities.Enum.CabType;
import Uber.Entities.Loc;
import Uber.Entities.Passenger;
import Uber.controller.RiderController;
import Uber.controller.UserController;
import Uber.database.*;
import Uber.services.*;
import Uber.services.fareStategy.FareCalculationStrategy;
import Uber.services.fareStategy.NormalFareStrategy;
import Uber.services.fareStategy.SurchargeFareStrategy;
import Uber.services.rideStrategies.NearAndRatingStrategy;
import Uber.services.rideStrategies.NearestIdelRideStrategy;
import Uber.services.rideStrategies.RideMatchingStrategy;

public class UberRunner extends Thread {
    @Override
    public void run(){
        System.out.println("running over");
        // DB layer
        PassengerDBInmemory passengerDBInmemory = new PassengerDBInmemory();
        LocationDBInmemory locationDBInmemory = new LocationDBInmemory();
        RiderDBInmemory riderDBInmemory = new RiderDBInmemory();
        RatingsDbInmemory ratingsDbInmemory = new RatingsDbInmemory();
        RideDBInmemory rideDBInmemory = new RideDBInmemory();
        PaymentsDBInmemory paymentsDBInmemory = new PaymentsDBInmemory();

        // Dao layer
        PassengerDao passengerDao = new PassengerDaoInmemory(passengerDBInmemory);
        LocationDao locationDao = new LocationDaoInmemory(locationDBInmemory, riderDBInmemory);
        RiderDao riderDao = new RiderDaoInmemory(riderDBInmemory);
        RideDao rideDao = new RideDaoInmemory(rideDBInmemory);
        RatingsDao ratingsDao = new RatingsDaoInmemory(ratingsDbInmemory);
        PaymentsDao paymentsDao = new PaymentsDaoInmemory(paymentsDBInmemory);

        // Strategy
        RideMatchingStrategy nearestIdelRideStrategy = new NearestIdelRideStrategy(locationDao, riderDao, 10.0);
        RideMatchingStrategy nearAndRatingStrategy = new NearAndRatingStrategy(locationDao, riderDao, 10.0);
        FareCalculationStrategy normalFareStrategy = new NormalFareStrategy();
        FareCalculationStrategy surchargeFareStrategy = new SurchargeFareStrategy();

        // services
        PassengerService passengerService = new PassengerService(passengerDao);
        PaymentsService paymentsService = new PaymentsService(paymentsDao);
        RideService rideService = new RideService(nearestIdelRideStrategy, locationDao, riderDao, passengerDao, normalFareStrategy, rideDao);
        RiderService riderService = new RiderService(riderDao, locationDao);

        // Rating service / command
        RatingService ratingService = new RatingService(ratingsDao, rideDao);
        RiderRatingCommand riderRatingCommand = ratingService;
        UserRatingCommand userRatingCommand = ratingService;

        // controller
        UserController userController = new UserController(passengerService, rideService, paymentsService, userRatingCommand );
        RiderController riderController = new RiderController(riderService, paymentsService, riderRatingCommand , rideService);

        // happy path
        userController.registerUser("usr1", "zalak", "email@email", "123");
        riderController.registerRider("ryd1", "arham", "arh@email", "123", "gj01abc", CabType.AUTO);
        riderController.startWorking("ryd1", new Loc(0, 0));
        userController.getRide("usr1", CabType.AUTO, new Loc(0, 1), new Loc(0, 10));
        userController.payForRide(0);
        userController.completeRideWithoutPayment(0);
        userController.rateRide(0, 5, 4);
        riderController.rateRide(0, 5, 3);
        userController.getPassenger("usr1");
        riderController.getRider("ryd1");
        riderController.getEarning("ryd1");
        riderController.withDrawEarnings("ryd1");
        riderController.getEarning("ryd1");

        // unregistered user creating ride
        try{
            userController.getRide("usr2", CabType.SEDAN, new Loc(0, 2), new Loc(0, 10));
        }
        catch (Exception e){
            e.printStackTrace(System.out);
//            System.out.println( e.getMessage());
        }
        // multiple riders get nearest
        riderController.updateRiderLocation("ryd1", new Loc(0,1));
        riderController.registerRider("ryd2", "krutarth", "kru@gmail", "2321", "ka01abc", CabType.AUTO);
        riderController.startWorking("ryd2", new Loc(0, 10));
        userController.getRide("usr1", CabType.AUTO, new Loc(0, 9), new Loc(0,30));
        try{
            // rating ride before it's end.
            userController.rateRide(1, 1, 1);
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        riderController.updateRiderLocation("ryd2", new Loc(0,45));
        try{
            // completing ride without payment;
            userController.completeRideWithoutPayment(1);
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        userController.cancleRide(1);
        try{
            userController.payForRide(1);
        } catch (Exception e){
            e.printStackTrace(System.out);
        }
        // user books multiple rides
        userController.getRide("usr1", CabType.AUTO, new Loc(0, 10), new Loc(0,-10));
        userController.getRide("usr1", CabType.AUTO, new Loc(1, 11), new Loc(5,-5));
        try{
            userController.completeRideWithPayment(2);
            userController.completeRideWithPayment(2);
            // completing ride twice
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        try{
            // cancling completed ride
            userController.cancleRide(2);
        }
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        try{
            // cancling non existent ride
            userController.cancleRide(3);
        }
        catch (Exception e){
            e.printStackTrace(System.out);
        }
        // cancelling after payment
        riderController.updateRiderLocation("ryd2", new Loc(0,2));
        userController.getRide("usr1", CabType.AUTO, new Loc(0,3), new Loc(0,4));
        userController.payForRide(4);
        riderController.getEarning("ryd2");
        userController.cancleRide(4);
        riderController.getEarning("ryd2");
        // paying again 2nd time
        userController.getRide("usr1", CabType.AUTO, new Loc(0,3), new Loc(0,4));
        try{
            userController.payForRide(5);
            userController.payForRide(5);
        }catch (Exception e){
            e.printStackTrace(System.out);
        }

    }
}
