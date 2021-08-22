package entity;

public class ThuongHieu {
	private String maTH;
	private String tenTH;
	private String xuatXu;
	public String getMaTH() {
		return maTH;
	}
	public void setMaTH(String maTH) {
		this.maTH = maTH;
	}
	public String getTenTH() {
		return tenTH;
	}
	public void setTenTH(String tenTH) {
		this.tenTH = tenTH;
	}
	public String getXuatXu() {
		return xuatXu;
	}
	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}
	@Override
	public String toString() {
		return "ThuongHieu [maTH=" + maTH + ", tenTH=" + tenTH + ", xuatXu=" + xuatXu + "]";
	}
	public ThuongHieu(String maTH, String tenTH, String xuatXu) {
		super();
		this.maTH = maTH;
		this.tenTH = tenTH;
		this.xuatXu = xuatXu;
	}
	public ThuongHieu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ThuongHieu(String maTH) {
		super();
		this.maTH = maTH;
	}
	
	
}
