package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Ultrasonic;

public class UltrasonicCommand extends CommandBase{
    private final Ultrasonic megaultrasonic;

    public UltrasonicCommand(Ultrasonic ultrasonic){
        megaultrasonic = ultrasonic;
        addRequirements(megaultrasonic);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        System.out.print(megaultrasonic.getDistance());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
    
}
