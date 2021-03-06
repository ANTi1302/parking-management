package ui;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


public class UI_QuanLyXeTrongBai extends JFrame{

	private JTable tblgoi;
	private DefaultTableModel tblModelGoi;
	private JButton  btnTimKiem,btnReset,btnXemTatCa;
	private JTextField txtTenChu, txtMaSv, txtxBienSoXe;
	private JLabel lblTenChu, lblMaSv, lblBienSoXe,lblTuNgay,lblDenNgay,lblTongSoXeBai,lblTongSoXeHT,lblTongLuotTT,lblTongLuotChuaTT,lblXeVao,lblXeRa;
	private JDateChooser jclTuNgay, jclDenNgay;
	private JRadioButton jrbTatCa, jrbDaThanhToan, jrbChuaThanhToan,jrbPVTatCa,jrbXeRa,jrbXeTrongBai;
	private ButtonGroup groupTT,groupPV;
	public UI_QuanLyXeTrongBai() throws IOException {
		BufferedImage imagebycycle = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		setTitle("Quản lý xe trong bãi");
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
		
		lblTenChu= new JLabel("Tên chủ thẻ:");
		lblMaSv= new JLabel("MSSV:");
		lblBienSoXe= new JLabel("Biển số xe:");
		
		lblTuNgay= new JLabel("Từ ngày:");
		lblDenNgay= new JLabel("Đến ngày:");
		
		jclTuNgay= new JDateChooser();
		jclTuNgay.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		jclTuNgay.getDateEditor().getUiComponent().setFocusable(false);
		
		jclDenNgay= new JDateChooser();
		jclDenNgay.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		jclDenNgay.getDateEditor().getUiComponent().setFocusable(false);
		
		txtTenChu =new JTextField();
		txtMaSv =new JTextField();
		txtxBienSoXe =new JTextField();
		int w1 = 190, w2 = 500, h = 40;
		
		txtTenChu.setBounds(100, 20, 200, h);
		txtTenChu.setFont(new Font("Arial", 50, 20));
		txtMaSv.setBounds(100, 65, 200, h);
		txtMaSv.setFont(new Font("Arial", 50, 20));
		txtxBienSoXe.setBounds(100, 110, 200, h);
		txtxBienSoXe.setFont(new Font("Arial", 50, 20));
		
		
		lblTenChu.setBounds(20, 20, 90, h);
		lblTenChu.setForeground(new Color(130,161,192));
		lblMaSv.setBounds(20, 65, 90, h);
		lblMaSv.setForeground(new Color(130,161,192));
		lblBienSoXe.setBounds(20, 110, 90, h);
		lblBienSoXe.setForeground(new Color(130,161,192));
		lblTuNgay.setBounds(320, 20, 90, h);
		lblTuNgay.setForeground(new Color(130,161,192));
		lblDenNgay.setBounds(320, 65, 90, h);
		lblDenNgay.setForeground(new Color(130,161,192));
		
		jclTuNgay.setBounds(380, 20, 200, h);
		jclDenNgay.setBounds(380, 65, 200, h);
		
		
		pnlNorth.setBorder(BorderFactory.createTitledBorder(null, null, TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlNorth.setBackground(new Color(53, 57, 65));
		pnlNorth.add(txtTenChu);
		pnlNorth.add(txtMaSv);
		pnlNorth.add(txtxBienSoXe);
		pnlNorth.add(lblTenChu);
		pnlNorth.add(lblMaSv);
		pnlNorth.add(lblBienSoXe);
		pnlNorth.add(jclTuNgay);
		pnlNorth.add(jclDenNgay);
		pnlNorth.add(lblTuNgay);
		pnlNorth.add(lblDenNgay);
		
		
		pnlNorth.setLayout(null);
		pnlNorth.setBounds(20, 10, 720, 200);
		
		JPanel pnlEast;
		pnMain.add(pnlEast = new JPanel());
		
		jrbTatCa= new JRadioButton("Tất cả");
		jrbChuaThanhToan= new JRadioButton("Chưa thanh toán");
		jrbDaThanhToan= new JRadioButton("Đã thanh toán");
		groupTT= new ButtonGroup();
		
		groupTT.add(jrbTatCa);
		groupTT.add(jrbChuaThanhToan);
		groupTT.add(jrbDaThanhToan);
		
		jrbPVTatCa= new JRadioButton("Tất cả");
		jrbXeRa= new JRadioButton("Xe đã ra");
		jrbXeTrongBai= new JRadioButton("Xe trong bãi");
		groupPV= new ButtonGroup();
		
		groupPV.add(jrbPVTatCa);
		groupPV.add(jrbXeRa);
		groupPV.add(jrbXeTrongBai);
		
		jrbChuaThanhToan.setBackground(new Color(53, 57, 65));
		jrbDaThanhToan.setBackground(new Color(53, 57, 65));
		jrbPVTatCa.setBackground(new Color(53, 57, 65));
		jrbTatCa.setBackground(new Color(53, 57, 65));
		jrbXeRa.setBackground(new Color(53, 57, 65));
		jrbXeTrongBai.setBackground(new Color(53, 57, 65));
		
		jrbChuaThanhToan.setForeground(new Color(130,161,192));
		jrbDaThanhToan.setForeground(new Color(130,161,192));
		jrbPVTatCa.setForeground(new Color(130,161,192));
		jrbTatCa.setForeground(new Color(130,161,192));
		jrbXeRa.setForeground(new Color(130,161,192));
		jrbXeTrongBai.setForeground(new Color(130,161,192));
		
		jrbTatCa.setBorder(new RoundedBorder(10));
		jrbTatCa.setBounds(20, 20, 100, h);
		jrbDaThanhToan.setBorder(new RoundedBorder(10));
		jrbDaThanhToan.setBounds(150, 20, 150, h);
		jrbChuaThanhToan.setBorder(new RoundedBorder(10));
		jrbChuaThanhToan.setBounds(300, 20, w1, h);
		
		jrbPVTatCa.setBorder(new RoundedBorder(10));
		jrbPVTatCa.setBounds(20, 50, 100, h);
		jrbXeTrongBai.setBorder(new RoundedBorder(10));
		jrbXeTrongBai.setBounds(150, 50, 150, h);
		jrbXeRa.setBorder(new RoundedBorder(10));
		jrbXeRa.setBounds(300, 50, 100, h);
		
		jrbPVTatCa.setSelected(true);
		jrbTatCa.setSelected(true);
		
		btnTimKiem =new JButton("Tìm kiếm");
		btnTimKiem.setBackground(new Color(38,40,43));
		btnTimKiem.setForeground(new Color(130,161,192));
		btnTimKiem.setBorder(new RoundedBorder(10));
		btnTimKiem.setBounds(500, 20, w1, h);
		
		btnReset =new JButton("Đặt lại");
		btnReset.setBackground(new Color(38,40,43));
		btnReset.setForeground(new Color(130,161,192));
		btnReset.setBorder(new RoundedBorder(10));
		btnReset.setBounds(500, 65, w1, h);
		
		btnXemTatCa =new JButton("Xem Tất Cả");
		btnXemTatCa.setBackground(new Color(38,40,43));
		btnXemTatCa.setForeground(new Color(130,161,192));
		btnXemTatCa.setBorder(new RoundedBorder(10));
		btnXemTatCa.setBounds(500, 110, w1, h);
		
		
		
		
		pnlEast.setBorder(BorderFactory.createTitledBorder(null, "Hành động", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlEast.setBackground(new Color(53, 57, 65));
		pnlEast.add(btnTimKiem);
		pnlEast.add(btnReset);
		pnlEast.add(btnXemTatCa);
		pnlEast.add(jrbChuaThanhToan);
		pnlEast.add(jrbDaThanhToan);
		pnlEast.add(jrbTatCa);
		pnlEast.add(jrbXeRa);
		pnlEast.add(jrbXeTrongBai);
		pnlEast.add(jrbPVTatCa);
		
		pnlEast.setLayout(null);
		pnlEast.setBounds(780, 10, 720, 200);

		
		String[] str = { "Tên gói", "Giá", "Thời lượng", "Visible","Mô tả" };
		tblModelGoi = new DefaultTableModel(str, 0);
		tblgoi = new JTable(tblModelGoi);
		JScrollPane jsc = new JScrollPane(tblgoi);
		tblgoi.setAutoCreateRowSorter(true);
		jsc.getVerticalScrollBar().setUnitIncrement(16);
		jsc.setBounds(10, 20, 1460, 300);
		JPanel pnlSouth;
		pnMain.add(pnlSouth = new JPanel());
		
		lblTongSoXeBai= new JLabel("Tổng số xe (trong bãi): 0");
		lblTongSoXeHT= new JLabel("Tổng số xe (trong hệ thống): 0");
		lblTongLuotTT= new JLabel("Tổng lượt đã thanh toán: 0");
		lblTongLuotChuaTT= new JLabel("Tổng lượt chưa thanh toán: 0");
		lblXeVao= new JLabel("Xe vào: 1");
		lblXeRa= new JLabel("Xe ra: 1");
		
		lblTongSoXeBai.setBounds(30, 350, 1000, h);
		lblTongSoXeBai.setForeground(new Color(130,161,192));
		lblTongSoXeBai.setFont(new Font("Arial", Font.PLAIN, 30));
		lblTongSoXeHT.setBounds(30, 400, 1000, h);
		lblTongSoXeHT.setForeground(new Color(130,161,192));
		lblTongSoXeHT.setFont(new Font("Arial", Font.PLAIN, 30));
		lblTongLuotTT.setBounds(600, 350, 1000, h);
		lblTongLuotTT.setForeground(new Color(130,161,192));
		lblTongLuotTT.setFont(new Font("Arial", Font.PLAIN, 30));
		lblTongLuotChuaTT.setBounds(600, 400, 1000, h);
		lblTongLuotChuaTT.setForeground(new Color(130,161,192));
		lblTongLuotChuaTT.setFont(new Font("Arial", Font.PLAIN, 30));
		lblXeVao.setBounds(1200, 350, 1000, h);
		lblXeVao.setForeground(new Color(130,161,192));
		lblXeVao.setFont(new Font("Arial", Font.PLAIN, 30));
		lblXeRa.setBounds(1200, 400, 1000, h);
		lblXeRa.setForeground(new Color(130,161,192));
		lblXeRa.setFont(new Font("Arial", Font.PLAIN, 30));
		
		
		pnlSouth.setBorder(BorderFactory.createTitledBorder(null, "Danh sách", TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), new Color(111,144,199)));
		pnlSouth.setBackground(new Color(53, 57, 65));
		pnlSouth.add(jsc);
		pnlSouth.add(lblTongSoXeHT);
		pnlSouth.add(lblTongSoXeBai);
		pnlSouth.add(lblTongLuotTT);
		pnlSouth.add(lblTongLuotChuaTT);
		pnlSouth.add(lblXeVao);
		pnlSouth.add(lblXeRa);
		pnlSouth.setLayout(null);
		pnlSouth.setBounds(20, 250, 1480, 500);
		
		
		
		
		add(pnMain);
	}
	public static void main(String[] args) throws IOException {
		new UI_QuanLyXeTrongBai();
	}
}
