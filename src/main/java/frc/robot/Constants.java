package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Xbox controller
    public static final XboxController controller = new XboxController(0);

    // Swerve controller
    public static final XboxController swerveController = new XboxController(1);

    // Hood
    public static final int hoodId = 11;
    // Flywheel
    public static final int flyWheelIdOne = 4;
    public static final int flyWheelIdTwo = 5;

    // Index IDs
    public static final int innerIndexId = 12;
    public static final int outerIndexId = 15;
    
    // Intake
    public static final int intakeId = 16;

    // Solenoid
    public static final int mainSolenoidOne = 14;
    public static final int mainSolenoidTwo = 15;

    // Climber
    public static final int leftArmID = 13;
    public static final int rightArmID = 9;

    // Shooter
    public static final int shooterIdOne = 14;
    public static final int shooterIdTwo = 10;

    // Swerve modules
    public static final int turnMotorOne = 1;
    public static final int driveMotorOne = 2;
    public static final int turnMotorTwo = 3;
    public static final int driveMotorTwo = 4;
    public static final int turnMotorThree = 5;
    public static final int driveMotorThree = 6;
    public static final int turnMotorFour = 7;
    public static final int driveMotorFour = 8;

    public static final double desiredRPM = 0.5;

    // Smartdashboard states
    public static boolean intakeStatus = false;
    public static boolean outerIndexStatus = false;
    public static boolean innerIndexStatus = false;

    // Global subsytems
    public final static Intake intake = new Intake();
    public final static Shooter shooter = new Shooter();
    public final static InnerIndex innerIndex = new InnerIndex();
    public final static OuterIndex outerIndex = new OuterIndex();
    public final static Climber climber = new Climber();
    public final static Hood hood = new Hood();
}
