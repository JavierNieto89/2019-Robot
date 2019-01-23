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

  public Pigeon() {
    pigeon = new PigeonIMU(Robot.getDrivetrain().motorBackLeft);

    pigeon.configFactoryDefault();
  }

  public double getAngle() {
    // TODO change to -180 to 180
    return -pigeon.getFusedHeading();
  }

  public void reset() {
    pigeon.setFusedHeading(0);
  }
}