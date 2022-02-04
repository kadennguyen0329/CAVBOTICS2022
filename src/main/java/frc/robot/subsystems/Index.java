package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Index extends SubsystemBase {
   private CANSparkMax index;

   public Index(){
       index = new CANSparkMax(0, MotorType.kBrushless);

   }

   public void start(){
       index.set(0.20);
   }

   public void stop(){
       index.set(0.0);
   }


}
