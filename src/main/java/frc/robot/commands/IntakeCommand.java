package frc.robot.commands;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.OuterIndex;

import com.fasterxml.jackson.databind.util.ClassUtil.Ctor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeCommand extends CommandBase {
  private Intake intake;
  private OuterIndex outerIndex;

  public IntakeCommand() {
    intake = RobotContainer.intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    /*Constants.intakeStatus = true;
    System.out.println("Solenoid current state: " + intake.getValue());
    intake.extend();*/
    intake.extend();
    
  }

  @Override
  public void execute() {
    // SmartDashboard.putBoolean("Intake Status", Constants.intakeStatus);

    intake.spinIntake();
    outerIndex.spin();
  }

  @Override
  public void end(boolean interrupted) {
   // Constants.intakeStatus = false;
    intake.stopIntake();
    intake.retract();
    outerIndex.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
