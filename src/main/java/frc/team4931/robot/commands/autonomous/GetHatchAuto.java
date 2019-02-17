package frc.team4931.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4931.robot.Robot;
import frc.team4931.robot.commands.hatchgrabber.ExtendVelcro;
import frc.team4931.robot.commands.hatchgrabber.ResetHatchGrabber;
import frc.team4931.robot.commands.hatchgrabber.RetractVelcro;
import frc.team4931.robot.commands.lineup.LineupWithTarget;
import frc.team4931.robot.commands.utilities.DriveForward;

public class GetHatchAuto extends CommandGroup {
  public GetHatchAuto() {
    setInterruptible(true);

    addSequential(new LineupWithTarget());
    addParallel(new ExtendVelcro());
    addParallel(new ResetHatchGrabber());

    addSequential(new DriveForward(0.15, 500));

    addSequential(new RetractVelcro());
  }

  @Override
  protected void interrupted() {
    Robot.getHatchGrabber().retractVelcro();
  }
}
