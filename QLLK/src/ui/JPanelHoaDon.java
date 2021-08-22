package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.DefaultComboBoxModel;

public class JPanelHoaDon extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtTimKiem;
	private DefaultTableModel modelHoaDon;

	private List<HoaDon> listHD;
	@SuppressWarnings("unused")
	private List<KhachHang> listKH;
	private HoaDonDAO hoaDonDAO;
	private KhachHangDAO khachHangDAO;
	private NhanVienDAO nhanVienDAO;
	private JComboBox<String> cboTimKiem;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public JPanelHoaDon() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 143, 1050, 480);
		add(scrollPane);
		String[] header = { "STT", "Mã hóa đơn", "Mã nhân viên", "Tên nhân viên", "Khách hàng", "Tên khách hàng",
				"Ngày lập hóa đơn" };
		modelHoaDon = new DefaultTableModel(header, 0);
		table = new JTable(modelHoaDon) {
			public boolean editCellAt(int row, int column, EventObject e) { // Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};

		JTableHeader header1 = table.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 18));

		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		table.setRowHeight(25);

		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);

		cboTimKiem = new JComboBox<String>();
		cboTimKiem.setBackground(Color.WHITE);
		cboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboTimKiem.setModel(new DefaultComboBoxModel<String>(new String[] { "Mã HD", "Tên KH", "Tên NV" }));
		cboTimKiem.setSelectedIndex(1);
		cboTimKiem.setBounds(54, 88, 200, 30);
		add(cboTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setBounds(301, 88, 803, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		txtTimKiem.addKeyListener(this);
		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setBackground(new Color(23, 70, 138));
		btnXemChiTiet.setForeground(Color.WHITE);
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemChiTiet.setBounds(960, 678, 144, 39);
		add(btnXemChiTiet);

		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.setBackground(new Color(23, 70, 138));
		pnlHoaDon.setBounds(0, 0, 1300, 59);
		add(pnlHoaDon);
		pnlHoaDon.setLayout(null);

		JLabel lblHan = new JLabel("Hóa đơn");
		lblHan.setHorizontalAlignment(SwingConstants.CENTER);
		lblHan.setBounds(0, 0, 1311, 59);
		pnlHoaDon.add(lblHan);
		lblHan.setForeground(Color.WHITE);
		lblHan.setFont(new Font("Tahoma", Font.BOLD, 25));

		hoaDonDAO = new HoaDonDAO();
		listHD = hoaDonDAO.getAllHoaDon();
		docDuLieuDatabaseVaoTableHoaDon();

		btnXemChiTiet.addActionListener(this);

	}

	

	/**
	 * Hàm xem chi tiết linh kiện
	 */
	public void xemChiTietLinhKien() {
		int row = table.getSelectedRow();
		listKH = new ArrayList<KhachHang>();
		khachHangDAO = new KhachHangDAO();
		nhanVienDAO = new NhanVienDAO();
		if (row != -1) {
			String maHD = modelHoaDon.getValueAt(row, 1).toString().trim();
			String maNV = modelHoaDon.getValueAt(row, 2).toString().trim();
			String maKH = modelHoaDon.getValueAt(row, 4).toString().trim();

			KhachHang khachHang = khachHangDAO.getKhachHang(maKH);
			NhanVien nhanVien = nhanVienDAO.getNhanVien(maNV);
			Date ngayLapHD = Date.valueOf(modelHoaDon.getValueAt(row, 6).toString());
			frm_ChiTietHoaDon ctHD = new frm_ChiTietHoaDon(new HoaDon(maHD, nhanVien, khachHang, ngayLapHD));
			ctHD.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng cần xem");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		xemChiTietLinhKien();
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

					listHD = hoaDonDAO.search("MaHD", txtTimKiem.getText());

					try {
						docDuLieuDatabaseVaoTableHoaDon();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (cboTimKiem.getSelectedIndex() == 1) {

					listHD = hoaDonDAO.search("TenKH", txtTimKiem.getText());

					try {
						docDuLieuDatabaseVaoTableHoaDon();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (cboTimKiem.getSelectedIndex() == 2) {

					listHD = hoaDonDAO.search("TenNV", txtTimKiem.getText());

					try {
						docDuLieuDatabaseVaoTableHoaDon();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
			else {
			listHD=hoaDonDAO.getAllHoaDon();
					docDuLieuDatabaseVaoTableHoaDon();
			
			}
		} 
	}
	public void docDuLieuDatabaseVaoTableHoaDon() {
		modelHoaDon.getDataVector().removeAllElements();
		int dem = 1;
		for (HoaDon hd : listHD) {
			modelHoaDon.addRow(new Object[] { dem, hd.getMaHD(), hd.getNhanVien().getMaNV(), hd.getNhanVien().getTenNV(),
							hd.getKhachHang().getMaKH(), hd.getKhachHang().getTenKH(), hd.getNgayLapHD() });
			dem++;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
