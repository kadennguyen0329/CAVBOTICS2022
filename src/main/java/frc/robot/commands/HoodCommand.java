package frc.robot.commands;

import frc.robot.subsystems.Hood;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HoodCommand extends CommandBase{
    private Hood x;
    public HoodCommand(Hood hood){
        hood = x;
        addRequirements(x);
    }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
      x.start();
  }

  @Override
  public void end(boolean interrupted) {
      x.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
    
}
