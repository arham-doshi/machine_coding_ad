package elevatorlld;

import elevatorlld.entity.Elevator;
import elevatorlld.entity.enums.FloorDirection;
import elevatorlld.strategy.AssignStrategy;

import java.util.List;

public class ElevatorController {
    AssignStrategy assignStrategy;
    List<Elevator> elevatorList;

    public ElevatorController(AssignStrategy assignStrategy, List<Elevator> elevatorList) {
        this.assignStrategy = assignStrategy;
        this.elevatorList = elevatorList;
    }

    public Integer requestElevator(Integer floorNumber, FloorDirection floorDirection){
        Integer elevatorNumber = this.assignStrategy.findElevator(elevatorList, floorNumber, floorDirection);
//        System.out.println("printing elevator number");
        for(Elevator elevator : elevatorList){
            if(elevator.getElevatorId().equals(elevatorNumber)){

                elevator.addFloor(floorNumber);
                return elevatorNumber;
            }
        }
        return -1;
    }
    public Integer pressFloor(Integer elevatorId, Integer floorId){
        for(Elevator elevator : elevatorList){
            if(elevator.getElevatorId().equals(elevatorId)){
                elevator.addFloor(floorId);
                return elevatorId;
            }
        }
        return -1;
    }

    public void tick(){
        for(Elevator elevator : elevatorList){
            elevator.processesTick();
        }
    }

}
