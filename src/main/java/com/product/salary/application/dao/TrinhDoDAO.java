package com.product.salary.application.dao;

import com.product.salary.application.entity.TrinhDo;

import java.util.List;

public interface TrinhDoDAO {
	
	/**
	 * Hàm tìm kiếm tất cả trình độ
	 * @return danh sách trình độ
	 */
	List<TrinhDo> timKiemTatCaTrinhDo();
	
	/**
	 * Hàm tìm kiếm trình độ bằng mã trình độ
	 * @return Trình độ có mã là mã trình độ truyền vào
	 */
	TrinhDo timKiemBangMaTrinhDo(String maTrinhDo);
}
