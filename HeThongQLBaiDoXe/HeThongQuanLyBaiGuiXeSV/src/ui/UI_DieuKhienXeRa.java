package ui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
import org.opencv.imgcodecs.Imgcodecs;

import connectDB.ConnectDB;
import dao.KhachHang_Dao;
import dao.ParkingHistory_Dao;
import dao.TheXe_Dao;
import entity.KhachHang;


public class UI_DieuKhienXeRa extends JFrame implements ActionListener{
	private JTextField txtBienSo, txtBienSoXe,txtID;
	private JButton btnNhanVien, btnReset,btnMoCong,btnDongCong,btnXacNhan,btnXemTheoThe,btnXemBienSo,btnXemTrongBai;
	private Checkbox ckcTuDong;
	private JTable tblsv;
	private JLabel cameraScreen,cameraScreen1;
	private org.opencv.videoio.VideoCapture capture;
	private Mat mat;
	private String name;
	private boolean click;
	private DefaultTableModel tblModelSV;
	private UI_GiaoDienChinh frmGiaDienChinh;
	private TheXe_Dao theXe_Dao= new TheXe_Dao();
	private KhachHang_Dao kh_Dao= new KhachHang_Dao();
	private ParkingHistory_Dao parkingHistory_Dao= new ParkingHistory_Dao();
	
