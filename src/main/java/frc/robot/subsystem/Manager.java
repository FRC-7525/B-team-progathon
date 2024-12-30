package frc.robot.subsystem;

import edu.wpi.first.wpilibj.XboxController;

// PLEASE EXCUSE BAD CODE, THIS WAS WRITTEN IN THE MIDDLE OF THE NIGHT

enum ManagerStates {
    HOLDING,
    IDLE,
    SCORE_HIGH,
    SCORE_MIDDLE,
    INTAKE_LOW,
    OUTAKE
}

public class Manager {
    private XboxController controller;
    // The following should be uncommented once other subsystems are built:
    /*
     * private Claw claw;
     * private Wrist wrist;
     * private Elevator elevator;
     */
    ManagerStates state;
    

    public Manager() {
        controller = new XboxController(0);
        // The following should be uncommented once other subsystems are built:
        /*
         * claw = new Claw();
         * wrist = new Wrist();
         * elevator = new Elevator();
         */
        state = ManagerStates.IDLE;
    }

    public void periodic() {
        handleTriggers();
        setSubsystemStates();
        // The following should be uncommented once the other subsystems are built:
        /*
         * claw.periodic();
         * wirst.periodic();
         * elevator.periodic();
         */
    }

    private void handleTriggers() {
        switch (state) {
            case IDLE:
            // IDLE goes to intake or outake (if something is stuck)
                if (controller.getAButtonPressed()) {
                    state = ManagerStates.INTAKE_LOW;
                }
                else if (controller.getRightBumperPressed()) {
                    System.out.println("ATTEMPTING TO GET RID OF STUCK OBJECT");
                    state = ManagerStates.OUTAKE;
                }
                break;
            case SCORE_HIGH:
                if (controller.getBButtonPressed()) {
                    state = ManagerStates.IDLE;
                }
                break;
            case SCORE_MIDDLE:
                if (controller.getBButtonPressed()) {
                    state = ManagerStates.IDLE;
                }
                break;
            case INTAKE_LOW:
                if (controller.getAButtonPressed()) {
                    state = ManagerStates.HOLDING;
                } 
                else if (controller.getBButtonPressed()) {
                    state = ManagerStates.IDLE;
                } else if (controller.getRightBumperPressed()) {
                    System.out.println("ATTEMPTING TO GET RID OF STUCK OBJECT");
                    state = ManagerStates.OUTAKE;
                }
                break;
            case OUTAKE:
                if (controller.getBButtonPressed()) {
                    state = ManagerStates.IDLE;
                }
                break;
            case HOLDING:
                if (controller.getBButtonPressed()) {
                    state = ManagerStates.IDLE;
                }
                else if (controller.getYButtonPressed()) {
                    state = ManagerStates.SCORE_HIGH;
                }
                else if (controller.getXButtonPressed()) {
                    state = ManagerStates.SCORE_MIDDLE;
                }
                else if (controller.getRightBumperPressed()) {
                    System.out.println("ATTEMPTING TO GET RID OF STUCK OBJECT");
                    state = ManagerStates.OUTAKE;
                }
            default:
                break;
        }
    }

    private void setSubsystemStates() {
        switch (state) {
            case IDLE:
                /*
                 * claw.setState(ClawStates.IDLE);
                 * wrist.setState(WristStates.FLAT);
                 * elevator.setTargetState(ElevatorStates.MIDDLE);
                 */
                break;
            case SCORE_HIGH:
                /*
                * elevator.setTargetState(ElevatorStates.HIGH);
                * wrist.setState(WristStates.HIGH);
                * claw.setState(ClawStates.SCORING);
                */
                break;
            case SCORE_MIDDLE:
                /*
                 * elevator.setTargetState(ElevatorStates.MIDDLE);
                 * wrist.setState(WristStates.HIGH);
                 * claw.setState(ClawStates.SCORING);
                 */
                break;
            case INTAKE_LOW:
                /*
                 * elevator.setTargetState(ElevatorStates.LOW);
                 * wrist.setState(WristStates.LOW);
                 * claw.setState(ClawStates.INTAKE);
                 */
                break;
            case OUTAKE:
                /*
                 * DO NOT SET ELEVATOR
                 * wrist.setState(WristStates.FLAT);
                 * claw.setState(ClawStates.OUTAKE);
                 */
                break;
            case HOLDING:
                /*
                 * claw.setState(ClawStates.HOLDING);
                 * wrist.setState(WristStates.FLAT);
                 * 
                 */
            default:
                break;
        }
    }



}
