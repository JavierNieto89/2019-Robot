package frc.team4931.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4931.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.WPILibVersion;

public class Climber extends Subsystem {

    private WPI_TalonSRX wenchMotor1 = new WPI_TalonSRX(RobotMap.CLIMBER_WENCH_1);

    //always set wenchMotor2 to the negative speed of wenchMotor1
    private WPI_TalonSRX wenchMotor2 = new WPI_TalonSRX(RobotMap.CLIMBER_WENCH_2);

    private DoubleSolenoid pneumatics = new DoubleSolenoid(
        RobotMap.COMPRESSOR, 
        RobotMap.CLIMBER_PISTON_LATCH, 
        RobotMap.CLIMBER_PISTON_RELEASE);

    public void initDefaultCommand() {

    }

    public void extendClimberArm() {
        wenchMotor1.set(1);
        wenchMotor2.set(-1);
    }

    public void retractClimberArm() {
        wenchMotor1.set(-1);
        wenchMotor2.set(1);
    }

    public void armStop() {
        wenchMotor1.stopMotor();
        wenchMotor2.stopMotor();
    }

    public void latch() {
        pneumatics.set(Value.kForward);
    }

    public void release() {
        pneumatics.set(Value.kReverse);
    }

    public void log() {
        SmartDashboard.putNumber("Wench Motor 1 Speed", wenchMotor1.get());
        SmartDashboard.putNumber("Wench Motor 2 Speed", -(wenchMotor2.get()));
    }
}