// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.swerveDrive;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.*;     
import com.revrobotics.*;
import com.kauailabs.navx.frc.*;

public class SwerveDriveTrainFieldCentric extends SubsystemBase{

     public SwerveModuleState[] moduleState;
     public ChassisSpeeds speed;
     private SwerveDriveKinematics kin;
     public SwerveModule mod1;
     public SwerveModule mod2;
    public SwerveModule mod3;
    public SwerveModule mod4;
    private final double MAX_SPEED;
    private final double MAX_RADIANS;
    private AHRS gyro;

    
    public SwerveDriveTrainFieldCentric(double distanceFromOrigin, int turn1, int drive1, int turn2, int drive2, int turn3, int drive3, int turn4, int drive4){
        
        Translation2d module1 = new Translation2d(distanceFromOrigin, distanceFromOrigin);
       Translation2d module2 = new Translation2d(-distanceFromOrigin, distanceFromOrigin);
        Translation2d module3 = new Translation2d(-distanceFromOrigin, -distanceFromOrigin);
        Translation2d module4 = new Translation2d(distanceFromOrigin, -distanceFromOrigin);
        
        try {
            gyro = new AHRS(SPI.Port.kMXP); 
            gyro.reset();
        } catch (RuntimeException ex ) {
            System.out.println("--------------");
            System.out.println("NavX not plugged in");
            System.out.println("--------------");
        }

        MAX_SPEED = 3;
        MAX_RADIANS = 3.14;
        kin = new SwerveDriveKinematics(module1, module2, module3, module4);
        moduleState = new SwerveModuleState[4];
        speed = ChassisSpeeds.fromFieldRelativeSpeeds(0,0,0, Rotation2d.fromDegrees(0));
        mod1 = new SwerveModule(turn1, drive1, 1);
        mod2 = new SwerveModule(turn2, drive2, 2);
        mod3 = new SwerveModule(turn3, drive3, 3);
        mod4 = new SwerveModule(turn4, drive4, 4);
        mod1.reset();
       mod2.reset();
        mod3.reset();
        mod3.reset(); 
    
        setDefaultCommand(new swerveDrive(this));
    }


    public void updatePeriodic(double translateX, double translateY, double yaw){
        
        speed = ChassisSpeeds.fromFieldRelativeSpeeds(translateX * MAX_SPEED, translateY * MAX_SPEED * -1, yaw * MAX_RADIANS * -1, new Rotation2d(Math.toRadians(getGyroAngle())));

        moduleState = kin.toSwerveModuleStates(speed);

        var optimized1 = SwerveModuleState.optimize(moduleState[0], mod1.currentAngle);
        var optimized2 = SwerveModuleState.optimize(moduleState[1], mod2.currentAngle);
        var optimized3 = SwerveModuleState.optimize(moduleState[2], mod3.currentAngle);
        var optimized4 = SwerveModuleState.optimize(moduleState[3], mod4.currentAngle);

        //System.out.println("Current Angle: " + Math.toDegrees(mod1.currentAngle.getDegrees()));
        //mod1.setCurrentAngle();
        //System.out.println("Passing through these values, Angle: " + optimized1.angle.getDegrees() + " Speed: " + optimized1.speedMetersPerSecond + " Current Position: " + mod1.currentAngle.getDegrees());
        mod1.setModule(optimized1.angle, optimized1.speedMetersPerSecond);
       mod2.setModule(optimized2.angle, optimized2.speedMetersPerSecond);
       mod3.setModule(optimized3.angle, optimized3.speedMetersPerSecond);
        mod4.setModule(optimized4.angle, optimized4.speedMetersPerSecond);

    }

    public double getGyroAngle(){
        double angle = -gyro.getAngle() % 360.0;
        if(angle < 0){
            angle = 360 + angle;
        }
        return angle;
    }
}
