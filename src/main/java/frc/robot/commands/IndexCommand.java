package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;

public class IndexCommand extends CommandBase{
    private Index index; 
   // private Sensor ultrasonics;
    
    public IndexCommand(Index x) {
        index = x;
        addRequirements(index);
      }
    
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
        index.spinOuterIndex();
        Timer.delay(1);
        index.spinInnerIndex();
        Timer.delay(0.75);
        index.stopInnerIndex();
        Timer.delay(0.75);
        index.stopOuterIndex();
      }
    
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
          index.spinInnerIndex();
      }
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {
          index.stopInnerIndex();
          index.stopOuterIndex();
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        return false;
        //(Constants.controller.getXButton());
      }
}
