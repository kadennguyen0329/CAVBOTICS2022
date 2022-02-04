package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
import java.util.List;
import org.photonvision.PhotonUtils;

public class PhotonVision extends SubsystemBase{
    private PhotonCamera camera;
    public PhotonVision(){
        camera = new PhotonCamera("photonvision");
        

    }
    public double xOffset(){
        var result = camera.getLatestResult();
        List<PhotonTrackedTarget> targets = result.getTargets();
        PhotonTrackedTarget target = result.getBestTarget();
        return target.getYaw();
    }
    public double yOffset(){
        var result = camera.getLatestResult();
        List<PhotonTrackedTarget> targets = result.getTargets();
        PhotonTrackedTarget target = result.getBestTarget();
        return target.getPitch();
    }
    public double distanceFromTarget(){
        var result = camera.getLatestResult();
        ///return PhotonUtils.calculateDistanceToTargetMeters(CAMERA_HEIGHT_METERS,TARGET_HEIGHT_METERS,CAMERA_PITCH_RADIANS,Math.toRadians(result.getBestTarget().getPitch()));
        return 0.0;
    }

}
