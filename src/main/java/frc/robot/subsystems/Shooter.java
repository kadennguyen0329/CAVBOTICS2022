package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.SparkMaxPIDController;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private CANSparkMax shooterLeft;
    private CANSparkMax shooterRight;
    private RelativeEncoder enc;

    public Shooter() {
        shooterLeft = new CANSparkMax(Constants.shooterIdOne, MotorType.kBrushless);
        shooterRight = new CANSparkMax(Constants.shooterIdTwo, MotorType.kBrushless);

        shooterLeft.enableVoltageCompensation(12);
        shooterRight.enableVoltageCompensation(12);

        shooterLeft.setOpenLoopRampRate(2.3);
        shooterRight.setOpenLoopRampRate(2.3);
        shooterLeft.setClosedLoopRampRate(2.3);
        shooterRight.setClosedLoopRampRate(2.3);

        enc = shooterLeft.getEncoder();
    }

    public void set(double voltage) {
        shooterLeft.setVoltage(voltage);
        shooterRight.setVoltage(-voltage);
    }

    public double getRPM() {
        return enc.getVelocity();
    }
}
