package frc.robot.subsystems;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.classes.AnalogEncoder;

public class SwerveModule {

    private CANSparkMax turn;
    private CANSparkMax drive;
    private AnalogEncoder enc;
    private PIDController cont;
    private Rotation2d currentAngle;

    public SwerveModule(int turnComponent, int driveComponent, int encoderPort) {

        turn = new CANSparkMax(turnComponent, MotorType.kBrushless);
        drive = new CANSparkMax(driveComponent, MotorType.kBrushless);
        enc = new AnalogEncoder(encoderPort);
        enc.reset();
        // turn.enableVoltageCompensation(12);
        // drive.enableVoltageCompensation(12);

        // turn.setOpenLoopRampRate(0.75);
        // drive.setOpenLoopRampRate(0.75);
        // turn.setClosedLoopRampRate(0.75);
        // drive.setClosedLoopRampRate(0.75);
        currentAngle = new Rotation2d(Math.PI/ 2 * -1);
        cont = new PIDController(0.004, 0, 0);
        cont.setTolerance(2);
        cont.enableContinuousInput(-180, 180);

    }

    // convert current module position into 0 to 360
    public double getAngle() {
        return enc.getBigAngle();
    }

    public double convertedAngle() {
        double temp = enc.getBigAngle();
        if (temp > 180)
            temp = -(360 - temp);

        return temp;
    }

    public void update() {
        enc.update();
    }

    // method to set the module angle and drive speed
    public void setModule(Rotation2d angle, double speed) {

        double output = -1 * cont.calculate(convertedAngle(), angle.getDegrees());

        // if (output >= 0) {
        //     turn.set(Math.min(0.10, output));
        // } else if (output < 0) {
        //     turn.set(Math.max(-0.10, output));
        // }

        turn.set(output);
        drive.set(speed);

        //  drive.set(speed);
    }

}
