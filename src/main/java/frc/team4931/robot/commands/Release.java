package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class Release extends Command {

    public Release() {
        requires(Robot.getClimber());
    }

    protected void initialize() {
        Robot.getClimber().release();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}