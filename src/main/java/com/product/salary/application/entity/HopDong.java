package com.product.salary.application.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class HopDong implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maHopDong;
	private String tenHopDong;
	private String tenKhachHang;
	private double tongTien;
	private double soTienCoc;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private String yeuCau;
	private boolean trangThai;

	public HopDong(String maHopDong, String tenHopDong, String tenKhachHang, double tongTien, double soTienCoc,
			LocalDate ngayBatDau, LocalDate ngayKetThuc, String yeuCau, boolean trangThai) throws Exception {
		setMaHopDong(maHopDong);
		setTenHopDong(tenHopDong);
		setTenKhachHang(tenKhachHang);
		setTongTien(tongTien);
		setSoTienCoc(soTienCoc);
		setNgayBatDau(ngayBatDau);
		setNgayKetThuc(ngayKetThuc);
		setYeuCau(yeuCau);
		setTrangThai(trangThai);
	}

	public HopDong() {
		super();
	}

	public HopDong(String maHopDong) {
		this.maHopDong = maHopDong;
	}

	/**
	 * @return the maHopDong
	 */
	public String getMaHopDong() {
		return maHopDong;
	}

	/**
	 * @param maHopDong the maHopDong to set
	 */
	public void setMaHopDong(String maHopDong) {
		this.maHopDong = maHopDong;
	}

	/**
	 * @return the tenHopDong
	 */
	public String getTenHopDong() {
		return tenHopDong;
	}

	/**
	 * @param tenHopDong the tenHopDong to set
	 * @throws Exception
	 */
	public void setTenHopDong(String tenHopDong) throws Exception {

		if (tenHopDong.trim().equals("")) {
			throw new Exception("Tên hợp đồng không được rỗng.");
		} else {
			this.tenHopDong = tenHopDong;
		}
	}

	/**
	 * @return the tenKhachHang
	 */
	public String getTenKhachHang() {
		return tenKhachHang;
	}

	/**
	 * @param tenKhachHang the tenKhachHang to set
	 * @throws Exception
	 */
	public void setTenKhachHang(String tenKhachHang) throws Exception {
		if (tenKhachHang.trim().equals("")) {
			throw new Exception("Tên khách hàng không được rỗng.");
		} else {
			this.tenKhachHang = tenKhachHang;
		}
	}

	/**
	 * @return the tongTien
	 */
	public double getTongTien() {
		return tongTien;
	}

	/**
	 * @param tongTien the tongTien to set
	 * @throws Exception
	 */
	public void setTongTien(double tongTien) throws Exception {
		if (tongTien < 0) {
			throw new Exception("Tổng tiền phải >= 0.");
		} else {
			this.tongTien = tongTien;
		}
	}

	/**
	 * @return the soTienCoc
	 */
	public double getSoTienCoc() {
		return soTienCoc;
	}

	/**
	 * @param soTienCoc the soTienCoc to set
	 * @throws Exception
	 */
	public void setSoTienCoc(double soTienCoc) throws Exception {
		if (soTienCoc < 0 || soTienCoc > this.tongTien) {
			throw new Exception("Số tiền cọc phải >= 0 và <= tổng tiền.");
		} else {
			this.soTienCoc = soTienCoc;
		}
	}

	/**
	 * @return the ngayBatDau
	 */
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	/**
	 * @param ngayBatDau the ngayBatDau to set
	 * @throws Exception
	 */
	public void setNgayBatDau(LocalDate ngayBatDau) throws Exception {
		this.ngayBatDau = ngayBatDau;
	}

	/**
	 * @return the ngayKetThuc
	 */
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	/**
	 * @param ngayKetThuc the ngayKetThuc to set
	 * @throws Exception
	 */
	public void setNgayKetThuc(LocalDate ngayKetThuc) throws Exception {
		this.ngayKetThuc = ngayKetThuc;
	}

	/**
	 * @return the yeuCau
	 */
	public String getYeuCau() {
		return yeuCau;
	}

	/**
	 * @param yeuCau the yeuCau to set
	 */
	public void setYeuCau(String yeuCau) {
		this.yeuCau = yeuCau;
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

	@Override
	public String toString() {
		return "HopDong [maHopDong=" + maHopDong + ", tenHopDong=" + tenHopDong + ", tenKhachHang=" + tenKhachHang
				+ ", tongTien=" + tongTien + ", soTienCoc=" + soTienCoc + ", ngayBatDau=" + ngayBatDau
				+ ", ngayKetThuc=" + ngayKetThuc + ", yeuCau=" + yeuCau + ", trangThai=" + trangThai + "]";
	}

}
