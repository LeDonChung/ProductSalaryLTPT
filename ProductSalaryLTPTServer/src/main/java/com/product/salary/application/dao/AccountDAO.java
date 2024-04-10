package com.product.salary.application.dao;

import com.product.salary.application.entity.Account;

import java.util.List;

public interface AccountDAO {

	/**
	 * Tìm kiếm tài khoản đang hoạt động bằng tài khoản và mật khẩu
	 * 
	 * @return account nếu tồn tại, null nếu không tồn tại
	 */
	public Account timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau(String taiKhoan, String matKhau);

	/**
	 * Tìm kiếm tất cả tài khoản
	 * 
	 * @return danh sách tài khoản
	 */
	public List<Account> timKiemTatCaTaiKhoan();


	/**
	 * Cập nhật mật khẩu
	 * 
	 * @param taiKhoan
	 * @param matKhau
	 * @return true nếu cập nhật thành công, false nếu cập nhật không thành công
	 */
	boolean capNhatMatKhau(String taiKhoan, String matKhau);
	
	/**
	 * Hàm tìm kiếm tài khoản bằng tài khoản nhân viên nhập vào
	 * @param taiKhoan
	 * @return
	 */
	Account timKiemBangTaiKhoan(String taiKhoan);
}
