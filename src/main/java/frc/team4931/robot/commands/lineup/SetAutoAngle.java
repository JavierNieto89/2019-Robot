package frc.team4931.robot.commands.lineup;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;

public class SetAutoAngle extends Command {

  @Override
  protected void initialize() {
    Robot.setAutoAngle();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
