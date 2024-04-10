package com.product.salary.application.entity;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Entity
@Table(name = "Account")
@Getter
@ToString
@NoArgsConstructor
public class Account implements Serializable {
	@Id
	@Column(name = "TaiKhoan", columnDefinition = "nvarchar(255)")
	private String taiKhoan;

	@Column(name = "MatKhau", columnDefinition = "nvarchar(255) not null")
	private String matKhau;

	@Column(name = "RoleName", columnDefinition = "nvarchar(50) not null")
	private String roleName;

	@OneToOne
	@JoinColumn(name = "MaNhanVien")
	@SerializedName("nhanVien")
	private  NhanVien nhanVien;

	/**
	 * -- SETTER --
	 *
	 * @param trangThai the trangThai to set
	 */
	@Setter
	@Column(name = "TrangThai")
	private boolean trangThai;

	public Account(String taiKhoan, String matKhau, String roleName, NhanVien nhanVien, boolean trangThai)
			throws Exception {
		setTaiKhoan(taiKhoan);
		setMatKhau(matKhau);
		setNhanVien(nhanVien);
		setTrangThai(trangThai);
		setRoleName(roleName);
	}


	public Account(String taiKhoan, String matKhau) {
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.roleName = "";
		this.nhanVien = null;
		this.trangThai = false;
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
}
