package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Flywheel;

public class ShootCommand extends CommandBase {
    private Flywheel flywheel;

    public ShootCommand(Flywheel f) {
        flywheel = f;
        addRequirements(flywheel);

    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
       
    }

    @Override
    public void end(boolean interrupted) {
        flywheel.stop();
    }

    @Override
    public boolean isFinished() {
        if (Constants.controller.getBButton()) {
            return true;
        }
        return false;
    }

}
