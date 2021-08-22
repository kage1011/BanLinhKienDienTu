
package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import conection.Conection;
import dao.LinhKienDAO;
import dao.LoaiLinhKienDAO;
import dao.NhaCungCapDAO;
import dao.NhanVienDAO;
import dao.ThuongHieuDAO;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.ThuongHieu;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JpanelLinhKien extends JPanel implements ActionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaLK;
	private JTextField txtTenLK;
	private JTextField txtSoLuong;
	private JTextField txtDonGia;

	private JTextField txtNgayNhap;
	private DefaultTableModel modelLinhKien;
	private JTable tableLinhKien;
	// private LinhKienDAO linhKienDAO;
	private JButton btnXoaRong;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnThem;
	private List<LinhKien> listLK;
	private List<LinhKien> listLK1;
	private JTextArea txtMoTa;

	private LinhKienDAO linhKienDAO;
	private JLabel lblMauSac;
	private JTextField txtMauSac;
	private JLabel lblTrongLuong;
	private JTextField txtTrongLuong;
	private JLabel lblBaoHanh;
	private JTextField txtBaoHanh;
	private JLabel lblMaNCC;
	private LoaiLinhKienDAO loaiLinhKienDao;
	private ThuongHieuDAO thuongHieuDao;
	private NhaCungCapDAO nhaCungCapDao;
	private List<NhaCungCap> listNCC;
	private List<ThuongHieu> listTH;
	private List<LoaiLinhKien> listLLK;
	private JComboBox<String> cboThuongHieu;
	private JComboBox<String> cboNCC;
	private JComboBox<String> cboLoai;
	private JLabel lblAnh;
	private JTextField txtAnh;
	private JComboBox<String> cboTimKiem;
	private JTextField txtTimKiem;
	private JButton btnAnh;
	private JButton btnXemChiTiet;
	private JLabel lblThue;
	private JTextField txtThue;
	private JTextField txtGiaNhap;
	private String ma;
	private Date date;
	private SimpleDateFormat dt1;
	private JLabel lblKg;
	private JLabel lblCi;
	private DecimalFormat df;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public JpanelLinhKien() throws IOException {
		try {
			Conection.getKetNoi();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		linhKienDAO = new LinhKienDAO();
		thuongHieuDao = new ThuongHieuDAO();
		loaiLinhKienDao = new LoaiLinhKienDAO();
		nhaCungCapDao = new NhaCungCapDAO();

		df = new DecimalFormat("#,##0.00");
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 143, 1050, 250);
		add(scrollPane);
		String[] colHeader = { "Mã LK", "Tên Linh Kiện", "Giá Bán", "Mô Tả", "SL", "Thuế(%)", "Giá Nhập", "Thương Hiệu",
				"Tên NCC", "Loại" };

		modelLinhKien = new DefaultTableModel(colHeader, 0);
		tableLinhKien = new JTable(modelLinhKien);
		tableLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableLinhKien);
		scrollPane.setBorder(
				new TitledBorder(null, "Danh Sách Linh Kiện", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tableLinhKien.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableLinhKien.getColumnModel().getColumn(1).setPreferredWidth(170);
		tableLinhKien.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableLinhKien.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableLinhKien.getColumnModel().getColumn(4).setPreferredWidth(35);
		tableLinhKien.getColumnModel().getColumn(5).setPreferredWidth(50);
		tableLinhKien.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableLinhKien.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableLinhKien.getColumnModel().getColumn(8).setPreferredWidth(110);
		tableLinhKien.getColumnModel().getColumn(9).setPreferredWidth(100);
		listLK = linhKienDAO.getAllLinhKien();

		JTableHeader header1 = tableLinhKien.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableLinhKien.setRowHeight(25);
		docDuLieuVaoModelLinhKien();

		cboTimKiem = new JComboBox<String>();
		cboTimKiem.setBackground(Color.WHITE);
		cboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboTimKiem.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Mã linh kiện", "Tên linh kiện", "Thương hiệu", "Loại" }));
		cboTimKiem.setSelectedIndex(1);
		cboTimKiem.setBounds(54, 88, 200, 30);
		add(cboTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setBounds(301, 88, 803, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		txtTimKiem.addKeyListener(this);

		JLabel lblMSnPhm = new JLabel("Mã LK:");
		lblMSnPhm.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMSnPhm.setBounds(54, 404, 94, 30);
		add(lblMSnPhm);

		txtMaLK = new JTextField();
		txtMaLK.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaLK.setBounds(154, 408, 180, 25);
		add(txtMaLK);
		txtMaLK.setColumns(10);
		txtMaLK.setText(taoMa());
		txtMaLK.setEditable(false);

		JLabel lblTn = new JLabel("Tên linh kiện:");
		lblTn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTn.setBounds(480, 404, 132, 30);
		add(lblTn);

		txtTenLK = new JTextField();
		txtTenLK.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTenLK.setBounds(603, 408, 501, 25);
		add(txtTenLK);
		txtTenLK.setColumns(10);

		JLabel lblSLng = new JLabel("S\u1ED1 L\u01B0\u1EE3ng:");
		lblSLng.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblSLng.setBounds(799, 523, 113, 30);
		add(lblSLng);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtSoLuong.setBounds(901, 524, 113, 25);
		add(txtSoLuong);
		txtSoLuong.setColumns(10);

		JLabel lblnGi = new JLabel("Giá bán:");
		lblnGi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblnGi.setBounds(480, 443, 94, 30);
		add(lblnGi);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDonGia.setBounds(603, 445, 157, 25);
		add(txtDonGia);
		txtDonGia.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Loại:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(480, 484, 56, 32);
		add(lblNewLabel_1);

		JLabel lblmota = new JLabel("Mô Tả:");
		lblmota.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblmota.setBounds(54, 605, 76, 30);
		add(lblmota);

		JLabel lblNewLabel_2 = new JLabel("Thương hiệu:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_2.setBounds(54, 523, 113, 30);
		add(lblNewLabel_2);

		cboThuongHieu = new JComboBox<String>();
		cboThuongHieu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cboThuongHieu.setBounds(154, 525, 302, 25);
		add(cboThuongHieu);

		listTH = thuongHieuDao.getAllTH();
		cboThuongHieu.addItem("Chọn thương hiệu.");
		for (ThuongHieu th : listTH) {
			int n = 0;
			for (int i = 0; i < cboThuongHieu.getItemCount(); i++) {
				if (cboThuongHieu.getItemAt(i).equals(th.getMaTH() + " - " + th.getTenTH())) {
					n++;
				}
			}
			if (n == 0) {
				cboThuongHieu.addItem(th.getMaTH() + " - " + th.getTenTH());
			}
		}
		cboNCC = new JComboBox<String>();
		cboNCC.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cboNCC.setBounds(154, 484, 302, 25);
		add(cboNCC);

		listNCC = nhaCungCapDao.getAllNCC();
		cboNCC.addItem("Chọn nhà cung cấp.");
		for (NhaCungCap ncc : listNCC) {
			int n = 0;
			for (int i = 0; i < cboNCC.getItemCount(); i++) {
				if (cboNCC.getItemAt(i).equals(ncc.getMaNCC() + " - " + ncc.getTenNCC())) {
					n++;
				}
			}
			if (n == 0) {
				cboNCC.addItem(ncc.getMaNCC() + " - " + ncc.getTenNCC());
			}
		}
		cboLoai = new JComboBox<String>();
		cboLoai.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cboLoai.setBounds(603, 484, 157, 25);
		add(cboLoai);

		listLLK = loaiLinhKienDao.getAllLLK();
		cboLoai.addItem("Chọn loại linh kiện.");
		for (LoaiLinhKien llk : listLLK) {
			int n = 0;
			for (int i = 0; i < cboLoai.getItemCount(); i++) {
				if (cboLoai.getItemAt(i).equals(llk.getMaLoaiLK() + " - " + llk.getTenLoaiLK())) {
					n++;
				}
			}
			if (n == 0) {
				cboLoai.addItem(llk.getMaLoaiLK() + " - " + llk.getTenLoaiLK());
			}

		}

		JLabel lblNewLabel_3 = new JLabel("Ngày Nhập:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(799, 484, 120, 30);
		add(lblNewLabel_3);

		long millis = System.currentTimeMillis();
		date = new Date(millis);
		dt1 = new SimpleDateFormat("yyyy-MM-dd");

		txtNgayNhap = new JTextField();
		txtNgayNhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtNgayNhap.setBounds(901, 484, 203, 25);
		txtNgayNhap.setColumns(10);
		txtNgayNhap.setEditable(false);
		txtNgayNhap.setText(dt1.format(date));
		add(txtNgayNhap);

		tableLinhKien.addMouseListener(this);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(23, 70, 138));
		panel.setBounds(0, 0, 1151, 59);
		add(panel);
		panel.setLayout(null);

		JLabel lblQunLLinh = new JLabel("QUẢN LÝ LINH KIỆN");
		lblQunLLinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLLinh.setBounds(0, 0, 1151, 59);
		panel.add(lblQunLLinh);
		lblQunLLinh.setForeground(Color.WHITE);
		lblQunLLinh.setFont(new Font("Times New Roman", Font.BOLD, 25));

		txtMoTa = new JTextArea();
		txtMoTa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMoTa.setBounds(154, 605, 606, 67);
		add(txtMoTa);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(54, 683, 110, 50);
		btnThem.setBackground(new Color(23, 70, 138));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(224, 683, 110, 50);
		btnXoa.setBackground(new Color(23, 70, 138));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnXoa);

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setBounds(404, 683, 150, 50);
		btnXoaRong.setBackground(new Color(23, 70, 138));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnXoaRong);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(624, 683, 110, 50);
		btnSua.setBackground(new Color(23, 70, 138));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnSua);

		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setBackground(new Color(23, 70, 138));
		btnXemChiTiet.setForeground(Color.WHITE);
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemChiTiet.setBounds(892, 683, 212, 50);
		add(btnXemChiTiet);

		lblMauSac = new JLabel("Màu Sắc:");
		lblMauSac.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMauSac.setBounds(799, 443, 70, 23);
		add(lblMauSac);

		txtMauSac = new JTextField();
		txtMauSac.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtMauSac.setBounds(901, 444, 203, 25);
		add(txtMauSac);
		txtMauSac.setColumns(10);

		lblTrongLuong = new JLabel("Trọng Lượng:");
		lblTrongLuong.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTrongLuong.setBounds(480, 527, 113, 20);
		add(lblTrongLuong);

		txtTrongLuong = new JTextField();
		txtTrongLuong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTrongLuong.setBounds(603, 524, 94, 25);
		add(txtTrongLuong);
		txtTrongLuong.setColumns(10);

		lblBaoHanh = new JLabel("Bảo Hành:");
		lblBaoHanh.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblBaoHanh.setBounds(799, 561, 100, 30);
		add(lblBaoHanh);

		txtBaoHanh = new JTextField();
		txtBaoHanh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtBaoHanh.setBounds(901, 564, 113, 25);
		add(txtBaoHanh);
		txtBaoHanh.setColumns(10);

		lblMaNCC = new JLabel("NCC:");
		lblMaNCC.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblMaNCC.setBounds(54, 486, 70, 23);
		add(lblMaNCC);

		lblAnh = new JLabel("Ảnh:");
		lblAnh.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblAnh.setBounds(54, 564, 100, 30);
		add(lblAnh);

		btnAnh = new JButton("Open file");
		btnAnh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAnh.setBackground(new Color(238, 238, 238));
		btnAnh.setBounds(376, 564, 80, 25);
		add(btnAnh);

		txtAnh = new JTextField();
		txtAnh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtAnh.setBounds(154, 564, 223, 25);
		add(txtAnh);

		lblThue = new JLabel("Thuế: ");
		lblThue.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblThue.setBounds(799, 601, 76, 30);
		add(lblThue);

		txtThue = new JTextField();
		txtThue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtThue.setBounds(901, 606, 113, 25);
		add(txtThue);

		JLabel lblGiaBan = new JLabel("Giá nhập:");
		lblGiaBan.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblGiaBan.setBounds(54, 443, 94, 30);
		add(lblGiaBan);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtGiaNhap.setBounds(154, 444, 180, 25);
		add(txtGiaNhap);
		txtGiaNhap.setColumns(10);

		btnAnh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFrame parentFrame = new JFrame();
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Open file");
				int userSelection = fileChooser.showSaveDialog(parentFrame);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					txtAnh.setText(fileToSave.getAbsolutePath());
				}

			}
		});
		btnXemChiTiet.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSua.addActionListener(this);

		JLabel lblThng = new JLabel("Tháng");
		lblThng.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblThng.setBounds(1018, 561, 56, 30);
		add(lblThng);

		lblKg = new JLabel("Gram");
		lblKg.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblKg.setBounds(700, 525, 60, 20);
		add(lblKg);

		lblCi = new JLabel("Cái");
		lblCi.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCi.setBounds(1172, 655, 47, 30);
		add(lblCi);

		JLabel lblThng_1 = new JLabel("%");
		lblThng_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblThng_1.setBounds(1018, 603, 56, 30);
		add(lblThng_1);

	}

	public boolean themlk(LinhKien lk) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLinhKien";
		Connection conn = DriverManager.getConnection(url, "sa", "sapassword");
		String sql = "insert into linhkien values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int n;
		FileInputStream fis = null;

		try {
			conn.setAutoCommit(false);
			File file = new File(txtAnh.getText());
			fis = new FileInputStream(file);

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, lk.getMaLK());
			stmt.setString(2, lk.getTenLK());
			stmt.setDouble(3, lk.getGiaBan());
			stmt.setString(4, lk.getMoTa());
			stmt.setString(5, lk.getBaoHanh());
			stmt.setDate(6, lk.getNgayNhap());
			stmt.setInt(7, lk.getSoLuongTon());
			stmt.setString(8, lk.getMauSac());
			stmt.setString(9, lk.getTrongLuong());
			stmt.setFloat(10, lk.getThue());
			stmt.setDouble(11, lk.getGiaNhap());
			stmt.setString(12, lk.getThuongHieu().getMaTH());
			stmt.setString(13, lk.getNhaCungCap().getMaNCC());
			stmt.setString(14, lk.getLoaiLoaiKien().getMaLoaiLK());

			stmt.setBinaryStream(15, fis, (int) file.length());
			try {
				n = stmt.executeUpdate();
				conn.commit();
				if (n > 0)
					return true;
			} finally {
				stmt.close();
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean them() throws ClassNotFoundException, SQLException, IOException {

		String ma = txtMaLK.getText().trim();
		String ten = txtTenLK.getText().trim();
		double gia = Double.parseDouble(txtDonGia.getText());
		String mota = txtMoTa.getText().trim();
		String baohanh = txtBaoHanh.getText().trim();
		Date ngaynhap = Date.valueOf(txtNgayNhap.getText());
		int soluong = Integer.parseInt(txtSoLuong.getText());
		String mausac = txtMauSac.getText().trim();
		String trongluong = txtTrongLuong.getText().trim();
		double gianhap = Double.parseDouble(txtGiaNhap.getText().trim());
		float thue = Float.parseFloat(txtThue.getText().trim());
		String[] tachTH = cboThuongHieu.getSelectedItem().toString().split("-");
		String maTH = tachTH[0].trim();
		String[] tachNCC = cboNCC.getSelectedItem().toString().split("-");
		String maNCC = tachNCC[0].trim();
		String[] tachLLK = cboLoai.getSelectedItem().toString().split("-");
		String maLLK = tachLLK[0].trim();
		System.out.println(maNCC);

		ThuongHieu th = new ThuongHieu(maTH);
		NhaCungCap ncc = new NhaCungCap(maNCC);
		LoaiLinhKien llk = new LoaiLinhKien(maLLK);

		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		if (ngaynhap.before(date) || ngaynhap.equals(date)) {
			LinhKien lk = new LinhKien(ma, ten, gia, mota, baohanh, ngaynhap, soluong, mausac, trongluong, thue,
					gianhap, th, ncc, llk);
			if (themlk(lk)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Trùng mã");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ");
		}

		return true;
	}

	private boolean validData() {
		String ten = txtTenLK.getText().trim();
		String mau = txtMauSac.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();
		String moTa = txtMoTa.getText().trim();
		String trongluong = txtTrongLuong.getText().trim();
		String soluong = txtSoLuong.getText().trim();
		String baoHanh = txtBaoHanh.getText().trim();
		String thue = txtThue.getText().trim();
		String giaban = txtDonGia.getText().trim();

		if (ten.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên linh kiện trống!");
			txtTenLK.selectAll();
			txtTenLK.requestFocus();
			return false;
		} else {

			if ((ten.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Tên linh kiện gồm chữ cái, có thể chứ khoảng cách");
				txtTenLK.requestFocus();
				txtTenLK.selectAll();
				return false;
			}
		}

		if (mau.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Màu trống !");
			txtMauSac.selectAll();
			txtMauSac.requestFocus();
			return false;
		} else {

			if ((mau.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Màu là chữ cái");
				txtMauSac.requestFocus();
				txtMauSac.selectAll();
				return false;
			}
		}
		if (giaNhap.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Giá nhập trống!");
			txtMauSac.selectAll();
			txtMauSac.requestFocus();
			return false;
		} else {

			if (!(giaNhap.matches("^\\d+$"))) {
				JOptionPane.showMessageDialog(this, "giá nhập là các chữ số");
				txtGiaNhap.requestFocus();
				txtGiaNhap.selectAll();
				return false;
			} else {
				double gia1 = Double.parseDouble(giaNhap);
				if (!(gia1 > 0)) {
					JOptionPane.showMessageDialog(this, "Giá nhập phải >0");
					txtGiaNhap.requestFocus();
					txtGiaNhap.selectAll();
					return false;
				}
			}
		}
		if (giaban.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Giá bán trống !");
			txtDonGia.selectAll();
			txtDonGia.requestFocus();
			return false;
		} else {

			if (!(giaban.matches("^\\d+$"))) {
				JOptionPane.showMessageDialog(this, "giá bán là các chữ số");
				txtDonGia.selectAll();
				txtDonGia.requestFocus();
				return false;
			} else {
				double gia2 = Double.parseDouble(giaban);
				if (!(gia2 > 0)) {
					JOptionPane.showMessageDialog(this, "Giá bán phải >0");
					txtDonGia.selectAll();
					txtDonGia.requestFocus();
					return false;
				}
			}
		}

		if (trongluong.trim().equals("")) {

			JOptionPane.showMessageDialog(this, "Trọng luợng trống!");
			txtTrongLuong.selectAll();
			txtTrongLuong.requestFocus();
			return false;
		} else {
			if (!(trongluong.matches("^\\d+$"))) {
				JOptionPane.showMessageDialog(this, "trọng lượng là các chữ số");
				txtTrongLuong.requestFocus();
				txtTrongLuong.selectAll();
				return false;
			} else {
				// double gia2 = Double.parseDouble(giaban);
				double tl = Double.parseDouble(trongluong);
				if (tl <= 0) {

					JOptionPane.showMessageDialog(this, "Trọng lượng phải > 0");
					txtTrongLuong.requestFocus();
					txtTrongLuong.selectAll();
					return false;
				}
			}
		}
		if (thue.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Thuế trống!");
			txtThue.selectAll();
			txtThue.requestFocus();
			return false;
		} else {
			if (!(thue.matches("^\\d+$"))) {
				JOptionPane.showMessageDialog(this, " thuế là các chữ số");

			} else {
				Float th = Float.parseFloat(thue);

				if (!(th >= 0)) {
					JOptionPane.showMessageDialog(this, "Thuế phải >=0");
					txtThue.requestFocus();
					txtThue.selectAll();
					return false;
				}
			}
		}
		if (soluong.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số lượng trống!");
			txtSoLuong.selectAll();
			txtSoLuong.requestFocus();
			return false;
		} else {

			if (!(soluong.matches("^\\d+$"))) {
				JOptionPane.showMessageDialog(this, "số lượng là các chữ số");
				txtSoLuong.selectAll();
				txtSoLuong.requestFocus();
				return false;
			} else {
				int sl = Integer.parseInt(soluong);
				if (!(sl > 0)) {
					JOptionPane.showMessageDialog(this, "Số lượng phải >0");
					txtSoLuong.requestFocus();
					txtSoLuong.selectAll();
					return false;
				}

			}
		}
		if (baoHanh.trim().equals(""))

		{
			JOptionPane.showMessageDialog(this, "Thời gian bảo hành trống !");
			txtBaoHanh.selectAll();
			txtBaoHanh.requestFocus();
			return false;
		} else {

			if (!(baoHanh.matches("^\\d+$"))) {
				JOptionPane.showMessageDialog(this, "Thời gian bảo hành là các chữ số");
				txtBaoHanh.requestFocus();
				txtBaoHanh.selectAll();
				return false;
			} else {
				int bh = Integer.parseInt(baoHanh);
				if (!(bh > 0)) {
					JOptionPane.showMessageDialog(this, "Thời gian bảo hành phải >0");
					txtBaoHanh.requestFocus();
					txtBaoHanh.selectAll();
					return false;
				}
			}
		}
		if (moTa.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Mô tả không được trống !");
			txtMoTa.selectAll();
			txtMoTa.requestFocus();
			return false;
		} else {

			if (moTa.matches(
					"^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]")) {
				JOptionPane.showMessageDialog(this, "Mô tả dồm chữ cái, số,...");
				txtMoTa.requestFocus();
				txtMoTa.selectAll();
				return false;

			}
		}
		if (txtAnh.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Ảnh không được trống !");
			txtMoTa.selectAll();
			txtMoTa.requestFocus();
			return false;
		}
		return true;
	}

	public void xoaRong() {
		// txtMaLK.setText("");
		txtMaLK.setText(taoMa());
		txtTenLK.setText("");
		txtMoTa.setText("");
		txtDonGia.setText("");
		txtBaoHanh.setText("");
		txtSoLuong.setText("");
		txtGiaNhap.setText("");
		txtTrongLuong.setText("");
		txtThue.setText("");
		txtMauSac.setText("");
		cboThuongHieu.setSelectedIndex(0);
		cboNCC.setSelectedIndex(0);
		cboLoai.setSelectedIndex(0);
		txtAnh.setText("");
		txtNgayNhap.setText(dt1.format(date));
	}

	public void xoaHetModel() {
		DefaultTableModel df = (DefaultTableModel) tableLinhKien.getModel();
		df.getDataVector().removeAllElements();
	}

	public boolean xoa() throws IOException {
		ArrayList<LinhKien> dslk = (ArrayList<LinhKien>) linhKienDAO.getAllLinhKien();
		int r = tableLinhKien.getSelectedRow();
		int tl = JOptionPane.showConfirmDialog(this, "Có muốn xóa hay không ?", "", JOptionPane.YES_NO_OPTION);
		if (tl == JOptionPane.YES_OPTION) {
			LinhKien lk = dslk.get(r);
			linhKienDAO.deleteLK(lk);
			modelLinhKien.removeRow(r);
			return true;
		}
		return false;
	}

	public boolean CapNhatLK(String ma, LinhKien lk) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QLLinhKien";
		Connection conn = DriverManager.getConnection(url, "sa", "sapassword");
		String sql = "update LinhKien \r\n"
				+ "set TenLK=?,GiaBan=?,MoTa=?,BaoHanh=?,NgayNhap=?,SoLuongTon=?,MauSac=?,TrongLuong=?,Thue=?,GiaNhap=?,MaTH=?,MaNCC=?,MaLoaiLK=?,Anh=? where MaLK=?";
		int n;
		FileInputStream fis = null;

		try {
			conn.setAutoCommit(false);
			File file = new File(txtAnh.getText());
			try {
				fis = new FileInputStream(file);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Chưa chọn ảnh");
			}

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, lk.getTenLK());
			stmt.setDouble(2, lk.getGiaBan());
			stmt.setString(3, lk.getMoTa());
			stmt.setString(4, lk.getBaoHanh());
			stmt.setDate(5, lk.getNgayNhap());
			stmt.setInt(6, lk.getSoLuongTon());
			stmt.setString(7, lk.getMauSac());
			stmt.setString(8, lk.getTrongLuong());
			stmt.setFloat(9, lk.getThue());
			stmt.setDouble(10, lk.getGiaNhap());
			stmt.setString(11, lk.getThuongHieu().getMaTH());
			stmt.setString(12, lk.getNhaCungCap().getMaNCC());
			stmt.setString(13, lk.getLoaiLoaiKien().getMaLoaiLK());
			stmt.setBinaryStream(14, fis, (int) file.length());
			stmt.setString(15, lk.getMaLK());
			try {
				n = stmt.executeUpdate();
				conn.commit();
				if (n > 0)
					return true;
			} finally {
				stmt.close();
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sua() throws ClassNotFoundException, SQLException, IOException {
		int row = tableLinhKien.getSelectedRow();
		if (row != -1) {
			String ma = txtMaLK.getText().trim();
			String ten = txtTenLK.getText().trim();
			double gia = Double.parseDouble(txtDonGia.getText());
			String mota = txtMoTa.getText().trim();
			String baohanh = txtBaoHanh.getText().trim();
			Date ngaynhap = Date.valueOf(txtNgayNhap.getText());
			int soluong = Integer.parseInt(txtSoLuong.getText());
			String mausac = txtMauSac.getText().trim();
			String trongluong = txtTrongLuong.getText().trim();
			double gianhap = Double.parseDouble(txtGiaNhap.getText().trim());
			float thue = Float.parseFloat(txtThue.getText().trim());
			String[] tachTH = cboThuongHieu.getSelectedItem().toString().split("-");
			String maTH = tachTH[0].trim();
			String[] tachNCC = cboNCC.getSelectedItem().toString().split("-");
			String maNCC = tachNCC[0].trim();
			String[] tachLLK = cboLoai.getSelectedItem().toString().split("-");
			String maLLK = tachLLK[0].trim();

			ThuongHieu th = new ThuongHieu(maTH);
			NhaCungCap ncc = new NhaCungCap(maNCC);
			LoaiLinhKien llk = new LoaiLinhKien(maLLK);

			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			if (ngaynhap.before(date) || ngaynhap.equals(date)) {
				if (CapNhatLK(ma, new LinhKien(ma, ten, gia, mota, baohanh, ngaynhap, soluong, mausac, trongluong, thue,
						gianhap, th, ncc, llk))) {

					modelLinhKien.setValueAt(ma, row, 0);
					modelLinhKien.setValueAt(ten, row, 1);
					modelLinhKien.setValueAt(gia, row, 2);
					modelLinhKien.setValueAt(mota, row, 3);
					// modelLinhKien.setValueAt(baohanh, row, 4);
					// modelLinhKien.setValueAt(ngaynhap, row, 5);
					modelLinhKien.setValueAt(soluong, row, 4);
					// modelLinhKien.setValueAt(mausac, row, 7);
					// modelLinhKien.setValueAt(trongluong, row, 8);
					modelLinhKien.setValueAt(thue, row, 5);
					modelLinhKien.setValueAt(gianhap, row, 6);
					modelLinhKien.setValueAt(th.getMaTH(), row, 7);
					modelLinhKien.setValueAt(ncc.getMaNCC(), row, 8);
					modelLinhKien.setValueAt(llk.getMaLoaiLK(), row, 9);

					txtMaLK.setEditable(false);
					JOptionPane.showMessageDialog(this, "Sửa thành công");
					return true;

				}
			}
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e1) {

		Object o = e1.getSource();
		if (o.equals(btnXemChiTiet)) {
			try {
				xemChiTietLinhKien();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (o.equals(btnThem)) {

			try {
				if (validData()) {
					if (them()) {
						listLK = linhKienDAO.getAllLinhKien();
						docDuLieuVaoModelLinhKien();
						tableLinhKien.clearSelection();
					}
				}
			} catch (HeadlessException | ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (o.equals(btnSua)) {
			try {
				if (validData()) {
					if (sua()) {
						listLK = linhKienDAO.getAllLinhKien();
						docDuLieuVaoModelLinhKien();
						tableLinhKien.clearSelection();
					}
				}
			} catch (HeadlessException | ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (o.equals(btnXoaRong))

			xoaRong();
		else if (o.equals(btnXoa))

		{
			try {
				if (xoa()) {

					try {
						listLK = linhKienDAO.getAllLinhKien();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					docDuLieuVaoModelLinhKien();
					tableLinhKien.clearSelection();
					xoaRong();
					JOptionPane.showMessageDialog(this, "Xóa thành công!");
				} else
					JOptionPane.showMessageDialog(this, "Xóa thất bại!");
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void xemChiTietLinhKien() throws IOException {
		int row = tableLinhKien.getSelectedRow();
		if (row != -1) {

			String maLK = modelLinhKien.getValueAt(row, 0).toString();
			listLK1 = linhKienDAO.timKiemLinhKien("MaLK", maLK);
			String ten = listLK.get(0).getTenLK();
			Double donGia = Double.parseDouble(modelLinhKien.getValueAt(row, 2).toString().replace(",", ""));
			String moTa = listLK.get(0).getMoTa();
			Date ngayNhap = listLK.get(0).getNgayNhap();
			int slTon = listLK.get(0).getSoLuongTon();
			String baoHanh = listLK.get(0).getBaoHanh();
			String mauSac = listLK.get(0).getMauSac();
			String trongLuong = listLK.get(0).getTrongLuong();
			float thue = listLK.get(0).getThue();
			double gianhap = listLK.get(0).getGiaNhap();
			String thuongHieu = tableLinhKien.getValueAt(row, 7).toString();
			String ncc = tableLinhKien.getValueAt(row, 8).toString();
			String loai = tableLinhKien.getValueAt(row, 9).toString();
			byte[] anh = listLK.get(0).getAnh();

			ThuongHieu th = new ThuongHieu(thuongHieu);
			NhaCungCap n = new NhaCungCap(ncc);
			LoaiLinhKien l = new LoaiLinhKien(loai);

			frm_ChiTietLinhKien lk = new frm_ChiTietLinhKien(new LinhKien(maLK, ten, donGia, moTa, baoHanh, ngayNhap,
					slTon, mauSac, trongLuong, thue, gianhap, th, n, l, anh));
			lk.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng cần xem");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableLinhKien.getSelectedRow();
		if (row != -1) {
			try {
				listLK = linhKienDAO.timKiemLinhKien("MaLK", modelLinhKien.getValueAt(row, 0).toString().trim());
				txtMaLK.setText(listLK.get(0).getMaLK());
				txtTenLK.setText(listLK.get(0).getTenLK());
				txtDonGia.setText(listLK.get(0).getGiaBan() + "");
				txtMoTa.setText(listLK.get(0).getMoTa());
				txtBaoHanh.setText(listLK.get(0).getBaoHanh());
				txtNgayNhap.setText(listLK.get(0).getNgayNhap() + "");
				txtSoLuong.setText(listLK.get(0).getSoLuongTon() + "");
				txtMauSac.setText(listLK.get(0).getMauSac());
				txtTrongLuong.setText(listLK.get(0).getTrongLuong());
				txtThue.setText(listLK.get(0).getThue() + "");
				txtGiaNhap.setText(listLK.get(0).getGiaNhap() + "");
				cboThuongHieu.setSelectedItem(
						listLK.get(0).getThuongHieu().getMaTH() + " - " + listLK.get(0).getThuongHieu().getTenTH());
				cboNCC.setSelectedItem(
						listLK.get(0).getNhaCungCap().getMaNCC() + " - " + listLK.get(0).getNhaCungCap().getTenNCC());
				cboLoai.setSelectedItem(listLK.get(0).getLoaiLoaiKien().getMaLoaiLK() + " - "
						+ listLK.get(0).getLoaiLoaiKien().getTenLoaiLK());
				txtAnh.setText(listLK.get(0).getAnh() + "");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		Object o = arg0.getSource();
		if (o.equals(txtTimKiem)) {
			if (!txtTimKiem.getText().equals("")) {
				if (cboTimKiem.getSelectedIndex() == 0) {

					try {
						listLK = linhKienDAO.timKiemLinhKien("MaLK", txtTimKiem.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					docDuLieuVaoModelLinhKien();

				}
				if (cboTimKiem.getSelectedIndex() == 1) {

					try {
						listLK = linhKienDAO.timKiemLinhKien("TenLK", txtTimKiem.getText());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					docDuLieuVaoModelLinhKien();

				}
			}
			if (cboTimKiem.getSelectedIndex() == 2) {

				try {
					listLK = linhKienDAO.timKiemLinhKien("TenTH", txtTimKiem.getText());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				docDuLieuVaoModelLinhKien();

			}

			if (cboTimKiem.getSelectedIndex() == 3) {

				try {
					listLK = linhKienDAO.timKiemLinhKien("TenLoaiLK", txtTimKiem.getText());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				docDuLieuVaoModelLinhKien();

			}

		} else {
			try {
				listLK = linhKienDAO.getAllLinhKien();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			docDuLieuVaoModelLinhKien();

		}

	}

	public void docDuLieuVaoModelLinhKien() {
		modelLinhKien.getDataVector().removeAllElements();
		for (LinhKien lk : listLK) {
			modelLinhKien.addRow(new Object[] { lk.getMaLK(), lk.getTenLK(), df.format(lk.getGiaBan()), lk.getMoTa(),
					lk.getSoLuongTon(), lk.getThue(), df.format(lk.getGiaNhap()), lk.getThuongHieu().getTenTH(),
					lk.getNhaCungCap().getTenNCC(), lk.getLoaiLoaiKien().getTenLoaiLK(), });
		}
	}

//	public void LoadDBData2JTable1() throws Exception {
//
//		modelLinhKien.getDataVector().removeAllElements();
//		for (LinhKien lk : listLK) {
//			modelLinhKien.addRow(new Object[] { lk.getMaLK(), lk.getTenLK(), lk.getGiaBan(), lk.getMoTa(),
//					lk.getBaoHanh(), lk.getNgayNhap(), lk.getSoLuongTon(), lk.getMauSac(), lk.getTrongLuong(),
//					lk.getThuongHieu().getTenTH(), lk.getNhaCungCap().getTenNCC(), lk.getLoaiLoaiKien().getTenLoaiLK(),
//					lk.getThuongHieu().getXuatXu() });
//		}
//
	// }
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Hàm tạo mã Hóa đơn, mã khách hàng
	 * 
	 * @param loaiMa
	 * @return ma
	 */
	@SuppressWarnings("unlikely-arg-type")
	public String taoMa() {

		linhKienDAO = new LinhKienDAO();
		Random rand = new Random();
		// int n = khachHangDAO.getAllKH().size();
		int rd = rand.nextInt(10000 - 1000) + 1000;

		do {
			// n=n+1;
			rd = rand.nextInt(10000 - 1000) + 1000;
			ma = "LK_" + String.valueOf(rd);
			try {
				listLK = linhKienDAO.getAllLinhKien();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (listLK.contains(ma));
		return ma;
	}
}
