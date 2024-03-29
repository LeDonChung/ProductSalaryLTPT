package com.product.salary.application.entity;

import java.time.LocalDate;

public class LuongNhanVien {
	private String maLuong;
	private int thang;
	private int nam;
	private LocalDate ngayTinhLuong;
	private double luong;
	private double luongThuong;

	public LuongNhanVien(String maLuong, int thang, int nam, LocalDate ngayTinhLuong, double luong,
			double luongThuong) {
		this.maLuong = maLuong;
		this.thang = thang;
		this.nam = nam;
		this.ngayTinhLuong = ngayTinhLuong;
		this.luong = luong;
		this.luongThuong = luongThuong;
	}

	public LuongNhanVien() {
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
		if (nam <= LocalDate.now().getYear() && thang >= LocalDate.now().getMonthValue())
			throw new Exception(String.format("Tháng tính lương phải <= tháng %d năm %d",
					LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
		else
			this.thang = thang;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) throws Exception {
		if (nam > LocalDate.now().getYear())
			throw new Exception(String.format("Năm tính lương phải <= năm %d", LocalDate.now().getYear()));
		else
			this.nam = nam;
	}

	public LocalDate getNgayTinhLuong() {
		return ngayTinhLuong;
	}

	public void setNgayTinhLuong(LocalDate ngayTinhLuong) throws Exception {
		if (ngayTinhLuong.isAfter(LocalDate.now()) || ngayTinhLuong.isBefore(LocalDate.now()))
			throw new Exception("Ngày tính lương phải là ngày hiện tại");
		else
			this.ngayTinhLuong = ngayTinhLuong;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public double getLuongThuong() {
		return luongThuong;
	}

	public void setLuongThuong(double luongThuong) throws Exception {
		if (luongThuong < 0)
			throw new Exception("Lương thưởng phải >= 0");
		else
			this.luongThuong = luongThuong;
	}

	@Override
	public String toString() {
		return "LuongNhanVien [maLuong=" + maLuong + ", thang=" + thang + ", nam=" + nam + ", ngayTinhLuong="
				+ ngayTinhLuong + ", luong=" + luong + ", luongThuong=" + luongThuong + "]";
	}

}
