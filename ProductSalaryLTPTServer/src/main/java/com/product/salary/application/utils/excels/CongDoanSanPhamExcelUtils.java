package com.product.salary.application.utils.excels;

import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.entity.SanPham;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CongDoanSanPhamExcelUtils {
	/**
	 * Dialog show file để lưu
	 */
	static FileDialog mFileDialogExport = new FileDialog(new JFrame(), "Xuất danh sách công đoạn sản phẩm",
			FileDialog.SAVE);

	/**
	 * Dialog show file để đọc
	 */
	static FileDialog mFileDialogImport = new FileDialog(new JFrame(), "Đọc danh sách công đoạn sản phẩm",
			FileDialog.LOAD);

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

	public static List<CongDoanSanPham> importExcelSanPham() {
		List<CongDoanSanPham> congDoanSanPhams = new ArrayList();
		// Cài đặt title
		mFileDialogImport.setTitle("Chọn Công Đoạn Sản Phẩm");

		// Lấy url đường dẫn gile
		String url = getFileImport();
		if (url == null) {
			return congDoanSanPhams;
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
					String maCongDoan = row.getCell(1).getStringCellValue();
					String tenCongDoan = row.getCell(2).getStringCellValue();
					int soLuongCanLam = (int) row.getCell(3).getNumericCellValue();
					Double giaCongDoan = row.getCell(4).getNumericCellValue();
					String thoiHan = row.getCell(5).getStringCellValue();
					String maCongDoanLamTruoc = row.getCell(6).getStringCellValue();
					String maSanPham = row.getCell(7).getStringCellValue();
					Boolean trangThai = row.getCell(8).getBooleanCellValue();
					CongDoanSanPham congDoanSanPhamLamTruoc = new CongDoanSanPham();
					congDoanSanPhamLamTruoc.setMaCongDoan(maCongDoanLamTruoc);
					CongDoanSanPham congDoanSanPham = new CongDoanSanPham(maCongDoan, tenCongDoan, soLuongCanLam,
							giaCongDoan, LocalDate.parse(thoiHan), congDoanSanPhamLamTruoc, new SanPham(maSanPham),
							trangThai);
					congDoanSanPhams.add(congDoanSanPham);
				}
				JOptionPane.showMessageDialog(null, "Nhập danh sách công đoạn sản phẩm thành công.");
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
				Logger.getLogger(CongDoanSanPhamExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return congDoanSanPhams;

	}

	/**
	 * Xuất file Excel danh sách sản phẩm
	 * 
	 * @param sanPhams
	 */
	public static void exportExcelCongDoanSanPham(List<CongDoanSanPham> congDoanSanPhams) {
		// Cài đặt title
		mFileDialogExport.setTitle("Xuất Công Đoạn Sản Phẩm");
		// Lấy url đường đẫn file
		String url = getFileExport();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			try (HSSFWorkbook workbook = new HSSFWorkbook()) {
				// Tạp sheet
				HSSFSheet sheet = workbook.createSheet("Công đoạn sản phẩm");

				// Dòng 0
				int rownum = 0;
				// Tạo dòng [0]
				HSSFRow row = sheet.createRow(rownum);
				// Tạo cột
				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã công đoạn");
				row.createCell(2, CellType.STRING).setCellValue("Tên công đoạn");
				row.createCell(3, CellType.NUMERIC).setCellValue("Số lượng cần làm");
				row.createCell(4, CellType.NUMERIC).setCellValue("Giá công đoạn");
				row.createCell(5, CellType.STRING).setCellValue("Thời hạn");
				row.createCell(6, CellType.STRING).setCellValue("Công đoạn làm trước");
				row.createCell(7, CellType.STRING).setCellValue("Mã sản phẩm");
				row.createCell(8, CellType.BOOLEAN).setCellValue("Trạng thái");
				for (CongDoanSanPham v : congDoanSanPhams) {
					// Duyệt từng dòng
					rownum++;
					// Dùng row để tạo lại row 0 -> n
					row = sheet.createRow(rownum);

					// Tạo dòng và đưa giá trị vào
					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(v.getMaCongDoan());
					row.createCell(2, CellType.STRING).setCellValue(v.getTenCongDoan());
					row.createCell(3, CellType.NUMERIC).setCellValue(v.getSoLuongCanLam());
					row.createCell(4, CellType.NUMERIC).setCellValue(v.getGiaCongDoan());
					row.createCell(5, CellType.STRING).setCellValue(v.getThoiHan().toString());
					row.createCell(6, CellType.STRING).setCellValue(v.getCongDoanLamTruoc().getMaCongDoan());
					row.createCell(7, CellType.STRING).setCellValue(v.getSanPham().getMaSanPham());
					row.createCell(8, CellType.BOOLEAN).setCellValue(v.isTrangThai());
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
						"Xuất danh sách công đoạn sản phẩm thành công vào: " + file.getAbsolutePath());
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(CongDoanSanPhamExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CongDoanSanPhamExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(CongDoanSanPhamExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
