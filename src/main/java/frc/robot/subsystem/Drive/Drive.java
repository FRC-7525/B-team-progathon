package frc.robot.subsystem.Drive;
import java.io.File;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;



public class Drive {
    
    private double maximumSpeed;
    private File swerveJsonDirectory;
    private SwerveDrive swerveDrive;
    private XboxController xboxController;

    private double forwardBackward;
    private double leftRight;
    private double rotation;
    
    public Drive() {
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;

        maximumSpeed = Units.feetToMeters(Constants.Drive.FEET_TO_METERS);
        swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
        swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed);
        xboxController = new XboxController(0);

    }

    public void periodic() {
        getControllerValues();

        // Defensive X-Formation if no inputs are detected
        if (forwardBackward == 0.0 && leftRight == 0.0 && rotation == 0.0) {
            swerveDrive.lockPose();
        } else {
            // Drive normally with input from the Xbox controller
            swerveDrive.drive (new Translation2d(forwardBackward * swerveDrive.getMaximumVelocity(),
                leftRight * swerveDrive.getMaximumVelocity()),
                rotation * swerveDrive.getMaximumAngularVelocity(),
                    false,
                    false);
        }
    }   

    private void getControllerValues() {
        forwardBackward = xboxController.getLeftY();
        leftRight = xboxController.getLeftX();
        rotation = xboxController.getRightX();

        // Deadband Correction
        forwardBackward = (Math.abs(forwardBackward) < 0.1) ? 0 : forwardBackward;
        leftRight = (Math.abs(leftRight) < 0.1) ? 0 : leftRight;
        rotation = (Math.abs(rotation) < 0.1) ? 0 : rotation;
    }

}


