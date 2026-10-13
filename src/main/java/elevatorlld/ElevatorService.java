package elevatorlld;

import elevatorlld.entity.Elevator;
import elevatorlld.entity.enums.FloorDirection;

public class ElevatorService {
    ElevatorController elevatorController;

    public ElevatorService(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    public void requestElevator(Integer floorNumber, FloorDirection floorDirection){
        System.out.println("Finding nearest elevator for you at floor "+floorNumber );
        Integer elevatorId =  elevatorController.requestElevator(floorNumber, floorDirection);
        if(elevatorId >= 0){
            System.out.println("Elevator number : "+elevatorId+" is coming to floor : "+floorNumber);

        }
    }
    public void pressFloor(Integer elevatorId, Integer floorId){

        Integer elevator = elevatorController.pressFloor(elevatorId, floorId);
        if(elevator >= 0){
            System.out.println("Pressing floor = "+floorId+" from elevator : "+elevator+" success");
        }
        else{
            System.out.println("error while pressing floor : "+floorId+" from elevator : "+elevatorId);
            throw new RuntimeException("Error pressing floor");
        }
    }

    public void tick(){
        elevatorController.tick();
    }
}
