package entity;

import java.sql.Date;
import java.util.Arrays;




public class LinhKien {
	private String maLK;
	private String tenLK;
	private double giaBan;
	private String moTa;
	private String baoHanh;
	private Date ngayNhap;
	private int soLuongTon;
	private String mauSac;
	private String trongLuong;
	private float thue;
	private double giaNhap;
	private ThuongHieu thuongHieu;
	private NhaCungCap nhaCungCap;
	private LoaiLinhKien loaiLoaiKien;

	private byte[] anh = new byte[1024];

	public LinhKien(String maLK) {
		super();
		this.maLK = maLK;
	}

	public LinhKien(String maLK, String tenLK, double giaBan, String moTa, String baoHanh, Date ngayNhap,
			int soLuongTon, String mauSac, String trongLuong,float thue,double giaNhap,byte[] anh) {
		super();
		this.maLK = maLK;
		this.tenLK = tenLK;
		this.giaBan = giaBan;
		this.moTa = moTa;
		this.baoHanh = baoHanh;
		this.ngayNhap = ngayNhap;
		this.soLuongTon = soLuongTon;
		this.mauSac = mauSac;
		this.trongLuong = trongLuong;
		this.thue=thue;
		this.giaNhap=giaNhap;
		this.anh = anh;
	}

	public LinhKien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LinhKien(String maLK, String tenLK, double giaBan, String moTa, String baoHanh, Date ngayNhap,
			int soLuongTon, String mauSac, String trongLuong, float thue, double giaNhap, ThuongHieu thuongHieu,
			NhaCungCap nhaCungCap, LoaiLinhKien loaiLoaiKien, byte[] anh) {
		super();
		this.maLK = maLK;
		this.tenLK = tenLK;
		this.giaBan = giaBan;
		this.moTa = moTa;
		this.baoHanh = baoHanh;
		this.ngayNhap = ngayNhap;
		this.soLuongTon = soLuongTon;
		this.mauSac = mauSac;
		this.trongLuong = trongLuong;
		this.thue = thue;
		this.giaNhap = giaNhap;
		this.thuongHieu = thuongHieu;
		this.nhaCungCap = nhaCungCap;
		this.loaiLoaiKien = loaiLoaiKien;
		this.anh = anh;
	}

	@Override
	public String toString() {
		return "LinhKien [maLK=" + maLK + ", tenLK=" + tenLK + ", giaBan=" + giaBan + ", moTa=" + moTa + ", baoHanh="
				+ baoHanh + ", ngayNhap=" + ngayNhap + ", soLuongTon=" + soLuongTon + ", mauSac=" + mauSac
				+ ", trongLuong=" + trongLuong + ", thue=" + thue + ", giaNhap=" + giaNhap + ", thuongHieu="
				+ thuongHieu + ", nhaCungCap=" + nhaCungCap + ", loaiLoaiKien=" + loaiLoaiKien + ", anh="
				+ Arrays.toString(anh) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLK == null) ? 0 : maLK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinhKien other = (LinhKien) obj;
		if (maLK == null) {
			if (other.maLK != null)
				return false;
		} else if (!maLK.equals(other.maLK))
			return false;
		return true;
	}

	public String getMaLK() {
		return maLK;
	}

	public void setMaLK(String maLK) {
		this.maLK = maLK;
	}

	public String getTenLK() {
		return tenLK;
	}

	public void setTenLK(String tenLK) {
		this.tenLK = tenLK;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getBaoHanh() {
		return baoHanh;
	}

	public void setBaoHanh(String baoHanh) {
		this.baoHanh = baoHanh;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public String getMauSac() {
		return mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public String getTrongLuong() {
		return trongLuong;
	}

	public void setTrongLuong(String trongLuong) {
		this.trongLuong = trongLuong;
	}

	public float getThue() {
		return thue;
	}

	public void setThue(float thue) {
		this.thue = thue;
	}

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public ThuongHieu getThuongHieu() {
		return thuongHieu;
	}

	public void setThuongHieu(ThuongHieu thuongHieu) {
		this.thuongHieu = thuongHieu;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public LoaiLinhKien getLoaiLoaiKien() {
		return loaiLoaiKien;
	}

	public void setLoaiLoaiKien(LoaiLinhKien loaiLoaiKien) {
		this.loaiLoaiKien = loaiLoaiKien;
	}

	public byte[] getAnh() {
		return anh;
	}

	public void setAnh(byte[] anh) {
		this.anh = anh;
	}

	public LinhKien(String maLK, String tenLK, double giaBan, String moTa, String baoHanh, Date ngayNhap,
			int soLuongTon, String mauSac, String trongLuong, ThuongHieu thuongHieu, NhaCungCap nhaCungCap,
			LoaiLinhKien loaiLoaiKien) {
		super();
		this.maLK = maLK;
		this.tenLK = tenLK;
		this.giaBan = giaBan;
		this.moTa = moTa;
		this.baoHanh = baoHanh;
		this.ngayNhap = ngayNhap;
		this.soLuongTon = soLuongTon;
		this.mauSac = mauSac;
		this.trongLuong = trongLuong;
		this.thuongHieu = thuongHieu;
		this.nhaCungCap = nhaCungCap;
		this.loaiLoaiKien = loaiLoaiKien;
	}

	public LinhKien(String maLK, String tenLK, double giaBan, String moTa, String baoHanh, Date ngayNhap,
			int soLuongTon, String mauSac, String trongLuong, float thue, double giaNhap, ThuongHieu thuongHieu,
			NhaCungCap nhaCungCap, LoaiLinhKien loaiLoaiKien) {
		super();
		this.maLK = maLK;
		this.tenLK = tenLK;
		this.giaBan = giaBan;
		this.moTa = moTa;
		this.baoHanh = baoHanh;
		this.ngayNhap = ngayNhap;
		this.soLuongTon = soLuongTon;
		this.mauSac = mauSac;
		this.trongLuong = trongLuong;
		this.thue = thue;
		this.giaNhap = giaNhap;	
		this.thuongHieu = thuongHieu;
		this.nhaCungCap = nhaCungCap;
		this.loaiLoaiKien = loaiLoaiKien;
	}
	
	
	
	
	
	
	
}
