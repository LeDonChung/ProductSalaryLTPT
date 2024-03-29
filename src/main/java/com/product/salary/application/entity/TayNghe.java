package com.product.salary.application.entity;

import java.util.Objects;

public class TayNghe {
	private String maTayNghe;
	private String tenTayNghe;

	public TayNghe() {

	}

	public TayNghe(String maTayNghe, String tenTayNghe) {
		setMaTayNghe(maTayNghe);
		setTenTayNghe(tenTayNghe);
	}

	public TayNghe(String maTayNghe) {
		this.maTayNghe = maTayNghe;
	}

	public String getMaTayNghe() {
		return maTayNghe;
	}

	public void setMaTayNghe(String maTayNghe) {
		this.maTayNghe = maTayNghe;
	}

	public String getTenTayNghe() {
		return tenTayNghe;
	}

	public void setTenTayNghe(String tenTayNghe) {
		this.tenTayNghe = tenTayNghe;
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
