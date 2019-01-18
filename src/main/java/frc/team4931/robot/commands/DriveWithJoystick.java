/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;
import frc.team4931.robot.subsystems.Drivetrain;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveWithJoystick extends Command {

 private Joystick joystick;
 private Drivetrain drivetrain;

  public DriveWithJoystick() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.getDrivetrain());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    joystick = Robot.getOperatorInput().getJoystick();
    drivetrain = Robot.getDrivetrain();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // FIXME
    drivetrain.driveCartesian(joystick.getY(), joystick.getX(), joystick.getZ());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
