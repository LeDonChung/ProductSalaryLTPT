package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.dao.CongDoanSanPhamDAO;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

public class CongDoanSanPhamDAOImpl extends AbstractDAO implements CongDoanSanPhamDAO, Serializable {

    @Override
    public List<CongDoanSanPham> timTatCaCongDoanSanPham(String maSanPham) {
        try (var em = getEntityManager()) {
            String query = "SELECT cd FROM CongDoanSanPham cd WHERE cd.sanPham.maSanPham = :maSanPham";
            return em.createQuery(query, CongDoanSanPham.class).setParameter("maSanPham", maSanPham).getResultList();
        }
    }

    @Override
    public List<CongDoanSanPham> timTatCaCongDoanSanPhamDangHoatDongBangMaSanPham(String maSanPham) {
        try (var em = getEntityManager()) {
            String query = "SELECT cd FROM CongDoanSanPham cd WHERE cd.sanPham.maSanPham = :maSanPham AND cd.trangThai = true ORDER BY cd.maCongDoan ASC";
            return em.createQuery(query, CongDoanSanPham.class)
                    .setParameter("maSanPham", maSanPham)
                    .getResultList();
        }
    }

    @Override
    public CongDoanSanPham capNhatCongDoanSanPham(CongDoanSanPham congDoanSanPham) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();

            congDoanSanPham = em.merge(congDoanSanPham);

            em.getTransaction().commit();

            return congDoanSanPham;
        }
    }

    @Override
    public boolean capNhatTrangThaiCongDoanSanPham(String maCongDoanSanPham, boolean trangThai) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();

            CongDoanSanPham congDoanSanPham = timKiemBangMaCongDoan(maCongDoanSanPham);
            if (congDoanSanPham != null) {
                congDoanSanPham.setTrangThai(trangThai);

                em.merge(congDoanSanPham);

                em.getTransaction().commit();
                return true;
            }
            return false;
        }
    }

    @Override
    public CongDoanSanPham themCongDoanSanPham(CongDoanSanPham congDoanSanPham) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();

            em.persist(congDoanSanPham);

            em.getTransaction().commit();
            return timKiemBangMaCongDoan(congDoanSanPham.getMaCongDoan());
        }
    }

    @Override
    public CongDoanSanPham timKiemBangMaCongDoan(String maCongDoanSearch) {
        try (var em = getEntityManager()) {
            return em.find(CongDoanSanPham.class, maCongDoanSearch);
        }
    }

    @Override
    public String timMaCongDoanSanPhamCuoiCungBangMaSanPham(String maSanPham) {
        try (EntityManager em = getEntityManager()) {
            String query = "SELECT cd FROM CongDoanSanPham cd WHERE cd.sanPham.maSanPham = :maSanPham ORDER BY cd.maCongDoan DESC";
            List<CongDoanSanPham> sanPhams = em.createQuery(query, CongDoanSanPham.class)
                    .setParameter("maSanPham", maSanPham)
                    .setMaxResults(1)
                    .getResultList();
            return sanPhams.isEmpty() ? null : sanPhams.get(0).getMaCongDoan();
        }
    }

    @Override
    public void capNhatSoLuongCanCuaCongDoan(String maCongDoan, int soLuong) {
        try(var em = getEntityManager()) {
            em.getTransaction().begin();

            CongDoanSanPham congDoanSanPham = timKiemBangMaCongDoan(maCongDoan);
            congDoanSanPham.setSoLuongCanLam(congDoanSanPham.getSoLuongCanLam() - soLuong);
            em.merge(congDoanSanPham);

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void capNhatSoLuongCanCuaCongDoanSau(String maCongDoan, int soLuong) {
        try(var em = getEntityManager()) {
            em.getTransaction().begin();

            CongDoanSanPham congDoanSanPham = timKiemBangMaCongDoan(maCongDoan);
            congDoanSanPham.setSoLuongCanLam(congDoanSanPham.getSoLuongCanLam() + soLuong);

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public CongDoanSanPham timCongDoanlamSauBangMaCongDoan(String maCongDoan) {
        try(var em = getEntityManager()) {
           String query = "SELECT cd FROM CongDoanSanPham cd WHERE cd.congDoanLamTruoc.maCongDoan = :maCongDoanLamTruoc";
           return em.createQuery(query, CongDoanSanPham.class)
                   .setParameter("maCongDoanLamTruoc", maCongDoan)
                   .getSingleResult();
        }
    }
}
