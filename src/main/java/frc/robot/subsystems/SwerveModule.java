package frc.robot.subsystems;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.classes.AnalogEncoder;

public class SwerveModule {

    private CANSparkMax turn;
    private CANSparkMax drive;
    public Rotation2d currentAngle;
    private final double MAX_SPEED;
    private AnalogEncoder encoder;
    private PIDController cont;
    private int port;
    private double currentSpeed;

    public SwerveModule(int turnComponent, int driveComponent, int encoderPort) {

        turn = new CANSparkMax(turnComponent, MotorType.kBrushless);
        drive = new CANSparkMax(driveComponent, MotorType.kBrushless);
        encoder = new AnalogEncoder(encoderPort);
        encoder.reset();
        port = encoderPort;
        currentSpeed = 0;
        // encoder will output 0 <= x <= 360, instantiating it to 90 to match WPILIB's
        // Swerve Kinematics methods
        MAX_SPEED = 8;
        currentAngle = new Rotation2d(Math.PI / 2 * -1);
        // set the current angle to -90 to match swerve kinematics
        cont = new PIDController(0.004, 0.00002, 0.00001);
        cont.enableContinuousInput(-180, 180); // need to change this to correspond
        if (turnComponent == 6) {
            drive.setInverted(true);
        }
        if (turnComponent == 4) {
            drive.setInverted(false);
        }

        if (turnComponent == 5) {
            drive.setInverted(true);
        }

        if (turnComponent == 1) {
            drive.setInverted(false);
        }

    }

    // convert current module position into 0 to 360
    /*
     * public double newGet(){
     * double nativeUnits = 11.654; //the native units that equates to a full module
     * rotation
     * double tempAngle = encoder.getPosition();
     * if (Math.abs(encoder.getPosition()) > nativeUnits){ //if the current encoder
     * position is greater than one full rotation
     * tempAngle = tempAngle % nativeUnits;
     * }
     * 
     * tempAngle = tempAngle / nativeUnits;
     * tempAngle *= 360;
     * 
     * tempAngle *= -1;
     * 
     * 
     * System.out.println("newGet() " + tempAngle);
     * return tempAngle;
     * }
     */

    // Gets the current radian value of the module using the current encoder value,
    // converts to swerve kinematics's 0 to -180, 180 to 0
    public double getAngle() {
        encoder.update();
        double temp = encoder.getBigAngle(); /// should be an angle between 0 and 360 for the full moduile
        // if (this.port == 1){
        // SmartDashboard.putNumber("big angle", temp);
        // }
        // first rotate the module angle by 90 degrees
        temp += 90;

        if (temp > 360) {
            temp = temp - 360;
        }

        // convert 0 to 360 to 0 to -180 to 180 to 0
        if (temp <= 180) {
            temp *= -1;
        } else {
            temp = 360 - temp;
        }
        return temp;
    }

    // debugging method, can set the module to a certain angle
    public void setSpecific(double angle) {
        double angleToRadian = Math.toRadians(angle);
        this.setModule(new Rotation2d(angleToRadian), 0);
    }

    // keeps track of the current angle in radians, rotation2d object
    public void setCurrentAngle() {
        double temp = Math.toRadians(getAngle());
        currentAngle = new Rotation2d(temp);
    }

    // method to set the module angle and drive speed
    public void setModule(Rotation2d angle, double speed) {

        // if (Math.abs(speed) <= 0.002){
        //     speed = currentSpeed-0.008;
        // }
        double setPoint = cont.calculate(getAngle(), angle.getDegrees());
        if (setPoint < 0) {
            // 0.17
            turn.set(Math.max(setPoint, -0.17));
        } else {
            // 0.17 on normal surface
            turn.set(Math.min(0.17, setPoint));
        }
        drive.set(speed / MAX_SPEED);
        this.setCurrentAngle();
        // this.currentSpeed = speed;
    }
}
