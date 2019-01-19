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

public class DriveTeleoperated extends Command {

  private Joystick joystick;
  private Drivetrain drivetrain;

  public DriveTeleoperated() {
    requires(Robot.getDrivetrain());
  }

  @Override
  protected void initialize() {
    joystick = Robot.getOperatorInput().getJoystick();
    drivetrain = Robot.getDrivetrain();
  }

  @Override
  protected void execute() {
    drivetrain.driveCartesian(joystick.getY(), joystick.getX(), joystick.getZ());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
