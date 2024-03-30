package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "LuongNhanVien")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LuongNhanVien {

	@Id
	@Column(name = "MaLuong", length = 15)
	private String maLuong;

	@Column(name = "Thang", nullable = false)
	private int thang;

	@Column(name = "Nam", nullable = false)
	private int nam;

	@Column(name = "NgayTinhLuong", columnDefinition = "DATETIME NOT NULL")
	private LocalDate ngayTinhLuong;

	@Column(name = "Luong", columnDefinition = "REAL NOT NULL")
	private double luong;

	@Column(name = "LuongThuong", columnDefinition = "REAL NOT NULL")
	private double luongThuong;

	@ToString.Exclude
	@OneToMany(mappedBy = "luongNhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ChamCongNhanVien> chamCongNhanViens;

	public LuongNhanVien(String maLuong, int thang, int nam, LocalDate ngayTinhLuong, double luong,
			double luongThuong) {
		this.maLuong = maLuong;
		this.thang = thang;
		this.nam = nam;
		this.ngayTinhLuong = ngayTinhLuong;
		this.luong = luong;
		this.luongThuong = luongThuong;
	}


	public void setThang(int thang) throws Exception {
		if (nam <= LocalDate.now().getYear() && thang >= LocalDate.now().getMonthValue())
			throw new Exception(String.format("Tháng tính lương phải <= tháng %d năm %d",
					LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
		else
			this.thang = thang;
	}

	public void setNam(int nam) throws Exception {
		if (nam > LocalDate.now().getYear())
			throw new Exception(String.format("Năm tính lương phải <= năm %d", LocalDate.now().getYear()));
		else
			this.nam = nam;
	}


	public void setNgayTinhLuong(LocalDate ngayTinhLuong) throws Exception {
		if (ngayTinhLuong.isAfter(LocalDate.now()) || ngayTinhLuong.isBefore(LocalDate.now()))
			throw new Exception("Ngày tính lương phải là ngày hiện tại");
		else
			this.ngayTinhLuong = ngayTinhLuong;
	}

	public void setLuongThuong(double luongThuong) throws Exception {
		if (luongThuong < 0)
			throw new Exception("Lương thưởng phải >= 0");
		else
			this.luongThuong = luongThuong;
	}

}
