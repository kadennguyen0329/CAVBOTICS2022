package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Sensor extends SubsystemBase{
    private Ultrasonic ultrasonic;
    private Ultrasonic ultrasonic2;
    
    public Sensor(){
        ultrasonic = new Ultrasonic(0,1);
        ultrasonic2 = new Ultrasonic(2,3);
        Ultrasonic.setAutomaticMode(true);
    }

    public boolean innerIsClosed(){
        if(ultrasonic.getRangeInches()<3){
            return true;
        }
        return false;
    }
    
    public boolean outerIsClosed(){
        if(ultrasonic2.getRangeInches()<3){
            return true;
        }
        return false;

    }
    

    

}

