package frc.team4931.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class ArmStop extends Command {

    public ArmStop() {
        requires(Robot.getClimber());
    }

    @Override
    protected void initialize() {
        Robot.getClimber().armStop();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}