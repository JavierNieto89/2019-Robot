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
  private static final double DEAD_ZONE = 0.1;

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
    var forward = -joystick.getY();
    var strafe = joystick.getX();
    var rotation = joystick.getZ();

    forward = Math.abs(forward) > DEAD_ZONE ? forward : 0;
    strafe = Math.abs(strafe) > DEAD_ZONE ? strafe : 0;
    rotation = Math.abs(rotation) > DEAD_ZONE ? rotation : 0;

    drivetrain.driveCartesian(forward, strafe, rotation);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
