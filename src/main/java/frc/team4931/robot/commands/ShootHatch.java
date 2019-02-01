package frc.team4931.robot.commands;

import frc.team4931.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootHatch extends CommandGroup {

    public ShootHatch() {
        System.out.println("ran Shoot Hatch");
        addSequential(new ExtendHatchGrabber());
        addSequential(new ResetHatchGrabber());
    }
}