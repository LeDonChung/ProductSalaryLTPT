package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CongDoanSanPham")
public class CongDoanSanPham implements Serializable {
	@Id
	@Column(name = "MaCongDoan", length = 15)
	private String maCongDoan;

	@Column(name = "TenCongDoan", length = 70, nullable = false)
	private String tenCongDoan;

	@Column(name = "SoLuongCanLam", nullable = false)
	private int soLuongCanLam;

	@Column(name = "GiaCongDoan", columnDefinition = "REAL NOT NULL")
	private double giaCongDoan;

	@Column(name = "ThoiHan", columnDefinition = "DATETIME NOT NULL")
	private LocalDate thoiHan;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaCongDoanLamTruoc")
	private CongDoanSanPham congDoanLamTruoc;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaSanPham")
	private SanPham sanPham;

	@Column(name = "TrangThai", nullable = false)
	private boolean trangThai;

	@Override
	public int hashCode() {
		return Objects.hash(maCongDoan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongDoanSanPham other = (CongDoanSanPham) obj;
		return Objects.equals(maCongDoan, other.maCongDoan);
	}

	public CongDoanSanPham(String maCongDoan, String tenCongDoan, int soLuongCanLam, double giaCongDoan,
                           LocalDate thoiHan, CongDoanSanPham congDoanLamTruoc, SanPham sanPham, boolean trangThai) throws Exception {
		setMaCongDoan(maCongDoan);
		setTenCongDoan(tenCongDoan);
		setSoLuongCanLam(soLuongCanLam);
		setGiaCongDoan(giaCongDoan);
		setThoiHan(thoiHan);
		setSanPham(sanPham);
		setTrangThai(trangThai);
		setCongDoanLamTruoc(congDoanLamTruoc);
	}

	public CongDoanSanPham(String maCongDoan, String tenCongDoan, SanPham sanPham, int soLuongCanLam) {
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.sanPham = sanPham;
		this.soLuongCanLam = soLuongCanLam;
	}

	public CongDoanSanPham(String maCongDoan, String tenCongDoan, SanPham sanPham) {
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.sanPham = sanPham;
	}

	/**
	 * @param tenCongDoan the tenCongDoan to set
	 * @throws Exception
	 */
	public void setTenCongDoan(String tenCongDoan) throws Exception {
		if (tenCongDoan.trim().equals("")) {
			throw new Exception("Tên công đoạn không được rỗng.");
		} else {
			this.tenCongDoan = tenCongDoan;
		}
	}

	/**
	 * @param soLuongCanLam the soLuongCanLam to set
	 * @throws Exception
	 */
	public void setSoLuongCanLam(int soLuongCanLam) throws Exception {
		if (soLuongCanLam < 0) {
			throw new Exception("Số lượng cần làm phải lớn hơn 0.");
		} else {
			this.soLuongCanLam = soLuongCanLam;
		}
	}

	/**
	 * @param giaCongDoan the giaCongDoan to set
	 * @throws Exception
	 */
	public void setGiaCongDoan(double giaCongDoan) throws Exception {
		if (giaCongDoan <= 0) {
			throw new Exception("Giá công đoạn phải lớn hơn 0.");
		} else {
			this.giaCongDoan = giaCongDoan;
		}
	}

	/**
	 * @param sanPham the sanPham to set
	 * @throws Exception
	 */
	public void setSanPham(SanPham sanPham) throws Exception {
		if (sanPham == null) {
			throw new Exception("Sản phẩm không hợp lệ.");
		} else {
			this.sanPham = sanPham;
		}
	}

	/**
	 * @return the trangThai
	 */
	public boolean isTrangThai() {
		return trangThai;
	}


	@Override
	public String toString() {
		return this.tenCongDoan;
	}

}
