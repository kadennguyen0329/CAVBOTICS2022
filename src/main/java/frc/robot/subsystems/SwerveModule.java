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

    public SwerveModule(int turnComponent, int driveComponent, int encoderPort){

        turn = new CANSparkMax(turnComponent, MotorType.kBrushless);
        drive = new CANSparkMax(driveComponent, MotorType.kBrushless);
        enc = new AnalogEncoder(encoderPort);

        cont = new PIDController(0.004, 0, 0);
        cont.setTolerance(1);
        cont.enableContinuousInput(-180, 180);


    }

    //convert current module position into 0 to 360
    public double getAngle(){
       return enc.getBigAngle();
    }

    public double convertedAngle()
    {
        double temp = enc.getBigAngle();
        if(temp > 180) temp = -(360 - temp);
        
        return temp;
    }

    public void update()
    {
        enc.update();
    }
    

    //method to set the module angle and drive speed
    public void setModule(Rotation2d angle, double speed){
        
        if(turn.getDeviceId() == 3) 
        {
            double output = -1 * cont.calculate(convertedAngle(), angle.getDegrees());
          
            if(output >= 0)
            {
               turn.set(Math.min(0.05, output));
            }
            else if(output < 0)
            {
                turn.set(Math.max(-0.05, output));
            }
         
            SmartDashboard.putNumber("pid", output);
            SmartDashboard.putNumber("currentPosition", this.convertedAngle());
        }
        //turn.set(cont.calculate(output(), angle.getDegrees()));
        //drive.set(speed);
    }

}
