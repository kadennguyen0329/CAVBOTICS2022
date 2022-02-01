package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PickUpWheel extends SubsystemBase {
    private CANSparkMax motor;
    public PickUpWheel() {
        motor = new CANSparkMax(1, MotorType.kBrushless);
    }

    public void setSpeed(double speed){
        motor.set(speed);
    }
}
