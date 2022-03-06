package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import frc.robot.Constants;

public class Hood extends SubsystemBase {
    private CANSparkMax hood; // left motor from the back
    private RelativeEncoder encoder;
    private SparkMaxPIDController pid;
    private double conversion;

    // The rotations of the motor per one length of the hood multiplied by the
    // native units of the encoder
    private final double hoodLength = 5.8;
    private final double hoodRange = 35;

    public Hood() {
        hood = new CANSparkMax(Constants.hoodId, MotorType.kBrushless);
        encoder = hood.getEncoder();
        pid = hood.getPIDController();
        pid.setOutputRange(-0.30, 0.30);
        pid.setP(0.3);
        conversion = hoodRange / hoodLength;
    }

    public double getHoodAngle() {
        return encoder.getPosition() * conversion + 10;
    }

    // debug helper
    public double getRawUnits() {
        return encoder.getPosition();
    }

    // sets the hood to a desired angle
    public void setHoodAngle(double angle) {
        pid.setReference((angle - 10) / conversion, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }

    public void hoodReset() {
        this.setHoodAngle(0);
        encoder.setPosition(0);
    }

}