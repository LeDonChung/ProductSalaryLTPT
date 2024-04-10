package com.product.salary.application.service;

import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.entity.SanPham;

import java.util.List;

public interface CongDoanSanPhamService {
	/**
	 * Tìm tất cả công đoạn sản phẩm theo mã sản phẩm
	 * 
	 * @return danhSachCongDoan
	 */
	List<CongDoanSanPham> timTatCaCongDoanSanPham(String maSanPham);

	/**
	 * Tìm tất cả công đoạn sản phẩm đang hoạt động
	 * 
	 * @param maSanPham là mã sản phẩm cần tìm kiếm
	 * @return danhSachCongDoan
	 */
	public List<CongDoanSanPham> timTatCaCongDoanSanPhamDangHoatDongBangMaSanPham(String maSanPham);

	/**
	 * Cập nhật công đoạn sản phẩm
	 * 
	 * @param congDoanSanPham
	 * @return
	 */
	CongDoanSanPham capNhatCongDoanSanPham(CongDoanSanPham congDoanSanPham);

	/**
	 * Cập nhật trạng thái công đoạn sản phẩm
	 * 
	 * @param maCongDoanSanPham là mã công đoạn sản phẩm cần cập nhật
	 * @param trangThai         là trạng thái cần cập nhật
	 * @return true: cập nhật thành công, false: cập nhật không thành công
	 */
	boolean capNhatTrangThaiCongDoanSanPham(String maCongDoanSanPham, boolean trangThai);

	/**
	 * Thêm công đoạn sản phẩm congDoanSanPham != null nếu thêm thành công
	 * congDoanSanPham == null nếu thêm công thành công
	 * 
	 * @param congDoanSanPham là công đoạn sản phẩm cần cập nhật
	 * @return congDoanSanPham
	 */
	CongDoanSanPham themCongDoanSanPham(CongDoanSanPham congDoanSanPham);

	/**
	 * Generate mã công đoạn sản phẩm
	 * 
	 * @param maSanPham
	 * @return
	 */
	public String generateMaCongDoanSanPham(String maSanPham);

	/**
	 * Thực hiện thêm nhiều công đoạn sản phẩm
	 * 
	 * @param sanPham          sản phẩm được thêm
	 * @param congDoanSanPhams danh sách công đoạn cần thêm
	 * @return danh sách công đoạn đã thêm thành công
	 */
	List<CongDoanSanPham> themNhieuCongDoanSanPham(SanPham sanPham, List<CongDoanSanPham> congDoanSanPhams);
	
	void capNhatSoLuongCanCuaCongDoan(String maCongDoan, int soLuong);

}
