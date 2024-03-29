package com.product.salary.application.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CongDoanSanPham implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maCongDoan;
	private String tenCongDoan;
	private int soLuongCanLam;
	private double giaCongDoan;
	private LocalDate thoiHan;
	private CongDoanSanPham congDoanLamTruoc;
	private SanPham sanPham;
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

	public CongDoanSanPham() {
		super();
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
	 * @return the maCongDoan
	 */
	public String getMaCongDoan() {
		return maCongDoan;
	}

	/**
	 * @param maCongDoan the maCongDoan to set
	 */
	public void setMaCongDoan(String maCongDoan) {
		this.maCongDoan = maCongDoan;
	}

	/**
	 * @return the tenCongDoan
	 */
	public String getTenCongDoan() {
		return tenCongDoan;
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
	 * @return the soLuongCanLam
	 */
	public int getSoLuongCanLam() {
		return soLuongCanLam;
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
	 * @return the giaCongDoan
	 */
	public double getGiaCongDoan() {
		return giaCongDoan;
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
	 * @return the thoiHan
	 */
	public LocalDate getThoiHan() {
		return thoiHan;
	}

	/**
	 * @param thoiHan the thoiHan to set
	 * @throws Exception
	 */
	public void setThoiHan(LocalDate thoiHan) throws Exception {
		this.thoiHan = thoiHan;
	}

	/**
	 * @return the sanPham
	 */
	public SanPham getSanPham() {
		return sanPham;
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

	/**
	 * @param trangThai the trangThai to set
	 */
	public void setTrangThai(boolean trangThai) {
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
		return this.tenCongDoan;
	}

	public CongDoanSanPham getCongDoanLamTruoc() {
		return congDoanLamTruoc;
	}

	public void setCongDoanLamTruoc(CongDoanSanPham congDoanLamTruoc) {
		this.congDoanLamTruoc = congDoanLamTruoc;
	}

}
