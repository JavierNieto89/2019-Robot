package frc.team4931.robot.commands.hatchgrabber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class ResetHatchGrabber extends Command {

    public ResetHatchGrabber() {
        requires(Robot.getHatchGrabber());
    }

    @Override
    protected void initialize() {
        Robot.getHatchGrabber().resetHatchGrabber();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}