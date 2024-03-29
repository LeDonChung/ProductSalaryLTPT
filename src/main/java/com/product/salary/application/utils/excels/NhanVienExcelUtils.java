package com.product.salary.application.utils.excels;

import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.entity.TrinhDo;
import com.product.salary.application.service.impl.ChucVuServiceImpl;
import com.product.salary.application.service.impl.PhongBanServiceImpl;
import com.product.salary.application.service.impl.TrinhDoServiceImpl;
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

public class NhanVienExcelUtils {

	/**
	 * Dialog show file để lưu
	 */
	static FileDialog mFileDialogExport = new FileDialog(new JFrame(), "Xuất danh sách nhân viên", FileDialog.SAVE);

	/**
	 * Dialog show file để đọc
	 */
	static FileDialog mFileDialogImport = new FileDialog(new JFrame(), "Đọc danh sách nhân viên", FileDialog.LOAD);

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
	public static List<NhanVien> importExcelNhanVien() {
		List<NhanVien> danhSachNhanVien = new ArrayList<NhanVien>();

		// cài đặt title
		mFileDialogImport.setTitle("Chọn danh sách nhân viên");

		// Lấy đường dẫn đến file
		String url = getFileImport();

		if (url == null) {
			return danhSachNhanVien;
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

//					String maNhanVien = row.getCell(1).getStringCellValue();
					String tenNhanVien = row.getCell(2).getStringCellValue();
					String email = row.getCell(3).getStringCellValue();
					String diaChi = row.getCell(4).getStringCellValue();
					int gioiTinh = (int) row.getCell(5).getNumericCellValue();

					// Chu vu
					String maChucVu = row.getCell(6).getStringCellValue();
//					String tenChucVu = row.getCell(7).getStringCellValue();
//					ChucVu chucVu = new ChucVu(maChucVu, tenChucVu);
					ChucVuServiceImpl chucVuService = new ChucVuServiceImpl();
					ChucVu chucVu = chucVuService.timKiemBangMaChucVu(maChucVu);

					String cccd = row.getCell(8).getStringCellValue();
					String soDienThoai = row.getCell(9).getStringCellValue();
					String ngaySinh = row.getCell(10).getStringCellValue();

					// Phong ban
					String maPhongBan = row.getCell(11).getStringCellValue();
//					String tenPhongBan = row.getCell(12).getStringCellValue();
//					PhongBan phongBan = new PhongBan(maPhongBan, tenPhongBan);
					PhongBanServiceImpl phongBanService = new PhongBanServiceImpl();
					PhongBan phongBan = phongBanService.timKiemBangMaPhongBan(maPhongBan);

					String ngayVaoLam = row.getCell(13).getStringCellValue();
					double luongCoSo = row.getCell(14).getNumericCellValue();
					double heSoLuong = row.getCell(15).getNumericCellValue();
					double troCap = row.getCell(16).getNumericCellValue();

					// trinh do
					String maTrinhDo = row.getCell(17).getStringCellValue();
//					String tenTrinhDo = row.getCell(18).getStringCellValue();
//					TrinhDo trinhDo = new TrinhDo(maTrinhDo, tenTrinhDo);
					TrinhDoServiceImpl trinhDoService = new TrinhDoServiceImpl();
					TrinhDo trinhDo = trinhDoService.timKiemBangMaTrinhDo(maTrinhDo);

					Boolean trangThai = row.getCell(19).getBooleanCellValue();

					NhanVien nhanVien = new NhanVien("", tenNhanVien, email, diaChi, gioiTinh, chucVu, cccd,
							soDienThoai, LocalDate.parse(ngaySinh), phongBan, LocalDate.parse(ngayVaoLam), luongCoSo,
							heSoLuong, troCap, trinhDo, null, trangThai);
					danhSachNhanVien.add(nhanVien);
				}

				JOptionPane.showMessageDialog(null, "Nhập danh sách nhân viên thành công.");
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
				Logger.getLogger(NhanVienExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return danhSachNhanVien;
	}

	public static void exportExcelNhanVien(List<NhanVien> dsNhanVien) {
		// cài đặt title
		mFileDialogExport.setTitle("Xuất danh sách nhân viên");

		// lấy đường dẫn lưu file
		String url = getFileExport();

		if (url == null)
			return;

		FileOutputStream outFile = null;
		try {
			outFile = new FileOutputStream(url);
			try (HSSFWorkbook workbook = new HSSFWorkbook()) {
				// Tạo sheet
				HSSFSheet sheet = workbook.createSheet("Nhân viên");

				// tạo dòng [0]
				int rownum = 0;
				HSSFRow row = sheet.createRow(rownum);

				// tạo cột cho dòng [0]
				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã nhân viên");
				row.createCell(2, CellType.STRING).setCellValue("Tên nhân viên");
				row.createCell(3, CellType.STRING).setCellValue("Email");
				row.createCell(4, CellType.STRING).setCellValue("Địa chỉ");
				row.createCell(5, CellType.NUMERIC).setCellValue("Giới tính");
				row.createCell(6, CellType.STRING).setCellValue("Mã chức vụ");
				row.createCell(7, CellType.STRING).setCellValue("Tên chức vụ");
				row.createCell(8, CellType.STRING).setCellValue("Cccd");
				row.createCell(9, CellType.STRING).setCellValue("Số điện thoại");
				row.createCell(10, CellType.STRING).setCellValue("Ngày sinh");
				row.createCell(11, CellType.STRING).setCellValue("Mã phòng ban");
				row.createCell(12, CellType.STRING).setCellValue("Tên phòng ban");
				row.createCell(13, CellType.STRING).setCellValue("Ngày vào làm");
				row.createCell(14, CellType.NUMERIC).setCellValue("Lương cơ sở");
				row.createCell(15, CellType.NUMERIC).setCellValue("Hệ số lương");
				row.createCell(16, CellType.NUMERIC).setCellValue("Trợ cấp");
				row.createCell(17, CellType.STRING).setCellValue("Mã trình độ");
				row.createCell(18, CellType.STRING).setCellValue("Tên trình độ");
				row.createCell(19, CellType.BOOLEAN).setCellValue("Trạng thái");

				// duyệt từng phòng ban và thêm vào từng dòng trog excel
				for (NhanVien nhanVien : dsNhanVien) {
					rownum++;
					row = sheet.createRow(rownum);

					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(nhanVien.getMaNhanVien());
					row.createCell(2, CellType.STRING).setCellValue(nhanVien.getHoTen());
					row.createCell(3, CellType.STRING).setCellValue(nhanVien.getEmail());
					row.createCell(4, CellType.STRING).setCellValue(nhanVien.getDiaChi());
					row.createCell(5, CellType.NUMERIC).setCellValue(nhanVien.getGioiTinh());
					row.createCell(6, CellType.STRING).setCellValue(nhanVien.getChucVu().getMaChucVu());
					row.createCell(7, CellType.STRING).setCellValue(nhanVien.getChucVu().getTenChucVu());
					row.createCell(8, CellType.STRING).setCellValue(nhanVien.getCccd());
					row.createCell(9, CellType.STRING).setCellValue(nhanVien.getSoDienThoai());
					row.createCell(10, CellType.STRING).setCellValue(nhanVien.getNgaySinh().toString());
					row.createCell(11, CellType.STRING).setCellValue(nhanVien.getPhongBan().getMaPhongBan());
					row.createCell(12, CellType.STRING).setCellValue(nhanVien.getPhongBan().getTenPhongBan());
					row.createCell(13, CellType.STRING).setCellValue(nhanVien.getNgayVaoLam().toString());
					row.createCell(14, CellType.NUMERIC).setCellValue(nhanVien.getLuongCoSo());
					row.createCell(15, CellType.NUMERIC).setCellValue(nhanVien.getHeSoLuong());
					row.createCell(16, CellType.NUMERIC).setCellValue(nhanVien.getTroCap());
					row.createCell(17, CellType.STRING).setCellValue(nhanVien.getTrinhDo().getMaTrinhDo());
					row.createCell(18, CellType.STRING).setCellValue(nhanVien.getTrinhDo().getTenTrinhDo());
					row.createCell(19, CellType.BOOLEAN).setCellValue(nhanVien.isTrangThai());
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
						"Xuất danh sách nhân viên thành công vào: " + file.getAbsolutePath());
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(NhanVienExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(NhanVienExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(NhanVienExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
