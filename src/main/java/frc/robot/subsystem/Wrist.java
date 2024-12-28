package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;

public class Wrist {

    enum WristStates {
        HIGH,
        LOW,
        FLAT;
    }

    private CANSparkMax pivotMotor;
    private WristStates state;
    private PIDController controller;

    public Wrist() {
        state = WristStates.FLAT;
        pivotMotor = new CANSparkMax(1, MotorType.kBrushless);

        pivotMotor.getEncoder().setPosition(0);

        controller = new PIDController(0, 0, 0);
    }

    public void setstate(WristStates state) {
        this.state = state;
    }

    public void periodic() {
        switch (state) {
            case HIGH:
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), 225));
                break;
            
            case LOW:
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), 135));
                break;
    
            case FLAT:
                pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), 180));
    
                break;
        
        }
    }

}