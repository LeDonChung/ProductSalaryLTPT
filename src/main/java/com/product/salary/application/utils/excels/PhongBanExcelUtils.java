package com.product.salary.application.utils.excels;

import com.product.salary.application.entity.PhongBan;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhongBanExcelUtils {

	/**
	 * Dialog show file để lưu
	 */
	static FileDialog mFileDialogExport = new FileDialog(new JFrame(), "Xuất danh sách phòng ban", FileDialog.SAVE);

	/**
	 * Dialog show file để đọc
	 */
	static FileDialog mFileDialogImport = new FileDialog(new JFrame(), "Đọc danh sách phòng ban", FileDialog.LOAD);

	/**
	 * Lấy đường dẫn xuất file
	 * 
	 * @return
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
	 * Lấy đường dẫn đọc file
	 * 
	 * @return
	 */
	private static String getFileImport() {
		mFileDialogImport.setVisible(true);

		String url = mFileDialogImport.getDirectory() + mFileDialogImport.getFile();
		if (url.equals("nullnull")) {
			return null;
		}
		return url;
	}

	/**
	 * import dánh sách phòng ban từ file excels
	 * 
	 * @return
	 */
	public static List<PhongBan> importExcelPhongBan() {
		List<PhongBan> danhSachPhongBan = new ArrayList<PhongBan>();

		// cài đặt title
		mFileDialogImport.setTitle("Chọn danh sách phòng ban");

		// Lấy đường dẫn đến file
		String url = getFileImport();

		if (url == null) {
			return danhSachPhongBan;
		}

		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(url);
			HSSFWorkbook workbook = null;
			try {
				workbook = new HSSFWorkbook(inFile);
				// Lấy sheet 0
				HSSFSheet sheet = workbook.getSheetAt(0);
				int rows = sheet.getLastRowNum();
				for (int i = 1; i <= rows; i++) {
					Row row = sheet.getRow(i);
					String maPhongBan = row.getCell(1).getStringCellValue();
					String tenPhongBan = row.getCell(2).getStringCellValue();
					int soLuongNhanVien = (int) row.getCell(3).getNumericCellValue();
					Boolean trangThai = row.getCell(4).getBooleanCellValue();

					PhongBan phongBan = new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien, trangThai);
					danhSachPhongBan.add(phongBan);
				}

				JOptionPane.showMessageDialog(null, "Nhập danh sách phòng ban thành công.");
			} catch (HeadlessException e) {
				e.printStackTrace();
			} finally {
				if (workbook != null) {
					workbook.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inFile != null) {
					inFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(PhongBanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return danhSachPhongBan;
	}

	public static void exportExcelPhongBan(List<PhongBan> dsPhongBan) {
		// cài đặt title
		mFileDialogExport.setTitle("Xuất danh sách phòng ban");

		// lấy đường dẫn lưu file
		String url = getFileExport();

		if (url == null)
			return;

		FileOutputStream outFile = null;
		try {
			outFile = new FileOutputStream(url);
			try (HSSFWorkbook workbook = new HSSFWorkbook()) {
				// Tạo sheet
				HSSFSheet sheet = workbook.createSheet("Phòng ban");

				// tạo dòng [0]
				int rownum = 0;
				HSSFRow row = sheet.createRow(rownum);

				// tạo cột cho dòng [0]
				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã phòng ban");
				row.createCell(2, CellType.STRING).setCellValue("Tên phòng ban");
				row.createCell(3, CellType.NUMERIC).setCellValue("Số lượng nhân viên");
				row.createCell(4, CellType.BOOLEAN).setCellValue("Trạng thái");

				// duyệt từng phòng ban và thêm vào từng dòng trog excel
				for (PhongBan phongBan : dsPhongBan) {
					rownum++;
					row = sheet.createRow(rownum);

					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(phongBan.getMaPhongBan());
					row.createCell(2, CellType.STRING).setCellValue(phongBan.getTenPhongBan());
					row.createCell(3, CellType.NUMERIC).setCellValue(phongBan.getSoLuongNhanVien());
					row.createCell(4, CellType.BOOLEAN).setCellValue(phongBan.isTrangThai());
				}

				// resize cột trog excel
				for (int i = 0; i < rownum; i++) {
					sheet.autoSizeColumn(i);
				}

				File file = new File(url);
				file.getParentFile().mkdirs();
				outFile = new FileOutputStream(file);
				workbook.write(outFile);

				JOptionPane.showMessageDialog(null,
						"Xuất danh sách phòng bann thành công vào: " + file.getAbsolutePath());
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(PhongBanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(PhongBanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(PhongBanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
