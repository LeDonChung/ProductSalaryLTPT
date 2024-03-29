package com.product.salary.application.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class ChamCongNhanVien implements Serializable {

	private String maChamCong;
	private NhanVien nhanVien;
	private CaLam caLam;
	private LocalDate ngayChamCong;
	private int trangThai;

	public ChamCongNhanVien() {
	}

	public ChamCongNhanVien(String maChamCong, NhanVien nhanVien, CaLam caLam, LocalDate ngayChamCong, int trangThai) throws Exception {
		setMaChamCong(maChamCong);
		setNhanVien(nhanVien);
		setCaLam(caLam);
		setNgayChamCong(ngayChamCong);
		setTrangThai(trangThai);
	}

	/**
	 * @return the maChamCong
	 */
	public String getMaChamCong() {
		return maChamCong;
	}

	/**
	 * @param maChamCong the maChamCong to set
	 */
	public void setMaChamCong(String maChamCong) {
		this.maChamCong = maChamCong;
	}

	/**
	 * @return the nhanVien
	 */
	public NhanVien getNhanVien() {
		return nhanVien;
	}

	/**
	 * @param nhanVien the nhanVien to set
	 */
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	/**
	 * @return the caLam
	 */
	public CaLam getCaLam() {
		return caLam;
	}

	/**
	 * @param caLam the caLam to set
	 */
	public void setCaLam(CaLam caLam) {
		this.caLam = caLam;
	}

	/**
	 * @return the ngayChamCong
	 */
	public LocalDate getNgayChamCong() {
		return ngayChamCong;
	}

	/**
	 * @param ngayChamCong the ngayChamCong to set
	 * @throws Exception
	 */
	public void setNgayChamCong(LocalDate ngayChamCong) throws Exception {
		if (ngayChamCong.isAfter(LocalDate.now()))
			throw new Exception("Ngày chấm công phải trước hoặc bằng ngày hiện tại");
		else
			this.ngayChamCong = ngayChamCong;
	}

	/**
	 * @return the trangThai
	 */
	public int getTrangThai() {
		return trangThai;
	}

	/**
	 * @param trangThai the trangThai to set
	 * @throws Exception
	 */
	public void setTrangThai(int trangThai) throws Exception {
		if (trangThai != 0 && trangThai != 1 && trangThai != 2)
			throw new Exception("Trạng thái phải thuộc 0 hoặc 1 hoặc 2");
		else
			this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "ChamCongNhanVien [maChamCong=" + maChamCong + ", nhanVien=" + nhanVien + ", caLam=" + caLam
				+ ", ngayChamCong=" + ngayChamCong + ", trangThai=" + trangThai + "]";
	}

}
