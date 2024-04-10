package com.product.salary.application.service;

import java.util.List;
import java.util.Map;

public interface LuongNhanVienService {
	/**
	 * Thống kê lương nhân viên theo nam
	 * 
	 * @return danh sách thông kế lương theo năm
	 */
	Map<String, Double> thongKeLuongNhanVienTheoNam();

	/**
	 * Hàm tìm kiếm lương theo tháng, năm của các nhân viên
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	List<Map<String, Object>> timKiemTatCaLuongNhanVienTheoThangVaNam(int thang, int nam);

	/**
	 * Hàm tính lương nhân viên và lưu vào CSDL
	 * 
	 * @param luongNV
	 * @return
	 */
	boolean tinhLuongNhanVien(int thang, int nam);

	/**
	 * Hàm phát sinh mã lương
	 * 
	 * @param maNhanVien
	 * @param thang
	 * @param nam
	 * @return
	 */
	String generateMaLuong(String maNhanVien, int thang, int nam);

	/**
	 * Hàm tìm kiếm tất cả chi tiết lương của nhân viên theo tháng và năm
	 * 
	 * @param maNhanVien
	 * @param thang
	 * @param nam
	 * @return
	 */
	List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maNhanVien, int thang, int nam);

	/**
	 * Thống kê lương nhân viên theo tháng, năm gồm: Tiền lương thấp nhất, tiền
	 * lương cao nhất, tổng nhân viên, tổng số tiền lương, nhân viên xuất sắc nhất
	 * 
	 * @return
	 */
	Map<String, Object> thongKeLuongNhanVienBangThangVaNam(int thang, int nam);
	
	/**
	 * Hàm cập nhật lương nhân viên sau khi được thưởng
	 * @param maLuong
	 * @param luongThuong
	 */
	void capNhatLuongThuong(String maLuong, double luongThuong);
}
