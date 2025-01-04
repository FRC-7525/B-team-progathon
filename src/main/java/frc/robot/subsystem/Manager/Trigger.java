package frc.robot.subsystem.Manager;

import java.util.function.BooleanSupplier;

public class Trigger {
    private final BooleanSupplier trigger;
    private final ManagerStates targetState;

    public Trigger(ManagerStates targetState, BooleanSupplier trigger) {
        this.targetState = targetState;
        this.trigger = trigger;
    }

    public boolean isTriggered() {
        return trigger.getAsBoolean();
    }

    public ManagerStates getTargetState() {
        return targetState;
    }

} 
