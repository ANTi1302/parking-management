package ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import nu.pattern.OpenCV;

public class ShapeDetection {


    static Runnable startShapeDetection(final JLabel cameraScreen,
                                                final JLabel cameraScreen1,
                                                 VideoCapture camera) {
        return () -> {
//        	OpenCV.loadShared();

//	        final VideoCapture camera1 = new VideoCapture(0);
            final Mat frame = new Mat();
            while (true) {
                // Read frame from camera
                camera.read(frame);

                // Process frame
                final Mat processed = ShapeDetectionUtil.processImage(frame);

                // Mark outer contour
                ShapeDetectionUtil.markOuterContour(processed, frame);

                // Draw current frame
                ShapeDetectionUtil.drawImage(frame, cameraScreen);

                // Draw current processed image (for debugging)
                ShapeDetectionUtil.drawImage(processed, cameraScreen1);
            }
        };
    }
   
}
