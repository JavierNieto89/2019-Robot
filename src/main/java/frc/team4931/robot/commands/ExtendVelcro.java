package frc.team4931.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.subsystems.*;
import frc.team4931.robot.Robot;

public class ExtendVelcro extends Command {
    
    public ExtendVelcro() {
        requires(Robot.getHatchGrabber());
    }

    protected void initialize() {
        Robot.getHatchGrabber().extendVelcro();
    }

    protected boolean isFinished() {
        return true;
    }
}