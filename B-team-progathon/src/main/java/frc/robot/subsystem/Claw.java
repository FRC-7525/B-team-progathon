package frc.robot.subsystem;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

// I WILL REPLACE MAGIC NUMBERS WITH CONSTANTS FILE LATER
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
        solenoidLeft = new Solenoid(PneumaticsModuleType.CTREPCM, 0); // NOT ACCURATE
        solenoidRight = new Solenoid(PneumaticsModuleType.CTREPCM, 1); // NOT ACCURATE
        
        // Initialize NEO 550 motors
        motorLeft = new CANSparkMax(2, MotorType.kBrushless); // NOT ACCURATE
        motorLeft.follow(motorRight);
        motorRight.setInverted(true);

        // Initialize state
        state = ClawStates.IDLE;
    }

    
    public void periodic() {
        switch (state) {
            case IDLE:
                solenoidLeft.set(false); // IDK how these work?????
                solenoidRight.set(false); // claw open

                motorRight.set(0);
                motorLeft.set(0);
            break;
            case OUTPUTING:
                solenoidLeft.set(false); // claw open
                solenoidRight.set(false);
                
                motorRight.set(0.5);
                motorLeft.set(0.5);
            break;
            case INTAKING:
                solenoidLeft.set(false); // claw open
                solenoidRight.set(false);

                motorRight.set(-0.5);
                motorLeft.set(-0.5);
            break;
            case HOLDING:
                solenoidLeft.set(true);
                solenoidRight.set(false);

                motorRight.set(0);
                motorLeft.set(0);
        }
    }

    public void setState(ClawStates state) {
        this.state = state;
    }
}
