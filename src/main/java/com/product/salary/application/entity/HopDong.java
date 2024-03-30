package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "HopDong")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class HopDong implements Serializable {

	@Id
	@Column(name = "MaHopDong", length = 15)
	private String maHopDong;

	@Column(name = "TenHopDong", length = 70, nullable = false)
	private String tenHopDong;

	@Column(name = "TenKhachHang", length = 70, nullable = false)
	private String tenKhachHang;

	@Column(name = "TongTien", nullable = false)
	private double tongTien;

	@Column(name = "SoTienCoc", nullable = false)
	private double soTienCoc;

	@Column(name = "NgayBatDau")
	private LocalDate ngayBatDau;

	@Column(name = "NgayKetThuc")
	private LocalDate ngayKetThuc;

	@Column(name = "YeuCau", length = 100, nullable = false)
	private String yeuCau;

	@Column(name = "TrangThai", nullable = false)
	private boolean trangThai;

	@OneToMany(mappedBy = "hopDong", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ChiTietHopDong> chiTietHopDongs;

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


	public HopDong(String maHopDong) {
		this.maHopDong = maHopDong;
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
	 * @param ngayBatDau the ngayBatDau to set
	 * @throws Exception
	 */
	public void setNgayBatDau(LocalDate ngayBatDau) throws Exception {
		this.ngayBatDau = ngayBatDau;
	}

	/**
	 * @return the trangThai
	 */
	public boolean isTrangThai() {
		return trangThai;
	}


}
