package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import dao.ChiTietHoaDonDAO;
import dao.NhanVienDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.LoaiLinhKien;
import entity.NhanVien;
import ui.JpanelNhanVien.DateLabelFormatter;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JComboBox;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

public class JpanelThongKe_user extends JPanel implements ActionListener,MouseListener,KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelThongKe;
	private JTable tableThongKe;
	private JTable table;
	private DecimalFormat df;
	private ChiTietHoaDonDAO chiTietHoaDonDao;
	private List<ChiTietHoaDon> listCTHD;
	private double TongTien;
	private HoaDon hoadon;
	private JLabel lblNgay;
	private JLabel lblNm;
	private JLabel lblThng;
	private JComboBox<String> cboNam;
	private JComboBox<String> cboThang;
	private NhanVien nv;
	private NhanVien nhv;
	private NhanVienDAO nhanVienDao;
	private JTextField txtNgay;
	private JButton btnchon;
	private JLabel lblTongCong;
	private JLabel lblSoLuong;
	private JLabel lblTongTien;
	private SqlDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JpanelThongKe_user(NhanVien nv) throws IOException {
		setBackground(Color.LIGHT_GRAY);
		
		this.nv=nv;
		df = new DecimalFormat("#,##0.00");
		try {
			Conection.getKetNoi();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		chiTietHoaDonDao = new ChiTietHoaDonDAO();
		nhanVienDao=new NhanVienDAO();
		nhv = nhanVienDao.getNhanVien(nv.getMaNV());
		listCTHD=chiTietHoaDonDao.ThongKeChoUser(nhv.getTenNV(), "", "", "");
		
		
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(23,70,138));
		panel.setBounds(0, 0, 1151, 59);
		add(panel);
		panel.setLayout(null);
		JLabel lblQunLLinh = new JLabel("QUẢN LÝ THỐNG KÊ");
		lblQunLLinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLLinh.setBounds(0, 0, 1151, 59);
		panel.add(lblQunLLinh);
		lblQunLLinh.setForeground(Color.WHITE);
		lblQunLLinh.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 164, 1050, 489);
		add(scrollPane);
		String[] colHeader = { "STT", "Ngày Lập", "Tên Khách Hàng", "Tên Sản Phẩm","Số Lượng","Thành Tiền"};
		modelThongKe = new DefaultTableModel(colHeader, 0);
		tableThongKe = new JTable(modelThongKe);
		tableThongKe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableThongKe);
		scrollPane.setBorder(
				new TitledBorder(null, "Danh Sách Nhà Cung Cấp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JTableHeader header1 = tableThongKe.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableThongKe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableThongKe.setRowHeight(30);

		
		tableThongKe.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableThongKe.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableThongKe.getColumnModel().getColumn(2).setPreferredWidth(300);
		tableThongKe.getColumnModel().getColumn(3).setPreferredWidth(400);
		tableThongKe.getColumnModel().getColumn(4).setPreferredWidth(110);
		tableThongKe.getColumnModel().getColumn(5).setPreferredWidth(200);
		
		table = new JTable();
		table.setBounds(316, 583, 1, 1);
		add(table);
		
		lblNgay = new JLabel("Ngày: ");
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNgay.setBounds(562, 89, 56, 47);
		add(lblNgay);
		
		lblNm = new JLabel("Năm: ");
		lblNm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNm.setBounds(54, 89, 70, 47);
		add(lblNm);
		
		lblThng = new JLabel("Tháng: ");
		lblThng.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblThng.setBounds(295, 89, 70, 47);
		add(lblThng);


		cboNam = new JComboBox<String>();
		cboNam.setModel(new DefaultComboBoxModel(new String[] {"","2021","2020"}));
		cboNam.setSelectedIndex(1);
		cboNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboNam.setBackground(Color.WHITE);
		cboNam.setBounds(106, 97, 97, 30);
		add(cboNam);
		
		cboThang = new JComboBox<String>();
		cboThang.setModel(new DefaultComboBoxModel(new String[] {"","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cboThang.setSelectedIndex(1);
		cboThang.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboThang.setBackground(Color.WHITE);
		cboThang.setBounds(360, 97, 97, 30);
		add(cboThang);
		
		txtNgay = new JTextField();
		txtNgay.setBounds(623, 97, 96, 30);
		txtNgay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(txtNgay);
		txtNgay.setColumns(10);
		
		JPanel pnlTong = new JPanel();
		pnlTong.setLayout(null);
		pnlTong.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.WHITE));
		pnlTong.setBackground(Color.LIGHT_GRAY);
		pnlTong.setBounds(54, 654, 1050, 59);
		add(pnlTong);
		
		lblTongCong = new JLabel("Tổng cộng:");
		lblTongCong.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTongCong.setBounds(10, 0, 139, 57);
		pnlTong.add(lblTongCong);
		
		lblSoLuong = new JLabel();
		lblSoLuong.setText("0");
		lblSoLuong.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSoLuong.setBounds(820, 0, 70, 57);
		pnlTong.add(lblSoLuong);
		
		lblTongTien = new JLabel();
		lblTongTien.setText("0.00");
		lblTongTien.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTongTien.setBounds(902, 0, 148, 57);
		pnlTong.add(lblTongTien);
		
		btnchon = new JButton("Chọn");
		btnchon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnchon.setForeground(Color.WHITE);
		btnchon.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnchon.setBackground(new Color(23, 70, 138));
		btnchon.setBounds(865, 96, 85, 30);
		add(btnchon);
		
		docDuLieuVaoModelLinhKien();
		
		double tong=tinhTongTien();
		lblTongTien.setText(df.format(tong));
		
		int sol=tinhSoLuong();
		lblSoLuong.setText(" "+sol);
		
		
	
		tableThongKe.addMouseListener(this);
		btnchon.addActionListener(this);
		
		
		
	}
	


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
//		Object o = arg0.getSource();
//		if (o.equals(cboTenNV.getSelectedItem().toString())) {
//				System.out.println(cboTenNV.getSelectedItem().toString());
//			
////			if(cboTenNV.getSelectedIndex()==1) {
////				listCTHD = chiTietHoaDonDao.timKiem(cboTenNV.getSelectedItem().toString(),"","");
////				docDuLieuVaoModelLinhKien();
////				double tong=tinhTongTien();
////				lblTongTien.setText(df.format(tong));
////				
////				int sol=tinhSoLuong();
////				lblSoLuong.setText(sol+"");
////			}
//		}

//		if (o.equals(txtNV) || o.equals(txtNam) || o.equals(txtThang)) {
//			if (!(txtNV).getText().equals("") || !(txtNam).getText().equals("") || !(txtThang).getText().equals("")  ) {
//				if (cboNV.getSelectedIndex() == 0 || cboNam.getSelectedIndex()==0 || cboThang.getSelectedIndex()==0) {
//					listCTHD = chiTietHoaDonDao.timKiem(txtNV.getText(),txtNam.getText(),txtThang.getText());
//					docDuLieuVaoModelLinhKien();
//					double tong=tinhTongTien();
//					lblTongTien.setText(df.format(tong));
//					
//					int sol=tinhSoLuong();
//					lblSoLuong.setText(sol+"");
//
//				}
//			
//			}else {
//				listCTHD=chiTietHoaDonDao.getAll();
//				docDuLieuVaoModelLinhKien();
//				double tong=tinhTongTien();
//				lblTongTien.setText(df.format(tong));
//				
//				int sol=tinhSoLuong();
//				lblSoLuong.setText(sol+"");
//			}
//		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object o = arg0.getSource();
		if (o.equals(btnchon)) {
			if( !(txtNgay.getText()=="") || !(cboNam.getSelectedItem()=="") || !(cboThang.getSelectedItem()=="") ) {
				
				listCTHD = chiTietHoaDonDao.ThongKeChoUser(nhv.getTenNV(),cboNam.getSelectedItem().toString(),cboThang.getSelectedItem().toString(),txtNgay.getText().trim());
				docDuLieuVaoModelLinhKien();
				double tong=tinhTongTien();
				lblTongTien.setText(df.format(tong));
				
				int sol=tinhSoLuong();
				lblSoLuong.setText(sol+"");
			}
			else {
				listCTHD=chiTietHoaDonDao.getAll();
				docDuLieuVaoModelLinhKien();
				double tong=tinhTongTien();
				lblTongTien.setText(df.format(tong));
				
				int sol=tinhSoLuong();
				lblSoLuong.setText(sol+"");
			}
		}
	}
	public void docDuLieuVaoModelLinhKien() {
		modelThongKe.getDataVector().removeAllElements();
		int count=0;
		for (ChiTietHoaDon tk : listCTHD) {
			count++;
			modelThongKe.addRow(
					new Object[] {count,  tk.getHoaDon().getNgayLapHD(), 
							tk.getHoaDon().getKhachHang().getTenKH(), tk.getLinhKien().getTenLK(),
							tk.getSoLuong(),df.format(tk.getLinhKien().getGiaBan()*tk.getSoLuong())});
		}
	}
	public double tinhTongTien() {
		double tongTien = 0;
		for (int i = 0; i < modelThongKe.getRowCount(); i++) {
			tongTien += Double.parseDouble(modelThongKe.getValueAt(i, 5).toString().replace(",", ""));
		}
		return tongTien;
	}
	
	public int tinhSoLuong() {
		int sl = 0;
		for (int i = 0; i < modelThongKe.getRowCount(); i++) {
			sl += Integer.parseInt(modelThongKe.getValueAt(i, 4).toString());
		}
		return sl;
	}
}
