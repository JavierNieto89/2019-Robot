package frc.team4931.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4931.robot.RobotMap;

public class HatchGrabber extends Subsystem {

    private DoubleSolenoid pneumatic;

    public HatchGrabber() {

        pneumatic = new DoubleSolenoid(
            RobotMap.COMPRESSOR,
            RobotMap.HATCH_GRABBER_EXTEND, 
            RobotMap.HATCH_GRABBER_RETRACT);

    }

    protected void initDefaultCommand() {
    }

    public void extendHatchGrabber() {
        pneumatic.set(Value.kForward);
    }

    public void resetHatchGrabber() {
        pneumatic.set(Value.kReverse);
    }


}