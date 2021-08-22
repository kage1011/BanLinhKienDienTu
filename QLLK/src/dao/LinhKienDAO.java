package dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conection;
import entity.LinhKien;
import entity.LoaiLinhKien;
import entity.NhaCungCap;

import entity.ThuongHieu;


public class LinhKienDAO {
	private Connection ketNoi;

	public LinhKienDAO() {
		// TODO Auto-generated constructor stub
		ketNoi = Conection.getKetNoi();
	}

	public List<LinhKien> phanTrang() {
		List<LinhKien> dSLK = new ArrayList<LinhKien>();
		PreparedStatement stmt = null;
		String sql = "select top 8 malk,tenlk,giaban,mota,baohanh,ngaynhap,soluongton,mausac,trongluong,TenNCC,TenLoaiLK,XuatXu,TenTH\r\n"
				+ "from linhkien lk join NhaCungCap ncc on lk.MaNCC=ncc.MaNCC\r\n"
				+ "join ThuongHieu th on lk.MaTH=th.MaTH\r\n"
				+ "join LoaiLinhKien llk on lk.MaLoaiLK=llk.MaLoaiLK";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				LinhKien lk = new LinhKien();
				NhaCungCap ncc=new NhaCungCap();
				LoaiLinhKien llk=new LoaiLinhKien();
				ThuongHieu th=new ThuongHieu();

				lk.setMaLK(rs.getString("MaLK"));
				lk.setTenLK(rs.getString("TenLK"));
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setMoTa(rs.getString("MoTa"));
				lk.setBaoHanh(rs.getString("BaoHanh"));
				lk.setNgayNhap(rs.getDate("NgayNhap"));
				lk.setSoLuongTon(rs.getInt("SoLuongTon"));
				lk.setMauSac(rs.getString("MauSac"));
				lk.setTrongLuong(rs.getString("TrongLuong"));
				th.setTenTH(rs.getString("TenTH"));
				ncc.setTenNCC(rs.getString("TenNCC"));
				llk.setTenLoaiLK(rs.getString("TenLoaiLK"));
				
				
				lk.setNhaCungCap(ncc);
				lk.setLoaiLoaiKien(llk);
				lk.setThuongHieu(th);
				
				dSLK.add(lk);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSLK;
	}

