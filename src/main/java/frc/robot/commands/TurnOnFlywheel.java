// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TurnOnFlywheel extends CommandBase {

    private Flywheel flywheel;
  public TurnOnFlywheel(Flywheel f) {
    flywheel = f;
  }

  @Override
  public void execute() {
    flywheel.start();
  }
  @Override
  public void end(boolean interrupted) {
    flywheel.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
