package frc.team4931.robot.commands.hatchgrabber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class PivotUp extends Command {

    public PivotUp() {
        requires(Robot.getHatchGrabber());
    }

    @Override
    protected void initialize() {
        Robot.getHatchGrabber().pivotUp();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}