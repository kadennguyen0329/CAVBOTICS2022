// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.AutoAim;
import frc.robot.subsystems.SwerveDriveTrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.*;

// Swerve Field Centric

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private SwerveDriveTrain swerve;
  private AutoAim autoAim;
  private CANSparkMax motor1;
  private CANSparkMax motor2;
  private CANSparkMax motor3;
  private CANSparkMax motor4;
  private CANSparkMax motor5;
  private CANSparkMax motor6;
  private CANSparkMax motor7;
  private CANSparkMax motor8;
  private CANSparkMax motor9;
  private CANSparkMax motor10;
  private CANSparkMax motor11;
  private CANSparkMax motor12;
  private CANSparkMax motor13;
  private CANSparkMax motor14;
  private CANSparkMax motor15;

  private int id;

  @Override
  public void robotInit() {
    motor1 = new CANSparkMax(1, MotorType.kBrushless);
    motor2 = new CANSparkMax(2, MotorType.kBrushless);
    motor3 = new CANSparkMax(3, MotorType.kBrushless);
    motor4 = new CANSparkMax(4, MotorType.kBrushless);
    motor5 = new CANSparkMax(5, MotorType.kBrushless);
    motor6 = new CANSparkMax(6, MotorType.kBrushless);
    motor7 = new CANSparkMax(7, MotorType.kBrushless);
    motor8 = new CANSparkMax(8, MotorType.kBrushless);
    motor9 = new CANSparkMax(9, MotorType.kBrushless);
    motor10 = new CANSparkMax(10, MotorType.kBrushless);
    motor11 = new CANSparkMax(11, MotorType.kBrushless);
    motor12 = new CANSparkMax(12, MotorType.kBrushed);
    motor13 = new CANSparkMax(13, MotorType.kBrushless);
    motor14 = new CANSparkMax(14, MotorType.kBrushless);
    motor15 = new CANSparkMax(16, MotorType.kBrushless);
    id = 0;

    // swerve = new SwerveDriveTrain(1.5, 1, 2, 3, 4, 5, 6, 7, 8);
    // swerve = new SwerveDriveTrainFieldCentric(1.5, 1, 2, 3, 4, 5, 6, 7, 8);
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    if (Constants.controller.getLeftBumperPressed())
      id++;
    if (Constants.controller.getRightBumperPressed())
      id--;

    if (Constants.controller.getAButton()) {

      if (id == 0)
        motor1.set(0.1);
      else if (id == 1)
        motor2.set(0.1);
      else if (id == 2)
        motor3.set(0.1);
      else if (id == 3)
        motor4.set(0.1);
      else if (id == 4)
        motor5.set(0.1);
      else if (id == 5)
        motor6.set(0.1);
      else if (id == 6)
        motor7.set(0.1);
      else if (id == 7)
        motor8.set(0.1);
      else if (id == 8)
        motor9.set(0.1);
      else if (id == 9)
        motor10.set(0.1);
      else if (id == 10)
        motor11.set(0.1);
      else if (id == 11)
        motor12.set(0.1);
      else if (id == 12)
        motor13.set(0.1);
      else if (id == 13)
        motor14.set(0.1);
      else if (id == 14)
        motor15.set(0.1);
    } else {
      motor1.stopMotor();
      motor2.stopMotor();
      motor3.stopMotor();
      motor4.stopMotor();
      motor5.stopMotor();
      motor6.stopMotor();
      motor7.stopMotor();
      motor8.stopMotor();
      motor9.stopMotor();
      motor10.stopMotor();
      motor11.stopMotor();
      motor12.stopMotor();
      motor13.stopMotor();
      motor14.stopMotor();
      motor15.stopMotor();

    }

    SmartDashboard.putNumber("ID", id + 1);
    // System.out.println(SmartDashboard.getNumber("motors1", 0));
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // swerve.updatePeriodic(controller.getLeftX(), controller.getLeftY(),
    // controller.getRightX());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
