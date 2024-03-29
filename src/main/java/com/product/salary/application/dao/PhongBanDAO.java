package com.product.salary.application.dao;

import com.product.salary.application.entity.PhongBan;

import java.util.List;

public interface PhongBanDAO {
	/**
	 * Hàm tìm kiếm tất cả phòng ban
	 * 
	 * @return danh sách các phòng ban được lưu trong hệ thống, CSDL
	 */
	List<PhongBan> timKiemTatCaPhongBan();

	/**
	 * Hàm tìm kiếm tất cả phòng ban còn đang hoạt động
	 * 
	 * @return danh sách phòng ban đang hoạt động
	 */
	List<PhongBan> timKiemTatCaPhongBanDangHoatDong();

	/**
	 * Hàm tìm kiếm phòng ban theo các tiêu chí
	 * 
	 * @param phongBan là đối tượng phòng ban gồm các biến là các tiêu chí tìm kiếm
	 * @return danh sách phòng ban khớp với các tiêu chí tìm
	 */
	List<PhongBan> timKiemPhongBan(PhongBan phongBan);

	/**
	 * Hàm tìm kiếm phòng ban theo mã
	 * 
	 * @param maPhongBan là mã của phòng ban cần tìm
	 * @return phòng ban có mã khớp với maPhongban, trả về null nếu không tìm thấy
	 */
	PhongBan timKiemBangMaPhongBan(String maPhongBan);

	/**
	 * Hàm cập nhật thông tin phòng ban
	 * 
	 * @param phongBan là đối tượng phòng ban cần cập nhật
	 * @return phòng ban đã cập nhật thành công, trả về null nếu cập nhật không
	 *         thành công
	 */
	PhongBan capNhatPhongBan(PhongBan phongBan);

	/**
	 * Hàm cập nhật trạng thái hoạt động của phòng ban
	 * 
	 * @param maPhongBan là mã của phòng ban cần cập nhật trạng thái
	 * @param trangThai  là trạng thái hoạt động của phòng ban
	 * @return true nếu cập nhật trạng thái thành công, false nếu cập nhật không
	 *         thành công
	 */
	boolean capNhatTrangThaiPhongBan(String maPhongBan, boolean trangThai);

	/**
	 * Hàm thêm thông tin một phòng ban vào hệ thống và CSDL
	 * 
	 * @param phongBan là đối tượng thêm vào
	 * @return Phòng ban đã được thêm vào, null nếu thêm không thành công
	 */
	PhongBan themPhongBan(PhongBan phongBan);

	/**
	 * Hàm cập nhật số lượng công nhân viên cuả phòng ban khi thêm / xóa nhân viên
	 * 
	 * @param maPhongBan mã phòng ban cần cập nhật
	 * @return true nếu cập nhật thành công, false nếu cập nhật không thành công
	 */
	boolean capNhatSoLuongNhanVienBangMaPhongBan(String maPhongBan, int soLuong);

	/**
	 * Hàm tìm kiếm mã phòng ban cuối cùng
	 * 
	 * @return trả về mã phòng ban cuối của danh sách phòng ban
	 */
	String timMaPhongbanCuoiCung();
}
