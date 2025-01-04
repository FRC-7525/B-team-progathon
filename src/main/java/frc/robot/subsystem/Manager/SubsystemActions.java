package frc.robot.subsystem.Manager;

import java.util.EnumMap;
import java.util.Map;

import frc.robot.subsystem.Elevator.Elevator;
import frc.robot.subsystem.Elevator.ElevatorStates;
import frc.robot.subsystem.Wrist.Wrist;
import frc.robot.subsystem.Wrist.WristStates;
import frc.robot.subsystem.Claw.Claw;
import frc.robot.subsystem.Claw.ClawStates;

public class SubsystemActions {
    private static final Map<ManagerStates, Runnable> stateActions = new EnumMap<>(ManagerStates.class);
    private static Claw claw;
    private static Wrist wrist;
    private static Elevator elevator;

    static {
        stateActions.put(ManagerStates.IDLE, SubsystemActions::IDLE);
        stateActions.put(ManagerStates.HOLDING, SubsystemActions::HOLDING);
        stateActions.put(ManagerStates.SCORE_HIGH, SubsystemActions::SCORING_HIGH);
        stateActions.put(ManagerStates.SCORE_MIDDLE, SubsystemActions::SCORING_MIDDLE);
        stateActions.put(ManagerStates.INTAKE_LOW, SubsystemActions::INTAKING_LOW);
        stateActions.put(ManagerStates.OUTAKE, SubsystemActions::OUTAKING);
    }

    public static void setSubsystem(Claw claw, Wrist wrist, Elevator elevator) {
        SubsystemActions.claw = claw;
        SubsystemActions.wrist = wrist;
        SubsystemActions.elevator = elevator;
    }

    public static void execute(ManagerStates state) {
        Runnable action = stateActions.get(state);
        if (action != null) {
            action.run();
        } else {
            System.out.println("No action defined for state: " + state);
        }
    }

    private static void IDLE() {
        claw.setState(ClawStates.IDLE);
        wrist.setState(WristStates.FLAT);
        elevator.setState(ElevatorStates.MIDDLE);
    }

    private static void HOLDING() {
        claw.setState(ClawStates.HOLDING);
        wrist.setState(WristStates.FLAT);
    }

    private static void SCORING_HIGH() {
        elevator.setState(ElevatorStates.HIGH);
        if (elevator.nearSetPoint()) {
            wrist.setState(WristStates.HIGH);
            claw.setState(ClawStates.OUTPUTING);
        }
    }

    private static void SCORING_MIDDLE() {
        elevator.setState(ElevatorStates.MIDDLE);
        if (elevator.nearSetPoint()) {
            wrist.setState(WristStates.HIGH);
            claw.setState(ClawStates.OUTPUTING);
        }
    }

    private static void INTAKING_LOW() {
        elevator.setState(ElevatorStates.LOW);
        if (elevator.nearSetPoint()) {
            wrist.setState(WristStates.LOW);
            claw.setState(ClawStates.INTAKING);
        }
    }

    private static void OUTAKING() {
        wrist.setState(WristStates.FLAT);
        claw.setState(ClawStates.OUTPUTING);
    }
}

