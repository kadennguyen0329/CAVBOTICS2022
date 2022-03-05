package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import frc.robot.Constants;

public class SimpleHood {
    private CANSparkMax hoodLeft; // left motor from the back
    private CANSparkMax hoodRight; // right motor from the back
    private RelativeEncoder encoderLeft;
    private RelativeEncoder encoderRight;
    private SparkMaxPIDController pidLeft;
    private SparkMaxPIDController pidRight;
    private double conversion;

    // The rotations of the motor per one length of the hood multiplied by the
    // native units of the encoder
    private final double hoodLength = 5.8;
    private final double hoodRange = 35;

    public SimpleHood(int CanID1) {
        hoodLeft = new CANSparkMax(CanID1, MotorType.kBrushless);
        //hoodRight = new CANSparkMax(CanID2, MotorType.kBrushless);
        encoderLeft = hoodLeft.getEncoder();
        //encoderRight = hoodRight.getEncoder();
        pidLeft = hoodLeft.getPIDController();
        //pidRight = hoodRight.getPIDController();
        pidLeft.setOutputRange(-0.30, 0.30);
        //pidRight.setOutputRange(-0.30, 0.30);
        pidLeft.setP(0.17);
        conversion = hoodRange / hoodLength;
    }

    public double getHoodAngle() {
        return encoderLeft.getPosition() * conversion + 10;
    }

    // debug helper
    public double getRawUnits() {
        return encoderRight.getPosition();
    }

    // sets the hood to a desired angle
    public void setHoodAngle(double angle) {
        pidLeft.setReference((angle - 10) / conversion, com.revrobotics.CANSparkMax.ControlType.kPosition);
        // the right motor turns in the opposite direction as the left, hence, I negated
        // the reference point below
        //pidRight.setReference(((angle - 10) / conversion) * -1, com.revrobotics.CANSparkMax.ControlType.kPosition);
    }

    public void hoodReset() {
        encoderLeft.setPosition(0);
        //encoderRight.setPosition(0);
    }

}