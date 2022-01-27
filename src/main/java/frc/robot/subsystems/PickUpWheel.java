package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class PickUpWheel extends SubsystemBase{
    private CANSparkMax pickUpSpark;

    public PickUpWheel(){
        pickUpSpark = new CANSparkMax(1, MotorType.kBrushless)    
    }

    public void setPower(double power){
        pickUpSpark.set(power);
    }
}