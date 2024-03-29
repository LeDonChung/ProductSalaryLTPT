package com.product.salary.application.service;

import com.product.salary.application.entity.NhanVien;

import java.util.List;

public interface NhanVienService {
	/**
	 * Hàm tìm kiếm tất cả nhân viên
	 * 
	 * @return danh sách tất cả nhân viên
	 */
	List<NhanVien> timKiemTatCaNhanVien();

	/**
	 * Hàm cập nhật thông tin nhân viên
	 * 
	 * @param nhanVien là đối tượng cần cập nhật thông tin
	 * @return đối tượng nhân viên đã được cập nhật, hoặc null nếu cập nhật không
	 *         thành công
	 */
	NhanVien capNhatNhanVien(NhanVien nhanVien);

	/**
	 * Hàm cập nhật trạng thái làm việc của nhân viên: Đang làm / Đã nghỉ làm
	 * 
	 * @param maNhanVien: mã nhân viên cần cập nhật trạng thái
	 * @param trangThai:  trạng thái làm việc của nhân viên
	 * @return true nếu cập nhật thành công trạng thái nhân viên, false nếu cập nhật
	 *         không thành công
	 */
	boolean capNhatTrangThaiNghiLamCuaNhanVien(String maNhanVien);

	/**
	 * Hàm tìm kiếm nhân viên, có thể tìm bằng nhiều tiêu chí: mã nhân viên, họ tên,
	 * phòng ban, điện thoại, địa chỉ, CCCD, email, trình độ
	 * 
	 * @param nhanVien gồm nhiều thuộc tính là các tiêu chí
	 * @return danh sách nhân viên theo các tiêu chí cần tìm
	 */
	List<NhanVien> timKiemNhanVien(NhanVien nhanVien);

	/**
	 * Hàm tìm kiếm 1 nhân viên bằng mã nhân viên nhập vào
	 * 
	 * @param maNhanVien là của nhân viên cần tìm
	 * @return nhân viên có mã là maNhanVien, hoặc null nếu không tìm thấy
	 */
	NhanVien timKiemBangMaNhanVien(String maNhanVien);

	/**
	 * Hàm thêm thông tin của một nhân viên
	 * 
	 * @param nhanVien đối tượng để thêm vào csdl của chương trình
	 * @return nhân viên đã đucojw thêm thành công, hoặc null nếu thêm không thành
	 *         công
	 */
	NhanVien themNhanVien(NhanVien nhanVien);

	/**
	 * Hàm phát sinh mã nhân viên
	 * 
	 * @param nhanVien thông tin của nhân viên nhập vào
	 * @return mã nhân viên
	 */
	String generateMaNhanVien(NhanVien nhanVien);

	/**
	 * Hàm tìm kiếm nhân viên bằng mã phòng ban
	 * 
	 * @param maPhongBan
	 * @return danh sách nhân viên có mã phòng ban = maPhongBan truyền vào
	 */
	List<NhanVien> timKiemNhanVienBangMaPhongBan(String maPhongBan);

	/**
	 * Tìm tổng số lượng nhân viên
	 * @return số lượng nhân viên
	 */
	int tongSoLuongNhanVien();
	
	/**
	 * Hàm thêm thông tin cuar nhiều nhân viên 1 lượt
	 * @param dsNhanVien
	 * @return
	 */
	List<NhanVien> themNhieuNhanVien(List<NhanVien> dsNhanVien);
	
	/**
	 * Hàm tìm kiếm nhân viên bằng mã nhân viên và cccd của nhân viên
	 * @param maNhanVien
	 * @param cccd
	 * @return
	 */
	NhanVien timKiemBangMaNhanVienVaCccd(String maNhanVien, String cccd);
}
