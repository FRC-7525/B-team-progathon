package frc.robot.subsystem.Elevator;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
    
    private CANSparkMax leftMotor; 
    private CANSparkMax rightMotor; 

    private ProfiledPIDController controller;
    private RelativeEncoder encoder; 
    private ElevatorStates state;
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
        motorsZeroed = false; 

        //Default to low state
        state = ElevatorStates.LOW;
    }

    //Sets the target state based on enum 
    public void setState (ElevatorStates state) {
        this.state = state; 
        //Reset controller each time target state changes 
        controller.setGoal(state.getTargetHeight());
    }

    //Gets the current height/position 
    private double getCurrentHeight() {
        double elevatorPosition = encoder.getPosition();   
        return ((elevatorPosition * Constants.Elevator.GEARING) * 
            Constants.Elevator.SPOOL_CONVERSION);
    }

    //Checks if near setpoint using tolerance 
    public boolean nearSetPoint () {
        return controller.atSetpoint(); 
    }

    public void zero() {
        double zeroingSpeed = -Constants.Elevator.ZEROING_SPEED;  
        if (rightMotor.getOutputCurrent() > Constants.Elevator.ZEROING_CURRENT_LIMIT.magnitude()) {
            zeroingSpeed = 0; 
            if (!motorsZeroed){
                encoder.setPosition(0); 
                motorsZeroed = true; 
            }
        }
        rightMotor.set(zeroingSpeed);
    }

    public void periodic () { 
        SmartDashboard.putString("State String", state.getStateString());
        double pidOutput = controller.calculate(getCurrentHeight(), state.getTargetHeight()); 
        if (!motorsZeroed) {
            zero(); 
        } else {
            rightMotor.setVoltage(pidOutput); 
        } 
    }
}
