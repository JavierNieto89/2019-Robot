package frc.team4931.robot.enums;

import frc.team4931.robot.RobotMap;

public enum  DriveMotors {
  FRONT_LEFT(RobotMap.MOTOR_DT_FRONT_LEFT), FRONT_RIGHT(RobotMap.MOTOR_DT_FRONT_RIGHT),
  BACK_LEFT(RobotMap.MOTOR_DT_BACK_LEFT), BACK_RIGHT(RobotMap.MOTOR_DT_BACK_RIGHT);

  int port;

  DriveMotors(int port) {
    this.port = port;
  }

  public int getPort() {
    return port;
  }

}
