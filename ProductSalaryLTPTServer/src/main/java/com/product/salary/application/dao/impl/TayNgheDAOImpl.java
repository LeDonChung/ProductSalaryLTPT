package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.dao.TayNgheDAO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TayNgheDAOImpl extends AbstractDAO implements TayNgheDAO, Serializable {

	@Override
	public List<TayNghe> timKiemTatCaTayNghe() {
		try(var em = getEntityManager()) {
			return em.createNamedQuery("TayNghe.timKiemTatCaTayNghe", TayNghe.class).getResultList();
		}
	}

	@Override
	public TayNghe capNhatTayNghe(TayNghe tayNghe) {
		try(var em = getEntityManager()){
			em.getTransaction().begin();
			em.merge(tayNghe);
			em.getTransaction().commit();
			return tayNghe;

		}
	}

	@Override
	public TayNghe themTayNghe(TayNghe tayNghe) {
		try(var em = getEntityManager()){
			em.getTransaction().begin();
			em.persist(tayNghe);
			em.getTransaction().commit();
			return tayNghe;
		}
	}

	@Override
	public TayNghe timKiemBangMaTayNghe(String maTayNgheSearch) {
		try(var em = getEntityManager()){
			return em.find(TayNghe.class, maTayNgheSearch);
		}
	}

	@Override
	public String timMaTayNgheCuoiCung() {
		try(var em = getEntityManager()){
			return em.createNamedQuery("TayNghe.timMaTayNgheCuoiCung", String.class).getSingleResult();
		}
	}

	@Override
	public boolean xoaTayNgheBangMa(String maTayNghe) {
		try(var em = getEntityManager()){
			em.getTransaction().begin();
			TayNghe tayNghe = em.find(TayNghe.class, maTayNghe);
			em.remove(tayNghe);
			em.getTransaction().commit();
			return true;
		}
	}

}
