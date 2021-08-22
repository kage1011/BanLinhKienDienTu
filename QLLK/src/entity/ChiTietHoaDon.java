package entity;

public class ChiTietHoaDon {
	private int soLuong;
	private HoaDon hoaDon;
	private LinhKien linhKien;

	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietHoaDon( HoaDon hoaDon, LinhKien linhKien,int soLuong) {
		super();
		this.soLuong = soLuong;
		this.hoaDon = hoaDon;
		this.linhKien = linhKien;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public LinhKien getLinhKien() {
		return linhKien;
	}

	public void setLinhKien(LinhKien linhKien) {
		this.linhKien = linhKien;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [soLuong=" + soLuong + ", hoaDon=" + hoaDon + ", linhKien=" + linhKien + "]";
	}
	
	public double tinhThanhTien(int soLuong, LinhKien linhKien) {
		double thanhTien = 0;
		thanhTien = soLuong * linhKien.getGiaBan()+(soLuong * linhKien.getGiaBan()*tinhThue(linhKien)) ;
		return thanhTien;
	}
	public double tinhThue(LinhKien lk) {
		double thue=0;
		thue=lk.getThue()/100 ;
		return thue;
	}

}
