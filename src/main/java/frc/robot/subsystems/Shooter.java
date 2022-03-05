package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.SparkMaxPIDController;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private CANSparkMax shooterLeft;
    private CANSparkMax shooterRight;
    private SparkMaxPIDController pidLeft;
    private SparkMaxPIDController pidRight;

    public Shooter() {
        shooterLeft = new CANSparkMax(Constants.shooterIdOne, MotorType.kBrushless);
        shooterRight = new CANSparkMax(Constants.shooterIdTwo, MotorType.kBrushless);
        pidLeft = shooterLeft.getPIDController();
        pidRight = shooterRight.getPIDController();
        pidLeft.setOutputRange(-1, 1);
        pidRight.setOutputRange(-1, 1);
        pidLeft.setP(0.1);
        pidRight.setP(0.1);
    }

    public void setWheel(double RPM) {
        shooterLeft.set(RPM);
        shooterRight.set(-RPM);
    }

    public boolean isDesiredRPM(double d){
        return (shooterLeft.getEncoder().getVelocity() > d);

    }
}
