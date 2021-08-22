package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;

import entity.NhaCungCap;

public class NhaCungCapDAO {

	private Connection ketNoi;

	public NhaCungCapDAO() {
		// TODO Auto-generated constructor stub
		ketNoi = Conection.getKetNoi();
	}

	public List<NhaCungCap> phanTrang(int fn, int ln) {
		List<NhaCungCap> dSNCC = new ArrayList<NhaCungCap>();
		PreparedStatement stmt = null;
		String sql = "select * from(select *, ROW_NUMBER() over (order by MaNCC) as STT from NhaCungCap) as PhanTrang where PhanTrang.STT Between "
				+ fn + " and " + ln;
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNCC(rs.getString("MaNCC"));

				ncc.setTenNCC(rs.getString("TenNCC"));

				ncc.setDiaChi(rs.getString("DiaChi"));
				ncc.setEmail(rs.getString("Email"));
				ncc.setsDT(rs.getString("SDT"));

				dSNCC.add(ncc);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSNCC;
	}
	public List<NhaCungCap> getAllNCC() {
		List<NhaCungCap> dSNCC = new ArrayList<NhaCungCap>();
		PreparedStatement stmt = null;
		String sql = "select * from NhaCungCap";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNCC(rs.getString("MaNCC"));

				ncc.setTenNCC(rs.getString("TenNCC"));

				ncc.setDiaChi(rs.getString("DiaChi"));
				ncc.setEmail(rs.getString("Email"));
				ncc.setsDT(rs.getString("SDT"));

				dSNCC.add(ncc);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSNCC;
	}

	public List<NhaCungCap> timKieNCC(String tk, String vl) {
		List<NhaCungCap> dSNCC = new ArrayList<NhaCungCap>();
		PreparedStatement stmt = null;
		String sql = "select * from NhaCungCap where " + tk + " like N'%" + vl + "%'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhaCungCap ncc=new NhaCungCap();
				ncc.setMaNCC("maNCC");
				ncc.setTenNCC("tenNCC");
				ncc.setDiaChi("diaChi");
				ncc.setEmail("email");
				ncc.setsDT("sDT");
				
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSNCC;
	}

	public Boolean themNCC(NhaCungCap ncc) {
		String sql = "insert into NhaCungCap values(?,?,?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, ncc.getMaNCC());
			stmt.setString(2, ncc.getTenNCC());
			stmt.setString(3, ncc.getDiaChi());
			stmt.setString(4, ncc.getEmail());
			stmt.setString(5, ncc.getsDT());
			
			try {
				n = stmt.executeUpdate();
				if (n > 0)
					return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteNCC(NhaCungCap ncc) {

		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = ketNoi.prepareStatement("delete from NhaCungCap where MaNCC= ?");
			stmt.setString(1, ncc.getMaNCC());
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

	public boolean update(String maNCC,NhaCungCap ncc) {
		PreparedStatement stmt = null;
		try {
			stmt = Conection.getKetNoi().prepareStatement("update NhaCungCap set TenNCC=?,DiaChi=?,Email=?,SDT=? where MaNCC=?");
			
			stmt.setString(1, ncc.getTenNCC());
			stmt.setString(2, ncc.getDiaChi());
			stmt.setString(3, ncc.getEmail());
			stmt.setString(4, ncc.getsDT());
			stmt.setString(5, ncc.getMaNCC());
			stmt.executeUpdate();
			
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public List<NhaCungCap> timKiemNCC(String tk, String vl) {
		List<NhaCungCap> dSLK = new ArrayList<NhaCungCap>();
		PreparedStatement stmt = null;
		String sql = "select * from NhaCungCap where " + tk + " like N'%" + vl + "%'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNCC(rs.getString("MaNCC"));

				ncc.setTenNCC(rs.getString("TenNCC"));

				ncc.setDiaChi(rs.getString("DiaChi"));
				ncc.setEmail(rs.getString("Email"));
				ncc.setsDT(rs.getString("SDT"));

				dSLK.add(ncc);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSLK;
	}

	public static void main(String[] args) {
		NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
		System.out.println(nhaCungCapDAO.getAllNCC());
	}
}
