package com.product.salary.application.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Objects;

public class CongNhan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maCongNhan;
	private String hoTen;
	private String email;
	private String diaChi;
	private int gioiTinh;
	private String cccd;
	private String soDienThoai;
	private LocalDate ngaySinh;
	private ToNhom toNhom;
	private LocalDate ngayVaoLam;
	private Double troCap;
	private byte[] hinhAnh;
	private Boolean trangThai;
	private TayNghe tayNghe;

	public CongNhan() {
	}

	public CongNhan(String maCongNhan, String hoTen, String email, String diaChi, int gioiTinh, String cccd,
                    String soDienThoai, LocalDate ngaySinh, ToNhom toNhom, LocalDate ngayVaoLam, double troCap, byte[] hinhAnh,
                    boolean trangThai, TayNghe tayNghe) throws Exception {
		setMaCongNhan(maCongNhan);
		setHoTen(hoTen);
		setEmail(email);
		setDiaChi(diaChi);
		setGioiTinh(gioiTinh);
		setCccd(cccd);
		setSoDienThoai(soDienThoai);
		setNgaySinh(ngaySinh);
		setToNhom(toNhom);
		setNgayVaoLam(ngayVaoLam);
		setTroCap(troCap);
		setHinhAnh(hinhAnh);
		setTrangThai(trangThai);
		setTayNghe(tayNghe);
	}

	public CongNhan(String maCongNhan, String hoTen, String email, String diaChi, int gioiTinh, String cccd,
                    String soDienThoai, LocalDate ngaySinh, ToNhom toNhom, LocalDate ngayVaoLam, double troCap, byte[] hinhAnh,
                    TayNghe tayNghe) throws Exception {
		setMaCongNhan(maCongNhan);
		setHoTen(hoTen);
		setEmail(email);
		setDiaChi(diaChi);
		setGioiTinh(gioiTinh);
		setCccd(cccd);
		setSoDienThoai(soDienThoai);
		setNgaySinh(ngaySinh);
		setToNhom(toNhom);
		setNgayVaoLam(ngayVaoLam);
		setTroCap(troCap);
		setHinhAnh(hinhAnh);
		setTayNghe(tayNghe);
	}

	public CongNhan(String maCongNhan, String hoTen, String email, String diaChi, String cccd, String soDienThoai,
			Boolean trangThai, int gioiTinh, LocalDate ngaySinh, LocalDate ngayVaoLam, Double troCap, ToNhom toNhom,
			TayNghe tayNghe) {
		this.maCongNhan = maCongNhan;
		this.hoTen = hoTen;
		this.email = email;
		this.diaChi = diaChi;
		this.cccd = cccd;
		this.soDienThoai = soDienThoai;
		this.trangThai = trangThai;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.ngayVaoLam = ngayVaoLam;
		this.troCap = troCap;
		this.toNhom = toNhom;
		this.tayNghe = tayNghe;
	}

	public CongNhan(String maCongNhan, String hoTen) {
		this.maCongNhan = maCongNhan;
		this.hoTen = hoTen;
	}

	public CongNhan(String maCongNhan, String hoTen, int gioiTinh, String soDienThoai) {
		this.maCongNhan = maCongNhan;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
	}

	public CongNhan(String maCongNhan, String hoTen, String soDienThoai, TayNghe tayNghe) {
		super();
		this.maCongNhan = maCongNhan;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.tayNghe = tayNghe;
	}

	public ToNhom getToNhom() {
		return toNhom;
	}

	public void setToNhom(ToNhom toNhom) {
		this.toNhom = toNhom;
	}

	public String getMaCongNhan() {
		return maCongNhan;
	}

	public void setMaCongNhan(String maCongNhan) {
		this.maCongNhan = maCongNhan;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) throws Exception {
		if (hoTen.trim().equals("")) {
			throw new Exception("Họ tên không được rỗng");
		} else {
			this.hoTen = hoTen;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if (email.trim().equals("")) {
			throw new Exception("Email không được rỗng");
		} else {
			this.email = email;
		}
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) throws Exception {
		this.diaChi = diaChi;
	}

	public int getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) throws Exception {
		if (cccd.trim().equals("")) {
			throw new Exception("CCCD không được rỗng");
		} else {
			this.cccd = cccd;
		}
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) throws Exception {
		if (soDienThoai.trim().equals("")) {
			throw new Exception("Số điện thoại không được rỗng");
		} else {
			this.soDienThoai = soDienThoai;
		}
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) throws Exception {
		int tuoi = Period.between(ngaySinh, LocalDate.now()).getYears();
		if (tuoi < 18) {
			throw new Exception(String.format("Công nhân phải >= 18 tuổi. Tuổi hiện tại: %d", tuoi));
		} else {
			this.ngaySinh = ngaySinh;
		}
	}

	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(LocalDate ngayVaoLam) throws Exception {
		if (ngayVaoLam.isAfter(LocalDate.now())) {
			throw new Exception("Ngày vào làm phải <= ngày hiện tại");
		} else {
			this.ngayVaoLam = ngayVaoLam;
		}

	}

	public Double getTroCap() {
		return troCap;
	}

	public void setTroCap(Double troCap) throws Exception {
		if (troCap < 0) {
			throw new Exception("Trợ cấp phải >= 0");
		} else {
			this.troCap = troCap;
		}
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public Boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	public TayNghe getTayNghe() {
		return tayNghe;
	}

	public void setTayNghe(TayNghe tayNghe) {
		this.tayNghe = tayNghe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CongNhan [maCongNhan=" + maCongNhan + ", hoTen=" + hoTen + ", email=" + email + ", diaChi=" + diaChi
				+ ", gioiTinh=" + gioiTinh + ", cccd=" + cccd + ", soDienThoai=" + soDienThoai + ", ngaySinh="
				+ ngaySinh + ", toNhom=" + toNhom + ", ngayVaoLam=" + ngayVaoLam + ", troCap=" + troCap + ", hinhAnh="
				+ Arrays.toString(hinhAnh) + ", trangThai=" + trangThai + ", tayNghe=" + tayNghe + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cccd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongNhan other = (CongNhan) obj;
		return Objects.equals(cccd, other.cccd);
	}

}
