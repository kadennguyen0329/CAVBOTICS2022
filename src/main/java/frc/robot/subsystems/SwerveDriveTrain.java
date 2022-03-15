// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.*;
import com.kauailabs.navx.frc.*;

public class SwerveDrive extends SubsystemBase {

  private SwerveModuleState[] moduleState;
  private ChassisSpeeds speeds;
  private SwerveDriveKinematics kinematics;
  private SwerveModule m_frontRightLocation;
  private SwerveModule m_frontLeftLocation;
  //private SwerveModule m_backLeftLocation;
  private SwerveModule m_backRightLocation;
  private final double MAX_SPEED;
  private final double MAX_RADIANS;
  private AHRS gyro;

  public SwerveDrive(double distanceFromOrigin) {

    // (Y,X) format
    Translation2d frontLeftLocation = new Translation2d(distanceFromOrigin, distanceFromOrigin);
    Translation2d frontRightLocation = new Translation2d(distanceFromOrigin, -distanceFromOrigin);
    Translation2d backLeftLocation = new Translation2d(-distanceFromOrigin, distanceFromOrigin);
    Translation2d backRightLocation = new Translation2d(-distanceFromOrigin, -distanceFromOrigin);

    kinematics = new SwerveDriveKinematics(frontLeftLocation, frontRightLocation, backLeftLocation, backRightLocation);

    try {
      gyro = new AHRS(SPI.Port.kMXP);
      gyro.zeroYaw();
    } catch (RuntimeException ex) {
      System.out.println("--------------");
      System.out.println("NavX not plugged in");
      System.out.println("--------------");
    }

    MAX_SPEED = 0.15;
    MAX_RADIANS = 3.14;

    moduleState = new SwerveModuleState[4];
    m_frontRightLocation = new SwerveModule(Constants.frontRightTurn, Constants.frontRightDrive,
        Constants.frontRightEncoder);
    m_frontLeftLocation = new SwerveModule(Constants.frontLeftTurn, Constants.frontLeftDrive,
        Constants.frontLeftEncoder);
    //m_backLeftLocation = new SwerveModule(Constants.backLeftTurn, Constants.backLeftDrive, Constants.backLeftEncoder);
    m_backRightLocation = new SwerveModule(Constants.backRightTurn, Constants.backRightDrive,
        Constants.backRightEncoder);

  }

  public void updatePeriodic(double translateY, double translateX, double yaw) {

    // speeds = ChassisSpeeds.fromFieldRelativeSpeeds(translateY * MAX_SPEED, translateX * MAX_SPEED * -1,
    //     yaw * MAX_RADIANS, new Rotation2d(Math.toRadians(getGyroAngle())));
    speeds = new ChassisSpeeds(-1 * translateY * MAX_SPEED, translateX *
    MAX_SPEED, yaw * MAX_RADIANS);

    moduleState = kinematics.toSwerveModuleStates(speeds);

    SmartDashboard.putNumber("before optimize", moduleState[0].angle.getDegrees());

    m_frontLeftLocation.update();
    m_frontRightLocation.update();
    //m_backLeftLocation.update();
    m_backRightLocation.update();

    m_frontLeftLocation.setModule(moduleState[0].angle, moduleState[0].speedMetersPerSecond);
    m_frontRightLocation.setModule(moduleState[1].angle, moduleState[1].speedMetersPerSecond);
    //m_backLeftLocation.setModule(moduleState[2].angle, moduleState[2].speedMetersPerSecond);
    m_backRightLocation.setModule(moduleState[3].angle, moduleState[3].speedMetersPerSecond);

    SmartDashboard.putNumber("gyro converted", this.getGyroAngle());
    SmartDashboard.putNumber("gyro raw", gyro.getAngle());

  }

  public double getGyroAngle() {

    // converts from 0 to 360 to 0 to 180 and 0 to -180
    double angle = gyro.getYaw() % 360.0;
    if (angle < 0)
      angle = 360 + angle;
    if (angle > 180)
      angle = -(360 - angle);

    return angle;
  }

  public double getRawAngle() {
    return gyro.getAngle();
  }
}
