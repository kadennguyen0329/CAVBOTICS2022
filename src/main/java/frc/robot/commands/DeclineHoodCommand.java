package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hood;

public class DeclineHoodCommand extends CommandBase {

    private Hood hood;

    public DeclineHoodCommand() {
        hood = RobotContainer.hood;
        addRequirements(hood);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        hood.manualMove(-0.05);
    }

    @Override
    public void end(boolean interrupted) {
        hood.manualMove(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}