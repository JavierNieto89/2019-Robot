package frc.team4931.robot.commands.hatchgrabber;

import frc.team4931.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootHatch extends CommandGroup {

    public ShootHatch() {
        addSequential(new ExtendHatchGrabber());
        addSequential(new WaitCommand(1));
        addSequential(new ResetHatchGrabber());
        addSequential(new RetractVelcro());
    }
}