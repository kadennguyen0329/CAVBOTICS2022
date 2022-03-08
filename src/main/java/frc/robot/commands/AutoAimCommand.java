package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AutoAim;
import frc.robot.subsystems.Hood;

public class AutoAimCommand extends CommandBase{
    
    private AutoAim aim;
    private Hood hood;

    public AutoAimCommand(AutoAim a, Hood h){
        aim = a;
        hood = h;

    }

    @Override
    public void initialize(){
        hood.hoodReset();
    }

    @Override
    public void execute(){
        
    }


}
