/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import frc.team4931.robot.commands.climber.*;
import frc.team4931.robot.commands.lineup.LineupWithTarget;
import frc.team4931.robot.enums.Angles;
import frc.team4931.robot.sensors.Camera;
import frc.team4931.robot.sensors.Pigeon;
import frc.team4931.robot.subsystems.Climber;
import frc.team4931.robot.subsystems.Drivetrain;
import frc.team4931.robot.subsystems.HatchGrabber;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private static Drivetrain drivetrain;

  private static Pigeon pigeon;

  private static OperatorInput operatorInput;

  private static HatchGrabber hatchGrabber;

  private static Climber climber;

  private static Compressor compressor;

  private static Camera camera;

  private AnalogInput pressure;

  private static Angles angle = Angles.NONE;

  private static ClimberSafety climberSafety;

  private static LimitSwitchSafety limitSwitchSafety;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();

    pigeon = new Pigeon(drivetrain.getMotor(RobotMap.PIGEON_IMU));

    hatchGrabber = new HatchGrabber();

    climber = new Climber();

    compressor = new Compressor(RobotMap.COMPRESSOR);
    compressor.setClosedLoopControl(true);
    compressor.start();

    operatorInput = new OperatorInput();

    camera = new Camera();

    pressure = new AnalogInput(RobotMap.PRESSURE);

    climberSafety = new ClimberSafety();

    limitSwitchSafety = new LimitSwitchSafety();

    SmartDashboard.putData(drivetrain);

    SmartDashboard.putBoolean("Reset Gyro", false);
    SmartDashboard.putBoolean("Reset Comp", false);

    SmartDashboard.putNumber(RobotMap.LINEUP_ANGLE_CO, 1);
    SmartDashboard.putNumber(RobotMap.LINEUP_OFFSET_CO, 1);
    SmartDashboard.putNumber(RobotMap.LINEUP_DISTANCE_CO, 0.2);
    SmartDashboard.putNumber(RobotMap.LINEUP_SCALE_SPEED, 0.4);

    SmartDashboard.putData(new InstantCommand() {
      @Override
      protected void initialize() {
        Robot.setAutoAngle();
      }
    });
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    Angles tempAngle = calculateAngle();
    if (tempAngle != Angles.NONE)
      angle = tempAngle;

    Scheduler.getInstance().run();

    log();
    startLimitSwitchSafety();
  }

  private Angles calculateAngle() {
    int pov = operatorInput.getJoystick().getPOV();

    switch (pov) {
      case 0:
        return Angles.FORWARD;
      case 45:
        return Angles.FORWARD_RIGHT;
      case 90:
        return Angles.RIGHT;
      case 135:
        return Angles.BACKWARD_RIGHT;
      case 180:
        return Angles.BACKWARD;
      case 225:
        return Angles.BACKWARD_LEFT;
      case 270:
        return Angles.LEFT;
      case 315:
        return Angles.FORWARD_LEFT;
    }
    return Angles.NONE;
  }

  @Override
  public void autonomousInit() {
    //new LineupWithTarget().start();
  }

  public void teleopInit() {
    compressor.start();
  }

  public void disabledInit() {
    compressor.stop();
  }

  public static Drivetrain getDrivetrain() {
    return drivetrain;
  }

  public static Pigeon getPigeon() {
    return pigeon;
  }

  public static OperatorInput getOperatorInput() {
    return operatorInput;
  }

  public static HatchGrabber getHatchGrabber() {
    return hatchGrabber;
  }

  public static Climber getClimber() {
    return climber;
  }

  public static Angles getAngle() {
   return angle;
  }

  public static void setAutoAngle() {
    angle = Angles.NONE;
  }

  public static void startClimberSafety() {
    if (climberSafety != null && !climberSafety.isRunning())
      climberSafety.start();
  }

  private static void startLimitSwitchSafety() {
    if(limitSwitchSafety != null && !limitSwitchSafety.isRunning()) {
      limitSwitchSafety.start();
    }
  }

  private void log() {
    pigeon.log();
    hatchGrabber.log();
    drivetrain.log();
    climber.log();

    SmartDashboard.putNumber("Pressure", (pressure.getValue() - 400) / 1800.0 * 120);
  }
}