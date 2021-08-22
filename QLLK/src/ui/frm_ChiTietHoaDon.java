package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.ChiTietHoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import ui.GenerateBillPdf;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.EventObject;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class frm_ChiTietHoaDon extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaHD;
	private JTextField txtNgayLapHD;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSdt;
	private JTextField txtDiaChi;
	private JTextField txtTongTien;
	private DefaultTableModel modelChiTietHD;
	private JTable tableChiTietHD;
	private JButton btnThoat;
	@SuppressWarnings("unused")
	private HoaDon hoaDon;
	private List<ChiTietHoaDon> listCTHD;
	private ChiTietHoaDonDAO chiTietHoaDonDAO;
	private double TongTien;
	private DecimalFormat df;
	private JButton btnIn;
	private String txttienkhachtra;
	private String txttienthoi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new frm_ChiTietHoaDon(new HoaDon()).setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public frm_ChiTietHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
		df = new DecimalFormat("#,##0.00");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 936, 779);
		setLocationRelativeTo(null);
		setTitle("Chi tiết hóa đơn");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel pnlChiTiet = new JPanel();
		pnlChiTiet.setBackground(new Color(23,70,138));
		pnlChiTiet.setBounds(0, 0, 918, 59);
		contentPane.add(pnlChiTiet);
		pnlChiTiet.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hóa đơn");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(0, 0, 918, 59);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pnlChiTiet.add(lblNewLabel);

		JLabel lblMaHD = new JLabel("Mã hóa đơn:");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaHD.setBounds(10, 72, 131, 30);
		contentPane.add(lblMaHD);

		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMaHD.setBounds(134, 72, 223, 30);
		contentPane.add(txtMaHD);
		txtMaHD.setColumns(10);
		txtMaHD.setEditable(false);
		String[] tach=hoaDon.getMaHD().split("-");
		txtMaHD.setText(tach[0].trim());

		JLabel lblNgayLapHD = new JLabel("Ngày lập hóa đơn:");
		lblNgayLapHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgayLapHD.setBounds(429, 72, 183, 30);
		contentPane.add(lblNgayLapHD);

		txtNgayLapHD = new JTextField();
		txtNgayLapHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNgayLapHD.setColumns(10);
		txtNgayLapHD.setBounds(579, 72, 268, 30);
		contentPane.add(txtNgayLapHD);
		txtNgayLapHD.setEditable(false);
		txtNgayLapHD.setText(hoaDon.getNgayLapHD().toString());
		

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaNV.setBounds(10, 115, 161, 30);
		contentPane.add(lblMaNV);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(134, 115, 223, 30);
		contentPane.add(txtMaNV);
		txtMaNV.setEditable(false);
		txtMaNV.setText(hoaDon.getNhanVien().getMaNV());

		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenNV.setBounds(429, 115, 161, 30);
		contentPane.add(lblTenNV);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(579, 115, 305, 30);
		contentPane.add(txtTenNV);
		txtTenNV.setEditable(false);
		txtTenNV.setText(hoaDon.getNhanVien().getTenNV());

		JLabel lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaKH.setBounds(10, 158, 161, 30);
		contentPane.add(lblMaKH);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(134, 158, 223, 30);
		contentPane.add(txtMaKH);
		txtMaKH.setEditable(false);
		txtMaKH.setText(hoaDon.getKhachHang().getMaKH());

		JLabel lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenKH.setBounds(429, 158, 183, 30);
		contentPane.add(lblTenKH);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(579, 158, 305, 30);
		contentPane.add(txtTenKH);
		txtTenKH.setEditable(false);
		txtTenKH.setText(hoaDon.getKhachHang().getTenKH());

		JLabel lblSdt = new JLabel("Số điện thoại:");
		lblSdt.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSdt.setBounds(10, 201, 161, 30);
		contentPane.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtSdt.setColumns(10);
		txtSdt.setBounds(134, 201, 223, 30);
		contentPane.add(txtSdt);
		txtSdt.setEditable(false);
		txtSdt.setText(hoaDon.getKhachHang().getsDT());

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiaChi.setBounds(10, 244, 84, 30);
		contentPane.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(85, 245, 799, 30);
		contentPane.add(txtDiaChi);
		txtDiaChi.setEditable(false);
		txtDiaChi.setText(hoaDon.getKhachHang().getDiaChi());

		String[] header = { "Mã linh kiện", "Tên linh kiện", "Số lượng", "Đơn giá", "Bảo hành", "Thành tiền","Thuế"};
		modelChiTietHD = new DefaultTableModel(header, 0);
		tableChiTietHD = new JTable(modelChiTietHD) {
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, EventObject e) { // Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		tableChiTietHD.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableChiTietHD.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableChiTietHD.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableChiTietHD.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableChiTietHD.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableChiTietHD.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableChiTietHD.getColumnModel().getColumn(6).setPreferredWidth(80);
		JTableHeader header1 = tableChiTietHD.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		tableChiTietHD.setFont(new Font("Tahoma", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(tableChiTietHD);
		scrollPane.setBounds(10, 287, 896, 364);
		scrollPane = new JScrollPane(tableChiTietHD);
		scrollPane.setBounds(12, 287, 894, 360);
		contentPane.add(scrollPane);
		tableChiTietHD.setRowHeight(25);
		
		docDuLieuDatabaseVaoTable();
		
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongTien.setBounds(10, 677, 113, 30);
		contentPane.add(lblTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(124, 677, 161, 30);
		contentPane.add(txtTongTien);
		txtTongTien.setEditable(false);
		
//		Tính tổng tiền
		chiTietHoaDonDAO = new ChiTietHoaDonDAO();
		listCTHD = chiTietHoaDonDAO.getAllChiTietHoaDon(txtMaHD.getText().trim());
		TongTien = hoaDon.tinhTongTien(listCTHD);
		txtTongTien.setText(df.format(TongTien));
		
		btnThoat = new JButton("Thoát");
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setBackground(new Color(23,70,138));
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThoat.setBounds(781, 683, 125, 30);
		contentPane.add(btnThoat);
		
		btnIn = new JButton("In hóa đơn");
		btnIn.setForeground(Color.WHITE);
		btnIn.setBackground(new Color(23,70,138));
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIn.setBounds(600, 683, 150, 30);
		contentPane.add(btnIn);
		
		try {
			txttienkhachtra=tach[1].trim();
			txttienthoi=tach[2].trim();
		} catch (Exception e) {
			// TODO: handle exception
		}
		btnThoat.addActionListener(this);
		btnIn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if(o.equals(btnIn)) {
			try {
			new GenerateBillPdf(txtMaHD.getText().trim()+"-"+txttienkhachtra+"-"+txttienthoi);
			String path="D:/hoadon/" + txtMaHD.getText().trim() + "_" + txtMaKH.getText().trim() + ".pdf";
			File file=new File(path);
			if(file.exists()) {
				Process process=Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+path);
				process.waitFor();
			}
			else
			JOptionPane.showMessageDialog(this, "File không tồn tại!");
			
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}	
		this.setVisible(false);
	}
	public void docDuLieuDatabaseVaoTable() {
//		TongTien = 0;
		chiTietHoaDonDAO = new ChiTietHoaDonDAO();
		listCTHD = chiTietHoaDonDAO.getChiTietHoaDon(txtMaHD.getText().trim());
		
		for (ChiTietHoaDon ct : listCTHD) {
			modelChiTietHD.addRow(new Object[] { ct.getLinhKien().getMaLK(), ct.getLinhKien().getTenLK(), ct.getSoLuong(),
					df.format(ct.getLinhKien().getGiaBan()), ct.getLinhKien().getBaoHanh(), df.format(ct.tinhThanhTien(ct.getSoLuong(), ct.getLinhKien())),
					(df.format(ct.getLinhKien().getThue()))
					});
//			TongTien += lk.getDonGiaLK()*lk.getSlTon();
		}
	}

}
