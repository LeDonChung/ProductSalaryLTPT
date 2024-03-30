/**
 * @author Trần Thị Thanh Tuyền tạo thực thể chức vụ
 */

package com.product.salary.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
@Entity
@Table(name = "ChucVu")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ChucVu {
	@Id
	@Column(name = "MaChucVu", length = 15)
	private String maChucVu;

	@Column(name = "TenChucVu", columnDefinition = "NVARCHAR(50) NOT NULL")
	private String tenChucVu;

	@Override
	public int hashCode() {
		return Objects.hash(maChucVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChucVu other = (ChucVu) obj;
		return Objects.equals(maChucVu, other.maChucVu);
	}

	@Override
	public String toString() {
		return this.tenChucVu;
	}

}
