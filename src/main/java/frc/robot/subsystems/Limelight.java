package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Constants;


public class Limelight extends SubsystemBase{

    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-sam");
    private final NetworkTableEntry pipeline = table.getEntry("pipeline");
    private final NetworkTableEntry xOffset = table.getEntry("tx");
    private final NetworkTableEntry yOffset = table.getEntry("ty");
    private final NetworkTableEntry angleOffset = table.getEntry("ts");
    private PIDController turnController;

    public LimeLight()
    {
        pipeline.setNumber(1);
        
    }


    public double getXOffset(){
        return xOffset.getDouble(-999);
    }

    public double getYOffset(){
        return yOffset.getDouble(-999);
    }

    public double getAngleOffset(){
        return angleOffset.getDouble(-999);
    }

    
}
