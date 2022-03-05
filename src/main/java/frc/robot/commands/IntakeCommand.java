package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class IntakeCommand extends CommandBase {
  private Intake intake;

  public IntakeCommand(Intake x) {
    intake = x;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.extend();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Constants.controller.getYButton()) {
      intake.spinIntake();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
    intake.retract();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Constants.controller.getXButton();
  }
}
