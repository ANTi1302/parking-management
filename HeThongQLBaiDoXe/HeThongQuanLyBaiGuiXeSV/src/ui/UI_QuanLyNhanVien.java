package ui;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


public class UI_QuanLyNhanVien extends JFrame{
	private JTable tblgoi;
	private DefaultTableModel tblModelGoi;
	private JButton btnTimKiem, btnXoa,btnCapNhat,btnReset,btnXemTatCa;
	private JLabel lblHoTen,lblNgaySinh,lblCMND,lblTenTK,lblMK,lblChucVu,lblTKHoTen, lblTKCMND;
	private JDateChooser jclNgaySinh;
	private ButtonGroup groupGT;
	private JRadioButton jrbNam, jrbNu;
	private JTextField txtHoTen,txtNgaySinh,txtCMND,txtTenTK,txtMK,txtChucVu,txtTKHoTen, txtTKCMND;
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
		// TODO Auto-generated method stub
		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBackground(new Color(53, 57, 65));
		JPanel pnlNorth;
		pnMain.add(pnlNorth = new JPanel());
		
		btnTimKiem =new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(38,40,43));
		btnTimKiem.setForeground(new Color(130,161,192));
		btnTimKiem.setBorder(new RoundedBorder(10));
		
		btnXemTatCa =new JButton("Xem tất cả");
		btnXemTatCa.setBackground(new Color(38,40,43));
		btnXemTatCa.setForeground(new Color(130,161,192));
		btnXemTatCa.setBorder(new RoundedBorder(10));
		
		txtTKHoTen =new JTextField();
		txtTKCMND =new JTextField();
		int w1 = 190, w2 = 500, h = 40;
		
		
		btnTimKiem.setBounds(500, 20, w1, h);
		btnXemTatCa.setBounds(500, 90, w1, h);
		
		lblTKHoTen= new JLabel("Họ tên: ");
		lblTKCMND= new JLabel("CMND: ");
		
		txtTKHoTen.setBounds(60, 20, 350, h);
		txtTKHoTen.setFont(new Font("Arial", 50, 20));
		txtTKCMND.setBounds(60, 110, 350, h);
		txtTKCMND.setFont(new Font("Arial", 50, 20));
		lblTKHoTen.setBounds(10, 20, 350, h);
		lblTKHoTen.setForeground(new Color(130,161,192));
		lblTKCMND.setBounds(10, 110, 350, h);
		lblTKCMND.setForeground(new Color(130,161,192));
		
		
		pnlNorth.setBorder(BorderFactory.createTitledBorder(null, "Tìm kiếm", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlNorth.setBackground(new Color(53, 57, 65));
		pnlNorth.add(btnTimKiem);
		pnlNorth.add(btnXemTatCa);
		pnlNorth.add(txtTKHoTen);
		pnlNorth.add(txtTKCMND);
		pnlNorth.add(lblTKHoTen);
		pnlNorth.add(lblTKCMND);
		pnlNorth.setLayout(null);
		pnlNorth.setBounds(20, 10, 720, 230);
		
		JPanel pnlEast;
		pnMain.add(pnlEast = new JPanel());
		
		txtHoTen= new JTextField();
		txtHoTen.setBorder(new RoundedBorder(10));
		txtHoTen.setBounds(60, 20, w1, h);
		
		jrbNu= new JRadioButton("Nữ");
		jrbNam= new JRadioButton("Nam");
		
		jrbNu.setBackground(new Color(53, 57, 65));
		jrbNu.setForeground(new Color(130,161,192));
		jrbNu.setBorder(new RoundedBorder(10));
		jrbNu.setBounds(90, 65, 100, h);
		jrbNu.setSelected(true);
		jrbNam.setBackground(new Color(53, 57, 65));
		jrbNam.setForeground(new Color(130,161,192));
		jrbNam.setBorder(new RoundedBorder(10));
		jrbNam.setBounds(200, 65, 100, h);
		
		groupGT= new ButtonGroup();
		groupGT.add(jrbNam);
		groupGT.add(jrbNu);
		
		jclNgaySinh= new JDateChooser();
		jclNgaySinh.setBorder(new RoundedBorder(10));
		jclNgaySinh.setBackground(new Color(53, 57, 65));
		jclNgaySinh.setBounds(60, 110, 180, h);
		
		txtCMND= new JTextField();
		txtCMND.setBorder(new RoundedBorder(10));
		txtCMND.setBounds(60, 155, 180, h);
		

		txtTenTK= new JTextField();
		txtTenTK.setBorder(new RoundedBorder(10));
		txtTenTK.setBounds(350, 20, 180, h);
		

		txtMK= new JTextField();
		txtMK.setBorder(new RoundedBorder(10));
		txtMK.setBounds(350, 155, 180, h);
		

		txtChucVu= new JTextField();
		txtChucVu.setBorder(new RoundedBorder(10));
		txtChucVu.setBounds(350, 110, 180, h);
		
		lblHoTen= new JLabel("Họ tên:");
		lblNgaySinh= new JLabel("Ngày sinh:");
		lblCMND= new JLabel("CMND:");
		lblTenTK= new JLabel("Tên tài khoản:");
		lblMK= new JLabel("Mật khẩu:");
		lblChucVu= new JLabel("Chức vụ:");
		JLabel lblGT = new JLabel("Giới tính:");
		
		lblHoTen.setBounds(20, 20, 350, h);
		lblHoTen.setForeground(new Color(130,161,192));
		lblGT.setBounds(20, 65, 350, h);
		lblGT.setForeground(new Color(130,161,192));
		lblNgaySinh.setBounds(20, 110, 350, h);
		lblNgaySinh.setForeground(new Color(130,161,192));
		lblCMND.setBounds(20, 155, 350, h);
		lblCMND.setForeground(new Color(130,161,192));
		lblTenTK.setBounds(250, 20, 350, h);
		lblTenTK.setForeground(new Color(130,161,192));
		lblMK.setBounds(250, 110, 350, h);
		lblMK.setForeground(new Color(130,161,192));
		lblChucVu.setBounds(250, 155, 350, h);
		lblChucVu.setForeground(new Color(130,161,192));
		
		
		btnReset =new JButton("Đặt lại");
		btnReset.setBackground(new Color(38,40,43));
		btnReset.setForeground(new Color(130,161,192));
		btnReset.setBorder(new RoundedBorder(10));
		btnReset.setBounds(600, 20, 100, h);
		
		btnCapNhat =new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(38,40,43));
		btnCapNhat.setForeground(new Color(130,161,192));
		btnCapNhat.setBorder(new RoundedBorder(10));
		btnCapNhat.setBounds(600, 65, 100, h);
		
		btnXoa =new JButton("Xóa");
		btnXoa.setBackground(new Color(38,40,43));
		btnXoa.setForeground(new Color(130,161,192));
		btnXoa.setBorder(new RoundedBorder(10));
		btnXoa.setBounds(600, 110, 100, h);
		
		
		pnlEast.setBorder(BorderFactory.createTitledBorder(null, "Hành động", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlEast.setBackground(new Color(53, 57, 65));
		pnlEast.add(btnReset);
		pnlEast.add(btnCapNhat);
		pnlEast.add(btnXoa);
		pnlEast.add(txtHoTen);
		pnlEast.add(jrbNam);
		pnlEast.add(jrbNu);
		pnlEast.add(jclNgaySinh);
		pnlEast.add(txtCMND);
		pnlEast.add(txtTenTK);
		pnlEast.add(txtMK);
		pnlEast.add(txtChucVu);
		pnlEast.add(lblHoTen);
		pnlEast.add(lblNgaySinh);
		pnlEast.add(lblCMND);
		pnlEast.add(lblTenTK);
		pnlEast.add(lblMK);
		pnlEast.add(lblChucVu);
		pnlEast.add(lblGT);
		
		pnlEast.setLayout(null);
		pnlEast.setBounds(780, 10, 720, 230);

		
		String[] str = { "Họ tên", "CMND", "Giới tính", "Ngày sinh","Tên tài khoản","Chức vụ","Tạo lúc","Cập nhật" };
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
		
		
		
		
		add(pnMain);
	}

	public static void main(String[] args) throws IOException {
		new UI_QuanLyNhanVien();
	}
}
