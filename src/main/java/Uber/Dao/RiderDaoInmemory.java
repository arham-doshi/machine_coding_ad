package Uber.Dao;

import Uber.Entities.Enum.CabType;
import Uber.Entities.Enum.RiderStatus;
import Uber.Entities.Rider;
import Uber.database.RiderDBInmemory;
import Uber.database.TableSchema.RiderTableSchema;

public class RiderDaoInmemory implements RiderDao{
    RiderDBInmemory riderDBInmemory;

    public RiderDaoInmemory(RiderDBInmemory riderDBInmemory) {
        this.riderDBInmemory = riderDBInmemory;
    }
    public Boolean hasRider(String riderId){
        return this.riderDBInmemory.getRiderTable().get(riderId) != null;
    }
    public Rider createRider(Rider rider) {
        rider.setRiderStatus(RiderStatus.NOT_WORKING);
        RiderTableSchema riderTableSchema = new RiderTableSchema(rider.getUserid(), rider.getName(), rider.getEmail(), rider.getEmail(), rider.getRiderStatus(), rider.getCabDetails());
        String riderId = rider.getUserid();
        this.riderDBInmemory.getRiderTable().put(riderId, riderTableSchema);
        this.riderDBInmemory.getRatingSum().put(riderId, 0.0);
        this.riderDBInmemory.getTotalRatings().put(riderId, 0);
        this.riderDBInmemory.getTotalRides().put(riderId, 0);
        return rider;
    }

    public Rider getRider(String riderId){
        RiderTableSchema riderTableEntry = this.riderDBInmemory.getRiderTable().get(riderId);
        if(riderTableEntry == null){
            throw new IllegalArgumentException("No rider found to get");
        }
        Rider rider = getRiderFromRiderTable(riderTableEntry);
        Integer feedbackCount = this.riderDBInmemory.getTotalRatings().getOrDefault(riderId, 0);
        Double feedbackSum =  this.riderDBInmemory.getRatingSum().getOrDefault(riderId, 0.0);
        if(feedbackCount == 0){
            rider.setRating(0.0);
        }
        else{
            rider.setRating((1.0*feedbackSum)/feedbackCount);
        }
        rider.setRidesCompleted(this.riderDBInmemory.getTotalRides().getOrDefault(riderId, 0));
        return rider;
    }

    public void setRiderBusy(String riderId) {
        RiderTableSchema rider = this.riderDBInmemory.getRiderTable().get(riderId);
        if(rider == null){
            throw new IllegalArgumentException("Rider not found : "+riderId);
        }
        if(rider.getRiderStatus() == RiderStatus.TERMINATED){
            throw new IllegalStateException("Terminated rider cannot be set busy : " + riderId );
        }
        if(rider.getRiderStatus() == RiderStatus.NOT_WORKING) {
            throw new IllegalStateException("Not working rider cannot be set busy : " + riderId );
        }
        if(rider.getRiderStatus() == RiderStatus.RIDING) {
            System.out.println("Rider : "+riderId+" already on a ride");
        }
        else{
            System.out.println("Rider : "+riderId+" is now Riding");

            rider.setRiderStatus(RiderStatus.RIDING);
            this.riderDBInmemory.getRiderTable().put(riderId, rider);
        }
    }
    public void setRiderIdle(String riderId){
        RiderTableSchema rider = this.riderDBInmemory.getRiderTable().get(riderId);
        if(rider == null){
            throw new IllegalArgumentException("Rider not found : "+riderId);
        }
        if(rider.getRiderStatus() == RiderStatus.TERMINATED){
            throw new IllegalStateException("Terminated rider cannot be set Idle : " + riderId );
        }

        if(rider.getRiderStatus() == RiderStatus.IDLE) {
            System.out.println("Rider : "+riderId+" already on a Idle");
            return;
        }

        System.out.println("Rider : "+riderId+" is now Idle");

        rider.setRiderStatus(RiderStatus.IDLE);


    }
    public void setRiderTerminated(String riderId){
        RiderTableSchema rider = this.riderDBInmemory.getRiderTable().get(riderId);
        if(rider == null){
            throw new IllegalArgumentException("Rider not found : "+riderId);
        }
        if(rider.getRiderStatus() == RiderStatus.TERMINATED){
            throw new IllegalStateException("Terminated rider cannot be set Not working : " + riderId );
        }
        if(rider.getRiderStatus() == RiderStatus.RIDING){
            throw new IllegalStateException("Riding rider cannot be set Not working : " + riderId );
        }
        if(rider.getRiderStatus() == RiderStatus.NOT_WORKING){
            System.out.println("Rider : "+riderId+" already on a not working");
        }
        System.out.println("Rider : "+riderId+" is now not working ");
        rider.setRiderStatus(RiderStatus.TERMINATED);

    }
    public void setRiderNotWorking(String riderId){
        RiderTableSchema rider = this.riderDBInmemory.getRiderTable().get(riderId);
        if(rider == null){
            throw new IllegalArgumentException("Rider not found : "+riderId);
        }
        if(rider.getRiderStatus() == RiderStatus.TERMINATED){
            System.out.println("Rider : "+riderId+" already on a Terminated");
        }
        System.out.println("Rider : "+riderId+" is now Terminated");
        rider.setRiderStatus(RiderStatus.TERMINATED);
    }
    public void addRating(String riderId, Double rating){


        if(this.riderDBInmemory.getTotalRatings().get(riderId) == null){
            this.riderDBInmemory.getTotalRatings().put(riderId, 0);
            this.riderDBInmemory.getRatingSum().put(riderId, 0.0);
        }
        Integer allRating = this.riderDBInmemory.getTotalRatings().get(riderId);
        Double ratingSum = this.riderDBInmemory.getRatingSum().get(riderId);
        allRating+=1;
        ratingSum+=rating;
        this.riderDBInmemory.getTotalRatings().put(riderId, allRating);
        this.riderDBInmemory.getRatingSum().put(riderId, ratingSum);

    }

    public Rider getRiderFromRiderTable(RiderTableSchema riderTableSchema){
        Rider rider = new Rider(riderTableSchema.getRiderId(), riderTableSchema.getName(), riderTableSchema.getEmail(), riderTableSchema.getNumber(), riderTableSchema.getCabDetails());
        return rider;
    }
    public void completeRide(String riderId){
        Integer totalRides = this.riderDBInmemory.getTotalRides().get(riderId);

        if(totalRides == null){
            throw new IllegalArgumentException("Completing non existing ride");
        }
        this.riderDBInmemory.getTotalRides().put(riderId, totalRides + 1);
//        RiderTableSchema riderTableSchema = this.riderDBInmemory.getRiderTable().get(riderId);
//        riderTableSchema.setRiderStatus(RiderStatus.IDLE);
//        this.riderDBInmemory.getRiderTable().put(riderId, riderTableSchema);
    }
}
