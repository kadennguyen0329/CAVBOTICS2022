package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SwerveDrive;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveCommand extends CommandBase {
  private static SwerveDrive swerveDrive;
  private static XboxController remote;
  private static Limelight light;
  private double p = 0.004;
  private double i = 0;
  private double d = 0.00001;

  public SwerveCommand() {
    light = RobotContainer.limelight;
    swerveDrive = RobotContainer.swerveDrive;
    remote = RobotContainer.swerveController;
    addRequirements(swerveDrive);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("SwerveCommand").setBoolean(true);

    
    p = (double)NetworkTableInstance.getDefault().getTable("/datatable").getEntry("P").getNumber(0.004);
    i = (double)NetworkTableInstance.getDefault().getTable("/datatable").getEntry("I").getNumber(0);
    d = (double)NetworkTableInstance.getDefault().getTable("/datatable").getEntry("D").getNumber(0.00001);
    //swerveDrive.setPID(p, i, d);
    if (RobotContainer.controller.getLeftStickButton()){
      //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(0);
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
    } else{


      
      if (Math.abs(remote.getLeftY()) >= 0.1 || Math.abs(remote.getLeftX()) >= 0.1 || Math.abs(remote.getRightX()) >= 0.1){
        swerveDrive.updatePeriodic(remote.getLeftX() * -1, remote.getLeftY() * -1, remote.getRightX());
        //System.out.println("working");

      } else{
        swerveDrive.stopAll();

      }

      // if (Math.abs(remote.getRawAxis(0)) >= 0.1 || Math.abs(remote.getRawAxis(1)) >= 0.1 || Math.abs(remote.getRawAxis(2)) >= 0.1){
      //   swerveDrive.updatePeriodic(remote.getRawAxis(0), remote.getRawAxis(1) * -1, remote.getRawAxis(2) * -1);

      // } else{
      //   swerveDrive.stopAll();

      // }
    }
    
    
  }

  @Override
  public void end(boolean interrupted) {
    //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("SwerveCommand").setBoolean(false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
