package com.product.salary.application.entity;

import java.io.Serializable;

public class ToNhom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maToNhom;
	private String tenToNhom;
	private int soLuongCongNhan;
	private Boolean trangThai;

	public ToNhom(String maToNhom, String tenToNhom, int soLuongCongNhan, Boolean trangThai) {
		setMaToNhom(maToNhom);
		setTenToNhom(tenToNhom);
		setSoLuongCongNhan(soLuongCongNhan);
		setTrangThai(trangThai);
	}

	public ToNhom(String maToNhom, String tenToNhom) {
		this.maToNhom = maToNhom;
		this.tenToNhom = tenToNhom;
	}

	public ToNhom(String maToNhom) {
		this.maToNhom = maToNhom;
	}

	public ToNhom() {
	}

	public String getMaToNhom() {
		return maToNhom;
	}

	public void setMaToNhom(String maToNhom) {
		this.maToNhom = maToNhom;
	}

	public String getTenToNhom() {
		return tenToNhom;
	}

	public void setTenToNhom(String tenToNhom) {
		this.tenToNhom = tenToNhom;
	}

	public int getSoLuongCongNhan() {
		return soLuongCongNhan;
	}

	public void setSoLuongCongNhan(int soLuongCongNhan) {
		this.soLuongCongNhan = soLuongCongNhan;
	}

	public Boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return this.tenToNhom;
	}

}
