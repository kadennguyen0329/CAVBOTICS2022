package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

import frc.robot.subsystems.*;

public class AutonomousCommand extends CommandBase {
    private AutoAim autoaim;
    private Climber climber;
    private Hood hood;
    private InnerIndex innerIndex;
    private Intake intake;
    private OuterIndex outerIndex;
    private Shooter shooter;
    private SwerveDriveTrain swerveDrive;
    private Timer timer;
    private boolean shot = false;
    private double power;
    private boolean aim = false;
    private boolean turned = false;
    private boolean intakeOn = false;
    private boolean moved = false;
    private boolean turnedAgain = false;
    private boolean retractedIntake = false;
    private boolean aim2 = false;
    private boolean shoot2 = false;

    

    public AutonomousCommand(AutoAim a, Climber c, Hood h, InnerIndex i, Intake in, OuterIndex o, Shooter s, SwerveDriveTrain swerve){
        autoaim = a;
        climber = c;
        hood = h;
        innerIndex = i;
        intake = in;
        outerIndex = o;
        shooter = s;
        swerveDrive = swerve;
        timer = new Timer();
        power = 0;

        addRequirements(autoaim, climber, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        //aim
        if(!shot && !aim && !turned && !intakeOn && !moved){
            if (autoaim.hasTarget() == 1) {
                // while (aim.getXOffset() > 3) {
                //   double x = aim.getXOffset();
                //   if (x < 0) {
                //     swerveDriveTrain.updatePeriodic(0, 0, 0.35);
                //   } else {
                //     swerveDriveTrain.updatePeriodic(0, 0, -0.35);
                //   }
                // }
        
                double angle = autoaim.setHoodAim();
                // double angle = 15;
                hood.setHoodAngle(angle);
                SmartDashboard.putNumber("angle", angle);
                System.out.println("Setting to: " + angle);
                // if (Constants.controller.getBButton()) {
                //   double RPM = aim.getRPM();
                //   // wheel.setWheel(RPM);
                // }
              }else{
                  aim = true;
              }

        }

        //shooting
        if(!shot && aim && !turned && !intakeOn && !moved){
        shooter.setWheel(power);
        if (power >= 0.6) {
            innerIndex.spin();
            outerIndex.spin();
            if(timer.get() >= 2){
                shot = true;
                shooter.setWheel(0);
                innerIndex.stop();
                outerIndex.stop();
            }
        }else{
            power += 0.009;
            timer.reset();
             }
        }

        //turn around 180 degrees

        if(!turned && shot && aim && !intakeOn && !moved){
            if(swerveDrive.getAngle() < 180.0){
                swerveDrive.updatePeriodic(0, 0, 0.3);
            }else{
                turned = true;
            }
            //yaw is in radians per second

        }

        //turn on intake

        if(turned && shot && aim && !intakeOn && !moved){
            intake.extend();
            intake.spinIntake();
            outerIndex.spin();
            intakeOn = true;

        }

        //move forward several meters

        if(turned && shot && aim && intakeOn && !moved){
            if((swerveDrive.getDriveDistance()/2.58) * 0.023876 > 4){
                swerveDrive.updatePeriodic(0, 0, 0);
                moved = true;
            }else{
                swerveDrive.updatePeriodic(0, 0.3, 0);
            }
        }

        //retract intake, turn off outer index

        if(turned && shot && aim && intakeOn && moved && !retractedIntake){
            intake.stopIntake();
            intake.retract();
            outerIndex.stop();
            retractedIntake = true;
        }

        

        //turn around again 

        if(turned && shot && aim && intakeOn && moved && retractedIntake && !turnedAgain){
            if(swerveDrive.getAngle() < 360.0){
                swerveDrive.updatePeriodic(0, 0, 0.3);
            }else{
                turnedAgain = true;
            }
        }

        //autoaim

        if(shot && turned && aim && intakeOn && moved && retractedIntake && turnedAgain && !aim2){
            if (autoaim.hasTarget() == 1) {
                // while (aim.getXOffset() > 3) {
                //   double x = aim.getXOffset();
                //   if (x < 0) {
                //     swerveDriveTrain.updatePeriodic(0, 0, 0.35);
                //   } else {
                //     swerveDriveTrain.updatePeriodic(0, 0, -0.35);
                //   }
                // }
        
                double angle = autoaim.setHoodAim();
                // double angle = 15;
                hood.setHoodAngle(angle);
                SmartDashboard.putNumber("angle", angle);
                System.out.println("Setting to: " + angle);
                // if (Constants.controller.getBButton()) {
                //   double RPM = aim.getRPM();
                //   // wheel.setWheel(RPM);
                // }
              }else{
                  aim2 = true;
              }

        }

        //shoot again 

        if(shot && turned && aim && intakeOn && moved && retractedIntake && turnedAgain && aim2 && !shoot2){
            shooter.setWheel(power);
            if (power >= 0.6) {
                innerIndex.spin();
                outerIndex.spin();
                if(timer.get() >= 2){
                    shot = true;
                    shooter.setWheel(0);
                    innerIndex.stop();
                    outerIndex.stop();
                }
            }else{
                power += 0.009;
                timer.reset();
                 }
            }





        
          

    }

    @Override
    public void end(boolean interrupted){
        
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    
    
}
