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

	@Column(name = "TenToNhom", columnDefinition = "NVARCHAR(50) NOT NULL")
	private String tenToNhom;

	@Column(name = "SoLuongCongNhan", columnDefinition = "INT NOT NULL DEFAULT 0")
	private int soLuongCongNhan;

	@OneToMany(mappedBy = "toNhom", fetch = FetchType.EAGER)
	private transient Set<CongNhan> congNhans;

	@Column(name = "TrangThai", columnDefinition = "BIT NOT NULL DEFAULT 1")
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
