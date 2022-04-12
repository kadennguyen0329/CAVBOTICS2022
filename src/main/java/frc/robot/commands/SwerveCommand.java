package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SwerveDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.subsystems.SwerveDrive;

public class SwerveCommand extends CommandBase {
  private static SwerveDrive swerveDrive;
  private static XboxController remote;
  private static Limelight light;
  private double contStrafe = remote.getRightX();
  private double contForward = remote.getRightY();
  private double contYaw = remote.getLeftX();
  private double theta = Math.toRadians(SwerveDrive.gyro.getAngle());
  private double gyroStrafe;
  private double gyroForward;

  public SwerveCommand() {
    light = RobotContainer.limelight;
    swerveDrive = RobotContainer.swerveDrive;
    remote = RobotContainer.swerveController;
    addRequirements(swerveDrive);
    swerveDrive.m_frontRightLocation.reset();
    swerveDrive.m_frontLeftLocation.reset();
    swerveDrive.m_backLeftLocation.reset();
    swerveDrive.m_backRightLocation.reset();
  }

  @Override
  public void initialize() {
    swerveDrive.m_frontRightLocation.reset();
    swerveDrive.m_frontLeftLocation.reset();
    swerveDrive.m_backLeftLocation.reset();
    swerveDrive.m_backRightLocation.reset();
  }

  @Override
  public void execute() {
    if (remote.getLeftStickButton()){
      if (light.hasTarget() == 1){
        double offset = light.getXOffset();
        if (Math.abs(offset) > 3){
          if (offset < 0){
            swerveDrive.updatePeriodic(0, 0, -0.3);
          } else{
            swerveDrive.updatePeriodic(0, 0, 0.3);
          }
        }
      }
    }else{
      gyroStrafe = -1 * (contForward * Math.sin(theta) - contStrafe * Math.cos(theta));
      gyroForward = contForward * Math.cos(theta) + contStrafe * Math.sin(theta);
      swerveDrive.updatePeriodic(gyroForward, gyroStrafe, contYaw);
    }
    //swerveDrive.updatePeriodic(remote.getRawAxis(0), remote.getRawAxis(1), remote.getRawAxis(2) * -1);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
