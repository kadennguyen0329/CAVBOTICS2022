package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.PickupWheel;

public class ShootCommand extends CommandBase {
    private Flywheel flywheel;
    private Index index;
    private Sensor sensor;
    private PickupWheel pickup;
    private Shooter shooter;

    public ShootCommand(Flywheel f, Index i, Sensor s, PickupWheel p, Shooter sh, int desiredRPM) {
        flywheel = f;
        index = i;
        sensor = s;
        pickup = p;
        shooter = sh;
        addRequirements(flywheel, index, sensor, pickup, shooter);
        
    }

    @Override
    public void initialize() {
        flywheel.start();
    }

    @Override
    public void execute() {
        if (flywheel.fullSpeed()) {
            // replace value, temp for now
            // to replace with aim
            shooter.setWheel(9);

            index.spinInnerIndex();
            index.spinOuterIndex();

            pickup.start();

        }
    }

    @Override
    public void end(boolean interrupted) {
        index.stopInnerIndex();
        index.stopOuterIndex();
        flywheel.stop();
        pickup.stop();
        shooter.setWheel(0);
    }

    @Override
    public boolean isFinished() {
        if (Constants.controller.getBButton()) {
            return true;
        }
        return false;
    }

}
