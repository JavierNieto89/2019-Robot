package frc.team4931.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;
import frc.team4931.robot.subsystems.Climber;

public class InvertClimberArm extends Command {

    private Climber climber = Robot.getClimber();

    public InvertClimberArm() {
        requires(climber);
    }

    protected void initialize() {
        climber.InvertClimberArm();
    }

    protected boolean isFinished() {
        return true;
    }
}