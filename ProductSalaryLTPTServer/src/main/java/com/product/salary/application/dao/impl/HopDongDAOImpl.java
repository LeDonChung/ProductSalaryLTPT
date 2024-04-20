package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.product.salary.application.dao.HopDongDAO;
import com.product.salary.application.entity.SanPham;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class HopDongDAOImpl extends AbstractDAO implements HopDongDAO, Serializable {

	@Override
	public List<HopDong> timTatCaHopDong() {

		try(EntityManager em = getEntityManager()) {
            return em.createNamedQuery("HopDong.timTatCaHopDong", HopDong.class).getResultList();
		}
	}

	@Override
	public HopDong themHopDong(HopDong hopDong) {

		try(EntityManager em = getEntityManager()) {
			em.getTransaction().begin();
			em.persist(hopDong);
			em.getTransaction().commit();

            return em.find(HopDong.class, hopDong.getMaHopDong());
		}
	}

	@Override
	public boolean thanhLyHopDong(String maHopDong) {
		try(EntityManager em = getEntityManager()) {
			em.getTransaction().begin();
			HopDong hopDong = em.find(HopDong.class, maHopDong);
			hopDong.setTrangThai(true);
			hopDong.setNgayKetThuc(LocalDate.now());
			em.merge(hopDong);

			List<ChiTietHopDong> chiTietHopDongs = timTatCaChiTietHopDongBangMaHopDong(maHopDong);
			chiTietHopDongs.forEach(chiTietHopDong -> {
				SanPham sp = em.find(SanPham.class, chiTietHopDong.getSanPham().getMaSanPham());
                try {
                    sp.setSoLuongTon(sp.getSoLuongTon() - chiTietHopDong.getSoLuong());
					em.merge(sp);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

			em.getTransaction().commit();
			return true;
		}
	}

	@Override
	public HopDong timHopDongBangMaHopDong(String maHopDongS) {
		try(var em = getEntityManager()) {
			return em.find(HopDong.class, maHopDongS);
		}
	}

	@Override
	public String timMaHopDongCuoiCung() {
		try(EntityManager em = getEntityManager()) {
			List<HopDong> hopDongs = em.createNamedQuery("HopDong.timMaHopDongCuoiCung", HopDong.class).setMaxResults(1).getResultList();
			return hopDongs.isEmpty() ? null : hopDongs.get(0).getMaHopDong();
		}
	}

	@Override
	public int tongSoLuongHopDong() {
		try(var em = getEntityManager()) {
			return ((Long) em.createNamedQuery("HopDong.tongSoLuongHopDong").getSingleResult()).intValue();
		}
	}

	@Override
	public void capNhatHopDong(HopDong hopDong) {
		try(EntityManager em = getEntityManager()) {
			em.getTransaction().begin();
			em.merge(hopDong);
			em.getTransaction().commit();
		}
	}

	@Override
	public List<ChiTietHopDong> timTatCaChiTietHopDongBangMaHopDong(String maHopDong) {
		try(EntityManager em = getEntityManager()) {
			return em.createNamedQuery("HopDong.timTatCaChiTietHopDongBangMaHopDong", ChiTietHopDong.class).setParameter("maHopDong", maHopDong).getResultList();
		}
	}

	@Override
	public ChiTietHopDong themChiTietHopDong(ChiTietHopDong chiTietHopDong) {
		try(EntityManager em = getEntityManager()) {
			em.getTransaction().begin();
			em.persist(chiTietHopDong);
			em.getTransaction().commit();
			return chiTietHopDong;
		}
	}
}
