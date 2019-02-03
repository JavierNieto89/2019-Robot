package frc.team4931.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4931.robot.RobotMap;

public class HatchGrabber extends Subsystem {

    private DoubleSolenoid pneumaticDispenser;
    private DoubleSolenoid pneumaticTopVelcro;
    private DoubleSolenoid pneumaticBottomVelcro;

    public HatchGrabber() {

        pneumaticDispenser = new DoubleSolenoid(RobotMap.COMPRESSOR, 
                RobotMap.DISPENSER_EXTEND, RobotMap.DISPENSER_RETRACT);
        pneumaticDispenser.set(Value.kReverse);

        pneumaticTopVelcro = new DoubleSolenoid(RobotMap.COMPRESSOR, 
                RobotMap.TOP_VELCRO_EXTEND, RobotMap.TOP_VELCRO_RETRACT);
        pneumaticTopVelcro.set(Value.kReverse);

        pneumaticBottomVelcro = new DoubleSolenoid(RobotMap.COMPRESSOR, 
                RobotMap.BOTTOM_VELCRO_EXTEND, RobotMap.BOTTOM_VELCRO_RETRACT);
        pneumaticBottomVelcro.set(Value.kReverse);

    }

    protected void initDefaultCommand() {
    }

    public void extendHatchGrabber() {
        pneumaticDispenser.set(Value.kForward);
    }

    public void resetHatchGrabber() {
        pneumaticDispenser.set(Value.kReverse);
    }

    public void extendVelcro() {
        pneumaticBottomVelcro.set(Value.kForward);
        pneumaticTopVelcro.set(Value.kForward);
    }

    public void retractVelcro() {
        pneumaticBottomVelcro.set(Value.kReverse);
        pneumaticBottomVelcro.set(Value.kReverse);
    }

    public void changeVelcroState() {
        if(pneumaticTopVelcro.get() == Value.kForward){
            extendVelcro();
        } else {
            retractVelcro();
        }
    }

}