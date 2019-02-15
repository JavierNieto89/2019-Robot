package frc.team4931.robot.commands.hatchgrabber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class PivotDown extends Command {

    public PivotDown() {
        requires(Robot.getHatchGrabber());
    }

    @Override
    protected void initialize() {
        Robot.getHatchGrabber().pivotDown();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}