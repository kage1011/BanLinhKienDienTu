package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import conection.Conection;
import dao.KhachHangDAO;

import entity.KhachHang;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class JpanelKhachHang extends JPanel implements ActionListener, MouseListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private KhachHangDAO khachHangDAO;
	private DefaultTableModel modelKhachHang;
	private JTable tableKhachHang;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaRong;
	private JButton btnSua;
	private JTextField txtMaKH;
	private JTextField txtDiaChi;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private List<KhachHang> listKH;

	private JComboBox<String> cboTimKiem;

	private JTextField txtTimKiem;

	private String ma;

	/**
	 * Create the panel.
	 */
	public JpanelKhachHang() {
		setBackground(Color.LIGHT_GRAY);

		try {
			Conection.getKetNoi();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		khachHangDAO = new KhachHangDAO();

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(23, 70, 138));
		panel.setBounds(0, 0, 1300, 59);
		add(panel);
		panel.setLayout(null);
		JLabel lblQunLLinh = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblQunLLinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLLinh.setBounds(0, 0, 1311, 59);
		panel.add(lblQunLLinh);
		lblQunLLinh.setForeground(Color.WHITE);
		lblQunLLinh.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 143, 1050, 318);
		add(scrollPane);
		String[] colHeader = { "Mã KH", "Tên KH", "SĐT", "Địa Chỉ" };
		modelKhachHang = new DefaultTableModel(colHeader, 0);
		tableKhachHang = new JTable(modelKhachHang);
		tableKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableKhachHang);
		scrollPane.setBorder(
				new TitledBorder(null, "Danh Sách Khách Hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JTableHeader header1 = tableKhachHang.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableKhachHang.setRowHeight(25);

		cboTimKiem = new JComboBox<String>();
		cboTimKiem.setBackground(Color.WHITE);
		cboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboTimKiem
		.setModel(new DefaultComboBoxModel<String>(new String[] { "Mã khách hàng", "Tên khách hàng", "SDT" }));
		cboTimKiem.setSelectedIndex(1);
		cboTimKiem.setBounds(54, 88, 200, 30);
		add(cboTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setBounds(301, 88, 803, 30);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		txtTimKiem.addKeyListener(this);

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
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnSua);

		JLabel lblMaKH = new JLabel("Mã KH:");
		lblMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblMaKH.setBounds(54, 515, 76, 14);
		add(lblMaKH);

		JLabel lblTenKH = new JLabel("Tên KH:");
		lblTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTenKH.setBounds(691, 515, 73, 14);
		add(lblTenKH);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDiaChi.setBounds(54, 615, 73, 14);
		add(lblDiaChi);

		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblSDT.setBounds(54, 564, 46, 14);
		add(lblSDT);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaKH.setBounds(130, 510, 350, 30);
		add(txtMaKH);
		txtMaKH.setColumns(10);
		txtMaKH.setText(taoMa());
		txtMaKH.setEditable(false);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDiaChi.setBounds(131, 610, 973, 30);
		add(txtDiaChi);
		txtDiaChi.setColumns(10);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(774, 510, 330, 30);
		add(txtTenKH);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtSDT.setColumns(10);
		txtSDT.setBounds(131, 559, 350, 30);
		add(txtSDT);

		tableKhachHang.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableKhachHang.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableKhachHang.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableKhachHang.getColumnModel().getColumn(3).setPreferredWidth(500);

		listKH = khachHangDAO.phanTrang(1, 20);
		try {
			LoadDBData2JTable1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSua.addActionListener(this);
		tableKhachHang.addMouseListener(this);

	}

	public boolean them() {

		String ma = txtMaKH.getText().trim();
		String ten = txtTenKH.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String sdt = txtSDT.getText().trim();

		KhachHang kh = new KhachHang(ma, ten, sdt, diachi);
		if (khachHangDAO.themKH(kh)) {
			listKH = khachHangDAO.getAllKH();
			try {
				LoadDBData2JTable1();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Thêm thành công");
		} else {
			JOptionPane.showMessageDialog(this, "Trùng mã");
		}

		return true;
	}

	private boolean validData() {
		String hoTen = txtTenKH.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();

		if (hoTen.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Họ tên khách hàng trống!");
			txtTenKH.selectAll();
			txtTenKH.requestFocus();
			return false;
		} else {

			if ((hoTen.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Tên khách hàng gồm chữ cái, có thể chứa khoảng trắng");
				txtTenKH.requestFocus();
				txtTenKH.selectAll();
				return false;
			}
		}

		if (sdt.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng trống !");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		} else {

			if (!(sdt.matches("^[0-9]{10}$"))) {
				JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng gồm 10 số");
				txtSDT.requestFocus();
				txtSDT.selectAll();
				return false;
			}
		}

		if (diaChi.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được trống!");
			txtDiaChi.selectAll();
			txtDiaChi.requestFocus();
			return false;
		} else {

			if (diaChi.matches(
					"^[a-z0-9A-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]")) {
				JOptionPane.showMessageDialog(this, "Địa chỉ gồm chữ cái, số,...");
				txtDiaChi.requestFocus();
				txtDiaChi.selectAll();
				return false;

			}
		}

		return true;
	}

	public void xoaRong() {
		txtMaKH.setText("");
		txtMaKH.setText(taoMa());
		txtTenKH.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");

	}

	public void xoaHetModel() {
		DefaultTableModel df = (DefaultTableModel) tableKhachHang.getModel();
		df.getDataVector().removeAllElements();
	}

	public boolean xoa() {
		ArrayList<KhachHang> dsKH = (ArrayList<KhachHang>) khachHangDAO.getAllKH();
		int r = tableKhachHang.getSelectedRow();
		int tl = JOptionPane.showConfirmDialog(this, "Có muốn xóa hay không ?", "", JOptionPane.YES_NO_OPTION);
		if (tl == JOptionPane.YES_OPTION) {
			KhachHang kh = dsKH.get(r);
			khachHangDAO.deleteKH(kh);
			modelKhachHang.removeRow(r);
			return true;
		}
		return false;
	}

	public boolean sua() {
		int row = tableKhachHang.getSelectedRow();
		if (row != -1) {
			String ma = txtMaKH.getText().trim();
			String ten = txtTenKH.getText().trim();
			String diachi = txtDiaChi.getText().trim();
			String sdt = txtSDT.getText().trim();

			KhachHang kh = new KhachHang(ma, ten, diachi, sdt);
			if (khachHangDAO.update(ma, kh)) {

				modelKhachHang.setValueAt(ma, row, 0);
				modelKhachHang.setValueAt(ten, row, 1);
				modelKhachHang.setValueAt(sdt, row, 2);
				modelKhachHang.setValueAt(diachi, row, 3);

				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableKhachHang.getSelectedRow();
		if (row != -1) {
			txtMaKH.setText(modelKhachHang.getValueAt(row, 0).toString());
			txtTenKH.setText(modelKhachHang.getValueAt(row, 1).toString());
			txtSDT.setText(modelKhachHang.getValueAt(row, 2).toString());
			txtDiaChi.setText(modelKhachHang.getValueAt(row, 3).toString());

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
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if(validData()) {
				if (them()) {
					//					xoaHetModel();
					listKH = khachHangDAO.getAllKH();
					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableKhachHang.clearSelection();
					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		} else if (o.equals(btnXoaRong))

			xoaRong();
		else if (o.equals(btnXoa))

		{
			if (xoa()) {
				xoaRong();
				//				xoaHetModel();
				listKH = khachHangDAO.getAllKH();
				try {
					LoadDBData2JTable1();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tableKhachHang.clearSelection();
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
			} else
				JOptionPane.showMessageDialog(this, "Xóa thất bại!");
		} else if (o.equals(btnSua)) {
			if(validData()) {
				if (sua()) {
					JOptionPane.showMessageDialog(this, "Sửa Thành Công!!!");
					listKH = khachHangDAO.getAllKH();
					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
		Object o = arg0.getSource();
		if (o.equals(txtTimKiem)) {
			if (!txtTimKiem.getText().equals("")) {
				if (cboTimKiem.getSelectedIndex() == 1) {
					listKH = khachHangDAO.timKieKH("TenKH", txtTimKiem.getText());

					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (cboTimKiem.getSelectedIndex() == 0) {
					listKH = khachHangDAO.timKieKH("MaKH", txtTimKiem.getText());

					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (cboTimKiem.getSelectedIndex() == 2) {
					listKH = khachHangDAO.timKieKH("SDT", txtTimKiem.getText());

					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				listKH = khachHangDAO.getAllKH();
				try {
					LoadDBData2JTable1();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void LoadDBData2JTable1() throws Exception {

		modelKhachHang.getDataVector().removeAllElements();
		for (KhachHang kh : listKH) {
			modelKhachHang.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getsDT(), kh.getDiaChi() });
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

		khachHangDAO = new KhachHangDAO();
		Random rand = new Random();
		// int n = khachHangDAO.getAllKH().size();
		int rd = rand.nextInt(10000 - 1000) + 1000;

		do {
			// n=n+1;
			rd = rand.nextInt(10000 - 1000) + 1000;
			ma = "KH_" + String.valueOf(rd);
			listKH = khachHangDAO.getAllKH();
		} while (listKH.contains(ma));
		return ma;
	}
}
