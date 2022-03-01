package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Index extends SubsystemBase {
    private CANSparkMax innerIndex;
    private CANSparkMax outerIndex;

    public Index() {
        innerIndex = new CANSparkMax(Constants.innerIndexPort, MotorType.kBrushless);
        outerIndex = new CANSparkMax(Constants.outerIndexPort, MotorType.kBrushless);
    }

    public void spinInnerIndex() {
        innerIndex.set(.2);
    }

    public void stopInnerIndex() {
        innerIndex.set(0);
    }

    public void spinOuterIndex() {
        outerIndex.set(.2);
    }

    public void stopOuterIndex() {
        outerIndex.set(0);
    }

}
