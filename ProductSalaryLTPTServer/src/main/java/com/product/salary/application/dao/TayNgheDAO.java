package com.product.salary.application.dao;

import com.product.salary.application.entity.TayNghe;

import java.util.List;

public interface TayNgheDAO {
	/**
	 * Hàm tìm kiếm tất cả tay nghề
	 * 
	 * @return danh sách tất cả tay nghề
	 */
	List<TayNghe> timKiemTatCaTayNghe();

	/**
	 * Cập nhật tay nghề
	 * 
	 * @param tayNghe là đối tượng cần cập nhật
	 * @return tayNghe là thông tin tay nghề mới cập nhật, null nếu cập nhật không
	 *         thành công
	 */
	TayNghe capNhatTayNghe(TayNghe tayNghe);

	/**
	 * Thêm tay nghề mới
	 * 
	 * @param tayNghe là thông tin tay nghề cần thêm
	 * @return tay nghề mới thêm
	 */
	TayNghe themTayNghe(TayNghe tayNghe);

	/**
	 * Tìm kiếm tay nghề bằng mã
	 * 
	 * @param tayNghe là mã tay nghề cần tìm
	 * @return tayNghe
	 */
	TayNghe timKiemBangMaTayNghe(String maTayNghe);

	/**
	 * Lấy mã tay nghề cuối cùng
	 * 
	 * @return tayNghe
	 */
	String timMaTayNgheCuoiCung();

	/**
	 * Xóa tay nghề bằng mã tay nghề
	 * 
	 * @param maTayNghe
	 * @return true: xóa thành công, false xóa không thành công
	 */
	boolean xoaTayNgheBangMa(String maTayNghe);
}
