package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.SwerveDrive;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveDriveTrain extends SubsystemBase {

    public SwerveModuleState[] moduleState;
    public ChassisSpeeds speed;
    private SwerveDriveKinematics kin;
    public SwerveModule moduleTopRight;
    public SwerveModule moduleTopLeft;
    public SwerveModule moduleBottomLeft;
    public SwerveModule moduleBottomRight;
    private AutoAim aim;
    private final double MAX_SPEED;
    private final double MAX_RADIANS;

    public SwerveDriveTrain(double distanceFromOrigin) {

        Translation2d topRight = new Translation2d(distanceFromOrigin, distanceFromOrigin);
        Translation2d topLeft = new Translation2d(-distanceFromOrigin, distanceFromOrigin);
        Translation2d bottomLeft = new Translation2d(-distanceFromOrigin, -distanceFromOrigin);
        Translation2d bottomRight = new Translation2d(distanceFromOrigin, -distanceFromOrigin);
        aim = new AutoAim();
        MAX_SPEED = 3;
        MAX_RADIANS = 1.5;
        kin = new SwerveDriveKinematics(topRight, topLeft, bottomLeft, bottomRight);
        moduleState = new SwerveModuleState[4];
        speed = new ChassisSpeeds(0, 0, 0);
        moduleTopRight = new SwerveModule(Constants.turnMotorOne, Constants.driveMotorOne, 0);
        moduleTopLeft = new SwerveModule(Constants.turnMotorTwo, Constants.driveMotorTwo, 1);
        moduleBottomLeft = new SwerveModule(Constants.turnMotorThree, Constants.driveMotorThree, 2);
        moduleBottomRight = new SwerveModule(Constants.turnMotorFour, Constants.driveMotorFour, 3);

        setDefaultCommand(new SwerveDrive(this, aim));
    }

    public void updatePeriodic(double translateX, double translateY, double yaw) {

        speed = new ChassisSpeeds(translateX * MAX_SPEED, translateY * MAX_SPEED * -1, yaw * MAX_RADIANS);

        moduleState = kin.toSwerveModuleStates(speed);

        var optimized1 = SwerveModuleState.optimize(moduleState[0], moduleTopRight.currentAngle);
        var optimized2 = SwerveModuleState.optimize(moduleState[1], moduleTopLeft.currentAngle);
        var optimized3 = SwerveModuleState.optimize(moduleState[2], moduleBottomLeft.currentAngle);
        var optimized4 = SwerveModuleState.optimize(moduleState[3], moduleBottomRight.currentAngle);

        // System.out.println("Current Angle: " +
        // Math.toDegrees(mod1.currentAngle.getDegrees()));
        // mod1.setCurrentAngle();
        // System.out.println("Passing through these values, Angle: " +
        // optimized1.angle.getDegrees() + " Speed: " + optimized1.speedMetersPerSecond
        // + " Current Position: " + mod1.currentAngle.getDegrees());
        // System.out.println("Setting angle to: " + optimized1.angle.getDegrees());
        moduleTopRight.setModule(optimized1.angle, optimized1.speedMetersPerSecond);
        moduleTopLeft.setModule(optimized2.angle, optimized2.speedMetersPerSecond);
        moduleBottomLeft.setModule(optimized3.angle, optimized3.speedMetersPerSecond);
        moduleBottomRight.setModule(optimized4.angle, optimized4.speedMetersPerSecond);

    }
}
