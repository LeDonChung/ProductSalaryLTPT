package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.SanPham;
import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.dao.ToNhomDAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToNhomDAOImpl extends AbstractDAO implements ToNhomDAO {

	@Override
	public List<ToNhom> timKiemTatCaToNhom() {
		try (var em = getEntityManager()) {
			return em.createNamedQuery("ToNhom.timKiemTatCaToNhom", ToNhom.class).getResultList();
		}
	}

	@Override
	public List<ToNhom> timKiemTatCaToNhomDangHoatDong() {
		try (var em = getEntityManager()) {
			return em.createNamedQuery("ToNhom.timKiemTatCaToNhomDangHoatDong", ToNhom.class).getResultList();
		}
	}

	@Override
	public List<ToNhom> timKiemToNhom(ToNhom toNhom) {
		try(var em = getEntityManager()) {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ToNhom> cq = cb.createQuery(ToNhom.class);
			Root<ToNhom> toNhomS = cq.from(ToNhom.class);

			List<Predicate> predicates = new ArrayList<>();
			if (!StringUtils.isBlank(toNhom.getMaToNhom())) {
				predicates.add(cb.like(toNhomS.get("maToNhom"), "%" + toNhom.getMaToNhom() + "%"));
			}
			if (!StringUtils.isBlank(toNhom.getTenToNhom())) {
				predicates.add(cb.like(toNhomS.get("tenToNhom"), "%" + toNhom.getTenToNhom() + "%"));
			}
			if (toNhom.isTrangThai() != null) {
				predicates.add(cb.equal(toNhomS.get("trangThai"), toNhom.isTrangThai()));
			}
			cq.where(cb.and(predicates.toArray(new Predicate[0])));

			TypedQuery<ToNhom> query = em.createQuery(cq);
			return query.getResultList();
		}
	}

	@Override
	public ToNhom timKiemBangMaToNhom(String maToNhom) {
		try (var em = getEntityManager()) {
			return em.find(ToNhom.class, maToNhom);
		}
	}

	@Override
	public ToNhom capNhatToNhom(ToNhom toNhom) {
		try (var em = getEntityManager()) {
			em.getTransaction().begin();
			em.merge(toNhom);
			em.getTransaction().commit();
			return toNhom;
		}
	}

	@Override
	public boolean capNhatTrangThaiToNhom(String maToNhom, boolean trangThai) {
		try (var em = getEntityManager()) {
			ToNhom toNhom = em.find(ToNhom.class, maToNhom);
			if (toNhom != null) {
				em.getTransaction().begin();
				toNhom.setTrangThai(trangThai);
				em.merge(toNhom);
				em.getTransaction().commit();
				return true;
			}
		}
		return false;
	}

	@Override
	public ToNhom themToNhom(ToNhom toNhom) {
		try (var em = getEntityManager()) {
			em.getTransaction().begin();
			em.persist(toNhom);
			em.getTransaction().commit();
			return toNhom;
		}

	}

	@Override
	public boolean capNhatSoLuongCongNhanBangMaToNhom(String maToNhom, int soLuong) {
		try(var em = getEntityManager()) {
			ToNhom toNhom = em.find(ToNhom.class, maToNhom);
			if(toNhom != null) {
				em.getTransaction().begin();
				toNhom.setSoLuongCongNhan(soLuong);
				em.merge(toNhom);
				em.getTransaction().commit();
				return true;
			}
		}
		return false;
	}

	@Override
	public String timMaToNhomCuoiCung() {
		try (var em = getEntityManager()) {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<String> cq = cb.createQuery(String.class);
			Root<ToNhom> toNhomS = cq.from(ToNhom.class);
			cq.select(toNhomS.get("maToNhom"));
			cq.orderBy(cb.desc(toNhomS.get("maToNhom")));
			TypedQuery<String> query = em.createQuery(cq);
			query.setMaxResults(1);
			return query.getSingleResult();
		}
	}

}
