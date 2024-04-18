package com.product.salary.application.service;

import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.PhanCongCongNhan;

import java.util.List;

public interface PhanCongCongViecService {

	/**
	 * Cập nhật phân công công nhân
	 * 
	 * @param phanCongCongNhan
	 * @return
	 */
	PhanCongCongNhan capNhatPhanCongCongNhan(PhanCongCongNhan phanCongCongNhan);

	/**
	 * Xóa phân công công nhân bằng mã phân công
	 * 
	 * @param maPhanCong
	 * @return
	 */
	boolean xoaPhanCongCongNhan(String maPhanCong);

	/**
	 * Phân công công việc cho công nhân
	 * 
	 * @param phanCongCongNhan
	 * @return
	 */
	PhanCongCongNhan phanCongCongNhan(PhanCongCongNhan phanCongCongNhan);

	public String generateMaPhanCongCongNhan(String maCongNhan, String maCongDoan);


	List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(String maCongNhan);

	List<CongNhan> timTatCaCongNhanChuaPhanCongVaoCongDoan(String maSanPham, String maCongDoan);

	List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongDoan(String maCongDoan);

	List<PhanCongCongNhan> phanCongNhieuCongNhan(List<PhanCongCongNhan> phanCongCongNhans);

    PhanCongCongNhan timPhanCongTheoMa(String maPhanCong);
}
