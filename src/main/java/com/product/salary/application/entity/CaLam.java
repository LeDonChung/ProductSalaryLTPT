package com.product.salary.application.entity;

import java.io.Serializable;

public class CaLam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maCa;
	private String tenCa;

	public CaLam(String maCa, String tenCa) {
		super();
		this.maCa = maCa;
		this.tenCa = tenCa;
	}

	public CaLam() {
		super();
	}

	/**
	 * @return the maCa
	 */
	public String getMaCa() {
		return maCa;
	}

	/**
	 * @param maCa the maCa to set
	 */
	public void setMaCa(String maCa) {
		this.maCa = maCa;
	}

	/**
	 * @return the tenCa
	 */
	public String getTenCa() {
		return tenCa;
	}

	/**
	 * @param tenCa the tenCa to set
	 */
	public void setTenCa(String tenCa) {
		this.tenCa = tenCa;
	}

	@Override
	public String toString() {
		return this.tenCa;
	}

}
