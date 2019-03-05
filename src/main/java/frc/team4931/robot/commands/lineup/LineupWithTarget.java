package frc.team4931.robot.commands.lineup;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4931.robot.Robot;
import frc.team4931.robot.enums.Angles;
import frc.team4931.robot.sensors.Pigeon;
import frc.team4931.robot.subsystems.Drivetrain;
import java.util.ArrayList;

public class LineupWithTarget extends Command {
  private static final String DISTANCE_KEY = "Vision Distance";
  private static final String OFFSET_KEY = "Vision Offset";
  private static final String SIGHT_KEY = "Vision Sight";
  private static double ANGLE_CORRECTION = 1.0; // AKA Max speed
  private static double OFFSET_CORRECTION = 1.35; // AKA speed per foot
  private static double DISTANCE_CORRECTION = 0.25; // AKA speed per foot
  private static double SCALE_SPEED_X = 0.5; // AKA what to multiply the speed by
  private static double SCALE_SPEED_Y = 0.35; // AKA what to multiply the speed by
  private static double SCALE_SPEED_Z = 0.35; // AKA what to multiply the speed by
  private Drivetrain drivetrain;
  private Pigeon pigeon;
  private boolean finished;
  private int targetAngle;
  private boolean useCurrentAngle = false;
  private boolean autoUpdate = true;

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
    ArrayList<Angles> values = new ArrayList<>();

    for (var val : Angles.values()) {
      if (val != Angles.NONE) values.add(val);
    }

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

  public static void setCoefficients(double angle, double offset, double distance, double scaleX, double scaleY, double scaleZ) {
    ANGLE_CORRECTION = angle;
    OFFSET_CORRECTION = offset;
    DISTANCE_CORRECTION = distance;
    SCALE_SPEED_X = scaleX;
    SCALE_SPEED_Y = scaleY;
    SCALE_SPEED_Z = scaleZ;
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
    SmartDashboard.putNumber("Target Angle", targetAngle);

    // Distance and Offset are in feet
    double curDistance = SmartDashboard.getNumber(DISTANCE_KEY, -1);
    double curOffset = SmartDashboard.getNumber(OFFSET_KEY, 0);
    boolean curSight = SmartDashboard.getBoolean(SIGHT_KEY, false);
    double curAngle = pigeon.getAngle();

    // Calculate how far from the nearest preset angle in degrees
    double deltaTarget = curAngle - targetAngle; //Positive if robot is in a clockwise rotation

    if (targetAngle == -180) targetAngle = 180;
    if (targetAngle == 180) {
      if (curAngle < 0) {
        deltaTarget = targetAngle + curAngle;
      } else {
        deltaTarget = -targetAngle + curAngle;
      }
    }

    // Calculate correction values
    double angleCorrection = Math.copySign(Math.pow(Math.abs(deltaTarget / 30), 0.5), -deltaTarget);
    double offsetCorrection = (1 - Math.pow(range(angleCorrection), 2)) * curOffset;
    double distanceCorrection = (1 -Math.pow(range(offsetCorrection), 2)) * curDistance;

    if (Math.abs(deltaTarget) < 3 && Math.abs(curOffset) < 0.15 && Math.abs(curDistance) < 2)
      finished = true;

    angleCorrection = range(angleCorrection * ANGLE_CORRECTION) * SCALE_SPEED_Z;
    offsetCorrection = range(offsetCorrection * OFFSET_CORRECTION) * SCALE_SPEED_X;
    distanceCorrection = range(distanceCorrection * DISTANCE_CORRECTION) * SCALE_SPEED_Y;

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
