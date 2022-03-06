package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.OuterIndex;

public class OuterIndexCommand extends CommandBase {

    private OuterIndex index;

    public OuterIndexCommand(OuterIndex i) {
        index = i;

    }

    @Override
    public void initialize() {
        index.spin();

    }

    @Override
    public void execute() {
        index.spin();
    }

    @Override
    public void end(boolean interrupted) {
        index.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
