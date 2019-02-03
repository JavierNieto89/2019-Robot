/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.sensors;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import frc.team4931.robot.Robot;
import frc.team4931.robot.RobotMap;

public class Pigeon {

  private PigeonIMU pigeon;

  public Pigeon(WPI_TalonSRX motor) {
    pigeon = new PigeonIMU(motor);

    pigeon.configFactoryDefault();
  }

  /**
   * Gets the current angle of the Pigeon IMU (Robot).
   *
   * @return angle -180 to 180.
   */
  public double getAngle() {
    double angle = -pigeon.getFusedHeading() % 360;
    double out = (angle < -180) ? angle + 360 : angle;
    return (out > 180) ? out - 360 : out;
  }

  public void reset() {
    pigeon.setFusedHeading(0);
  }
}