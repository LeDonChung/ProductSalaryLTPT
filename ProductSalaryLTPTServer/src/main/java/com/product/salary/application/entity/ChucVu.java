/**
 * @author Trần Thị Thanh Tuyền tạo thực thể chức vụ
 */

package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "ChucVu")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@NamedQueries({
		@NamedQuery(name = "ChucVu.timKiemTatCaChucVu", query = "SELECT cv FROM ChucVu cv"),
		@NamedQuery(name = "ChucVu.timMaChucVuCuoiCung", query = "SELECT cv FROM ChucVu cv ORDER BY cv.maChucVu DESC")

})
public class ChucVu implements Serializable {
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
