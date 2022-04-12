package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OuterIndex;

public class OuterIndexCommand extends CommandBase {

    private OuterIndex index;

    public OuterIndexCommand() {
        index = RobotContainer.outerIndex;
        addRequirements(index);
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
