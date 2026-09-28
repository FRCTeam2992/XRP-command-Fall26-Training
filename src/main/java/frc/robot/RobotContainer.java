// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.XRPDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XRPDrivetrain m_xrpDrivetrain = new XRPDrivetrain();
  private final Arm m_arm = new Arm();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_xrpDrivetrain);

  private final CommandXboxController m_Controller = new CommandXboxController(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_arm.setDefaultCommand(m_arm.moveArm(() -> axisToAngle(m_Controller.getRightY())));
    m_xrpDrivetrain.setDefaultCommand(m_xrpDrivetrain.arcadeDriveCommand(
        () -> -m_Controller.getLeftY(), m_Controller::getRightX));
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_Controller.a().whileTrue(m_arm.resetArm());

    m_Controller.b().onTrue(m_arm.moveArm(45.0).withTimeout(1.0));

    m_Controller.y().toggleOnTrue(m_arm.moveArm(180.0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  // Convert Joystick to Arm Angle
  public double axisToAngle(double axis) {
    return (axis + 1.0) * 90.0;
  }
}
