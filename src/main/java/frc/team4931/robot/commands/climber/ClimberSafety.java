package frc.team4931.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;
import frc.team4931.robot.sensors.Pigeon;
import frc.team4931.robot.subsystems.Climber;

public class ClimberSafety extends Command {
  private static final double THRESHOLD = -1.5;

  private Climber climber;
  private Pigeon pigeon;

  public ClimberSafety() {
    climber = Robot.getClimber();
    pigeon = Robot.getPigeon();

    setInterruptible(false);
  }

  @Override
  protected void execute() {
    double acc = pigeon.getAcceleration();

    if (acc < -THRESHOLD)
      climber.latch();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
