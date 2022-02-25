package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Flywheel extends SubsystemBase{
    private CANSparkMax rightMotor;
    private CANSparkMax leftMotor;
    private RelativeEncoder encoder;
    private int desiredRPM; 

    public Flywheel(int v)
    {
        rightMotor = new CANSparkMax(Constants.flyWheelPort, MotorType.kBrushless);
        leftMotor = new CANSparkMax(Constants.flyWheelPort2, MotorType.kBrushless);
        desiredRPM = v;
    }

    public void start()
    {
        rightMotor.set(0.8);
        leftMotor.set(0.8);
     }
    
      public void stop()
      {
        rightMotor.disable();
        leftMotor.disable();
      }

      public boolean fullSpeed(){
        encoder = rightMotor.getEncoder();

        if(encoder.getVelocity() > desiredRPM){
          return true;
        }
        return false;
      }
}