package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4931.robot.Robot;

public class RetractVelcro extends Command {

    public RetractVelcro() {
        requires(Robot.getHatchGrabber());
    }

    protected void initialize() {
        Robot.getHatchGrabber().retractVelcro();
    }

    protected boolean isFinished() {
        return true;
    }
}