package frc.robot.commands;

import java.lang.invoke.ConstantCallSite;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

import frc.robot.subsystems.*;

public class Auto1 extends CommandBase {
    private Limelight autoaim;
    private Hood hood;
    private InnerIndex innerIndex;
    private Intake intake;
    private OuterIndex outerIndex;
    private Shooter shooter;
    private SwerveDrive swerveDrive;
    private Timer timer;
    private double power;
    private int step;

    public Auto1(Limelight a, Hood h, InnerIndex i, Intake in, OuterIndex o, Shooter s, SwerveDrive swerve){
        autoaim = a;
        hood = h;
        innerIndex = i;
        intake = in;
        outerIndex = o;
        shooter = s;
        swerveDrive = swerve;
        timer = new Timer();
        power = 0;
        step = 0;
        addRequirements(autoaim, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
        swerveDrive.m_frontRightLocation.reset();
        swerveDrive.m_frontLeftLocation.reset();
        swerveDrive.m_backLeftLocation.reset();
        swerveDrive.m_backRightLocation.reset();
    }

    @Override
    public void initialize(){
        hood.hoodReset();
        intake.retract();
        //SmartDashboard.putNumber("Auto works", 1);
        //System.out.println("Wotks 2");
    }

    @Override
    public void execute(){
        
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto1").setBoolean(true);

        
        switch(step){

            case 0:
                swerveDrive.gyro.reset();
                hood.setHoodAngle(20);
                shooter.set(4.5);
                if(shooter.getRPM() > 1900) {
                    outerIndex.spin();
                    innerIndex.spin();
                    step = 1;
                    shooter.set(0);
                }
            break;


            //turn 180
            case 1:
                //System.out.println("gyro angle: " + swerveDrive.getGyroAngle());
                if (Math.abs(swerveDrive.getGyroAngle()) < 173){
                    swerveDrive.updatePeriodic(0, 0, 0.6);
                } else{
                    System.out.println("This casues stop: " + swerveDrive.getGyroAngle());
                    //swerveDrive.updatePeriodic(0, 0, 0);
                    swerveDrive.stopAll();
                    swerveDrive.resetDrive();
                    innerIndex.stop();
                    step = 2;
                }
            break;

                //turn around 180 degrees
            case 2:
                if (!Constants.intakeStatus){
                    intake.extend();
                    Constants.intakeStatus = true;
                }
                intake.spinIntake();
                outerIndex.spin();

                if (Math.abs(swerveDrive.getDriveDistance()) > 48 ){
                    swerveDrive.stopAll();
                    //swerveDrive.updatePeriodic(0, 0, 0);]
                    intake.retract();
                    intake.stopIntake();
                    //outerIndex.stop();
                    Constants.intakeStatus = false;
                    step = 3;
                    System.out.println("Drove 4 feet");
                } else{
                    swerveDrive.updatePeriodic(0, 0.2, 0);
                }
            break;

         
            case 3:
                if (Math.abs(swerveDrive.getGyroAngle()) > 13){
                    swerveDrive.updatePeriodic(0, 0, -0.6);
                } else{
                    swerveDrive.stopAll();
                    swerveDrive.resetDrive();
                    outerIndex.stop();
                    step = 4;
                }         
            break;

            //move forward several meters

            case 4:
                if (Math.abs(swerveDrive.getDriveDistance()) < 10){
                    swerveDrive.updatePeriodic(0, 0.15, 0);
                } else{
                    swerveDrive.stopAll();
                    hood.setHoodAngle(32);
                    shooter.set(5);
                    if(shooter.getRPM() > 2100) {
                        outerIndex.spin();
                        innerIndex.spin();
                        shooter.set(0);
                        step = 5;
                    }
                }
            break;

            case 5:
                outerIndex.stop();
                intake.stopIntake();
                step = 9;
            break;

            //turn around again
            case 6:
                
            break;

            //autoaim again 

            case 7:
                
            break;


            //shoot again

            case 8:

            break;

            default:
            step = 9;
            break;
        }
    }

    @Override
    public void end(boolean interrupted){
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto1").setBoolean(false);

    }

    @Override
    public boolean isFinished(){
        return (step >= 9);
    }

    
    
}
