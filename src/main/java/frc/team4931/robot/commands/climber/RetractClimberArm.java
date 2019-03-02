package frc.team4931.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;
import frc.team4931.robot.subsystems.Climber;

public class RetractClimberArm extends Command {

    private Climber climber;

    public RetractClimberArm() {
        climber = Robot.getClimber();
        requires(climber);
    }

    @Override
    protected void initialize() {
        if(climber.getLimitSwitchValue() == false) {
            Robot.getClimber().retractClimberArm();
        }
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