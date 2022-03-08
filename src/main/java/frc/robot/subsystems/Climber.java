package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Climber extends SubsystemBase{
    private CANSparkMax leftArm;
    private CANSparkMax rightArm;

    public Climber(){
        leftArm = new CANSparkMax(Constants.leftArmID, MotorType.kBrushless);
        rightArm = new CANSparkMax(Constants.rightArmID, MotorType.kBrushless);
    }

    public void extend(){
        leftArm.set(0.05);
        rightArm.set(0.05);
    }
    public void retract(){
        leftArm.set(-0.05);
        rightArm.set(-0.05);
    }
    public void stop(){
        leftArm.set(0);
        rightArm.set(0);
    }



}
