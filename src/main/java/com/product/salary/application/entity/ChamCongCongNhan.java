package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ChamCongCongNhan")
public class ChamCongCongNhan implements Serializable {

	@Id
	@Column(name = "MaChamCong", length = 15)
	private String maChamCong;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaPhanCong")
	private PhanCongCongNhan phanCongCongNhan;

	@Column(name = "SoLuongHoanThanh", columnDefinition = "INT NOT NULL CHECK(SoLuongHoanThanh > 0)")
	private int soLuongHoanThanh;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaCa")
	private CaLam caLam;

	@Column(name = "NgayChamCong", columnDefinition = "DATETIME CHECK(NgayChamCong <= GETDATE())")
	private LocalDate ngayChamCong;

	@Column(name = "TrangThai", columnDefinition = "INT NOT NULL DEFAULT 1")
	private int trangThai;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "MaLuong")
	private LuongCongNhan luongCongNhan;

	public ChamCongCongNhan(String maChamCong, PhanCongCongNhan phanCongCongNhan, int soLuongHoanThanh, CaLam caLam,
                            LocalDate ngayChamCong, int trangThai, LuongCongNhan luongCongNhan) throws Exception {
		setMaChamCong(maChamCong);
		setPhanCongCongNhan(phanCongCongNhan);
		setSoLuongHoanThanh(soLuongHoanThanh);
		setCaLam(caLam);
		setNgayChamCong(ngayChamCong);
		setTrangThai(trangThai);
		setLuongCongNhan(luongCongNhan);
	}

	public void setSoLuongHoanThanh(int soLuongHoanThanh) throws Exception {
		if (soLuongHoanThanh < 0) {
			throw new Exception("Số lượng hoàn thành >= 0");
		} else {
			this.soLuongHoanThanh = soLuongHoanThanh;
		}
	}

	public void setNgayChamCong(LocalDate ngayChamCong) throws Exception {
		if (ngayChamCong.isAfter(LocalDate.now())) {
			throw new Exception("Ngày chấm công phải <= ngày hiện tại");
		} else {
			this.ngayChamCong = ngayChamCong;
		}
	}
}
