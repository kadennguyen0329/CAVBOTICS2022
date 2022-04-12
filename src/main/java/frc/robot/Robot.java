// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.HoodCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.OuterIndexCommand;
import frc.robot.commands.ShootCommand;
// import frc.robot.subsystems.AutoAim;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveDrive;

import com.kauailabs.navx.frc.AHRS;

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

  // Global subsytems
  public static Intake intake;
  public static Shooter shooter;
  public static InnerIndex innerIndex;
  public static OuterIndex outerIndex;
  public static Climber climber;
  public static Hood hood;
  // public static AutoAim autoAim;
  public static SwerveDrive swerveDrive;
  public static XboxController controller;
  public static XboxController swerveController;
  RobotContainer m_RobotContainer;
  Command m_autonomousCommand;  

  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    intake = new Intake();
    shooter = new Shooter();
    innerIndex = new InnerIndex();
    outerIndex = new OuterIndex();
    climber = new Climber();
    hood = new Hood();
    // autoAim = new AutoAim();
    swerveDrive = new SwerveDrive(0.29);
    controller = new XboxController(0);
    swerveController = new XboxController(1);
    m_RobotContainer = new RobotContainer();
    SwerveDrive.gyro.calibrate();
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
    SmartDashboard.putBoolean("Intake (B) Status", Constants.intakeStatus);
    SmartDashboard.putBoolean("Outer Index (START) Status", Constants.outerIndexStatus);
    SmartDashboard.putBoolean("Inner Index (X) Status", Constants.innerIndexStatus);
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
    m_autonomousCommand = m_RobotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
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
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

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
