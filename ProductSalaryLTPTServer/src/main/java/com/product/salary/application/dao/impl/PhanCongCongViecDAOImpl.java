package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.*;
import com.product.salary.application.dao.PhanCongCongViecDAO;

import java.io.Serializable;
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
			return em.createNamedQuery("PhanCongCongNhan.timMaPhanCongCuoiCung", String.class).getSingleResult();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}





	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(String maCongNhan) {
		try(var em = getEntityManager()){
			return em.createNamedQuery("PhanCongCongNhan.timTatCaPhanCongTheoMaCongNhanChuaHoanThanh", PhanCongCongNhan.class).setParameter("maCongNhan", maCongNhan).getResultList();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<CongNhan> timTatCaCongNhanChuaPhanCongVaoCongDoan(String maSanPham, String maCongDoan) {
		try(var em = getEntityManager()){
			return em.createNamedQuery("PhanCongCongNhan.timTatCaCongNhanChuaPhanCongVaoCongDoan", CongNhan.class).setParameter("maSanPham", maSanPham).setParameter("maCongDoan", maCongDoan).getResultList();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongDoan(String maCongDoan) {
		try(var em = getEntityManager()){
			return em.createNamedQuery("PhanCongCongNhan.timTatCaPhanCongTheoMaCongDoan", PhanCongCongNhan.class).setParameter("maCongDoan", maCongDoan).getResultList();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
