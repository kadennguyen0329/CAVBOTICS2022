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
    private CANSparkMax intake;
    private Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    private DoubleSolenoid leftSolenoid = new DoubleSolenoid(10, PneumaticsModuleType.CTREPCM, 0, 1);
    private DoubleSolenoid rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
    private DoubleSolenoid spindexerSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);

    public Intake(){
    intake = new CANSparkMax(0,MotorType.kBrushless);
    }

    public void start(){
        intake.set(0.20);
    }

    public void stop(){
        intake.set(0.0);
    }

    public void extend() {
        leftSolenoid.toggle();
        rightSolenoid.toggle();
    }

    public void retract()
    {
        leftSolenoid.toggle();
        rightSolenoid.toggle();
    }
    
}