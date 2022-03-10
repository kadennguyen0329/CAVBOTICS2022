package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;
import frc.robot.Constants;
import frc.robot.Robot;

public class ShootCommand extends CommandBase {
    private static InnerIndex innerIndex;
    private static OuterIndex outerIndex;
    private static Shooter shooter;
    private static double power;
    private static Hood hood;

    public ShootCommand() {
        innerIndex = Robot.innerIndex;
        outerIndex = Robot.outerIndex;
        shooter = Robot.shooter;
        hood = Robot.hood;
        power = 0;
        addRequirements(innerIndex, outerIndex, shooter);

    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        
        shooter.setWheel(power);
        if (power >= 0.6) {
            
            innerIndex.spin();
            outerIndex.spin();
        }else{
            power += 0.09;
            Timer.delay(0.2);
        }
    }

    @Override
    public void end(boolean interrupted) {
        innerIndex.stop();
        outerIndex.stop();
        shooter.setWheel(0);
        power = 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
