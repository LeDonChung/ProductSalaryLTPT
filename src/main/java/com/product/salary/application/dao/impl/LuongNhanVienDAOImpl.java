package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.LuongNhanVien;
import com.product.salary.application.dao.LuongNhanVienDAO;
import jakarta.persistence.Query;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuongNhanVienDAOImpl extends AbstractDAO implements LuongNhanVienDAO {

    @Override
    public Map<String, Double> thongKeLuongNhanVienTheoNam() {
        Connection conn = getConnection();
        Statement statement = null;
        ResultSet rs = null;
        Map<String, Double> results = new HashMap<String, Double>();
        if (conn != null) {
            try {
                StringBuilder query = new StringBuilder(
                        "SELECT Nam, SUM(Luong) AS 'Luong' FROM LuongNhanVien GROUP BY Nam ORDER BY Nam");
                statement = conn.createStatement();
                rs = statement.executeQuery(query.toString());
                while (rs.next()) {
                    results.put(String.format("%s", rs.getInt("Nam")), rs.getDouble("Luong"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return results;
    }

    @Override
    public int laySoLuongCaSangVaChieuKhongThuocNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(*) FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam AND ccnv.caLam.maCa IN ('SA', 'CH') AND ccnv.trangThai IN (1, 2)");
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            return query.getFirstResult();
        }
    }

    @Override
    public int laySoLuongCaToiKhongThuocNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(*) FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam AND ccnv.caLam.maCa = 'TO' AND ccnv.trangThai IN (1, 2)");
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            return query.getFirstResult();
        }
    }

    @Override
    public int laySoLuongCaLamNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(*) FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam AND DAYOFWEEK(ccnv.ngayChamCong) = 1 AND ccnv.trangThai IN (1, 2)");
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            return query.getFirstResult();
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
            Query query = em.createQuery("SELECT ccnv.maChamCong, ccnv.caLam.maCa, ccnv.ngayChamCong, ccnv.trangThai FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam", Object[].class);
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);

            List<Object[]> results = query.getResultList();
            return results.stream().map(result -> {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("MaChamCong", result[0]);
                map.put("MaCa", result[1]);
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
        try (var em = getEntityManager()){
            Query query = em.createQuery("SELECT MONTH(l.thang) AS thang, SUM(l.luong) AS tongLuong " +
                    "FROM LuongNhanVien l WHERE YEAR(l.luong) = :nam GROUP BY MONTH(l.thang)", Object[].class);
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
            luongNV.setLuongThuong(luongThuong);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int demSoLuongDiLamTreCuaNhanVien(String maNhanVien, int thang, int nam) {
        try (var em = getEntityManager()) {
            Query query = em.createQuery("SELECT COUNT(*) FROM ChamCongNhanVien ccnv " +
                    "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang " +
                    "AND YEAR(ccnv.ngayChamCong) = :nam AND ccnv.trangThai = 2", Integer.class);
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            return query.getFirstResult();
        }
    }

}
