/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.team4931.robot.RobotMap;
import frc.team4931.robot.commands.DriveTeleoperated;
import frc.team4931.robot.enums.DriveMotors;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {

  private WPI_TalonSRX motorFrontLeft;
  private WPI_TalonSRX motorFrontRight;
  private WPI_TalonSRX motorBackLeft;
  private WPI_TalonSRX motorBackRight;

  private MecanumDrive mecanumDrive;

  public Drivetrain() {
    motorFrontLeft = new WPI_TalonSRX(RobotMap.MOTOR_DT_FRONT_LEFT);
    motorFrontRight = new WPI_TalonSRX(RobotMap.MOTOR_DT_FRONT_RIGHT);
    motorBackLeft = new WPI_TalonSRX(RobotMap.MOTOR_DT_BACK_LEFT);
    motorBackRight = new WPI_TalonSRX(RobotMap.MOTOR_DT_BACK_RIGHT);

    mecanumDrive = new MecanumDrive(motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight);

    mecanumDrive.setDeadband(0.05);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveTeleoperated());
  }

  /**
   * s Use this for cartesian driving in a robot oriented axis.
   * 
   * @see edu.wpi.first.wpilibj.drive.MecanumDrive#driveCartesian(double, double,
   *      double)
   */
  public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
    mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation);
  }

  /**
   * Use this for cartesian driving in a world oriented axis.
   * 
   * @see edu.wpi.first.wpilibj.drive.MecanumDrive#driveCartesian(double, double,
   *      double, double)
   */
  public void driveCartesianFieldOriented(double ySpeed, double xSpeed, double zRotation, double gyroAngle) {
    mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation, gyroAngle);
  }

  /**
   * This method is for driving in a direction relative to the robot in degrees.
   * 
   * @see edu.wpi.first.wpilibj.drive.MecanumDrive#drivePolar(double, double,
   *      double)
   */
  public void drivePolar(double magnitude, double angle, double zRotation) {
    mecanumDrive.drivePolar(magnitude, angle, zRotation);
  }

  public WPI_TalonSRX getMotor(DriveMotors motor) {
    WPI_TalonSRX returnMotor;
    switch (motor) {
      case FRONT_LEFT:
        returnMotor = motorFrontLeft;
        break;
      case FRONT_RIGHT:
        returnMotor = motorFrontRight;
        break;
      case BACK_LEFT:
        returnMotor = motorBackLeft;
        break;
      case BACK_RIGHT:
        returnMotor = motorBackRight;
        break;
      default:
        returnMotor = motorBackLeft;
        break;
    }
    return returnMotor;
  }

  /**
   * Sets all motors to stop.
   */
  public void stop() {
    mecanumDrive.stopMotor();
  }

  public void log() {
    SmartDashboard.putNumber("Front Left Motor Speed", motorFrontLeft.get());
    SmartDashboard.putNumber("Back Left Motor Speed", motorBackLeft.get());
    SmartDashboard.putNumber("Front Right Motor Speed", motorFrontRight.get());
    SmartDashboard.putNumber("Back Right Motor Speed", motorBackRight.get());
  }
}