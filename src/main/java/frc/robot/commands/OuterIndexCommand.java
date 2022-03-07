package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        Constants.outerIndexStatus = true;
        index.spin();

    }

    @Override
    public void execute() {
        // SmartDashboard.putBoolean("Outer Index Status", Constants.outerIndexStatus);
        index.spin();
    }

    @Override
    public void end(boolean interrupted) {
        Constants.outerIndexStatus = false;
        index.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
