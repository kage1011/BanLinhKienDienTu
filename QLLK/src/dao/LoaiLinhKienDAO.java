package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.LoaiLinhKien;

public class LoaiLinhKienDAO {

	private Connection ketNoi;

	public LoaiLinhKienDAO() {
		// TODO Auto-generated constructor stub
		ketNoi = Conection.getKetNoi();
	}
	public List<LoaiLinhKien> getAllLLK() {
		List<LoaiLinhKien> dSLLK = new ArrayList<LoaiLinhKien>();
		PreparedStatement stmt = null;
		String sql = "select * from LoaiLinhKien";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				LoaiLinhKien llk = new LoaiLinhKien();
				llk.setMaLoaiLK(rs.getString("MaLoaiLK"));
				llk.setTenLoaiLK(rs.getString("TenLoaiLK"));
				dSLLK.add(llk);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSLLK;
	}
	public static void main(String[] args) {
		LoaiLinhKienDAO loaiLinhKienDao = new LoaiLinhKienDAO();
		System.out.println(loaiLinhKienDao.getAllLLK());
	}
}
