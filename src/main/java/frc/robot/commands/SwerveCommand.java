package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.SwerveDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveCommand extends CommandBase {
  private static SwerveDrive swerveDrive;
  private static XboxController remote;
  
  public SwerveCommand() {
    swerveDrive = Robot.swerveDrive;
    remote = Robot.controller;
  }

  @Override
  public void initialize() {}


  @Override
  public void execute() {
    swerveDrive.updatePeriodic(remote.getLeftY(), remote.getLeftX(), remote.getRightX());
  }
  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
