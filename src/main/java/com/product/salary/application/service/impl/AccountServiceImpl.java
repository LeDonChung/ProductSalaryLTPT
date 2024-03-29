package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.AccountDAO;
import com.product.salary.application.dao.impl.AccountDAOImpl;
import com.product.salary.application.entity.Account;
import com.product.salary.application.utils.PasswordUtils;
import com.product.salary.application.service.AccountService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
	private AccountDAO accountDAO;

	public AccountServiceImpl() {
		this.accountDAO = new AccountDAOImpl();
	}

	@Override
	public Account timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau(String taiKhoan, String matKhau) {
		Account account = null;
		try {
			// Kiểm tra tồn tại bằng cách mã hóa thông tin mật khẩu nhập vào
			String matKhauMaHoa = PasswordUtils.toSHA1(matKhau);
			account = this.accountDAO.timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau(taiKhoan, matKhauMaHoa);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public List<Account> timKiemTatCaTaiKhoan() {
		List<Account> account = new ArrayList<Account>();
		try {

			account = accountDAO.timKiemTatCaTaiKhoan();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account capNhatTaiKhoan(Account account) {
		try {
			Account isExists = accountDAO.timTaiKhoanBangTaiKhoan(account.getTaiKhoan(), account.getMatKhau());
			if (isExists == null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("Account.taiKhoangKhongTonTai"));
				return null;
			}

			return accountDAO.capNhatTaiKhoan(account);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account themTaiKhoan(Account account) {
		try {
			Account isExists = accountDAO.timTaiKhoanBangTaiKhoan(account.getTaiKhoan(), account.getMatKhau());
			if (isExists != null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("Account.taiKhoangTonTai"));
				return null;
			}
			return accountDAO.themTaiKhoan(account);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean capNhatTrangThaiTaiKhoan(String taiKhoan, boolean trangThai) {
		try {
			return accountDAO.capNhatTrangThaiTaiKhoan(taiKhoan, trangThai);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean capNhatMatKhau(String taiKhoan, String matKhau) {
		try {
			// Kiểm tra tồn tại bằng cách mã hóa thông tin mật khẩu nhập vào
			String matKhauMaHoa = PasswordUtils.toSHA1(matKhau);
			return accountDAO.capNhatMatKhau(taiKhoan, matKhauMaHoa);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Account timTaiKhoanBangTaiKhoan(String taiKhoanS, String matKhauS) {
		Account account = null;
		try {
			account = this.accountDAO.timTaiKhoanBangTaiKhoan(taiKhoanS, matKhauS);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account timKiemBangTaiKhoan(String taiKhoan) {
		Account account = null;
		try {
			account = accountDAO.timKiemBangTaiKhoan(taiKhoan);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return account;
	}

}
