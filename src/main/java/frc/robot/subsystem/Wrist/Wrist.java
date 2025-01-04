package frc.robot.subsystem.Wrist;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Wrist {

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

    public void setState(WristStates state) {
        this.state = state;
    }

    public void periodic() {
        SmartDashboard.putString("Wrist State", state.getStateString());
        pivotMotor.set(controller.calculate(pivotMotor.getEncoder().getPosition(), state.getTargetAngle()));
    }

}