package frc.team4931.robot.commands.lineup;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4931.robot.Robot;
import frc.team4931.robot.enums.Angles;
import frc.team4931.robot.sensors.Pigeon;
import frc.team4931.robot.subsystems.Drivetrain;

public class LineupWithTarget extends Command {
  private static final String DISTANCE_KEY = "Vision Distance";
  private static final String OFFSET_KEY = "Vision Offset";
  private static final String SIGHT_KEY = "Vision Sight";
  private static final double ANGLE_CORRECTION = 0.5; // AKA Max speed
  private static final double OFFSET_CORRECTION = 0.35; // AKA speed per foot
  private static final double DISTANCE_CORRECTION = 0.15; // AKA speed per foot
  private static final double SCALE_SPEED = 0.666; // AKA what to multiply the speed by
  private Drivetrain drivetrain;
  private Pigeon pigeon;
  private boolean finished;
  private int targetAngle;
  private boolean useCurrentAngle = false;
  private boolean autoUpdate = false;

  public LineupWithTarget(boolean autoUpdate) {
    this.autoUpdate = autoUpdate;
    drivetrain = Robot.getDrivetrain();
    useCurrentAngle = true;
    requires(drivetrain);
    setInterruptible(true);
  }

  public LineupWithTarget(boolean autoUpdate, Angles angle) {
    this.autoUpdate = autoUpdate;
    drivetrain = Robot.getDrivetrain();

    if (angle == Angles.NONE) {
      useCurrentAngle = true;
    } else {
      targetAngle = angle.getAngle();
      useCurrentAngle = false;
    }

    requires(drivetrain);
    setInterruptible(true);
  }

  public void updateTarget(Angles angle) {
    if (angle == Angles.NONE) {
      useCurrentAngle = true;
    } else {
      targetAngle = angle.getAngle();
      useCurrentAngle = false;
    }
  }

  private double range(double value) {
    return Math.min(Math.max(value, -1), 1);
  }

  private int getClosestAngle(double angle) {
    Angles[] values = Angles.values();
    int low = -180;
    int high = 180;

    for(Angles i : values) {
      int ang = i.getAngle();

      if (ang <= angle && ang >= low)
        low = ang;

      else if (ang >= angle && ang <= high)
        high = ang;
    }

    double diff_low = Math.abs(angle - low);
    double diff_high = Math.abs(high - angle);

    return (diff_high < diff_low) ? high : low;
  }

  @Override
  protected void initialize() {
    pigeon = Robot.getPigeon();
    finished = false;

    if (autoUpdate) updateTarget(Robot.getAngle());

    if (useCurrentAngle) targetAngle = getClosestAngle(pigeon.getAngle());
  }

  @Override
  protected void execute() {
    // Distance and Offset are in feet
    double curDistance = SmartDashboard.getNumber(DISTANCE_KEY, -1);
    double curOffset = SmartDashboard.getNumber(OFFSET_KEY, -1);
    boolean curSight = SmartDashboard.getBoolean(SIGHT_KEY, false);
    double curAngle = pigeon.getAngle();

    // Calculate how far from the nearest preset angle in degrees
    double deltaTarget = curAngle - targetAngle; //Positive if robot is in a clockwise rotation

    // Calculate correction values
    double angleCorrection = -deltaTarget / 30 * ANGLE_CORRECTION;
    double offsetCorrection = (1 - Math.pow(range(angleCorrection), 2)) * curOffset * OFFSET_CORRECTION;
    double distanceCorrection = (1 -Math.pow(range(offsetCorrection), 2)) * curDistance * DISTANCE_CORRECTION;

    if (Math.abs(deltaTarget) < 3 && Math.abs(curOffset) < 0.15 && Math.abs(curDistance) < 0.15)
      finished = true;

    angleCorrection = range(angleCorrection) * SCALE_SPEED;
    offsetCorrection = range(offsetCorrection) * SCALE_SPEED;
    distanceCorrection = range(distanceCorrection) * SCALE_SPEED;

    if (curSight)
      drivetrain.driveCartesian(distanceCorrection, offsetCorrection, angleCorrection);
    else
      drivetrain.driveCartesian(0, 0, 0);
  }

  @Override
  protected void end() {
    drivetrain.stop();
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }
}
