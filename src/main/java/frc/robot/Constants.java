package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

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

    // Hood
    public static final int hoodIdOne = 2;
    public static final int hoodIdTwo = 3;

    // Flywheel
    public static final int flyWheelIdOne = 4;
    public static final int flyWheelIdTwo = 5;

    // Index IDs
    public static final int innerIndexId = 9;
    public static final int outerIndexId = 7;
    
    // Intake
    public static final int intakeId = 12;

    // Solenoid
    public static final int mainSolenoidOne = 0;
    public static final int mainSolenoidTwo = 1;

    // Pickup wheel
    public static final int pickupWheelId = 13;

    // Shooter
    public static final int shooterIdOne = 10;
    public static final int shooterIdTwo = 14;

    // Swerve modules
    public static final int turnMotorOne = 1;
    public static final int driveMotorOne = 2;
    public static final int turnMotorTwo = 3;
    public static final int driveMotorTwo = 4;
    public static final int turnMotorThree = 5;
    public static final int driveMotorThree = 6;
    public static final int turnMotorFour = 7;
    public static final int driveMotorFour = 8;

    public static final int desiredRPM = 100;
}
