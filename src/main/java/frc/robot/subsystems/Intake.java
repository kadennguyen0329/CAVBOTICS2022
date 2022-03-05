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
        mainSolenoid = new DoubleSolenoid(10, PneumaticsModuleType.CTREPCM, Constants.mainSolenoidOne,
                Constants.mainSolenoidTwo);
        compressor = new Compressor(PneumaticsModuleType.CTREPCM);
        compressor.enableDigital();
    }

    public void spinIntake() {
        intake.set(.20);
    }

    public void extend() {
        compressor.enableDigital();
        mainSolenoid.toggle();
    }

    public void retract() {
        compressor.disable();
        mainSolenoid.toggle();
    }

    public void stopIntake() {
        intake.set(0);
    }

}
