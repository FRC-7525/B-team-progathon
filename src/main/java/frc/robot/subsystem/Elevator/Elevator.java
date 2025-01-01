package frc.robot.subsystem.Elevator;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class Elevator {
    
    private CANSparkMax leftMotor; 
    private CANSparkMax rightMotor; 

    private ProfiledPIDController controller;
    private RelativeEncoder encoder; 
    private ElevatorStates targetState;
    private boolean motorsZeroed; 
    
    public Elevator() {

        //Initialize NEOs 
        leftMotor = new CANSparkMax(Constants.Elevator.LEFT_MOTOR_CANID, MotorType.kBrushless);
        rightMotor = new CANSparkMax(Constants.Elevator.RIGHT_MOTOR_CANID, MotorType.kBrushless);

        //Initialize PID Controller
        controller = new ProfiledPIDController(Constants.Elevator.ELEVATOR_P, Constants.Elevator.ELEVATOR_I, Constants.Elevator.ELEVATOR_D, 
        new TrapezoidProfile.Constraints(Constants.Elevator.MAX_VELOCITY.magnitude(), Constants.Elevator.MAX_ACCELERATION.magnitude()));  
        controller.setTolerance(Constants.Elevator.DISTANCE_TOLERANCE.magnitude(),
        Constants.Elevator.VELOCITY_TOLERANCE.magnitude());

        //Motor Logistics
        leftMotor.follow(rightMotor);
        encoder = rightMotor.getEncoder();   
        encoder.setPositionConversionFactor(1);  
        encoder.setPosition(0); 
        rightMotor.setVoltage(0);
        motorsZeroed = false; 

        //Default to low state
        targetState = ElevatorStates.LOW;
    }

    //Sets the target state based on enum 
    public void setState (ElevatorStates state) {
        targetState = state; 
        //Reset controller each time target state changes 
        controller.setGoal(targetState.getTargetHeight());
    }

    //Gets the current height/position 
    private double getCurrentHeight() {
        double elevatorPosition = encoder.getPosition();   
        return ((elevatorPosition * Constants.Elevator.GEARING) * 
            Constants.Elevator.SPOOL_CONVERSION);
    }

    public void zero() {
        double rightZeroingSpeed = -Constants.Elevator.ZEROING_VELOCITY.magnitude();  
        if (rightMotor.getOutputCurrent() > Constants.Elevator.ZEROING_CURRENT_LIMIT.magnitude()) {
            rightZeroingSpeed = 0; 
            if (!motorsZeroed){
                encoder.setPosition(0); 
                motorsZeroed = true; 
            }
        }
        rightMotor.set(rightZeroingSpeed);
    }

    public void periodic () {
        if (targetState != null){
            double currentHeight = getCurrentHeight();   
            double targetHeight = targetState.getTargetHeight(); 
            double pidOutput = controller.calculate(currentHeight, targetHeight); 
            rightMotor.setVoltage(pidOutput); ///I feel like this is wrong but ehhh
            zero(); //I just need to call it, right? 
        }
    }
}
