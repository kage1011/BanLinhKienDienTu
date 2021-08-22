package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;



public class ChiTietHoaDonDAO {
	private Connection ketNoi;

	public ChiTietHoaDonDAO() {
		// TODO Auto-generated constructor stub
		ketNoi = Conection.getKetNoi();
	}

	public List<ChiTietHoaDon> getAllChiTietHoaDon(String maHD) {
		List<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		String sql = "select lk.MaLK, TenLK, SoLuong, lk.GiaBan, BaoHanh,Thue\r\n"
				+ "from ChiTietHoaDon ct join LinhKien lk on ct.MaLK = lk.MaLK \r\n"
				+ "where ct.MaHD = '"+maHD+"'";
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
				LinhKien lk = new LinhKien();
				
				lk.setMaLK(rs.getString("MaLK"));
				lk.setTenLK(rs.getString("TenLK"));
				chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));			
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setBaoHanh(rs.getString("BaoHanh"));
				lk.setThue(rs.getFloat("Thue"));
				
				chiTietHoaDon.setLinhKien(lk);
				dsCTHD.add(chiTietHoaDon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsCTHD;
	}
	
	
	
	
	public List<ChiTietHoaDon> getChiTietHoaDon(String maHD) {
		List<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		String sql = "select lk.MaLK, TenLK, SoLuong, lk.GiaBan, BaoHanh,Thue\r\n"
				+ "from ChiTietHoaDon ct join LinhKien lk on ct.MaLK = lk.MaLK \r\n"
				+ "where ct.MaHD = '"+maHD+"'";
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
				LinhKien lk = new LinhKien();
				
				lk.setMaLK(rs.getString("MaLK"));
				lk.setTenLK(rs.getString("TenLK"));
				chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));			
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setBaoHanh(rs.getString("BaoHanh"));
				lk.setThue(rs.getFloat("Thue"));
				chiTietHoaDon.setLinhKien(lk);
				dsCTHD.add(chiTietHoaDon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsCTHD;
	}

	public Boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		String sql = "insert into ChiTietHoaDon values(?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, chiTietHoaDon.getHoaDon().getMaHD());
			stmt.setString(2, chiTietHoaDon.getLinhKien().getMaLK());
			stmt.setInt(3, chiTietHoaDon.getSoLuong());
			

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
	public List<ChiTietHoaDon> getAll() {
		List<ChiTietHoaDon> dSKTDT = new ArrayList<ChiTietHoaDon>();
		PreparedStatement stmt = null;
		String sql = "select NgayLap, TenNV, TenKH, TenLK,giaban,lk.thue, ct.SoLuong\r\n"
				+ "from HoaDon hd join ChiTietHoaDon ct on ct.MaHD=hd.MaHD\r\n"
				+ "join KhachHang kh on kh.MaKH=hd.MaKH\r\n"
				+ "join LinhKien lk on lk.MaLK=ct.MaLK\r\n"
				+ "join NhanVien nv on nv.MaNV=hd.MaNV";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				ChiTietHoaDon cthd=new ChiTietHoaDon();
				HoaDon hd = new HoaDon();
				KhachHang kh = new KhachHang();
				LinhKien lk=new LinhKien();
				NhanVien nv=new NhanVien();
				
				hd.setNgayLapHD(rs.getDate("NgayLap"));
				nv.setTenNV(rs.getString("TenNV"));
				kh.setTenKH(rs.getString("TenKH"));
				lk.setTenLK(rs.getString("TenLK"));
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setThue(rs.getFloat("Thue"));
				cthd.setSoLuong(rs.getInt("SoLuong"));
				
				hd.setKhachHang(kh);
				hd.setNhanVien(nv);
				cthd.setHoaDon(hd);
				cthd.setLinhKien(lk);
				
				
				dSKTDT.add(cthd);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSKTDT;
	}
	
	public List<ChiTietHoaDon> ThongKeChoAdmin(String tennv, String nam, String thang) {
		List<ChiTietHoaDon> dstk = new ArrayList<ChiTietHoaDon>();
		PreparedStatement stmt = null;
		String sql = "select NgayLap, TenNV, TenKH, TenLK,lk.GiaBan,lk.Thue, ct.SoLuong\r\n" + 
				"from HoaDon hd join ChiTietHoaDon ct on ct.MaHD=hd.MaHD\r\n" + 
				"join KhachHang kh on kh.MaKH=hd.MaKH\r\n" + 
				"join LinhKien lk on lk.MaLK=ct.MaLK\r\n" + 
				"join NhanVien nv on nv.MaNV=hd.MaNV\r\n" + 
				"where TenNV like N'%"+tennv+"%'\r\n" + 
				"and Year(NgayLap) like N'%"+nam+"%' and  Month(NgayLap) like N'%"+thang+"%'\r\n" + 
				"ORDER BY Month(NgayLap)";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon cthd=new ChiTietHoaDon();
				HoaDon hd = new HoaDon();
				KhachHang kh = new KhachHang();
				LinhKien lk=new LinhKien();
				NhanVien nv=new NhanVien();
				
				hd.setNgayLapHD(rs.getDate("NgayLap"));
				nv.setTenNV(rs.getString("TenNV"));
				kh.setTenKH(rs.getString("TenKH"));
				lk.setTenLK(rs.getString("TenLK"));
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setThue(rs.getFloat("Thue"));
				cthd.setSoLuong(rs.getInt("SoLuong"));
				

				hd.setKhachHang(kh);
				hd.setNhanVien(nv);
				cthd.setHoaDon(hd);
				cthd.setLinhKien(lk);
				
				
				dstk.add(cthd);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dstk;
	}
	public List<ChiTietHoaDon> ThongKeChoUser(String tennv, String nam, String thang, String ngay) {
		List<ChiTietHoaDon> dstk = new ArrayList<ChiTietHoaDon>();
		PreparedStatement stmt = null;
		String sql = "select NgayLap, TenNV, TenKH, TenLK, giaban,lk.thue, ct.SoLuong\r\n"
				+ "from HoaDon hd join ChiTietHoaDon ct on ct.MaHD=hd.MaHD\r\n"
				+ "join KhachHang kh on kh.MaKH=hd.MaKH\r\n"
				+ "join LinhKien lk on lk.MaLK=ct.MaLK\r\n"
				+ "join NhanVien nv on nv.MaNV=hd.MaNV\r\n"
				+ "where TenNV like N'%"+tennv+"%' and Year(NgayLap) like N'%"+nam+"%' and  Month(NgayLap) like N'%"+thang+"%' and  Day(NgayLap) like N'%"+ngay+"%'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon cthd=new ChiTietHoaDon();
				HoaDon hd = new HoaDon();
				KhachHang kh = new KhachHang();
				LinhKien lk=new LinhKien();
				NhanVien nv=new NhanVien();
				
				hd.setNgayLapHD(rs.getDate("NgayLap"));
				nv.setTenNV(rs.getString("TenNV"));
				kh.setTenKH(rs.getString("TenKH"));
				lk.setTenLK(rs.getString("TenLK"));
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setThue(rs.getFloat("Thue"));
				cthd.setSoLuong(rs.getInt("SoLuong"));

				hd.setKhachHang(kh);
				hd.setNhanVien(nv);
				cthd.setHoaDon(hd);
				cthd.setLinhKien(lk);
				
				
				dstk.add(cthd);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dstk;
	}
}
