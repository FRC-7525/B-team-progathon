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

        maximumSpeed = Units.feetToMeters(Constants.Drive.MAX_SPEED_IN_FEET);
        swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
        swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed);
        xboxController = new XboxController(0);

    }

    public void periodic() {
        forwardBackward = xboxController.getLeftY();
        leftRight = xboxController.getLeftX();
        rotation = xboxController.getRightX();

        // Defensive X-Formation if no inputs are detected
        if (xboxController.getAButtonPressed()) {
            swerveDrive.lockPose();
        } else {
            // Drive normally with input from the Xbox controller
            swerveDrive.drive (new Translation2d(forwardBackward * swerveDrive.getMaximumVelocity(),
                leftRight * swerveDrive.getMaximumVelocity()),
                rotation * swerveDrive.getMaximumAngularVelocity(),
                    true,
                    false);
        }
    }   


}


