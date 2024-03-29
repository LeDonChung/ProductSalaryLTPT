package com.product.salary.application.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class LuongCongNhan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maLuong;
	private int thang;
	private int nam;
	private LocalDate ngayTinhLuong;
	private double luong;
	private double luongThuong;

	public LuongCongNhan() {
		
	}

	public LuongCongNhan(String maLuong, int thang, int nam, LocalDate ngayTinhLuong, double luong, double luongThuong)
			throws Exception {
		setMaLuong(maLuong);
		setThang(thang);
		setNam(nam);
		setNgayTinhLuong(ngayTinhLuong);
		setLuong(luong);
		setLuongThuong(luongThuong);
	}

	public String getMaLuong() {
		return maLuong;
	}

	public void setMaLuong(String maLuong) {
		this.maLuong = maLuong;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) throws Exception {
		if (thang > 12 || thang < 1) {
			throw new Exception(" 1 <= Tháng <= 12");
		} else {
			this.thang = thang;
		}
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) throws Exception {
		if (nam > LocalDate.now().getYear() || nam < 1990) {
			throw new Exception("Năm phải <= năm hiện tại");
		} else {
			this.nam = nam;
		}
	}

	public LocalDate getNgayTinhLuong() {
		return ngayTinhLuong;
	}

	public void setNgayTinhLuong(LocalDate ngayTinhLuong) throws Exception {
		if (ngayTinhLuong.isAfter(LocalDate.now())) {
			throw new Exception("Ngày tính lương <= ngày hiện tại");
		} else {
			this.ngayTinhLuong = ngayTinhLuong;
		}
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) throws Exception {
		if (luong < 0) {
			throw new Exception("Lương phải > 0");
		} else {
			this.luong = luong;
		}
	}

	public double getLuongThuong() {
		return luongThuong;
	}

	public void setLuongThuong(double luongThuong) throws Exception {
		if (luongThuong < 0) {
			throw new Exception("Lương thưởng phải > 0");
		} else {
			this.luongThuong = luongThuong;
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "LuongCongNhan [maLuong=" + maLuong + ", thang=" + thang + ", nam=" + nam + ", ngayTinhLuong="
				+ ngayTinhLuong + ", luong=" + luong + ", luongThuong=" + luongThuong + "]";
	}

}
