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

@Deprecated
public class LineupCorrectX extends Command {

  private String distanceRowName;
  private double distanceFinalLocation;

  private Command moveCartesianCommand;

  public LineupCorrectX(String distanceRowName, double distanceFinalLocation) {
    requires(Robot.getDrivetrain());

    this.distanceRowName = distanceRowName;
    this.distanceFinalLocation = distanceFinalLocation;
  }

  @Override
  protected void initialize() {
    double distanceCorrection = SmartDashboard.getNumber(distanceRowName, 0) - distanceFinalLocation;

    moveCartesianCommand = new MoveCartesian(0, distanceCorrection, true);
  }

  @Override
  protected boolean isFinished() {
    if (moveCartesianCommand != null)
      return moveCartesianCommand.isCompleted();
    else
      return false;
  }
}
