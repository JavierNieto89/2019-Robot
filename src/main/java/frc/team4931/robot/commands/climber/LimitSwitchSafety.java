package frc.team4931.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;
import frc.team4931.robot.subsystems.Climber;

public class LimitSwitchSafety extends Command {

    private Climber climber;
    private boolean triggered;

    public LimitSwitchSafety() {
        climber = Robot.getClimber();
        triggered = false;
        setInterruptible(false);
    }

    protected void execute() {
        boolean limitSwitch = climber.getLimitSwitchValue();

        if(limitSwitch == true) {
            if(climber.getWench().get() < 0) {
                climber.armStop();
                triggered = true;
            }
        }
    }

    protected boolean isFinished() {
        return triggered;
    }
}