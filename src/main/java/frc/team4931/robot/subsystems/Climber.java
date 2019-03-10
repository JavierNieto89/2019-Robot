package frc.team4931.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4931.robot.RobotMap;

public class Climber extends Subsystem {

  private WPI_TalonSRX wenchMotor1;
  private WPI_TalonSRX wenchMotor2;
  private SpeedControllerGroup wench;
  private DigitalInput limitSwitch;
  private boolean isInverted = false;

  public Climber() {
    wenchMotor1 = new WPI_TalonSRX(RobotMap.CLIMBER_WENCH_1);
    wenchMotor2 = new WPI_TalonSRX(RobotMap.CLIMBER_WENCH_2);
    limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH);

    //always set wenchMotor2 to the negative speed of wenchMotor1
    wenchMotor2.setInverted(true);

    wench = new SpeedControllerGroup(wenchMotor1, wenchMotor2);
    wench.setInverted(isInverted);
    SmartDashboard.putBoolean("Climber is Inverted", isInverted);
  }

  private DoubleSolenoid pneumatics = new DoubleSolenoid(
      RobotMap.COMPRESSOR,
      RobotMap.CLIMBER_PISTON_LATCH,
      RobotMap.CLIMBER_PISTON_RELEASE);

  public void initDefaultCommand() {

  }

  public void extendClimberArm() {
    wench.set(1);
  }

  public void retractClimberArm() {
    wench.set(-1);
  }

  public void armStop() {
    wench.stopMotor();
  }

  public void InvertClimberArm() {
    isInverted = !isInverted;
    wench.setInverted(isInverted);
    SmartDashboard.putBoolean("Climber is Inverted", isInverted);
  }

  public void latch() {
    pneumatics.set(Value.kForward);
  }

  public void release() {
    pneumatics.set(Value.kReverse);
  }

  public void pressureTest() {
    pneumatics.set(Value.kOff);
  }

  public void log() {
    SmartDashboard.putNumber("Wench Motor 1 Speed", wenchMotor1.get());
    SmartDashboard.putNumber("Wench Motor 2 Speed", -(wenchMotor2.get()));
    SmartDashboard.putNumber("Wench Motor Group Speed", wench.get());
    SmartDashboard.putBoolean("Limit Switch is triggered", limitSwitch.get());
  }

  public boolean getLimitSwitchValue() {
    return !(limitSwitch.get());
  }

  public SpeedControllerGroup getWench() {
    return wench;
  }
}