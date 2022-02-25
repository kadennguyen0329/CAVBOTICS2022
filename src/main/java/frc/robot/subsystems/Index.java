package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Index extends SubsystemBase {
   private CANSparkMax outerIndex;
   private CANSparkMax innerIndex; 

   public Index(){
       innerIndex = new CANSparkMax(Constants.innerIndexPort, MotorType.kBrushless);
       outerIndex = new CANSparkMax(Constants.outerIndexPort, MotorType.kBrushless);


   }

   public void spinOuterIndex(){
       outerIndex.set(.5);
   }

   public void spinInnerIndex(){
       innerIndex.set(.5);
   }

   public void stopOuterIndex(){
       outerIndex.set(0.0);
   }

   public void stopInnerIndex(){
       innerIndex.set(0);
   }


}
