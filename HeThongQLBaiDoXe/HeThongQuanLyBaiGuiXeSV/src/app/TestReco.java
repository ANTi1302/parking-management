package app;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TestReco {
	Tesseract ts;

	public TestReco() throws TesseractException, IOException {
		ts = new Tesseract();
		ts.setDatapath("");
		ts.setLanguage("eng");
		String text = ts.doOCR(getImage("images/demo02.png"));
		System.out.println(text);
	}

	private BufferedImage getImage(String imgPath) throws IOException {
		// read image
		Mat mat = Imgcodecs.imread(imgPath);
		
		//changr image to gray scale
		Mat gray= new Mat();
		Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);
		
		
		//resize image
		Mat resized= new Mat();
		Size size=new Size(mat.width()*1.9f, mat.height()*1.9f);
		Imgproc.resize(gray, resized, size);
		
		//convert to buffered
		MatOfByte mof= new MatOfByte();
		byte imageByte[];
		Imgcodecs.imencode(".png", resized, mof);
		imageByte= mof.toArray();
		
		
		return ImageIO.read(new ByteArrayInputStream(imageByte));
	}

	public static void main(String[] args) throws TesseractException, IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		System.out.println("aa");
		new TestReco();
	}
}
