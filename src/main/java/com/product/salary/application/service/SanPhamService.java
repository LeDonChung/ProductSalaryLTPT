package com.product.salary.application.service;

import com.product.salary.application.entity.SanPham;

import java.util.List;

public interface SanPhamService {
	/**
	 * Hàm tìm kiếm tất cả sarn phẩm
	 * 
	 * @return danh sách tất cả sản phẩm
	 */
	List<SanPham> timKiemTatCaSanPham();

	/**
	 * Cập nhật sản phẩm
	 * 
	 * @param sanPham là đối tượng cần cập nhật
	 * @return sanPham là thông tin sản phẩm mới cập nhật, null nếu cập nhật không
	 *         thành công
	 */
	SanPham capNhatSanPham(SanPham sanPham);

	/**
	 * Cập nhật trạngt thái sản phẩm
	 * 
	 * @param trangThai là trạng thái sản phẩm cần cập nhật true: đang sản xuất
	 *                  false: ngưng sản xuất
	 * @return sanPham là thông tin sản phẩm mới cập nhật, null nếu cập nhật không
	 *         thành công
	 */
	boolean capNhatTrangThaiSanPham(String maSanPham, boolean trangThai);

	/**
	 * Tím kiếm sản phẩm theo nhiều tiêu chí như mã sản phẩm, tên sản phẩm, chất
	 * liệu, trạng thái
	 * 
	 * @param sanPham gồm nhiều thuộc tính chứa các tiêu chí
	 * @return danh sách sản phẩm được tìm thấy
	 */
	List<SanPham> timKiemSanPham(SanPham sanPham);

	/**
	 * Thêm sản phẩm mới
	 * 
	 * @param sanPham là thông tin sản phẩm cần thêm
	 * @return sản phẩm mới thêm
	 */
	SanPham themSanPham(SanPham sanPham);

	/**
	 * Tạo mã sản phẩm mới
	 * 
	 * @return mã sản phẩm mới
	 */
	public String generateMaSanPham();

	/**
	 * Tìm danh sách sản phẩm đang sản xuất
	 * 
	 * @return
	 */
	List<SanPham> timTatCaSanPhamDangSanXuat();

	/**
	 * Tìm tổng số lượng sản phẩm
	 * 
	 * @return số lượng sản phẩm
	 */
	int tongSoLuongSanPham();

	/**
	 * Thực hiện chức năng thêm nhiều sản phẩm
	 * 
	 * @param sanPhams danh sách sản phẩm cần thêm
	 * @return danh sách sản phẩm đã thêm thành công
	 */
	List<SanPham> themNhieuSanPham(List<SanPham> sanPhams);
}
