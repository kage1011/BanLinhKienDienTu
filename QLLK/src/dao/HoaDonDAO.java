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


public class HoaDonDAO {
	private Connection ketNoi;

	public HoaDonDAO() {
		// TODO Auto-generated constructor stub
		ketNoi = Conection.getKetNoi();
	}

	public List<HoaDon> getAllHoaDon() {
		List<HoaDon> dsHD = new ArrayList<HoaDon>();
		PreparedStatement stmt = null;
		String sql = "select MaHD, hd.MaNV, TenNV, hd.MaKH ,TenKH, NgayLap\r\n"
				+ "from HoaDon hd join KhachHang kh on hd.MaKH=kh.MaKH\r\n"
				+ "join NhanVien nv on hd.MaNV=nv.MaNV";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon();
				NhanVien nv = new NhanVien();
				KhachHang kh = new KhachHang();
				
				hd.setMaHD(rs.getString("MaHD"));
				nv.setMaNV(rs.getString("MaNV"));
				nv.setTenNV(rs.getString("TenNV"));
				kh.setMaKH(rs.getString("MaKH"));
				kh.setTenKH(rs.getString("TenKH"));
				hd.setNgayLapHD(rs.getDate("NgayLap"));
				
				hd.setNhanVien(nv);
				hd.setKhachHang(kh);
				dsHD.add(hd);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHD;
	}
	public List<HoaDon> search(String tk, String vl) {
		List<HoaDon> dsHD = new ArrayList<HoaDon>();
		PreparedStatement stmt = null;
		String sql = "select MaHD, hd.MaNV, TenNV, hd.MaKH ,TenKH, NgayLap \r\n"
				+ "from HoaDon hd join KhachHang kh on hd.MaKH=kh.MaKH \r\n"
				+ "join NhanVien nv on hd.MaNV=nv.MaNV \r\n"
				+ "where "+tk+" like N'%"+vl+"%'";
		
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon();
				NhanVien nv = new NhanVien();
				KhachHang kh = new KhachHang();
				
				hd.setMaHD(rs.getString("MaHD"));
				nv.setMaNV(rs.getString("MaNV"));
				nv.setTenNV(rs.getString("TenNV"));
				kh.setMaKH(rs.getString("MaKH"));
				kh.setTenKH(rs.getString("TenKH"));
				hd.setNgayLapHD(rs.getDate("NgayLap"));
				
				hd.setNhanVien(nv);
				hd.setKhachHang(kh);
				dsHD.add(hd);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHD;
	}

	
	public Boolean themHoaDon(HoaDon hoaDon) {
		String sql = "insert into HoaDon values(?,?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, hoaDon.getMaHD());
			stmt.setString(2, hoaDon.getNhanVien().getMaNV());
			stmt.setString(3, hoaDon.getKhachHang().getMaKH());
			stmt.setDate(4, hoaDon.getNgayLapHD());
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
	
	public HoaDon getHD(String maHD) throws Exception {
		HoaDon hd = new HoaDon();
		PreparedStatement stmt = null;
		String sql = "select MaHD, hd.MaNV, TenNV, hd.MaKH ,TenKH, NgayLap from HoaDon hd join KhachHang kh on hd.MaKH=kh.MaKH   join NhanVien nv on hd.MaNV=nv.MaNV where  hd.MaHD = ?";
		try {
			stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			rs.next();

			NhanVien nv = new NhanVien();
			KhachHang kh = new KhachHang();

			hd.setMaHD(rs.getString("MaHD"));
			nv.setMaNV(rs.getString("MaNV"));
			nv.setTenNV(rs.getString("TenNV"));
			kh.setMaKH(rs.getString("MaKH"));
			kh.setTenKH(rs.getString("TenKH"));
			hd.setNgayLapHD(rs.getDate("NgayLap"));

			hd.setNhanVien(nv);
			hd.setKhachHang(kh);
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hd;
	}

	public List<ChiTietHoaDon> getAllChiTietHoaDon(String maHD) {
		List<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		String sql = "select lk.MaLK, TenLK, SoLuong, lk.GiaBan, BaoHanh,Thue\r\n"
				+ "from ChiTietHoaDon ct join LinhKien lk on ct.MaLK = lk.MaLK \r\n" + "where ct.MaHD = '" + maHD + "'";
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
	
}
