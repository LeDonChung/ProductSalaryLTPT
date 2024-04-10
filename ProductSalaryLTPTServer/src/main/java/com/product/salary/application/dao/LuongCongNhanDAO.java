package com.product.salary.application.dao;

import com.product.salary.application.entity.LuongCongNhan;

import java.util.List;
import java.util.Map;

public interface LuongCongNhanDAO {
	/**
	 * Tính tổng tiền công nhân theo mã công nhân, tháng, năm
	 * 
	 * @param maCongNhan
	 * @param thang
	 * @param nam
	 * @return tổng tiền
	 */
	double tinhTongTienCongNhanTheoMaCongNhanVaThangVaNam(String maCongNhan, int thang, int nam);

	/**
	 * Cập nhật lương công nhân
	 * 
	 * @param luongCongNhan lương công nhân cần cập nhật
	 * @return luongCongNhan nếu cập nhật thành công, null nếu cập nhật thất bại
	 */
	LuongCongNhan capNhatLuongCongNhan(LuongCongNhan luongCongNhan);

	/**
	 * Thêm lương công nhân
	 * 
	 * @param luongCongNhan lương công nhân cần thêm
	 * @return luongCongNhan nếu thêm thành công, null nếu thêm thất bại
	 */
	LuongCongNhan themLuongCongNhan(LuongCongNhan luongCongNhan);

	/**
	 * Tìm kiếm lương công nhân bằng mã lương
	 * 
	 * @param maLuong
	 * @return luongCongNhan nếu tìm kiếm thành công, null nếu tìm kiếm không có
	 */
	LuongCongNhan timKiemLuongCongNhanBangMaLuong(String maLuong);

	/**
	 * Tìm tất cả lương công nhân theo tháng và năm
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	List<LuongCongNhan> timTatCaLuongCongNhanTheoThangVaNam(int thang, int nam);

	/**
	 * Tìm danh sách chi tiết lương theo mã công nhân, tháng và năm
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maCongNhan, int thang, int nam);

	/**
	 * Thống kê lương công nhân theo năm
	 * 
	 * @return danh sách thống kê lương theo năm
	 */
	Map<String, Double> thongKeLuongCongNhanTheoNam();

	/**
	 * Thống kê lương công nhân theo tháng
	 * 
	 * @param nam năm thống kê
	 * @return
	 */
	Map<String, Double> thongKeLuongCongNhanTheoThang(int nam);

	void capNhatLuongThuong(String maLuong, double luongThuong);

}
