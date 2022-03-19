package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Hood;

public class HoodCommand extends CommandBase {
    
    private Hood hood;
    private Limelight limelight;

    public HoodCommand() {
        hood = RobotContainer.hood;
        limelight = RobotContainer.limelight;
        addRequirements(hood, limelight);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        hood.adjustAngle(limelight.getXDistance());
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("HoodCommand").setBoolean(true);

    }

    @Override
    public void end(boolean interrupted) {
        // if (interrupted)
        // hood.hoodReset();
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("HoodCommand").setBoolean(false);

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
