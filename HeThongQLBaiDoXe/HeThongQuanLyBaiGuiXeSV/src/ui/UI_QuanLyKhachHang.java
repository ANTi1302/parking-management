package ui;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhachHang_Dao;
import dao.TheXe_Dao;
import entity.KhachHang;


public class UI_QuanLyKhachHang extends JFrame implements MouseListener, ActionListener{

	private JTable tblgoi;
	private DefaultTableModel tblModelGoi;
	private JButton btnTimKiem, btnXemTatCa,btnCapNhat,btnXoa,btnReset;
	private JTextField txtHoTen,txtEmail,txtTenTK,txtMK,txtSDT,txtMSSV,txtTheXe,txtTimKiemHoTen,txtTimKiemmail,txtTiemKiemSDT,txtTiemKiemMSSV;
	private JLabel lblHoTen,lblEmail,lblTenTK,lblMK,lblSDT,lblMSSV,lblTheXe,lblTimKiemHoTen,lblTimKiemmail,lblTiemKiemSDT,lblTiemKiemMSSV;
	private KhachHang_Dao khdao= new KhachHang_Dao();
	private TheXe_Dao theXe_Dao= new TheXe_Dao();
	public UI_QuanLyKhachHang() throws IOException, SQLException {
		ConnectDB.getInstance().connect();
		BufferedImage imagebycycle = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		setTitle("Quản lý khách hàng");
		setIconImage(imgbycycle.getImage());
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		setVisible(true);
		giaoDien();
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
		
		btnCapNhat =new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(38,40,43));
		btnCapNhat.setForeground(new Color(130,161,192));
		btnCapNhat.setBorder(new RoundedBorder(10));
		
		btnReset =new JButton("Đặt lại");
		btnReset.setBackground(new Color(38,40,43));
		btnReset.setForeground(new Color(130,161,192));
		btnReset.setBorder(new RoundedBorder(10));
		
		btnXoa =new JButton("Xóa");
		btnXoa.setBackground(new Color(38,40,43));
		btnXoa.setForeground(new Color(130,161,192));
		btnXoa.setBorder(new RoundedBorder(10));
		
		lblHoTen= new JLabel("Họ tên:");
		lblEmail= new JLabel("Email:");
		lblTenTK= new JLabel("Tên tài khoản:");
		lblMK= new JLabel("Mật khẩu:");
		lblSDT= new JLabel("Số điện thoại:");
		lblMSSV= new JLabel("MSSV:");
		lblTheXe= new JLabel("Thẻ Xe:");
		
		
		
		txtHoTen =new JTextField();
		int w1 = 190, w2 = 500, h = 40;
		btnXoa.setBounds(600, 80, 90, h);
		txtHoTen.setBounds(100, 20, 200, h);
		txtHoTen.setFont(new Font("Arial", 50, 20));
		btnCapNhat.setBounds(600, 20, 90, h);
		btnReset.setBounds(600, 140, 90, h);
		lblHoTen.setBounds(20, 20, 90, h);
		lblHoTen.setForeground(new Color(130,161,192));
		lblEmail.setBounds(20, 65, 90, h);
		lblEmail.setForeground(new Color(130,161,192));
		lblTenTK.setBounds(20, 110, 90, h);
		lblTenTK.setForeground(new Color(130,161,192));
		lblMK.setBounds(20, 155, 90, h);
		lblMK.setForeground(new Color(130,161,192));
		
		lblSDT.setBounds(310, 20, 90, h);
		lblSDT.setForeground(new Color(130,161,192));
		lblMSSV.setBounds(310, 65, 90, h);
		lblMSSV.setForeground(new Color(130,161,192));
		lblTheXe.setBounds(310, 110, 90, h);
		lblTheXe.setForeground(new Color(130,161,192));
		
		txtEmail =new JTextField();
		txtEmail.setBounds(100, 65, 200, h);
		txtEmail.setFont(new Font("Arial", 50, 20));
		
