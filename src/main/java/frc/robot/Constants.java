package frc.robot;

public class Constants {
    //TODO: Basically fix all these values bc I think they're all wrong/random :)
    public static final class Elevator {
        //Height for each level 
        public static final double HIGH_HEIGHT = 3;  
        public static final double MIDDLE_HEIGHT = 2; 
        public static final double LOW_HEIGHT = 1; 
        
        //CAN IDs 
        public static final int LEFT_MOTOR_CANID = 5; 
        public static final int RIGHT_MOTOR_CANID = 6; 

        //PID Controller 
        public static final double ELEVATOR_P = 0.8; 
        public static final double ELEVATOR_I = 0.0; 
        public static final double ELEVATOR_D = 0.02; 

        //Speeds 
        public static final double IDLE_SPEED = 0; 
        public static final double UP_SPEED = 0.4; 
        public static final double DOWN_SPEED = -0.4; 

        //Target vs Current Relation  
        public static final double TOLERANCE = 1; 
    }
}
