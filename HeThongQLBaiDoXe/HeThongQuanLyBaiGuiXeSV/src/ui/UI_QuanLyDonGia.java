package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class UI_QuanLyDonGia extends JFrame {

	public UI_QuanLyDonGia() throws IOException {

		BufferedImage imagebycycle = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		setTitle("Quản lý đơn giá");
		setIconImage(imgbycycle.getImage());
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		setVisible(true);
		giaoDien();
	
	
	}
	
	private void giaoDien() {

		
		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBackground(new Color(53, 57, 65));
		JPanel pnlNorth;
		pnMain.add(pnlNorth = new JPanel());
		
		
		pnlNorth.setBorder(BorderFactory.createTitledBorder(null, "Đơn giá hằng ngày", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlNorth.setBackground(new Color(53, 57, 65));
		pnlNorth.setLayout(null);
		pnlNorth.setBounds(20, 10, 680, 750);
		
		JPanel pnlEast;
		pnMain.add(pnlEast = new JPanel());
		
		
		pnlEast.setBorder(BorderFactory.createTitledBorder(null, "Giá thời gian đặt biệt", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlEast.setBackground(new Color(53, 57, 65));
		pnlEast.setLayout(null);
		pnlEast.setBounds(750, 10, 750, 750);
		
		
		
		add(pnMain);
	}

	public static void main(String[] args) throws IOException {
		new UI_QuanLyDonGia();
	}
}
