package com.product.salary.application.dao;

import com.product.salary.application.entity.SanPham;

import java.util.List;

public interface SanPhamDAO {
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
	 * Tìm kiếm sản phẩm bằng mã
	 * 
	 * @param maSanPham là mã sản phẩm cần tìm
	 * @return sanPham
	 */
	SanPham timKiemBangMaSanPham(String maSanPham);

	/**
	 * Lấy mã sản phẩm cuối cùng
	 * 
	 * @return maSanPham
	 */
	String timMaSanPhamCuoiCung();

	/**
	 * Tìm danh sách sản phẩm đang sản xuất
	 * 
	 * @return danhSachSanPham
	 */
	List<SanPham> timTatCaSanPhamDangSanXuat();

	/**
	 * Cập nhật số lượng công đoạn trong sản phẩm
	 * 
	 * @param maSanPham mã sản phẩm cần cập nhật
	 * @param i         số lượng cập nhật
	 */
	void capNhatSoLuongCongDoan(String maSanPham, int i);

	/**
	 * Kiểm tra tồn kho Nếu soLuongKiemTra > soLuongTon thì trả về fasle là không đủ
	 * Nếu soLuongKiemTra <= soLuongTon thì trả về true là đủ
	 * 
	 * @param maSanPham
	 * @param soLuongKiemTra
	 * @return true: đủ số lượng, false: không đủ số lượng
	 */
	boolean kiemTraTonKho(String maSanPham, int soLuongKiemTra);

	/**
	 * Tìm tổng số lượng sản phẩm
	 * 
	 * @return số lượng sản phẩm
	 */
	int tongSoLuongSanPham();
}
