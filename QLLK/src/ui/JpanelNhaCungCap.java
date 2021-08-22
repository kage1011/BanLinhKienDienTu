package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Date;
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
import dao.LinhKienDAO;
import dao.NhaCungCapDAO;
import entity.LinhKien;
import entity.NhaCungCap;
import entity.NhanVien;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class JpanelNhaCungCap extends JPanel implements ActionListener, MouseListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelNhaCungCap;
	private JTable tableNhaCungCap;
	private JTextField txtMaNCC;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTextField txtTenNCC;
	private JTextField txtSDT;
	private NhaCungCapDAO nhaCungCapDAO;
	private List<NhaCungCap> listNCC;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaRong;
	private JButton btnSua;
	private JComboBox<String> cboTimKiem;
	private JTextField txtTimKiem;
	private String ma;

	/**
	 * Create the panel.
	 */
	public JpanelNhaCungCap() {
		setBackground(Color.LIGHT_GRAY);

		try {
			Conection.getKetNoi();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		nhaCungCapDAO = new NhaCungCapDAO();

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(23, 70, 138));
		panel.setBounds(0, 0, 1300, 59);
		add(panel);
		panel.setLayout(null);
		JLabel lblQunLLinh = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
		lblQunLLinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLLinh.setBounds(0, 0, 1311, 59);
		panel.add(lblQunLLinh);
		lblQunLLinh.setForeground(Color.WHITE);
		lblQunLLinh.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 143, 1050, 340);
		add(scrollPane);
		String[] colHeader = { "Mã NCC", "Tên NCC", "Địa Chỉ", "Email", "SĐT" };
		modelNhaCungCap = new DefaultTableModel(colHeader, 0);
		tableNhaCungCap = new JTable(modelNhaCungCap);
		tableNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableNhaCungCap);
		scrollPane.setBorder(
				new TitledBorder(null, "Danh Sách Nhà Cung Cấp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JTableHeader header1 = tableNhaCungCap.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableNhaCungCap.setRowHeight(25);

		cboTimKiem = new JComboBox<String>();
		cboTimKiem.setBackground(Color.WHITE);
		cboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboTimKiem.setModel(new DefaultComboBoxModel<String>(new String[] { "Mã NCC", "Tên NCC" }));
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
		btnSua.setBounds(757, 674, 110, 50);
		btnSua.setBackground(new Color(23, 70, 138));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnSua);

		JLabel lblMaKH = new JLabel("Mã NCC:");
		lblMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblMaKH.setBounds(54, 517, 76, 14);
		add(lblMaKH);

		JLabel lblTenKH = new JLabel("Tên NCC:");
		lblTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTenKH.setBounds(637, 517, 73, 14);
		add(lblTenKH);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDiaChi.setBounds(54, 619, 73, 14);
		add(lblDiaChi);

		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblSDT.setBounds(54, 568, 46, 14);
		add(lblSDT);

		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaNCC.setBounds(130, 512, 350, 30);
		add(txtMaNCC);
		txtMaNCC.setColumns(10);
		txtMaNCC.setText(taoMa());
		txtMaNCC.setEditable(false);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtDiaChi.setBounds(131, 614, 973, 30);
		add(txtDiaChi);
		txtDiaChi.setColumns(10);

		txtTenNCC = new JTextField();
		txtTenNCC.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(720, 512, 384, 30);
		add(txtTenNCC);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtSDT.setColumns(10);
		txtSDT.setBounds(131, 563, 350, 30);
		add(txtSDT);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblEmail.setBounds(637, 568, 46, 14);
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtEmail.setBounds(720, 563, 384, 30);
		add(txtEmail);
		txtEmail.setColumns(10);

		tableNhaCungCap.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableNhaCungCap.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableNhaCungCap.getColumnModel().getColumn(2).setPreferredWidth(400);
		tableNhaCungCap.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableNhaCungCap.getColumnModel().getColumn(4).setPreferredWidth(100);

		listNCC = nhaCungCapDAO.phanTrang(1, 20);
		docDuLieuVaoModelLinhKien();

		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);

		tableNhaCungCap.addMouseListener(this);

		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row1 = tableNhaCungCap.getSelectedRow();

				if (row1 == -1) {
					JOptionPane.showMessageDialog(null, "Chọn dòng muốn sửa");
					return;
				} else {
					if (validData()) {
						String ma = txtMaNCC.getText().trim();
						String ten = txtTenNCC.getText().trim();
						String diachi = txtDiaChi.getText().trim();
						String email = txtEmail.getText().trim();
						String sdt = txtSDT.getText().trim();

						NhaCungCap ncc = new NhaCungCap(ma, ten, diachi, email, sdt);
						int t1 = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa ?", "Sửa",
								JOptionPane.YES_NO_OPTION);
						if (t1 == JOptionPane.YES_OPTION) {
							if (nhaCungCapDAO.update(ma, ncc)) {
								modelNhaCungCap.setValueAt(ma, row1, 0);
								modelNhaCungCap.setValueAt(ten, row1, 1);
								modelNhaCungCap.setValueAt(diachi, row1, 2);
								modelNhaCungCap.setValueAt(email, row1, 3);
								modelNhaCungCap.setValueAt(sdt, row1, 4);
							}
						}
					}
				}
			}

		});
	}

	public boolean them() {

		String ma = txtMaNCC.getText().trim();
		String ten = txtTenNCC.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String email = txtEmail.getText().trim();
		String sdt = txtSDT.getText().trim();

		NhaCungCap ncc = new NhaCungCap(ma, ten, diachi, email, sdt);
		if (nhaCungCapDAO.themNCC(ncc)) {
			listNCC = nhaCungCapDAO.getAllNCC();
			try {
				docDuLieuVaoModelLinhKien();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(this, "Thêm thành công");
		} else {
			JOptionPane.showMessageDialog(this, "Trùng mã");
		}

		return true;
	}

	private boolean validData() {
		String hoTen = txtTenNCC.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String email = txtEmail.getText().trim();

		if (hoTen.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên NCC trống !");
			txtTenNCC.selectAll();
			txtTenNCC.requestFocus();
			return false;
		} else {

			if ((hoTen.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				JOptionPane.showMessageDialog(this, "Tên NCC gồm chữ cái, có thể chứa khoảng trắng");
				txtTenNCC.requestFocus();
				txtTenNCC.selectAll();
				return false;
			}
		}

		if (sdt.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại trống !");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		} else {

			if (!(sdt.matches("^[0-9]{10}$"))) {
				JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số");
				txtSDT.requestFocus();
				txtSDT.selectAll();
				return false;
			}
		}
		if (email.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Email trống!");
			txtEmail.selectAll();
			txtEmail.requestFocus();
			return false;
		} else {

			if (!(email.matches("^[A-Za-z]+((\\.|\\_)?[A-Za-z0-9])*@(yahoo|gmail|outlook).com$"))) {
				JOptionPane.showMessageDialog(this, "Email có dạng abc@yahoo|gmail|outlook.com");
				txtEmail.requestFocus();
				txtEmail.selectAll();
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
				JOptionPane.showMessageDialog(this, "Địa chỉ gồm chữ cái, số,..");
				txtDiaChi.requestFocus();
				txtDiaChi.selectAll();
				return false;

			}
		}

		return true;
	}

	public void xoaRong() {
		txtMaNCC.setText(taoMa());
		txtTenNCC.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtSDT.setText("");

	}

	public void xoaHetModel() {
		DefaultTableModel df = (DefaultTableModel) tableNhaCungCap.getModel();
		df.getDataVector().removeAllElements();
	}

	public boolean xoa() {
		ArrayList<NhaCungCap> dsNCC = (ArrayList<NhaCungCap>) nhaCungCapDAO.getAllNCC();
		int r = tableNhaCungCap.getSelectedRow();
		int tl = JOptionPane.showConfirmDialog(this, "Có muốn xóa hay không ?", "", JOptionPane.YES_NO_OPTION);
		if (tl == JOptionPane.YES_OPTION) {
			NhaCungCap ncc = dsNCC.get(r);
			nhaCungCapDAO.deleteNCC(ncc);
			modelNhaCungCap.removeRow(r);
			return true;
		}
		return false;
	}

	public boolean sua() {
		int row = tableNhaCungCap.getSelectedRow();
		if (row != -1) {
			String ma = txtMaNCC.getText().trim();
			String ten = txtTenNCC.getText().trim();
			String diachi = txtDiaChi.getText().trim();
			String email = txtEmail.getText().trim();
			String sdt = txtSDT.getText().trim();

			if (nhaCungCapDAO.update(ma, new NhaCungCap(ma, ten, diachi, email, sdt))) {

				modelNhaCungCap.setValueAt(ma, row, 0);
				modelNhaCungCap.setValueAt(ten, row, 1);
				modelNhaCungCap.setValueAt(diachi, row, 2);
				modelNhaCungCap.setValueAt(email, row, 3);
				modelNhaCungCap.setValueAt(sdt, row, 4);

				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableNhaCungCap.getSelectedRow();
		if (row != -1) {
			txtMaNCC.setText(modelNhaCungCap.getValueAt(row, 0).toString());
			txtTenNCC.setText(modelNhaCungCap.getValueAt(row, 1).toString());
			txtDiaChi.setText(modelNhaCungCap.getValueAt(row, 2).toString());
			txtEmail.setText(modelNhaCungCap.getValueAt(row, 3).toString());
			txtSDT.setText(modelNhaCungCap.getValueAt(row, 4).toString());

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
			if (validData()) {
				if (them()) {
//					xoaHetModel();
					listNCC = nhaCungCapDAO.getAllNCC();
					tableNhaCungCap.clearSelection();
					try {
						docDuLieuVaoModelLinhKien();
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
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
				listNCC = nhaCungCapDAO.getAllNCC();
				docDuLieuVaoModelLinhKien();
				tableNhaCungCap.clearSelection();
				JOptionPane.showMessageDialog(this, "Xóa thành công!");
			} else
				JOptionPane.showMessageDialog(this, "Xóa thất bại!");
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
				if (cboTimKiem.getSelectedIndex() == 1) {
					listNCC = nhaCungCapDAO.timKiemNCC("TenNCC", txtTimKiem.getText());
					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (cboTimKiem.getSelectedIndex() == 0) {
					listNCC = nhaCungCapDAO.timKiemNCC("MaNCC", txtTimKiem.getText());
					try {
						LoadDBData2JTable1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			} else {
				listNCC = nhaCungCapDAO.getAllNCC();
				docDuLieuVaoModelLinhKien();
			}
		}
	}

	public void docDuLieuVaoModelLinhKien() {
		modelNhaCungCap.getDataVector().removeAllElements();
		for (NhaCungCap ncc : listNCC) {
			modelNhaCungCap.addRow(
					new Object[] { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getEmail(), ncc.getsDT() });
		}
	}

	public void LoadDBData2JTable1() throws Exception {

		modelNhaCungCap.getDataVector().removeAllElements();
		for (NhaCungCap ncc : listNCC) {
			modelNhaCungCap.addRow(
					new Object[] { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getEmail(), ncc.getsDT() });
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

		nhaCungCapDAO = new NhaCungCapDAO();
		Random rand = new Random();
		// int n = khachHangDAO.getAllKH().size();
		int rd = rand.nextInt(10000 - 1000) + 1000;

		do {
			// n=n+1;
			rd = rand.nextInt(10000 - 1000) + 1000;
			ma = "NCC_" + String.valueOf(rd);
			listNCC = nhaCungCapDAO.getAllNCC();
		} while (listNCC.contains(ma));
		return ma;
	}
}
