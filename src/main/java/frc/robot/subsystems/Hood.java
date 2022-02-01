package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Hood extends SubsystemBase{
    private CANSparkMax hoodSpark;
    private CANSparkMax hoodSpark2;

    public Hood(){
        hoodSpark = new CANSparkMax(0, MotorType.kBrushless);
        hoodSpark2 = new CANSparkMax(1, MotorType.kBrushless);

    }
    
    public void start(){
        hoodSpark.set(0.20);
        hoodSpark2.set(0.20);
    }

    public void stop(){
        hoodSpark.set(0.0);
        hoodSpark.set(0.0);
    }


}
