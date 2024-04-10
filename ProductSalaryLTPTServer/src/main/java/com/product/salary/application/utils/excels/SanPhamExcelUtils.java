package com.product.salary.application.utils.excels;

import com.product.salary.application.entity.SanPham;
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

public class SanPhamExcelUtils {
	/**
	 * Dialog show file để lưu
	 */
	static FileDialog mFileDialogExport = new FileDialog(new JFrame(), "Xuất danh sách sản phẩm", FileDialog.SAVE);

	/**
	 * Dialog show file để đọc
	 */
	static FileDialog mFileDialogImport = new FileDialog(new JFrame(), "Đọc danh sách sản phẩm", FileDialog.LOAD);

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

	public static List<SanPham> importExcelSanPham() {
		List<SanPham> sanPhams = new ArrayList();
		// Cài đặt title
		mFileDialogImport.setTitle("Chọn Nhà Cung Cấp");

		// Lấy url đường dẫn gile
		String url = getFileImport();
		if (url == null) {
			return sanPhams;
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
					String tenSanPham = row.getCell(2).getStringCellValue();
					int soLuongTon = (int) row.getCell(3).getNumericCellValue();
					String chatLieu = row.getCell(4).getStringCellValue();
					String donViTinh = row.getCell(5).getStringCellValue();
					int soCongDoan = (int) row.getCell(6).getNumericCellValue();
					Double donGia = (Double) row.getCell(7).getNumericCellValue();
					Boolean trangThai = row.getCell(8).getBooleanCellValue();
					SanPham sanPham = new SanPham("", tenSanPham, soLuongTon, null, chatLieu, donViTinh,
							soCongDoan, donGia, trangThai);
					sanPhams.add(sanPham);
				}
				JOptionPane.showMessageDialog(null, "Nhập danh sách sản phẩm thành công.");
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
				Logger.getLogger(SanPhamExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return sanPhams;

	}

	/**
	 * Xuất file Excel danh sách sản phẩm
	 * 
	 * @param sanPhams
	 */
	public static void exportExcelSanPham(List<SanPham> sanPhams) {
		// Cài đặt title
		mFileDialogExport.setTitle("Xuất Nhà Cung Cấp");
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
				row.createCell(1, CellType.STRING).setCellValue("Mã sản phẩm");
				row.createCell(2, CellType.STRING).setCellValue("Tên sản phẩm");
				row.createCell(3, CellType.NUMERIC).setCellValue("Số lượng tồn");
				row.createCell(4, CellType.STRING).setCellValue("Chất liệu");
				row.createCell(5, CellType.STRING).setCellValue("Đơn vị tính");
				row.createCell(6, CellType.NUMERIC).setCellValue("Số công đoạn");
				row.createCell(7, CellType.NUMERIC).setCellValue("Đơn giá");
				row.createCell(8, CellType.BOOLEAN).setCellValue("Trạng thái");
				
				for (SanPham v : sanPhams) {
					// Duyệt từng dòng
					rownum++;
					// Dùng row để tạo lại row 0 -> n
					row = sheet.createRow(rownum);

					// Tạo dòng và đưa giá trị vào
					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(v.getMaSanPham());
					row.createCell(2, CellType.STRING).setCellValue(v.getTenSanPham());
					row.createCell(3, CellType.NUMERIC).setCellValue(v.getSoLuongTon());
					row.createCell(4, CellType.STRING).setCellValue(v.getChatLieu());
					row.createCell(5, CellType.STRING).setCellValue(v.getDonViTinh());
					row.createCell(6, CellType.NUMERIC).setCellValue(v.getSoCongDoan());
					row.createCell(7, CellType.NUMERIC).setCellValue(v.getDonGia());
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
						"Xuất danh sách sản phẩm thành công vào: " + file.getAbsolutePath());
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(SanPhamExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(SanPhamExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(SanPhamExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void main(String[] args) {
		List<SanPham> sanPhams = new ArrayList();
		try {
//			sanPhams.add(new SanPham("SP001", "Sản phẩm 1", 20, null, "Sắt, hợp kim", "Kg", 6, 200000.0, true));
//			sanPhams.add(new SanPham("SP002", "Sản phẩm 1", 20, null, "Sắt, hợp kim", "Kg", 6, 200000.0, true));
//			sanPhams.add(new SanPham("SP003", "Sản phẩm 1", 20, null, "Sắt, hợp kim", "Kg", 6, 200000.0, true));
//			sanPhams.add(new SanPham("SP004", "Sản phẩm 1", 20, null, "Sắt, hợp kim", "Kg", 6, 200000.0, true));
//			sanPhams.add(new SanPham("SP005", "Sản phẩm 1", 20, null, "Sắt, hợp kim", "Kg", 6, 200000.0, true));
			sanPhams = SanPhamExcelUtils.importExcelSanPham();
			System.out.println(sanPhams);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
