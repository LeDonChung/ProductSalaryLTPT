package com.product.salary.application.dao;

import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.PhanCongCongNhan;

import java.util.List;


public interface PhanCongCongViecDAO {

	
	/**
	 * Cập nhật phân công công nhân
	 * 
	 * @param phanCongCongNhan
	 * @return
	 */
	PhanCongCongNhan capNhatPhanCongCongNhan(PhanCongCongNhan phanCongCongNhan);
	
	/**
	 * Tìm kiếm phân công bằng mã phân công
	 * @param maPhanCong
	 * @return
	 */
	PhanCongCongNhan timKiemBangMaPhanCong(String maPhanCong);
	
	/**
	 * Xóa phân công công nhân bằng mã phân công
	 * @param maPhanCong
	 * @return
	 */
	boolean xoaPhanCongCongNhan(String maPhanCong);
	
	/**
	 * Phân công công việc cho công nhân
	 * @param phanCongCongNhan
	 * @return
	 */
	PhanCongCongNhan phanCongCongNhan(PhanCongCongNhan phanCongCongNhan);
	
	/**
	 * Tìm mã cuối cùng của phân công công việc bằng mã phân công
	 * @param maPhanCong
	 * @return
	 */
	String timMaPhanCongCuoiCung();

	
	List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(String maCongNhan);
	
	List<CongNhan> timTatCaCongNhanChuaPhanCongVaoCongDoan(String maSanPham, String maCongDoan);

	List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongDoan(String maCongDoan);
}
