package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.NhanVien;

public class ChuyenManHinh {
	private JPanel pnlMHTrangChu;
	private NhanVien nhanVien;
	private String chonTrang = "";
	private List<DanhMuc> dSTrang = null;

	public ChuyenManHinh(JPanel pnlMHTrangChu, NhanVien nhanVien) {
		this.pnlMHTrangChu = pnlMHTrangChu;
		this.nhanVien=nhanVien;
	}

	public void setManHinh(JPanel pnlChon, JLabel lblChon) throws Exception {
		chonTrang = "TrangChu";
		pnlChon.setBackground(new Color(0,139,252));
		lblChon.setBackground(new Color(0,139,252));

		pnlMHTrangChu.removeAll();
		pnlMHTrangChu.setLayout(new BorderLayout());
		pnlMHTrangChu.add(new JpanelBanLinhKien(nhanVien));
		pnlMHTrangChu.validate();
		pnlMHTrangChu.repaint();
	}

	public void setSuKien(List<DanhMuc> danhMuc) {
		this.dSTrang = danhMuc;
		for (DanhMuc lst : danhMuc) {
			lst.getLbl().addMouseListener(new LabelEvent(lst.getChon(), lst.getPnl(), lst.getLbl()));
		}
	}

	class LabelEvent implements MouseListener {

		private JPanel node;
		private String chon;

		private JPanel pnlChon;
		private JLabel lblChon;

		public LabelEvent(String chon, JPanel pnlChon, JLabel lblChon) {
			this.chon = chon;
			this.pnlChon = pnlChon;
			this.lblChon = lblChon;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			switch (chon) {
			case "TrangChu":
				node = new JpanelTrangChu();
				setPanel();
				break;
			case "HoaDon":
				node = new JPanelHoaDon();
				setPanel();
				break;
				
			case "KhachHang":
				node = new JpanelKhachHang();
				setPanel();
				break;
			case "LinhKien":
				try {
					node = new JpanelLinhKien();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setPanel();
				break;
			case "BanLinhKien":
				try {
					node =new JpanelBanLinhKien(nhanVien);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setPanel();
				break;
			case "NhanVien":
				if(nhanVien.getQuyen().trim().equals("Admin")) {
					try {
						node = new JpanelNhanVien();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setPanel();
				}
				else   {
					JOptionPane.showMessageDialog(null, "Chức năng này chỉ dành cho người quản lý");
				}
				break;
			case "NhaCungCap":
				node = new JpanelNhaCungCap();
				setPanel();
				break;
		
			case "ThongKe":
				if(nhanVien.getQuyen().trim().equals("Admin")) {
					try {
						node=new JpanelThongKe();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setPanel();
				}
				else if(nhanVien.getQuyen().trim().equals("User")){
					try {
						node=new JpanelThongKe_user(nhanVien);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setPanel();
				}
				
					break;
			
			case "DangXuat":
				break;
			default:
				node = new JpanelTrangChu();
				break;
			}
			
			setThayDoiNen(chon);
		}
		
		public void setPanel() {
			if (!chon.equals("DangXuat")) {
				pnlMHTrangChu.removeAll();
				pnlMHTrangChu.setLayout(new BorderLayout());
				pnlMHTrangChu.add(node);
				pnlMHTrangChu.validate();
				pnlMHTrangChu.repaint();
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			chonTrang = chon;
			pnlChon.setBackground(new Color(0,139,252));
			lblChon.setBackground(new Color(0,139,252));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if (!chonTrang.equalsIgnoreCase(chon)) {
				pnlChon.setBackground(new Color(0,139,252));
				lblChon.setBackground(new Color(0,139,252));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (!chonTrang.equalsIgnoreCase(chon)) {
				pnlChon.setBackground(new Color(23,70,138));
				lblChon.setBackground(new Color(23,70,138));
			}
		}
	}

	private void setThayDoiNen(String chon) {
		for (DanhMuc danhMuc : dSTrang) {
			if (danhMuc.getChon().equalsIgnoreCase(chon)) {
				danhMuc.getPnl().setBackground(new Color(0,139,252));
				danhMuc.getLbl().setBackground(new Color(0,139,252));
			} else {
				danhMuc.getPnl().setBackground(new Color(23,70,138));
				danhMuc.getLbl().setBackground(new Color(0,139,252));
			}
		}
	}
}
