package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OuterIndex extends SubsystemBase{

    private CANSparkMax innerIndex;

    public OuterIndex(){
        innerIndex = new CANSparkMax(Constants.outerIndexId, MotorType.kBrushless);

    }

    public void spin(){
        innerIndex.set(-0.25);
    }

    public void stop(){
        innerIndex.set(0);
    }

    
}
