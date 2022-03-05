package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Hood extends SubsystemBase{
    private CANSparkMax hoodSpark;
    private CANSparkMax hoodSpark2;

    public Hood(){
        hoodSpark = new CANSparkMax(Constants.hoodIdOne, MotorType.kBrushless);
        hoodSpark2 = new CANSparkMax(Constants.hoodIdTwo, MotorType.kBrushless);

    }
    
    public void set(double p){
        hoodSpark.set(p);
        hoodSpark2.set(p);
    }

    public void stop(){
        hoodSpark.set(0.0);
        hoodSpark.set(0.0);
    }


}
