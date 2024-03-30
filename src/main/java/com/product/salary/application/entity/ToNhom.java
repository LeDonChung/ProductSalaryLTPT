package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ToNhom")
@Entity
@Getter
@Setter
public class ToNhom implements Serializable {

	@Id
	@Column(name = "MaToNhom", length = 15)
	private String maToNhom;

	@Column(name = "TenToNhom", length = 50, nullable = false)
	private String tenToNhom;

	@Column(name = "SoLuongCongNhan")
	private int soLuongCongNhan;

	@OneToMany(mappedBy = "toNhom", fetch = FetchType.LAZY)
	private Set<CongNhan> congNhans;

	@Column(name = "TrangThai", nullable = false)
	private Boolean trangThai;

	public ToNhom(String maToNhom, String tenToNhom) {
		this.maToNhom = maToNhom;
		this.tenToNhom = tenToNhom;
	}

	public ToNhom(String maToNhom, String tenToNhom, int soLuongCongNhan, Boolean trangThai) {
		this.maToNhom = maToNhom;
		this.tenToNhom = tenToNhom;
		this.soLuongCongNhan = soLuongCongNhan;
		this.trangThai = trangThai;
	}

	public ToNhom(String maToNhom) {
		this.maToNhom = maToNhom;
	}

	public Boolean isTrangThai() {
		return trangThai;
	}

	@Override
	public String toString() {
		return this.tenToNhom;
	}

}
