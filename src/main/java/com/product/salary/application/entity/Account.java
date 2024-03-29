package com.product.salary.application.entity;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String taiKhoan;
	private String matKhau;
	private String roleName;
	private NhanVien nhanVien;
	private boolean trangThai;

	public Account(String taiKhoan, String matKhau, String roleName, NhanVien nhanVien, boolean trangThai)
			throws Exception {
		setTaiKhoan(taiKhoan);
		setMatKhau(matKhau);
		setNhanVien(nhanVien);
		setTrangThai(trangThai);
		setRoleName(roleName);
	}

	public Account() {
		super();
	}
	

	public Account(String taiKhoan, String matKhau) {
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
	}

	/**
	 * @return the taiKhoan
	 */
	public String getTaiKhoan() {
		return taiKhoan;
	}

	/**
	 * @param taiKhoan the taiKhoan to set
	 * @throws Exception
	 */
	public void setTaiKhoan(String taiKhoan) throws Exception {
		if (taiKhoan.trim().equals("")) {
			throw new Exception("Tài khoản không được rỗng");
		} else {
			this.taiKhoan = taiKhoan;
		}
	}

	/**
	 * @return the matKhau
	 */
	public String getMatKhau() {
		return matKhau;
	}

	/**
	 * @param matKhau the matKhau to set
	 * @throws Exception
	 */
	public void setMatKhau(String matKhau) throws Exception {
		if (matKhau.trim().equals("")) {
			throw new Exception("Mật khẩu không được rỗng");
		} else {
			this.matKhau = matKhau;
		}
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 * @throws Exception
	 */
	public void setRoleName(String roleName) throws Exception {
		if (roleName.trim().equals("")) {
			throw new Exception("Vai trò không được rỗng");
		} else {
			this.roleName = roleName;
		}
	}

	/**
	 * @return the nhanVien
	 */
	public NhanVien getNhanVien() {
		return nhanVien;
	}

	/**
	 * @param nhanVien the nhanVien to set
	 * @throws Exception
	 */
	public void setNhanVien(NhanVien nhanVien) throws Exception {
		if (nhanVien == null) {
			throw new Exception("Nhân viên không được lệ.");
		} else {
			this.nhanVien = nhanVien;
		}
	}

	/**
	 * @return the trangThai
	 */
	public boolean isTrangThai() {
		return trangThai;
	}

	/**
	 * @param trangThai the trangThai to set
	 */
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "Account [taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", roleName=" + roleName + ", nhanVien="
				+ nhanVien + ", trangThai=" + trangThai + "]";
	}
	
}
