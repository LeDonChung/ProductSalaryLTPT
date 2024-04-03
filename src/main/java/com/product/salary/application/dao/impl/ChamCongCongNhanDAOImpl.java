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
			var query = em.createQuery("SELECT DISTINCT cn FROM ChamCongCongNhan cc " +
					"JOIN PhanCongCongNhan pc ON cc.phanCongCongNhan.maPhanCong = pc.maPhanCong " +
					"JOIN CongNhan cn ON pc.congNhan.maCongNhan = cn.maCongNhan " +
					"WHERE MONTH(cc.ngayChamCong) = :thang AND YEAR(cc.ngayChamCong) = :nam", CongNhan.class);
			query.setParameter("thang", thang);
			query.setParameter("nam", nam);
			return query.getResultList();
		}
	}

	@Override
	public boolean capNhatChamCongCongNhanBangMaCongNhanVaThangVaNam(String maCongNhan, int thang, int nam,
			String maLuong) {
		try(var em = getEntityManager()) {
			var query = em.createQuery("UPDATE ChamCongCongNhan cc SET cc.luongCongNhan.maLuong = :maLuong " +
					"WHERE cc.maChamCong IN " +
					"(SELECT cc.maChamCong FROM ChamCongCongNhan cc " +
					"JOIN PhanCongCongNhan pc ON cc.phanCongCongNhan.maPhanCong = pc.maPhanCong " +
					"WHERE YEAR(cc.ngayChamCong) = :nam AND MONTH(cc.ngayChamCong) = :thang AND pc.congNhan.maCongNhan = :maCongNhan)");
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
			var query = em.createQuery("SELECT cc FROM ChamCongCongNhan cc WHERE cc.ngayChamCong = :ngayChamCong", ChamCongCongNhan.class);
			query.setParameter("ngayChamCong", ngayChamCong);
			return query.getResultList();
		}
	}

	@Override
	public List<CongNhan> timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(LocalDate ngayChamCong, String caLam) {

		try(var em = getEntityManager()) {
			var query = em.createQuery("SELECT cn FROM CongNhan cn " +
					"WHERE cn.trangThai = true AND cn.maCongNhan NOT IN " +
					"(SELECT pc.congNhan.maCongNhan FROM ChamCongCongNhan cc JOIN PhanCongCongNhan pc ON cc.phanCongCongNhan.maPhanCong = pc.maPhanCong " +
					"WHERE cc.ngayChamCong = :ngayChamCong AND cc.caLam.maCa = :caLam)", CongNhan.class);
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
			var query = em.createQuery("SELECT cc.maChamCong FROM ChamCongCongNhan cc WHERE cc.ngayChamCong = :ngayChamCong AND cc.caLam.maCa = :caLam ORDER BY cc.maChamCong DESC", String.class);
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
