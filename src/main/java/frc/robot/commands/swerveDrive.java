package frc.robot.commands;

import frc.robot.subsystems.SwerveDriveTrainFieldCentric;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class swerveDrive extends CommandBase {
  private final SwerveDriveTrainFieldCentric swerveDriveTrainFieldCentric;
  private final XboxController remote;
  
  public swerveDrive(SwerveDriveTrainFieldCentric _swerveDriveTrainFieldCentric) {
    swerveDriveTrainFieldCentric = _swerveDriveTrainFieldCentric;
    remote = new XboxController(0);
    addRequirements(_swerveDriveTrainFieldCentric);
  }

  @Override
  public void initialize() {}


  @Override
  public void execute() {
    swerveDriveTrainFieldCentric.updatePeriodic(remote.getLeftX(), remote.getLeftY(), remote.getRightX());
    
  }
  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
