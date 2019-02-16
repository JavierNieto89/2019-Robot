/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.commands.utilities;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;
import frc.team4931.robot.enums.Angles;
import frc.team4931.robot.sensors.Pigeon;
import frc.team4931.robot.subsystems.Drivetrain;

public class RotateToDegree extends Command {

  private Drivetrain drivetrain;
  private Pigeon pigeon;
  private double targetDegree;
  private double startingAngle;
  private double currentAngle;

  private static final double RAMP_UP_THRESHOLD_DISTANCE = 45;
  private static final double RAMP_DOWN_THRESHOLD_DISTANCE = 45;
  private static final double START_SPEED = 0.2;
  private static final double END_SPEED = 0.1;
  private static final double MAX_SPEED = 1;

  public RotateToDegree(double targetDegree) {
    requires(Robot.getDrivetrain());

    this.targetDegree = targetDegree;

    this.pigeon = Robot.getPigeon();
    this.drivetrain = Robot.getDrivetrain();
  }

  private int getClosestAngle(double angle) {
    Angles[] values = Angles.values();
    int low = -180;
    int high = 180;

    for(Angles i : values) {
      int ang = i.getAngle();

      if (ang <= angle && ang >= low)
        low = ang;

      else if (ang >= angle && ang <= high)
        high = ang;
    }

    double diff_low = Math.abs(angle - low);
    double diff_high = Math.abs(high - angle);

    return (diff_high < diff_low) ? high : low;
  }

  private double ramp(double current) {
    return calculateSpeed(Math.abs(current - startingAngle), Math.abs(targetDegree - startingAngle), RAMP_UP_THRESHOLD_DISTANCE, RAMP_DOWN_THRESHOLD_DISTANCE,
        MAX_SPEED, START_SPEED, END_SPEED);
  }

  // Menacing looking algorithm... Check
  private double calculateSpeed(double current, double target, double rampUpThreshold,
      double rampDownThreshold, double maxSpeed, double startSpeed, double endSpeed) {
    return Math.min(Math.min(Math.pow(current / rampUpThreshold, 0.75), 1) * (maxSpeed - startSpeed)
            + startSpeed,
        Math.min(Math.pow(Math.max((target - current) / rampDownThreshold, 0), 1.5), 1) * (maxSpeed
            - endSpeed)
            + endSpeed);
  }

  @Override
  protected void initialize() {
    startingAngle = pigeon.getAngleContinuous();
    targetDegree = getClosestAngle(startingAngle);
  }

  @Override
  protected void execute() {
    currentAngle = pigeon.getAngleContinuous();
    double speed = Math.copySign(ramp(currentAngle), targetDegree - startingAngle);

    drivetrain.driveCartesian(0, 0, speed);
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(currentAngle) >= Math.abs(targetDegree);
  }
}
