package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ProfiledPIDController;
import static frc.robot.Constants.Elevator.*;

enum ElevatorStates {
        HIGH(HIGH_HEIGHT), 
        MIDDLE(MIDDLE_HEIGHT), 
        LOW(LOW_HEIGHT); 

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
        leftMotor = new CANSparkMax(LEFT_MOTOR_CANID, MotorType.kBrushless);
        rightMotor = new CANSparkMax(RIGHT_MOTOR_CANID, MotorType.kBrushless);

        //Initialize PID Controller
        controller = new ProfiledPIDController(ELEVATOR_P, ELEVATOR_I, ELEVATOR_D, null); 
        controller.setTolerance(TOLERANCE);

        //Motor Logistics
        encoder = rightMotor.getEncoder(); 
        encoder.setPositionConversionFactor(1); //Okay?? 
        leftMotor.follow(rightMotor); 

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
    public double getCurrentHeight() {
        return encoder.getPosition(); 
    }

    //Motors need to be stopped once it reaches goal of Middle state??
    public void stopMotors() { 
        rightMotor.set(IDLE_SPEED); 
    }

    public void periodic () {
        if (targetState != null){
            double currentHeight = getCurrentHeight(); 
            //Calculates change I think???
            double pidOutput = controller.calculate(currentHeight); 

            //Stops motors once it's close enough to tolerance 
            if (controller.atSetpoint()) {
            rightMotor.set(IDLE_SPEED);
            } else {
                if (pidOutput > 0) {
                    rightMotor.set(UP_SPEED);
                } else {
                    rightMotor.set(DOWN_SPEED);
                }
            }   
        }
    }
}
