// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import com.revrobotics.*;

public class SwerveDriveTrain{

    public SwerveModuleState[] moduleState;
    public ChassisSpeeds speed;
    private SwerveDriveKinematics kin;
    private SwerveModule mod1;
    private SwerveModule mod2;
    private SwerveModule mod3;
    private SwerveModule mod4;
    private final double MAX_SPEED;
    private final double MAX_RADIANS;
    
    public SwerveDriveTrain(double distanceFromOrigin, int turn1, int turn2, int turn3, int turn4, int drive1, int drive2, int drive3, int drive4){
        
        Translation2d module1 = new Translation2d(distanceFromOrigin, distanceFromOrigin);
        Translation2d module2 = new Translation2d(-distanceFromOrigin, distanceFromOrigin);
        Translation2d module3 = new Translation2d(-distanceFromOrigin, -distanceFromOrigin);
        Translation2d module4 = new Translation2d(distanceFromOrigin, -distanceFromOrigin);
        MAX_SPEED = 3;
        MAX_RADIANS = 3.14;
        kin = new SwerveDriveKinematics(module1, module2, module3, module4);
        moduleState = new SwerveModuleState[4];
        speed = new ChassisSpeeds(0,0,0);
        mod1 = new SwerveModule(turn1, drive1);
        mod2 = new SwerveModule(turn2, drive2);
        mod3 = new SwerveModule(turn3, drive3);
        mod4 = new SwerveModule(turn4, drive1);
    }


    public void updatePeriodic(double translateX, double translateY, double yaw){
        
        speed.vxMetersPerSecond = translateX * MAX_SPEED;
        speed.vyMetersPerSecond = translateY * MAX_SPEED;
        speed.omegaRadiansP erSecond = yaw * MAX_RADIANS;

        moduleState = kin.toSwerveModuleStates(speed);

        var optimized1 = SwerveModuleState.optimize(moduleState[0], mod1.currentAngle);
        var optimized2 = SwerveModuleState.optimize(moduleState[1], mod2.currentAngle);
        var optimized3 = SwerveModuleState.optimize(moduleState[2], mod3.currentAngle);
        var optimized4 = SwerveModuleState.optimize(moduleState[3], mod4.currentAngle);

        System.out.println("Current Angle: " + mod1.getAngle() + " // Desired Angle: " + optimized1.angle.getDegrees());
        System.out.println();
    }
}
