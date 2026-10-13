package elevatorlld;

import elevatorlld.entity.Elevator;
import elevatorlld.entity.enums.FloorDirection;
import elevatorlld.strategy.AssignNearest;

import java.util.List;

public class ElevatorRunner extends Thread{
    public void run(){
        System.out.println("inside elevator runner");
        // elevator 1
        Elevator elevator1 = new Elevator(10, 1);
        Elevator elevator2 = new Elevator(10, 2);
        Elevator elevator3 = new Elevator(10, 3);
        ElevatorController elevatorController = new ElevatorController(new AssignNearest(), List.of(elevator1, elevator2, elevator3));
        ElevatorService elevatorService = new ElevatorService(elevatorController);

        elevatorService.requestElevator(1, FloorDirection.UP);
        elevatorService.tick();
        elevatorService.tick();
        elevatorService.tick();
        elevatorService.tick();
        elevatorService.pressFloor(1,3);
        elevatorService.tick();
        elevatorService.tick();
        elevatorService.tick();
        elevatorService.requestElevator(0, FloorDirection.UP);
        elevatorService.tick();
        elevatorService.pressFloor(2, 10);
        elevatorService.tick();
        elevatorService.tick();
        elevatorService.tick();
        elevatorService.tick();
    }
}
