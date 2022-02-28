package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Index extends SubsystemBase {
   private CANSparkMax innerIndex; 

   public Index(){
       innerIndex = new CANSparkMax(Constants.innerIndexPort, MotorType.kBrushless);
   }

   public void spinInnerIndex(){
       innerIndex.set(.2);
   }

   public void stopInnerIndex(){
       innerIndex.set(0);
   }


}
