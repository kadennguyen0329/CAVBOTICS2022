package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AutoAim;
import frc.robot.subsystems.Hood;

public class HoodCommand extends CommandBase {
    private Hood hood;
    private AutoAim aim;
    private double angle;

    public HoodCommand(Hood h, AutoAim _aim) {
        hood = h;
        aim = _aim;
        angle = h.getHoodAngle();
        addRequirements(hood);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        // aim the shooter on left bumper
        if (Constants.controller.getLeftBumper()) {
            angle = aim.setHoodAim();
            hood.setHoodAngle(angle);
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) hood.hoodReset();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
