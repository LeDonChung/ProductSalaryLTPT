package com.product.salary.application.service;

import com.product.salary.application.entity.ChucVu;

import java.util.List;

public interface ChucVuService {
	/**
	 * Hàm tìm kiếm tất cả chức vụ
	 * 
	 * @return danh sách tất cả chức vụ
	 */
	List<ChucVu> timKiemTatCaChucVu();

	/**
	 * Cập nhật chức vụ
	 * 
	 * @param chucVu là đối tượng cần cập nhật
	 * @return chucVu là thông tin chức vụ mới cập nhật, null nếu cập nhật không
	 *         thành công
	 */
	ChucVu capNhatChucVu(ChucVu chucVu);

	/**
	 * Thêm chức vụ mới
	 * 
	 * @param chucVu là thông tin chức vụ cần thêm
	 * @return chức vụ mới thêm
	 */
	ChucVu themChucVu(ChucVu chucVu);

	/**
	 * Tìm kiếm chức vụ bằng mã
	 * 
	 * @param chucVu là mã chức vụ cần tìm
	 * @return chucVu
	 */
	ChucVu timKiemBangMaChucVu(String maChucVu);

	/**
	 * Tạo mã chức vụ
	 * 
	 * @return maChucVu
	 */
	String generateMaChucVu();

	/**
	 * Xóa chức vụ bằng mã
	 * 
	 * @param maChucVu
	 * @return true là đã xóa thành công, false là chưa xóa thành công
	 */
	boolean xoaChucVuBangMa(String maChucVu);
}
