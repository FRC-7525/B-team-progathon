package frc.robot.subsystem;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
// Pneumatics HUB and Controller stuff??

enum ClawStates {
    IDLE,
    OUTPUTING,
    INTAKING,
    HOLDING
}

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
        switch (state) {
            case IDLE:
                solenoidLeft.set(false); // IDK how these work?????
                solenoidRight.set(false); // claw open

                motorLeft.set(0);
            break;
            case OUTPUTING:
                solenoidLeft.set(false); // claw open
                solenoidRight.set(false);
            
                motorLeft.set(Constants.Claw.OUTPUTTING_SPEED);
            break;
            case INTAKING:
                solenoidLeft.set(false); // claw open
                solenoidRight.set(false);

                motorLeft.set(Constants.Claw.INTAKING_SPEED);
            break;
            case HOLDING:
                solenoidLeft.set(true); // claw closed
                solenoidRight.set(true);

                motorLeft.set(0);
        }
    }

    public void setState(ClawStates state) {
        this.state = state;
    }
}
