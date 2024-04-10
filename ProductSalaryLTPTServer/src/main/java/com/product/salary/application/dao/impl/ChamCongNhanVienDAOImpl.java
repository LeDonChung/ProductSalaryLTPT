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
			Query query = em.createQuery("SELECT ccnv FROM ChamCongNhanVien ccnv WHERE ccnv.ngayChamCong = :ngayChamCong AND ccnv.caLam.maCa = :maCa", ChamCongNhanVien.class);
			query.setParameter("ngayChamCong", ngayChamCong);
			query.setParameter("maCa", maCa);
			return query.getResultList();
		}
	}

	@Override
	public List<NhanVien> timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong(LocalDate ngayChamCong, String caLam) {
		try (var em = getEntityManager()){
			Query query = em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.maNhanVien NOT IN " +
					"(SELECT ccnv.nhanVien.maNhanVien FROM ChamCongNhanVien ccnv " +
					" WHERE ccnv.ngayChamCong = :ngayChamCong AND ccnv.caLam.maCa = :maCa)");
			query.setParameter("ngayChamCong", ngayChamCong);
			query.setParameter("maCa", caLam);

			return query.getResultList();
		}
	}

	@Override
	public ChamCongNhanVien themChamCongNhanVien(ChamCongNhanVien chamCongNV) {
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
			List<ChamCongNhanVien> chamCongNhanViens = em.createQuery("SELECT ccnv FROM ChamCongNhanVien ccnv WHERE ccnv.ngayChamCong = :ngayChamCong AND ccnv.caLam.maCa = :maCa ORDER BY ccnv.maChamCong DESC", ChamCongNhanVien.class)
					.setParameter("ngayChamCong", ngayChamCong)
					.setParameter("maCa", caLam)
					.setMaxResults(1)
					.getResultList();
			return chamCongNhanViens.isEmpty() ? null : chamCongNhanViens.get(0).getMaChamCong();
		}
	}

	@Override
	public boolean capNhatTrangThaiDiLamCuaNhanVien(String maChamCong, int trangThai) {
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
			Query query = em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.maNhanVien IN " +
					"(SELECT ccnv.nhanVien.maNhanVien FROM ChamCongNhanVien ccnv WHERE MONTH(ccnv.ngayChamCong) = :thang AND YEAR(ccnv.ngayChamCong) = :nam)");
			query.setParameter("thang", thang);
			query.setParameter("nam", nam);
			return query.getResultList();
		}
	}

	@Override
	public boolean capNhatMaLuongBangMaNhanVienVaThangVaNam(String maNhanVien, int thang, int nam, String maLuong) {
		try (var em = getEntityManager()){
			Query query = em.createQuery("UPDATE ChamCongNhanVien ccnv SET ccnv.luongNhanVien.maLuong = :maLuong " +
					"WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang AND YEAR(ccnv.ngayChamCong) = :nam");
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
			Query query = em.createQuery("SELECT ccnv FROM ChamCongNhanVien ccnv " +
					"WHERE ccnv.nhanVien.maNhanVien = :maNhanVien " +
					"AND MONTH(ccnv.ngayChamCong) = :thang " +
					"AND YEAR(ccnv.ngayChamCong) = :nam", ChamCongNhanVien.class);
			query.setParameter("maNhanVien", maNhanVien);
			query.setParameter("thang", thang);
			query.setParameter("nam", nam);
			return query.getResultList();
		}
	}
}
