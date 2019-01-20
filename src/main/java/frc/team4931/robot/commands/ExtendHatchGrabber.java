package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class ExtendHatchGrabber extends Command {

    public ExtendHatchGrabber() {
        requires(Robot.getHatchGrabber());
    }

    @Override
    protected void initialize() {
        Robot.getHatchGrabber().extendHatchGrabber();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}