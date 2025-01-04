package frc.robot.subsystem.Wrist;

import frc.robot.Constants;

public enum WristStates {
        HIGH("HIGH", Constants.Wrist.HIGH.magnitude()),
        LOW("LOW", Constants.Wrist.LOW.magnitude()),
        FLAT("FLAT", Constants.Wrist.FLAT.magnitude());

        private String stateString;
        private double targetAngle;

        WristStates(String stateString, double targetAngle) {
            this.stateString = stateString;
            this.targetAngle = targetAngle;
        }

        public String getStateString() {
            return stateString;
        }
        

        public double getTargetAngle() {
            return targetAngle;
        }
    }

