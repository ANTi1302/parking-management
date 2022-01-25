package ui;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class UI_QuanLyNhanVien extends JFrame{

	public UI_QuanLyNhanVien() throws IOException {

		BufferedImage imagebycycle = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		setTitle("Quản lý nhân viên");
		setIconImage(imgbycycle.getImage());
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		setVisible(true);
		giaoDien();
	}
	
	private void giaoDien() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) throws IOException {
		new UI_QuanLyNhanVien();
	}
}
