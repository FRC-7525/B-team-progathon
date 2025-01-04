package frc.robot.subsystem.Manager;

import java.util.ArrayList;
import java.util.Collection;

public enum ManagerStates {
    HOLDING("HOLDING"),
    IDLE("IDLE"),
    SCORE_HIGH("SCORING HIGH"),
    SCORE_MIDDLE("SCORING MIDDLE"),
    INTAKE_LOW("INTAKING LOW"),
    OUTAKE("OUTAKING");

    private String stateString;
    private ArrayList<Trigger> triggers;

    ManagerStates(String stateString) {
        this.stateString = stateString;
        this.triggers = new ArrayList<>();
    }

    public void setTriggers(ManagerStates state, Collection <? extends Trigger> c) {
        state.triggers.addAll(c);
    }

    public String getStateString() {
        return stateString;
    }

    public ArrayList<Trigger> getTriggers() {
        return this.triggers;
    }
    
}
