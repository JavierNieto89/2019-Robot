package frc.team4931.robot.commands.utilities;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4931.robot.Robot;
import frc.team4931.robot.subsystems.Drivetrain;

public class DriveForward extends Command {
  private Drivetrain drive;
  private double speed;
  private int time;
  private long startTime;

  public DriveForward(double speed, int time) {
    this.speed = speed;
    this.time = time;
  }

  @Override
  protected void initialize() {
    drive = Robot.getDrivetrain();
    startTime = System.currentTimeMillis();
  }

  @Override
  protected void execute() {
    drive.driveCartesian(speed, 0, 0);
  }

  @Override
  protected boolean isFinished() {
    return System.currentTimeMillis() - startTime >= time;
  }
}
