/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4931.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import frc.team4931.robot.commands.hatchgrabber.*;
import frc.team4931.robot.commands.climber.*;
import frc.team4931.robot.commands.autonomous.*;
import frc.team4931.robot.commands.lineup.SetAutoAngle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInput {

  private Joystick joystick1;
  private Joystick joystick2;

  public OperatorInput() {
    joystick1 = new Joystick(RobotMap.JOYSTICK1);
    joystick2 = new Joystick(RobotMap.JOYSTICK2);

    Button shoot = new JoystickButton(joystick1, 1);
    shoot.whenPressed(new ShootHatch());

    Button changeVelcro = new JoystickButton(joystick1, 2);
    changeVelcro.whenPressed(new ChangeVelcroState());

    Button bamBam = new JoystickButton(joystick1, 7);
    bamBam.whenPressed(new ExtendHatchGrabber());

    Button retract = new JoystickButton(joystick1, 8);
    retract.whenPressed(new ResetHatchGrabber());

    Button pivotDown = new JoystickButton(joystick1, 9);
    pivotDown.whenPressed(new PivotDown());

    Button pivotReset = new JoystickButton(joystick1, 10);
    pivotReset.whenPressed(new PivotUp());

    Button unwindWench = new JoystickButton(joystick1, 5);
    unwindWench.whenPressed(new ExtendClimberArm());
    unwindWench.whenReleased(new ArmStop());

    Button windWench = new JoystickButton(joystick1, 3);
    windWench.whenPressed(new RetractClimberArm());
    windWench.whenReleased(new ArmStop());

    Button climberLatch = new JoystickButton(joystick1, 4);
    climberLatch.whenPressed(new Latch());

    Button climberRelease = new JoystickButton(joystick1, 6);
    climberRelease.whenPressed(new Release());

    Button getHatch = new JoystickButton(joystick1, 11);
    getHatch.whenPressed(new GetHatchAuto());

    Button placeHatch = new JoystickButton(joystick1, 12);
    placeHatch.whenPressed(new PlaceHatchAuto());

    Button setAutoAngle = new JoystickButton(joystick2, 2);
    setAutoAngle.whenReleased(new SetAutoAngle());
  }

  public Joystick getJoystick() {
    return joystick1;
  }

  public Joystick getJoystick2() {
    return joystick2;
  }
}
