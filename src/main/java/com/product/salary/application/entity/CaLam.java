package com.product.salary.application.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "CaLam")
public class CaLam implements Serializable{

	@Id
	@Column(name = "MaCa", length = 15)
	private String maCa;

	@Column(name = "TenCa", length = 60,nullable = false)
	private String tenCa;

	@Override
	public String toString() {
		return this.tenCa;
	}

}
