package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private CANSparkMax intake;
    private Compressor compressor;
    private DoubleSolenoid mainSolenoid;

    public Intake() {
        intake = new CANSparkMax(Constants.intakeId, MotorType.kBrushless);
        mainSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.mainSolenoidOne, Constants.mainSolenoidTwo);
        compressor = new Compressor(PneumaticsModuleType.REVPH);
        compressor.enableDigital();
        System.out.println(getValue());
        System.out.println(getValue());
        System.out.println(getValue());
        System.out.println(getValue());
    }

    public void spinIntake() {
        intake.set(-.35);
    }

    public void extend() {
        //mainSolenoid.toggle();
        mainSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void retract() {
        //compressor.disable();
        //mainSolenoid.toggle();
        mainSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void stopIntake() {
        intake.set(0);
    }

    public DoubleSolenoid.Value getValue(){
        return mainSolenoid.get();
    }

}
