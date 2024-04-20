package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PhanCongCongNhan")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
		@NamedQuery(name = "PhanCongCongNhan.timMaPhanCongCuoiCung", query = "SELECT MAX(p.maPhanCong) FROM PhanCongCongNhan p"),
		@NamedQuery(name = "PhanCongCongNhan.timTatCaPhanCongTheoMaCongNhanChuaHoanThanh", query = "SELECT p FROM PhanCongCongNhan p WHERE p.congNhan.maCongNhan = :maCongNhan AND p.trangThai = false"),
		@NamedQuery(name = "PhanCongCongNhan.timTatCaCongNhanChuaPhanCongVaoCongDoan", query = "SELECT c FROM CongNhan c WHERE c.maCongNhan NOT IN (SELECT DISTINCT p.congNhan.maCongNhan FROM PhanCongCongNhan p JOIN p.congDoanSanPham cd WHERE cd.sanPham.maSanPham = :maSanPham AND cd.maCongDoan = :maCongDoan AND p.trangThai = false)"),
		@NamedQuery(name = "PhanCongCongNhan.timTatCaPhanCongTheoMaCongDoan", query = "SELECT p FROM PhanCongCongNhan p WHERE p.congDoanSanPham.maCongDoan = :maCongDoan"),
})
public class PhanCongCongNhan implements Serializable {
	@Id
	@Column(name = "MaPhanCong", length = 15)
	private String maPhanCong;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaCongNhan")
	private CongNhan congNhan;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaCongDoan")
	private CongDoanSanPham congDoanSanPham;

	@Column(name = "NgayPhanCong", columnDefinition = "DATETIME NOT NULL")
	private LocalDate ngayPhanCong;

	@Column(name = "TrangThai", columnDefinition = "BIT NOT NULL")
	private boolean trangThai;

	public PhanCongCongNhan(String maPhanCong,
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



	public boolean isTrangThai() {
		return trangThai;
	}

}
