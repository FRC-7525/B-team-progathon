package frc.robot;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.units.Current;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Velocity; 
import edu.wpi.first.units.Angle;

public class Constants {
    public static final class Wrist {
        public static final int CONVERSION_FACTOR = 360;
        public static final int PIVOT_MOTOR_ID = 1;
        public static final Measure<Angle> HIGH = Degrees.of(225);
        public static final  Measure<Angle> FLAT = Degrees.of(180);
        public static final Measure<Angle> LOW = Degrees.of(175);
        public static final double WRIST_P = 1;
        public static final double WRIST_I = 0;
        public static final double WRIST_D = 0;
      
    public static final class Elevator {
        //Height for each level - meters
        public static final Measure<Distance> HIGH_HEIGHT = Meters.of(2.286); //Max height is 96.5in so 90in for high ig
        public static final Measure<Distance> MIDDLE_HEIGHT = Meters.of(1.397); // Middle point between high and low
        public static final Measure<Distance> LOW_HEIGHT = Meters.of(0.508); //Min height is 12in so 20in for low ig
        
        //CAN IDs 
        public static final int LEFT_MOTOR_CANID = 5; 
        public static final int RIGHT_MOTOR_CANID = 6; 

        //PID Controller 
        public static final double ELEVATOR_P = 0.8; 
        public static final double ELEVATOR_I = 0.0;  
        public static final double ELEVATOR_D = 0.02; 
        public static final Measure<Velocity<Distance>> MAX_VELOCITY = MetersPerSecond.of(1); 
        public static final Measure<Velocity<Velocity<Distance>>> MAX_ACCELERATION = MetersPerSecondPerSecond.of(0.5);
        public static final Measure<Distance> DISTANCE_TOLERANCE = Meters.of(0.05); 
        public static final Measure<Velocity<Distance>> VELOCITY_TOLERANCE = MetersPerSecond.of(0.05);   
      
        //Gear
        public static final double BASE_RATIO = (12/85);
        public static final double LOW_RATIO = (50/34);
        public static final double FINAL_RATIO = (24/74);
        public static final double GEARING = BASE_RATIO/LOW_RATIO/FINAL_RATIO;

        //Spool
        public static final Measure<Distance> CIRCUMFERENCE_SPOOL =  Inches.of(2);
        public static final double SPOOL_CONVERSION = CIRCUMFERENCE_SPOOL.in(Meters) * Math.PI; 

        //Zeroing
        public static final double ZEROING_SPEED = (0.3); //random value 
        public static final Measure<Current> ZEROING_CURRENT_LIMIT = Amps.of(10);
    }
}
