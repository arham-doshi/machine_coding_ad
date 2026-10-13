package elevatorlld.strategy;

import elevatorlld.entity.Elevator;
import elevatorlld.entity.enums.FloorDirection;

import java.util.List;

public interface AssignStrategy {
    public Integer findElevator(List<Elevator> elevatorList, Integer floor, FloorDirection floorDirection);
}
