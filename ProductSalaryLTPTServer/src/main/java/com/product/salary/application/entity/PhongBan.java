package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PhongBan")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
		@NamedQuery(name = "PhongBan.timKiemTatCaPhongBan", query = "SELECT pb FROM PhongBan pb"),
		@NamedQuery(name = "PhongBan.timMaPhongbanCuoiCung", query = "SELECT pb FROM PhongBan pb ORDER BY pb.maPhongBan DESC"),
		@NamedQuery(name = "PhongBan.timKiemTatCaPhongBanDangHoatDong", query = "SELECT pb FROM PhongBan pb WHERE pb.trangThai = true ")
})
public class PhongBan implements Serializable {

	@Id
	@Column(name = "MaPhongBan", length = 15)
	private String maPhongBan;

	@Column(name = "TenPhongBan", columnDefinition = "NVARCHAR(70) NOT NULL")
	private String tenPhongBan;

	@OneToMany(mappedBy = "phongBan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ToString.Exclude
	private transient Set<NhanVien> nhanViens;

	@Column(name = "SoLuongNhanVien", columnDefinition = "INT NOT NULL")
	private Integer soLuongNhanVien;

	@Column(name = "TrangThai", columnDefinition = "BIT NOT NULL")
	private Boolean trangThai;

	public Boolean isTrangThai() {
		return trangThai;
	}

	public PhongBan(String maPhongBan, String tenPhongBan, int soLuongNhanVien, Boolean trangThai) {
		this.maPhongBan = maPhongBan;
		this.tenPhongBan = tenPhongBan;
		this.soLuongNhanVien = soLuongNhanVien;
		this.trangThai = trangThai;
	}

	public PhongBan(String maPhongBan, String tenPhongBan, Boolean trangThai) throws Exception {
		setMaPhongBan(maPhongBan);
		setTenPhongBan(tenPhongBan);
		setTrangThai(trangThai);
	}

	public PhongBan(String maPhongBan, String tenPhongBan) {
		super();
		this.maPhongBan = maPhongBan;
		this.tenPhongBan = tenPhongBan;
	}

	/**
	 * @param tenPhongBan the tenPhongBan to set
	 * @throws Exception
	 */
	public void setTenPhongBan(String tenPhongBan) throws Exception {
		if (tenPhongBan.equals(""))
			throw new Exception("Tên phòng ban không được rỗng");
		else
			this.tenPhongBan = tenPhongBan;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhongBan);
	}

	@Override
	public String toString() {
		return tenPhongBan;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongBan other = (PhongBan) obj;
		return Objects.equals(maPhongBan, other.maPhongBan);
	}

}
