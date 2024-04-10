package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "SanPham")
@Entity
public class SanPham implements Serializable {

	@Id
	@Column(name = "MaSanPham", length = 15)
	private String maSanPham;

	@Column(name = "TenSanPham", columnDefinition = "NVARCHAR(70) NOT NULL")
	private String tenSanPham;

	@Column(name = "SoLuongTon", columnDefinition = "INT NOT NULL CHECK(SoLuongTon >= 0) DEFAULT 0")
	private int soLuongTon;

	@Column(name = "HinhAnh", columnDefinition = "IMAGE")
	private byte[] hinhAnh;

	@Column(name = "ChatLieu", columnDefinition = "NVARCHAR(70)")
	private String chatLieu;

	@Column(name = "DonViTinh", columnDefinition = "NVARCHAR(70)")
	private String donViTinh;

	@Column(name = "DonGia", columnDefinition = "FLOAT NOT NULL DEFAULT 0")
	private Double donGia;

	@Column(name = "SoCongDoan", columnDefinition = "INT DEFAULT 0")
	private int soCongDoan;

	@Column(name = "TrangThai", columnDefinition = "BIT NOT NULL DEFAULT 1")
	private Boolean trangThai;

	@OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CongDoanSanPham> congDoanSanPhams;

	public SanPham(String maSanPham, String tenSanPham, String donViTinh, int soLuongTon, String chatLieu,
			Double donGia, byte[] hinhAnh) throws Exception {
		setMaSanPham(maSanPham);
		setTenSanPham(tenSanPham);
		setSoLuongTon(soLuongTon);
		setHinhAnh(hinhAnh);
		setDonViTinh(donViTinh);
		setDonGia(donGia);
		setChatLieu(chatLieu);
		setHinhAnh(hinhAnh);
	}

	public SanPham(String maSanPham, String tenSanPham, String chatLieu, String donViTinh, Double donGia,
			Boolean trangThai) {
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.chatLieu = chatLieu;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.trangThai = trangThai;
	}
	

	public SanPham(String maSanPham, String tenSanPham, Double donGia) {
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.donGia = donGia;
	}

	public SanPham(String maSanPham ,String tenSanPham) {
		this.tenSanPham = tenSanPham;
		this.maSanPham = maSanPham;
	}

	public SanPham(String maSanPham, String tenSanPham, int soLuongTon, byte[] hinhAnh, String chatLieu,
			String donViTinh, Double donGia, boolean trangThai) throws Exception {
		setMaSanPham(maSanPham);
		setTenSanPham(tenSanPham);
		setSoLuongTon(soLuongTon);
		setHinhAnh(hinhAnh);
		setDonViTinh(donViTinh);
		setDonGia(donGia);
		setTrangThai(trangThai);
		setChatLieu(chatLieu);
	}

	public SanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public SanPham(String maSanPham, String tenSanPham, int soLuongTon, byte[] hinhAnh, String chatLieu, String donViTinh, int soCongDoan, Double donGia, boolean trangThai) {
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuongTon = soLuongTon;
		this.hinhAnh = hinhAnh;
		this.chatLieu = chatLieu;
		this.donViTinh = donViTinh;
		this.soCongDoan = soCongDoan;
		this.donGia = donGia;
		this.trangThai = trangThai;
	}


	/**
	 * @param tenSanPham the tenSanPham to set
	 * @throws Exception
	 */
	public void setTenSanPham(String tenSanPham) throws Exception {
		if (tenSanPham.trim().equals("")) {
			throw new Exception("Tên sản phẩm không được rỗng.");
		} else {
			this.tenSanPham = tenSanPham;
		}
	}

	/**
	 * @param soLuongTon the soLuongTon to set
	 * @throws Exception
	 */
	public void setSoLuongTon(int soLuongTon) throws Exception {
		if (soLuongTon < 0) {
			throw new Exception("Số lượng tồn phải >= 0.");
		} else {
			this.soLuongTon = soLuongTon;
		}
	}

	/**
	 * @param donGia the donGia to set
	 * @throws Exception
	 */
	public void setDonGia(Double donGia) throws Exception {
		if (donGia < 0) {
			throw new Exception("Đơn giá phải >= 0.");
		} else {
			this.donGia = donGia;
		}
	}

	/**
	 * @return the trangThai
	 */
	public Boolean isTrangThai() {
		return trangThai;
	}

	@Override
	public String toString() {
		return this.tenSanPham;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}
	
}
