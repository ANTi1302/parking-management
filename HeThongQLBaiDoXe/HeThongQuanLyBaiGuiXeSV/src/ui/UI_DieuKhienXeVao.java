package ui;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import connectDB.ConnectDB;
import dao.KhachHang_Dao;
import dao.ParkingHistory_Dao;
import dao.TheXe_Dao;
import entity.KhachHang;
import entity.ParkingHistory;
import entity.TheXe;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import nu.pattern.OpenCV;

public class UI_DieuKhienXeVao extends JFrame implements ActionListener {
	private JTextField txtThe, txtBienSoXe;
	JLabel txtMess;
	private JButton btnNhanVien, btnReset, btnMoCong, btnDongCong, btnXacNhan, btnXemTheoThe, btnXemBienSo,
			btnXemTrongBai;
	private Checkbox ckcTuDong;
	private JTable tblsv;
	private JLabel cameraScreen;
	private org.opencv.videoio.VideoCapture capture;
	private Mat mat;
	private boolean click;
	private String name;
	private DefaultTableModel tblModelSV;
	private Tesseract ts;
	private ImageIcon icon;
	VideoCapture camera;
	private TheXe_Dao theXe_Dao = new TheXe_Dao();
	private KhachHang_Dao kh_Dao = new KhachHang_Dao();
	private ParkingHistory_Dao parkingHistory_Dao=new ParkingHistory_Dao();
	private UI_GiaoDienChinh frmGiaDienChinh;

	public UI_DieuKhienXeVao() throws IOException, SQLException, TesseractException {
		ConnectDB.getInstance().connect();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		startShapeDetection(cameraScreen2, cameraScreen1, capture).run();
	
		BufferedImage imagebycycle = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		setTitle("Qu???n l?? xe v??o");
		setIconImage(imgbycycle.getImage());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		setVisible(true);
		giaoDien();
		startCamera();
	}

	private static class RoundedBorder implements Border {

		private int radius;

		RoundedBorder(int radius) {
			this.radius = radius;
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		}
	}

