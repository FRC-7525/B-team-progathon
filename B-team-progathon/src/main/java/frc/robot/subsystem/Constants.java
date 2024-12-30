package frc.robot.subsystem;



public class Constants {
    public static final class Claw {
        public static final int LEFT_PNEUMATICS_ID = 0;
        public static final int RIGHT_PNEUMATICS_ID = 1;

        public static final int LEFT_MOTOR_ID = 2;
        public static final int RIGHT_MOTOR_ID = 3;

        // Speeds are not in units, percentage of max power
        public static final double IDLE_SPEED = 0;
        public static final double OUTPUTTING_SPEED = 0.5;
        public static final double INTAKING_SPEED = -0.5;
        public static final double HOLDING_SPEED = 0;

    }
}
