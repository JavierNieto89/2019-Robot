/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import frc.team4931.robot.commands.lineup.LineupWithTarget;
import frc.team4931.robot.enums.DriveMotors;
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
  
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();

    pigeon = new Pigeon(drivetrain.getMotor(DriveMotors.BACK_LEFT));
    pigeon.reset();

    hatchGrabber = new HatchGrabber();

    climber = new Climber();

    compressor = new Compressor(RobotMap.COMPRESSOR);
    compressor.setClosedLoopControl(true);
    compressor.start();

    operatorInput = new OperatorInput();

    SmartDashboard.putData(drivetrain);
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
    Scheduler.getInstance().run();
    log();
  }

  @Override
  public void autonomousInit() {
    new LineupWithTarget().start();
  }

  public void teleopInit() {
    compressor.start();
  }

  public void disablesInit() {
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

  private void log() {
    SmartDashboard.putNumber("Gyro Angle", pigeon.getAngle());
    hatchGrabber.log();
    drivetrain.log();
    climber.log();
  }
}