	private void giaoDien() {

		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBackground(new Color(53, 57, 65));
		JPanel pnlNorth;
		pnMain.add(pnlNorth = new JPanel());

//		cameraScreen1 = new JLabel();
//		cameraScreen1.setBounds(530, 20, 500, 500);
//		pnlNorth.add(cameraScreen1);
//
//		cameraScreen2 = new JLabel();
//		cameraScreen2.setBounds(530, 100, 500, 500);
//		pnlNorth.add(cameraScreen2);

		cameraScreen = new JLabel();
		cameraScreen.setBounds(20, 20, 900, 500);
		pnlNorth.add(cameraScreen);

		pnlNorth.setBorder(BorderFactory.createTitledBorder(null, "Camera", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("times new roman", Font.PLAIN, 12), new Color(111, 144, 199)));
		pnlNorth.setBackground(new Color(53, 57, 65));

		pnlNorth.setLayout(null);
		pnlNorth.setBounds(20, 10, 750, 550);

		JPanel pnlHeader;
		pnMain.add(pnlHeader = new JPanel());
		pnlHeader.setBorder(BorderFactory.createTitledBorder(null, "Th??ng tin th???", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("times new roman", Font.PLAIN, 12), new Color(111, 144, 199)));
		pnlHeader.setBackground(new Color(53, 57, 65));
		pnlHeader.setLayout(null);
		pnlHeader.setBounds(50, 600, 300, 150);

		txtThe = new JPasswordField();
		pnlHeader.add(txtThe);
		int w1 = 220, w2 = 500, h = 40;
		txtThe.setBounds(20, 20, w1, h);
		txtThe.setForeground(Color.WHITE);
		txtThe.setBackground(new Color(39, 40, 44));
		txtThe.requestFocus();
		txtThe.setEditable(true);

		btnNhanVien = new JButton("Anh thu");
		pnlHeader.add(btnNhanVien);
		btnNhanVien.setBounds(20, 100, w1, h);
		btnNhanVien.setBorderPainted(false);
		btnNhanVien.setContentAreaFilled(false);
		btnNhanVien.setFocusPainted(false);
		btnNhanVien.setForeground(Color.GREEN);

		JPanel pnlHeader02;
		pnMain.add(pnlHeader02 = new JPanel());
		pnlHeader02.setBorder(BorderFactory.createTitledBorder(null, "Th??ng tin bi???n s???", TitledBorder.LEFT,
				TitledBorder.TOP, new Font("times new roman", Font.PLAIN, 12), new Color(111, 144, 199)));
		pnlHeader02.setBackground(new Color(53, 57, 65));
		pnlHeader02.setLayout(null);
		pnlHeader02.setBounds(400, 600, 300, 150);

		txtBienSoXe = new JTextField();
		pnlHeader02.add(txtBienSoXe);
		txtBienSoXe.setForeground(Color.WHITE);
		txtBienSoXe.setBackground(new Color(39, 40, 44));
		int w11 = 220, w22 = 500, hh = 20;
		txtBienSoXe.setBounds(20, 20, w11, h);
		txtBienSoXe.setEditable(false);

		btnReset = new JButton("Th??? l???i");
		pnlHeader02.add(btnReset);
		btnReset.setBounds(100, 70, 100, hh);
		btnReset.setBackground(new Color(38, 40, 43));
		btnReset.setForeground(new Color(130, 161, 192));
		btnReset.setBorder(new RoundedBorder(10));
		btnNhanVien.setForeground(Color.GREEN);

		txtMess = new JLabel();
		pnlHeader02.add(txtMess);
		txtMess.setBounds(140, 100, w1, h);
		txtMess.setForeground(Color.GREEN);

		JPanel pnlHeader01;
		pnMain.add(pnlHeader01 = new JPanel());
		pnlHeader01.setLayout(null);
		pnlHeader01.setBorder(BorderFactory.createTitledBorder(null, "Tr??ch xu???t th??ng tin", TitledBorder.LEFT,
				TitledBorder.TOP, new Font("times new roman", Font.PLAIN, 12), new Color(111, 144, 199)));
		pnlHeader01.setBackground(new Color(53, 57, 65));
		pnlHeader01.setBounds(20, 570, 750, 190);

		btnMoCong = new JButton("M??? c???ng");
		btnMoCong.setBounds(80, 30, 200, 50);
		btnMoCong.setBackground(new Color(38, 40, 43));
		btnMoCong.setForeground(new Color(130, 161, 192));
		btnMoCong.setBorder(new RoundedBorder(10));
		btnDongCong = new JButton("????ng c???ng");
		btnDongCong.setBounds(350, 30, 200, 50);
		btnDongCong.setBackground(new Color(38, 40, 43));
		btnDongCong.setForeground(new Color(130, 161, 192));
		btnDongCong.setBorder(new RoundedBorder(10));
		JPanel pnlWest01;
		pnMain.add(pnlWest01 = new JPanel());
		pnlWest01.setBorder(BorderFactory.createTitledBorder(null, "??i???u khi???n g???p", TitledBorder.LEFT,
				TitledBorder.TOP, new Font("times new roman", Font.PLAIN, 12), new Color(111, 144, 199)));
		pnlWest01.setBackground(new Color(53, 57, 65));
		pnlWest01.setLayout(null);
		pnlWest01.setBounds(820, 30, 650, 100);
		pnlWest01.add(btnMoCong);
		pnlWest01.add(btnDongCong);

		btnXacNhan = new JButton("X??c nh???n v??o xe");
		btnXacNhan.setBounds(80, 30, 200, 50);
		btnXacNhan.setBackground(new Color(38, 40, 43));
		btnXacNhan.setForeground(new Color(130, 161, 192));
		btnXacNhan.setBorder(new RoundedBorder(10));
		ckcTuDong = new Checkbox("T??? ?????ng");
		ckcTuDong.setForeground(new Color(130, 161, 192));
		ckcTuDong.setBounds(500, 30, 60, 50);
		JPanel pnlWest02;
		pnMain.add(pnlWest02 = new JPanel());
		pnlWest02.setBorder(BorderFactory.createTitledBorder(null, "X??c nh???n xe", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("times new roman", Font.PLAIN, 12), new Color(111, 144, 199)));
		pnlWest02.add(btnXacNhan);
		pnlWest02.add(ckcTuDong);
		pnlWest02.setBackground(new Color(53, 57, 65));
		pnlWest02.setLayout(null);
		pnlWest02.setBounds(820, 130, 650, 100);

		btnXemTheoThe = new JButton("Xem theo th???");
		btnXemTheoThe.setBounds(80, 30, 150, 50);
		btnXemTheoThe.setBackground(new Color(38, 40, 43));
		btnXemTheoThe.setForeground(new Color(130, 161, 192));
		btnXemTheoThe.setBorder(new RoundedBorder(10));
		btnXemBienSo = new JButton("Xem theo bi???n s???");
		btnXemBienSo.setBounds(265, 30, 150, 50);
		btnXemBienSo.setBackground(new Color(38, 40, 43));
		btnXemBienSo.setForeground(new Color(130, 161, 192));
		btnXemBienSo.setBorder(new RoundedBorder(10));
		btnXemTrongBai = new JButton("Xem trong b??i");
		btnXemTrongBai.setBounds(450, 30, 150, 50);
		btnXemTrongBai.setBackground(new Color(38, 40, 43));
		btnXemTrongBai.setForeground(new Color(130, 161, 192));
		btnXemTrongBai.setBorder(new RoundedBorder(10));

		String[] str = { "Bien So Xe", "Barcode", "Gi??? v??o" };
		tblModelSV = new DefaultTableModel(str, 0);
		tblsv = new JTable(tblModelSV);
		JScrollPane jsc = new JScrollPane(tblsv);
		tblsv.setAutoCreateRowSorter(true);
		jsc.getVerticalScrollBar().setUnitIncrement(16);
		jsc.setBounds(10, 100, 620, 400);
		JPanel pnlWest03;
		pnMain.add(pnlWest03 = new JPanel());
		pnlWest03.setBorder(BorderFactory.createTitledBorder(null, "Qu???n l??", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("times new roman", Font.PLAIN, 12), new Color(111, 144, 199)));
		pnlWest03.add(btnXemTheoThe);
		pnlWest03.add(btnXemBienSo);
		pnlWest03.add(btnXemTrongBai);
		pnlWest03.add(jsc);
		pnlWest03.setForeground(new Color(64, 89, 154));
		pnlWest03.setBackground(new Color(53, 57, 65));
		pnlWest03.setLayout(null);
		pnlWest03.setBounds(820, 230, 650, 520);

		JPanel pnlWest;
		pnMain.add(pnlWest = new JPanel());
		pnlWest.setBorder(BorderFactory.createTitledBorder(null, "??i???u khi???n", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("times new roman", Font.PLAIN, 12), new Color(111, 144, 199)));
		pnlWest.setForeground(new Color(64, 89, 154));
		pnlWest.setBackground(new Color(53, 57, 65));
		pnlWest.setLayout(null);
		pnlWest.setBounds(800, 10, 700, 750);

		add(pnMain);
		btnDongCong.addActionListener(this);
		btnXacNhan.addActionListener(this);
		btnReset.addActionListener(this);
		btnMoCong.addActionListener(this);
		txtThe.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					cbbTimKiemTenNhanVien(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		txtThe.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				keyType(evt);
			}
		});
	}

	private void keyType(KeyEvent evt) {
		if ("".equals(txtThe.getText().trim())) {
			theXe_Dao.gettalltbThe();
			Object[] obj = new Object[] { "M?? The", "Barcode" };
			DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
			tblsv.setModel(tableModel);
			tblsv.setAutoCreateRowSorter(true);
			txtMess.setText("Erro!!!");
		}

	}

