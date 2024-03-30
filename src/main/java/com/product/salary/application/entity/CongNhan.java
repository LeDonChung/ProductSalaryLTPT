package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name="CongNhan")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CongNhan implements Serializable {
	@Id
	@Column(name = "MaCongNhan", length = 15)
	private String maCongNhan;

	@Column(name = "HoTen", length = 70, nullable = false)
	private String hoTen;

	@Column(name = "Email", length = 70, nullable = false)
	private String email;

	@Column(name = "DiaChi", length = 100, nullable = false)
	private String diaChi;

	@Column(name = "GioiTinh", nullable = false)
	private int gioiTinh;

	@Column(name = "CCCD", length = 12, unique = true)
	private String cccd;

	@Column(name = "SoDienThoai", length = 10, nullable = false)
	private String soDienThoai;

	@Column(name = "NgaySinh", columnDefinition = "DATETIME NOT NULL")
	private LocalDate ngaySinh;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaToNhom")
	private ToNhom toNhom;

	@Column(name = "NgayVaoLam", columnDefinition = "DATETIME NOT NULL")
	private LocalDate ngayVaoLam;

	@Column(name = "TroCap", columnDefinition = "REAL NOT NULL")
	private Double troCap;

	@Column(name = "HinhAnh")
	private byte[] hinhAnh;

	@Column(name = "TrangThai", nullable = false)
	private Boolean trangThai;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaTayNghe")
	private TayNghe tayNghe;

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


	public void setHoTen(String hoTen) throws Exception {
		if (hoTen.trim().equals("")) {
			throw new Exception("Họ tên không được rỗng");
		} else {
			this.hoTen = hoTen;
		}
	}


	public void setEmail(String email) throws Exception {
		if (email.trim().equals("")) {
			throw new Exception("Email không được rỗng");
		} else {
			this.email = email;
		}
	}

	public void setCccd(String cccd) throws Exception {
		if (cccd.trim().equals("")) {
			throw new Exception("CCCD không được rỗng");
		} else {
			this.cccd = cccd;
		}
	}


	public void setSoDienThoai(String soDienThoai) throws Exception {
		if (soDienThoai.trim().equals("")) {
			throw new Exception("Số điện thoại không được rỗng");
		} else {
			this.soDienThoai = soDienThoai;
		}
	}


	public void setNgaySinh(LocalDate ngaySinh) throws Exception {
		int tuoi = Period.between(ngaySinh, LocalDate.now()).getYears();
		if (tuoi < 18) {
			throw new Exception(String.format("Công nhân phải >= 18 tuổi. Tuổi hiện tại: %d", tuoi));
		} else {
			this.ngaySinh = ngaySinh;
		}
	}


	public void setNgayVaoLam(LocalDate ngayVaoLam) throws Exception {
		if (ngayVaoLam.isAfter(LocalDate.now())) {
			throw new Exception("Ngày vào làm phải <= ngày hiện tại");
		} else {
			this.ngayVaoLam = ngayVaoLam;
		}

	}


	public void setTroCap(Double troCap) throws Exception {
		if (troCap < 0) {
			throw new Exception("Trợ cấp phải >= 0");
		} else {
			this.troCap = troCap;
		}
	}

	public Boolean isTrangThai() {
		return trangThai;
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
