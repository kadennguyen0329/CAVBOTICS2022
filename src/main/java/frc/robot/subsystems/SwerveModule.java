package frc.robot.subsystems;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.classes.AnalogEncoder;


public class SwerveModule extends SubsystemBase {

    private CANSparkMax turn;
    private CANSparkMax drive;
    private RelativeEncoder enc;
    private RelativeEncoder driveEnc;
    private PIDController cont;
    public Rotation2d currentAngle;

    public SwerveModule(int turnComponent, int driveComponent, int encoderPort) {

        turn = new CANSparkMax(turnComponent, MotorType.kBrushless);
        drive = new CANSparkMax(driveComponent, MotorType.kBrushless);
        enc = turn.getEncoder();
        enc.setPositionConversionFactor(1);
        driveEnc = drive.getEncoder();
        driveEnc.setPositionConversionFactor(1);
        // enc = new AnalogEncoder(encoderPort);
        // enc.reset();
        // turn.enableVoltageCompensation(12);
        // drive.enableVoltageCompensation(12);

        //turn.setOpenLoopRampRate(0);
        // drive.setOpenLoopRampRate(0.75);
        //turn.setClosedLoopRampRate(0);
        // drive.setClosedLoopRampRate(0.75);
        currentAngle = new Rotation2d(Math.PI/ 2 * -1);
        //ont = new PIDController(0.9, 0.00, 0);
        cont = new PIDController(0.004 , 0, 0.00001);
        cont.enableContinuousInput(-180, 180);

    }

    public void stop(){
        turn.set(0);
        drive.set(0);
    }

    public double newGet(){
        double nativeUnits = 11.654; //the native units that equates to a full module rotation
        double tempAngle = enc.getPosition();
        if (Math.abs(enc.getPosition()) > nativeUnits){ //if the current encoder position is greater than one full rotation
            tempAngle = tempAngle % nativeUnits;
        }

        tempAngle = tempAngle / nativeUnits;
        tempAngle *= 360;

        tempAngle *= -1;

        
        //System.out.println("newGet() " + tempAngle);
        return tempAngle;
    }

    public double getAngle(){
        double temp = this.newGet(); ///should be an angle between 0 and 360 for the full moduile
        
        //first rotate the module angle by 90 degrees
        temp += 90;

        if (temp > 360){
            temp = temp - 360;
        }

        //convert 0 to 360 to 0 to -180 to 180 to 0
        if (temp <= 180){
            temp *= -1;
        }
        else{
            temp = 360 - temp;
        }

        //System.out.println("getAngle() " + temp);
        return temp;
    }

    public void setCurrentAngle(){
        double temp = Math.toRadians(getAngle());
        currentAngle = new Rotation2d(temp);
    }
 
    // method to set the module angle and drive speed
    public void setModule(Rotation2d angle, double speed) {

            double setPoint = cont.calculate(getAngle(), angle.getDegrees());
            if (setPoint < 0){
                turn.set(Math.max(setPoint, -0.17));
            } else{
                turn.set(Math.min(0.17, setPoint));
            }
            drive.set(speed / 4);
            this.setCurrentAngle();
    }
    public void reset(){
        enc.setPosition(0);
        driveEnc.setPosition(0);
    }

    public double getDrive(){
        double wheelCircumference = Math.PI * 4;
        double wheelRotations = (driveEnc.getPosition() / 2.58) /  2.5;
        return wheelCircumference * wheelRotations;
    }

    public void driveRest(){
        driveEnc.setPosition(0);
    }
}
