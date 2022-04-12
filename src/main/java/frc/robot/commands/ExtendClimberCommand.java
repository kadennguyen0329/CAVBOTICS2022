package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class ExtendClimberCommand extends CommandBase{

    private Climber climber;

    public ExtendClimberCommand(){
        climber = RobotContainer.climber;
        addRequirements(climber);
    }

    @Override
    public void execute(){
        climber.extend();
    }

    @Override
    public void end(boolean interrupted){
        climber.stop();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
