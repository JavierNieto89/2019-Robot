package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class ResetHatchGrabber extends Command {

    public ResetHatchGrabber() {
        requires(Robot.getHatchGrabber());
    }

    @Override
    protected void initialize() {
        System.out.println("Reseted Hatch Grabber");
        Robot.getHatchGrabber().resetHatchGrabber();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}