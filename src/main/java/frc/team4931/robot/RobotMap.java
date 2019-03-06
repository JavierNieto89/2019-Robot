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
  public final static int MOTOR_DT_FRONT_LEFT = 2;
  public final static int MOTOR_DT_FRONT_RIGHT = 3;
  public final static int MOTOR_DT_BACK_LEFT = 4;
  public final static int MOTOR_DT_BACK_RIGHT = 1;

  /**
   * Talon SRX port that the talon is attached to
   */
  public final static DriveMotors PIGEON_IMU = DriveMotors.FRONT_RIGHT;

  public final static int JOYSTICK1 = 0;
  public static final int JOYSTICK2 = 1;

  public final static int COMPRESSOR = 6;

  public final static int PRESSURE = 0;

  public final static int LIMIT_SWITCH = 9;

  // forward solenoid channel to extend hatch grabber pistons
  public final static int DISPENSER_EXTEND = 1;

  // reverse solenoid channel to extend hatch grabber pistons
  public final static int DISPENSER_RETRACT = 0;

  // forward solenoid channel to extend top velcro pad
  public final static int VELCRO_EXTEND = 3;

  // reverse solenoid channel to retract top velcro pad
  public final static int VELCRO_RETRACT = 2;

  // forward solenoid channel for climber pistons to latch onto 3rd level
  public final static int CLIMBER_PISTON_LATCH = 4;

  // reverse solenoid channel for climber pistons to release from 3rd level
  public final static int CLIMBER_PISTON_RELEASE = 5;

  // forward solenoid channel for hatch grabber pivot pistons
  public final static int PIVOT_PISTON_EXTEND = 7;

  // reverse solenoid channel for hatch grabber pivot pistons
  public final static int PIVOT_PISTON_RETRACT = 6;

  // motor channels for Climber wench
  public final static int CLIMBER_WENCH_1 = 5;
  public final static int CLIMBER_WENCH_2 = 6;

  // The USB port the camera is connected to
  public final static int CAMERA = 0;

  // The width and height of the camera output feed
  public final static int CAMERA_WIDTH = 240;
  public final static int CAMERA_HEIGHT = 180;

  // Camera toggle UI visibility
  public final static int CAMERA_UI_TOGGLE = 1; // FIXME Remap

  public final static int CAMERA_UI_LOCATION_HEIGHT = 100; // The Y value of the eclipse on the screen. The X value is
  public final static int CAMERA_UI_LOCATION_WIDTH = 85; // the camera width / 2.


  public final static int CAMERA_UI_WIDTH = 100; // Eclipse height
  public final static int CAMERA_UI_HEIGHT = 50; // Eclipse width

  // The width of the outline for the eclipse
  public final static int CAMERA_UI_LINE_WIDTH = 1;

  public final static String LINEUP_DISTANCE_CO = "Lineup Coeff DISTANCE";
  public final static String LINEUP_OFFSET_CO = "Lineup Coeff OFFSET";
  public final static String LINEUP_ANGLE_CO = "Lineup Coeff ANGLE";
  public final static String LINEUP_SCALE_SPEED = "Lineup Scale SPEED";
}