package frc.robot.commands;

import frc.robot.subsystems.AutoAim;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SimpleHood;
import frc.robot.subsystems.SwerveDriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveDrive extends CommandBase {
  private final SwerveDriveTrain swerveDriveTrain;
  private final XboxController remote;
  private final AutoAim aim;
  private final SimpleHood hood;
  private Shooter wheel;

  public SwerveDrive(SwerveDriveTrain _swerveDriveTrainFieldCentric, AutoAim _autoAim) {
    swerveDriveTrain = _swerveDriveTrainFieldCentric;
    remote = new XboxController(0);
    aim = _autoAim;
    wheel = new Shooter();
    hood = new SimpleHood(11);
    addRequirements(_swerveDriveTrainFieldCentric);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    if (remote.getAButton()) {
      if (aim.hasTarget() == 1) {
        while (Math.abs(aim.getXOffset()) > 3) {
          double x = aim.getXOffset();
          if (x < 0) {
            swerveDriveTrain.updatePeriodic(0, 0, 0.2);
          } else {
            swerveDriveTrain.updatePeriodic(0, 0, -0.2);
          }
        }

        double angle = aim.setHoodAim();
        System.out.println("Setting to : " + angle);
        // //SmartDashboard.putNumber("Hood", angle);
        hood.setHoodAngle(angle);
        // System.out.println("Distance: " + aim.testDistance());

        // if (remote.getBButton()) {
        // double RPM = aim.getRPM();
        // // wheel.setWheel(RPM);
        // }
      }
    } else if (remote.getYButton()) {
      hood.hoodReset();
    } else if (remote.getStartButton()) {
      wheel.setWheel(0.6);
    } else {
      swerveDriveTrain.updatePeriodic(remote.getLeftX(), remote.getLeftY(), -1 * remote.getRightX());
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
