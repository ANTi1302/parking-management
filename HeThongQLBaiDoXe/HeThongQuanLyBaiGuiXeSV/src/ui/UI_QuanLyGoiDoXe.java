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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class UI_QuanLyGoiDoXe extends JFrame{

	private JTable tblgoi;
	private DefaultTableModel tblModelGoi;
	private JButton btnTimKiem, btnXoaTimKiem, btnThem,btnCapNhat,btnXoa;
	private Checkbox jckVisible;
	private JTextField txtTiemKiem;
	public UI_QuanLyGoiDoXe() throws IOException {
		BufferedImage imagebycycle = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		setTitle("Quản lý gói đỗ xe");
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
		
		btnTimKiem =new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(38,40,43));
		btnTimKiem.setForeground(new Color(130,161,192));
		btnTimKiem.setBorder(new RoundedBorder(10));
		
		btnXoaTimKiem =new JButton("Xóa");
		btnXoaTimKiem.setBackground(new Color(38,40,43));
		btnXoaTimKiem.setForeground(new Color(130,161,192));
		btnXoaTimKiem.setBorder(new RoundedBorder(10));
		
		txtTiemKiem =new JTextField();
		int w1 = 190, w2 = 500, h = 40;
		btnTimKiem.setBounds(500, 20, w1, h);
		btnXoaTimKiem.setBounds(500, 90, w1, h);
		txtTiemKiem.setBounds(40, 20, 350, h);
		txtTiemKiem.setFont(new Font("Arial", 50, 20));
		
		jckVisible= new Checkbox("Visible");
		jckVisible.setFont(new Font("Arial", 20, 15));
		jckVisible.setBounds(150, 90, w1, h);
		jckVisible.setForeground(new Color(130,161,192));
		
		pnlNorth.setBorder(BorderFactory.createTitledBorder(null, null, TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlNorth.setBackground(new Color(53, 57, 65));
		pnlNorth.add(btnTimKiem);
		pnlNorth.add(btnXoaTimKiem);
		pnlNorth.add(txtTiemKiem);
		pnlNorth.add(jckVisible);
		pnlNorth.setLayout(null);
		pnlNorth.setBounds(20, 10, 720, 200);
		
		JPanel pnlEast;
		pnMain.add(pnlEast = new JPanel());
		
		btnThem =new JButton("Thêm");
		btnThem.setBackground(new Color(38,40,43));
		btnThem.setForeground(new Color(130,161,192));
		btnThem.setBorder(new RoundedBorder(10));
		btnThem.setBounds(40, 70, w1, h);
		
		btnCapNhat =new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(38,40,43));
		btnCapNhat.setForeground(new Color(130,161,192));
		btnCapNhat.setBorder(new RoundedBorder(10));
		btnCapNhat.setBounds(270, 70, w1, h);
		
		btnXoa =new JButton("Xóa");
		btnXoa.setBackground(new Color(38,40,43));
		btnXoa.setForeground(new Color(130,161,192));
		btnXoa.setBorder(new RoundedBorder(10));
		btnXoa.setBounds(500, 70, w1, h);
		
		
		pnlEast.setBorder(BorderFactory.createTitledBorder(null, "Hành động", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlEast.setBackground(new Color(53, 57, 65));
		pnlEast.add(btnThem);
		pnlEast.add(btnCapNhat);
		pnlEast.add(btnXoa);
		pnlEast.setLayout(null);
		pnlEast.setBounds(780, 10, 720, 200);

		
		String[] str = { "Tên gói", "Giá", "Thời lượng", "Visible","Mô tả" };
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
		new UI_QuanLyGoiDoXe();
	}
}
