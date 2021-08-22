package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.LinhKien;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class frm_ChiTietLinhKien extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlChiTietLK;
	private JTextField txtMaLK;
	private JTextField txtSoLuongTon;
	private JTextField txtThuongHieu;
	private JTextField txtNgayNhap;
	private JTextField txtDonGia;
	private JTextField txtBaoHanh;
	private JLabel lblChiTietLK;
	private JLabel lblMaLinhKien;
	private JLabel lblTenLinhKien;
	private JLabel lblMoTaLK;
	private JLabel lblThuongHieu;
	private JLabel lblDonGia;
	private JLabel lblNgayNhap;
	private JLabel lblSoLuongTon;
	private JLabel lblBaoHanh;
	private JTextArea txtMoTa;
	private JTextArea txtTenLK;

	private JTextField txtLoai;
	private JButton btnThoat;
	private DecimalFormat df;
	private JPanel pnlChiTiet;
	private JLabel lblLoaiLK;

	private JTextField txtMauSac;
	private JTextField txtTrongLuong;
	private JLabel lblGiNhp;
	private JTextField txtGiaNhap;
	private JLabel lblThu;
	private JTextField txtThue;
	private JLabel lblnh;
	private JLabel lblLinkanh;
	private JLabel lblNhCungCp;
	private JLabel lblThng;
	private JLabel lblKg;
	private JLabel lblThng_1;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public frm_ChiTietLinhKien(LinhKien linhkien) {
		df = new DecimalFormat("#,##0.00");
		setTitle("Chi tiết linh kiện");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(150, 100, 1167, 694);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnlChiTietLK = new JPanel();
		pnlChiTietLK.setBounds(0, 0, 1167, 664);
		pnlChiTietLK.setBackground(Color.LIGHT_GRAY);
		contentPane.add(pnlChiTietLK);
		pnlChiTietLK.setLayout(null);

		lblMaLinhKien = new JLabel("Mã linh kiện:");
		lblMaLinhKien.setBounds(66, 70, 114, 25);
		lblMaLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblMaLinhKien);

		lblTenLinhKien = new JLabel("Tên linh kiện:");
		lblTenLinhKien.setBounds(66, 122, 134, 25);
		lblTenLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblTenLinhKien);

		lblMoTaLK = new JLabel("Mô tả linh kiện:");
		lblMoTaLK.setBounds(440, 345, 149, 25);
		lblMoTaLK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblMoTaLK);

		lblThuongHieu = new JLabel("Thương hiệu:");
		lblThuongHieu.setBounds(66, 179, 134, 25);
		lblThuongHieu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblThuongHieu);

		lblDonGia = new JLabel("Giá bán:");
		lblDonGia.setBounds(66, 234, 91, 25);
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblDonGia);

		lblNgayNhap = new JLabel("Ngày nhập:");
		lblNgayNhap.setBounds(440, 70, 114, 25);
		lblNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblNgayNhap);

		lblSoLuongTon = new JLabel("Số lượng tồn:");
		lblSoLuongTon.setBounds(66, 402, 134, 25);
		lblSoLuongTon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblSoLuongTon);

		lblBaoHanh = new JLabel("Bảo hành:");
		lblBaoHanh.setBounds(66, 563, 114, 25);
		lblBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblBaoHanh);

		txtMaLK = new JTextField();
		txtMaLK.setBounds(212, 70, 182, 27);
		txtMaLK.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMaLK.setHorizontalAlignment(SwingConstants.CENTER);
		pnlChiTietLK.add(txtMaLK);

		txtMaLK.setColumns(10);
		txtMaLK.setEditable(false);
		txtMaLK.setText(linhkien.getMaLK());

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setBounds(210, 402, 182, 27);
		txtSoLuongTon.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuongTon.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSoLuongTon.setColumns(10);
		pnlChiTietLK.add(txtSoLuongTon);
		txtSoLuongTon.setEditable(false);
		txtSoLuongTon.setText(String.valueOf(linhkien.getSoLuongTon()));

		txtThuongHieu = new JTextField();
		txtThuongHieu.setBounds(210, 179, 182, 27);
		txtThuongHieu.setHorizontalAlignment(SwingConstants.CENTER);
		txtThuongHieu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtThuongHieu.setColumns(10);
		pnlChiTietLK.add(txtThuongHieu);
		txtThuongHieu.setEditable(false);
		txtThuongHieu.setText(linhkien.getThuongHieu().getMaTH());

		txtNgayNhap = new JTextField();
		txtNgayNhap.setBounds(550, 70, 182, 27);
		txtNgayNhap.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtNgayNhap.setColumns(10);
		pnlChiTietLK.add(txtNgayNhap);
		txtNgayNhap.setEditable(false);
		txtNgayNhap.setText(String.valueOf(linhkien.getNgayNhap()));

		txtDonGia = new JTextField();
		txtDonGia.setBounds(210, 234, 182, 27);
		txtDonGia.setHorizontalAlignment(SwingConstants.CENTER);
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtDonGia.setColumns(10);
		pnlChiTietLK.add(txtDonGia);
		txtDonGia.setEditable(false);
		txtDonGia.setText(df.format(linhkien.getGiaBan()));

		txtBaoHanh = new JTextField();
		txtBaoHanh.setBounds(210, 563, 115, 27);
		txtBaoHanh.setHorizontalAlignment(SwingConstants.CENTER);
		txtBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtBaoHanh.setColumns(10);
		pnlChiTietLK.add(txtBaoHanh);
		txtBaoHanh.setEditable(false);
		txtBaoHanh.setText(linhkien.getBaoHanh());

		txtMoTa = new JTextArea();
		txtMoTa.setBounds(440, 390, 667, 203);
		txtMoTa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlChiTietLK.add(txtMoTa);
		txtMoTa.setEditable(false);
		txtMoTa.setText(linhkien.getMoTa());


		btnThoat = new JButton("Thoát");
		btnThoat.setBounds(1014, 615, 97, 30);
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setBackground(new Color(23,70,138));
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlChiTietLK.add(btnThoat);



		txtTenLK = new JTextArea();
		txtTenLK.setBounds(211, 122, 521, 27);
		txtTenLK.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlChiTietLK.add(txtTenLK);
		txtTenLK.setEditable(false);
		txtTenLK.setText(linhkien.getTenLK());

		lblLoaiLK = new JLabel("Loại:");
		lblLoaiLK.setBounds(440, 179, 91, 25);
		lblLoaiLK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblLoaiLK);

		txtLoai = new JTextField();
		txtLoai.setBounds(550, 179, 182, 27);
		txtLoai.setText("0.0");
		txtLoai.setHorizontalAlignment(SwingConstants.CENTER);
		txtLoai.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtLoai.setEditable(false);
		txtLoai.setColumns(10);
		pnlChiTietLK.add(txtLoai);
		txtLoai.setText(linhkien.getLoaiLoaiKien().getMaLoaiLK());

		pnlChiTiet = new JPanel();
		pnlChiTiet.setBounds(0, 0, 1157, 54);
		pnlChiTiet.setBackground(new Color(23, 70, 138));
		pnlChiTietLK.add(pnlChiTiet);
		pnlChiTiet.setLayout(null);

		lblChiTietLK = new JLabel("Chi tiết linh kiện");
		lblChiTietLK.setBackground(new Color(23, 70, 138));
		lblChiTietLK.setBounds(0, 0, 1151, 54);
		pnlChiTiet.add(lblChiTietLK);
		lblChiTietLK.setForeground(Color.WHITE);
		lblChiTietLK.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblChiTietLK.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMuSc = new JLabel("Màu sắc:");
		lblMuSc.setBounds(66, 345, 114, 25);
		lblMuSc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblMuSc);

		JLabel lblTrngLng = new JLabel("Trọng lượng:");
		lblTrngLng.setBounds(66, 455, 134, 25);
		lblTrngLng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblTrngLng);

		txtTrongLuong = new JTextField();
		txtTrongLuong.setBounds(210, 455, 115, 27);
		txtTrongLuong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTrongLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrongLuong.setEditable(false);
		txtTrongLuong.setText(linhkien.getTrongLuong());
		pnlChiTietLK.add(txtTrongLuong);

		txtMauSac = new JTextField();
		txtMauSac.setBounds(210, 345, 182, 27);
		txtMauSac.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMauSac.setEditable(false);
		txtMauSac.setHorizontalAlignment(SwingConstants.CENTER);
		txtMauSac.setText(linhkien.getMauSac());
		pnlChiTietLK.add(txtMauSac);
		
		lblGiNhp = new JLabel("Giá nhập:");
		lblGiNhp.setBounds(440, 234, 91, 25);
		lblGiNhp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblGiNhp);
		
		txtGiaNhap = new JTextField();
		txtGiaNhap.setBounds(550, 234, 182, 27);
		txtGiaNhap.setHorizontalAlignment(SwingConstants.CENTER);
		txtGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtGiaNhap.setEditable(false);
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setText(df.format(linhkien.getGiaNhap()));
		pnlChiTietLK.add(txtGiaNhap);
		
		lblThu = new JLabel("Thuế:");
		lblThu.setBounds(66, 514, 91, 25);
		lblThu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChiTietLK.add(lblThu);
		
		txtThue = new JTextField();
		txtThue.setBounds(210, 511, 115, 27);
		txtThue.setHorizontalAlignment(SwingConstants.CENTER);
		txtThue.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtThue.setEditable(false);
		txtThue.setColumns(10);
		txtThue.setText(linhkien.getThue()+"");
		pnlChiTietLK.add(txtThue);
		
		lblnh = new JLabel("Ảnh:");
		lblnh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblnh.setBounds(786, 70, 56, 25);
		pnlChiTietLK.add(lblnh);
		
		lblLinkanh = new JLabel(" ");
		lblLinkanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLinkanh.setBounds(849, 70, 258, 248);
		lblLinkanh.setIcon(new ImageIcon(new javax.swing.ImageIcon(linhkien.getAnh())
				.getImage().getScaledInstance(263,248, Image.SCALE_SMOOTH)));
		pnlChiTietLK.add(lblLinkanh);
		
		lblNhCungCp = new JLabel("Nhà cung cấp:");
		lblNhCungCp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhCungCp.setBounds(66, 293, 165, 25);
		pnlChiTietLK.add(lblNhCungCp);
		
		JTextArea txtncc = new JTextArea();
		txtncc.setText((String) null);
		txtncc.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtncc.setEditable(false);
		txtncc.setBounds(212, 291, 521, 27);
		txtncc.setText(linhkien.getNhaCungCap().getMaNCC());
		pnlChiTietLK.add(txtncc);
		
		lblThng = new JLabel("%");
		lblThng.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblThng.setBounds(332, 513, 31, 30);
		pnlChiTietLK.add(lblThng);
		
		lblKg = new JLabel("Gram");
		lblKg.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblKg.setBounds(332, 459, 60, 20);
		pnlChiTietLK.add(lblKg);
		
		lblThng_1 = new JLabel("Tháng");
		lblThng_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblThng_1.setBounds(332, 563, 56, 30);
		pnlChiTietLK.add(lblThng_1);

		btnThoat.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
