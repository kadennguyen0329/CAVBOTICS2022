package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Index extends SubsystemBase{
    private CANSparkMax motor1;
    private CANSparkMax motor2;

    public Index()
    {
        motor1 = new CANSparkMax(0, MotorType.kBrushless);
        motor2 = new CANSparkMax(0, MotorType.kBrushless);
    }

    public void start()
    {
        motor1.set(0.25);
        motor2.set(0.25);
     }
    
      public void stop()
      {
        motor1.disable();
        motor2.disable();
      }
}