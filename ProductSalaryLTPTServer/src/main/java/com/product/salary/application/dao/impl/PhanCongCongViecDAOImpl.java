package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.*;
import com.product.salary.application.dao.PhanCongCongViecDAO;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhanCongCongViecDAOImpl extends AbstractDAO implements PhanCongCongViecDAO, Serializable {


	@Override
	public PhanCongCongNhan capNhatPhanCongCongNhan(PhanCongCongNhan phanCongCongNhan) {
		try(var em = getEntityManager()){
			em.getTransaction().begin();
			em.merge(phanCongCongNhan);
			em.getTransaction().commit();
			return phanCongCongNhan;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PhanCongCongNhan timKiemBangMaPhanCong(String maPhanCong) {
		try(var em = getEntityManager()){
			return em.find(PhanCongCongNhan.class, maPhanCong);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean xoaPhanCongCongNhan(String maPhanCong) {
		try(var em = getEntityManager()){
			em.getTransaction().begin();
			PhanCongCongNhan phanCongCongNhan = em.find(PhanCongCongNhan.class, maPhanCong);
			phanCongCongNhan.setTrangThai(false);
			em.merge(phanCongCongNhan);
			em.getTransaction().commit();
			return true;
		}
		catch (Exception e){
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public PhanCongCongNhan phanCongCongNhan(PhanCongCongNhan phanCongCongNhan) {
		try(var em = getEntityManager()){
			em.getTransaction().begin();
			phanCongCongNhan.getCongNhan().setHinhAnh(null);
			em.persist(phanCongCongNhan);
			em.getTransaction().commit();
			return phanCongCongNhan;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String timMaPhanCongCuoiCung() {
		try(var em = getEntityManager()){
			String query = "SELECT MAX(p.maPhanCong) FROM PhanCongCongNhan p";
			return em.createQuery(query, String.class).getSingleResult();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}





	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(String maCongNhan) {
		try(var em = getEntityManager()){
			String query = "SELECT p FROM PhanCongCongNhan p WHERE p.congNhan.maCongNhan = :maCongNhan AND p.trangThai = false";
			return em.createQuery(query, PhanCongCongNhan.class).setParameter("maCongNhan", maCongNhan).getResultList();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<CongNhan> timTatCaCongNhanChuaPhanCongVaoCongDoan(String maSanPham, String maCongDoan) {
		try(var em = getEntityManager()){
			String query = "SELECT c FROM CongNhan c WHERE c.maCongNhan NOT IN (SELECT DISTINCT p.congNhan.maCongNhan FROM PhanCongCongNhan p JOIN p.congDoanSanPham cd WHERE cd.sanPham.maSanPham = :maSanPham AND cd.maCongDoan = :maCongDoan AND p.trangThai = false)";
			return em.createQuery(query, CongNhan.class).setParameter("maSanPham", maSanPham).setParameter("maCongDoan", maCongDoan).getResultList();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongDoan(String maCongDoan) {
		try(var em = getEntityManager()){
			String query = "SELECT p FROM PhanCongCongNhan p WHERE p.congDoanSanPham.maCongDoan = :maCongDoan";
			return em.createQuery(query, PhanCongCongNhan.class).setParameter("maCongDoan", maCongDoan).getResultList();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
