package frc.team4931.robot.commands.hatchgrabber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class ChangeVelcroState extends Command {

    public ChangeVelcroState() {
        requires(Robot.getHatchGrabber());
    }

    @Override
    protected void initialize() {
        Robot.getHatchGrabber().changeVelcroState();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}