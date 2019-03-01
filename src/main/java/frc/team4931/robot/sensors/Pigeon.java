/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot.sensors;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU.GeneralStatus;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pigeon {

  private PigeonIMU pigeon;

  public Pigeon(WPI_TalonSRX motor) {
    pigeon = new PigeonIMU(motor);

    pigeon.configFactoryDefault();
    //pigeon.enterCalibrationMode(CalibrationMode.Magnetometer12Pt); // FIXME REMOVE THIS LINE!!!
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

  /**
   * Gets the current angle of the Pigeon IMU (Robot).
   *
   * @return angle -inf to +inf.
   */
  public double getAngleContinuous() {
    return -pigeon.getFusedHeading();
  }

  public double getAbsoluteCompassHeading() {
    return pigeon.getAbsoluteCompassHeading();
  }

  public double getCompassHeading() {
    return pigeon.getCompassHeading();
  }

  public void resetCompass() {

  }

  public GeneralStatus getGeneralStatus() {
    GeneralStatus status = new GeneralStatus();
    pigeon.getGeneralStatus(status);
    return status;
  }

  public double getTiltAcceleration() {
    double[] rawGyroData = new double[3];
    pigeon.getRawGyro(rawGyroData);
    return rawGyroData[0];
  }

  public void zero() {
    pigeon.setFusedHeading(0);
  }

  public void reset() {
    pigeon.setFusedHeading(0);
  }

  public void log() {
    SmartDashboard.putNumber("Angle", getAngle());
    SmartDashboard.putNumber("Angle Continuous", getAngleContinuous());
    SmartDashboard.putNumber("Compass Angle Absolute", getAbsoluteCompassHeading());
    SmartDashboard.putNumber("Compass Angle", getCompassHeading());

    var stat = getGeneralStatus();
    SmartDashboard.putNumber("Compass Cal Error", stat.calibrationError);
    SmartDashboard.putBoolean("Compass Calibrating", stat.bCalIsBooting);

    if (SmartDashboard.getBoolean("Reset Gyro", false)) {
      zero();
      SmartDashboard.putBoolean("Reset Gyro", false);
    }
  }
}