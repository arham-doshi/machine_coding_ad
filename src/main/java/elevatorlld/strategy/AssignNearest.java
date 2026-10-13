package elevatorlld.strategy;

import elevatorlld.entity.Elevator;
import elevatorlld.entity.enums.ElevatorState;
import elevatorlld.entity.enums.FloorDirection;

import java.util.List;

public class AssignNearest implements AssignStrategy {
    public Integer findElevator(List<Elevator> elevatorList, Integer floor, FloorDirection floorDirection) {
        Integer dis = -1;
        Integer elevatorId = 0;
        for(Elevator elevator : elevatorList){
            Integer currentDistance = Math.abs(floor - elevator.getCurrentFloor());
            if(dis == -1 || dis > currentDistance){
                dis = currentDistance;
                elevatorId = elevator.getElevatorId();
            }
        }
        System.out.println("in find neareaste : " + elevatorId + " and dist : "+dis);
        return elevatorId;

    }
}
