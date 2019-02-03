package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class ChangeVelcroState extends Command {

    public ChangeVelcroState() {
        requires(Robot.getHatchGrabber());
    }

    protected void initialize() {
        Robot.getHatchGrabber().changeVelcroState();
    }

    protected boolean isFinished() {
        return true;
    }
}