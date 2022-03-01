package frc.robot.commands;

import frc.robot.subsystems.AutoAim;
import frc.robot.subsystems.SwerveDriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveDrive extends CommandBase {
  private final SwerveDriveTrain swerveDriveTrain;
  private final XboxController remote;
  private final AutoAim aim;
  
  public SwerveDrive(SwerveDriveTrain _swerveDriveTrainFieldCentric) {
    swerveDriveTrain = _swerveDriveTrainFieldCentric;
    remote = new XboxController(0);
    addRequirements(_swerveDriveTrainFieldCentric);
    aim = new AutoAim();
  }

  @Override
  public void initialize() {}


  @Override
  public void execute() {
    if (remote.getAButtonPressed()){
        double[] temp = aim.aimPeriodic();
        swerveDriveTrain.updatePeriodic(0, 0, temp[2]);
    }
    else{
      swerveDriveTrain.updatePeriodic(remote.getLeftX(), remote.getLeftY(), remote.getRightX());
    }  
  }
  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
