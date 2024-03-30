package com.product.salary.application.service;

import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;

import java.util.List;

public interface HopDongService {
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
	 * @param chiTietHopDongs danh sách chi tiết hợp đồng mới
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

	String generateMaHopDong();

	/*
	 * Tìm tổng số lượng hợp đồng
	 */
	int tongSoLuongHopDong();
}
