/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.commands.lineup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4931.robot.commands.SnapToDegree;

public class LineupGroup extends CommandGroup {

  /**
   * These are the final positions for the camera (robot) when the command is
   * complete. The target should be be this distance away and have this much of an
   * offset.
   */
  private final static double FINAL_LOCATION_DISTANCE = 0.5;
  private final static double FINAL_LOCATION_OFFSET = 0.0;

  /**
   * The number of snap points determines how the robot rotates to get parallel
   * with the target.
   * 
   * For example, if the robot is at 129 degrees of rotation it would snap to 135
   * as it's the nearest eighth of a circle.
   */
  private final static int ROTATION_SNAP_POINTS = 8;

  /**
   * These are the locations of the vision target data in the Smart Dashboard.
   */
  private final static String DISTANCE_ROW_NAME = "Distance";
  private final static String OFFSET_ROW_NAME = "Offset";

  /**
   * This is the delay in milliseconds between the camera capturing a frame and
   * the data being in the network table.
   * 
   * If the PI is able to process at 10 frames per second that would be a delay of
   * 100 milliseconds for each frame.
   */
  private final static short PI_DELAY_MS = 150;

  public LineupGroup() {
    if (!(SmartDashboard.containsKey(DISTANCE_ROW_NAME) && SmartDashboard.containsKey(OFFSET_ROW_NAME)))
      return;

    System.out.println("Starting lineup");

    /**
     * We could switch over how the movement is done. Right now it's calculated at
     * the beginning of every command. I haven't began working on the actual
     * movement cause I'm not sure what sensors will be connected. So having
     * movement influenced by the PI in real time could be something we could
     * explore.
     */

    addSequential(new SnapToDegree(ROTATION_SNAP_POINTS));
    addSequential(new WaitCommand(PI_DELAY_MS));
    addSequential(new LineupCorrectY(OFFSET_ROW_NAME, FINAL_LOCATION_OFFSET));
    addSequential(new WaitCommand(PI_DELAY_MS));
    addSequential(new LineupCorrectX(DISTANCE_ROW_NAME, FINAL_LOCATION_DISTANCE));
  }
}
