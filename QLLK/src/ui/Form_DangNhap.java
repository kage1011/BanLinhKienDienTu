package ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conection.Conection;
import entity.NhanVien;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Form_DangNhap extends JFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5603437961006913993L;
	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnDangXuat;
	private NhanVien nhanVien;

	private JLabel lblHidePassword;
	private JPanel pnlHidePassword;
	private JLabel lblUnHidePassword;
	private JPanel pnlUnHidePassword;

	/**
	 * Create the frame.
	 */
	public Form_DangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 418);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlBG = new JPanel();
		pnlBG.setBackground(new Color(173, 216, 230));
		pnlBG.setBounds(0, 0, 295, 379);
		contentPane.add(pnlBG);
		pnlBG.setLayout(null);

		JLabel lblLogo = new JLabel("");

		lblLogo.setBounds(71, 62, 161, 143);
		lblLogo.setIcon(new ImageIcon(Form_DangNhap.class.getResource("/images/rsz_2maytinh.png")));
		pnlBG.add(lblLogo);

		JLabel lblTenTiem = new JLabel("Cửa Hàng Linh Kiện ");
		lblTenTiem.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		lblTenTiem.setBounds(50, 216, 211, 36);
		pnlBG.add(lblTenTiem);

		JLabel lblNewLabel = new JLabel("DreamTeam");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblNewLabel.setBounds(85, 251, 147, 28);
		pnlBG.add(lblNewLabel);

		JPanel pnlDangNhapBG = new JPanel();
		pnlDangNhapBG.setBackground(new Color(255, 255, 255));
		pnlDangNhapBG.setBounds(294, 0, 379, 379);
		contentPane.add(pnlDangNhapBG);
		pnlDangNhapBG.setLayout(null);

		txtTaiKhoan = new JTextField();

		txtTaiKhoan.setBackground(Color.WHITE);
		txtTaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTaiKhoan.setText("User Name");
		txtTaiKhoan.setBounds(90, 99, 220, 31);
		pnlDangNhapBG.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);

		txtMatKhau = new JPasswordField();

		txtMatKhau.setForeground(Color.BLACK);
		txtMatKhau.setBackground(Color.WHITE);
		txtMatKhau.setBounds(90, 163, 220, 31);
		pnlDangNhapBG.add(txtMatKhau);

		JLabel lblUser = new JLabel();
		lblUser.setBounds(34, 99, 46, 31);
		lblUser.setIcon(new ImageIcon(Form_DangNhap.class.getResource("/images/icons8_male_user_32px_3.png")));
		pnlDangNhapBG.add(lblUser);

		JLabel lblPassword = new JLabel();
		lblPassword.setBounds(34, 163, 46, 31);
		lblPassword.setIcon(new ImageIcon(Form_DangNhap.class.getResource("/images/icons8_lock_30px.png")));
		pnlDangNhapBG.add(lblPassword);

		JLabel lblNewLabel_1 = new JLabel("ĐĂNG NHẬP");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBackground(new Color(102, 205, 170));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(57, 23, 279, 54);
		pnlDangNhapBG.add(lblNewLabel_1);

		btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setIcon(new ImageIcon(Form_DangNhap.class.getResource("/images/icons8_login_32px.png")));
		btnDangNhap.setBounds(34, 277, 142, 47);
		pnlDangNhapBG.add(btnDangNhap);

		btnDangXuat = new JButton("Hủy");
		btnDangXuat.setBounds(217, 277, 119, 47);
		btnDangXuat.setIcon(new ImageIcon(Form_DangNhap.class.getResource("/images/icons8_lock_30px.png")));
		pnlDangNhapBG.add(btnDangXuat);

		pnlHidePassword = new JPanel();
		pnlHidePassword.setBounds(316, 163, 53, 31);
		pnlDangNhapBG.add(pnlHidePassword);

		lblHidePassword = new JLabel("");
		pnlHidePassword.add(lblHidePassword);

		pnlUnHidePassword = new JPanel();
		pnlUnHidePassword.setBounds(316, 163, 53, 31);
		lblUnHidePassword = new JLabel("");
		pnlUnHidePassword.add(lblUnHidePassword);
		pnlDangNhapBG.add(pnlUnHidePassword);

		btnDangNhap.addActionListener(this);
		btnDangXuat.addActionListener(this);
		lblHidePassword.addMouseListener(this);
		lblUnHidePassword.addMouseListener(this);
		txtMatKhau.addKeyListener(this);
		txtTaiKhoan.addKeyListener(this);

