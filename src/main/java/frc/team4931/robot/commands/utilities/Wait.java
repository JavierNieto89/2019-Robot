/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.commands.utilities;

import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {

  private long startTime;
  private long waitTime;

  public Wait(long waitTime) {
    this.waitTime = waitTime;
  }

  @Override
  protected void initialize() {
    startTime = System.currentTimeMillis();
  }

  @Override
  protected boolean isFinished() {
    return System.currentTimeMillis() - startTime >= waitTime;
  }
}
