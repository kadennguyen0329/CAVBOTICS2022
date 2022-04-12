package frc.robot.commands;
import java.lang.invoke.ConstantCallSite;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

import frc.robot.subsystems.*;

public class Auto2 extends CommandBase {

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

    public Auto2(Limelight a, Hood h, InnerIndex i, Intake in, OuterIndex o, Shooter s, SwerveDrive swerve){
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
        intake.extend();
        swerveDrive.resetGyro();
    }

    @Override
    public void execute(){

        switch(step){
            case 0:
                if (!Constants.intakeStatus){
                    intake.extend();
                    Constants.intakeStatus = true;
                }
                intake.spinIntake();
                outerIndex.spin();

                if (Math.abs(swerveDrive.getDriveDistance()) > 44 ){
                    swerveDrive.stopAll();
                    intake.retract();
                    Constants.intakeStatus = false;
                    step = 1;
                    System.out.println("Drove 4 feet");
                } else{
                    swerveDrive.updatePeriodic(0.018, 0.4, 0);
                }
        
            break;

            case 1:
                if (Math.abs(swerveDrive.getGyroAngle()) < 153 ){
                    swerveDrive.updatePeriodic(0, 0, 0.8);
                } else{
                    intake.stopIntake();
                    swerveDrive.stopAll();
                    swerveDrive.resetDrive();
                    outerIndex.stop();
                    step = 2;
                }   
            break;

            case 2:   
                hood.setHoodAngle(35);
                shooter.set(4);
                if(shooter.getRPM() > 1900) {
                    outerIndex.spin();
                    innerIndex.spin();
                    swerveDrive.resetDrive();
                    step = 3;
                }        
            break;

         
            case 3:
                if (Math.abs(swerveDrive.getDriveDistance()) < 4){
                    swerveDrive.updatePeriodic(0, -0.08, 0);
                } else{
                    swerveDrive.stopAll();
                    outerIndex.stop();
                    innerIndex.stop();
                    shooter.set(0);
                    step = 9;
                }    
            break;

            case 4:
                if (Math.abs(swerveDrive.getDriveDistance()) < 5){
                    swerveDrive.updatePeriodic(0, 0.08, 0);
                } else{
                    swerveDrive.stopAll();
                    hood.setHoodAngle(29);
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
                innerIndex.stop();
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
    }

    @Override
    public boolean isFinished(){
        return (step >= 9);
    }

    
    
}
