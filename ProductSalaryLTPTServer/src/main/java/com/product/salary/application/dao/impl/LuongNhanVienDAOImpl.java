package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.LuongNhanVien;
import com.product.salary.application.dao.LuongNhanVienDAO;
import jakarta.persistence.Query;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuongNhanVienDAOImpl extends AbstractDAO implements LuongNhanVienDAO {

    @Override
    public Map<String, Double> thongKeLuongNhanVienTheoNam() {

        try(var em = getEntityManager()) {
            Query query = em.createQuery("SELECT l.nam, SUM(l.luong) FROM LuongNhanVien l GROUP BY l.nam");
            List<Object[]> results = query.getResultList();
            Map<String, Double> map = new HashMap<>();
            for (Object[] result : results) {
                map.put(String.valueOf(result[0]), (Double) result[1]);
            }
            return map;
        }
    }

    @Override
    public Long laySoLuongCaSangVaChieuKhongThuocNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(*) FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam AND ccnv.caLam.maCa IN ('SA', 'CH') AND ccnv.trangThai = 1");
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            return (Long) query.getSingleResult();
        }
    }

    @Override
    public Long laySoLuongCaToiKhongThuocNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(*) FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam AND ccnv.caLam.maCa = 'TO' AND ccnv.trangThai IN (1, 2)");
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            return (Long) query.getSingleResult();
        }
    }

    @Override
    public Long laySoLuongCaLamNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam) {
        try (var em = getEntityManager()) {
            // Query: SELECT COUNT(*) FROM ChamCongNhanVien ccnv WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang AND YEAR(ccnv.ngayChamCong) = :nam AND  (ccnv.ngayChamCong) = 1 AND ccnv.trangThai IN (1, 2)
            // So sánh ngày chủ nhật trong tuần sửa dụng jpa: DAYOFWEEK(ccnv.ngayChamCong) = 1

            Query query = em.createQuery("SELECT COUNT(*) FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam AND ccnv.trangThai IN (1, 2)");
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            return (Long) query.getSingleResult();
        }
    }

    @Override
    public List<LuongNhanVien> timKiemTatCaLuongNhanVienTheoThangVaNam(int thang, int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT l FROM LuongNhanVien l WHERE l.thang = :thang AND l.nam = :nam", LuongNhanVien.class);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            return query.getResultList();
        }
    }

    @Override
    public LuongNhanVien timKiemBangMaLuong(String maLuong) {
        try (var em = getEntityManager()) {
            return em.find(LuongNhanVien.class, maLuong);
        }
    }

    @Override
    public LuongNhanVien themLuongNhanVien(LuongNhanVien luongNV) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(luongNV);
            em.getTransaction().commit();
            luongNV = em.find(LuongNhanVien.class, luongNV.getMaLuong());
            return luongNV;
        }
    }

    @Override
    public LuongNhanVien capNhatLuongNhanVien(LuongNhanVien luongNV) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();
            em.merge(luongNV);
            em.getTransaction().commit();
            return luongNV;
        }
    }

    @Override
    public List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maNhanVien, int thang, int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT ccnv.maChamCong, ccnv.caLam.tenCa, ccnv.ngayChamCong, ccnv.trangThai FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam", Object[].class);
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);

            List<Object[]> results = query.getResultList();
            return results.stream().map(result -> {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("MaChamCong", result[0]);
                map.put("CaLam", result[1]);
                map.put("NgayChamCong", result[2]);
                String trangThai = "";
                if ((int) result[3] == 1) {
                    trangThai = "Có mặt";
                } else if ((int) result[3] == 2) {
                    trangThai = "Trễ";
                } else {
                    trangThai = "Nghỉ";
                }
                map.put("TrangThai", trangThai);
                return map;
            }).toList();
        }
    }

    @Override
    public Map<String, Double> thongKeLuongNhanVienTheoThang(int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT l.thang AS thang, SUM(l.luong) AS tongLuong " +
                    "FROM LuongNhanVien l WHERE l.nam = :nam GROUP BY l.thang", Object[].class);
            query.setParameter("nam", nam);
            List<Object[]> results = query.getResultList();
            Map<String, Double> map = new HashMap<>();
            for (Object[] result : results) {
                map.put(String.valueOf(result[0]), (Double) result[1]);
            }
            return map;
        }
    }

    @Override
    public void capNhatLuongThuong(String maLuong, double luongThuong) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();
            LuongNhanVien luongNV = em.find(LuongNhanVien.class, maLuong);
            if (luongNV == null) {
                return;
            }
            luongNV.setLuongThuong(luongThuong);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long demSoLuongDiLamTreCuaNhanVien(String maNhanVien, int thang, int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(*) FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam AND ccnv.trangThai = 2", Integer.class);
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            return (Long) query.getSingleResult();
        }
    }

}
