package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class PickupWheel extends SubsystemBase {
    private CANSparkMax pickup;

    public PickupWheel() {
        pickup = new CANSparkMax(Constants.pickupWheelId, MotorType.kBrushless);
    }

    public void start() {
        pickup.set(-0.22);
    }

    public void stop() {
        pickup.set(0);
    }
}
