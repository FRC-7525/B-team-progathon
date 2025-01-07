package frc.robot.subsystem.Claw;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
// Pneumatics HUB and Controller stuff??


public class Claw {
    // Solenoids for claw open and close
    private Solenoid solenoidLeft;
    private Solenoid solenoidRight;
    
    // NEO 550 motors for wheel spinning
    private CANSparkMax motorLeft;
    private CANSparkMax motorRight;

    private ClawStates state;

    
    public Claw() {
        // Initialize solenoids
        solenoidLeft = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.Claw.LEFT_PNEUMATICS_ID); // NOT ACCURATE
        solenoidRight = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.Claw.RIGHT_PNEUMATICS_ID); // NOT ACCURATE
        
        // Initialize NEO 550 motors
        motorLeft = new CANSparkMax(Constants.Claw.LEFT_MOTOR_ID, MotorType.kBrushless); // NOT ACCURATE
        motorRight = new CANSparkMax(Constants.Claw.RIGHT_MOTOR_ID, MotorType.kBrushless); // NOT ACCURATE
        motorRight.follow(motorLeft);
        motorRight.setInverted(true);
        // Initialize state
        state = ClawStates.IDLE;
    }

    
    public void periodic() {
        solenoidLeft.set(state.getClawClosed());
        solenoidRight.set(state.getClawClosed());

        motorLeft.set(state.getMotorSpeed());

        logState();
    }

    public void setState(ClawStates state) {
        this.state = state;
    }

    private void logState() {
        SmartDashboard.putString("Claw State", state.getState());
    }
}
