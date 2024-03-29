package com.product.salary.application.utils.excels;

import com.product.salary.application.entity.ToNhom;
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

public class ToNhomExcelUtils {
	/**
	 * Dialog show file để lưu
	 */
	static FileDialog mFileDialogExport = new FileDialog(new JFrame(), "Xuất danh sách tổ nhóm", FileDialog.SAVE);

	/**
	 * Dialog show file để đọc
	 */
	static FileDialog mFileDialogImport = new FileDialog(new JFrame(), "Đọc danh sách tổ nhóm", FileDialog.LOAD);

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
	 * Lấy đường dẫn xuất file
	 * 
	 * @return url
	 */
	private static String getFileImport() {
		mFileDialogImport.setVisible(true);

		String url = mFileDialogImport.getDirectory() + mFileDialogImport.getFile();
		if (url.equals("nullnull")) {
			return null;
		}
		return url;
	}

	public static void exportExcelSanPham(List<ToNhom> dsToNhom) {
		// Cài đặt title
		mFileDialogExport.setTitle("Xuất tổ nhóm");
		// Lấy url đường đẫn file
		String url = getFileExport();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			try (HSSFWorkbook workbook = new HSSFWorkbook()) {
				// Tạp sheet
				HSSFSheet sheet = workbook.createSheet("Nhà cung cấp");

				// Dòng 0
				int rownum = 0;
				// Tạo dòng [0]
				HSSFRow row = sheet.createRow(rownum);

				// Tạo cột
				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã tổ nhóm");
				row.createCell(2, CellType.STRING).setCellValue("Tên tổ nhóm");
				row.createCell(3, CellType.NUMERIC).setCellValue("Số lượng công nhân");
				row.createCell(4, CellType.STRING).setCellValue("Trạng thái");

				for (ToNhom v : dsToNhom) {
					// Duyệt từng dòng
					rownum++;
					// Dùng row để tạo lại row 0 -> n
					row = sheet.createRow(rownum);

					// Tạo dòng và đưa giá trị vào
					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(v.getMaToNhom());
					row.createCell(2, CellType.STRING).setCellValue(v.getTenToNhom());
					row.createCell(3, CellType.NUMERIC).setCellValue(v.getSoLuongCongNhan());
					row.createCell(4, CellType.STRING).setCellValue(v.isTrangThai());

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
						"Xuất danh sách tổ nhóm thành công vào: " + file.getAbsolutePath());
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ToNhomExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ToNhomExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ToNhomExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public static List<ToNhom> importExcelToNhom() {
		List<ToNhom> dsToNhom = new ArrayList();
		// Cài đặt title
		mFileDialogImport.setTitle("Tổ nhóm");

		// Lấy url đường dẫn gile
		String url = getFileImport();
		if (url == null) {
			return dsToNhom;
		}
		FileInputStream inFile = null;
		try {
			inFile = new FileInputStream(url);
			HSSFWorkbook workbook = null;
			try {
				workbook = new HSSFWorkbook(inFile);
				// Lất sheet 0
				HSSFSheet sheet = workbook.getSheetAt(0);
				int rows = sheet.getLastRowNum();
				for (int i = 1; i <= rows; i++) {
					Row row = sheet.getRow(i);
					String maToNhom = row.getCell(1).getStringCellValue();
					String tenToNhom = row.getCell(2).getStringCellValue();
					int soLuongCongNhan = (int) row.getCell(3).getNumericCellValue();
					Boolean trangThai = row.getCell(4).getBooleanCellValue();
					ToNhom toNhom = new ToNhom(maToNhom, tenToNhom, soLuongCongNhan, trangThai);
					dsToNhom.add(toNhom);
				}
				JOptionPane.showMessageDialog(null, "Nhập danh sách tổ nhóm thành công.");
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
				Logger.getLogger(ToNhomExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return dsToNhom;

	}
}
