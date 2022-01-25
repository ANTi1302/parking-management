package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import nu.pattern.OpenCV;

public class ShapeDetection {

  

    static Runnable startShapeDetection(final JPanel cameraFeed,
                                                final JPanel processedFeed,
                                                final VideoCapture camera) {
        return () -> {
            final Mat frame = new Mat();

            while (true) {
                // Read frame from camera
                camera.read(frame);

                // Process frame
                final Mat processed = ShapeDetectionUtil.processImage(frame);

                // Mark outer contour
                ShapeDetectionUtil.markOuterContour(processed, frame);

                // Draw current frame
                ShapeDetectionUtil.drawImage(frame, cameraFeed);

                // Draw current processed image (for debugging)
                ShapeDetectionUtil.drawImage(processed, processedFeed);
            }
        };
    }
}
