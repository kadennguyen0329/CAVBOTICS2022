package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AutoAim {
    private NetworkTable table;
    private NetworkTableEntry pipeline;
    private PIDController pid;

    public AutoAim() {
        table = NetworkTableInstance.getDefault().getTable("limelight-sam");
        pipeline = table.getEntry("pipeline");
        pid = new PIDController(0.004, 0, 0);
        pid.setTolerance(3);
    }

    public double getXOffset() {
        return table.getEntry("tx").getDouble(0);
    }

    public double getYOffset() {
        return table.getEntry("ty").getDouble(0);
    }

    public int hasTarget() {
        return (int) table.getEntry("tv").getDouble(0);
    }

    public double getXDistance() {
        // limelight is fixed at 30 degrees
        double realAngle = 21 + getYOffset();
        return 7.4 / Math.tan(Math.toRadians(realAngle));
    }

    public double setHoodAim() {
        double distance = getXDistance();
        // this is some random correlation, 5 feet away equals hood at 10 degrees, 10
        // feet away equals 20 degrees, etc
        return Math.abs(distance * 2);
    }

    public double testDistance(){
        double distance = getXDistance();
        return distance;
    }
    public double getSpeed() {
        // Don't move the drivetrain if it's already within the tolerance
        return pid.calculate(this.getXOffset());
    }

    public double getRPM() {
        double baseRPM = 1500;
        double distance = getXDistance();
        return baseRPM + (distance * 50);
    }
}