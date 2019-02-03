package frc.team4931.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4931.robot.Robot;
import frc.team4931.robot.commands.hatchgrabber.ExtendVelcro;
import frc.team4931.robot.commands.hatchgrabber.ShootHatch;
import frc.team4931.robot.commands.lineup.LineupWithTarget;

public class PlaceHatchAuto extends CommandGroup {
  public PlaceHatchAuto() {
    setInterruptible(true);

    addSequential(new LineupWithTarget());
    addParallel(new ExtendVelcro());

    addSequential(new ShootHatch());
  }

  @Override
  protected void interrupted() {
    Robot.getHatchGrabber().retractVelcro();
  }
}
