package com.product.salary.application.entity;

import java.io.Serializable;

public class ChiTietHopDong implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HopDong hopDong;
	private SanPham sanPham;
	private int soLuong;
	private double giaDatLam;

	public ChiTietHopDong(HopDong hopDong, SanPham sanPham, int soLuong, double giaDatLam) throws Exception {
		setHopDong(hopDong);
		setSanPham(sanPham);
		setSoLuong(soLuong);
		setGiaDatLam(giaDatLam);
	}

	public ChiTietHopDong() {
		super();
	}

	/**
	 * @return the hopDong
	 */
	public HopDong getHopDong() {
		return hopDong;
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
	 * @return the soLuong
	 */
	public int getSoLuong() {
		return soLuong;
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
	 * @return the giaDatLam
	 */
	public double getGiaDatLam() {
		return giaDatLam;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ChiTietHopDong [hopDong=" + hopDong + ", sanPham=" + sanPham + ", soLuong=" + soLuong + ", giaDatLam="
				+ giaDatLam + "]";
	}

}
