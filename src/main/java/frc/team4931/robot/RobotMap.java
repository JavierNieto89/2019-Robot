/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot;

import frc.team4931.robot.enums.DriveMotors;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public final static int MOTOR_DT_FRONT_LEFT = 2;
  public final static int MOTOR_DT_FRONT_RIGHT = 3;
  public final static int MOTOR_DT_BACK_LEFT = 4;
  public final static int MOTOR_DT_BACK_RIGHT = 1;

  /**
   * Talon SRX port that the talon is attached to
   */
  public final static DriveMotors PIGEON_IMU = DriveMotors.BACK_LEFT;

  public final static int JOYSTICK = 0;

  public final static int COMPRESSOR = 6;

  //forward solenoid channel to extend hatch grabber pistons
  public final static int DISPENSER_EXTEND = 0;

  //reverse solenoid channel to extend hatch grabber pistons
  public final static int DISPENSER_RETRACT = 1;

  //forward solenoid channel to extend top velcro pad
  public final static int TOP_VELCRO_EXTEND = 2;

  //reverse solenoid channel to retract top velcro pad
  public final static int TOP_VELCRO_RETRACT = 3;

  //forward solenoid channel to extend bottom velcro pad
  public final static int BOTTOM_VELCRO_EXTEND = 4;

  //reverse solenoid channel to retract bottom velcro pad
  public final static int BOTTOM_VELCRO_RETRACT = 5;

  //forward solenoid channel for climber pistons to latch onto 3rd level
  public final static int CLIMBER_PISTON_LATCH = 6;

  //reverse solenoid channel for climber pistons to release from 3rd level
  public final static int CLIMBER_PISTON_RELEASE = 7;

  //motor channel for Climber wench
  public final static int CLIMBER_WENCH = 5;

}