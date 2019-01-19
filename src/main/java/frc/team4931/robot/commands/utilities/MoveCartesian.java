/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.commands.utilities;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;
import frc.team4931.robot.subsystems.Drivetrain;

public class MoveCartesian extends Command {

  private Drivetrain drivetrain;

  private double xFeet, yFeet;
  private boolean worldRelative;

  public MoveCartesian(double xFeet, double yFeet, boolean worldRelative) {
    requires(Robot.getDrivetrain());

    this.xFeet = xFeet;
    this.yFeet = yFeet;
    this.worldRelative = worldRelative;
  }

  @Override
  protected void initialize() {
    drivetrain = Robot.getDrivetrain();
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
