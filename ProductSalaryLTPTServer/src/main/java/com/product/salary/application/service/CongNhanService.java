package com.product.salary.application.service;

import com.product.salary.application.entity.CongNhan;

import java.util.List;

public interface CongNhanService {

	/**
	 * Tìm kiếm tất cả công nhân
	 * 
	 * @return danh sách công nhân
	 */
	List<CongNhan> timKiemTatCaCongNhan();

	/**
	 * Hàm tìm kiếm công nhân theo mã tổ nhóm
	 * 
	 * @param maToNhom
	 * @return
	 */
	List<CongNhan> timKiemCongNhanBangMaToNhom(String maToNhom);

	/**
	 * Cập nhật thông tin công nhân
	 * 
	 * @param congNhan cần cập nhật
	 * @return thông tin congNhan vừa cập nhật
	 */
	CongNhan capNhatCongNhan(CongNhan congNhan);

	/**
	 * Cập nhật trạng thái công nhân
	 * 
	 * @param trangThai là trạng thái đi làm của công nhân true: đang làm, false: đã
	 *                  nghỉ
	 * @return thông tin công nhân vừa cập nhật
	 */
	boolean capNhatTrangThaiCongNhan(String maCongNhan, boolean trangThai);

	/**
	 * Tìm kiếm công nhân
	 * 
	 * @param congNhan
	 * @return
	 */
	List<CongNhan> timKiemCongNhan(CongNhan congNhan);

	/**
	 * Hàm thêm công nhân mới
	 * 
	 * @param congNhan là thông tin công nhân cần thêm
	 * @return công nhân mới
	 */
	CongNhan themCongNhan(CongNhan congNhan);

	/**
	 * Tạo mã công nhân mới
	 * 
	 * @return mã công nhân mới
	 */
	public String generateMaCongNhan(CongNhan congNhan);

	public boolean kiemTraCccd(String cccd);

	/**
	 * Tìm tổng số lượng công nhân
	 * 
	 * @return số lượng công nhân
	 */
	int tongSoLuongCongNhan();

	/**
	 * Tìm kiếm công nhân bằng mã công nhân
	 * 
	 * @param maCongNhan
	 * @return
	 */
	CongNhan timKiemBangMaCongNhan(String maCongNhan);
	
	/**
	 * Thêm nhiều sản phẩm bằng file excel
	 * @param dsCongNhan danh sách công nhân cần thêm
	 * @return danh sách công nhân đã thêm
	 */
	List<CongNhan> themNhieuCongNhan(List<CongNhan> dsCongNhan);
}
