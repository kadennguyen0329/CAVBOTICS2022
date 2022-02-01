package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PickUpWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PickUpWheelCommand extends CommandBase {
  private final PickUpWheelCommand m_subsystem;
 

  public PickUpWheelCommand(PickUpWheel subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
