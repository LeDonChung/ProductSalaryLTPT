package com.product.salary.application.service;

import com.product.salary.application.entity.ToNhom;

import java.util.List;


public interface ToNhomService {
	/**
	 * Hàm tìm kiếm tất cả tổ nhóm
	 * 
	 * @return danh sách các tổ nhóm được lưu trong hệ thống, CSDL
	 */
	List<ToNhom> timKiemTatCaToNhom();

	/**
	 * Hàm tìm kiếm tất cả tổ nhóm còn đg hoạt động
	 * 
	 * @return danh sach tổ nhóm đang hoạt động
	 */
	List<ToNhom> timKiemTatCaToNhomDangHoatDong();

	/**
	 * Hàm tìm kiếm tổ nhóm theo các tiêu chí
	 * 
	 * @param toNhom là đối tượng tổ nhóm gồm các biến là các tiêu chí tìm kiếm
	 * @return danh sách tổ nhóm khớp với các tiêu chí tìm
	 */
	List<ToNhom> timKiemToNhom(ToNhom toNhom);

	/**
	 * Hàm tìm kiếm tổ nhóm theo mã
	 * 
	 * @param maToNhom là mã cua tổ nhóm cần tìm
	 * @return tổ nhóm có mã khớp với maToNhom, trả về null nếu không tìm thấy
	 */
	ToNhom timKiemBangMaToNhom(String maToNhom);

	/**
	 * Hàm cập nhật thông tin tổ nhóm
	 * 
	 * @param toNhom là đối tượng tổ nhóm cần cập nhật
	 * @return phòng bantổ nhóm đã cập nhật thành công, trả về null nếu cập nhật
	 *         không thành công
	 */
	ToNhom capNhatToNhom(ToNhom toNhom);

	/**
	 * Hàm cập nhật trạng thái hoạt động của tổ nhóm
	 * 
	 * @param maToNhom  là mã của phòng ban cần cập nhật trạng thái
	 * @param trangThai là trạng thái hoạt động của tổ nhóm
	 * @return true nếu cập nhật trạng thái thành công, false nếu cập nhật không
	 *         thành công
	 */
	boolean capNhatTrangThaiToNhom(String maToNhom, boolean trangThai);

	/**
	 * Hàm thêm thông tin một tổ nhóm vào hệ thống và CSDL
	 * 
	 * @param toNhom là đối tượng thêm vào
	 * @return tổ nhóm đã được thêm vào, null nếu thêm không thành công
	 */
	ToNhom themToNhom(ToNhom toNhom);

	/**
	 * Hàm cập nhật số lượng công nhân cuả tổ nhóm khi thêm / xóa công nhân
	 * 
	 * @param maToNhom mã tổ nhóm cần cập nhật
	 * @return true nếu cập nhật thành công, false nếu cập nhật không thành công
	 */
	boolean capNhatSoLuongCongNhanBangMaToNhom(String maToNhom, int soLuong);
	
	/**
	 * Hàm phát sinh mã tổ nhóm
	 * @return
	 */
	String generateMaToNhom();
	
	List<ToNhom> themNhieuToNhom(List<ToNhom> dsToNhom);
}
