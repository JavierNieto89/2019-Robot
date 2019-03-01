package frc.team4931.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class Latch extends Command {

    public Latch() {
        requires(Robot.getClimber());
    }

    @Override
    public void initialize() {
        Robot.getClimber().latch();
        Robot.startClimberSafety();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}