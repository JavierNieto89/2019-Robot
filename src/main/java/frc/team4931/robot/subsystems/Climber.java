package frc.team4931.robot.subsystems;



import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4931.robot.RobotMap;

public class Climber extends Subsystem {

    private WPI_TalonSRX wenchMotor = new WPI_TalonSRX(RobotMap.CLIMBER_WENCH);

    private DoubleSolenoid pneumatics = new DoubleSolenoid(
        RobotMap.COMPRESSOR, 
        RobotMap.CLIMBER_PISTON_LATCH, 
        RobotMap.CLIMBER_PISTON_RELEASE);

    public void initDefaultCommand() {

    }

    public void extendClimberArm() {
        wenchMotor.set(1);
    }

    public void retractClimberArm() {
        wenchMotor.set(-1);
    }

    public void armStop() {
        wenchMotor.set(0);
    }

    public void latch() {
        pneumatics.set(Value.kForward);
    }

    public void release() {
        pneumatics.set(Value.kReverse);
    }
}