//		Test
		txtTaiKhoan.setText("NV_001");
		txtMatKhau.setText("sapassword");
		lblHidePassword.setIcon(new ImageIcon(Form_DangNhap.class.getResource("/images/icons8_eye_24px.png")));
		lblUnHidePassword.setIcon(new ImageIcon(Form_DangNhap.class.getResource("/images/icons8_invisible_24px.png")));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(txtTaiKhoan)) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				txtMatKhau.requestFocus();
			}
		}
		if (o.equals(txtMatKhau)) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				if (valid()) {
					if (KiemTraTaiKhoan(txtTaiKhoan.getText(), txtMatKhau.getText())) {
//						nhanVien = getNhanVien(txtTaiKhoan.getText());
						nhanVien = new NhanVien(txtTaiKhoan.getText());
						try {
							new TrangChu(nhanVien).setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(this, "Mật khẩu hay tài khoản không chính xác!!!");
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(lblHidePassword)) {
			txtMatKhau.setEchoChar((char) 0);
			pnlHidePassword.setVisible(false);
			pnlUnHidePassword.setVisible(true);
		} else if (o.equals(lblUnHidePassword)) {
			txtMatKhau.setEchoChar('*');
			pnlUnHidePassword.setVisible(false);
			pnlHidePassword.setVisible(true);
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

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDangNhap)) {
			if (valid()) {
				if (KiemTraTaiKhoan(txtTaiKhoan.getText(), txtMatKhau.getText())) {
//					nhanVien = new NhanVien(txtTaiKhoan.getText());
					nhanVien = getNhanVien(txtTaiKhoan.getText());
					this.setVisible(false);
					try {
						new TrangChu(nhanVien).setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Mật khẩu hay tài khoản không chính xác!!!");
				}
			}
		} else if (o.equals(btnDangXuat)) {
			txtTaiKhoan.requestFocus();
			txtTaiKhoan.selectAll();
			txtMatKhau.setText("");
		}
	}

	private Boolean KiemTraTaiKhoan(String taiKhoan, String matKhau) {
		String sql = "select * from NhanVien where MaNV = '" + taiKhoan + "' and NhanVien.MatKhau = '" + matKhau + "'";
		ResultSet rs = null;
		Boolean tmp = false;
		try {
			PreparedStatement stmt = Conection.getKetNoi().prepareStatement(sql);
			rs = stmt.executeQuery();
			tmp = rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}

	@SuppressWarnings("deprecation")
	private Boolean valid() {
		String taiKhoan = txtTaiKhoan.getText().trim();
		if (taiKhoan.equals("")) {
			thongBao(txtTaiKhoan, "Tài khoản không được để trống");
			return false;
		} else if (txtMatKhau.getText().equals("")) {
			thongBao(txtMatKhau, "Bạn chưa nhập mật khẩu");
			return false;

		}
		return true;
	}

	private void thongBao(JTextField tf, String mes) {
		JOptionPane.showMessageDialog(this, mes);
		tf.selectAll();
		tf.requestFocus();
	}

	private NhanVien getNhanVien(String taiKhoan) {
		PreparedStatement stmt = null;
		String sql = "select * from NhanVien where MaNV = '" + taiKhoan + "'";
		nhanVien = new NhanVien();
		try {
			stmt = Conection.getKetNoi().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nhanVien.setMaNV(rs.getString("MaNV"));
				nhanVien.setQuyen(rs.getString("Quyen"));
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanVien;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_DangNhap frame = new Form_DangNhap();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
