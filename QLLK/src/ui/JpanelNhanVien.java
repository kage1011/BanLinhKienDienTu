package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import conection.Conection;
import dao.NhanVienDAO;
import entity.NhanVien;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class JpanelNhanVien extends JPanel implements ActionListener, MouseListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV;

	private JTextField txtMatKhau;
	private JTable table;
	private DefaultTableModel modelNhanVien;
	private NhanVienDAO nhanVienDAO;
	private JComboBox<String> comboBox;
	private JComboBox<String> cboGioiTinh;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton btnXoaRong;
	private JTextField txtDiaChi;
	private JTextField txtTenNV;
	private JTextField txtSDT;
	private List<NhanVien> listNV;
	private JComboBox<String> cboTimKiem;
	private JTextField txtTimKiem;
	private String ma;
	private SqlDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public JpanelNhanVien() throws Exception {

		setBackground(Color.LIGHT_GRAY);

		try {
			Conection.getKetNoi();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		nhanVienDAO = new NhanVienDAO();
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(23, 70, 138));
		panel.setBounds(0, 0, 1300, 59);
		add(panel);
		panel.setLayout(null);
		JLabel lblQunLLinh = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblQunLLinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLLinh.setBounds(0, 0, 1311, 59);
		panel.add(lblQunLLinh);
		lblQunLLinh.setForeground(Color.WHITE);
		lblQunLLinh.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 143, 1050, 318);
		add(scrollPane);
		String[] colHeader = { "Mã nhân viên", "Họ tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại",
				"Địa chỉ" };
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		table = new JTable(modelNhanVien);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		scrollPane.setBorder(
				new TitledBorder(null, "Danh Sách Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JTableHeader header1 = table.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(25);

		cboTimKiem = new JComboBox<String>();
		cboTimKiem.setBackground(Color.WHITE);
		cboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboTimKiem.setModel(new DefaultComboBoxModel<String>(new String[] { "Mã nhân viên", "Tên nhân viên", "SDT" }));
		cboTimKiem.setSelectedIndex(1);
		cboTimKiem.setBounds(54, 88, 200, 30);
		add(cboTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setBounds(297, 88, 803, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(212, 674, 110, 50);
		btnThem.setBackground(new Color(23, 70, 138));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(383, 674, 110, 50);
		btnXoa.setBackground(new Color(23, 70, 138));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnXoa);

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setBounds(552, 674, 150, 50);
		btnXoaRong.setBackground(new Color(23, 70, 138));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnXoaRong);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(755, 674, 110, 50);
		btnSua.setBackground(new Color(23, 70, 138));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(btnSua);

		JLabel lblMaNV = new JLabel("Mã NV:");
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblMaNV.setBounds(54, 497, 76, 14);
		add(lblMaNV);

		JLabel lblTenNV = new JLabel("Tên NV:");
		lblTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTenNV.setBounds(654, 497, 76, 14);
		add(lblTenNV);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDiaChi.setBounds(54, 543, 73, 23);
		add(lblDiaChi);

		JLabel lblMatKhau = new JLabel("MK:");
		lblMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblMatKhau.setBounds(54, 594, 46, 14);
		add(lblMatKhau);

		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblSDT.setBounds(654, 594, 46, 14);
		add(lblSDT);

		JLabel lblNgaySinh = new JLabel("Ngày sinh :");
		lblNgaySinh.setBounds(654, 543, 95, 23);
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		add(lblNgaySinh);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaNV.setBounds(166, 491, 312, 30);
		add(txtMaNV);
		txtMaNV.setColumns(10);
		txtMaNV.setText(taoMa());
		txtMaNV.setEditable(false);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDiaChi.setBounds(166, 540, 312, 30);
		add(txtDiaChi);
		txtDiaChi.setColumns(10);

		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMatKhau.setBounds(166, 587, 312, 30);
		add(txtMatKhau);
		txtMatKhau.setColumns(10);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(793, 491, 312, 30);
		add(txtTenNV);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtSDT.setColumns(10);
		txtSDT.setBounds(793, 587, 312, 30);
		add(txtSDT);

		model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.day", "Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setEditable(true);
		datePicker.setTextEditable(true);
		datePicker.setBackground(Color.WHITE);
		datePicker.setShowYearButtons(true);
		datePicker.setSize(312, 30);
		datePicker.setLocation(793, 540);
		this.add(datePicker);

//		textNgay = new JTextField();
//		textNgay.setFont(new Font("Times New Roman", Font.PLAIN, 17));
//		textNgay.setBounds(793, 540, 312, 30);
//		add(textNgay);
//		textNgay.setColumns(10);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblGioiTinh.setBounds(54, 640, 80, 14);
		add(lblGioiTinh);

		cboGioiTinh = new JComboBox<String>();
		cboGioiTinh.addItem("Nam");
		cboGioiTinh.addItem("Nữ");
		cboGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cboGioiTinh.setBounds(166, 633, 101, 30);
		add(cboGioiTinh);

		JLabel lblNewLabel = new JLabel("Quyền truy cập: ");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(654, 633, 200, 30);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		add(lblNewLabel);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(793, 633, 110, 30);
		comboBox.addItem("User");
		comboBox.addItem("Admin");
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		add(comboBox);

		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(330);

		LoadDBData2JTable1();

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		txtTimKiem.addKeyListener(this);
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int row1 = table.getSelectedRow();

				if (row1 == -1) {
					JOptionPane.showMessageDialog(null, "chọn dòng muốn sửa");
				} else {
					if (validData()) {
						String ma = txtMaNV.getText().trim();
						String ten = txtTenNV.getText().trim();
						String quyen = (String) (comboBox.getSelectedItem());
						Date ngay = Date.valueOf(datePicker.getJFormattedTextField().getText());
						String dc = txtDiaChi.getText().trim();
						String sdt = txtSDT.getText().trim();
						String mk = txtMatKhau.getText().trim();
						String gioitinh = (String) (cboGioiTinh.getSelectedItem());
						NhanVien nv = new NhanVien(ma, ten, gioitinh, ngay, dc, sdt, mk, quyen);
						int t1 = JOptionPane.showConfirmDialog(null, "bạn có muốn sửa ?", "Sửa",
								JOptionPane.YES_NO_OPTION);
						if (t1 == JOptionPane.YES_OPTION) {
							if (nhanVienDAO.updateNV(ma, nv)) {
								modelNhanVien.setValueAt(ma, row1, 0);
								modelNhanVien.setValueAt(ten, row1, 1);
								modelNhanVien.setValueAt(gioitinh, row1, 2);
								modelNhanVien.setValueAt(ngay, row1, 3);
								modelNhanVien.setValueAt(sdt, row1, 4);
								modelNhanVien.setValueAt(dc, row1, 5);
								modelNhanVien.setValueAt(mk, row1, 6);
								modelNhanVien.setValueAt(quyen, row1, 7);

							}
						}

					}
				}
			}
		});
		table.addMouseListener(this);

	}

	public class DateLabelFormatter extends AbstractFormatter {

		/**
		 * 
		 */
		private static final long serialVersionUID = -566062085698006350L;
		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;

				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}

	private boolean validData() {
		String hoTen = txtTenNV.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String mk = txtMatKhau.getText().trim();
		// String ngaySinh = textNgay.getText().trim();

		if (hoTen.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Họ tên nhân viên trống!");
			txtTenNV.selectAll();
			txtTenNV.requestFocus();
			return false;
		} else {

			if ((hoTen.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Tên nhân viên không hợp lệ");
				txtTenNV.requestFocus();
				txtTenNV.selectAll();
				return false;
			}
		}
		if (mk.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu trống !");
			txtMatKhau.selectAll();
			txtMatKhau.requestFocus();
			return false;
		} else {

			if ((mk.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Mật khẩu hồm chữ, số, kí tự đặc biệt");
				txtMatKhau.requestFocus();
				txtMatKhau.selectAll();
				return false;
			}
		}
//		if (ngaySinh.trim().equals("")) {
//			JOptionPane.showMessageDialog(this, "Ngày sinh trống !");
//			textNgay.selectAll();
//			textNgay.requestFocus();
//			return false;
//		} else {
//
//			if (!(ngaySinh.matches("[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])"))) {
//				JOptionPane.showMessageDialog(this, " ngày sinh có dạng YYYY-MM-dd");
//				textNgay.requestFocus();
//				textNgay.selectAll();
//				return false;
//			}
//		}
		if (sdt.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng trống!");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		} else {

			if (!(sdt.matches("^[0-9]{10}$"))) {
				JOptionPane.showMessageDialog(this, "Số điện thoại nhân viên gồm 10 số");
				txtSDT.requestFocus();
				txtSDT.selectAll();
				return false;
			}
		}

		if (diaChi.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được trống !");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		} else {

			if (diaChi.matches(
					"^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]")) {
				JOptionPane.showMessageDialog(this, "Địa chỉ gồm chữ cái, số, kí tự đặc biệt");
				txtDiaChi.requestFocus();
				txtDiaChi.selectAll();
				return false;

			}
		}

		return true;
	}

	private void xoaRongTextfields() {
		txtMaNV.setText(taoMa());
		txtMatKhau.setText("");
		txtDiaChi.setText("");
		// textNgay.setText("");
		datePicker.getJFormattedTextField().setText("");
		txtTenNV.setText("");
		txtSDT.setText("");
		cboGioiTinh.setSelectedIndex(0);
		comboBox.setSelectedIndex(0);
		txtMaNV.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		NhanVien nhanVien = nhanVienDAO.getNhanVien(table.getValueAt(row, 0).toString().trim());
		txtMaNV.setText(table.getValueAt(row, 0).toString().trim());
		txtTenNV.setText(table.getValueAt(row, 1).toString());
		cboGioiTinh.setSelectedItem(table.getValueAt(row, 2));
		datePicker.getJFormattedTextField().setText(table.getValueAt(row, 3).toString());
		txtSDT.setText(table.getValueAt(row, 4).toString());
		txtDiaChi.setText(table.getValueAt(row, 5).toString().trim());
		txtMatKhau.setText(nhanVien.getMatKhau().trim());
		comboBox.setSelectedItem(nhanVien.getQuyen().trim());

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
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (validData()) {
				String ma = txtMaNV.getText().trim();
				String gt = txtTenNV.getText().trim();
				String quyen = (String) (comboBox.getSelectedItem());
				// Date ngay = Date.valueOf(textNgay.getText());
				Date ngay = Date.valueOf(datePicker.getJFormattedTextField().getText());
				String dc = txtDiaChi.getText().trim();
				String sdt = txtSDT.getText().trim();
				String mk = txtMatKhau.getText().trim();
				String gioitinh = (String) (cboGioiTinh.getSelectedItem());
				NhanVien nv = new NhanVien(ma, gt, gioitinh, ngay, dc, sdt, mk, quyen);
				try {
					if (nhanVienDAO.themNV(nv)) {
						listNV = nhanVienDAO.getAllNV();
						try {
							LoadDBData2JTable1();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Trùng Mã");
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		} else if (o.equals(btnXoaRong)) {
			xoaRongTextfields();
		} else if (o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Chọn dòng cần xóa");
				return;
			} else {
				int t = JOptionPane.showConfirmDialog(null, "bạn có muốn xóa ?", "Xóa", JOptionPane.YES_NO_OPTION);
				if (t == JOptionPane.YES_OPTION) {
					List<NhanVien> dskh = nhanVienDAO.getAllNV();
					if (row >= 0 && row < dskh.size()) {
						NhanVien kh = dskh.get(row);
						if (!kh.getQuyen().trim().equals("Admin")) {

							try {
								if (nhanVienDAO.xoaNV(kh)) {
									listNV = nhanVienDAO.getAllNV();
									LoadDBData2JTable1();
									table.clearSelection();
									xoaRongTextfields();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(this, "Không thể xóa người quản lý");
						}
					}

				}
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		Object o = arg0.getSource();
		if (o.equals(txtTimKiem)) {
			if (!txtTimKiem.getText().equals("")) {
				if (cboTimKiem.getSelectedIndex() == 0) {
					listNV = nhanVienDAO.timKiemNV("MaNV", txtTimKiem.getText());

					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (cboTimKiem.getSelectedIndex() == 1) {
					listNV = nhanVienDAO.timKiemNV("TenNV", txtTimKiem.getText());

					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (cboTimKiem.getSelectedIndex() == 2) {
					listNV = nhanVienDAO.timKiemNV("SDT", txtTimKiem.getText());

					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			} else {
				listNV = nhanVienDAO.getAllNV();
				try {
					LoadDBData2JTable1();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void LoadDBData2JTable1() throws Exception {
		modelNhanVien.getDataVector().removeAllElements();
		for (NhanVien nv : listNV) {
			modelNhanVien.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getGioiTinh(), nv.getNgaySinh(),
					nv.getsDT(), nv.getDiaChi() });
		}
	}

	/**
	 * Hàm tạo mã Hóa đơn, mã khách hàng
	 * 
	 * @param loaiMa
	 * @return ma
	 */
	@SuppressWarnings("unlikely-arg-type")
	public String taoMa() {

		nhanVienDAO = new NhanVienDAO();
		Random rand = new Random();
		// int n = khachHangDAO.getAllKH().size();
		int rd = rand.nextInt(10000 - 1000) + 1000;

		do {
			// n=n+1;
			rd = rand.nextInt(10000 - 1000) + 1000;
			ma = "NV_" + String.valueOf(rd);
			listNV = nhanVienDAO.getAllNV();
		} while (listNV.contains(ma));
		return ma;
	}
}
