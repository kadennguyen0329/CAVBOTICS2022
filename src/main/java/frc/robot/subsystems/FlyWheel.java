package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class FlyWheel extends SubsystemBase{
    private CANSparkMax rightMotor;
    private CANSparkMax leftMotor;

    public FlyWheel()
    {
        rightMotor = new CANSparkMax(0, MotorType.kBrushless);
        leftMotor = new CANSparkMax(0, MotorType.kBrushless);
    }

    public void start(double shooterSpeed)
    {
        rightMotor.set(0.25);
        leftMotor.set(0.25);
     }
    
      public void stop()
      {
        rightMotor.disable();
        leftMotor.disable();
      }
}