package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class RetractClimberArm extends Command {

    public RetractClimberArm() {
        requires(Robot.getClimber());
    }

    protected void initialize() {
        Robot.getClimber().retractClimberArm();
    }

    protected boolean isFinished() {
        return true;
    }

}