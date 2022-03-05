package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AutoAim;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Index;

public class HoodCommand extends CommandBase {
  private Hood hood;
  private AutoAim aim;

  public HoodCommand(Hood h, AutoAim _aim) {
    hood = h;
    aim = _aim;
    addRequirements(hood);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
