package frc.robot.subsystem.Claw;

import frc.robot.subsystem.Constants.Claw;

enum ClawStates {
    IDLE("IDLE", 0, false),
    OUTPUTING("OUTPUTING", Claw.OUTPUTTING_SPEED, false),
    INTAKING("INTAKING", Claw.INTAKING_SPEED, false),
    HOLDING("HOLDING", 0, true);

    private String stateString;
    private double speed;
    private boolean clawClosed;

    ClawStates(String stateString, double speed, boolean clawClosed) {
        this.stateString = stateString;
        this.speed = speed;
        this.clawClosed = clawClosed;
    }

    public String getState() {
        return stateString;
    }

    public double getMotorSpeed() {
        return speed;
    }

    public boolean getClawClosed() {
        return clawClosed;
    }
}
