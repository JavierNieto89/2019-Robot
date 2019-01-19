/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.commands.lineup;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4931.robot.Robot;
import frc.team4931.robot.commands.utilities.MoveCartesian;

public class LineupCorrectY extends Command {

  private String offsetRowName;
  private double offsetFinalLocation;

  private Command moveCartesianCommand;

  public LineupCorrectY(String offsetRowName, double offsetFinalLocation) {
    requires(Robot.getDrivetrain());

    this.offsetRowName = offsetRowName;
    this.offsetFinalLocation = offsetFinalLocation;
  }

  @Override
  protected void initialize() {
    double offsetCorrection = -(SmartDashboard.getNumber(offsetRowName, 0) - offsetFinalLocation);

    moveCartesianCommand = new MoveCartesian(0, offsetCorrection, true);
  }

  @Override
  protected boolean isFinished() {
    if (moveCartesianCommand != null)
      return moveCartesianCommand.isCompleted();
    else
      return false;
  }
}
