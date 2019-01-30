package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class Latch extends Command {

    public Latch() {
        requires(Robot.getClimber());
    }

    public void initialize() {
        Robot.getClimber().latch();
    }

    public boolean isFinished() {
        return true;
    }
}