package com.product.salary.application.service;

import com.product.salary.application.entity.CaLam;
import com.product.salary.application.entity.ChamCongCongNhan;
import com.product.salary.application.entity.CongNhan;

import java.time.LocalDate;
import java.util.List;

public interface ChamCongCongNhanService {

	/**
	 * Tìm kiếm tất cả chấm công của công nhân
	 * @return danh sách chấm công của tất cả công nhân
	 */
	List<ChamCongCongNhan> timKiemTatCaChamCongCongNhan(LocalDate ngayChamCong);
	
	/**
	 * Tìm tất cả công nhân chưa chấm công theo ca và ngày chấm công
	 * @param ngayChamCong
	 * @param caLam
	 * @return danh sách công nhân chưa chấm công
	 */
	List<CongNhan> timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(LocalDate ngayChamCong, String caLam);
	
	/**
	 * Chấm công cho công nhân
	 * @param chamCong
	 * @return công nhân đã chấm công
	 */
	ChamCongCongNhan themChamCongCongNhan(ChamCongCongNhan chamCong);
	
	
	//public String genertateMaChamCongCongNhan(LocalDate ngayChamCong);
	
	/**
	 * Cập nhật chấm công của công nhân 
	 * @param chamCong
	 * @return 
	 */
	boolean capNhatChamCongCongNhan(String maChamCong, int trangThai, int soLuongHoanThanh);
	 String genertateMaChamCongCongNhan(LocalDate ngayChamCong, CaLam caLam);
}
