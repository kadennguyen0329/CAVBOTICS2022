package frc.robot.subsystems;
 
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
 
public class AutoAim {
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-sam");
    private final NetworkTableEntry pipeline = table.getEntry("pipeline");
 
    public AutoAim(){
       
    }
 
    public double getXOffset(){
        return table.getEntry("tx").getDouble(0);
    }
 
    public double getYOffset(){
        return table.getEntry("ty").getDouble(0);
    }
 
    public int hasTarget(){
        return (int)table.getEntry("tv").getDouble(0);
    }
    public double[] aimPeriodic(){
        double[] values = {0,0,0};
        if (hasTarget() == 0){
            System.out.println("Warning, no target in sight");
            return values;
        }
        //first move the drivetrain so that x offset is 0
        double distance = 8 / Math.tan(30);
        values[2] = setDriveTrainAim(getXOffset());
        // setHoodAim(getYOffset(), distance);
        return values;
 
    }
 
    public void setHoodAim(double yOffset, double distance){
        // double absoluteAngle =
        // double sendAngle = yOffset +
    }
 
    public double setDriveTrainAim(double xOffSet){
        //Don't move the drivetrain if it's already within the tolerance
        if (Math.abs(xOffSet) <= 3){
            return 0;
        }
 
        if (xOffSet < 0){
        //if x offset is negative, then we need to turn the drive train to the right
            return -0.3;
        }
        else{
            return 0.3;
        }
    }
}
 
 

