package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.SanPham;
import com.product.salary.application.dao.SanPhamDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAOImpl extends AbstractDAO implements SanPhamDAO, Serializable {

	@Override
	public List<SanPham> timKiemTatCaSanPham() {
		try(var em = getEntityManager()) {
			return em.createNamedQuery("SanPham.timKiemTatCaSanPham", SanPham.class).getResultList();
		}
	}

	@Override
	public SanPham capNhatSanPham(SanPham sanPham) {
		try(var em = getEntityManager()) {
			em.getTransaction().begin();
			em.merge(sanPham);
			em.getTransaction().commit();
			return sanPham;
		}
	}

	@Override
	public boolean capNhatTrangThaiSanPham(String maSanPham, boolean trangThai) {
		try(var em = getEntityManager()) {
			SanPham sanPham = em.find(SanPham.class, maSanPham);
			if(sanPham != null) {
				em.getTransaction().begin();
				sanPham.setTrangThai(trangThai);
				em.merge(sanPham);
				em.getTransaction().commit();
				return true;
			}
			return false;
		}
	}

	@Override
	public List<SanPham> timKiemSanPham(SanPham search) {
		try(var em = getEntityManager()) {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<SanPham> cq = cb.createQuery(SanPham.class);
			Root<SanPham> product = cq.from(SanPham.class);

			List<Predicate> predicates = new ArrayList<>();
			if (!StringUtils.isBlank(search.getMaSanPham())) {
				predicates.add(cb.like(product.get("maSanPham"), "%" + search.getMaSanPham() + "%"));
			}
			if (!StringUtils.isBlank(search.getDonViTinh())) {
				predicates.add(cb.like(product.get("donViTinh"), "%" + search.getDonViTinh() + "%"));
			}
			if (!StringUtils.isBlank(search.getTenSanPham())) {
				predicates.add(cb.like(product.get("tenSanPham"), "%" + search.getTenSanPham() + "%"));
			}
			if (search.isTrangThai() != null) {
				predicates.add(cb.equal(product.get("trangThai"), search.isTrangThai()));
			}
			if (!StringUtils.isBlank(search.getChatLieu())) {
				predicates.add(cb.like(product.get("chatLieu"), "%" + search.getChatLieu() + "%"));
			}
			if (search.getDonGia() != null && search.getDonGia() >= 0) {
				predicates.add(cb.greaterThanOrEqualTo(product.get("donGia"), search.getDonGia()));
			}

			cq.where(cb.and(predicates.toArray(new Predicate[0])));

			TypedQuery<SanPham> query = em.createQuery(cq);
			return query.getResultList();
		}
	}

	@Override
	public SanPham themSanPham(SanPham sanPham) {
		try(var em = getEntityManager()) {
			em.getTransaction().begin();
			em.persist(sanPham);
			em.getTransaction().commit();

			sanPham = timKiemBangMaSanPham(sanPham.getMaSanPham());

			return sanPham;
		}
	}

	@Override
	public SanPham timKiemBangMaSanPham(String maSanPhamSeach) {
		try(var em = getEntityManager()) {
			SanPham sanPham = em.find(SanPham.class, maSanPhamSeach);
			return sanPham;
		}
	}

	@Override
	public String timMaSanPhamCuoiCung() {
		try(EntityManager em = getEntityManager()) {
			List<SanPham> sanPhams = em.createNamedQuery("SanPham.timMaSanPhamCuoiCung", SanPham.class).setMaxResults(1).getResultList();
			return sanPhams.isEmpty() ? null : sanPhams.get(0).getMaSanPham();
		}
	}

	@Override
	public List<SanPham> timTatCaSanPhamDangSanXuat() {
		try(var em = getEntityManager()) {
			return em.createNamedQuery("SanPham.timTatCaSanPhamDangSanXuat", SanPham.class).getResultList();
		}
	}

	@Override
	public void capNhatSoLuongCongDoan(String maSanPham, int i) {
		try(var em = getEntityManager()) {
			SanPham sanPham = em.find(SanPham.class, maSanPham);
			if(sanPham != null) {
				em.getTransaction().begin();
				sanPham.setSoCongDoan(i);
				em.merge(sanPham);
				em.getTransaction().commit();
			}
		}
	}

	@Override
	public boolean kiemTraTonKho(String maSanPham, int soLuongKiemTra) {
		try(var em = getEntityManager()) {
			SanPham sanPham = em.find(SanPham.class, maSanPham);
			if(sanPham != null) {
				return sanPham.getSoLuongTon() >= soLuongKiemTra;
			}
			return false;
		}
	}
	
	@Override
	public int tongSoLuongSanPham() {
		try(var em = getEntityManager()) {
			return em.createNamedQuery("SanPham.tongSoLuongSanPham", Long.class).getSingleResult().intValue();
		}
	}
}
