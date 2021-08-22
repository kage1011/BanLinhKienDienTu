package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;

import entity.NhanVien;


public class NhanVienDAO {
	private Connection ketNoi;

	
	public NhanVienDAO() {
		// TODO Auto-generated constructor stub
		ketNoi=Conection.getKetNoi();
	}
	public List<NhanVien> getAllNV() {
		List<NhanVien> dSNV = new ArrayList<NhanVien>();
		PreparedStatement stmt = null;
		String sql = "select * from NhanVien";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString("MaNV"));
				nv.setTenNV(rs.getString("TenNV"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setDiaChi(rs.getString("DiaChi"));
				nv.setsDT(rs.getString("SDT"));
				nv.setMatKhau(rs.getString("MatKhau"));
				nv.setQuyen(rs.getString("Quyen"));
				dSNV.add(nv);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSNV;
	}
	public List<NhanVien> getAllNV(String maNV) throws Exception {
		List<NhanVien> dskh = new ArrayList<NhanVien>();
		try {
			PreparedStatement stmt = ketNoi.prepareStatement("select * from NhanVien where MaNV=?");
			stmt.setString(1, maNV);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien lk = new NhanVien();
				lk.setMaNV(rs.getString("MaNV"));
				lk.setTenNV(rs.getString("TenNV"));
				lk.setGioiTinh(rs.getString("GioiTinh"));
				lk.setNgaySinh(rs.getDate("NgaySinh"));
				lk.setDiaChi(rs.getString("DiaChi"));
				lk.setsDT(rs.getString("SDT"));
				lk.setMaNV(rs.getString("MatKhau"));
				lk.setQuyen(rs.getString("Quyen"));
				dskh.add(lk);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}

		return dskh;
	}
	public boolean themNV(NhanVien nv) throws SQLException {
		String sql = "insert into NhanVien values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);

			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setString(3, nv.getGioiTinh());
			stmt.setDate(4, nv.getNgaySinh());
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(6, nv.getsDT());
			stmt.setString(7, nv.getMatKhau());
			stmt.setString(8, nv.getQuyen());

			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		return false;
	}

	public boolean xoaNV(NhanVien nv) throws SQLException {
		try {
			PreparedStatement stmt = ketNoi.prepareStatement("delete from NhanVien where MaNV = ?");
			stmt.setString(1, nv.getMaNV());
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateNV(String maNV, NhanVien nv) {

		try {
			PreparedStatement stmt = ketNoi.prepareStatement("update NhanVien set TenNV = ?, GioiTinh=?,NgaySinh=?,DiaChi=?,SDT= ?,MatKhau=?,Quyen=? where MaNV = ?");
			stmt.setString(1, nv.getTenNV());
			stmt.setString(2, nv.getGioiTinh());
			stmt.setDate(3, nv.getNgaySinh());
			stmt.setString(4, nv.getDiaChi());
			stmt.setString(5, nv.getsDT());
			stmt.setString(6, nv.getMatKhau());
			stmt.setString(7, nv.getQuyen());
			stmt.setString(8, nv.getMaNV());
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	public NhanVien getNhanVien(String maNV) {
		NhanVien nv = new NhanVien();
		PreparedStatement stmt = null;
		String sql = "select * from nhanvien where manv='"+maNV+"'" ;
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				nv.setMaNV(rs.getString("MaNV"));
				nv.setTenNV(rs.getString("TenNV"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setDiaChi(rs.getString("DiaChi"));
				nv.setsDT(rs.getString("SDT"));
				nv.setMatKhau(rs.getString("MatKhau"));
				nv.setQuyen(rs.getString("Quyen"));
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	public List<NhanVien> timKiemNV(String tk, String vl) {
		List<NhanVien> dSLK = new ArrayList<NhanVien>();
		PreparedStatement stmt = null;
		String sql = "select * from NhanVien where "+tk+" like N'%"+vl+"%'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString("MaNV"));
				nv.setTenNV(rs.getString("TenNV"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setDiaChi(rs.getString("DiaChi"));
				nv.setsDT(rs.getString("SDT"));
				nv.setMatKhau(rs.getString("MatKhau"));
				nv.setQuyen(rs.getString("Quyen"));
				dSLK.add(nv);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSLK;
	}
}
