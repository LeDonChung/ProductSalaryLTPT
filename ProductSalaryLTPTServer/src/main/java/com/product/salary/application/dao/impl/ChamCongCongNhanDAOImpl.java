package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.*;
import com.product.salary.application.dao.ChamCongCongNhanDAO;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ChamCongCongNhanDAOImpl extends AbstractDAO implements ChamCongCongNhanDAO, Serializable {

	@Override
	public List<CongNhan> timDanhSachCongNhanDiLamBangThangVaNam(int thang, int nam) {

		try(var em = getEntityManager()) {
			var query = em.createNamedQuery("ChamCongCongNhan.timDanhSachCongNhanDiLamBangThangVaNam", CongNhan.class);
			query.setParameter("thang", thang);
			query.setParameter("nam", nam);
			return query.getResultList();
		}
	}

	@Override
	public boolean capNhatChamCongCongNhanBangMaCongNhanVaThangVaNam(String maCongNhan, int thang, int nam,
			String maLuong) {
		try(var em = getEntityManager()) {
			var query = em.createNamedQuery("ChamCongCongNhan.capNhatChamCongCongNhanBangMaCongNhanVaThangVaNam");
			query.setParameter("maLuong", maLuong);
			query.setParameter("nam", nam);
			query.setParameter("thang", thang);
			query.setParameter("maCongNhan", maCongNhan);
			em.getTransaction().begin();
			int status = query.executeUpdate();
			em.getTransaction().commit();
			return status > 0;
		}
	}

	@Override
	public List<ChamCongCongNhan> timTatCaChamCongCongNhan(LocalDate ngayChamCong) {

		try(var em = getEntityManager()) {
			var query = em.createNamedQuery("ChamCongCongNhan.timTatCaChamCongCongNhan", ChamCongCongNhan.class);
			query.setParameter("ngayChamCong", ngayChamCong);
			return query.getResultList();
		}
	}

	@Override
	public List<CongNhan> timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(LocalDate ngayChamCong, String caLam) {

		try(var em = getEntityManager()) {
			var query = em.createNamedQuery("ChamCongCongNhan.timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong", CongNhan.class);
			query.setParameter("ngayChamCong", ngayChamCong);
			query.setParameter("caLam", caLam);
			return query.getResultList();
		}
	}

	@Override
	public ChamCongCongNhan themChamCongCongNhan(ChamCongCongNhan chamCong) {
		try(var em = getEntityManager()) {
			em.getTransaction().begin();
			em.persist(chamCong);
			em.getTransaction().commit();

			return em.find(ChamCongCongNhan.class, chamCong.getMaChamCong());
		}
	}

	@Override
	public ChamCongCongNhan timKiemBangMaChamCongCongNhan(String maChamCong) {
		try(var em = getEntityManager()) {
			return em.find(ChamCongCongNhan.class, maChamCong);
		}
	}

	@Override
	public String timKiemMaChamCongCongNhanCuoiCungTheoNgayVaCaLam(LocalDate ngayChamCong, String caLam) {

		try(var em = getEntityManager()) {
			var query = em.createNamedQuery("ChamCongCongNhan.timKiemMaChamCongCongNhanCuoiCungTheoNgayVaCaLam", String.class);
			query.setParameter("ngayChamCong", ngayChamCong);
			query.setParameter("caLam", caLam);
			query.setMaxResults(1);
			return query.getResultList().isEmpty() ? null : query.getSingleResult();
		}
	}

	@Override
	public boolean capNhatChamCongCongNhan(String maChamCong, int trangThai, int soLuongHoanThanh) {
		try(var em = getEntityManager()) {
			em.getTransaction().begin();
			ChamCongCongNhan chamCongCongNhan = em.find(ChamCongCongNhan.class, maChamCong);
			if(chamCongCongNhan == null) {
				return false;
			}
			chamCongCongNhan.setSoLuongHoanThanh(soLuongHoanThanh);
			chamCongCongNhan.setTrangThai(trangThai);
			em.merge(chamCongCongNhan);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}
