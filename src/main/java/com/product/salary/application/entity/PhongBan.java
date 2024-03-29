package com.product.salary.application.entity;

import java.io.Serializable;
import java.util.Objects;

public class PhongBan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maPhongBan;
	private String tenPhongBan;
	private int soLuongNhanVien;
	private Boolean trangThai;

	public PhongBan(String maPhongBan, String tenPhongBan, int soLuongNhanVien, Boolean trangThai) throws Exception {
		setMaPhongBan(maPhongBan);
		setTenPhongBan(tenPhongBan);
		setSoLuongNhanVien(soLuongNhanVien);
		setTrangThai(trangThai);
	}

	public PhongBan(String maPhongBan, String tenPhongBan) {
		super();
		this.maPhongBan = maPhongBan;
		this.tenPhongBan = tenPhongBan;
	}

	public PhongBan(String maPhongBan, String tenPhongBan, Boolean trangThai) {
		this.maPhongBan = maPhongBan;
		this.tenPhongBan = tenPhongBan;
		this.trangThai = trangThai;
	}

	public PhongBan() {

	}

	/**
	 * @return the maPhongBan
	 */
	public String getMaPhongBan() {
		return maPhongBan;
	}

	/**
	 * @param maPhongBan the maPhongBan to set
	 */
	public void setMaPhongBan(String maPhongBan) {
		this.maPhongBan = maPhongBan;
	}

	/**
	 * @return the tenPhongBan
	 */
	public String getTenPhongBan() {
		return tenPhongBan;
	}

	/**
	 * @param tenPhongBan the tenPhongBan to set
	 * @throws Exception
	 */
	public void setTenPhongBan(String tenPhongBan) throws Exception {
		if (tenPhongBan.equals(""))
			throw new Exception("Tên phòng ban không được rỗng");
		else
			this.tenPhongBan = tenPhongBan;
	}

	/**
	 * @return the soLuongNhanVien
	 */
	public int getSoLuongNhanVien() {
		return soLuongNhanVien;
	}

	/**
	 * @param soLuongNhanVien the soLuongNhanVien to set
	 * @throws Exception
	 */
	public void setSoLuongNhanVien(int soLuongNhanVien) throws Exception {
		if (soLuongNhanVien < 0)
			throw new Exception("Số lượng nhân viên phải >= 0");
		else
			this.soLuongNhanVien = soLuongNhanVien;
	}

	/**
	 * @return the trangThai
	 */
	public Boolean isTrangThai() {
		return trangThai;
	}

	/**
	 * @param trangThai the trangThai to set
	 */
	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhongBan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongBan other = (PhongBan) obj;
		return Objects.equals(maPhongBan, other.maPhongBan);
	}

	@Override
	public String toString() {
		return this.tenPhongBan;
	}
}
