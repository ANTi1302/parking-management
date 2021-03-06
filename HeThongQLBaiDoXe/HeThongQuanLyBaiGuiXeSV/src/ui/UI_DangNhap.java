
package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JTextField;




public class UI_DangNhap extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chucVu;
	JLabel lblTaiKhoan, lblMatKhau, lblTieuDe, lblnote;
	JTextField txtTaiKhoan;
	JPasswordField txtMatKhau;
	JButton btnDangNhap, btnQuenMK;
	Color gbMain = new Color(214, 214, 214);

	public UI_DangNhap() throws IOException {
		// TODO Auto-generated constructor stub
		BufferedImage imagebycycle = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon imgbycycle = new ImageIcon(imagebycycle.getScaledInstance(50, 50, imagebycycle.SCALE_SMOOTH));
		setTitle("Đăng nhập hệ thống");
		setIconImage(imgbycycle.getImage());
		JPanel pNorth;
		add(pNorth = new JPanel(), BorderLayout.NORTH);


		 BufferedImage image = ImageIO.read(new File("image/bicycle.png"));
		ImageIcon img = new ImageIcon(image.getScaledInstance(50, 50, image.SCALE_SMOOTH));
		JLabel showImage = new JLabel();
		showImage.setIcon(img);
		pNorth.add(showImage);	
		pNorth.setBackground(new Color(53,57,65));

		JPanel pCenter;
		add(pCenter = new JPanel(), BorderLayout.CENTER);
		pCenter.setBackground(new Color(53,57,65));
		
		Box x;
		pCenter.add(x = Box.createVerticalBox());
		x.add(Box.createVerticalStrut(10));

		Box x0;
		x.add(x0 = Box.createHorizontalBox());
		x0.add(lblTieuDe = new JLabel("ĐĂNG NHẬP"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 15));
		lblTieuDe.setForeground(Color.WHITE);
		
		x.add(Box.createVerticalStrut(30));

		Box x1;
		x.add(x1 = Box.createHorizontalBox());
		x1.add(Box.createHorizontalStrut(50));
		x1.add(lblTaiKhoan = new JLabel("Tài khoản: "));
		lblTaiKhoan.setForeground(new Color(143,166,198));
	
		x1.add(Box.createHorizontalStrut(20));
		x1.add(txtTaiKhoan = new JTextField());
		txtTaiKhoan.setBackground(new Color(38,40,43));
		txtTaiKhoan.setForeground(Color.WHITE);
		
		x1.add(Box.createHorizontalStrut(50));
		x.add(Box.createVerticalStrut(15));

		Box x2;
		x.add(x2 = Box.createHorizontalBox());
		x2.add(Box.createHorizontalStrut(50));
		x2.add(lblMatKhau = new JLabel("Mật khẩu:  "));
		lblMatKhau.setForeground(new Color(143,166,198));
		
		x2.add(Box.createHorizontalStrut(20));
		x2.add(txtMatKhau = new JPasswordField());
		txtMatKhau.setBackground(new Color(38,40,43));
		txtMatKhau.setForeground(Color.WHITE);
		
		x2.add(Box.createHorizontalStrut(50));
		x.add(Box.createVerticalStrut(20));
		
		Box x3;
		x.add(x3 = Box.createHorizontalBox());
		x3.add(btnDangNhap = new JButton("    Đăng nhập    "));
		btnDangNhap.setForeground(new Color(143,166,198));
		btnDangNhap.setBackground(new Color(38,40,43));
		x3.add(Box.createHorizontalStrut(20));
		
		x3.add(btnQuenMK = new JButton("Quên Mật Khẩu"));
		btnQuenMK.setForeground(new Color(143,166,198));
		btnQuenMK.setBackground(new Color(38,40,43));
		
		x.add(Box.createVerticalStrut(30));
		
		btnDangNhap.addActionListener(this);
		btnQuenMK.addActionListener(this);
		txtTaiKhoan.addActionListener(this);
		txtMatKhau.addActionListener(this);
		setSize(500, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws IOException {
		new UI_DangNhap();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object sou = e.getSource();
		if (sou.equals(btnDangNhap) || sou.equals(txtMatKhau) || sou.equals(txtTaiKhoan)) {
			String taiKhoan = txtTaiKhoan.getText();
			char[] char_matKhau =txtMatKhau.getPassword();
			String matKhau = new String(char_matKhau);
//			User user = new User_Dao().getUserTheomaNVFirst(taiKhoan);
//			System.out.println(user);

//			if (user != null && taiKhoan.equalsIgnoreCase(user.getNhanVien().getMaNV())
//					&&matKhau.equalsIgnoreCase(user.getPassword() )) {
//				try {
//					UI_HoaDon frm1 = new UI_HoaDon(user);
//				} catch (SQLException | IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				this.dispose();
//			} else
//				JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng");
//		} else if (sou.equals(btnQuenMK)) {
//			JTextField txtMaNV = new JTextField(10);
//			int actionMaNV = JOptionPane.showConfirmDialog(null, txtMaNV, "Nhập mã nhân viên",
//					JOptionPane.OK_CANCEL_OPTION);
//
//			JPasswordField txtMatKhau = new JPasswordField(10);
//
//			if (actionMaNV == JOptionPane.OK_OPTION) {
//				User user = new User_Dao().getUserTheomaNVFirst(txtMaNV.getText());
//				if (user == null) {
//					JOptionPane.showInternalMessageDialog(this.getContentPane(),
//							"Không có mã nhân viên này trong hệ thống");
//				} else {
//					int actionPwd = JOptionPane.showConfirmDialog(null, txtMatKhau, "Nhập mật khẩu mới",
//							JOptionPane.OK_CANCEL_OPTION);
//					if (actionPwd == JOptionPane.OK_OPTION) {
//						char[] char_matKhau = txtMatKhau.getPassword();
//						String matKhau = new String(char_matKhau);
//
//						new User_Dao().update(new User(user.getId(), matKhau, user.getChucNang(), user.getNhanVien()));
//						JOptionPane.showInternalMessageDialog(this.getContentPane(), "Thay đổi mật khẩu thành công");
//					}
//				}
//
//			}

		}
	}

}
