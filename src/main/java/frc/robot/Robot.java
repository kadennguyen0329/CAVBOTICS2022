// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.HoodCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.OuterIndexCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.AutoAim;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveDriveTrain;

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
  private RobotContainer m_RobotContainer;
  private Command m_autoCommand;
  private int id;

  @Override
  public void robotInit() {
    swerve = new SwerveDriveTrain(1.5);
    m_RobotContainer = new RobotContainer();
    // RobotContainer m_robotContainer = new RobotContainer();
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
    SmartDashboard.putBoolean("Intake Status", Constants.intakeStatus);
    SmartDashboard.putBoolean("Outer Index Status", Constants.outerIndexStatus);
    SmartDashboard.putBoolean("Inner Index Status", Constants.innerIndexStatus);
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
    Intake intake = Constants.intake;
    OuterIndex outerIndex = Constants.outerIndex;
    Hood hood = Constants.hood;
    InnerIndex innerIndex = Constants.innerIndex;
    double desiredRPM = 0;
    Shooter shooter = Constants.shooter;

    Timer timer = new Timer();
    timer.reset();

    Command intakeCommand = new IntakeCommand(intake);
    Command outerIndexCommand = new OuterIndexCommand(outerIndex);

    while (timer.get() <= 2) {
      swerve.updatePeriodic(0, 0.2, 0);
    }

    intakeCommand.cancel();
    outerIndexCommand.cancel();

    while (autoAim.hasTarget() == 0) {
      swerve.updatePeriodic(0, 0, 0.3);
    }

    while (Math.abs(autoAim.getXOffset()) > 3) {
      if (autoAim.getXOffset() < 0) {
        swerve.updatePeriodic(0, 0, 0.2);
      } else {
        swerve.updatePeriodic(0, 0, -0.2);
      }
    }

    Command hoodCommand = new HoodCommand(hood, autoAim);

    Command shootCommand = new ShootCommand(innerIndex, outerIndex, shooter, desiredRPM);

    hoodCommand.cancel();
    shootCommand.cancel();

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
