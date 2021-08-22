package ui;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

public class GenerateBillPdf {

	private HoaDonDAO daohd;
	private NhanVienDAO daonv;
	private KhachHangDAO daokh;
	private ChiTietHoaDonDAO cthddao;
	public GenerateBillPdf(String mahd) {
		HoaDon hd = new HoaDon();
		KhachHang kh = new KhachHang();
		NhanVien nv = new NhanVien();
		
		List<ChiTietHoaDon> dscthd = new ArrayList<ChiTietHoaDon>();
		DecimalFormat df = new DecimalFormat("###,###,###.#");

		daohd = new HoaDonDAO();
		daonv = new NhanVienDAO();
		daokh = new KhachHangDAO();
		cthddao = new ChiTietHoaDonDAO();
		
		String tach[]=mahd.split("-");
		String maHD=tach[0].trim();
		String txtTienKhachTra=tach[1].trim();
		String txtTienThoi=tach[2].trim();
		
		try {
			hd = daohd.getHD(maHD);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		kh = daokh.getKhachHang(hd.getKhachHang().getMaKH());
		nv = daonv.getNhanVien(hd.getNhanVien().getMaNV());
		dscthd = cthddao.getAllChiTietHoaDon(hd.getMaHD());

		Document doc = new Document();
		try {

			BaseFont bf1 = BaseFont.createFont("font/timesbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font f1 = new Font(bf1, 10);
			Font f2 = new Font(bf1, 13);

			PdfWriter write = PdfWriter.getInstance(doc,
					new FileOutputStream("D:/hoadon/" + maHD + "_" + kh.getMaKH() + ".pdf"));

			doc.open();
			Paragraph p1 = new Paragraph("DREAM TEAM - CỬA HÀNG LINH KIỆN MÁY TÍNH", f2);
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			Paragraph p2 = new Paragraph("PHIẾU XUẤT KHO BÁN HÀNG", f2);
			p2.setAlignment(Paragraph.ALIGN_CENTER);
			p2.setSpacingAfter(20f);

			doc.add(p1);
			doc.add(p2);
			doc.add(new Paragraph(String.format("Tên cửa hàng:%7sCỬA HÀNG LINH KIỆN MÁY TÍNH DREAM TEAM", ""), f1));
			doc.add(new Paragraph(String.format("Địa chỉ:%18sTầng 02, Cao ốc Newton Residence, Số 38 Trương Quốc Dung, Phường 08, Quận Phú Nhuận, TP.HCM", ""),
					f1));

			doc.add(new Paragraph("\n"));
			LocalTime time = LocalTime.now();
			String s1 = String.format("Số HĐ:%7s" + maHD + "%7s" + "Nhân viên lập:%3s" + nv.getTenNV(), "", "", "");
			String s2 = String.format(
					"Ngày lập:%3s" + hd.getNgayLapHD() + "%44s" + "Ngày in:%3s" + LocalDate.now() + "%3s%02d:%02d:%02d",
					"", "", "", "", time.getHour(), +time.getMinute(), +time.getSecond());
			String s3 = String.format("Mã KH:%6s" + kh.getMaKH() + "%7s" + "Tên KH:%3s" + kh.getTenKH() + "%5s"
					+ "SĐT:%3s" + kh.getsDT(), "", "", "", "", "");
			doc.add(new Paragraph(s1, f1));
			doc.add(new Paragraph(s2, f1));
			doc.add(new Paragraph("\n"));
			doc.add(new Paragraph(s3, f1));
			doc.add(new Paragraph(String.format("Địa chỉ:%7s" + kh.getDiaChi(), ""), f1));

			PdfPTable table = new PdfPTable(8);
			/**
			 * for table
			 */
			table.setWidthPercentage(100);
			table.setSpacingBefore(5f);
			table.setSpacingAfter(20f);

			float[] colwidth = { 0.75f, 1.5f, 3f, 0.75f, 1.5f, 0.75f, 1f, 1.75f };
			table.setWidths(colwidth);

			PdfPCell c0 = new PdfPCell(new Paragraph("STT", f1));
			PdfPCell c1 = new PdfPCell(new Paragraph("Mã SP", f1));
			PdfPCell c2 = new PdfPCell(new Paragraph("Tên SP", f1));
			PdfPCell c3 = new PdfPCell(new Paragraph("ĐVT", f1));
			PdfPCell c4 = new PdfPCell(new Paragraph("Đơn giá (VNĐ)", f1));
			PdfPCell c5 = new PdfPCell(new Paragraph("Số lượng", f1));
			PdfPCell c6 = new PdfPCell(new Paragraph("Thuế(%)", f1));
			PdfPCell c7 = new PdfPCell(new Paragraph("Thành tiền (VNĐ)", f1));

			c0.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c7.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);


			c0.setFixedHeight(30);
			c0.setBorderWidthRight(0f);
			c1.setBorderWidthRight(0f);
			c2.setBorderWidthRight(0f);
			c3.setBorderWidthRight(0f);
			c4.setBorderWidthRight(0f);
			c5.setBorderWidthRight(0f);
			c6.setBorderWidthRight(0f);
			c7.setBorderWidthRight(0.5f);

			table.addCell(c0);
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			table.addCell(c4);
			table.addCell(c5);
			table.addCell(c6);
			table.addCell(c7);

			int i = 1;
			for (ChiTietHoaDon ct : dscthd) {
				LinhKien s = ct.getLinhKien();
				PdfPCell c8 = new PdfPCell(new Paragraph(i++ + "", f1));
				PdfPCell c9 = new PdfPCell(new Paragraph(s.getMaLK(), f1));
				PdfPCell c10 = new PdfPCell(new Paragraph(s.getTenLK(), f1));
				PdfPCell c11 = new PdfPCell(new Paragraph("Cái", f1));
				PdfPCell c12 = new PdfPCell(new Paragraph(df.format(ct.getLinhKien().getGiaBan()), f1));
				PdfPCell c13 = new PdfPCell(new Paragraph(ct.getSoLuong() + "", f1));
				PdfPCell c14 = new PdfPCell(new Paragraph(df.format(ct.getLinhKien().getThue()), f1));
				PdfPCell c15 = new PdfPCell(
						new Paragraph(df.format(ct.tinhThanhTien(ct.getSoLuong(), ct.getLinhKien())), f1));

				c8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c9.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c10.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c12.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c13.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c15.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

				c8.setFixedHeight(30f);

				c8.setBorderWidthTop(0f);
				c8.setBorderWidthRight(0f);
				c9.setBorderWidthTop(0f);
				c9.setBorderWidthRight(0f);
				c10.setBorderWidthTop(0f);
				c10.setBorderWidthRight(0f);
				c11.setBorderWidthTop(0f);
				c11.setBorderWidthRight(0f);
				c12.setBorderWidthTop(0f);
				c12.setBorderWidthRight(0f);
				c13.setBorderWidthTop(0f);
				c13.setBorderWidthRight(0f);
				c14.setBorderWidthTop(0f);
				c15.setBorderWidthTop(0f);


				table.addCell(c8);
				table.addCell(c9);
				table.addCell(c10);
				table.addCell(c11);
				table.addCell(c12);
				table.addCell(c13);
				table.addCell(c14);
				table.addCell(c15);


			}
			doc.add(new Paragraph("\n"));
			doc.add(table);
			
			doc.add(new Paragraph(String.format("----------------------------------------------------------------------------------------------------------------------------------")));
			
			doc.add(new Paragraph(
					String.format("%-30s %-136s" + df.format(hd.tinhTongTien(dscthd)) + " vnd", "Tổng tiền:", ""),
					f1));
			doc.add(new Paragraph(String.format("%-30s %-130s" + txtTienKhachTra + " vnd",
					"Tổng khách đưa:", ""), f1));
			doc.add(new Paragraph(String.format("----------------------------------------------------------------------------------------------------------------------------------")));
			
			doc.add(new Paragraph(String.format("%-30s %-140s" + txtTienThoi + " vnd",
					"Còn lại:", ""), f1));
			
			doc.add(new Paragraph(String.format("%s", "Giá trên đã bao gồm thuế VAT."), f1));
			doc.add(new Paragraph(String.format("----------------------------------------------------------------------------------------------------------------------------------")));
			
			doc.add(new Paragraph("\n\n"));
			doc.add(new Paragraph(String.format("%8sNgười mua hàng %120s   Người bán hàng", "", ""), f1));
			doc.add(new Paragraph(String.format("%5s(Ký, ghi rõ họ tên) %120s (Ký, ghi rõ họ tên)", "", ""), f1));
			doc.add(new Paragraph("\n\n\n"));
			doc.add(new Paragraph(String.format("%8s %-30s %110s %-30s", "", kh.getTenKH(), "", nv.getTenNV()), f1));
			doc.add(new Paragraph("\n"));
			Paragraph p3 = new Paragraph(
					"Quý khách vui lòng kiểm tra kỹ hàng hóa và hóa đơn trước khi rời khỏi cửa hàng.", f1);
			p3.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(p3);

			doc.close();
			write.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Không thể tạo hóa đơn");
			e.printStackTrace();
		}
	}
}