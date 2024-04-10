package com.product.salary.application.service;

import com.product.salary.application.entity.ChamCongNhanVien;
import com.product.salary.application.entity.NhanVien;

import java.time.LocalDate;
import java.util.List;

public interface ChamCongNhanVienService {

	/**
	 * Hàm tìm kiếm tất cả các chấm công của nhân viên theo ngày và ca làm
	 * 
	 * @return danh sách chấm công của tất cả nhân viên
	 */
	List<ChamCongNhanVien> timKiemTatCaChamCongNhanVienTheoCaVaNgay(LocalDate ngayChamCong, String maCa);

	/**
	 * Hàm tìm kiếm tất cả nhân viên chauw được chấm công theo ca làm và ngày làm
	 * 
	 * @param ngayChamCong
	 * @param caLam
	 * @return danh sách nhân viên chư được chấm công ca làm và ngày làm đó
	 */
	List<NhanVien> timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong(LocalDate ngayChamCong, String caLam);

	/**
	 * Hàm thêm 1 chấm công nhân viên
	 * 
	 * @param chamCongNV thông tin chấm công của nv
	 * @return chấm công nhân viên
	 */
	ChamCongNhanVien themChamCongNhanVien(ChamCongNhanVien chamCongNV);

	/**
	 * Hàm tìm kiếm chấm công nhân viên bằng mã chấm công
	 * 
	 * @param maChamCong
	 * @return chấm công nhân viên có mã = maChamCong, return null nếu không tìm
	 *         thấy
	 */
	ChamCongNhanVien timKiemBangMaChamCongNhanVien(String maChamCong);
	
	/**
	 * Hàm cập nhật trạng thái đi là của nhân viên 
	 * @param maChamCong
	 * @param trangThai
	 * @return true nếu cập nhật thành công, false nếu cập nhật không thành công
	 */
	boolean capNhatTrangThaiDiLamCuaNhanVien(String maChamCong, int trangThai);
}
