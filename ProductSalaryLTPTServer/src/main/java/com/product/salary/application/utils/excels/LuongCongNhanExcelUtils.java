package com.product.salary.application.utils.excels;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LuongCongNhanExcelUtils {
	/**
	 * Dialog show file để lưu
	 */
	static FileDialog mFileDialogExport = new FileDialog(new JFrame(), "Xuất danh sách lương công nhân",
			FileDialog.SAVE);

	/**
	 * Lấy đường dẫn xuất file
	 * 
	 * @return url
	 */
	private static String getFileExport() {
		mFileDialogExport.setFile("untitled.xls");
		mFileDialogExport.setVisible(true);

		String url = mFileDialogExport.getDirectory() + mFileDialogExport.getFile();
		if (url.equals("nullnull")) {
			return null;
		}
		return url;
	}

	/**
	 * Xuất file Excel lương công nhân
	 * 
	 * @param luongCongNhans thông tin lương cần xuất
	 */
	public static void exportExcelDanhSachLuongCongNhan(List<Map<String, Object>> luongCongNhans) {
		// Cài đặt title
		mFileDialogExport.setTitle("Xuất Lương Công Nhân");
		// Lấy url đường đẫn file
		String url = getFileExport();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			try (HSSFWorkbook workbook = new HSSFWorkbook()) {
				// Tạp sheet
				HSSFSheet sheet = workbook.createSheet("Lương công nhân");

				// Dòng 0
				int rownum = 0;
				// Tạo dòng [0]
				HSSFRow row = sheet.createRow(rownum);
				// Tạo cột
				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã lương");
				row.createCell(2, CellType.STRING).setCellValue("Mã công nhân");
				row.createCell(3, CellType.STRING).setCellValue("Tên công nhân");
				row.createCell(4, CellType.STRING).setCellValue("Giới tính");
				row.createCell(5, CellType.STRING).setCellValue("Số điện thoại");
				row.createCell(6, CellType.STRING).setCellValue("Lương tháng");
				row.createCell(7, CellType.STRING).setCellValue("Lương thưởng");
				row.createCell(8, CellType.STRING).setCellValue("Trợ cấp");
				row.createCell(9, CellType.STRING).setCellValue("Tổng lương");
				row.createCell(10, CellType.STRING).setCellValue("Ngày tính lương");
				for (Map<String, Object> v : luongCongNhans) {
					// Duyệt từng dòng
					rownum++;
					// Dùng row để tạo lại row 0 -> n
					row = sheet.createRow(rownum);

					// Tạo dòng và đưa giá trị vào
					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(v.get("MaLuong").toString());
					row.createCell(2, CellType.STRING).setCellValue(v.get("MaCongNhan").toString());
					row.createCell(3, CellType.STRING).setCellValue(v.get("TenCongNhan").toString());
					row.createCell(4, CellType.STRING).setCellValue(v.get("GioiTinh").toString());
					row.createCell(5, CellType.STRING).setCellValue(v.get("SoDienThoai").toString());
					row.createCell(6, CellType.STRING).setCellValue(v.get("LuongThang").toString());
					row.createCell(7, CellType.STRING).setCellValue(v.get("LuongThuong").toString());
					row.createCell(8, CellType.STRING).setCellValue(v.get("TroCap").toString());
					row.createCell(9, CellType.STRING).setCellValue(v.get("TongLuong").toString());
					row.createCell(10, CellType.STRING).setCellValue(v.get("NgayTinhLuong").toString());
				}

				// Tự động resize cột
				for (int i = 0; i < rownum; i++) {
					sheet.autoSizeColumn(i);
				}

				File file = new File(url);
				file.getParentFile().mkdirs();
				outFile = new FileOutputStream(file);
				workbook.write(outFile);

				JOptionPane.showMessageDialog(null,
						"Xuất danh sách lương công nhân thành công vào: " + file.getAbsolutePath());
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(LuongCongNhanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(LuongCongNhanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(LuongCongNhanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
