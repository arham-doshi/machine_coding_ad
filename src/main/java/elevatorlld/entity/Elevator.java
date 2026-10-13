package elevatorlld.entity;

import elevatorlld.entity.enums.DoorState;
import elevatorlld.entity.enums.ElevatorState;

import java.util.TreeSet;

public class Elevator {
    Integer topFloor;
    Integer elevatorId;
    DoorState doorState;
    ElevatorState elevatorState;
    TreeSet<Integer> floorsToVisit;

    public Integer getTopFloor() {
        return topFloor;
    }

    public void setTopFloor(Integer topFloor) {
        this.topFloor = topFloor;
    }

    public Integer getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(Integer elevatorId) {
        this.elevatorId = elevatorId;
    }

    public DoorState getDoorState() {
        return doorState;
    }

    public void setDoorState(DoorState doorState) {
        this.doorState = doorState;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public TreeSet<Integer> getFloorsToVisit() {
        return floorsToVisit;
    }

    public void setFloorsToVisit(TreeSet<Integer> floorsToVisit) {
        this.floorsToVisit = floorsToVisit;
    }

    public Integer getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }

    Integer currentFloor;

    public Elevator(Integer topFloor, Integer elevatorId) {
        this.topFloor = topFloor;
        this.elevatorId = elevatorId;
        this.doorState = DoorState.CLOSE;
        this.elevatorState = ElevatorState.IDLE;
        floorsToVisit = new TreeSet<>();
        this.currentFloor = 0;
    }

    public void addFloor(Integer floorNumber){
        if(floorNumber > topFloor || floorNumber < 0 ) {
            throw new RuntimeException("floor number cannot be > top floor");
        }

        floorsToVisit.add(floorNumber);
        System.out.println("floor added for : "+floorNumber+" to elevator : "+elevatorId);
    }

    public void processesTick() {
        if(floorsToVisit.contains(currentFloor)){
            floorsToVisit.remove(currentFloor);
            this.doorState = DoorState.OPEN;
            System.out.println("door state for elevator : "+ elevatorId + " is now "  + doorState );
            this.doorState = DoorState.CLOSE;
            System.out.println("door state for elevator : "+ elevatorId + " is now "  + doorState );
        }
        else{
            if(this.elevatorState.equals(ElevatorState.IDLE) && !this.floorsToVisit.isEmpty()){
                Integer lowestFloor = this.floorsToVisit.first();
                if(lowestFloor > currentFloor){
                    this.elevatorState = ElevatorState.UP;
                }
                else{
                    this.elevatorState = ElevatorState.DOWN;
                }
            }
            if(!this.elevatorState.equals(ElevatorState.IDLE) && this.floorsToVisit.isEmpty()){
                this.elevatorState = ElevatorState.IDLE;
            }
            if(!this.elevatorState.equals(ElevatorState.IDLE) && !this.floorsToVisit.isEmpty()) {
                if(elevatorState.equals(ElevatorState.UP)){
                    Integer maxFloor = this.floorsToVisit.last();
                    if(maxFloor > currentFloor){
                        currentFloor++;
                    }
                    else{
                        this.elevatorState = ElevatorState.DOWN;
                        currentFloor--;
                    }
                }
                else{
                    Integer minFloor = this.floorsToVisit.first();
                    if(minFloor < currentFloor){
                        currentFloor--;
                    }
                    else{
                        this.elevatorState = ElevatorState.UP;
                        currentFloor++;
                    }
                }

            }
            System.out.println("Elevator : "+this.elevatorId+" at floor : "+currentFloor);

        }
    }
}
