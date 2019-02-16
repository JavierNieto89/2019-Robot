package frc.team4931.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class ExtendClimberArm extends Command {

    public ExtendClimberArm() {
        requires(Robot.getClimber());
    }

    @Override
    protected void initialize() {
        Robot.getClimber().extendClimberArm();
    }

    @Override
    protected void end() {
        //Robot.getClimber().armStop();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
