package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic extends SubsystemBase{
    private AnalogPotentiometer pot;

    public Ultrasonic(){
        pot = new AnalogPotentiometer(new AnalogInput(0), 180, 30);
    }

    public double getDistance(){
        return pot.get();
    }
}

