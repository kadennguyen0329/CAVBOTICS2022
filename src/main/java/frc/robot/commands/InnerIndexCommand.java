package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.InnerIndex;

public class InnerIndexCommand extends CommandBase {

    private InnerIndex index;

    public InnerIndexCommand(InnerIndex i) {
        index = i;

    }

    @Override
    public void initialize() {
        Constants.innerIndexStatus = true;
        index.spin();

    }

    @Override
    public void execute() {
        // SmartDashboard.putBoolean("Intake Status", Constants.innerIndexStatus);
        index.spin();
    }

    @Override
    public void end(boolean interrupted) {
        Constants.innerIndexStatus = false;
        index.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
