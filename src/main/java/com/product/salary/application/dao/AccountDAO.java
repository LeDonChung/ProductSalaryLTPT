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
	 * Cập nhật thông tin tài khoản
	 * 
	 * @return account nếu cập nhật thành công, null nếu cập nhật không thành công
	 */
	public Account capNhatTaiKhoan(Account account);

	/**
	 * Thêm tài khoản
	 * 
	 * @return account nếu thêm thành công, null nếu thêm không thành công
	 */
	public Account themTaiKhoan(Account account);

	/**
	 * Cập nhật trạng thái tài khoản
	 * 
	 * @param taiKhoan  là tài khoản đăng nhập
	 * @param trangThai là trạng thái hoạt động của tài khoản
	 * @return true: cập nhật hành công, false cập nhật không thành công
	 */
	public boolean capNhatTrangThaiTaiKhoan(String taiKhoan, boolean trangThai);

	/**
	 * Tìm tài khoản bằng tài khoản và mật khẩu
	 * 
	 * @param taiKhoanS tài khoản cần tìm
	 * @param matKhauS  mật khẩu cần tìm
	 * @return account nếu tìm thành công, null nếu tìm không thành công
	 */
	Account timTaiKhoanBangTaiKhoan(String taiKhoanS, String matKhauS);

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
