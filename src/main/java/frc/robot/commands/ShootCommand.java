package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Shooter;

public class ShootCommand extends CommandBase {
    private Index index;
    private Shooter shooter;

    public ShootCommand(Index i, Shooter sh, int desiredRPM) {
        index = i;
        shooter = sh;
        addRequirements(index, shooter);

    }

    @Override
    public void initialize() {
        shooter.setWheel(9);
        Timer.delay(3);
    }

    @Override
    public void execute() {
        index.spinInnerIndex();
        index.spinOuterIndex();
    }

    @Override
    public void end(boolean interrupted) {
        index.stopOuterIndex();
        index.stopInnerIndex();
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
