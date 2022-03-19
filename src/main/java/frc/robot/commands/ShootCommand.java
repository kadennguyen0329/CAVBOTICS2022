package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShootCommand extends CommandBase {
    private static Shooter shooter;

    public ShootCommand() {
        shooter = RobotContainer.shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        shooter.set(4.5);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
