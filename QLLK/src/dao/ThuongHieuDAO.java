package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.ThuongHieu;


public class ThuongHieuDAO {

	private Connection ketNoi;

	public ThuongHieuDAO() {
		// TODO Auto-generated constructor stub
		ketNoi = Conection.getKetNoi();
	}

	public List<ThuongHieu> phanTrang(int fn, int ln) {
		List<ThuongHieu> dSTH = new ArrayList<ThuongHieu>();
		PreparedStatement stmt = null;
		String sql = "select * from(select *, ROW_NUMBER() over (order by MaTH) as STT from ThuongHieu) as PhanTrang where PhanTrang.STT Between "
				+ fn + " and " + ln;
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ThuongHieu th = new ThuongHieu();
				th.setMaTH(rs.getString("MaTH"));
				th.setTenTH(rs.getString("TenTH"));
				th.setXuatXu(rs.getString("xuatXu"));

				dSTH.add(th);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSTH;
	}
	public List<ThuongHieu> getAllTH() {
		List<ThuongHieu> dSTH = new ArrayList<ThuongHieu>();
		PreparedStatement stmt = null;
		String sql = "select * from ThuongHieu";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ThuongHieu th = new ThuongHieu();
				th.setMaTH(rs.getString("MaTH"));
				th.setTenTH(rs.getString("TenTH"));
				th.setXuatXu(rs.getString("XuatXu"));
				dSTH.add(th);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSTH;
	}

	public List<ThuongHieu> timKieTH(String tk, String vl) {
		List<ThuongHieu> dSTH = new ArrayList<ThuongHieu>();
		PreparedStatement stmt = null;
		String sql = "select * from ThuongHieu where " + tk + " like N'%" + vl + "%'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ThuongHieu th = new ThuongHieu();
			
				th.setMaTH(rs.getString("MaTH"));
				th.setTenTH(rs.getString("TenTH"));
				th.setXuatXu(rs.getString("XuatXu"));

	
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSTH;
	}

	public Boolean themKH(ThuongHieu kh) {
		String sql = "insert into ThuongHieu values(?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, kh.getMaTH());
			stmt.setString(2, kh.getTenTH());
			stmt.setString(3, kh.getXuatXu());

			
			
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

	public boolean deleteKH(ThuongHieu th) {

		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = ketNoi.prepareStatement("delete from ThuongHieu where MaTH= ?");
			stmt.setString(1, th.getMaTH());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (n > 0) {
			return true;
		} else
			return false;
	}

	public boolean update(String ma,ThuongHieu th) {
		PreparedStatement stmt = null;
		try {
			stmt = Conection.getKetNoi().prepareStatement("update ThuongHieu set TenTH=?,XuatXu=? where MaTH=?");
			stmt.setString(1, th.getTenTH());
			stmt.setString(2, th.getXuatXu());
			stmt.setString(4, th.getMaTH());
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
		ThuongHieuDAO thuongHieuDAO = new ThuongHieuDAO();
		System.out.println(thuongHieuDAO.getAllTH());
	}
}
