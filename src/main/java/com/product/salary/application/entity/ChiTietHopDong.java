package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "ChiTietHopDong")
@Entity
public class ChiTietHopDong implements Serializable {
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaHopDong")
	private HopDong hopDong;

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaSanPham")
	private SanPham sanPham;

	@Column(name = "SoLuong", nullable = false)
	private int soLuong;

	@Column(name = "GiaDatLam", columnDefinition = "REAL NOT NULL")
	private double giaDatLam;

	public ChiTietHopDong(HopDong hopDong, SanPham sanPham, int soLuong, double giaDatLam) throws Exception {
		setHopDong(hopDong);
		setSanPham(sanPham);
		setSoLuong(soLuong);
		setGiaDatLam(giaDatLam);
	}

	/**
	 * @param hopDong the hopDong to set
	 * @throws Exception
	 */
	public void setHopDong(HopDong hopDong) throws Exception {
		if (hopDong == null) {
			throw new Exception("Hợp đồng không hợp lệ.");
		} else {
			this.hopDong = hopDong;
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
	 * @param soLuong the soLuong to set
	 * @throws Exception
	 */
	public void setSoLuong(int soLuong) throws Exception {
		if (soLuong <= 0) {
			throw new Exception("Số lượng đặt phải > 0.");
		} else {
			this.soLuong = soLuong;
		}
	}

	/**
	 * @param giaDatLam the giaDatLam to set
	 * @throws Exception
	 */
	public void setGiaDatLam(double giaDatLam) throws Exception {
		if (giaDatLam < sanPham.getDonGia()) {
			throw new Exception("Giá đặt làm phải >= đơn giá sản phẩm.");
		} else {
			this.giaDatLam = giaDatLam;
		}
	}
}
