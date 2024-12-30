package frc.robot.subsystems;

import frc.robot.Constants;

import static frc.robot.Constants.Elevator.IDLE_MODE;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.ProfiledPIDController;

enum ElevatorStates {
    HIGH(Constants.Elevator.HIGH_HEIGHT), 
    MIDDLE(Constants.Elevator.MIDDLE_HEIGHT), 
    LOW(Constants.Elevator.LOW_HEIGHT); 

    private double targetHeight; 

    ElevatorStates(double targetHeight) {
        this.targetHeight = targetHeight; 
    }

    public double getTargetHeight() {
        return targetHeight; 
    }
}

public class Elevator {
    private CANSparkMax leftMotor; 
    private CANSparkMax rightMotor; 

    private ProfiledPIDController controller; 
    private ElevatorStates targetState;
    private RelativeEncoder encoder; 
    
    public Elevator() {

        //Initialize NEOs 
        leftMotor = new CANSparkMax(Constants.Elevator.LEFT_MOTOR_CANID, MotorType.kBrushless);
        rightMotor = new CANSparkMax(Constants.Elevator.RIGHT_MOTOR_CANID, MotorType.kBrushless);

        //Initialize PID Controller
        controller = new ProfiledPIDController(Constants.Elevator.ELEVATOR_P, 
        Constants.Elevator.ELEVATOR_I, Constants.Elevator.ELEVATOR_D, null);  
        controller.setTolerance(Constants.Elevator.TOLERANCE);

        //Motor Logistics
        encoder = rightMotor.getEncoder(); 
        encoder.setPositionConversionFactor(1); //Okay?
        leftMotor.follow(rightMotor); 
        rightMotor.setIdleMode(IDLE_MODE); 

        //Default to middle state
        targetState = ElevatorStates.MIDDLE;
    }

    //Sets the target state based on enum 
    public void setTargetState (ElevatorStates state) {
        targetState = state; 
        //Reset controller each time target state changes 
        controller.setGoal(targetState.getTargetHeight());
    }

    //Gets the current height I think, possibly 
    private double getCurrentHeight() {
        return encoder.getPosition(); 
    }

    //Stops Motors (only need right because left follows)
    private void stopMotors() { 
        rightMotor.setVoltage(Constants.Elevator.IDLE_SPEED); 
        rightMotor.setIdleMode(IDLE_MODE); 
    }

    public void periodic () {
        if (targetState != null){
            double currentHeight = getCurrentHeight();  
            double targetHeight = targetState.getTargetHeight(); 
            //Calculates change I think???
            double pidOutput = controller.calculate(currentHeight, targetHeight); 

            //Stops motors once it's close enough to tolerance 
            if (controller.atSetpoint()) {
            stopMotors();
            } else {
                if (pidOutput > 0) {
                    rightMotor.setVoltage(Constants.Elevator.UP_SPEED);
                } else {
                    rightMotor.setVoltage(Constants.Elevator.DOWN_SPEED);
                }
            }   
        }
    }
}
