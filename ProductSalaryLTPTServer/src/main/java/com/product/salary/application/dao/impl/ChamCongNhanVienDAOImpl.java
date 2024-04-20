package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.*;
import com.product.salary.application.dao.ChamCongNhanVienDAO;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

public class ChamCongNhanVienDAOImpl extends AbstractDAO implements ChamCongNhanVienDAO {

	@Override
	public List<ChamCongNhanVien> timKiemTatCaChamCongNhanVienTheoCaVaNgay(LocalDate ngayChamCong, String maCa) {
		try (var em = getEntityManager()){
			Query query = em.createNamedQuery("ChamCongNhanVien.timKiemTatCaChamCongNhanVienTheoCaVaNgay", ChamCongNhanVien.class);
			query.setParameter("ngayChamCong", ngayChamCong);
			query.setParameter("maCa", maCa);
			return query.getResultList();
		}
	}

	@Override
	public List<NhanVien> timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong(LocalDate ngayChamCong, String caLam) {
		try (var em = getEntityManager()){
			Query query = em.createNamedQuery("ChamCongNhanVien.timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong", NhanVien.class);
			query.setParameter("ngayChamCong", ngayChamCong);
			query.setParameter("maCa", caLam);

			return query.getResultList();
		}
	}

	@Override
	public synchronized ChamCongNhanVien themChamCongNhanVien(ChamCongNhanVien chamCongNV) {
		try (var em = getEntityManager()){
			em.getTransaction().begin();
			em.persist(chamCongNV);
			em.getTransaction().commit();
			chamCongNV = em.find(ChamCongNhanVien.class, chamCongNV.getMaChamCong());
			return chamCongNV;
		}
	}

	@Override
	public ChamCongNhanVien timKiemBangMaChamCongNhanVien(String maChamCong) {
		try (var em = getEntityManager()){
			return em.find(ChamCongNhanVien.class, maChamCong);
		}
	}

	@Override
	public String timKiemMaChamCongNhanVienCuoiCungTheoNgayVaCaLam(LocalDate ngayChamCong, String caLam) {
		try (var em = getEntityManager()){
			List<ChamCongNhanVien> chamCongNhanViens = em.createNamedQuery("ChamCongNhanVien.timKiemMaChamCongNhanVienCuoiCungTheoNgayVaCaLam", ChamCongNhanVien.class)
					.setParameter("ngayChamCong", ngayChamCong)
					.setParameter("maCa", caLam)
					.setMaxResults(1)
					.getResultList();
			return chamCongNhanViens.isEmpty() ? null : chamCongNhanViens.get(0).getMaChamCong();
		}
	}

	@Override
	public synchronized boolean capNhatTrangThaiDiLamCuaNhanVien(String maChamCong, int trangThai) {
		try (var em = getEntityManager()){
			ChamCongNhanVien chamCongNhanVien = em.find(ChamCongNhanVien.class, maChamCong);
			if (chamCongNhanVien != null){
				em.getTransaction().begin();
				chamCongNhanVien.setTrangThai(trangThai);
				em.merge(chamCongNhanVien);
				em.getTransaction().commit();
				return true;
			}
			return false;
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<NhanVien> timKiemDanhSachNhanVienDiLamBangThangVaNam(int thang, int nam) {
		try (var em = getEntityManager()){
			Query query = em.createNamedQuery("ChamCongNhanVien.timKiemDanhSachNhanVienDiLamBangThangVaNam", NhanVien.class);
			query.setParameter("thang", thang);
			query.setParameter("nam", nam);
			return query.getResultList();
		}
	}

	@Override
	public synchronized boolean capNhatMaLuongBangMaNhanVienVaThangVaNam(String maNhanVien, int thang, int nam, String maLuong) {
		try (var em = getEntityManager()){
			Query query = em.createNamedQuery("ChamCongNhanVien.capNhatMaLuongBangMaNhanVienVaThangVaNam");
			query.setParameter("maLuong", maLuong);
			query.setParameter("maNhanVien", maNhanVien);
			query.setParameter("thang", thang);
			query.setParameter("nam", nam);
			em.getTransaction().begin();
			int result = query.executeUpdate();
			em.getTransaction().commit();
			return result > 0;
		}
	}

	@Override
	public List<ChamCongNhanVien> timKiemChamCongNhanVienTheoMaNhanVienVaThangVaNam(String maNhanVien, int thang,
			int nam) {
		try (var em = getEntityManager()){
			Query query = em.createNamedQuery("ChamCongNhanVien.timKiemChamCongNhanVienTheoMaNhanVienVaThangVaNam", ChamCongNhanVien.class);
			query.setParameter("maNhanVien", maNhanVien);
			query.setParameter("thang", thang);
			query.setParameter("nam", nam);
			return query.getResultList();
		}
	}
}
