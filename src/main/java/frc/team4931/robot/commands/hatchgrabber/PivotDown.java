package frc.team4931.robot.commands.hatchgrabber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class PivotDown extends Command {

    public PivotDown() {
        requires(Robot.getHatchGrabber());
    }

    protected void initialize() {
        Robot.getHatchGrabber().pivotDown();
    }

    protected boolean isFinished() {
        return true;
    }
}