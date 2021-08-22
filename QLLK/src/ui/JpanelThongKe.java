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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import conection.Conection;
import dao.ChiTietHoaDonDAO;
import dao.NhanVienDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhanVien;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JComboBox;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class JpanelThongKe extends JPanel implements ActionListener,MouseListener,KeyListener{
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
	private JLabel lblTenNv;
	private JLabel lblNm;
	private JLabel lblThng;
	private JComboBox<String> cboTenNV;
	private JButton btnchon;
	private JComboBox<String> cboNam;
	private JComboBox<String> cboThang;
	private JPanel pnlTong;
	private JLabel lblTongCong;
	private JLabel lblSoLuong;
	private JLabel lblTongTien;
	private NhanVienDAO nhanVienDao;
	private List<NhanVien> listNV;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JpanelThongKe() throws IOException {
		setBackground(Color.LIGHT_GRAY);
		df = new DecimalFormat("#,##0.00");
		try {
			Conection.getKetNoi();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		chiTietHoaDonDao = new ChiTietHoaDonDAO();
		listCTHD=chiTietHoaDonDao.getAll();
		nhanVienDao=new NhanVienDAO();
		
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
		String[] colHeader = { "STT", "Ngày Lập", "Tên Nhân Viên","Tên Khách Hàng", "Tên Sản Phẩm","Số Lượng","Thành Tiền"};
		modelThongKe = new DefaultTableModel(colHeader, 0);
		tableThongKe = new JTable(modelThongKe);
		tableThongKe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tableThongKe);
		scrollPane.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		JTableHeader header1 = tableThongKe.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableThongKe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableThongKe.setRowHeight(30);

		
		tableThongKe.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableThongKe.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableThongKe.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableThongKe.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableThongKe.getColumnModel().getColumn(4).setPreferredWidth(200);
		tableThongKe.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableThongKe.getColumnModel().getColumn(6).setPreferredWidth(150);
		
		table = new JTable();
		table.setBounds(316, 583, 1, 1);
		add(table);
		
		lblTenNv = new JLabel("Tên NV: ");
		lblTenNv.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTenNv.setBounds(54, 89, 116, 47);
		add(lblTenNv);
		
		lblNm = new JLabel("Năm: ");
		lblNm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNm.setBounds(395, 89, 70, 47);
		add(lblNm);
		
		lblThng = new JLabel("Tháng: ");
		lblThng.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblThng.setBounds(636, 89, 70, 47);
		add(lblThng);
		
		cboTenNV = new JComboBox<String>();
		listNV = nhanVienDao.getAllNV();
		cboTenNV.addItem(""); 
		for (NhanVien nv : listNV) {
			int n = 0;
			for (int i = 0; i < cboTenNV.getItemCount(); i++) {
				if (cboTenNV.getItemAt(i).equals(nv.getTenNV())) {
					n++;
				}
			}
			if (n == 0) {
				cboTenNV.addItem(nv.getTenNV());
			}

		}
		cboTenNV.setSelectedIndex(1);
		cboTenNV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboTenNV.setBackground(Color.WHITE);
		cboTenNV.setBounds(125, 97, 192, 30);
		add(cboTenNV);


		cboNam = new JComboBox<String>();
		cboNam.setModel(new DefaultComboBoxModel(new String[] {"","2020","2021"}));
		cboNam.setSelectedIndex(1);
		cboNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboNam.setBackground(Color.WHITE);
		cboNam.setBounds(447, 97, 97, 30);
		add(cboNam);
		
		cboThang = new JComboBox<String>();
		cboThang.setModel(new DefaultComboBoxModel(new String[] {"","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cboThang.setSelectedIndex(1);
		cboThang.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboThang.setBackground(Color.WHITE);
		cboThang.setBounds(701, 97, 97, 30);
		add(cboThang);
		
		
		btnchon = new JButton("Chọn");
		btnchon.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnchon.setBackground(new Color(23,70,138));
		btnchon.setForeground(Color.WHITE);
		btnchon.setBounds(913, 96, 85, 30);
		add(btnchon);
		
		tableThongKe.addMouseListener(this);
		btnchon.addActionListener(this);
		
		pnlTong = new JPanel();
		pnlTong.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.WHITE));
		pnlTong.setLayout(null);
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
		lblSoLuong.setBounds(782, 0, 87, 57);
		pnlTong.add(lblSoLuong);

		
		lblTongTien = new JLabel();
		lblTongTien.setText("0.00");
		lblTongTien.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTongTien.setBounds(879, 0, 171, 57);
		pnlTong.add(lblTongTien);
		
		
		docDuLieuVaoModelLinhKien();
		
		double tong=tinhTongTien();
		lblTongTien.setText(df.format(tong));
		
		int sol=tinhSoLuong();
		lblSoLuong.setText(" "+sol);
		
		
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
			if( !(cboTenNV.getSelectedItem()=="") || !(cboNam.getSelectedItem()=="") || !(cboThang.getSelectedItem()=="") ) {
				listCTHD = chiTietHoaDonDao.ThongKeChoAdmin(cboTenNV.getSelectedItem().toString(),cboNam.getSelectedItem().toString(),cboThang.getSelectedItem().toString());
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
					new Object[] {count,  tk.getHoaDon().getNgayLapHD(),tk.getHoaDon().getNhanVien().getTenNV(), 
							tk.getHoaDon().getKhachHang().getTenKH(), tk.getLinhKien().getTenLK(),
							tk.getSoLuong(),df.format(tk.tinhThanhTien(tk.getSoLuong(), tk.getLinhKien()))});
		}
	}
	public double tinhTongTien() {
		double tongTien = 0;
		for (int i = 0; i < modelThongKe.getRowCount(); i++) {
			tongTien += Double.parseDouble(modelThongKe.getValueAt(i, 6).toString().replace(",", ""));
		}
		return tongTien;
	}
	
	public int tinhSoLuong() {
		int sl = 0;
		for (int i = 0; i < modelThongKe.getRowCount(); i++) {
			sl += Integer.parseInt(modelThongKe.getValueAt(i, 5).toString());
		}
		return sl;
	}
}