//public void cbbTimKiemTenNhanVien() {
//	
//}
	private void cbbTimKiemTenNhanVien(java.awt.event.KeyEvent evt) throws SQLException {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			ConnectDB.getInstance();
			Connection con = (Connection) ConnectDB.getConnection();
			Statement statement = con.createStatement();

			String cautruyvan = "";
			cautruyvan = "Select * from TheXe where barcode = " + txtThe.getText().trim() + " ";
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
			Object[] obj = new Object[] { "M?? The", "Barcode" };
			DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
			tblsv.setModel(tableModel);
			tblsv.setAutoCreateRowSorter(true);
			try {
				while (rs.next()) {
					Object[] item = new Object[2];
					item[0] = rs.getString("id");
					item[1] = rs.getString("barcode");

					tableModel.addRow(item);

				}
				txtMess.setText("H???p l??");
			} catch (SQLException ex) {
				System.out.println(ex.toString());
			}

		}

	}

	public static void main(String[] args) throws IOException, TesseractException, SQLException {
		
//		startShapeDetection(cameraScreen2, cameraScreen1, capture).run();
		new UI_DieuKhienXeVao();
		

	}

	public void startCamera() throws TesseractException, IOException {
		capture = new org.opencv.videoio.VideoCapture(0);
		mat = new Mat();
		byte[] imageData;
		
//		startShapeDetection(cameraScreen2, cameraScreen1, capture).run();
		while (true) {
			capture.read(mat);

			final MatOfByte buf = new MatOfByte();
			Imgcodecs.imencode(".jpg", mat, buf);

			imageData = buf.toArray();
			icon = new ImageIcon(imageData);
			cameraScreen.setIcon(icon);

			if (click) {
				name = JOptionPane.showInputDialog(this, "Enter image name");
				if (name == null) {
					name = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss").format(new Date());

				}

				Imgcodecs.imwrite("images/" + name + ".jpg", mat);
				ts = new Tesseract();
				ts.setDatapath("");
				ts.setLanguage("eng");
				String text = ts.doOCR(getImage("images/" + name + ".jpg"));
				txtBienSoXe.setText(text);
				System.out.println(text);
				if (txtBienSoXe.getText().equals("") || txtThe.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Ph???i nh???p d??? li???u tr?????c.");
				} else {
//					 String fullname, String student_id, 
//					String username, String password, String email, String phone, TheXe card_id, Date created_at, Date updated_at
					TheXe the = theXe_Dao.getTheTheoBarcode(String.valueOf(txtThe.getText()));

					KhachHang kh = new KhachHang(txtBienSoXe.getText(), the, new Date());
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					String datecreated = simpleDateFormat.format(kh.getCreated_at());
					kh_Dao.create(kh);
					ParkingHistory pr=new ParkingHistory(txtBienSoXe.getText(), new Date(), 5, kh_Dao.gettalltbKhachHangTheoIDCustumer(),name);
					parkingHistory_Dao.create(pr);
					tblModelSV.addRow(new Object[] { kh.getStudent_id(), the.getBarcode(), datecreated });
					tblsv.setModel(tblModelSV);
					tblsv.setAutoCreateRowSorter(true);
					txtThe.setText("");
					txtBienSoXe.setText("");
					txtThe.requestFocus();
				}
				click = false;

			}
		}

	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object sou = e.getSource();
		if (sou.equals(btnDongCong)) {
			try {
				frmGiaDienChinh = new UI_GiaoDienChinh();
				capture.release();

				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (sou.equals(btnXacNhan)) {
			click = true;

		} else if (sou.equals(btnReset)) {
			txtBienSoXe.setText("");
			click = true;

		} else if (sou.equals(btnMoCong)) {
			
		}

	}

	public static void createJFrame(final JLabel... panels) {
		final JFrame window = new JFrame("Shape Detection");
		window.setSize(new Dimension(panels.length * 640, 480));
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setLayout(new GridLayout(1, panels.length));

		for (final JLabel panel : panels) {
			window.add(panel);
		}

		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private BufferedImage getImage(String imgPath) throws IOException {
		// read image
		Mat mat = Imgcodecs.imread(imgPath);

		// changr image to gray scale
		Mat gray = new Mat();
		Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);

		// resize image
		Mat resized = new Mat();
		Size size = new Size(mat.width() * 1.9f, mat.height() * 1.9f);
		Imgproc.resize(gray, resized, size);

		// convert to buffered
		MatOfByte mof = new MatOfByte();
		byte imageByte[];
		Imgcodecs.imencode(".jpg", resized, mof);
		imageByte = mof.toArray();

		return ImageIO.read(new ByteArrayInputStream(imageByte));
	}

}
