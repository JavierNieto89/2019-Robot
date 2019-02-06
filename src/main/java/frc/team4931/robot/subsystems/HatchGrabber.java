package frc.team4931.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4931.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HatchGrabber extends Subsystem {

    private DoubleSolenoid pneumaticDispenser;
    private DoubleSolenoid pneumaticTopVelcro;
    private DoubleSolenoid pneumaticBottomVelcro;
    private DoubleSolenoid pneumaticPivot;

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

        pneumaticPivot = new DoubleSolenoid(RobotMap.COMPRESSOR,
                RobotMap.PIVOT_PISTON_EXTEND,
                RobotMap.PIVOT_PISTON_RETRACT);

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

    public void pivotDown() {
        pneumaticPivot.set(Value.kForward);
    }

    public void pivotUp() {
        pneumaticPivot.set(Value.kReverse);
    }

    public void log() {
        SmartDashboard.putBoolean("Top Velcro Extended", 
                pneumaticTopVelcro.get() == Value.kForward ? true : false);
        SmartDashboard.putBoolean("Bottom Velcro Extended", 
                pneumaticBottomVelcro.get() == Value.kForward ? true : false);
    }

}