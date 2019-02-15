package frc.team4931.robot.commands.hatchgrabber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4931.robot.Robot;

public class RetractVelcro extends Command {

    public RetractVelcro() {
        requires(Robot.getHatchGrabber());
    }

    @Override
    protected void initialize() {
        Robot.getHatchGrabber().retractVelcro();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}