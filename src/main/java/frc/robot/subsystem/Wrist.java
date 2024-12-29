package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.Constants;



public class Wrist {

    enum WristStates {
        HIGH,
        LOW,
        FLAT;
    }

    private CANSparkMax pivotMotor;
    private WristStates state;
    private PIDController controller;
    private RelativeEncoder encoder;    

    public Wrist() {
        pivotMotor = new CANSparkMax(Constants.Wrist.PIVOT_MOTOR_ID, MotorType.kBrushless);
        encoder.setPositionConversionFactor(Constants.Wrist.CONVERSION_FACTOR); //setting conversion factor
        encoder.setPosition(0);
        controller = new PIDController(Constants.Wrist.WRIST_P, Constants.Wrist.WRIST_I, Constants.Wrist.WRIST_D); //not accurate
        state = WristStates.FLAT;
    }

    public void setstate(WristStates state) {
        this.state = state;
    }

    public void periodic() {
        switch (state) {
            case HIGH:
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), Constants.Wrist.HIGH)); //wrist high
                break; 
            
            case LOW:
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), Constants.Wrist.LOW)); //wrist low
                break;
    
            case FLAT:
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), Constants.Wrist.FLAT)); //wrist straight
                break;
        
        }
    }

}