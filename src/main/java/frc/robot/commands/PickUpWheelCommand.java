package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PickUpWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class PickUpWheelCommand extends CommandBase {
  private final PickUpWheel m_subsystem;
 

  public PickUpWheelCommand(PickUpWheel subsystem) {
    m_subsystem = subsystem;
    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return (Constants.controller.getYButton());
  }
}
