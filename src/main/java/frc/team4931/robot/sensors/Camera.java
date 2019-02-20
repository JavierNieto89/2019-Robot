package frc.team4931.robot.sensors;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.cscore.VideoSink;
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
  private static final boolean LOAD_EVERY_LOOP = false;

  private UsbCamera camera;
  private CvSink source;
  private Mat frame;
  private Button toggleUIButton;

  private RotatedRect uiRotatedRect;
  private Scalar uiScaler;

  private Thread captureThread;

  public Camera() {
    camera = new UsbCamera("Camera", RobotMap.CAMERA);
    camera.setResolution(RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT);
    camera.setFPS(30);
    camera.setVideoMode(PixelFormat.kMJPEG, RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT, 30);

    source = new CvSink("Camera");
    source.setSource(camera);

    //CvSource outputStream = new CvSource("Camera", PixelFormat.kBGR, RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT, 30);
    CvSource outputStream = CameraServer.getInstance().putVideo("Camera", RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT);
    outputStream.setVideoMode(PixelFormat.kMJPEG, RobotMap.CAMERA_WIDTH, RobotMap.CAMERA_HEIGHT, 30);
    outputStream.getProperty("width").set(RobotMap.CAMERA_WIDTH);
    outputStream.getProperty("height").set(RobotMap.CAMERA_HEIGHT);
    outputStream.getProperty("compression").set(30);
    outputStream.getProperty("default_compression").set(30);
    outputStream.getProperty("fps").set(30);

    frame = new Mat();

    if (!LOAD_EVERY_LOOP) {
      uiRotatedRect = new RotatedRect(
          new Point(RobotMap.CAMERA_UI_LOCATION_WIDTH, RobotMap.CAMERA_UI_LOCATION_HEIGHT),
          new Size(RobotMap.CAMERA_UI_WIDTH, RobotMap.CAMERA_UI_HEIGHT), 0);
      uiScaler = new Scalar(0, 255, 0);
    }

    toggleUIButton = new JoystickButton(Robot.getOperatorInput().getJoystick2(), RobotMap.CAMERA_UI_TOGGLE);

    captureThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        if (source.grabFrame(frame) == 0)
          continue;

        if (LOAD_EVERY_LOOP) {
          uiRotatedRect = new RotatedRect(
              new Point(RobotMap.CAMERA_UI_LOCATION_WIDTH, RobotMap.CAMERA_UI_LOCATION_HEIGHT),
              new Size(RobotMap.CAMERA_UI_WIDTH, RobotMap.CAMERA_UI_HEIGHT), 0);
          uiScaler = new Scalar(0, 255, 0);
        }

        if (toggleUIButton.get())
          Imgproc.ellipse(frame, uiRotatedRect, uiScaler, RobotMap.CAMERA_UI_LINE_WIDTH);

        outputStream.putFrame(frame);
      }
    });

    captureThread.start();
  }
}