	public List<LinhKien> getAllLinhKien() throws IOException {
		List<LinhKien> dSLK = new ArrayList<LinhKien>();
		PreparedStatement stmt = null;
		String sql = "select malk,tenlk,giaban,mota,baohanh,ngaynhap,soluongton,mausac,trongluong,thue,GiaNhap,TenNCC,TenLoaiLK,XuatXu,TenTH,anh\r\n"
				+ "from linhkien lk join NhaCungCap ncc on lk.MaNCC=ncc.MaNCC\r\n"
				+ "join ThuongHieu th on lk.MaTH=th.MaTH\r\n"
				+ "join LoaiLinhKien llk on lk.MaLoaiLK=llk.MaLoaiLK";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				LinhKien lk = new LinhKien();
				NhaCungCap ncc=new NhaCungCap();
				LoaiLinhKien llk=new LoaiLinhKien();
				ThuongHieu th=new ThuongHieu();

				lk.setMaLK(rs.getString("MaLK"));
				lk.setTenLK(rs.getString("TenLK"));
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setMoTa(rs.getString("MoTa"));
				lk.setBaoHanh(rs.getString("BaoHanh"));
				lk.setNgayNhap(rs.getDate("NgayNhap"));
				lk.setSoLuongTon(rs.getInt("SoLuongTon"));
				lk.setMauSac(rs.getString("MauSac"));
				lk.setTrongLuong(rs.getString("TrongLuong"));
				lk.setThue(rs.getFloat("Thue"));
				lk.setGiaNhap(rs.getDouble("GiaNhap"));
				th.setTenTH(rs.getString("TenTH"));
				ncc.setTenNCC(rs.getString("TenNCC"));
				llk.setTenLoaiLK(rs.getString("TenLoaiLK"));
				
				ByteArrayOutputStream bos=new ByteArrayOutputStream(); 
				InputStream is = rs.getBinaryStream("anh");
				try {
					while (is.read(lk.getAnh()) > 0) {
						bos.write(lk.getAnh());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
					
				lk.setAnh(bos.toByteArray());
			
				lk.setNhaCungCap(ncc);
				lk.setLoaiLoaiKien(llk);
				lk.setThuongHieu(th);

				dSLK.add(lk);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSLK;
	}
	
	public LinhKien getLinhKien(String maLK) {
		PreparedStatement stmt = null;
		LinhKien lk = new LinhKien();
		String sql = "select * from LinhKien where MaLK='"+maLK+"'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				NhaCungCap ncc=new NhaCungCap();
				LoaiLinhKien llk=new LoaiLinhKien();
				ThuongHieu th=new ThuongHieu();

				lk.setMaLK(rs.getString("MaLK"));
				lk.setTenLK(rs.getString("TenLK"));
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setMoTa(rs.getString("MoTa"));
				lk.setBaoHanh(rs.getString("BaoHanh"));
				lk.setNgayNhap(rs.getDate("NgayNhap"));
				lk.setSoLuongTon(rs.getInt("SoLuongTon"));
				lk.setMauSac(rs.getString("MauSac"));
				lk.setTrongLuong(rs.getString("TrongLuong"));
				lk.setThue(rs.getFloat("Thue"));
				lk.setGiaNhap(rs.getDouble("GiaNhap"));
				th.setTenTH(rs.getString("TenTH"));
				ncc.setTenNCC(rs.getString("TenNCC"));
				llk.setTenLoaiLK(rs.getString("TenLoaiLK"));
				
				lk.setNhaCungCap(ncc);
				lk.setLoaiLoaiKien(llk);
				lk.setThuongHieu(th);
				
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lk;
	}
	
	public List<LinhKien> timKiemLinhKien(String tk,String vl) throws IOException {
		List<LinhKien> dSLK = new ArrayList<LinhKien>();
		PreparedStatement stmt = null;
		String sql = "select malk,tenlk,giaban,mota,baohanh,ngaynhap,soluongton,mausac,trongluong,thue,gianhap,TenNCC,TenLoaiLK,XuatXu,TenTH,anh,lk.MaTH,lk.MaNCC,lk.MaLoaiLK\r\n"
				+ "from linhkien lk join NhaCungCap ncc on lk.MaNCC=ncc.MaNCC\r\n"
				+ "join ThuongHieu th on lk.MaTH=th.MaTH\r\n"
				+ "join LoaiLinhKien llk on lk.MaLoaiLK=llk.MaLoaiLK\r\n"
				+ "where "+tk+" like N'%"+vl+"%' ";
		try {
		
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				LinhKien lk = new LinhKien();
				NhaCungCap ncc=new NhaCungCap();
				LoaiLinhKien llk=new LoaiLinhKien();
				ThuongHieu th=new ThuongHieu();

				lk.setMaLK(rs.getString("MaLK"));
				lk.setTenLK(rs.getString("TenLK"));
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setMoTa(rs.getString("MoTa"));
				lk.setBaoHanh(rs.getString("BaoHanh"));
				lk.setNgayNhap(rs.getDate("NgayNhap"));
				lk.setSoLuongTon(rs.getInt("SoLuongTon"));
				lk.setMauSac(rs.getString("MauSac"));
				lk.setTrongLuong(rs.getString("TrongLuong"));
				lk.setThue(rs.getFloat("Thue"));
				lk.setGiaNhap(rs.getDouble("GiaNhap"));
				th.setTenTH(rs.getString("TenTH"));
				ncc.setTenNCC(rs.getString("TenNCC"));
				llk.setTenLoaiLK(rs.getString("TenLoaiLK"));
				ByteArrayOutputStream bos=new ByteArrayOutputStream(); 
				InputStream is = rs.getBinaryStream("anh");
				while (is.read(lk.getAnh()) > 0) {
					bos.write(lk.getAnh());
				}	
				lk.setAnh(bos.toByteArray());
				th.setMaTH(rs.getString("MaTH"));
				ncc.setMaNCC(rs.getString("MaNCC"));
				llk.setMaLoaiLK(rs.getString("MaLoaiLK"));
				
				lk.setNhaCungCap(ncc);
				lk.setLoaiLoaiKien(llk);
				lk.setThuongHieu(th);
				
				dSLK.add(lk);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSLK;
	}
	
	public List<LinhKien> timKiemLinhKienban(String tk,String vl,String tk1,String vl1,String tk2,String vl2) throws IOException {
		List<LinhKien> dSLK = new ArrayList<LinhKien>();
		PreparedStatement stmt = null;
		String sql = "select malk,tenlk,giaban,mota,baohanh,ngaynhap,soluongton,mausac,trongluong,thue,gianhap,TenNCC,TenLoaiLK,XuatXu,TenTH,anh\r\n"
				+ "from linhkien lk join NhaCungCap ncc on lk.MaNCC=ncc.MaNCC\r\n"
				+ "join ThuongHieu th on lk.MaTH=th.MaTH\r\n"
				+ "join LoaiLinhKien llk on lk.MaLoaiLK=llk.MaLoaiLK\r\n"
				+ "where "+tk+" like N'%"+vl+"%' and "+tk1+" like N'%"+vl1+"%' and "+tk2+" like N'%"+vl2+"%'";
		try {
		
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				LinhKien lk = new LinhKien();
				NhaCungCap ncc=new NhaCungCap();
				LoaiLinhKien llk=new LoaiLinhKien();
				ThuongHieu th=new ThuongHieu();

				lk.setMaLK(rs.getString("MaLK"));
				lk.setTenLK(rs.getString("TenLK"));
				lk.setGiaBan(rs.getDouble("GiaBan"));
				lk.setMoTa(rs.getString("MoTa"));
				lk.setBaoHanh(rs.getString("BaoHanh"));
				lk.setNgayNhap(rs.getDate("NgayNhap"));
				lk.setSoLuongTon(rs.getInt("SoLuongTon"));
				lk.setMauSac(rs.getString("MauSac"));
				lk.setTrongLuong(rs.getString("TrongLuong"));
				lk.setThue(rs.getFloat("Thue"));
				lk.setGiaNhap(rs.getDouble("GiaNhap"));
				th.setTenTH(rs.getString("TenTH"));
				ncc.setTenNCC(rs.getString("TenNCC"));
				llk.setTenLoaiLK(rs.getString("TenLoaiLK"));
				
				ByteArrayOutputStream bos=new ByteArrayOutputStream(); 
				InputStream is = rs.getBinaryStream("anh");
				while (is.read(lk.getAnh()) > 0) {
					bos.write(lk.getAnh());
				}	
				lk.setAnh(bos.toByteArray());
				
				lk.setNhaCungCap(ncc);
				lk.setLoaiLoaiKien(llk);
				lk.setThuongHieu(th);
				
				dSLK.add(lk);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSLK;
	}

	public Boolean capNhatSoLuongLinhKien(int soLuong, String ma) {
		String sql = "update LinhKien set SoLuongTon -=" + soLuong + " where MaLK = '" + ma + "'";
		try {
			PreparedStatement statement = ketNoi.prepareStatement(sql);
			int n = statement.executeUpdate();
			if (n > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean themlk(LinhKien lk) throws ClassNotFoundException, SQLException,IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url="jdbc:sqlserver://localhost:1433;databaseName=QLLinhKien";
        Connection conn = DriverManager.getConnection(url, "sa", "sapassword");
        String sql="insert into linhkien values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int n;
        FileInputStream fis = null;

        try {
            conn.setAutoCommit(false);
            File file = new File("");
            fis = new FileInputStream(file);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, lk.getMaLK());
            stmt.setString(2, lk.getTenLK());
            stmt.setDouble(3,lk.getGiaBan());
            stmt.setString(4,lk.getMoTa());
            stmt.setString(5,lk.getBaoHanh());
            stmt.setDate(6,lk.getNgayNhap());
            stmt.setInt(7,lk.getSoLuongTon());
            stmt.setString(8,lk.getMauSac());
            stmt.setString(9,lk.getTrongLuong());
            stmt.setFloat(10,lk.getThue());
            stmt.setDouble(11, lk.getGiaNhap());
            stmt.setString(12,lk.getThuongHieu().getMaTH());
            stmt.setString(13,lk.getNhaCungCap().getMaNCC());
            stmt.setString(14,lk.getLoaiLoaiKien().getMaLoaiLK());
            
            stmt.setBinaryStream(15, fis, (int) file.length());
            try {
				n=stmt.executeUpdate();
				conn.commit();
				if(n>0)
					return true;
			} finally {
				stmt.close();
	            fis.close();
			}
        }catch (Exception e) {
        	e.printStackTrace();
		}
		return false;
	}

	public boolean deleteLK(LinhKien lk) {

		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = ketNoi.prepareStatement("delete from LinhKien where MaLK= ?");
			stmt.setString(1, lk.getMaLK());
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

	public boolean update(String malk,LinhKien lk) {
		PreparedStatement stmt = null;
		try {
			stmt = Conection.getKetNoi().prepareStatement("update LinhKien set TenLK=?,GiaBan=?,MoTa=?,BaoHanh=?,NgayNhap=?,SoLuongTon=?,MauSac=?,TrongLuong=?,MaNCC=? where MaLK=?");
			stmt.setString(1, lk.getMaLK());
			stmt.setString(2, lk.getTenLK());
			stmt.setDouble(3, lk.getGiaBan());
			stmt.setString(4, lk.getMoTa());
			stmt.setString(5, lk.getBaoHanh());
			stmt.setDate(6, lk.getNgayNhap());
			stmt.setInt(7, lk.getSoLuongTon());
			stmt.setString(8, lk.getMauSac());
			stmt.setString(9, lk.getTrongLuong());
			stmt.executeUpdate();
			ketNoi.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return true;

	}
	
	public int demSluongDuLieuTrongDB() {
		int dem = 0;
		String sql = "select count(MaLK) as Dem from LinhKien";
		try {
			PreparedStatement statement = ketNoi.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				dem = rs.getInt("Dem");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dem;
	}

	public static void main(String[] args) throws IOException {
		LinhKienDAO linhKienDAO = new LinhKienDAO();
		System.out.println(linhKienDAO.getAllLinhKien());
	}
}
