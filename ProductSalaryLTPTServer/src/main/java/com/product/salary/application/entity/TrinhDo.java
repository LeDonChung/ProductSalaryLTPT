package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "TrinhDo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@NamedQueries({
		@NamedQuery(name = "TrinhDo.timKiemTatCaTrinhDo", query = "SELECT td FROM TrinhDo td"),
})
public class TrinhDo implements Serializable {
	@Id
	@Column(name = "MaTrinhDo", length = 15)
	private String maTrinhDo;

	@Column(name = "TenTrinhDo", columnDefinition = "NVARCHAR(50) NOT NULL")
	private String tenTrinhDo;

	@Override
	public int hashCode() {
		return Objects.hash(maTrinhDo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrinhDo other = (TrinhDo) obj;
		return Objects.equals(maTrinhDo, other.maTrinhDo);
	}

	@Override
	public String toString() {
		return this.tenTrinhDo;
	}
}
