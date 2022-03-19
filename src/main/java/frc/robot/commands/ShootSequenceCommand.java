package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShootSequenceCommand extends CommandBase {
    private static InnerIndex innerIndex;
    private static OuterIndex outerIndex;
    private static Shooter shooter;
    private static Hood hood;
    private static Limelight limelight;

    public ShootSequenceCommand() {
        innerIndex = RobotContainer.innerIndex;
        outerIndex = RobotContainer.outerIndex;
        shooter = RobotContainer.shooter;
        hood = RobotContainer.hood;
        limelight = RobotContainer.limelight;
        addRequirements(innerIndex, outerIndex, shooter, limelight);

    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if(limelight.getXDistance() <= 6)
        {
            shooter.set(4.5);
            if(shooter.getRPM() > 1900) {
                outerIndex.spin();
                innerIndex.spin();
            }
        }
        else if (limelight.getXDistance() <= 12)
        {
            shooter.set(4.8);
            if(shooter.getRPM() > 2100) {
                outerIndex.spin();
                innerIndex.spin();
            }
        }
        else {
            shooter.set(5);
            if (shooter.getRPM() > 2300){
                outerIndex.spin();
                innerIndex.spin();
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        innerIndex.stop();
        outerIndex.stop();
        shooter.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
