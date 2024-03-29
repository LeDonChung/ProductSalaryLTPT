package com.product.salary.application.entity;

import java.util.Objects;

public class TrinhDo {
	private String maTrinhDo;
	private String tenTrinhDo;

	public TrinhDo() {
	}

	public TrinhDo(String maTrinhDo, String tenTrinhDo) {
		super();
		this.maTrinhDo = maTrinhDo;
		this.tenTrinhDo = tenTrinhDo;
	}

	public String getMaTrinhDo() {
		return maTrinhDo;
	}

	public void setMaTrinhDo(String maTrinhDo) {
		if (maTrinhDo.equals(""))
			this.maTrinhDo = "Mã trình độ";
		else
			this.maTrinhDo = maTrinhDo;
	}

	public String getTenTrinhDo() {
		return tenTrinhDo;
	}

	public void setTenTrinhDo(String tenTrinhDo) {
		if (tenTrinhDo.equals(""))
			this.tenTrinhDo = "Đại học";
		else
			this.tenTrinhDo = tenTrinhDo;
	}

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
