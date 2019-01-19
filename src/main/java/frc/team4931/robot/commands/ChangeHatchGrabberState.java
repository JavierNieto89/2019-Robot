package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class ChangeHatchGrabberState extends Command {

    @Override
    protected void initialize() {
        Robot.getHatchGrabber().changeHatchGrabberState();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}