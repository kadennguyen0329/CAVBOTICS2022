package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIndex;

public class InnerIndexCommand extends CommandBase{

    private InnerIndex index;
    

    public InnerIndexCommand(InnerIndex i){
        index = i;

    }

    @Override
    public void initialize(){
        index.spin();

    }

    @Override
    public void execute(){
        index.spin();
    }

    @Override
    public void end(boolean interrupted){
        index.stop();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
