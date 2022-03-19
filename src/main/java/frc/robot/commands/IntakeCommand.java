package frc.robot.commands;

import frc.robot.subsystems.Intake;

import com.fasterxml.jackson.databind.util.ClassUtil.Ctor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeCommand extends CommandBase {
  private Intake intake;

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
  }

  @Override
  public void end(boolean interrupted) {
   // Constants.intakeStatus = false;
    intake.stopIntake();
    intake.retract();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
