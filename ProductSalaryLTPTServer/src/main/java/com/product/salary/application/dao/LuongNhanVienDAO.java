package com.product.salary.application.dao;

import com.product.salary.application.entity.LuongNhanVien;

import java.util.List;
import java.util.Map;

public interface LuongNhanVienDAO {
	/**
	 * Thống kê lương nhân viên theo nam
	 * 
	 * @return danh sách thông kế lương theo năm
	 */
	Map<String, Double> thongKeLuongNhanVienTheoNam();

	/**
	 * Hàm lấy số lượng ca sáng và ca chiều mà nhân viên đi làm, không bao gồm ngày
	 * chủ nhật
	 * 
	 * @param maNhanVien
	 * @param thang
	 * @param nam
	 * @return
	 */
	int laySoLuongCaSangVaChieuKhongThuocNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam);

	/**
	 * Hàm lấy số lượng ca tối mà nhân viên đi làm, không bao gồm ngày chủ nhật
	 * 
	 * @param maNhanVien
	 * @param thang
	 * @param nam
	 * @return
	 */
	int laySoLuongCaToiKhongThuocNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam);

	/**
	 * Hàm lấy số lượng ca làm mà nhân viên đi làm ngày chủ nhật
	 * 
	 * @param maNhanVien
	 * @param thang
	 * @param nam
	 * @return
	 */
	int laySoLuongCaLamNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam);

	/**
	 * Hàm tìm kiếm lương theo tháng, năm của các nhân viên
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	List<LuongNhanVien> timKiemTatCaLuongNhanVienTheoThangVaNam(int thang, int nam);

	/**
	 * Hàm tìm kiếm lương nhân viên bằng mã lương
	 * 
	 * @param maMuong
	 * @return
	 */
	LuongNhanVien timKiemBangMaLuong(String maLuong);

	/**
	 * Hàm thêm thông tin lương nhân viên vào CSDL
	 * 
	 * @param luongNV
	 * @return
	 */
	LuongNhanVien themLuongNhanVien(LuongNhanVien luongNV);

	/**
	 * Hàm cập nhật thông tin lương nhân viên
	 * 
	 * @param luongNV
	 * @return
	 */
	LuongNhanVien capNhatLuongNhanVien(LuongNhanVien luongNV);

	/**
	 * Hàm tìm kiếm tất cả chi tiết lương của nhân viên theo tháng và năm
	 * @param maNhanVien
	 * @param thang
	 * @param nam
	 * @return
	 */
	List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maNhanVien, int thang, int nam);

	/**
	 * 
	 * @param nam
	 * @return
	 */
	Map<String, Double> thongKeLuongNhanVienTheoThang(int nam);
	
	/**
	 * Hàm cập nhật lương nhân viên sau khi được thưởng
	 * @param maLuong
	 * @param luongThuong
	 */
	void capNhatLuongThuong(String maLuong, double luongThuong);
	
	/**
	 * hàm tính số lượng buổi trễ của nhân viên
	 * @param maNhanVien
	 * @param thang
	 * @param nam
	 * @return
	 */
	int demSoLuongDiLamTreCuaNhanVien(String maNhanVien, int thang, int nam);
	
	
	
}
