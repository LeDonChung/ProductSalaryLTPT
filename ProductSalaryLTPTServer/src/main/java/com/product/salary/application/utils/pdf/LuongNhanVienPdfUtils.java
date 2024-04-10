package com.product.salary.application.utils.pdf;

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
import java.util.Map;

public class LuongNhanVienPdfUtils {
	// Tài liệu là 1 file pdf
	static Document document;
	// Ghi file
	static FileOutputStream file;
	static Font fontData;
	static Font fontTitle;
	static Font fontHeader;

	// Dialog choose file type SAVE
	static FileDialog fileDialog = new FileDialog(new JFrame(), "XUất danh sách lương nhân viên", FileDialog.SAVE);

	/**
	 * set title
	 * 
	 * @param title
	 */
	public static void setTitle(String title) {
		try {
			Paragraph pdfTitle4 = new Paragraph(new Phrase(title, fontTitle));
			pdfTitle4.setAlignment(Element.ALIGN_CENTER);
			document.add(pdfTitle4);
			document.add(Chunk.NEWLINE);
		} catch (DocumentException ex) {
			ex.printStackTrace();
		}
	}

	// Lấy đường dẫn file
	public static String getFile() {
		fileDialog.setFile("untitled.pdf");
		fileDialog.setVisible(true);

		String url = fileDialog.getDirectory() + fileDialog.getFile();
		if (url.equals("nullnull"))
			return null;

		return url;
	}

	public static void writeLuongNhanVien(int thang, int nam, List<Map<String, Object>> dsLuong) {
		String url = "";
		try {
			// cài đặt font chữ cho tài liệu
			fontData = new Font(
					BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10,
					Font.NORMAL);
			fontTitle = new Font(
					BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 20,
					Font.NORMAL);
			fontHeader = new Font(
					BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11,
					Font.NORMAL);

			fileDialog.setTitle("Danh sách lương");
			url = getFile();
			if (url == null)
				return;

			file = new FileOutputStream(url);
			document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();

			setTitle(String.format("Bảng thanh toán tiền lương \nTháng %d Năm %d", thang, nam));

			// khoảng trong giua hang
			Chunk glue = new Chunk(new VerticalPositionMark());

			// tạo table cho bảng lương
			PdfPTable pdfTable = new PdfPTable(9);

			// set header cho các cột
			pdfTable.addCell(new PdfPCell(new Phrase("STT", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Mã lương", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Mã nhân viên", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Tên nhân viên", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Giới tính", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Số điện thoại", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Trợ cấp", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Lương thưởng", fontHeader)));
			pdfTable.addCell(new PdfPCell(new Phrase("Tổng lương", fontHeader)));
			pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);

			int row = 1;
			for (Map<String, Object> v : dsLuong) {
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%d", row++), fontData)));
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%s", v.get("MaLuong")), fontData)));
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%s", v.get("MaNhanVien")), fontData)));
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%s", v.get("TenNhanVien")), fontData)));
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%s", v.get("GioiTinh")), fontData)));
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%s", v.get("SoDienThoai")), fontData)));
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%s", PriceFormatterUtils.format((Double) v.get("TroCap"))), fontData)));
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%s", PriceFormatterUtils.format((Double) v.get("LuongThuong"))), fontData)));
				pdfTable.addCell(new PdfPCell(new Phrase(String.format("%s", PriceFormatterUtils.format((Double) v.get("TongLuong"))), fontData)));
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
			footer.add("             ");
			footer.add("Kế toán trưởng");
			footer.add(glue);
			footer.add("Giám đốc");
			footer.add("             ");
			document.add(footer);

			document.close();

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

		} catch (DocumentException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeFile() {
		document.close();
	}

}
