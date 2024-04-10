package com.product.salary.application.dao;

import com.product.salary.application.entity.ChamCongCongNhan;
import com.product.salary.application.entity.CongNhan;

import java.time.LocalDate;
import java.util.List;

public interface ChamCongCongNhanDAO {

	/**
	 * Tìm tất cả chấm công của công nhân
	 * @return danh sách chấm công
	 */
	List<ChamCongCongNhan> timTatCaChamCongCongNhan(LocalDate ngayChamCong);
	
	/**
	 * Tìm tất cả công nhân chưa chấm công theo ca và ngày chấm công
	 * @param ngayChamCong
	 * @param caLam
	 * @return danh sách công nhân chưa chấm công
	 */
	List<CongNhan> timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(LocalDate ngayChamCong, String caLam);
	
	/**
	 * Tìm kiếm chấm công công nhân bằng mã chấm công
	 * @param maChamCong
	 * @return
	 */
	ChamCongCongNhan timKiemBangMaChamCongCongNhan(String maChamCong);
	
	/**
	 * Chấm công cho công nhân
	 * @param chamCong
	 * @return công nhân đã chấm công
	 */
	ChamCongCongNhan themChamCongCongNhan(ChamCongCongNhan chamCong);
	
	/**
	 * Cập nhật chấm công của công nhân 
	 * @param chamCong
	 * @return 
	 */
	boolean capNhatChamCongCongNhan(String maChamCong, int trangThai, int soLuongHoanThanh);

	/**
	 * Hàm tìm kiếm mã chấm công cuối cùng của ngày và ca làm
	 * @param ngayChamCong
	 * @return mã chấm công cuối cùng của ngày, null nếu không tìm thấy
	 */
	String timKiemMaChamCongCongNhanCuoiCungTheoNgayVaCaLam(LocalDate ngayChamCong, String caLam);

	List<CongNhan> timDanhSachCongNhanDiLamBangThangVaNam(int thang, int nam);

	/**
	 * Cập nhật danh sách chấm công bằng mã công nhân, tháng và năm
	 * 
	 * @param maCongNhan
	 * @param thang
	 * @param nam
	 */
	boolean capNhatChamCongCongNhanBangMaCongNhanVaThangVaNam(String maCongNhan, int thang, int nam, String maLuong);
}
