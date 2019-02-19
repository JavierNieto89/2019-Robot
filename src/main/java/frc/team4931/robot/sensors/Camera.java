package frc.team4931.robot.sensors;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team4931.robot.Robot;
import frc.team4931.robot.RobotMap;

public class Camera {
  private static final boolean LOAD_EVERY_LOOP = true;

  private UsbCamera camera;
  private CvSink source;
  private Mat frame;
  private Button toggleUIButton;

  private RotatedRect uiRotatedRect;
  private Scalar uiScaler;

  private Thread captureThread;

  public Camera() {
    camera = CameraServer.getInstance().startAutomaticCapture(RobotMap.CAMERA);
    camera.setResolution(RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT);

    source = CameraServer.getInstance().getVideo();
    CvSource outputStream = CameraServer.getInstance().putVideo("Camera", RobotMap.CAMERA_WIDTH,
        RobotMap.CAMERA_HEIGHT);
    frame = new Mat();

    if (!LOAD_EVERY_LOOP) {
      uiRotatedRect = new RotatedRect(
          new Point(RobotMap.CAMERA_WIDTH / 2.0, RobotMap.CAMERA_UI_LOCATION_HEIGHT),
          new Size(RobotMap.CAMERA_UI_WIDTH, RobotMap.CAMERA_UI_HEIGHT), 0);
      uiScaler = new Scalar(0, 0, 255);
    }

    toggleUIButton = new JoystickButton(Robot.getOperatorInput().getJoystick(), RobotMap.CAMERA_UI_TOGGLE);

    captureThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        source.grabFrame(frame);

        if (LOAD_EVERY_LOOP) {
          uiRotatedRect = new RotatedRect(
              new Point(RobotMap.CAMERA_WIDTH / 2.0, RobotMap.CAMERA_UI_LOCATION_HEIGHT),
              new Size(RobotMap.CAMERA_UI_WIDTH, RobotMap.CAMERA_UI_HEIGHT), 0);
          uiScaler = new Scalar(0, 0, 255);
        }

        if (toggleUIButton.get())
          Imgproc.ellipse(frame, uiRotatedRect, uiScaler, RobotMap.CAMERA_UI_LINE_WIDTH);

        outputStream.putFrame(frame);
      }
    });

    captureThread.start();
  }
}
