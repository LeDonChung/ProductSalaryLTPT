package com.product.salary.application.utils.pdf;

import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.itextpdf.text.Font;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.product.salary.application.utils.PriceFormatterUtils;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HopDongPdfUtils {
	// Tài liệu là 1 file pdf
	static Document document;
	// Ghi file
	static FileOutputStream file;
	static Font fontData;
	static Font fontTitle;
	static Font fontHeader;
	// Dialog choose file type SAVE
	static FileDialog fd = new FileDialog(new JFrame(), "Xuất danh sách lương công nhân", FileDialog.SAVE);

	private static void getFont() {
		try {
			fontData = new Font(
					BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10,
					Font.NORMAL);
			fontTitle = new Font(
					BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 20,
					Font.NORMAL);
			fontHeader = new Font(
					BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11,
					Font.NORMAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Thiết lập title
	 * 
	 * @param title
	 */
	public static void setTitle(String title) {
		try {
			Paragraph pdfTitle1 = new Paragraph(new Phrase("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM", fontHeader));
			pdfTitle1.setAlignment(Element.ALIGN_CENTER);
			Paragraph pdfTitle2 = new Paragraph(new Phrase("Độc lập - Tự do- Hạnh phúc", fontHeader));
			pdfTitle2.setAlignment(Element.ALIGN_CENTER);
			Paragraph pdfTitle3 = new Paragraph(new Phrase("*****************", fontHeader));
			pdfTitle3.setAlignment(Element.ALIGN_CENTER);
			document.add(Chunk.NEWLINE);
			Paragraph pdfTitle4 = new Paragraph(new Phrase(title, fontTitle));
			pdfTitle4.setAlignment(Element.ALIGN_CENTER);

			document.add(pdfTitle1);
			document.add(pdfTitle2);
			document.add(pdfTitle3);
			document.add(pdfTitle4);
			document.add(Chunk.NEWLINE);
		} catch (DocumentException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Lấy đường dẫn file
	 * 
	 * @return
	 */
	private static String getFile() {
		// default
		fd.setFile("untitled.pdf");
		fd.setVisible(true);
		String url = fd.getDirectory() + fd.getFile();
		if (url.equals("nullnull")) {
			return null;
		}
		return url;
	}

	/**
	 * Thực hiện chức năng xuất thông tin hợp đồng
	 * 
	 * @param id
	 */
	public static void writeHopDong(HopDong hopDong, List<ChiTietHopDong> chiTietHopDongs) {
		String url = "";
		try {
			fd.setTitle("Thanh lý hợp đồng");
			url = getFile();
			if (url == null) {
				return;
			}

			// Font
			getFont();

			// Open
			file = new FileOutputStream(url);
			document = new Document();
			PdfWriter.getInstance(document, file);

			document.open();

			// Title
			setTitle(String.format("BIÊN BẢN HỢP ĐỒNG"));

			// Khoang trong giua hang
			Chunk glue = new Chunk(new VerticalPositionMark());

			// Can cu
			getCanCu();
			document.add(Chunk.NEWLINE);
			// Thong tin hop dong
			getThongTinHopDong(hopDong);
			document.add(Chunk.NEWLINE);

			Paragraph chiTietHopDong = new Paragraph(new Phrase("CHI TIẾT HỢP ĐÔNG", new Font(
					BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10,
					Font.BOLD)));

			document.add(chiTietHopDong);
			document.add(Chunk.NEWLINE);

			// Tao table cho cac chi tiet hợp dồng
			PdfPTable pdfTable = new PdfPTable(5);

			// Set headers cho table chi tiet
			pdfTable.addCell(new PdfPCell(new Phrase("STT", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Mã sản phẩm", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Giá đặt làm", fontHeader)));
			pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			int row = 1;
			for (ChiTietHopDong v : chiTietHopDongs) {
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%d", row++), fontData)));
				pdfTable.addCell(
						new PdfPCell(new Phrase(String.format("%s", v.getSanPham().getMaSanPham()), fontData)));
				pdfTable.addCell(
						new PdfPCell(new Phrase(String.format("%s", v.getSanPham().getTenSanPham()), fontData)));
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%s", v.getSoLuong()), fontData)));
				pdfTable.addCell(new PdfPCell(
						new Phrase(String.format("%s", PriceFormatterUtils.format(v.getGiaDatLam())), fontData)));
			}

			document.add(pdfTable);

			document.add(Chunk.NEWLINE);
			Paragraph date = new Paragraph();
			date.setFont(fontData);
			date.add(glue);
			date.add("Ngày....tháng....năm....");
			document.add(date);

			document.add(Chunk.NEWLINE);
			Paragraph footer = new Paragraph();
			footer.setFont(fontData);
			footer.add("                Giám đốc");
			document.add(footer);

			document.close();

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

		} catch (DocumentException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void getThongTinHopDong(HopDong hopDong) {
		try {
			// Khoang trong giua hang
			Chunk glue = new Chunk(new VerticalPositionMark());

			Paragraph paragraph1 = new Paragraph(new Phrase("THÔNG TIN HỢP ĐỒNG", new Font(
					BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10,
					Font.BOLD)));
			Paragraph paragraph2 = new Paragraph();
			paragraph2.setFont(fontData);
			paragraph2.add("Mã hợp đồng: " + hopDong.getMaHopDong());
			paragraph2.add(glue);
			paragraph2.add("Tên khách hàng: " + hopDong.getTenKhachHang());

			Paragraph paragraph3 = new Paragraph();
			paragraph3.setFont(fontData);
			paragraph3.add("Tên hợp đồng: " + hopDong.getTenHopDong());
			paragraph3.add(glue);
			paragraph3.add("Ngày kết thúc: " + hopDong.getNgayKetThuc());

			Paragraph paragraph4 = new Paragraph();
			paragraph4.setFont(fontData);
			paragraph4.add("Ngày bắt đầu: " + hopDong.getNgayBatDau());
			paragraph4.add(glue);
			paragraph4.add("Trạng thái: " + (hopDong.isTrangThai() ? "Đã thanh lý" : "Chưa thanh lý"));

			Paragraph paragraph5 = new Paragraph();
			paragraph5.setFont(fontData);
			paragraph5.add("Số tiền cọc: " + PriceFormatterUtils.format(hopDong.getSoTienCoc()));
			paragraph5.add(glue);
			paragraph5.add("Tổng tiền: " + PriceFormatterUtils.format(hopDong.getTongTien()));

			Paragraph paragraph6 = new Paragraph();
			paragraph6.setFont(fontData);
			paragraph6.add("Số tiền phải thanh toán: "
					+ PriceFormatterUtils.format(hopDong.getTongTien() - hopDong.getSoTienCoc()));

			Paragraph paragraph7 = new Paragraph();
			paragraph7.setFont(fontData);
			paragraph7.add("Yêu cầu: " + hopDong.getYeuCau());
			document.add(paragraph1);
			document.add(paragraph2);
			document.add(paragraph3);
			document.add(paragraph4);
			document.add(paragraph5);
			document.add(paragraph6);
			document.add(paragraph7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getCanCu() {
		try {
			// Khoang trong giua hang
			Chunk glue = new Chunk(new VerticalPositionMark());
			Paragraph paragraph1 = new Paragraph(new Phrase("– Căn cứ Bộ Luật Dân sự 2015", fontHeader));
			Paragraph paragraph2 = new Paragraph(new Phrase("– Căn cứ Luật Thương mại 2005", fontHeader));
			Paragraph paragraph3 = new Paragraph(
					new Phrase("– Căn cứ vào các văn bản và quy định pháp luật có liên quan", fontHeader));

			document.add(paragraph1);
			document.add(paragraph2);
			document.add(paragraph3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeFile() {
		document.close();
	}
}
