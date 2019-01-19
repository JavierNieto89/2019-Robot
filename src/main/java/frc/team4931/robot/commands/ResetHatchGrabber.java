package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class ResetHatchGrabber extends Command {

    @Override
    protected void initialize() {
        Robot.getHatchGrabber().resetHatchGrabber();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}