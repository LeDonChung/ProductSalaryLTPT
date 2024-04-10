package com.product.salary.application.utils.excels;

import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.entity.ToNhom;
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

public class CongNhanExcelUtils {
	/**
	 * Dialog show file để lưu
	 */
	static FileDialog mFileDialogExport = new FileDialog(new JFrame(), "Xuất danh sách công nhân", FileDialog.SAVE);

	/**
	 * Dialog show file để đọc
	 */
	static FileDialog mFileDialogImport = new FileDialog(new JFrame(), "Đọc danh sách công nhân", FileDialog.LOAD);

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

	public static void exportExcelCongNhan(List<CongNhan> dsCongNhan) {
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
				HSSFSheet sheet = workbook.createSheet("Công nhân");

				// Dòng 0
				int rownum = 0;
				// Tạo dòng [0]
				HSSFRow row = sheet.createRow(rownum);

				// Tạo cột
				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã công nhân");
				row.createCell(2, CellType.STRING).setCellValue("Họ tên");
				row.createCell(3, CellType.NUMERIC).setCellValue("CCCD");
				row.createCell(4, CellType.STRING).setCellValue("Ngày sinh");
				row.createCell(5, CellType.STRING).setCellValue("Địa chỉ");
				row.createCell(6, CellType.NUMERIC).setCellValue("Điện thoại");
				row.createCell(7, CellType.NUMERIC).setCellValue("Email");
				row.createCell(8, CellType.BOOLEAN).setCellValue("Giới tính");
				row.createCell(9, CellType.BOOLEAN).setCellValue("Mã tổ nhóm");
				row.createCell(10, CellType.BOOLEAN).setCellValue("Ngày vào làm");
				row.createCell(11, CellType.BOOLEAN).setCellValue("Trợ cấp");
				row.createCell(12, CellType.BOOLEAN).setCellValue("Mã tay nghề");
				row.createCell(13, CellType.BOOLEAN).setCellValue("Trạng thái");
				for (CongNhan v : dsCongNhan) {
					// Duyệt từng dòng
					rownum++;
					// Dùng row để tạo lại row 0 -> n
					row = sheet.createRow(rownum);

					// Tạo dòng và đưa giá trị vào
					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(v.getMaCongNhan());
					row.createCell(2, CellType.STRING).setCellValue(v.getHoTen());
					row.createCell(3, CellType.NUMERIC).setCellValue(v.getCccd());
					row.createCell(4, CellType.STRING).setCellValue(v.getNgaySinh().toString());
					row.createCell(5, CellType.STRING).setCellValue(v.getDiaChi());
					row.createCell(6, CellType.NUMERIC).setCellValue(v.getSoDienThoai());
					row.createCell(7, CellType.NUMERIC).setCellValue(v.getEmail());
					row.createCell(8, CellType.BOOLEAN).setCellValue(v.getGioiTinh());
					row.createCell(9, CellType.BOOLEAN).setCellValue(v.getToNhom().getMaToNhom());
					row.createCell(10, CellType.BOOLEAN).setCellValue(v.getNgayVaoLam().toString());
					row.createCell(11, CellType.BOOLEAN).setCellValue(v.getTroCap());
					row.createCell(12, CellType.BOOLEAN).setCellValue(v.getTayNghe().getMaTayNghe());
					row.createCell(13, CellType.BOOLEAN).setCellValue(v.isTrangThai());

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
						"Xuất danh sách công nhân thành công vào: " + file.getAbsolutePath());
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(CongNhanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(CongNhanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(CongNhanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static List<CongNhan> importExcelCongNhan() {
		List<CongNhan> dsCongNhan = new ArrayList();
		// Cài đặt title
		mFileDialogImport.setTitle("Chọn Nhà Cung Cấp");

		// Lấy url đường dẫn gile
		String url = getFileImport();
		if (url == null) {
			return dsCongNhan;
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
					// String maCongNhan = row.getCell(1).getStringCellValue();
					String tenCongNhan = row.getCell(2).getStringCellValue();
					String cccd = row.getCell(3).getStringCellValue();
					String ngaySinh = row.getCell(4).getStringCellValue();
					String diaChi = row.getCell(5).getStringCellValue();
					String soDienThoai = row.getCell(6).getStringCellValue();
					String email = row.getCell(7).getStringCellValue();
					int gioiTinh = (int) row.getCell(8).getNumericCellValue();

					String maToNhom = row.getCell(9).getStringCellValue();
					ToNhom toNhom = new ToNhom(maToNhom);

					String ngayVaoLam = row.getCell(10).getStringCellValue();

					Double troCap = row.getCell(11).getNumericCellValue();

					String maTayNghe = row.getCell(12).getStringCellValue();
					TayNghe tayNghe = new TayNghe(maTayNghe);

					Boolean trangThai = row.getCell(13).getBooleanCellValue();

					CongNhan congNhan = new CongNhan("", tenCongNhan, email, diaChi, cccd, soDienThoai, trangThai,
							gioiTinh, LocalDate.parse(ngaySinh), LocalDate.parse(ngayVaoLam), troCap, toNhom, tayNghe);
					dsCongNhan.add(congNhan);
				}
				JOptionPane.showMessageDialog(null, "Nhập danh sách công nhân thành công.");
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
				Logger.getLogger(CongNhanExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return dsCongNhan;
	}
}
