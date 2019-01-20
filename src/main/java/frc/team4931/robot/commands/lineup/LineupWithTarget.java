package frc.team4931.robot.commands.lineup;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4931.robot.Robot;
import frc.team4931.robot.commands.utilities.RotateToDegree;
import frc.team4931.robot.sensors.Pigeon;
import frc.team4931.robot.subsystems.Drivetrain;

public class LineupWithTarget extends Command {
  private static final String DISTANCE_KEY = "Vision Distance";
  private static final String OFFSET_KEY = "Vision Offset";
  private static final double ROTATION_SNAP_POINTS = 8;
  private static final double DEGREES_PER_POINT = 360 / ROTATION_SNAP_POINTS;
  private static final double ANGLE_CORRECTION = 0.5; // AKA Max speed
  private static final double OFFSET_CORRECTION = 0.2; // AKA speed per foot
  private static final double DISTANCE_CORRECTION = 0.2; // AKA speed per foot
  private Drivetrain drivetrain;
  private Pigeon pigeon;
  private boolean finished;

  public LineupWithTarget() {
    drivetrain = Robot.getDrivetrain();
    requires(drivetrain);
    setInterruptible(true);
  }

  @Override
  protected void initialize() {
    pigeon = Robot.getPigeon();
    finished = false;
  }

  @Override
  protected void execute() {
    // Distance and Offset are in feet
    var curDistance = SmartDashboard.getNumber(DISTANCE_KEY, -1);
    var curOffset = SmartDashboard.getNumber(OFFSET_KEY, -1);
    var curAngle = pigeon.getAngle();

    // Calculate how far from the nearest preset angle in degrees
    var targetAngle = Math.round(curAngle / DEGREES_PER_POINT) * DEGREES_PER_POINT;
    var deltaTarget = (targetAngle - curAngle); //Positive if robot is in a clockwise rotation

    // Calculate correction values
    var angleCorrection = -deltaTarget / (DEGREES_PER_POINT / 2) * ANGLE_CORRECTION;
    var offsetCorrection = Math.pow(Math.max(1 - Math.abs(angleCorrection), 0), 2) * -curOffset * OFFSET_CORRECTION;
    var distanceCorrection = Math.pow(Math.max(1 - Math.abs(offsetCorrection), 0), 2) * curDistance * DISTANCE_CORRECTION;

    if (Math.abs(deltaTarget) < 3 && Math.abs(curOffset) < 0.15 && Math.abs(curDistance) < 0.15)
      finished = true;

    drivetrain.driveCartesian(offsetCorrection, distanceCorrection, angleCorrection);
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
