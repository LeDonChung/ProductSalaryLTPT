package com.product.salary.application.service;

import java.util.List;
import java.util.Map;

public interface LuongCongNhanService {
	/**
	 * Tinh lương công nhân theo tháng, năm
	 * 
	 * @param thang
	 * @param nam
	 * @return true: tính lương thành công, false: tính lương không thành công
	 */
	boolean tinhLuongCongNhan(int thang, int nam);

	/**
	 * Tìm danh sách lương công nhân theo tháng và năm
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	List<Map<String, Object>> timTatCaLuongCongNhanTheoThangVaNam(int thang, int nam);

	/**
	 * Lấy mã lương dựa vài mã công nhân, tháng, năm
	 * 
	 * @param maCongNhan
	 * @param thang
	 * @param nam
	 * @return
	 */
	public String generateMaLuong(String maCongNhan, int thang, int nam);

	/**
	 * Tìm danh sách chi tiết lương theo tháng và năm
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maCongNhan, int thang, int nam);

	/**
	 * Thống kê lương công nhân theo nam
	 * 
	 * @return danh sách thông kế lương theo năm
	 */
	Map<String, Double> thongKeLuongCongNhanTheoNam();

	/**
	 * Thống kê lương công nhân theo tháng, năm gồm: Tiền lương thấp nhất, tiền lương cao nhất, tổng công nhân, tổng số tiền lương, công nhân xuất sắc nhất
	 * @return 
	 */
	Map<String, Object> thongKeLuongCongNhanBangThangVaNam(int thang, int nam);

	/**
	 * Cập nhật lương thưởng theo mã lương
	 * @param maLuong mã lương cần cập nhật
	 * @param luongThuong lương thưởng muốn cập nhật
	 */
	void capNhatLuongThuong(String maLuong, double luongThuong);
	
	
}
