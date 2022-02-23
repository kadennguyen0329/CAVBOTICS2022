package frc.robot.commands;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Sensor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class IntakeCommand extends CommandBase{
    private Intake intake; 
    private Sensor ultrasonics;
    
    public IntakeCommand(Intake x, Sensor y) {
        intake = x;
        ultrasonics = y;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(intake, ultrasonics);
      }
    
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
        intake.startInner();
        intake.startOuter();
        intake.extend();
      }
    
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
          if(ultrasonics.innerIsClosed()){
            intake.stopInner();
          }else{
            intake.startInner();
          }

          if(ultrasonics.outerIsClosed()){
            intake.stopOuter();
          }else{
            intake.startOuter();
          }
      }
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {
          intake.stopInner();
          intake.stopOuter();
          intake.retract();
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        return (Constants.controller.getXButton() || (ultrasonics.outerIsClosed() && ultrasonics.innerIsClosed()));
      }
}
