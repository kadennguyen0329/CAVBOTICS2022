package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Sensor;

public class ShootCommand extends CommandBase {
    private Flywheel flywheel;
    private Index index; 
    private Sensor sensor;

    public ShootCommand(Flywheel f, Index i, Sensor s, int desiredRPM){
        flywheel = f;
        index = i;
        sensor = s;
        addRequirements(flywheel, index, sensor);
    }

    @Override
    public void initialize(){
        flywheel.start();
        
    }

    @Override
    public void execute(){
        if(flywheel.fullSpeed() && sensor.innerIsClosed()){
            index.spinInnerIndex();
        }
        if(!sensor.innerIsClosed() && sensor.outerIsClosed()){
            index.spinInnerIndex();

        }

    }

    @Override
    public void end(boolean interrupted){
        index.stopInnerIndex();
        flywheel.stop();

    }

    @Override
    public boolean isFinished(){
        if(!sensor.innerIsClosed() && !sensor.outerIsClosed()){
            return true;
        }
        return false;

    }


}
