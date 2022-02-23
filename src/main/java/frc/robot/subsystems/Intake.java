package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    private CANSparkMax innerIntake;
    private CANSparkMax outerIntake;
    private Compressor compressor;
    private DoubleSolenoid mainSolenoid;
    
    public Intake(){
    innerIntake = new CANSparkMax(Constants.intakePort,MotorType.kBrushless);
    outerIntake = new CANSparkMax(Constants.intakePort2, MotorType.kBrushless);
    mainSolenoid = new DoubleSolenoid(10, PneumaticsModuleType.CTREPCM, Constants.mainSolenoid1, Constants.mainSolenoid2);
    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    compressor.enableDigital();
    }

    public void startInner(){
        innerIntake.set(0.20);
    }
    public void startOuter(){
        outerIntake.set(0.20);
    }

    public void stopInner(){
        innerIntake.set(0.0);
    }

    public void stopOuter(){
        outerIntake.set(0);
    }

    public void extend() {
        mainSolenoid.toggle();
    }

    public void retract()
    {
        mainSolenoid.toggle();
    }
    
}
