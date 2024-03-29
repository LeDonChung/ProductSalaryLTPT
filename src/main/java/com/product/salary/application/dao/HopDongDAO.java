package com.product.salary.application.dao;

import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;

import java.util.List;

public interface HopDongDAO {
	/**
	 * Tìm tất cả hợp đồng
	 * 
	 * @return danh sách hợp đồng
	 */
	List<HopDong> timTatCaHopDong();

	/**
	 * Thêm hợp đồng mới
	 * 
	 * @param hopDong         hợp đồng cần thêm mới
	 * @param chiTietHopDongs danh sách chi tiết hợp đồng cần thêm
	 * @return hopDong nếu thêm thành công, null nếu thêm không thành công
	 */

	HopDong themHopDong(HopDong hopDong, List<ChiTietHopDong> chiTietHopDongs);

	/**
	 * Thanh lý hợp đồng
	 * 
	 * @param maHopDong mã hợp đồng cần thành lý
	 * @return true: thêm thành công, false: thêm thất bại
	 */
	boolean thanhLyHopDong(String maHopDong);

	/**
	 * Tìm kiếm hợp đồng bằng mã
	 * 
	 * @param maHopDong mã hợp đồng cần tìm kiếm
	 * @return hopDong nếu có tồn tại, null nếu không tồn tại
	 */
	HopDong timHopDongBangMaHopDong(String maHopDong);

	/**
	 * Tìm mã hợp đồng ở vị trí cuối cùng
	 * 
	 * @return mã hợp đồng
	 */
	String timMaHopDongCuoiCung();

	/**
	 * Tìm tổng số lượng hợp đồng
	 * 
	 * @return tổng số lượng hợp đồng
	 */
	int tongSoLuongHopDong();
}
