package com.product.salary.application.dao;

import com.product.salary.application.entity.ChiTietHopDong;

import java.util.List;

public interface ChiTietHopDongDAO {
	/**
	 * Thêm danh sách chi tiết hợp đồng
	 * 
	 * @param chiTietHopDongs danh sách chi tiết hợp đồng
	 * @return danh sách chi tiết hợp đồng mới thêm thành công
	 */
	List<ChiTietHopDong> themDanhSachChiTietHopDong(List<ChiTietHopDong> chiTietHopDongs);

	/**
	 * Tìm kiếm danh sách chi tiết hợp đồng bằng mã
	 * 
	 * @return danh sách chi tiết hợp đồng
	 */
	List<ChiTietHopDong> timTatCaChiTietHopDongBangMaHopDong(String maHopDong);
}
