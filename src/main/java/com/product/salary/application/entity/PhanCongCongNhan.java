package com.product.salary.application.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class PhanCongCongNhan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maPhanCong;
	private CongNhan congNhan;
	private CongDoanSanPham congDoanSanPham;
	private LocalDate ngayPhanCong;
	private boolean trangThai;

	public PhanCongCongNhan() {

	}

	public PhanCongCongNhan(String maPhanCong, CongNhan congNhan, CongDoanSanPham congDoanSanPham,
                            LocalDate ngayPhanCong, boolean trangThai) throws Exception {
		setMaPhanCong(maPhanCong);
		setCongNhan(congNhan);
		setCongDoanSanPham(congDoanSanPham);
		setNgayPhanCong(ngayPhanCong);
		setTrangThai(trangThai);
	}

	public PhanCongCongNhan(String maPhanCong, CongNhan congNhan, CongDoanSanPham congDoanSanPham,
                            LocalDate ngayPhanCong) {
		this.maPhanCong = maPhanCong;
		this.congNhan = congNhan;
		this.congDoanSanPham = congDoanSanPham;
		this.ngayPhanCong = ngayPhanCong;
	}

	public PhanCongCongNhan(String maPhanCong, CongNhan congNhan, CongDoanSanPham congDoanSanPham) {
		this.maPhanCong = maPhanCong;
		this.congNhan = congNhan;
		this.congDoanSanPham = congDoanSanPham;
	}

	public PhanCongCongNhan(String maPhanCong, boolean trangThai) {
		this.maPhanCong = maPhanCong;
		this.trangThai = trangThai;
	}

	public String getMaPhanCong() {
		return maPhanCong;
	}

	public void setMaPhanCong(String maPhanCong) {
		this.maPhanCong = maPhanCong;
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}

	public CongDoanSanPham getCongDoanSanPham() {
		return congDoanSanPham;
	}

	public void setCongDoanSanPham(CongDoanSanPham congDoanSanPham) {
		this.congDoanSanPham = congDoanSanPham;
	}

	public LocalDate getNgayPhanCong() {
		return ngayPhanCong;
	}

	public void setNgayPhanCong(LocalDate ngayPhanCong) throws Exception {
		this.ngayPhanCong = ngayPhanCong;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PhanCongCongNhan [maPhanCong=" + maPhanCong + ", congNhan=" + congNhan + ", congDoanSanPham="
				+ congDoanSanPham + ", ngayPhanCong=" + ngayPhanCong + ", trangThai=" + trangThai + "]";
	}
	
}
