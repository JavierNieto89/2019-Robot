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
import edu.wpi.first.wpilibj.command.*;
import frc.team4931.robot.commands.lineup.SetAutoAngle;
import frc.team4931.robot.enums.Angles;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * ^ Rhetorical way of saying when an instance of this class is created it'll create Joystick objects.
 * Those objects then have listeners setup in the main WPI Tread that will submit a command when the bound
 * physical action is taken. Therefore realistic speaking only one OperatorInput object can be used.
 */
public class OperatorInput {

  private Joystick joystick1;
  private Joystick joystick2;

  public OperatorInput() {
    joystick1 = new Joystick(RobotMap.JOYSTICK1);
    joystick2 = new Joystick(RobotMap.JOYSTICK2);

    ////////////////////JOYSTICK 1/////////////////////////
    Button shoot = new JoystickButton(joystick1, 1);
    shoot.whenPressed(new ShootHatch());

    Button changeVelcro = new JoystickButton(joystick1, 2);
    changeVelcro.whenPressed(new ChangeVelcroState());

    Button pivotDown = new JoystickButton(joystick1, 3);
    pivotDown.whenPressed(new PivotDown());

    Button placeHatch = new JoystickButton(joystick1, 4);
    placeHatch.whenPressed(new PlaceHatchAuto());

    Button pivotReset = new JoystickButton(joystick1, 5);
    pivotReset.whenPressed(new PivotUp());

    Button getHatch = new JoystickButton(joystick1, 6);
    getHatch.whenPressed(new GetHatchAuto());

    Button bamBam = new JoystickButton(joystick1, 7);
    bamBam.whenPressed(new ExtendHatchGrabber());

    Button retract = new JoystickButton(joystick1, 8);
    retract.whenPressed(new ResetHatchGrabber());

    ///////////////////JOYSTICK 2/////////////////////////
    Button windWench = new JoystickButton(joystick2, 3);
    windWench.whenPressed(new RetractClimberArm());
    windWench.whenReleased(new ArmStop());

    Button climberLatch = new JoystickButton(joystick2, 6);
    climberLatch.whenPressed(new Latch());

    Button unwindWench = new JoystickButton(joystick2, 5);
    unwindWench.whenPressed(new ExtendClimberArm());
    unwindWench.whenReleased(new ArmStop());

    Button climberRelease = new JoystickButton(joystick2, 4);
    climberRelease.whenPressed(new Release());

    Button setAutoAngle = new JoystickButton(joystick2, 12);
    setAutoAngle.whenPressed(new SetAutoAngle());
  }

  public Joystick getJoystick() {
    return joystick1;
  }

  public Joystick getJoystick2() {
    return joystick2;
  }
}
