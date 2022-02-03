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
  public void execute() {
    m_subsystem.setSpeed(.8);
  
  }

  @Override
  public void end(boolean interrupted) {
    m_subsystem.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return (Constants.controller.getYButton());
  }
}
