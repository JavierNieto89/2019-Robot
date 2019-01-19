package frc.team4931.robot.commands;

import frc.team4931.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootHatch extends CommandGroup {

    public ShootHatch() {
        addSequential(new ExtendHatchGrabber());
        addSequential(new ResetHatchGrabber());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}