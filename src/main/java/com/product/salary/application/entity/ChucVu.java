/**
 * @author Trần Thị Thanh Tuyền tạo thực thể chức vụ
 */

package com.product.salary.application.entity;

import java.util.Objects;

public class ChucVu {
	private String maChucVu;
	private String tenChucVu;

	public ChucVu() {

	}

	/**
	 * constructor đầy đủ tham số
	 * 
	 * @param maChucVu
	 * @param tenChucVu
	 * @throws Exception
	 */
	public ChucVu(String maChucVu, String tenChucVu) {
		super();
		this.maChucVu = maChucVu;
		this.tenChucVu = tenChucVu;
	}

	/**
	 * @return the maChucVu
	 */
	public String getMaChucVu() {
		return maChucVu;
	}

	/**
	 * mã chức vụ theo định dạng CVxx, xx là số tự nhiên tự tăng
	 * 
	 * @param maChucVu the maChucVu to set
	 */
	public void setMaChucVu(String maChucVu) {
		this.maChucVu = maChucVu;
	}

	/**
	 * @return the tenChucVu
	 */
	public String getTenChucVu() {
		return tenChucVu;
	}

	/**
	 * Tên chức vụ không được rỗng
	 * 
	 * @param tenChucVu the tenChucVu to set
	 * @throws Exception
	 */
	public void setTenChucVu(String tenChucVu) throws Exception {
		if (tenChucVu.equals(""))
			throw new Exception("Tên chức vụ không được rỗng");
		else
			this.tenChucVu = tenChucVu;
	}

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
