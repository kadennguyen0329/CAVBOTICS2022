package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class IntakeCommand extends CommandBase {
  private Intake intake;

  public IntakeCommand(Intake x) {
    intake = x;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.extend();
  }

  @Override
  public void execute() {
    if (Constants.controller.getYButton()) {
      intake.spinIntake();
    }
  }

  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
    intake.retract();
  }

  @Override
  public boolean isFinished() {
    return Constants.controller.getXButton();
  }
}
