package com.product.salary.application.dao;

import com.product.salary.application.entity.CongDoanSanPham;

import java.util.List;

public interface CongDoanSanPhamDAO {
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
	 * Tìm kiếm công đoạn bằng mã công đoạn
	 * @param maCongDoan
	 * @return
	 */
	CongDoanSanPham timKiemBangMaCongDoan(String maCongDoan);
	
	/**
	 * Tìm mã công đoạn sản phẩm cuối cùng bằng mã sản phẩm
	 * 
	 * @param maSanPham là mã sản phẩm cần tìm
	 * @return mã công đoạn sản phẩm cuối cùng
	 */
	String timMaCongDoanSanPhamCuoiCungBangMaSanPham(String maSanPham);
	
	void capNhatSoLuongCanCuaCongDoan(String maCongDoan, int soLuong);

	CongDoanSanPham timCongDoanlamSauBangMaCongDoan(String maCongDoan);

	void capNhatSoLuongCanCuaCongDoanSau(String maCongDoan, int soLuong);
}
