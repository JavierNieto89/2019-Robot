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

public class Drivetrain extends Subsystem {

  private WPI_TalonSRX motorFrontLeft;
  private WPI_TalonSRX motorFrontRight;
  public WPI_TalonSRX motorBackLeft;
  private WPI_TalonSRX motorBackRight;

  private MecanumDrive mecanumDrive;

  public Drivetrain() {
    motorFrontLeft = new WPI_TalonSRX(RobotMap.MOTOR_DT_FRONT_LEFT);
    motorFrontRight = new WPI_TalonSRX(RobotMap.MOTOR_DT_FRONT_RIGHT);
    motorBackLeft = new WPI_TalonSRX(RobotMap.MOTOR_DT_BACK_LEFT);
    motorBackRight = new WPI_TalonSRX(RobotMap.MOTOR_DT_BACK_RIGHT);

    mecanumDrive = new MecanumDrive(motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight);
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

  /**
   * Sets all motors to stop.
   */
  public void stop() {
    mecanumDrive.stopMotor();
  }
}