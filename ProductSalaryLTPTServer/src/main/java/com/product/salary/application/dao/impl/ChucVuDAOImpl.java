package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.dao.ChucVuDAO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChucVuDAOImpl extends AbstractDAO implements ChucVuDAO, Serializable {


	@Override
	public List<ChucVu> timKiemTatCaChucVu() {
		try (var em = getEntityManager()){
			return em.createQuery("SELECT cv FROM ChucVu cv", ChucVu.class).getResultList();
		}
	}

	@Override
	public ChucVu capNhatChucVu(ChucVu chucVu) {
		try (var em = getEntityManager()){
			em.getTransaction().begin();
			em.merge(chucVu);
			em.getTransaction().commit();
			return chucVu;
		}
	}

	@Override
	public ChucVu themChucVu(ChucVu chucVu) {
		try (var em = getEntityManager()){
			em.getTransaction().begin();
			em.persist(chucVu);
			em.getTransaction().commit();
			chucVu = timKiemBangMaChucVu(chucVu.getMaChucVu());
			return chucVu;
		}
	}

	@Override
	public ChucVu timKiemBangMaChucVu(String maChucVuSearch) {
		try (var em = getEntityManager()){
			return em.find(ChucVu.class, maChucVuSearch);
		}
	}

	@Override
	public String timMaChucVuCuoiCung() {
		try (var em = getEntityManager()){
			String query = "SELECT cv FROM ChucVu cv ORDER BY cv.maChucVu DESC";
			List<ChucVu> chucVus = em.createQuery(query, ChucVu.class).setMaxResults(1).getResultList();
			return chucVus.isEmpty() ? null : chucVus.get(0).getMaChucVu();
		}
	}

	@Override
	public boolean xoaChucVuBangMa(String maChucVu) {
		try (var em = getEntityManager()){
			ChucVu chucVu = em.find(ChucVu.class, maChucVu);
			if(chucVu != null){
				em.getTransaction().begin();
				em.remove(chucVu);
				em.getTransaction().commit();
				return true;
			}
			return false;
		}
	}

}