		txtTenTK =new JTextField();
		txtTenTK.setBounds(100, 110, 200, h);
		txtTenTK.setFont(new Font("Arial", 50, 20));
		
		txtMK =new JTextField();
		txtMK.setBounds(100, 155, 200, h);
		txtMK.setFont(new Font("Arial", 50, 20));
		
		txtSDT =new JTextField();
		txtSDT.setBounds(390, 20, 200, h);
		txtSDT.setFont(new Font("Arial", 50, 20));
		
		txtMSSV =new JTextField();
		txtMSSV.setBounds(390, 65, 200, h);
		txtMSSV.setFont(new Font("Arial", 50, 20));
		
		txtTheXe =new JTextField();
		txtTheXe.setBounds(390, 110, 200, h);
		txtTheXe.setFont(new Font("Arial", 50, 20));
		
		pnlNorth.setBorder(BorderFactory.createTitledBorder(null, null, TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlNorth.setBackground(new Color(53, 57, 65));
		pnlNorth.add(btnCapNhat);
		pnlNorth.add(btnReset);
		pnlNorth.add(btnXoa);
		pnlNorth.add(txtHoTen);
		pnlNorth.add(txtEmail);
		pnlNorth.add(txtTenTK);
		pnlNorth.add(txtMK);
		pnlNorth.add(txtSDT);
		pnlNorth.add(txtMSSV);
		pnlNorth.add(txtTheXe);
		pnlNorth.add(lblHoTen);
		pnlNorth.add(lblTenTK);
		pnlNorth.add(lblEmail);
		pnlNorth.add(lblMK);
		pnlNorth.add(lblSDT);
		pnlNorth.add(lblMSSV);
		pnlNorth.add(lblTheXe);
	
		pnlNorth.setLayout(null);
		pnlNorth.setBounds(20, 10, 720, 200);
		
		JPanel pnlEast;
		pnMain.add(pnlEast = new JPanel());
		
		lblTimKiemHoTen= new JLabel("Họ tên:");
		lblTimKiemmail= new JLabel("Email:");
		lblTiemKiemMSSV= new JLabel("MSSV:");
		lblTiemKiemSDT= new JLabel("SDT:");
		
		
		lblTimKiemHoTen.setBounds(20, 20, 90, h);
		lblTimKiemHoTen.setForeground(new Color(130,161,192));
		lblTimKiemmail.setBounds(20, 65, 90, h);
		lblTimKiemmail.setForeground(new Color(130,161,192));
		lblTiemKiemMSSV.setBounds(310, 20, 90, h);
		lblTiemKiemMSSV.setForeground(new Color(130,161,192));
		lblTiemKiemSDT.setBounds(310, 65, 90, h);
		lblTiemKiemSDT.setForeground(new Color(130,161,192));
		
		txtTimKiemHoTen =new JTextField();
		txtTimKiemHoTen.setBounds(100, 20, 200, h);
		txtTimKiemHoTen.setFont(new Font("Arial", 50, 20));
		
		txtTimKiemmail =new JTextField();
		txtTimKiemmail.setBounds(100, 65, 200, h);
		txtTimKiemmail.setFont(new Font("Arial", 50, 20));
		
		txtTiemKiemMSSV =new JTextField();
		txtTiemKiemMSSV.setBounds(350, 20, 200, h);
		txtTiemKiemMSSV.setFont(new Font("Arial", 50, 20));
		
		txtTiemKiemSDT =new JTextField();
		txtTiemKiemSDT.setBounds(350, 65, 200, h);
		txtTiemKiemSDT.setFont(new Font("Arial", 50, 20));
		
		btnTimKiem =new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(38,40,43));
		btnTimKiem.setForeground(new Color(130,161,192));
		btnTimKiem.setBorder(new RoundedBorder(10));
		btnTimKiem.setBounds(600, 20, 90, h);
		
		
		
		btnXemTatCa =new JButton("Xem tất cả");
		btnXemTatCa.setBackground(new Color(38,40,43));
		btnXemTatCa.setForeground(new Color(130,161,192));
		btnXemTatCa.setBorder(new RoundedBorder(10));
		btnXemTatCa.setBounds(600, 70, 90, h);
		
		
		pnlEast.setBorder(BorderFactory.createTitledBorder(null, "Hành động", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlEast.setBackground(new Color(53, 57, 65));
		pnlEast.add(btnTimKiem);
		pnlEast.add(btnXemTatCa);
		pnlEast.add(txtTimKiemHoTen);
		pnlEast.add(txtTimKiemmail);
		pnlEast.add(txtTiemKiemMSSV);
		pnlEast.add(txtTiemKiemSDT);
		pnlEast.add(lblTimKiemHoTen);
		pnlEast.add(lblTimKiemmail);
		pnlEast.add(lblTiemKiemMSSV);
		pnlEast.add(lblTiemKiemSDT);
		
		pnlEast.setLayout(null);
		pnlEast.setBounds(780, 10, 720, 200);

		
		String[] str = { "Mã SV", "Họ tên", "Tên tài khoản", "Số điện thoại","Email","Mã thẻ","Lượt gửi","Tạo lúc","Cập nhật lúc" };
		tblModelGoi = new DefaultTableModel(str, 0);
		tblgoi = new JTable(tblModelGoi);
		JScrollPane jsc = new JScrollPane(tblgoi);
		tblgoi.setAutoCreateRowSorter(true);
		jsc.getVerticalScrollBar().setUnitIncrement(16);
		jsc.setBounds(10, 20, 1460, 400);
		JPanel pnlSouth;
		pnMain.add(pnlSouth = new JPanel());
		pnlSouth.setBorder(BorderFactory.createTitledBorder(null, "Danh sách", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlSouth.setBackground(new Color(53, 57, 65));
		pnlSouth.add(jsc);
		pnlSouth.setLayout(null);
		pnlSouth.setBounds(20, 250, 1480, 520);
		
		ArrayList<KhachHang> listKH = khdao.gettalltbKhachHangCoNgayRa();
		for (KhachHang nv1 : listKH) {
			tblModelGoi.addRow(new Object[] { nv1.getID_custemer(), nv1.getFullname(), nv1.getStudent_id(), nv1.getPhone(),
					 nv1.getUsername(), nv1.getCard_id(),nv1.getEmail(), nv1.getCreated_at(), nv1.getUpdated_at() });
		}
		
		
		add(pnMain);
		
		
		tblgoi.addMouseListener(this);
		btnReset.addActionListener(this);
	}
	public static void main(String[] args) throws IOException, SQLException {
		new UI_QuanLyKhachHang();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
			 int row = tblgoi.getSelectedRow();
			 try {
				 txtHoTen.setText(tblgoi.getValueAt(row, 1).toString());
					txtEmail.setText(tblgoi.getValueAt(row, 4).toString());
					txtTenTK.setText(tblgoi.getValueAt(row, 2).toString());
					txtSDT.setText(tblgoi.getValueAt(row, 3).toString());
					txtTheXe.setText(tblgoi.getValueAt(row, 0).toString());
			} catch (Exception e2) {
				// TODO: handle exception
				 txtHoTen.setText(tblgoi.getValueAt(row, 0).toString());
					txtEmail.setText(tblgoi.getValueAt(row, 0).toString());
					txtTenTK.setText(tblgoi.getValueAt(row, 0).toString());
					txtSDT.setText(tblgoi.getValueAt(row, 0).toString());
					txtTheXe.setText(tblgoi.getValueAt(row, 0).toString());
			}
				
				
		   
		

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		Object o=e.getSource();
		if (o.equals(btnReset)) {
			 txtHoTen.setText("");
				txtEmail.setText("");
				txtTenTK.setText("");
				txtSDT.setText("");
				txtTheXe.setText("");
		}
	}
}
