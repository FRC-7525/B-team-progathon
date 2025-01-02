package frc.robot;
import static edu.wpi.first.units.Units.*;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;



public final class Constants {
    
    

    public static final class Wrist {
        public static final int CONVERSION_FACTOR = 360;
        public static final int PIVOT_MOTOR_ID = 1;
        public static final Measure<Angle> HIGH = Degrees.of(225);
        public static final  Measure<Angle> FLAT = Degrees.of(180);
        public static final Measure<Angle> LOW = Degrees.of(175);
        public static final double WRIST_P = 1;
        public static final double WRIST_I = 0;
        public static final double WRIST_D = 0;
    }
}
