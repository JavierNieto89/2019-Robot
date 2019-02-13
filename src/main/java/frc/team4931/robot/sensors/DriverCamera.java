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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4931.robot.Robot;
import frc.team4931.robot.enums.Angles;
import frc.team4931.robot.sensors.Pigeon;
import frc.team4931.robot.subsystems.Drivetrain;

public class DriverCamera {

    public DriverCamera() {
      new Thread(() -> {
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(640, 480);
        
        CvSink cvSink = CameraServer.getInstance().getVideo();
        CvSource outputStream = CameraServer.getInstance().putVideo("Camera", 640, 480);
        
        Mat source = new Mat();
        
        while(!Thread.interrupted()) {
            cvSink.grabFrame(source);
            Imgproc.ellipse (source,new RotatedRect (new Point(200, 150),new Size(260, 180), 180 
              ),
              new Scalar(0, 0, 255),
              10);
            outputStream.putFrame(source);
        }
    }).start();
    }
}
