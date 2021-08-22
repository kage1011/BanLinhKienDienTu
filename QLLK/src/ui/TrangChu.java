package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.accessibility.Accessible;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.NhanVienDAO;
import entity.NhanVien;
import javax.swing.JTextField;

public class TrangChu extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTrangChu;
	private Container pnlMenu;
	private JPanel pnlTrangChu;
	private JLabel lblCuaHang;
	private JPanel pnlCuaHang;
	private JPanel pnlHoaDon;
	private JLabel lblHoaDon;
	private JPanel pnlLinhKien;
	private JLabel lblLinhKien;
	private JPanel pnlKhachHang;
	private JLabel lblKhachHang;
	private JPanel pnlNhanVien;
	private JLabel lblNhanVien;
	private JPanel pnlDangXuat;
	private JPanel contentPane;
	private JPanel pnlMHChinh;
	private JLabel lblDangXuat;
	private JPanel pnlNhaCungCap;
	private JLabel lblNhaCungCap;
	private JPanel pnlTimKiem;
	private JLabel lblTimKiem;
	private JPanel pnlBanHang;
	private JLabel lblBanHang;
	private JPanel pnlThongKe;
	private JLabel lblThongKe;
	private NhanVien nhanVien;
	private JLabel lblImage;
	private JPanel panel;
	private JPanel pnlNV;
	private JLabel lblMaNV;
	private JLabel lblTenNV;
	private NhanVienDAO nhanVienDao;
	private JPanel pnlgio;
	private JPanel pnlngay;
	private JLabel lblGio_1;
	private JLabel lblNgay_1;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */

	public TrangChu(NhanVien nhanVien) throws Exception {
		this.nhanVien = nhanVien;
		setTitle("Quản Lý mua bán linh kiện");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(23,70,138));
		pnlMenu.setBounds(0, 0, 209, 740);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		pnlCuaHang = new JPanel();
		pnlCuaHang.setBackground(new Color(23,70,138));
		pnlCuaHang.setBounds(0, 0, 208, 121);
		pnlMenu.add(pnlCuaHang);
		pnlCuaHang.setLayout(null);

		lblCuaHang = new JLabel("");
		lblCuaHang.setBounds(0, 0, 207, 122);
		pnlCuaHang.add(lblCuaHang);
		lblCuaHang.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dreamteam.png")).getImage().getScaledInstance(240,170, Image.SCALE_SMOOTH)));
		lblCuaHang.setBackground(Color.BLACK);
		lblCuaHang.setForeground(new Color(23,70,138));
		lblCuaHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuaHang.setFont(new Font("Tahoma", Font.BOLD, 18));

		pnlTrangChu = new JPanel();
		pnlTrangChu.setBackground(new Color(23,70,138));
		pnlTrangChu.setBounds(0, 123, 208, 53);
		pnlMenu.add(pnlTrangChu);
		pnlTrangChu.setLayout(null);

		lblTrangChu = new JLabel("Trang chủ");
		lblTrangChu.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_home_page_24px_1.png")));
		lblTrangChu.setBounds(12, 0, 196, 53);
		pnlTrangChu.add(lblTrangChu);
		lblTrangChu.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrangChu.setForeground(Color.WHITE);
		lblTrangChu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrangChu.setBackground(Color.BLACK);

		pnlHoaDon = new JPanel();
		pnlHoaDon.setLayout(null);
		pnlHoaDon.setBackground(new Color(23,70,138));
		pnlHoaDon.setBounds(0, 437, 208, 53);
		pnlMenu.add(pnlHoaDon);

		lblHoaDon = new JLabel("Hóa đơn");
		lblHoaDon.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_details_24px.png")));
		lblHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
		lblHoaDon.setForeground(Color.WHITE);
		lblHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHoaDon.setBackground(Color.BLACK);
		lblHoaDon.setBounds(12, 0, 196, 53);
		pnlHoaDon.add(lblHoaDon);

		pnlLinhKien = new JPanel();
		pnlLinhKien.setLayout(null);
		pnlLinhKien.setBackground(new Color(23,70,138));
		pnlLinhKien.setBounds(0, 281, 208, 53);
		pnlMenu.add(pnlLinhKien);

		lblLinhKien = new JLabel("Linh kiện");
		lblLinhKien.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_computer_32px.png")));
		lblLinhKien.setHorizontalAlignment(SwingConstants.LEFT);
		lblLinhKien.setForeground(Color.WHITE);
		lblLinhKien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLinhKien.setBackground(Color.BLACK);
		lblLinhKien.setBounds(12, 0, 196, 53);
		pnlLinhKien.add(lblLinhKien);

		pnlKhachHang = new JPanel();
		pnlKhachHang.setLayout(null);
		pnlKhachHang.setBackground(new Color(23,70,138));
		pnlKhachHang.setBounds(0, 228, 208, 53);
		pnlMenu.add(pnlKhachHang);

		lblKhachHang = new JLabel("Khách hàng");
		lblKhachHang.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_customer_24px.png")));
		lblKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblKhachHang.setForeground(Color.WHITE);
		lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKhachHang.setBackground(Color.BLACK);
		lblKhachHang.setBounds(12, 0, 196, 53);
		pnlKhachHang.add(lblKhachHang);

		pnlNhanVien = new JPanel();
		pnlNhanVien.setLayout(null);
		pnlNhanVien.setBackground(new Color(23,70,138));
		pnlNhanVien.setBounds(0, 333, 208, 53);
		pnlMenu.add(pnlNhanVien);

		lblNhanVien = new JLabel("Nhân viên");
		lblNhanVien.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_technical_support_24px.png")));
		lblNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhanVien.setForeground(Color.WHITE);
		lblNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNhanVien.setBackground(Color.BLACK);
		lblNhanVien.setBounds(12, 0, 196, 53);
		pnlNhanVien.add(lblNhanVien);

		pnlDangXuat = new JPanel();
		pnlDangXuat.setLayout(null);
		pnlDangXuat.setBackground(new Color(23,70,138));
		pnlDangXuat.setBounds(0, 541, 208, 53);
		pnlMenu.add(pnlDangXuat);

		pnlMHChinh = new JPanel();
		pnlMHChinh.setBackground(Color.white);
		pnlMHChinh.setBounds(209, 0, 1151, 740);
		contentPane.add(pnlMHChinh);

		nhanVienDao=new NhanVienDAO();
		nhanVienDao.getAllNV(nhanVien.getMaNV());


		pnlNV=new JPanel();
		pnlNV.setLayout(null);
		pnlNV.setBackground(new Color(23,70,138));
		pnlNV.setBounds(0, 593, 198, 84);
		pnlMenu.add(pnlNV);

		lblMaNV=new JLabel(nhanVien.getMaNV());
		lblMaNV.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_male_user_32px_3.png")));
		lblMaNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaNV.setForeground(Color.WHITE);
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaNV.setBounds(5, 0, 196, 53);
		pnlNV.add(lblMaNV);

		lblTenNV=new JLabel();
		lblTenNV.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_male_user_32px_3.png")));
		lblTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNV.setForeground(Color.WHITE);
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenNV.setBounds(5, 35, 196, 53);
		pnlNV.add(lblTenNV);

		nhanVienDao =new NhanVienDAO();
		nhanVienDao.getAllNV();
		NhanVien nv = nhanVienDao.getNhanVien(nhanVien.getMaNV());
		lblTenNV.setText(nv.getTenNV());


		ChuyenManHinh chuyen = new ChuyenManHinh(pnlMHChinh,nhanVien);
		try {
			chuyen.setManHinh(pnlTrangChu,lblTrangChu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pnlMHChinh.setLayout(null);


		panel = new JPanel();
		panel.setBounds(0, 0, 1151, 740);
		pnlMHChinh.add(panel);
		panel.setLayout(null);

		JLabel lblHinhChinh = new JLabel("");
		lblHinhChinh.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/637390.jpg")).getImage().getScaledInstance(1200,751, Image.SCALE_SMOOTH)));	
		lblHinhChinh.setBounds(0, 0, 1210, 751);
		panel.add(lblHinhChinh);




		pnlNhaCungCap = new JPanel();
		pnlNhaCungCap.setLayout(null);
		pnlNhaCungCap.setBackground(new Color(23,70,138));
		pnlNhaCungCap.setBounds(0, 386, 208, 53);
		pnlMenu.add(pnlNhaCungCap);

		lblNhaCungCap = new JLabel("Nhà Cung Cấp");
		lblNhaCungCap.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_management_24px.png")));
		lblNhaCungCap.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhaCungCap.setForeground(Color.WHITE);
		lblNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNhaCungCap.setBackground(Color.BLACK);
		lblNhaCungCap.setBounds(12, 0, 196, 53);
		pnlNhaCungCap.add(lblNhaCungCap);

		pnlTimKiem = new JPanel();
		pnlTimKiem.setLayout(null);
		pnlTimKiem.setBackground(new Color(23,70,138));
		pnlTimKiem.setBounds(10, 463, 208, 53);


		lblTimKiem = new JLabel("Tìm Kiếm");
		lblTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimKiem.setForeground(Color.WHITE);
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTimKiem.setBackground(Color.BLACK);
		lblTimKiem.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_search_database_24px.png")));
		lblTimKiem.setBounds(12, 0, 196, 53);

		pnlThongKe = new JPanel();
		pnlThongKe.setLayout(null);
		pnlThongKe.setBackground(new Color(23,70,138));
		pnlThongKe.setBounds(0, 488, 208, 53);
		pnlMenu.add(pnlThongKe);


		List<DanhMuc> listDanhMuc = new ArrayList<>();
		listDanhMuc.add(new DanhMuc("TrangChu", pnlTrangChu, lblTrangChu));
		listDanhMuc.add(new DanhMuc("HoaDon", pnlHoaDon, lblHoaDon));
		listDanhMuc.add(new DanhMuc("KhachHang", pnlKhachHang, lblKhachHang));
		listDanhMuc.add(new DanhMuc("LinhKien", pnlLinhKien, lblLinhKien));
		listDanhMuc.add(new DanhMuc("NhanVien", pnlNhanVien, lblNhanVien));
		listDanhMuc.add(new DanhMuc("NhaCungCap", pnlNhaCungCap, lblNhaCungCap));
		listDanhMuc.add(new DanhMuc("TimKiem", pnlTimKiem, lblTimKiem));

		lblDangXuat = new JLabel("Đăng xuất");
		lblDangXuat.setBounds(0, 0, 196, 53);
		pnlDangXuat.add(lblDangXuat);
		lblDangXuat.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_shutdown_50px.png")));
		lblDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
		lblDangXuat.setForeground(Color.WHITE);
		lblDangXuat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDangXuat.setBackground(Color.BLACK);
		listDanhMuc.add(new DanhMuc("DangXuat", pnlDangXuat, lblDangXuat));

		lblThongKe = new JLabel("Thống Kê");
		lblThongKe.setBounds(10, 0, 196, 53);
		pnlThongKe.add(lblThongKe);
		lblThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		lblThongKe.setForeground(Color.WHITE);
		lblThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThongKe.setBackground(Color.BLACK);
		lblThongKe.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_pie_chart_24px.png")));
		listDanhMuc.add(new DanhMuc("ThongKe", pnlThongKe, lblThongKe));

		pnlBanHang = new JPanel();
		pnlBanHang.setBounds(0, 177, 208, 53);
		pnlMenu.add(pnlBanHang);
		pnlBanHang.setLayout(null);
		pnlBanHang.setBackground(new Color(23,70,138));

		lblBanHang = new JLabel("Bán Linh Kiện");
		lblBanHang.setBounds(10, 0, 196, 53);
		pnlBanHang.add(lblBanHang);
		lblBanHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblBanHang.setForeground(Color.WHITE);
		lblBanHang.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBanHang.setBackground(Color.BLACK);
		lblBanHang.setIcon(new ImageIcon(TrangChu.class.getResource("/images/icons8_buy_24px.png")));
		listDanhMuc.add(new DanhMuc("BanLinhKien", pnlBanHang, lblBanHang));

		lblDangXuat.addMouseListener(this);
		chuyen.setSuKien(listDanhMuc);
		DongHo();
	}

	public void DongHo() {
		pnlgio = new JPanel();
		pnlgio.setBounds(0, 680, 208, 42);
		pnlgio.setLayout(null);
		pnlgio.setBackground(new Color(23,70,138));
		pnlMenu.add(pnlgio);
		
		lblGio_1 = new JLabel("Giờ: ");
		lblGio_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGio_1.setForeground(Color.WHITE);
		lblGio_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGio_1.setBounds(10, 20, 148, 30);
		pnlgio.add(lblGio_1);
		
		pnlngay = new JPanel();
		pnlngay.setBounds(0, 713, 208, 34);
		pnlngay.setLayout(null);
		pnlngay.setBackground(new Color(23,70,138));
		pnlMenu.add(pnlngay);
		
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
		
		lblNgay_1 = new JLabel("Ngày: ");
		lblNgay_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgay_1.setForeground(Color.WHITE);
		lblNgay_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgay_1.setBounds(10, 0, 151, 33);
		lblNgay_1.setText(dt1.format(date));
		pnlngay.add(lblNgay_1);
		
		new Thread() {
			public void run() {
				while (true) {
					Calendar ca = new GregorianCalendar();
					int gio = ca.get(Calendar.HOUR_OF_DAY);
					int phut = ca.get(Calendar.MINUTE);
					int giay = ca.get(Calendar.SECOND);
					String dongHo = gio + " : " + phut + " : " + giay;
					lblGio_1.setText(dongHo);
				}
			}

		}.start();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(lblDangXuat)) {
			int tl = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Cảnh báo",JOptionPane.YES_NO_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				this.setVisible(false);

				new Form_DangNhap().setVisible(true);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
