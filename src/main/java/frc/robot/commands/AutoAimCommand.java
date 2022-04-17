package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.OuterIndex;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoAimCommand extends CommandBase {
  private static SwerveDrive swerveDrive;
  private static XboxController remote;
  private static Shooter shooter;
  private static Hood hood;
  private static InnerIndex inner;
  private static OuterIndex outer;
  private static Limelight light;
  private double p = 0.004;
  private double i = 0;
  private double d = 0.00001;

  public AutoAimCommand() {
    light = RobotContainer.limelight;
    swerveDrive = RobotContainer.swerveDrive;
    remote = RobotContainer.swerveController;
    hood = RobotContainer.hood;
    inner = RobotContainer.innerIndex;
    this.shooter = RobotContainer.shooter;
    outer = RobotContainer.outerIndex;
    this.shooter = RobotContainer.shooter;
    addRequirements(swerveDrive);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    //swerveDrive.setPID(p, i, d);
      double mode = NetworkTableInstance.getDefault().getTable("/datatable").getEntry("shooterMode").getDouble(0);
      //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(0);
      System.out.println("Aligning left and right");
      double startTime = 0;
      double turnStartTime = 0;
      if (mode == 0){
        if (light.hasTarget() == 1){
          if(light.getXDistance() <= 13)
          {
              System.out.println("Short Distance");
              shooter.set(3.3);
              startTime = System.currentTimeMillis();
          }
          else if (light.getXDistance() <= 18)
          {
              System.out.println("Medium Distance");
              shooter.set(3.8);
              startTime = System.currentTimeMillis();
          }
          else {
              System.out.println("Long Distance");
              shooter.set(4.2);
              startTime = System.currentTimeMillis();
          }
          System.out.println("Found target");
          double offset = light.getXOffset();
          turnStartTime = System.currentTimeMillis();
          while (Math.abs(offset) > 2){
            if (offset < 0){
              swerveDrive.updatePeriodic(0, 0, -0.05 * Math.sqrt(Math.abs(offset)));
            } else{
              swerveDrive.updatePeriodic(0, 0, 0.05 * Math.sqrt(Math.abs(offset)));
            }
            offset = light.getXOffset();
          }
          swerveDrive.stopAll();
          System.out.println("Finished turning");
          hood.adjustAngle(light.getXDistance());
          System.out.println("Setting Hood to : " + light.getXDistance());
        } else{
          System.out.println("Manual, can't find target");
          shooter.set(4);
          hood.adjustAngle(15);
          startTime = System.currentTimeMillis();
          turnStartTime = System.currentTimeMillis();

        }
      } else{
        shooter.set(4);
        hood.adjustAngle(15);
        startTime = System.currentTimeMillis();
        turnStartTime = System.currentTimeMillis();

      }
      //finished starting up the flywheel and autoaiming left and right and setting hood angle
      //wait for flywheel to ramp up
      System.out.println("Current Time waiting: " + System.currentTimeMillis());
      while (Math.abs(startTime - System.currentTimeMillis()) < 2000 && Math.abs(turnStartTime - System.currentTimeMillis()) < 2000){
        continue;
      }
      System.out.println("Finished first waiting: " + System.currentTimeMillis());

      double midStart = System  .currentTimeMillis();
      System.out.println("loop 2");
      inner.spin();
      outer.spin();

      while (Math.abs(midStart - System.currentTimeMillis()) < 2000){
        continue;
      }
      System.out.println("Finished loop 2");
      shooter.set(0);
      inner.stop();
      outer.stop();
      RobotContainer.status = 1;
  }

  @Override
  public void end(boolean interrupted) {
    //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("SwerveCommand").setBoolean(false);
    swerveDrive.stopAll();
    shooter.set(0);
    inner.stop();
    outer.stop();
  }

  @Override
  public boolean isFinished() {
    if (RobotContainer.status == 1) {
        RobotContainer.status = 0;
        return true;   
    }
    return false;
  }
}
