package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;

public class Constants {
    
    public static final class Elevator {
        //Height for each level - meters
        public static final double HIGH_HEIGHT = 2.286; //Max height is 96.5in so 90in for high ig
        public static final double MIDDLE_HEIGHT = 1.397; // Middle point between high and low
        public static final double LOW_HEIGHT = 0.508; //Min height is 12in so 20in for low ig
        
        //CAN IDs 
        public static final int LEFT_MOTOR_CANID = 5; 
        public static final int RIGHT_MOTOR_CANID = 6; 

        //PID Controller 
        public static final double ELEVATOR_P = 0.8; 
        public static final double ELEVATOR_I = 0.0; 
        public static final double ELEVATOR_D = 0.02; 

        //Speeds - Percentage of max speed
        public static final double IDLE_SPEED = 0; 
        public static final double UP_SPEED = 0.4; 
        public static final double DOWN_SPEED = -0.4; 

        //Idle Mode 
        public static final IdleMode IDLE_MODE = IdleMode.kBrake; 

        //Target vs Current Relation  
        public static final double TOLERANCE = 0.1;  

        //Gears - inches
        public static boolean HIGH_GEAR = false;
        public static final double BASE_RATIO = (12/85);
        public static final double LOW_RATIO = (50/34);
        public static final double HIGH_RATIO = (24/60);
        public static final double FINAL_RATIO = (24/74); 
        public static final double GEARING = (BASE_RATIO/(HIGH_GEAR ? HIGH_RATIO :LOW_RATIO));

        //Conversions
        public static final double INCHES_TO_METERS = 0.0254;
        public static final double SPOOL_CONVERSION = 2 *Math.PI;
    }
}
