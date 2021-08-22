package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.KhachHang;


public class KhachHangDAO {

	private Connection ketNoi;

	public KhachHangDAO() {
		// TODO Auto-generated constructor stub
		ketNoi = Conection.getKetNoi();
	}

	public List<KhachHang> phanTrang(int fn, int ln) {
		List<KhachHang> dSKH = new ArrayList<KhachHang>();
		PreparedStatement stmt = null;
		String sql = "select * from(select *, ROW_NUMBER() over (order by MaKH) as STT from KhachHang) as PhanTrang where PhanTrang.STT Between "
				+ fn + " and " + ln;
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setMaKH(rs.getString("MaKH"));

				kh.setTenKH(rs.getString("TenKH"));

				kh.setDiaChi(rs.getString("DiaChi"));
				kh.setsDT(rs.getString("SDT"));

				dSKH.add(kh);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSKH;
	}
	public KhachHang getKhachHang(String maKH) {
		KhachHang kh = new KhachHang();
		PreparedStatement stmt = null;
		String sql = "select * from KhachHang where MaKH = '" + maKH + "'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh.setMaKH(rs.getString("MaKH"));

				kh.setTenKH(rs.getString("TenKH"));

				kh.setDiaChi(rs.getString("DiaChi"));
				kh.setsDT(rs.getString("SDT"));
				
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kh;
	}
	public List<KhachHang> getAllKH() {
		List<KhachHang> dSKH = new ArrayList<KhachHang>();
		PreparedStatement stmt = null;
		String sql = "select * from KhachHang";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setMaKH(rs.getString("MaKH"));
				kh.setTenKH(rs.getString("TenKH"));
				kh.setDiaChi(rs.getString("DiaChi"));
				kh.setsDT(rs.getString("SDT"));
				dSKH.add(kh);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSKH;
	}

	public List<KhachHang> timKieKH(String tk, String vl) {
		List<KhachHang> dSKH = new ArrayList<KhachHang>();
		PreparedStatement stmt = null;
		String sql = "select * from KhachHang where "+tk+" like N'%"+vl+"%'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				KhachHang kh = new KhachHang();
				
				kh.setMaKH(rs.getString("MaKH"));
				kh.setTenKH(rs.getString("TenKH"));
				kh.setDiaChi(rs.getString("DiaChi"));
				kh.setsDT(rs.getString("SDT"));
				
				dSKH.add(kh);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSKH;
	}

	public Boolean themKH(KhachHang kh) {
		String sql = "insert into KhachHang values(?,?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getTenKH());
			stmt.setString(3, kh.getsDT());
			stmt.setString(4, kh.getDiaChi());
			
			
			try {
				n = stmt.executeUpdate();
				if (n > 0)
					return true;
			} catch (Exception e) {
				// TODO: handle exception
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteKH(KhachHang kh) {
		try {
			PreparedStatement stmt = ketNoi.prepareStatement("delete from KhachHang where MaKH= ?");
			stmt.setString(1, kh.getMaKH());
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return false;
	}

	public boolean update(String ma,KhachHang kh) {
		PreparedStatement stmt = null;
		try {
			stmt = Conection.getKetNoi().prepareStatement("update KhachHang set TenKH=?,DiaChi=?,SDT=? where MaKH=?");
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getsDT());
			stmt.setString(3, kh.getDiaChi());
			stmt.setString(4, kh.getMaKH());
			stmt.executeUpdate();
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	

	public static void main(String[] args) {
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		System.out.println(khachHangDAO.getAllKH());
	}
}
