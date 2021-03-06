package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import net.sourceforge.tess4j.TesseractException;
public class UI_GiaoDienChinh extends JFrame implements ActionListener{
	private JButton btnLogo1, btnLogo2, btnLogo3;
	private JButton btnQuanLyXeVao, btnQuanLyXeRa,btnQLXeTrongBai,btnQLNguoiDung,btnQLNhanVien,btnQLTK,btnGoiGiuXe,btnQLDonGia,btnThanhToan;
	private JButton btnDangXuat;
	private org.opencv.videoio.VideoCapture capture;
	
	private UI_DieuKhienXeVao frmQuanLyXeVao;
	private UI_DieuKhienXeRa frmQuanLyXeRa;
	private UI_DangNhap frmGiaoDienDangNhap;
	private UI_QuanLyGoiDoXe frmQuanLyGoiXe;
	private UI_QuanLyDonGia frmQuanLyDonGia;
	private UI_QuanLyKhachHang frmQuanLyKH;
	private UI_QuanLyXeTrongBai frmQuanLyXeTrongBai;
	private UI_QuanLyNhanVien frmQuanLyNV;
	
	public UI_GiaoDienChinh() throws IOException {
		BufferedImage imagebycycle = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		setTitle("Lựa chọn tác vụ");
		setIconImage(imgbycycle.getImage());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
	private void giaoDien() throws IOException {
//		JPanel pnMain= new JPanel();

		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(new Color(53,57,65));
		//logo1
		BufferedImage imagebycycle = ImageIO.read(new File("image/itfaculty.jpg"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		pnNorth.add(btnLogo1 = new JButton("",imgbycycle));

		btnLogo1.setBorderPainted(false);
		btnLogo1.setContentAreaFilled(false);
		btnLogo1.setFocusPainted(false);
		//logo2
		BufferedImage imageit = ImageIO.read(new File("image/lifilogo.jpg"));
		ImageIcon imgit = new ImageIcon(imageit.getScaledInstance(50, 50, imageit.SCALE_SMOOTH));
		pnNorth.add(btnLogo2 = new JButton("",imgit));

		btnLogo2.setBorderPainted(false);
		btnLogo2.setContentAreaFilled(false);
		btnLogo2.setFocusPainted(false);
		//logo3
		BufferedImage imagebk = ImageIO.read(new File("image/logobk.jpeg"));
		ImageIcon imgbk = new ImageIcon(imagebk.getScaledInstance(50, 50, imagebk.SCALE_SMOOTH));
		pnNorth.add(btnLogo3 = new JButton("",imgbk));

		btnLogo3.setBorderPainted(false);
		btnLogo3.setContentAreaFilled(false);
		btnLogo3.setFocusPainted(false);
		
		////////////////////////////////////////////
		JPanel pnCenter = new JPanel();
		
		JPanel pnNoiDung= new JPanel();
		pnNoiDung.setLayout(new BoxLayout(pnNoiDung, BoxLayout.Y_AXIS));
		pnNoiDung.setBorder(new EmptyBorder(new Insets(0, -500, 10, 10)));
		pnNoiDung.setBackground(new Color(53,57,65));
		pnCenter.setBackground(new Color(53,57,65));
		JLabel lblTuaDe= new JLabel("                           DỰ ÁN");
		lblTuaDe.setForeground(Color.BLUE);
		lblTuaDe.setFont(new Font("Arial", 0, 30));
		pnNoiDung.add(lblTuaDe);
		
		JLabel lblTuaDe2= new JLabel("HỆ THỐNG QUẢN LÝ BÃI XE MÁY SINH VIÊN");
		lblTuaDe2.setFont(new Font("Arial", 0, 30));
		lblTuaDe2.setForeground(Color.ORANGE);
		pnNoiDung.add(lblTuaDe2);
		JPanel pnCacNut= new JPanel();
		pnCacNut.setLayout(new BoxLayout(pnCacNut, BoxLayout.Y_AXIS));
		pnCacNut.setBorder(new EmptyBorder(new Insets(50, 300, 10, 10)));
		pnCacNut.setBackground(new Color(53,57,65));
		//Hang nut thu nhat
		JPanel pnNut1= new JPanel();
		pnNut1.setLayout(new BoxLayout(pnNut1, BoxLayout.X_AXIS));
		pnNut1.setBackground(new Color(53,57,65));
		btnQuanLyXeVao= new JButton("Quản lý xe vào");
		btnQuanLyXeVao.setBackground(new Color(38,40,43));
		btnQuanLyXeVao.setForeground(new Color(130,161,192));
		btnQuanLyXeVao.setBorder(new RoundedBorder(10));
		pnNut1.setBorder(new EmptyBorder(new Insets(100, 300, 10, 10)));
		btnQuanLyXeVao.add(Box.createRigidArea(new Dimension(150, 30))); 
		pnNut1.add(btnQuanLyXeVao);
		pnCacNut.add(pnNut1);
		//Nut2
		btnQuanLyXeRa= new JButton("Quản lý xe ra");
		pnNut1.add(btnQuanLyXeRa);
		btnQuanLyXeRa.setBackground(new Color(38,40,43));
		btnQuanLyXeRa.setForeground(new Color(130,161,192));
		btnQuanLyXeRa.setBorder(new RoundedBorder(10));
		btnQuanLyXeRa.add(Box.createRigidArea(new Dimension(150, 30))); 
		//Nut3
		btnQLXeTrongBai= new JButton("Quản lý xe trong bãi");
		pnNut1.add(btnQLXeTrongBai);
		btnQLXeTrongBai.setBackground(new Color(38,40,43));
		btnQLXeTrongBai.setForeground(new Color(130,161,192));
		btnQLXeTrongBai.setBorder(new RoundedBorder(10));
		btnQLXeTrongBai.add(Box.createRigidArea(new Dimension(150, 30))); 
		//Hang nut thu 2
		JPanel pnNut2= new JPanel();
		pnNut2.setLayout(new BoxLayout(pnNut2, BoxLayout.X_AXIS));
		pnNut2.setBackground(new Color(53,57,65));
		pnNut2.setBorder(new EmptyBorder(new Insets(100, 300, 10, 10)));
		btnQLNguoiDung= new JButton("Quản lý người dùng");
		pnNut2.add(btnQLNguoiDung);
		btnQLNguoiDung.setBackground(new Color(38,40,43));
		btnQLNguoiDung.setForeground(new Color(130,161,192));
		btnQLNguoiDung.setBorder(new RoundedBorder(10));
		btnQLNguoiDung.add(Box.createRigidArea(new Dimension(150, 30))); 
		btnQLNhanVien= new JButton("Quản lý nhân viên");
		pnNut2.add(btnQLNhanVien);
		btnQLNhanVien.setBackground(new Color(38,40,43));
		btnQLNhanVien.setForeground(new Color(130,161,192));
		btnQLNhanVien.setBorder(new RoundedBorder(10));
		btnQLNhanVien.add(Box.createRigidArea(new Dimension(150, 30))); 
		btnQLTK= new JButton("Quản lý - Thống kê");
		pnNut2.add(btnQLTK);
		btnQLTK.setBackground(new Color(38,40,43));
		btnQLTK.setForeground(new Color(130,161,192));
		btnQLTK.setBorder(new RoundedBorder(10));
		btnQLTK.add(Box.createRigidArea(new Dimension(150, 30))); 
		//Hang nut thu 3
		JPanel pnNut3= new JPanel();
		pnNut3.setLayout(new BoxLayout(pnNut3, BoxLayout.X_AXIS));
		pnNut3.setBackground(new Color(53,57,65));
		pnNut3.setBorder(new EmptyBorder(new Insets(100, 300, 10, 10)));
		btnGoiGiuXe= new JButton("Quản lý gói giữ xe");
		pnNut3.add(btnGoiGiuXe);
		btnGoiGiuXe.setBackground(new Color(38,40,43));
		btnGoiGiuXe.setForeground(new Color(130,161,192));
		btnGoiGiuXe.setBorder(new RoundedBorder(10));
		btnGoiGiuXe.add(Box.createRigidArea(new Dimension(150, 30))); 
		btnQLDonGia= new JButton("Quản lý đơn giá");
		pnNut3.add(btnQLDonGia);
		btnQLDonGia.setBackground(new Color(38,40,43));
		btnQLDonGia.setForeground(new Color(130,161,192));
		btnQLDonGia.setBorder(new RoundedBorder(10));
		btnQLDonGia.add(Box.createRigidArea(new Dimension(150, 30))); 
		btnThanhToan= new JButton("Thanh toán");
		pnNut3.add(btnThanhToan);
		btnThanhToan.setBackground(new Color(38,40,43));
		btnThanhToan.setForeground(new Color(130,161,192));
		btnThanhToan.setBorder(new RoundedBorder(10));
		btnThanhToan.add(Box.createRigidArea(new Dimension(150, 30))); 
		
		
		
		pnCacNut.add(pnNut1);
		pnCacNut.add(pnNut2);
		pnCacNut.add(pnNut3);
		
		pnNoiDung.add(pnCacNut);
		pnCenter.add(pnNoiDung);
		//////////////////////////////////////////////////
		JPanel pnSouth = new JPanel();
		pnSouth.setBackground(new Color(53,57,65));
		JPanel pnHeader= new JPanel();
		pnHeader.setLayout(new BoxLayout(pnHeader, BoxLayout.Y_AXIS));
		pnHeader.setBackground(new Color(53,57,65));
		JButton lblTenNguoiDung= new JButton("Anh Thư");
		lblTenNguoiDung.setForeground(Color.BLUE);
		lblTenNguoiDung.setFont(new Font("Arial", 5, 30));
		pnHeader.add(lblTenNguoiDung);
		lblTenNguoiDung.setBorderPainted(false);
		lblTenNguoiDung.setContentAreaFilled(false);
		lblTenNguoiDung.setFocusPainted(false);
		
		BufferedImage imageout = ImageIO.read(new File("image/out.png"));
		ImageIcon imgout = new ImageIcon(imageout.getScaledInstance(50, 50, imageout.SCALE_SMOOTH));

		 btnDangXuat= new JButton("Đăng xuất",imgout);
		 btnDangXuat.setForeground(Color.ORANGE);
		 btnDangXuat.setContentAreaFilled(false);
		 btnDangXuat.setBorderPainted(false);
		 pnHeader.add(btnDangXuat);
		
		pnSouth.add(pnHeader);
		///////////////////////////////////////////////
		add(pnNorth, BorderLayout.NORTH);
		add(pnCenter, BorderLayout.CENTER);
		add(pnSouth, BorderLayout.SOUTH);

		btnQuanLyXeVao.addActionListener(this);
		btnGoiGiuXe.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnQLDonGia.addActionListener(this);
		btnQLNguoiDung.addActionListener(this);
		btnQLNhanVien.addActionListener(this);
		btnQLTK.addActionListener(this);
		btnQLXeTrongBai.addActionListener(this);
		btnQuanLyXeRa.addActionListener(this);
		btnThanhToan.addActionListener(this);
	}

	public static void main(String[] args) throws IOException {
		
		new UI_GiaoDienChinh();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object sou = e.getSource();
		if (sou.equals(btnQuanLyXeVao)) {
			try {
//				frmHoaDon = new UI_HoaDon(userCurr);
				 frmQuanLyXeVao = new UI_DieuKhienXeVao();
//				 frmQuanLyXeVao.main(null);
//				  capture = new VideoCapture(0);
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TesseractException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (sou.equals(btnQuanLyXeRa)) {
			try {
//				frmHoaDon = new UI_HoaDon(userCurr);
				 frmQuanLyXeRa = new UI_DieuKhienXeRa();
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (sou.equals(btnDangXuat)) {
			try {
//				frmHoaDon = new UI_HoaDon(userCurr);
				 frmGiaoDienDangNhap = new UI_DangNhap();
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if (sou.equals(btnGoiGiuXe)) {
			try {
//				frmHoaDon = new UI_HoaDon(userCurr);
				 frmQuanLyGoiXe = new UI_QuanLyGoiDoXe();
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (sou.equals(btnQLDonGia)) {
			try {
//				frmHoaDon = new UI_HoaDon(userCurr);
				 frmQuanLyDonGia = new UI_QuanLyDonGia();
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (sou.equals(btnQLXeTrongBai)) {
			try {
//				frmHoaDon = new UI_HoaDon(userCurr);
				 frmQuanLyXeTrongBai = new UI_QuanLyXeTrongBai();
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (sou.equals(btnQLNguoiDung)) {
			try {
//				frmHoaDon = new UI_HoaDon(userCurr);
				 frmQuanLyKH = new UI_QuanLyKhachHang();
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (sou.equals(btnQLNhanVien)) {
			try {
//				frmHoaDon = new UI_HoaDon(userCurr);
				 frmQuanLyNV = new UI_QuanLyNhanVien();
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
