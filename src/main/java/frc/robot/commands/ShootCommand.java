package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;

public class ShootCommand extends CommandBase {
    private InnerIndex innerIndex;
    private OuterIndex outerIndex;
    private Shooter shooter;

    public ShootCommand(InnerIndex i, OuterIndex o, Shooter sh, int desiredRPM) {
        innerIndex = i;
        outerIndex = o;
        shooter = sh;
        addRequirements(innerIndex, outerIndex, shooter);

    }

    @Override
    public void initialize() {
        shooter.setWheel(9);
        Timer.delay(3);
    }

    @Override
    public void execute() {
        if (shooter.isDesiredRPM(Constants.desiredRPM)) {
            innerIndex.spin();
            outerIndex.spin();
        }
    }

    @Override
    public void end(boolean interrupted) {
        innerIndex.stop();
        outerIndex.stop();
        shooter.setWheel(0);
    }

    @Override
    public boolean isFinished() {
        /*
         * if (Constants.controller.getBButton()) {
         * return true;
         * }
         * return false;
         */
        return false;
    }

}
