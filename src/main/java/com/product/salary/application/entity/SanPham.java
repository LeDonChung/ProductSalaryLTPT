package com.product.salary.application.entity;

import java.io.Serializable;
import java.util.Objects;

public class SanPham implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maSanPham;
	private String tenSanPham;
	private int soLuongTon;
	private byte[] hinhAnh;
	private String chatLieu;
	private String donViTinh;
	private int soCongDoan;
	private Double donGia;
	private Boolean trangThai;

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
			String donViTinh, int soCongDoan, Double donGia, boolean trangThai) throws Exception {
		setMaSanPham(maSanPham);
		setTenSanPham(tenSanPham);
		setSoLuongTon(soLuongTon);
		setHinhAnh(hinhAnh);
		setDonViTinh(donViTinh);
		setSoCongDoan(soCongDoan);
		setDonGia(donGia);
		setTrangThai(trangThai);
		setChatLieu(chatLieu);
	}

	public SanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	/**
	 * @return the maSanPham
	 */
	public String getMaSanPham() {
		return maSanPham;
	}

	/**
	 * @param maSanPham the maSanPham to set
	 */
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	/**
	 * @return the tenSanPham
	 */
	public String getTenSanPham() {
		return tenSanPham;
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
	 * @return the soLuongTon
	 */
	public int getSoLuongTon() {
		return soLuongTon;
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
	 * @return the hinhAnh
	 */
	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	/**
	 * @param hinhAnh the hinhAnh to set
	 */
	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	/**
	 * @return the chatLieu
	 */
	public String getChatLieu() {
		return chatLieu;
	}

	/**
	 * @param chatLieu the chatLieu to set
	 */
	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}

	/**
	 * @return the donViTinh
	 */
	public String getDonViTinh() {
		return donViTinh;
	}

	/**
	 * @param donViTinh the donViTinh to set
	 */
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	/**
	 * @return the soCongDoan
	 */
	public int getSoCongDoan() {
		return soCongDoan;
	}

	/**
	 * @param soCongDoan the soCongDoan to set
	 */
	public void setSoCongDoan(int soCongDoan) {
		this.soCongDoan = soCongDoan;
	}

	/**
	 * @return the donGia
	 */
	public Double getDonGia() {
		return donGia;
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

	/**
	 * @param trangThai the trangThai to set
	 */
	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
