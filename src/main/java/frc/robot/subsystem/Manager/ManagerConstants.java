package frc.robot.subsystem.Manager;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.XboxController;

public class ManagerConstants {
    static XboxController controller = new XboxController(0);


    public final static ArrayList<Trigger> IDLE_TRIGGERS = new ArrayList<>() {{
        add(new Trigger(ManagerStates.INTAKE_LOW, () -> controller.getAButtonPressed()));
        add(new Trigger(ManagerStates.OUTAKE, () -> controller.getRightBumperPressed()));
    }};

    public final static ArrayList<Trigger> HOLDING_TRIGGERS = new ArrayList<>() {{
        add(new Trigger(ManagerStates.IDLE, () -> controller.getBButtonPressed()));
        add(new Trigger(ManagerStates.SCORE_HIGH, () -> controller.getYButtonPressed()));
        add(new Trigger(ManagerStates.SCORE_MIDDLE, () -> controller.getXButtonPressed()));
        add(new Trigger(ManagerStates.OUTAKE, () -> controller.getRightBumperPressed()));
    }};

    public final static ArrayList<Trigger> SCORE_HIGH_TRIGGERS = new ArrayList<>() {{
        add(new Trigger(ManagerStates.IDLE, () -> controller.getBButtonPressed()));
    }};

    public final static ArrayList<Trigger> SCORE_MIDDLE_TRIGGERS = new ArrayList<>() {{
        add(new Trigger(ManagerStates.IDLE, ()-> controller.getBButtonPressed()));
    }};

    public final static ArrayList<Trigger> INTAKE_LOW_TRIGGERS = new ArrayList<>() {{
        add(new Trigger(ManagerStates.IDLE, () -> controller.getBButtonPressed()));
        add(new Trigger(ManagerStates.HOLDING, () -> controller.getAButtonPressed()));
        add(new Trigger(ManagerStates.OUTAKE, () -> controller.getRightBumperPressed()));
    }};

    public final static ArrayList<Trigger> OUTAKE_TRIGGERS = new ArrayList<>() {{
        add(new Trigger(ManagerStates.IDLE, () -> controller.getBButtonPressed()));
    }};


}
