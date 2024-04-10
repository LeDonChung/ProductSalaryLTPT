package com.product.salary.application.dao;

import com.product.salary.application.entity.CongNhan;

import java.util.List;

public interface CongNhanDAO {

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
	 * Tìm kiếm bằng mã công nhân
	 * 
	 * @param maCongNhan là mã công nhân cần tìm
	 * @return công nhân
	 */
	CongNhan timKiemBangMaCongNhan(String maCongNhan);


	/**
	 * Tìm kiếm bằng mã công nhân
	 * 
	 * @param maCongNhan là mã công nhân cần tìm
	 * @return công nhân
	 */
	CongNhan timKiemBangCCCD(String cccd);

	/**
	 * Hàm lấy mã công nhân cuối cùng của năm vào làm
	 * 
	 * @param nam là năm đc truyền vào để truy vấn
	 * @return chuỗi ký tự là mã công nhhaan cuối cùng của năm
	 */
	String layMaCongNhanCuoiCungCuaNam(int nam);

	/**
	 * Tìm tổng số lượng công nhân
	 * 
	 * @return số lượng công nhân
	 */
	int tongSoLuongCongNhan();
}
