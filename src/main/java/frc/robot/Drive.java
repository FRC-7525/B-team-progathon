package frc.robot;
import java.io.File;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.util.Units;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;



public class Drive {
    
    private double maximumSpeed;
    private File swerveJsonDirectory;
    private SwerveDrive swerveDrive;
    private XboxController xboxController;
    
    public Drive() {
        maximumSpeed = Units.feetToMeters(Constants.Drive.FEET_TO_METERS);
        swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
        swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed);
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        xboxController = new XboxController(0);

    }

    public void periodic() {
        swerveDrive.drive (new Translation2d(xboxController.getLeftX() * swerveDrive.getMaximumVelocity(),
            xboxController.getLeftY() * swerveDrive.getMaximumVelocity()),
            xboxController.getRightX() * swerveDrive.getMaximumAngularVelocity(),
                    false,
                    false);
    }   

}


