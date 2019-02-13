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

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInput {

  private Joystick joystick;

  public OperatorInput() {
    joystick = new Joystick(RobotMap.JOYSTICK);

    Button shoot = new JoystickButton(joystick, 1);
    shoot.whenPressed(new ShootHatch());

    Button changeVelcro = new JoystickButton(joystick, 2);
    changeVelcro.whenPressed(new ChangeVelcroState());

    Button bamBam = new JoystickButton(joystick, 7);
    bamBam.whenPressed(new ExtendHatchGrabber());

    Button retract = new JoystickButton(joystick, 8);
    retract.whenPressed(new ResetHatchGrabber());

    Button pivotDown = new JoystickButton(joystick, 9);
    pivotDown.whenPressed(new PivotDown());

    Button pivotReset = new JoystickButton(joystick, 10);
    pivotReset.whenPressed(new PivotUp());

    Button unwindWench = new POVButton(joystick, 0);
    unwindWench.whileHeld(new ExtendClimberArm());
    unwindWench.whenReleased(new ArmStop());

    Button windWench = new POVButton(joystick, 180);
    windWench.whileHeld(new RetractClimberArm());
    windWench.whenReleased(new ArmStop());

    Button latch = new JoystickButton(joystick, 3);
    latch.whenPressed(new Latch());

    Button release = new JoystickButton(joystick, 4);
    release.whenPressed(new Release());

    Button getHatch = new JoystickButton(joystick, 11);
    getHatch.whenPressed(new GetHatchAuto());

    Button placeHatch = new JoystickButton(joystick, 12);
    placeHatch.whenPressed(new PlaceHatchAuto());
  }

  public Joystick getJoystick() {
    return joystick;
  }
}