	public UI_DieuKhienXeRa() throws IOException, SQLException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		ConnectDB.getInstance().connect();
		BufferedImage imagebycycle = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		setTitle("Qu???n l?? xe ra");
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
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
	private void giaoDien() {
		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBackground(new Color(53, 57, 65));
		JPanel pnlNorth;
		pnMain.add(pnlNorth = new JPanel());
		
		cameraScreen1 = new JLabel();
		cameraScreen1.setBounds(400, 20, 500, 500);
		cameraScreen1.setBackground(Color.BLUE);
		pnlNorth.add(cameraScreen1);
		
		cameraScreen= new JLabel();
		cameraScreen.setBounds(20, 20, 350, 500);
		pnlNorth.add(cameraScreen);
		
		
	
		pnlNorth.setBorder(BorderFactory.createTitledBorder(null, "Camera", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlNorth.setBackground(new Color(53, 57, 65));
		pnlNorth.setLayout(null);
		pnlNorth.add(cameraScreen);
		pnlNorth.setBounds(20, 10, 750, 550);

		JPanel pnlHeader;
		pnMain.add(pnlHeader = new JPanel());
		pnlHeader.setBorder(BorderFactory.createTitledBorder(null, "Th??ng tin check in", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlHeader.setBackground(new Color(53, 57, 65));
		pnlHeader.setLayout(null);
		pnlHeader.setBounds(50, 600, 300, 150);

		txtBienSo = new JPasswordField();
		pnlHeader.add(txtBienSo);
		int w1 = 220, w2 = 500, h = 40;
		txtBienSo.setBounds(20, 20, w1, h);
		txtBienSo.setFont(new Font("Arial", 50, 20));
		txtBienSo.setForeground(Color.WHITE);
		txtBienSo.setBackground(new Color(39,40,44));
		txtBienSo.setEditable(true);
		
		txtID = new JTextField();
		pnlHeader.add(txtID);
		txtID.setBounds(20, 65, w1, h);
		txtID.setFont(new Font("Arial", 50, 20));
		txtID.setForeground(Color.WHITE);
		txtID.setBackground(new Color(39,40,44));
		txtID.setEditable(false);

		btnNhanVien = new JButton("Anh thu");
		pnlHeader.add(btnNhanVien);
		btnNhanVien.setBounds(20, 100, w1, h);
		btnNhanVien.setBorderPainted(false);
		btnNhanVien.setContentAreaFilled(false);
		btnNhanVien.setFocusPainted(false);
		btnNhanVien.setForeground(Color.GREEN);

		JPanel pnlHeader02;
		pnMain.add(pnlHeader02 = new JPanel());
		pnlHeader02.setBorder(BorderFactory.createTitledBorder(null, "Th??ng tin bi???n s???", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlHeader02.setBackground(new Color(53, 57, 65));
		pnlHeader02.setLayout(null);
		pnlHeader02.setBounds(400, 600, 300, 150);

		txtBienSoXe = new JTextField();
		pnlHeader02.add(txtBienSoXe);
		txtBienSoXe.setForeground(Color.WHITE);
		txtBienSoXe.setBackground(new Color(39,40,44));
		int w11 = 220, w22 = 500, hh = 20;
		txtBienSoXe.setBounds(20, 20, w11, h);
		txtBienSoXe.setFont(new Font("Arial", 50, 20));
		txtBienSoXe.setEditable(false);

		btnReset = new JButton("Th??? l???i");
		pnlHeader02.add(btnReset);
		btnReset.setBounds(100, 70, 100, hh);
		btnReset.setBackground(new Color(38,40,43));
		btnReset.setForeground(new Color(130,161,192));
		btnReset.setBorder(new RoundedBorder(10));
		btnNhanVien.setForeground(Color.GREEN);

		btnReset = new JButton();
		pnlHeader02.add(btnReset);
		btnReset.setBounds(40, 100, w1, h);
		btnReset.setBorderPainted(false);
		btnReset.setContentAreaFilled(false);
		btnReset.setFocusPainted(false);
		btnReset.setForeground(Color.YELLOW);

		JPanel pnlHeader01;
		pnMain.add(pnlHeader01 = new JPanel());
		pnlHeader01.setLayout(null);
		pnlHeader01.setBorder(BorderFactory.createTitledBorder(null, "Tr??ch xu???t th??ng tin", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlHeader01.setBackground(new Color(53, 57, 65));
		pnlHeader01.setBounds(20, 570, 750, 190);

		btnMoCong = new JButton("M??? c???ng");
		btnMoCong.setBounds(80, 30, 200, 50);
		btnMoCong.setBackground(new Color(38,40,43));
		btnMoCong.setForeground(new Color(130,161,192));
		btnMoCong.setBorder(new RoundedBorder(10));
		btnDongCong = new JButton("????ng c???ng");
		btnDongCong.setBounds(350, 30, 200, 50);
		btnDongCong.setBackground(new Color(38,40,43));
		btnDongCong.setForeground(new Color(130,161,192));
		btnDongCong.setBorder(new RoundedBorder(10));
		JPanel pnlWest01;
		pnMain.add(pnlWest01 = new JPanel());
		pnlWest01.setBorder(BorderFactory.createTitledBorder(null, "??i???u khi???n g???p", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlWest01.setBackground(new Color(53, 57, 65));
		pnlWest01.setLayout(null);
		pnlWest01.setBounds(820, 30, 650, 100);
		pnlWest01.add(btnMoCong);
		pnlWest01.add(btnDongCong);
		
		btnXacNhan = new JButton("X??c nh???n th??ng tin kh???p");
		btnXacNhan.setBounds(80, 30, 200, 50);
		btnXacNhan.setBackground(new Color(38,40,43));
		btnXacNhan.setForeground(new Color(130,161,192));
		btnXacNhan.setBorder(new RoundedBorder(10));
		ckcTuDong= new Checkbox("T??? ?????ng");
		ckcTuDong.setBackground(new Color(38,40,43));
		ckcTuDong.setForeground(new Color(130,161,192));
		ckcTuDong.setBounds(500, 30, 60, 50);
		JPanel pnlWest02;
		pnMain.add(pnlWest02 = new JPanel());
		pnlWest02.setBorder(BorderFactory.createTitledBorder(null, "X??c nh???n xe", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlWest02.add(btnXacNhan);
		pnlWest02.add(ckcTuDong);
		pnlWest02.setBackground(new Color(53, 57, 65));
		pnlWest02.setLayout(null);
		pnlWest02.setBounds(820, 130, 650, 100);
		
		btnXemBienSo = new JButton("Xem theo bi???n s???");
		btnXemBienSo.setBounds(80, 30, 200, 50);
		btnXemBienSo.setBackground(new Color(38,40,43));
		btnXemBienSo.setForeground(new Color(130,161,192));
		btnXemBienSo.setBorder(new RoundedBorder(10));
		btnXemTrongBai = new JButton("Xem trong b??i");
		btnXemTrongBai.setBounds(350, 30, 200, 50);
		btnXemTrongBai.setBackground(new Color(38,40,43));
		btnXemTrongBai.setForeground(new Color(130,161,192));
		btnXemTrongBai.setBorder(new RoundedBorder(10));
		
		// B???ng Nh??n Vi??n
		String[] str = { "ID", "H??? & T??n", "Bi???n s???", "Gi??? v??o" };
		tblModelSV = new DefaultTableModel(str, 0);
		tblsv = new JTable(tblModelSV);
		JScrollPane jsc = new JScrollPane(tblsv);
		tblsv.setAutoCreateRowSorter(true);
		jsc.getVerticalScrollBar().setUnitIncrement(16);
		jsc.setBounds(10, 100, 620, 400);
		JPanel pnlWest03;
		pnMain.add(pnlWest03 = new JPanel());
		pnlWest03.setBorder(BorderFactory.createTitledBorder(null, "Qu???n l??", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlWest03.add(btnXemBienSo);
		pnlWest03.add(btnXemTrongBai);
		pnlWest03.add(jsc);
		pnlWest03.setForeground(new Color(64, 89, 154));
		pnlWest03.setBackground(new Color(53, 57, 65));
		pnlWest03.setLayout(null);
		pnlWest03.setBounds(820, 230, 650, 520);

		JPanel pnlWest;
		pnMain.add(pnlWest = new JPanel());
		pnlWest.setBorder(BorderFactory.createTitledBorder(null, "??i???u khi???n", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlWest.setForeground(new Color(64, 89, 154));
		pnlWest.setBackground(new Color(53, 57, 65));
		pnlWest.setLayout(null);
		pnlWest.setBounds(800, 10, 700, 750);

		add(pnMain);
		btnDongCong.addActionListener(this);
		btnXacNhan.addActionListener(this);
		btnReset.addActionListener(this);
		txtBienSo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					cbbTimKiemTenNhanVien(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		txtBienSo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				keyType(evt);
			}
		});
	}
	private void keyType(KeyEvent evt) {
		if ("".equals(txtBienSo.getText().trim())) {
			txtBienSo.setText("");
			txtBienSo.setEditable(true);
			txtBienSo.requestFocus();
			txtBienSoXe.setText("");
			txtBienSoXe.setEditable(false);
			txtID.setText("");
			txtID.setEditable(false);
			JOptionPane.showMessageDialog(this, "Card not find");
			btnReset.setText("Eror!!!");
			
		}
		
	}
//public void cbbTimKiemTenNhanVien() {
//	
//}
private void cbbTimKiemTenNhanVien(java.awt.event.KeyEvent evt) throws SQLException, IOException {
	if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
		KhachHang kh = null;
		kh = kh_Dao.gettalltbKhachHangTheoCard( String.valueOf(txtBienSo.getText()));
		if (kh == null) {
			txtBienSo.setText("");
			txtBienSo.setEditable(true);
			txtBienSo.requestFocus();
			txtBienSoXe.setText("");
			txtBienSoXe.setEditable(false);
			txtID.setText("");
			txtID.setEditable(false);
			JOptionPane.showMessageDialog(this, "Card not find");
		} else {
//			txtBienSo.setText(kh.getCard_id().getBarcode());
			txtID.setText(String.valueOf(kh.getCard_id().getId()));
			txtBienSoXe.setText(kh.getStudent_id());
			btnReset.setText("???? tr??ch xu???t");
			BufferedImage imagebycycle;
			
			ConnectDB.getInstance();
			Connection con = (Connection) ConnectDB.getConnection();
			Statement statement = con.createStatement();
			String cautruyvan = "";
			cautruyvan = "\r\n"
					+ "SELECT  ParkingHistory.license_plate, ParkingHistory.check_in_at, ParkingHistory.check_out_at, ParkingHistory.img\r\n"
					+ "FROM     Custemer INNER JOIN\r\n"
					+ "                  ParkingHistory ON Custemer.ID_custemer = ParkingHistory.custemer_id INNER JOIN\r\n"
					+ "                  TheXe ON Custemer.card_id = TheXe.id\r\n"
					+ "GROUP BY Custemer.ID_custemer, Custemer.fullname, ParkingHistory.license_plate, ParkingHistory.check_in_at, ParkingHistory.check_out_at, TheXe.barcode, ParkingHistory.img\r\n"
					+ "having TheXe.barcode=" + txtBienSo.getText().trim() + " ";
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
			Object[] obj = new Object[] { "Bi???n s??? xe", "Gi??? v??o","Gi??? ra","Img" };
			DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
			tblsv.setModel(tableModel);
			tblsv.setAutoCreateRowSorter(true);
			try {
				String name = "";
				while (rs.next()) {
					Object[] item = new Object[4];
					item[0] = rs.getString("license_plate");
					item[1] = rs.getString("check_in_at");
					item[2] = rs.getString("check_out_at");
					item[3] = rs.getString("img");
					name=rs.getString("img");
					System.out.println(name);
					tableModel.addRow(item);

				}

				imagebycycle = ImageIO.read(new File("images/"+name+".jpg"));
				ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(490, 490, imagebycycle.SCALE_SMOOTH));
				cameraScreen1.setIcon(imgbycycle);
			} catch (SQLException ex) {
				System.out.println(ex.toString());
			}

		}}
		
	}
	public static void main(String[] args) throws IOException, SQLException {
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new UI_DieuKhienXeRa();
	}
	public void startCamera() throws IOException, SQLException {
		capture= new org.opencv.videoio.VideoCapture(0);
		mat = new Mat();
		byte[] imageData;
		
		ImageIcon icon;
		while (true) {
			capture.read(mat);
			
			
			
			final MatOfByte buf= new MatOfByte();
			Imgcodecs.imencode(".jpg", mat, buf);
			
			imageData= buf.toArray();
			icon= new ImageIcon(imageData);
			cameraScreen.setIcon(icon);
			
			if (click) {
				 name= JOptionPane.showInputDialog(this,"Enter image name");
				if (name==null) {
					name= new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss").format(new Date());
				}
				
				Imgcodecs.imwrite("images/"+name+".jpg", mat);
				click=false;
//				kh_Dao.updateMaChucVu(txtID.getText());
//				txtBienSo.setText("");
//				txtBienSo.setEditable(true);
//				txtBienSo.requestFocus();
//				txtBienSoXe.setText("");
//				txtBienSoXe.setEditable(false);
//				txtID.setText("");
//				txtID.setEditable(false);
//				cameraScreen1.setIcon(null);
				
				ConnectDB.getInstance();
				Connection con=(Connection) ConnectDB.getConnection();
				PreparedStatement stmt=null;
				int n=0;
				try {
					stmt=con.prepareStatement("UPDATE [dbo].[ParkingHistory]\r\n"
							+ "SET [check_out_at] = GETDATE()\r\n"
							+ "WHERE EXISTS(\r\n"
							+ "\r\n"
							+ "SELECT [license_plate],[check_in_at],[check_out_at]\r\n"
							+ "FROM     Custemer INNER JOIN\r\n"
							+ "                  ParkingHistory ON Custemer.ID_custemer = ParkingHistory.custemer_id INNER JOIN\r\n"
							+ "                  TheXe ON Custemer.card_id = TheXe.id\r\n"
							+ "		where TheXe.barcode=?\r\n"
							+ "				  )");
					
					stmt.setString(1,txtBienSo.getText());
					n=stmt.executeUpdate();
					
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				finally {
					try {
						stmt.close();
					} catch (SQLException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
				kh_Dao.updateMaChucVu(txtID.getText());
				Object[] obj = new Object[] { "ID", "H??? t??n","Bi???n s??? xe","Gi??? v??o" };
				DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
				tblsv.setModel(tableModel);
				tblsv.setAutoCreateRowSorter(true);
				txtBienSo.setText("");
				txtBienSo.setEditable(true);
				txtBienSo.requestFocus();
				txtBienSoXe.setText("");
				txtBienSoXe.setEditable(false);
				txtID.setText("");
				txtID.setEditable(false);
				cameraScreen1.setIcon(null);
			}
			//Chay doc file
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
			click=true;
		} else if (sou.equals(btnReset)) {
			click=true;
		}
		
	}
}
