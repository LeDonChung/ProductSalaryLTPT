package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "LuongCongNhan")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LuongCongNhan implements Serializable {
	@Id
	@Column(name = "MaLuong", length = 16)
	private String maLuong;

	@Column(name = "Thang")
	private int thang;

	@Column(name = "Nam")
	private int nam;

	@Column(name = "NgayTinhLuong", columnDefinition = "DATETIME NOT NULL DEFAULT GETDATE()")
	private LocalDate ngayTinhLuong;

	@Column(name = "Luong", columnDefinition = "FLOAT NOT NULL")
	private double luong;

	@Column(name = "LuongThuong", columnDefinition = "FLOAT NOT NULL")
	private double luongThuong;


	@OneToMany(mappedBy = "luongCongNhan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ToString.Exclude
	private transient Set<ChamCongCongNhan> chamCongCongNhans;

	public LuongCongNhan(String maLuong, int thang, int nam, LocalDate ngayTinhLuong, double luong, double luongThuong)
			throws Exception {
		setMaLuong(maLuong);
		setThang(thang);
		setNam(nam);
		setNgayTinhLuong(ngayTinhLuong);
		setLuong(luong);
		setLuongThuong(luongThuong);
	}

	public void setThang(int thang) throws Exception {
		if (thang > 12 || thang < 1) {
			throw new Exception(" 1 <= Tháng <= 12");
		} else {
			this.thang = thang;
		}
	}

	public void setNam(int nam) throws Exception {
		if (nam > LocalDate.now().getYear() || nam < 1990) {
			throw new Exception("Năm phải <= năm hiện tại");
		} else {
			this.nam = nam;
		}
	}

	public void setNgayTinhLuong(LocalDate ngayTinhLuong) throws Exception {
		if (ngayTinhLuong.isAfter(LocalDate.now())) {
			throw new Exception("Ngày tính lương <= ngày hiện tại");
		} else {
			this.ngayTinhLuong = ngayTinhLuong;
		}
	}

	public void setLuong(double luong) throws Exception {
		if (luong < 0) {
			throw new Exception("Lương phải > 0");
		} else {
			this.luong = luong;
		}
	}

	public void setLuongThuong(double luongThuong) throws Exception {
		if (luongThuong < 0) {
			throw new Exception("Lương thưởng phải > 0");
		} else {
			this.luongThuong = luongThuong;
		}
	}


}
