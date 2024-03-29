package com.product.salary.application.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class ChamCongCongNhan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maChamCong;
	private PhanCongCongNhan phanCongCongNhan;
	private int soLuongHoanThanh;
	private CaLam caLam;
	private LocalDate ngayChamCong;
	private int trangThai;
	private LuongCongNhan luongCongNhan;

	public ChamCongCongNhan() {
	}

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

	public String getMaChamCong() {
		return maChamCong;
	}

	public void setMaChamCong(String maChamCong) {
		this.maChamCong = maChamCong;
	}

	public PhanCongCongNhan getPhanCongCongNhan() {
		return phanCongCongNhan;
	}

	public void setPhanCongCongNhan(PhanCongCongNhan phanCongCongNhan) {
		this.phanCongCongNhan = phanCongCongNhan;
	}

	public int getSoLuongHoanThanh() {
		return soLuongHoanThanh;
	}

	public void setSoLuongHoanThanh(int soLuongHoanThanh) throws Exception {
		if (soLuongHoanThanh < 0) {
			throw new Exception("Số lượng hoàn thành >= 0");
		} else {
			this.soLuongHoanThanh = soLuongHoanThanh;
		}
	}

	public CaLam getCaLam() {
		return caLam;
	}

	public void setCaLam(CaLam caLam) {
		this.caLam = caLam;
	}

	public LocalDate getNgayChamCong() {
		return ngayChamCong;
	}

	public void setNgayChamCong(LocalDate ngayChamCong) throws Exception {
		if (ngayChamCong.isAfter(LocalDate.now())) {
			throw new Exception("Ngày chấm công phải <= ngày hiện tại");
		} else {
			this.ngayChamCong = ngayChamCong;
		}
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public LuongCongNhan getLuongCongNhan() {
		return luongCongNhan;
	}

	public void setLuongCongNhan(LuongCongNhan luongCongNhan) {
		this.luongCongNhan = luongCongNhan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ChamCongCongNhan [maChamCong=" + maChamCong + ", phanCongCongNhan=" + phanCongCongNhan
				+ ", soLuongHoanThanh=" + soLuongHoanThanh + ", caLam=" + caLam + ", ngayChamCong=" + ngayChamCong
				+ ", trangThai=" + trangThai + ", luongCongNhan=" + luongCongNhan + "]";
	}
}
