package frc.robot.subsystem.Elevator;

import frc.robot.Constants;

enum ElevatorStates {
    HIGH(Constants.Elevator.HIGH_HEIGHT.magnitude()), 
    MIDDLE(Constants.Elevator.MIDDLE_HEIGHT.magnitude()), 
    LOW(Constants.Elevator.LOW_HEIGHT.magnitude()); 

    private double targetHeight;  

    ElevatorStates(double targetHeight) {
        this.targetHeight = targetHeight;  
    }

    public double getTargetHeight() {
        return targetHeight; 
    }
}
