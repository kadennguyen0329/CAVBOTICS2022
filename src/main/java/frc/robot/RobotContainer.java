// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ExtendClimberCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.robot.subsystems.Flywheel;
// import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
// import frc.robot.subsystems.Limelight;
// import frc.robot.subsystems.PhotonVision;
// import frc.robot.subsystems.PickUpWheel;
// import frc.robot.subsystems.Sensor;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.InnerIndexCommand;
import frc.robot.commands.OuterIndexCommand;
import frc.robot.commands.RetractClimberCommand;
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.InnerIndex;
// import frc.robot.commands.PhotonVisionCommand;
// import frc.robot.commands.PickUpWheelCommand;
// import frc.robot.commands.TurnOnFlywheel;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Climber;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {
   /* new JoystickButton(Robot.controller, 4).whenPressed(new PickUpWheelCommand(pickUpWheel));
    new JoystickButton(Robot.controller, 3).whenPressed(new IntakeCommand(intake, ultrasonics));
    new JoystickButton(Robot.controller, 1).whenPressed(new IndexCommand(index));*/

    // Y button shoot
    new JoystickButton(Robot.controller, XboxController.Button.kY.value).toggleWhenPressed(new ShootCommand());
    // B button intake
    new JoystickButton(Robot.controller, XboxController.Button.kB.value).toggleWhenPressed(new IntakeCommand());
    // X button inner index
    new JoystickButton(Robot.controller, XboxController.Button.kX.value).toggleWhenPressed(new InnerIndexCommand());
    // Start button outer intake 
    new JoystickButton(Robot.controller, XboxController.Button.kStart.value).toggleWhenPressed(new OuterIndexCommand());
    //Right bumper Extend Climber
    //new JoystickButton(Robot.controller, XboxController.Button.kRightBumper.value).toggleWhenPressed(new ExtendClimberCommand(climber));
    //Left bumper Retract Climber
    //new JoystickButton(Robot.controller, XboxController.Button.kLeftBumper.value).toggleWhenPressed(new RetractClimberCommand(climber));


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
