package frc.team4931.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4931.robot.RobotMap;
import frc.team4931.robot.commands.ChangeHatchGrabberState;

public class HatchGrabber extends Subsystem {

    private DoubleSolenoid pneumatic;

    public HatchGrabber() {

        pneumatic = new DoubleSolenoid(
            RobotMap.COMPRESSOR,
            RobotMap.HATCH_GRABBER_EXTEND, 
            RobotMap.HATCH_GRABBER_RETRACT);

    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ChangeHatchGrabberState());
    }

    public void changeHatchGrabberState() {
        if(pneumatic.get() == Value.kForward) {
            pneumatic.set(Value.kReverse);
        }

        if(pneumatic.get() == Value.kReverse) {
            pneumatic.set(Value.kForward);
        }
    }

    public void resetHatchGrabberState() {
        pneumatic.set(Value.kReverse);
    }


}