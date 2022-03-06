// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
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
import frc.robot.subsystems.OuterIndex;
import frc.robot.subsystems.InnerIndex;
// import frc.robot.commands.PhotonVisionCommand;
// import frc.robot.commands.PickUpWheelCommand;
// import frc.robot.commands.TurnOnFlywheel;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  // private final ExampleCommand m_autCommand = new ExampleCommand(m_exampleSubsystem);
  /*public final static Flywheel flywheel = new Flywheel();
  public final static Hood hood = new Hood(); */
  public final static Intake intake = new Intake();
  public final static Shooter shooter = new Shooter();
  public final static InnerIndex innerIndex = new InnerIndex();
  public final static OuterIndex outerIndex = new OuterIndex();
  /*public final static Limelight limelight = new Limelight();
  public final static PhotonVision photonvision = new PhotonVision();
  public final static PickUpWheel pickUpWheel = new PickUpWheel();

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
   /* new JoystickButton(Constants.controller, 4).whenPressed(new PickUpWheelCommand(pickUpWheel));
    new JoystickButton(Constants.controller, 3).whenPressed(new IntakeCommand(intake, ultrasonics));
    new JoystickButton(Constants.controller, 1).whenPressed(new IndexCommand(index));*/

    // Y button
    new JoystickButton(Constants.controller, XboxController.Button.kY.value).whenPressed(new ShootCommand(innerIndex, outerIndex, shooter, Constants.desiredRPM));
    // B button
    new JoystickButton(Constants.controller, XboxController.Button.kB.value).whenPressed(new IntakeCommand(intake));
    // X button
    new JoystickButton(Constants.controller, XboxController.Button.kX.value).toggleWhenPressed(new InnerIndexCommand(innerIndex));
    // A button
    new JoystickButton(Constants.controller, XboxController.Button.kStart.value).whenPressed(new OuterIndexCommand(outerIndex));
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}
