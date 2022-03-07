package frc.robot.commands;

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
    

    public AutonomousCommand(AutoAim a, Climber c, Hood h, InnerIndex i, Intake in, OuterIndex o, Shooter s, SwerveDriveTrain swerve){
        autoaim = a;
        climber = c;
        hood = h;
        innerIndex = i;
        intake = in;
        outerIndex = o;
        shooter = s;
        swerveDrive = swerve;

        addRequirements(autoaim, climber, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
    }

    @Override
    public void initialize(){
        shooter.setWheel(0.6);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
