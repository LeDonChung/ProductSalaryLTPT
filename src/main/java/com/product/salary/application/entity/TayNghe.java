package com.product.salary.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "TayNghe")
public class TayNghe {
	@Id
	@Column(name = "MaTayNghe", length = 15)
	private String maTayNghe;

	@Column(name = "TenTayNghe", columnDefinition = "NVARCHAR(70) NOT NULL")
	private String tenTayNghe;

	public TayNghe(String maTayNghe) {
		this.maTayNghe = maTayNghe;
	}

	@Override
	public String toString() {
		return this.tenTayNghe;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTayNghe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TayNghe other = (TayNghe) obj;
		return Objects.equals(maTayNghe, other.maTayNghe);
	}

}
