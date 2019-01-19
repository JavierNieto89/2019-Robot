/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;
import frc.team4931.robot.commands.utilities.RotateToDegree;

public class SnapToDegree extends Command {

  private int rotationSnapPoints;

  Command rotateToDegreeCommand;

  public SnapToDegree(int rotationSnapPoints) {
    requires(Robot.getDrivetrain());

    this.rotationSnapPoints = rotationSnapPoints;
  }

  @Override
  protected void initialize() {
    double currentRotation = Double.MAX_VALUE; // FIXME: Make work with gyro

    double x = (360 / rotationSnapPoints); // I can't think what to name this var right now. If you have any ideas what
                                           // to call this please rename it.
    double targetDegree = Math.round(currentRotation / x) * x;

    rotateToDegreeCommand = new RotateToDegree(targetDegree);
  }

  @Override
  protected boolean isFinished() {
    if (rotateToDegreeCommand != null)
      return rotateToDegreeCommand.isCompleted();
    else
      return false;
  }
}
