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
@ToString
public class PhongBan implements Serializable {

	@Id
	@Column(name = "MaPhongBan", length = 15)
	private String maPhongBan;

	@Column(name = "TenPhongBan", columnDefinition = "NVARCHAR(70) NOT NULL")
	private String tenPhongBan;

	@OneToMany(mappedBy = "phongBan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<NhanVien> nhanViens;

	@Column(name = "SoLuongNhanVien", columnDefinition = "INT NOT NULL DEFAULT 0")
	private Integer soLuongNhanVien;

	@Column(name = "TrangThai", columnDefinition = "BIT NOT NULL DEFAULT 1")
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
