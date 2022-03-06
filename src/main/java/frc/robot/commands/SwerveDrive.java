package frc.robot.commands;

import frc.robot.subsystems.AutoAim;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveDriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class SwerveDrive extends CommandBase {
  private final SwerveDriveTrain swerveDriveTrain;
  // private final XboxController remote;
  private final AutoAim aim;

  public SwerveDrive(SwerveDriveTrain _swerveDriveTrainFieldCentric, AutoAim _autoAim) {
    swerveDriveTrain = _swerveDriveTrainFieldCentric;
    aim = _autoAim;
    addRequirements(_swerveDriveTrainFieldCentric);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    if (Constants.controller.getAButton()) {
      if (aim.hasTarget() == 1) {
        while (Math.abs(aim.getXOffset()) > 3) {
          double x = aim.getXOffset();
          if (x < 0) {
            swerveDriveTrain.updatePeriodic(0, 0, 0.2);
          } else {
            swerveDriveTrain.updatePeriodic(0, 0, -0.2);
          }
        }
      }
    }

    swerveDriveTrain.updatePeriodic(Constants.swerveController.getRawAxis(2), Constants.swerveController.getRawAxis(3), Constants.swerveController.getRawAxis(0)*-1);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
