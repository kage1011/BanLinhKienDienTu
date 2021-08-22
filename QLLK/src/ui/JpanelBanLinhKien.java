package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.LinhKienDAO;
import dao.LoaiLinhKienDAO;
import dao.NhaCungCapDAO;
import dao.NhanVienDAO;
import dao.ThuongHieuDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.ThuongHieu;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.EventObject;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class JpanelBanLinhKien extends JPanel implements KeyListener, ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pnlThongTinLK;
	private JPanel pnlHoaDon;
	private JPanel pnlThongTin;
	private JPanel pnlHD;
	private JPanel pnlThanhToan;
	private JLabel lblThongTinLK;
	private JLabel lblTimKiem;
	private JLabel lblHoaDon;
	private JLabel lblMaHoDon;
	private JLabel lblNgayLap;
	private JLabel lblMaKhachHang;
	private JLabel lblKhachHang;
	private JLabel lblSoDienThoai;
	private JLabel lblDiaChi;
	private JLabel lblTienThoi;
	private JLabel lblSoLuong;
	private JLabel lblTongTien;
	private JLabel lblTienKhachTra;
	private JTextField txtMaHoaDon;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtSoDienThoai;
	private JTextField txtNgayLapHD;
	private JTextField txtDiaChiKH;
	private JTextField txtTrang;
	private JTextField txtTimKiem;
	private JTextField txtTongTien;
	private JTextField txtTienKhachTra;
	private JTextField txtTienThoi;
	private JTextField txtSoLuong;
	private JButton btnThanhToan;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnThem;
	private JButton btnXemChiTiet;
	private JButton btnTaoMoi;
	private DefaultTableModel modelThongTinLK;
	private DefaultTableModel modelHoaDon;
	private JTable tableThongTinLK;
	private JTable tableHoaDon;
	private JScrollPane scrollPaneLK;
	private JScrollPane scrollPaneHoaDon;
	private JComboBox<String> cboTimKiem;
	private LinhKienDAO linhKienDao;
	private KhachHangDAO khachHangDAO;
	private NhaCungCapDAO nhaCungCapDao;
	private LoaiLinhKienDAO loaiLinhKienDao;
	private ThuongHieuDAO thuongHieuDao;
	private List<LinhKien> listLK;
	@SuppressWarnings("unused")
	private List<NhaCungCap> listNCC;
	@SuppressWarnings("unused")
	private List<ThuongHieu> listTH;
	@SuppressWarnings("unused")
	private List<LoaiLinhKien> listLLK;
	private List<KhachHang> listKH;
	private DecimalFormat df;
	private SimpleDateFormat dt1;
	private Date date;
	private NhanVien nhanVien;

	private String ma;

	private HoaDonDAO hoaDonDao;

	private List<HoaDon> listHD;

	private ChiTietHoaDonDAO chiTietHoaDonDAO;

	private NhanVienDAO nhanVienDAO;

	JpanelKhachHang_ex kh;

	private JButton btnTaoMoiKH;
	private JTextField txtTimKiem1;

	private JComboBox<String> cboTimKiem_1;
	private JLabel lblTenLK;
	private JLabel lblThngHiu;
	private JComboBox<String> cboTimKiem_2;
	private JLabel lblLoiLinhKin;
	private JTextField txtTimKiem2;

	/**
	 * Tạo giao diện trang chủ.
	 * 
	 * @throws Exception
	 */
	public JpanelBanLinhKien(NhanVien nhanVien) throws Exception {
		setBackground(new Color(225, 225, 225));
		setLayout(null);
		linhKienDao = new LinhKienDAO();
		nhaCungCapDao = new NhaCungCapDAO();
		thuongHieuDao = new ThuongHieuDAO();
		loaiLinhKienDao = new LoaiLinhKienDAO();

		this.nhanVien = nhanVien;
		df = new DecimalFormat("#,##0.0");
		long millis = System.currentTimeMillis();
		date = new Date(millis);
		dt1 = new SimpleDateFormat("yyyy-MM-dd");
		ThongTinLKpnl();
		HoaDonpnl();

		listLK = linhKienDao.getAllLinhKien();
		listNCC = nhaCungCapDao.getAllNCC();
		listLLK = loaiLinhKienDao.getAllLLK();
		listTH = thuongHieuDao.getAllTH();
		khachHangDAO = new KhachHangDAO();
		listKH = khachHangDAO.getAllKH();

		LoadDBData2JTable1();
		dangKySuKien();
	}

	/**
	 * Hàm tạo giao diện cho pnl Thông tin linh kiện
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void ThongTinLKpnl() {
		// TODO Auto-generated method stub
		pnlThongTinLK = new JPanel();
		pnlThongTinLK.setBackground(new Color(225, 225, 225));
		pnlThongTinLK.setBounds(0, 0, 555, 750);
		this.add(pnlThongTinLK);
		pnlThongTinLK.setLayout(null);

		lblTimKiem = new JLabel("Tìm kiếm theo:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTimKiem.setBounds(10, 70, 164, 16);
		pnlThongTinLK.add(lblTimKiem);

		String[] colHeaderThongTinLK = { "Mã LK", "Tên LK", "Giá Bán", "SLT", "TH", "Loại", "Ảnh" };
		modelThongTinLK = new DefaultTableModel(colHeaderThongTinLK, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public java.lang.Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 6)
					return ImageIcon.class;
				return Object.class;
			};
		};
		tableThongTinLK = new JTable(modelThongTinLK) {
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, EventObject e) { // Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		JTableHeader header = tableThongTinLK.getTableHeader();
		header.setBackground(new Color(23, 70, 138));
		header.setForeground(Color.white);
		header.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableThongTinLK.setBackground(Color.WHITE);
		tableThongTinLK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneLK = new JScrollPane(tableThongTinLK);
		scrollPaneLK.setBounds(12, 147, 533, 524);
		pnlThongTinLK.add(scrollPaneLK);

		tableThongTinLK.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableThongTinLK.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableThongTinLK.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableThongTinLK.getColumnModel().getColumn(3).setPreferredWidth(10);
		tableThongTinLK.getColumnModel().getColumn(4).setPreferredWidth(50);
		tableThongTinLK.getColumnModel().getColumn(5).setPreferredWidth(60);
		tableThongTinLK.getColumnModel().getColumn(6).setPreferredWidth(70);

		// scrollPaneLK.setViewportView(tableThongTinLK);
		tableThongTinLK.setRowHeight(200);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(JpanelBanLinhKien.class.getResource("/images/icons8_buy_24px.png")));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(23, 70, 138));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(223, 682, 108, 50);
		pnlThongTinLK.add(btnThem);

		txtSoLuong = new JTextField();
		txtSoLuong.setBackground(Color.WHITE);
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSoLuong.setBounds(449, 690, 63, 30);
		txtSoLuong.setText("1");
		pnlThongTinLK.add(txtSoLuong);

		lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoLuong.setBounds(375, 690, 89, 30);
		pnlThongTinLK.add(lblSoLuong);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiem.setBounds(106, 111, 140, 25);
		pnlThongTinLK.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		cboTimKiem = new JComboBox<String>();
		cboTimKiem.setBackground(Color.WHITE);
		cboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboTimKiem.setModel(new DefaultComboBoxModel(new String[] { "Tên linh kiện" }));
		cboTimKiem.setSelectedIndex(0);
		cboTimKiem.setBounds(173, 119, 63, 16);
		pnlThongTinLK.add(cboTimKiem);

		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setIcon(new ImageIcon(JpanelBanLinhKien.class.getResource("/images/more_details_24px.png")));
		btnXemChiTiet.setForeground(Color.WHITE);
		btnXemChiTiet.setBackground(new Color(23, 70, 138));
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemChiTiet.setBounds(37, 682, 159, 50);
		pnlThongTinLK.add(btnXemChiTiet);

		pnlThongTin = new JPanel();
		pnlThongTin.setBackground(new Color(23, 70, 138));
		pnlThongTin.setBounds(0, 0, 623, 59);
		pnlThongTinLK.add(pnlThongTin);
		pnlThongTin.setLayout(null);

		lblThongTinLK = new JLabel("Thông tin linh kiện");
		lblThongTinLK.setForeground(Color.WHITE);
		lblThongTinLK.setBounds(0, 0, 623, 59);
		pnlThongTin.add(lblThongTinLK);
		lblThongTinLK.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblThongTinLK.setHorizontalAlignment(SwingConstants.CENTER);

	}

	/**
	 * Hàm tạo giao diện cho panel Hóa đơn bán hàng
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void HoaDonpnl() {
		// TODO Auto-generated method stub
		pnlHoaDon = new JPanel();
		pnlHoaDon.setBackground(new Color(225, 225, 225));
		pnlHoaDon.setBounds(554, 0, 627, 820);
		this.add(pnlHoaDon);
		pnlHoaDon.setLayout(null);

		lblMaHoDon = new JLabel("Mã HD");
		lblMaHoDon.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaHoDon.setBounds(12, 70, 60, 30);
		pnlHoaDon.add(lblMaHoDon);

		lblNgayLap = new JLabel("Ngày lập HD");
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgayLap.setBounds(359, 110, 88, 23);
		pnlHoaDon.add(lblNgayLap);

		lblMaKhachHang = new JLabel("Mã KH");
		lblMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaKhachHang.setBounds(359, 72, 88, 27);
		pnlHoaDon.add(lblMaKhachHang);

		lblKhachHang = new JLabel("Tên KH");
		lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKhachHang.setBounds(12, 106, 60, 30);
		pnlHoaDon.add(lblKhachHang);

		lblSoDienThoai = new JLabel("SĐT");
		lblSoDienThoai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoDienThoai.setBounds(12, 144, 60, 30);
		pnlHoaDon.add(lblSoDienThoai);

		lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiaChi.setBounds(289, 144, 66, 28);
		pnlHoaDon.add(lblDiaChi);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(476, 73, 103, 25);
		pnlHoaDon.add(txtMaKhachHang);
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setText(taoMa("KhachHang"));

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(79, 109, 186, 25);
		pnlHoaDon.add(txtTenKhachHang);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(79, 147, 186, 25);
		pnlHoaDon.add(txtSoDienThoai);

		txtNgayLapHD = new JTextField();
		txtNgayLapHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNgayLapHD.setColumns(10);
		txtNgayLapHD.setBounds(476, 109, 103, 25);
		txtNgayLapHD.setEditable(false);

		txtNgayLapHD.setText(dt1.format(date));

		pnlHoaDon.add(txtNgayLapHD);

		txtDiaChiKH = new JTextField();
		txtDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDiaChiKH.setColumns(10);
		txtDiaChiKH.setBounds(343, 147, 236, 25);
		pnlHoaDon.add(txtDiaChiKH);

		txtTrang = new JTextField();
		txtTrang.setText("1");
		txtTrang.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrang.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtTrang.setBounds(1122, 5652, 56, 32);
		pnlThongTinLK.add(txtTrang);
		txtTrang.setColumns(10);
		txtTrang.setEditable(false);

		txtTimKiem1 = new JTextField();
		txtTimKiem1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiem1.setColumns(10);
		txtTimKiem1.setBounds(375, 111, 146, 25);
		pnlThongTinLK.add(txtTimKiem1);

		cboTimKiem_1 = new JComboBox<String>();
		cboTimKiem_1.setModel(new DefaultComboBoxModel(new String[] { "Thương hiệu" }));
		cboTimKiem_1.setSelectedIndex(0);
		cboTimKiem_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboTimKiem_1.setBackground(Color.WHITE);
		cboTimKiem_1.setBounds(475, 119, 24, 16);
		pnlThongTinLK.add(cboTimKiem_1);

		lblTenLK = new JLabel("Tên Linh Kiện:");
		lblTenLK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenLK.setBounds(12, 108, 119, 30);
		pnlThongTinLK.add(lblTenLK);

		lblThngHiu = new JLabel("Thương Hiệu:");
		lblThngHiu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThngHiu.setBounds(272, 111, 119, 30);
		pnlThongTinLK.add(lblThngHiu);

		cboTimKiem_2 = new JComboBox<String>();
		cboTimKiem_2.setModel(new DefaultComboBoxModel(new String[] { "LoạiLK" }));
		cboTimKiem_2.setSelectedIndex(0);
		cboTimKiem_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboTimKiem_2.setBackground(Color.WHITE);
		cboTimKiem_2.setBounds(496, 116, 24, 16);
		pnlThongTinLK.add(cboTimKiem_2);

		lblLoiLinhKin = new JLabel("Loại Linh Kiện:");
		lblLoiLinhKin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoiLinhKin.setBounds(272, 71, 119, 30);
		pnlThongTinLK.add(lblLoiLinhKin);

		txtTimKiem2 = new JTextField();
		txtTimKiem2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiem2.setColumns(10);
		txtTimKiem2.setBounds(375, 70, 146, 25);
		pnlThongTinLK.add(txtTimKiem2);

		String[] colHeaderHoaDon = { "Mã LK", "Tên linh kiện", "SL", "Đơn giá", "Bảo hành", "Thành tiền", "Thuế(%)" };
		modelHoaDon = new DefaultTableModel(colHeaderHoaDon, 0);
		tableHoaDon = new JTable(modelHoaDon) {
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, EventObject e) { // Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		JTableHeader header = tableHoaDon.getTableHeader();
		header.setBackground(new Color(23, 70, 138));
		header.setForeground(Color.white);
		header.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPaneHoaDon = new JScrollPane(tableHoaDon);
		scrollPaneHoaDon.setBounds(13, 209, 566, 408);
		pnlHoaDon.add(scrollPaneHoaDon);
		tableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableHoaDon.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableHoaDon.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableHoaDon.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableHoaDon.getColumnModel().getColumn(5).setPreferredWidth(90);
		tableHoaDon.getColumnModel().getColumn(6).setPreferredWidth(70);
		tableHoaDon.setRowHeight(25);
		// scrollPaneHD.setViewportView(tableHoaDon);

		pnlThanhToan = new JPanel();
		pnlThanhToan.setBorder(
				new TitledBorder(null, "Thanh to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThanhToan.setBounds(12, 628, 303, 105);
		pnlHoaDon.add(pnlThanhToan);
		pnlThanhToan.setLayout(null);

		lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongTien.setBounds(10, 11, 137, 30);
		pnlThanhToan.add(lblTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTongTien.setBounds(124, 16, 152, 20);
		pnlThanhToan.add(txtTongTien);
		txtTongTien.setColumns(10);
		txtTongTien.setEditable(false);

		lblTienKhachTra = new JLabel("Tiền khách trả:");
		lblTienKhachTra.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTienKhachTra.setBounds(10, 38, 152, 30);
		pnlThanhToan.add(lblTienKhachTra);

		lblTienThoi = new JLabel("Tiền thối lại:");
		lblTienThoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTienThoi.setBounds(10, 68, 137, 30);
		pnlThanhToan.add(lblTienThoi);

		txtTienKhachTra = new JTextField();
		txtTienKhachTra.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTienKhachTra.setBounds(124, 45, 152, 20);
		pnlThanhToan.add(txtTienKhachTra);
		txtTienKhachTra.setColumns(10);

		txtTienThoi = new JTextField();
		txtTienThoi.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTienThoi.setBounds(124, 75, 152, 20);
		pnlThanhToan.add(txtTienThoi);
		txtTienThoi.setColumns(10);
		txtTienThoi.setEditable(false);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setIcon(new ImageIcon(JpanelBanLinhKien.class.getResource("/images/checkout_24px.png")));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThanhToan.setBackground(new Color(23, 70, 138));
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setBounds(430, 684, 149, 41);
		pnlHoaDon.add(btnThanhToan);

		btnTaoMoi = new JButton("Tạo mới");
		btnTaoMoi.setIcon(new ImageIcon(JpanelBanLinhKien.class.getResource("/images/new_copy_24px.png")));
		btnTaoMoi.setForeground(Color.WHITE);
		btnTaoMoi.setBackground(new Color(23, 70, 138));
		btnTaoMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTaoMoi.setBounds(430, 628, 149, 41);
		pnlHoaDon.add(btnTaoMoi);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(JpanelBanLinhKien.class.getResource("/images/delete_document_24px.png")));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBounds(325, 628, 95, 41);
		pnlHoaDon.add(btnXoa);
		btnXoa.setBackground(new Color(23, 70, 138));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(JpanelBanLinhKien.class.getResource("/images/update_file_24px.png")));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBounds(325, 684, 95, 41);
		pnlHoaDon.add(btnSua);
		btnSua.setBackground(new Color(23, 70, 138));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setBounds(79, 73, 103, 25);
		pnlHoaDon.add(txtMaHoaDon);
		txtMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaHoaDon.setColumns(10);
		txtMaHoaDon.setEditable(false);
		txtMaHoaDon.setText(taoMa("HoaDon"));

		pnlHD = new JPanel();
		pnlHD.setBackground(new Color(23, 70, 138));
		pnlHD.setBounds(0, 0, 771, 59);
		pnlHoaDon.add(pnlHD);
		pnlHD.setLayout(null);

		lblHoaDon = new JLabel("Hóa đơn bán hàng");
		lblHoaDon.setForeground(Color.WHITE);
		lblHoaDon.setBounds(0, 0, 700, 59);
		pnlHD.add(lblHoaDon);
		lblHoaDon.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);

		btnTaoMoiKH = new JButton("Tạo KH mới");
		btnTaoMoiKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTaoMoiKH.setBackground(new Color(23, 70, 138));
		btnTaoMoiKH.setForeground(Color.WHITE);
		btnTaoMoiKH.setBounds(459, 175, 120, 30);
		pnlHoaDon.add(btnTaoMoiKH);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(txtTenKhachHang)) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				txtSoDienThoai.requestFocus();
			}
		} else if (o.equals(txtSoDienThoai)) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				if (txtSoDienThoai.getText().matches("^0\\d{9}$")) {
					listKH = khachHangDAO.timKieKH("SDT", txtSoDienThoai.getText().trim());
					if (listKH.size() == 1) {
						txtMaKhachHang.setText(listKH.get(0).getMaKH());
						txtDiaChiKH.setText(listKH.get(0).getDiaChi());
						txtTenKhachHang.setText(listKH.get(0).getTenKH());
					} else {
						txtMaKhachHang.setText(taoMa("KhachHang"));
						txtDiaChiKH.requestFocus();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Không tồn tại KH");
					txtSoDienThoai.selectAll();

				}
			}

		} else if (o.equals(txtTienKhachTra)) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				if (txtTienKhachTra.getText().matches("^\\d+$")) {
					tinhTienThoi();
				} else {
					txtTienThoi.setText("");
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(txtTimKiem) || o.equals(txtTimKiem1) || o.equals(txtTimKiem2)) {
			if (!(txtTimKiem).getText().equals("") || !(txtTimKiem1).getText().equals("")
					|| !(txtTimKiem2).getText().equals("")) {
				if (cboTimKiem.getSelectedIndex() == 0 || cboTimKiem_1.getSelectedIndex() == 0
						|| cboTimKiem_2.getSelectedIndex() == 0) {
					try {
						listLK = linhKienDao.timKiemLinhKienban("TenLK", txtTimKiem.getText(), "TenTH",
								txtTimKiem1.getText(), "TenLoaiLK", txtTimKiem2.getText());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					capNhatSoLuongModel();
					txtTrang.setText("1");
				}
//				if (cboTimKiem.getSelectedIndex() == 1|| cboTimKiem_1.getSelectedIndex() == 1) {
//					try {
//						listLK = linhKienDao.timKiemLinhKien("TenLK", txtTimKiem.getText(),"TenLK",txtTimKiem1.getText());
//					} catch (IOException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//					try {
//						LoadDBData2JTable1();
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					capNhatSoLuongModel();
//					txtTrang.setText("1");
//				}
//				if (cboTimKiem.getSelectedIndex() == 2 ||cboTimKiem_1.getSelectedIndex() == 2  ) {
//					try {
//						listLK = linhKienDao.timKiemLinhKien("TenTH", txtTimKiem.getText(),"TenTH", txtTimKiem1.getText());
//					} catch (IOException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//
//					try {
//						LoadDBData2JTable1();
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					capNhatSoLuongModel();
//					txtTrang.setText("1");
//				}
//				if (cboTimKiem.getSelectedIndex() == 3 ||cboTimKiem_1.getSelectedIndex() == 3) {
//					try {
//						listLK = linhKienDao.timKiemLinhKien("TenLoaiLK", txtTimKiem.getText(),"TenLoaiLK", txtTimKiem1.getText());
//					} catch (IOException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//					try {
//						LoadDBData2JTable1();
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					txtTrang.setText("1");
//				}
//				if (cboTimKiem.getSelectedIndex() == 3 ||cboTimKiem_1.getSelectedIndex() == 1) {
//					try {
//						listLK = linhKienDao.timKiemLinhKien("TenLoaiLK", txtTimKiem.getText(),"TenLK", txtTimKiem1.getText());
//					} catch (IOException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//					try {
//						LoadDBData2JTable1();
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					txtTrang.setText("1");
//				}
			} else {
				try {
					listLK = linhKienDao.getAllLinhKien();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					LoadDBData2JTable1();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				capNhatSoLuongModel();
				txtTrang.setText("1");
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			try {
				themLinhKienVaoHoaDon();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnXoa)) {
			try {
				xoaLinhKienKhoiHoaDon();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnXemChiTiet)) {
			try {
				xemChiTietLinhKien();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnTaoMoi)) {
			try {
				taoHoaDonMoi();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnSua)) {
			try {
				sua();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (o.equals(btnThanhToan)) {
			try {
				ThanhToan();
				if (!txtTienThoi.getText().equals("") && !txtTenKhachHang.getText().equals("")) {
					txtTienKhachTra.setText(df.format(Double.parseDouble(txtTienKhachTra.getText())));
					xemChiTietHoaDon();
					xoaRong();

				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnTaoMoiKH)) {
			kh = new JpanelKhachHang_ex();
			kh.setVisible(true);
			kh.getBtnXem().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					txtMaKhachHang.setText(kh.getMaKH());
					txtTenKhachHang.setText(kh.getTenKH());
					txtSoDienThoai.setText(kh.getSDT());
					txtDiaChiKH.setText(kh.getDiaChi());
				}
			});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(tableThongTinLK)) {
			// int row = tableThongTinLK.getSelectedRow();
			txtSoLuong.setText("1");
			txtSoLuong.selectAll();
			txtSoLuong.requestFocus();
		} else if (o.equals(tableHoaDon)) {
			int row = tableHoaDon.getSelectedRow();
			txtSoLuong.setText(modelHoaDon.getValueAt(row, 2).toString());
			txtSoLuong.selectAll();
			txtSoLuong.requestFocus();
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

	/**
	 * 
	 */
	public void LoadDBData2JTable1() throws Exception {

		modelThongTinLK.getDataVector().removeAllElements();
		for (LinhKien lk : listLK) {
			ImageIcon icon = new ImageIcon(
					new javax.swing.ImageIcon(lk.getAnh()).getImage().getScaledInstance(90, 60, Image.SCALE_SMOOTH));

			modelThongTinLK.addRow(new Object[] { lk.getMaLK(), lk.getTenLK(), df.format(lk.getGiaBan()),
					lk.getSoLuongTon(), lk.getThuongHieu().getTenTH(), lk.getLoaiLoaiKien().getTenLoaiLK(), icon });
		}
	}

	/**
	 * Hàm đọc dữ liệu từ SQL vào bảng thông tin linh kiện
	 */

	/**
	 * Hàm tạo mã Hóa đơn, mã khách hàng
	 * 
	 * @param loaiMa
	 * @return ma
	 */
	@SuppressWarnings("unlikely-arg-type")
	public String taoMa(String loaiMa) {
		hoaDonDao = new HoaDonDAO();
		khachHangDAO = new KhachHangDAO();
		Random rand = new Random();
		int rd = rand.nextInt(100000 - 10000) + 10000;
		switch (loaiMa) {
		case "HoaDon":
			do {
				rd = rand.nextInt(100000 - 10000) + 10000;
				ma = "HD_" + String.valueOf(rd);
				listHD = hoaDonDao.getAllHoaDon();
			} while (listHD.contains(ma));
			break;
		case "KhachHang":
			do {
				rd = rand.nextInt(100000 - 10000) + 10000;
				ma = "KH_" + String.valueOf(rd);
				listKH = khachHangDAO.getAllKH();
			} while (listKH.contains(ma));
			break;
		}
		return ma;
	}

	/**
	 * Hàm đăng kí sự kiện cho Jbutton, JTextfield
	 */
	private void dangKySuKien() {
		// TODO Auto-generated method stub
		txtTimKiem.addKeyListener(this);
		txtTimKiem1.addKeyListener(this);
		txtTimKiem2.addKeyListener(this);
		txtSoLuong.addKeyListener(this);
		txtSoDienThoai.addKeyListener(this);
		txtTenKhachHang.addKeyListener(this);
		txtTienKhachTra.addKeyListener(this);
		btnXemChiTiet.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTaoMoi.addActionListener(this);
		btnThanhToan.addActionListener(this);
		tableThongTinLK.addMouseListener(this);
		tableHoaDon.addMouseListener(this);
		btnTaoMoiKH.addActionListener(this);
	}

	/**
	 * Hàm tính tổng tiền
	 */
	public void tinhTongTien() {
		double tongTien = 0;
		for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
			tongTien += Double.parseDouble(modelHoaDon.getValueAt(i, 5).toString().replace(",", ""));
		}
		txtTongTien.setText(df.format(tongTien));
	}

	/**
	 * Hàm tính tiền thối
	 */
	public void tinhTienThoi() {
		if (txtTienKhachTra.getText().trim().matches("^\\d+$")) {
			double tkt = Double.parseDouble(txtTienKhachTra.getText().replace(",", ""));
			String tienKT = txtTienKhachTra.getText().replace(",", "");
			double tienKhachTra = Double.parseDouble(tienKT);
			double tongTien = Double.parseDouble(txtTongTien.getText().replace(",", ""));
			if (tkt < tongTien) {
				JOptionPane.showMessageDialog(this, "Tiền khách trả phải >= tổng tiền");
				txtTienKhachTra.selectAll();
				txtTienKhachTra.requestFocus();
				txtTienKhachTra.removeAll();

			} else if (!txtTongTien.getText().equals(""))

			{
				if (tienKhachTra >= tongTien) {
					txtTienThoi.setText(df.format(tienKhachTra - tongTien));
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "Tiền khách trả phải là số và >= tổng tiền");
		}

	}

	/**
	 * Hàm cập nhật lại số lượng linh kiện trong model
	 */
	public void capNhatSoLuongModel() {
		for (int i = 0; i < modelThongTinLK.getRowCount(); i++) {
			for (int j = 0; j < modelHoaDon.getRowCount(); j++) {
				if (modelThongTinLK.getValueAt(i, 0).toString().equals(modelHoaDon.getValueAt(j, 0))) {
					int sl = Integer.parseInt(modelThongTinLK.getValueAt(i, 3).toString())
							- Integer.parseInt(modelHoaDon.getValueAt(j, 2).toString());
					modelThongTinLK.setValueAt(String.valueOf(sl), i, 3);
				}
			}
		}
	}

	/**
	 * Hàm xóa rỗng hóa đơn
	 */
	public void xoaRong() {
		txtMaHoaDon.setText(taoMa("HoaDon"));
		txtMaKhachHang.setText(taoMa("KhachHang"));
		txtTenKhachHang.setText("");
		txtSoDienThoai.setText("");
		txtDiaChiKH.setText("");
		txtNgayLapHD.setText(dt1.format(date));
		txtTongTien.setText("");
		txtTienKhachTra.setText("");
		txtTienThoi.setText("");
		modelHoaDon.getDataVector().removeAllElements();
		modelHoaDon.fireTableDataChanged();
	}

	/**
	 * Hàm sửa số lượng linh kiện trong hóa đơn
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void sua() throws Exception {
		String soLuong = txtSoLuong.getText();
		int row2 = tableHoaDon.getSelectedRow();
		int sl = Integer.parseInt(soLuong);
		int so = Integer.parseInt(txtSoLuong.getText().trim());

		if (row2 != -1) {
			if (soLuong.matches("^\\d+$")) {
				if (so <= 0) {
					JOptionPane.showMessageDialog(this, "Số lượng phải >0");
				} else if (!txtSoLuong.getText().equals("0")) {
					double gia = Double.parseDouble(modelHoaDon.getValueAt(row2, 3).toString().replace(",", ""));
					modelHoaDon.setValueAt(txtSoLuong.getText(), row2, 2);
					modelHoaDon.setValueAt(df.format(sl * gia), row2, 5);
					LoadDBData2JTable1();
					tableThongTinLK.setRowSelectionInterval(0, 0);
					capNhatSoLuongModel();
					tinhTongTien();
				} else {
					modelHoaDon.removeRow(row2);
					LoadDBData2JTable1();
					tableThongTinLK.setRowSelectionInterval(0, 0);
					capNhatSoLuongModel();
					tinhTongTien();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để sửa");
			}
		} else {
			JOptionPane.showMessageDialog(this, "chỉ được nhập số");
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
		}
	}

	/**
	 * Hàm thêm linh kiện vào hóa đơn
	 * 
	 * @throws IOException
	 */
	public void themLinhKienVaoHoaDon() throws IOException {
		int row = tableThongTinLK.getSelectedRow();
		String soLuong = txtSoLuong.getText();
		// double thue = 0;
		if (row != -1) {
			if (soLuong.matches("^\\d+$")) {
				String soLuongTbl = modelThongTinLK.getValueAt(row, 3).toString();
				Double donGia = Double.parseDouble(modelThongTinLK.getValueAt(row, 2).toString().replace(",", ""));
				int so = Integer.parseInt(txtSoLuong.getText().trim());
				if (so <= 0) {
					JOptionPane.showMessageDialog(this, "Số lượng phải >0");
				} else if (txtSoLuong.getText().matches("^\\d+$")) {
					String maLK = modelThongTinLK.getValueAt(row, 0).toString();
					String tenLK = modelThongTinLK.getValueAt(row, 1).toString();
					List<LinhKien> list = linhKienDao.timKiemLinhKien("MaLK", maLK);
					int sl = Integer.parseInt(soLuong);
					int slTbl = Integer.parseInt(soLuongTbl);

					if (sl > slTbl) {
						JOptionPane.showMessageDialog(this, "Không đủ số lượng");
						txtSoLuong.selectAll();
						txtSoLuong.requestFocus();
					} else {
						double thanhTien = sl * donGia;
						int capNhatSoLuong = slTbl - sl;
						int dong = -1;
						for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
							if (modelHoaDon.getValueAt(i, 0).equals(maLK)) {
								dong = i;
							}
						}
						if (dong == -1) {
							modelHoaDon.addRow(
									new Object[] { maLK, tenLK, soLuong, df.format(donGia), list.get(0).getBaoHanh(),
											df.format(thanhTien + (thanhTien * list.get(0).getThue()) / 100),
											df.format(list.get(0).getThue()) });
							modelThongTinLK.setValueAt(capNhatSoLuong, row, 3);

						} else {
							int sl2 = Integer.parseInt(modelHoaDon.getValueAt(dong, 2).toString());
							modelHoaDon.setValueAt(sl + sl2, dong, 2);
							modelHoaDon.setValueAt(df.format((sl + sl2) * donGia), dong, 5);
							modelThongTinLK.setValueAt(capNhatSoLuong, row, 3);
						}
						tinhTongTien();
					}
				} else {
					JOptionPane.showMessageDialog(this, "số lượng phải > 0");
					txtSoLuong.selectAll();
					txtSoLuong.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(this, "số lượng là các chữ số và > 0");
			}
		} else {
			JOptionPane.showMessageDialog(this, "chưa chọn linh kiện");
		}
	}

	/**
	 * Hàm xóa linh kiện khỏi hóa đơn
	 * 
	 * @throws Exception
	 */
	public void xoaLinhKienKhoiHoaDon() throws Exception {
		int row2 = tableHoaDon.getSelectedRow();
		int trang = Integer.parseInt(txtTrang.getText());
		if (row2 != -1) {
			modelHoaDon.removeRow(row2);
			int soDau = 20 * (trang - 1) + 1;
			@SuppressWarnings("unused")
			int soCuoi = soDau + 19;
			LoadDBData2JTable1();
			capNhatSoLuongModel();
			tableThongTinLK.setRowSelectionInterval(0, 0);
			tinhTongTien();
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để xóa");
		}
	}

	/**
	 * Hàm xem chi tiết linh kiện
	 * 
	 * @throws IOException
	 */
	public void xemChiTietLinhKien() throws IOException {
		int row = tableThongTinLK.getSelectedRow();
		if (row != -1) {

			String maLK = modelThongTinLK.getValueAt(row, 0).toString();
			listLK = linhKienDao.timKiemLinhKien("MaLK", maLK);
			String ten = listLK.get(0).getTenLK();
			Double donGia = Double.parseDouble(modelThongTinLK.getValueAt(row, 2).toString().replace(",", ""));
			String moTa = listLK.get(0).getMoTa();
			Date ngayNhap = listLK.get(0).getNgayNhap();
			int slTon = Integer.parseInt(modelThongTinLK.getValueAt(row, 3).toString());
			String baoHanh = listLK.get(0).getBaoHanh();
			String mauSac = listLK.get(0).getMauSac();
			String trongLuong = listLK.get(0).getTrongLuong();
			double gianhap = listLK.get(0).getGiaNhap();
			float thue = listLK.get(0).getThue();
			String thuongHieu = listLK.get(0).getThuongHieu().getTenTH();
			String ncc = listLK.get(0).getNhaCungCap().getTenNCC();
			String loai = listLK.get(0).getLoaiLoaiKien().getTenLoaiLK();
			byte[] anh = listLK.get(0).getAnh();

			NhaCungCap n = new NhaCungCap(ncc);
			LoaiLinhKien l = new LoaiLinhKien(loai);
			ThuongHieu th = new ThuongHieu(thuongHieu);

			LinhKien linhkien = new LinhKien(maLK, ten, donGia, moTa, baoHanh, ngayNhap, slTon, mauSac, trongLuong,
					thue, gianhap, anh);
			linhkien.setThuongHieu(th);
			linhkien.setNhaCungCap(n);
			linhkien.setLoaiLoaiKien(l);

			frm_ChiTietLinhKien lk = new frm_ChiTietLinhKien(linhkien);
			lk.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng cần xem");
		}
	}

	/**
	 * Hàm tạo hóa đơn mới
	 * 
	 * @throws Exception
	 */

	@SuppressWarnings("unlikely-arg-type")
	public void taoHoaDonMoi() throws Exception {
		listHD = hoaDonDao.getAllHoaDon();
		if (!listHD.contains(txtMaHoaDon.getText())) {
			int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn tạo mới hóa đơn không", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				xoaRong();
				LoadDBData2JTable1();
			}
		}
	}

	/**
	 * Hàm thanh toán và lưu hóa đơn
	 * 
	 * @throws Exception
	 */
	public void ThanhToan() throws Exception {
		chiTietHoaDonDAO = new ChiTietHoaDonDAO();
		String maHD = txtMaHoaDon.getText().trim();
		Date ngayLap = Date.valueOf(txtNgayLapHD.getText());
		String maKH = txtMaKhachHang.getText().trim();
		String hoTen = txtTenKhachHang.getText().trim();
		String sdt = txtSoDienThoai.getText().trim();
		String diaChi = txtDiaChiKH.getText().trim();
		int soLuong;
		String maLK;
		KhachHang khachHang = new KhachHang(maKH, hoTen, sdt, diaChi);
		HoaDon hoaDon = new HoaDon(maHD, nhanVien, khachHang, ngayLap);
		hoaDonDao.themHoaDon(hoaDon);
		if (modelHoaDon.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "Hóa đơn trống");

		} else if (txtTienKhachTra.getText().equals(""))
			JOptionPane.showMessageDialog(this, "Nhập Tiền Khách Đưa");
		else if (txtTenKhachHang.getText().equals(""))
			JOptionPane.showMessageDialog(this, "Khách hàng trống");
