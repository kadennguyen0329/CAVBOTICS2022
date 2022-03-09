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
    private double power;
    private int step;

    

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
        step = 0;

        addRequirements(autoaim, climber, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        
        switch(step){
            //aim
            case 0:
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
                  step = 1;
              }
              
            break;
              //shoot
            case 1:
              shooter.setWheel(power);
                if (power >= 0.6) {
                innerIndex.spin();
                outerIndex.spin();
                    if(timer.get() >= 2){
                    step = 2;
                    shooter.setWheel(0);
                    innerIndex.stop();
                    outerIndex.stop();
                    }
                }else{
                power += 0.009;
                timer.reset();
                }

            break;

                //turn around 180 degrees
            case 2:
                if(swerveDrive.getAngle() < 180.0){
                    swerveDrive.updatePeriodic(0, 0, 0.3);
                }else{
                    step = 3;
                }
            break;

            //turn on intake
            case 3:
                intake.extend();
                intake.spinIntake();
                outerIndex.spin();
                step = 4;
            break;

            //move forward several meters

            case 4:
                if((swerveDrive.getDriveDistance()/2.58) * 0.023876 > 4){
                    swerveDrive.updatePeriodic(0, 0, 0);
                    step = 5;
                }else{
                    swerveDrive.updatePeriodic(0, 0.3, 0);
                }

            break;

            
            //retract intake, turn off outer index

            case 5:
                intake.stopIntake();
                intake.retract();
                outerIndex.stop();
                step = 6;
            break;

            //turn around again
            case 6:
                if(swerveDrive.getAngle() < 360.0){
                    swerveDrive.updatePeriodic(0, 0, 0.3);
                }else{
                    swerveDrive.updatePeriodic(0,0,0);
                    step = 7;
                }
            break;

            //autoaim again 

            case 7:
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
                  step = 8;
                }

            break;


            //shoot again

            case 8:

                shooter.setWheel(power);
                if (power >= 0.6) {
                    innerIndex.spin();
                    outerIndex.spin();
                    if(timer.get() >= 2){
                        step = 9;
                        shooter.setWheel(0);
                        innerIndex.stop();
                        outerIndex.stop();
                    }
                }else{
                    power += 0.009;
                    timer.reset();
                }

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
