package frc.team4931.robot.commands.lineup;

public enum Angles {
  FORWARD(0), FORWARD_RIGHT(30), RIGHT(90), BACKWARD_RIGHT(150), BACKWARD(180),
  BACKWARD_LEFT(-150), LEFT(-90), FORWARD_LEFT(-30);

  private int angle;

  Angles(int angle) {
    this.angle = angle;
  }

  int getAngle() {
    return this.angle;
  }
}