//		else if (!listKH.equals(maKH)) {
//			khachHangDAO.themKH(khachHang);
//		}
		tinhTienThoi();

		for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
			soLuong = Integer.parseInt(modelHoaDon.getValueAt(i, 2).toString());
			maLK = modelHoaDon.getValueAt(i, 0).toString();
			LinhKien linhKien = new LinhKien(maLK);
			ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, linhKien, soLuong);
			chiTietHoaDonDAO.themChiTietHoaDon(chiTietHoaDon);
			linhKienDao.capNhatSoLuongLinhKien(soLuong, maLK);
		}
	}

	public void xemChiTietHoaDon() {
		khachHangDAO = new KhachHangDAO();
		nhanVienDAO = new NhanVienDAO();
		String maKH = txtMaKhachHang.getText();
		String maNV = nhanVien.getMaNV();
		KhachHang khachHang = khachHangDAO.getKhachHang(maKH);
		NhanVien nhanVien = nhanVienDAO.getNhanVien(maNV);
		Date ngayLapHD = Date.valueOf(txtNgayLapHD.getText());

		frm_ChiTietHoaDon ctHD = new frm_ChiTietHoaDon(
				new HoaDon(txtMaHoaDon.getText().trim() + "-" + txtTienKhachTra.getText().trim() + "-"
						+ txtTienThoi.getText().trim(), nhanVien, khachHang, ngayLapHD));
		ctHD.setVisible(true);
	}

	public void themKHMoin() {
		JpanelKhachHang_ex ex = new JpanelKhachHang_ex();
		ex.setVisible(true);

	}
}
