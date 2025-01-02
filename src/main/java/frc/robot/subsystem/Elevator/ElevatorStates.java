package frc.robot.subsystem.Elevator;

import frc.robot.Constants;

enum ElevatorStates {
    HIGH("HIGH", Constants.Elevator.HIGH_HEIGHT.magnitude()), 
    MIDDLE("MIDDLE", Constants.Elevator.MIDDLE_HEIGHT.magnitude()), 
    LOW("LOW", Constants.Elevator.LOW_HEIGHT.magnitude()); 

    private double targetHeight;  
    private String stateString; 

    ElevatorStates(String stateString, double targetHeight) {  
        this.stateString = stateString; 
        this.targetHeight = targetHeight; 
    }

    public double getTargetHeight() {
        return targetHeight; 
    }

    public String getStateString(){
        return stateString; 
    }
}
