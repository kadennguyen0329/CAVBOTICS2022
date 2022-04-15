package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;

public class LimeLightOffCommand extends CommandBase {
    private static Limelight limelight;

    public LimeLightOffCommand() {
        limelight = RobotContainer.limelight;
        addRequirements(limelight);

    }

    @Override
    public void execute() {
        NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
    }

    @Override
    public void end(boolean interrupted) {
        //NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
       
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
