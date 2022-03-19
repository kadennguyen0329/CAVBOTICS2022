// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.simulation.XboxControllerSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.*;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {


  public static Intake intake;
  public static Shooter shooter;
  public static InnerIndex innerIndex;
  public static OuterIndex outerIndex;
  public static Climber climber;
  public static Hood hood;
  public static Limelight limelight;
  public static SwerveDrive swerveDrive;
  public static XboxController controller;
  public static XboxController controller_2;
  public static XboxController swerveController;
  public static SwerveCommand swerveCommand;
  


  public RobotContainer() {

    controller = new XboxController(0);
    swerveController = new XboxController(1);
    intake = new Intake();
    shooter = new Shooter();
    innerIndex = new InnerIndex();
    outerIndex = new OuterIndex();
    climber = new Climber();
    hood = new Hood();
    limelight = new Limelight();
    swerveDrive = new SwerveDrive(0.413);
    intake.retract();
    
    configureButtonBindings();
  }

  private void configureButtonBindings() {

  
    // // Y button shoot
   new JoystickButton(controller, XboxController.Button.kY.value).toggleWhenPressed(new ShootCommand());
    // // B button intake
     new JoystickButton(controller, XboxController.Button.kX.value).toggleWhenPressed(new IntakeCommand());
    // // X button inner index
     new JoystickButton(controller, XboxController.Button.kB.value).toggleWhenPressed(new InnerIndexCommand());
     // A button kick out ball
     new JoystickButton(controller, XboxController.Button.kBack.value).toggleWhenPressed(new KickOutBallsCommand());
    // // A button outer intake 
     new JoystickButton(controller, XboxController.Button.kA.value).toggleWhenPressed(new OuterIndexCommand());
    // //Left bumper Extend Climber swerve controller
    new JoystickButton(controller, XboxController.Button.kLeftBumper.value).whenHeld(new ExtendClimberCommand());
    new JoystickButton(controller, XboxController.Button.kRightBumper.value).whenHeld(new RetractClimberCommand());
    //left bumper decline hood
    new JoystickButton(controller, XboxController.Axis.kRightTrigger.value).whenHeld(new DeclineHoodCommand());
    //right bumper raise hood
    new JoystickButton(controller, XboxController.Axis.kLeftTrigger.value).whenHeld(new RaiseHoodCommand());
    
    //start button, start swerve
    new JoystickButton(controller, XboxController.Button.kStart.value).toggleWhenPressed(new SwerveCommand());

   


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //System.out.println("Runing command");
    switch((int)NetworkTableInstance.getDefault().getTable("/datatable").getEntry("routine").getNumber(0)){
      case 1:
      return new OneBallAuto(limelight, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
      case 2:
      return new Auto1(limelight, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
      case 3:
      return new Auto2(limelight, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
      default:
      return new DoNothingCommand();


    
    
    }
  }
}
