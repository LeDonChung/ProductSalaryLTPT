package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.LuongCongNhan;
import com.product.salary.application.dao.LuongCongNhanDAO;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class LuongCongNhanDAOImpl extends AbstractDAO implements LuongCongNhanDAO, Serializable {

    @Override
    public double tinhTongTienCongNhanTheoMaCongNhanVaThangVaNam(String maCongNhan, int thang, int nam) {

        try (var em = getEntityManager()) {
            return em.createNamedQuery("LuongCongNhan.tinhTongTienCongNhanTheoMaCongNhanVaThangVaNam", Double.class).setParameter("thang", thang)
                    .setParameter("nam", nam)
                    .setParameter("maCongNhan", maCongNhan)
                    .getSingleResult();
        }
    }

    @Override
    public LuongCongNhan capNhatLuongCongNhan(LuongCongNhan luongCongNhan) {

        try (var em = getEntityManager()) {
            em.getTransaction().begin();
            luongCongNhan = em.merge(luongCongNhan);
            em.getTransaction().commit();
            return luongCongNhan;
        }
    }

    @Override
    public LuongCongNhan themLuongCongNhan(LuongCongNhan luongCongNhan) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(luongCongNhan);
            em.getTransaction().commit();
            return luongCongNhan;
        }
    }

    @Override
    public LuongCongNhan timKiemLuongCongNhanBangMaLuong(String maLuongS) {
        try (var em = getEntityManager()) {
            return em.find(LuongCongNhan.class, maLuongS);
        }
    }

    @Override
    public List<LuongCongNhan> timTatCaLuongCongNhanTheoThangVaNam(int thangS, int namS) {
        try (var em = getEntityManager()) {
            var query = em.createNamedQuery("LuongCongNhan.timTatCaLuongCongNhanTheoThangVaNam", LuongCongNhan.class);
            query.setParameter("thang", thangS);
            query.setParameter("nam", namS);
            return query.getResultList();
        }
    }

    @Override
    public List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maCongNhan, int thang, int nam) {
        try (var em = getEntityManager()) {
            var query = em.createNamedQuery("LuongCongNhan.timTatCaChiTietLuongTheoThangVaNam", Object[].class);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            query.setParameter("maCongNhan", maCongNhan);

            List<Object[]> results = query.getResultList();
            return results.stream().map(result -> {
                Map<String, Object> chiTiet = new HashMap<>();
                chiTiet.put("MaChamCong", result[0]);
                chiTiet.put("MaCongNhan", result[1]);
                chiTiet.put("HoTen", result[2]);
                chiTiet.put("TenSanPham", result[3]);
                chiTiet.put("TenCongDoan", result[4]);
                chiTiet.put("NgayCham", result[5]);
                chiTiet.put("CaLam", result[6]);
                chiTiet.put("SoLuongHoanThanh", result[7]);
                chiTiet.put("TongTien", result[8]);
                String trangThai = "";

                if (Integer.parseInt(result[9].toString()) == 0) {
                    trangThai = "Nghỉ không phép";
                } else if (Integer.parseInt(result[9].toString()) == 1) {
                    trangThai = "Có mặt";
                } else {
                    trangThai = "Nghỉ có phép";
                }

                chiTiet.put("TrangThai", trangThai);
                return chiTiet;
            }).toList();
        }
    }

    @Override
    public Map<String, Double> thongKeLuongCongNhanTheoNam() {
        try (var em = getEntityManager()) {
            var query = em.createNamedQuery("LuongCongNhan.thongKeLuongCongNhanTheoNam", Object[].class);

            List<Object[]> results = query.getResultList();
            return results.stream()
                    .collect(HashMap::new, (map, result) -> map.put(result[0].toString(), Double.parseDouble(result[1].toString())), HashMap::putAll);
        }
    }

    @Override
    public Map<String, Double> thongKeLuongCongNhanTheoThang(int nam) {

        try (var em = getEntityManager()) {
            var query = em.createNamedQuery("LuongCongNhan.thongKeLuongCongNhanTheoThang", Object[].class);
            query.setParameter("nam", nam);

            List<Object[]> results = query.getResultList();
            return results.stream()
                    .collect(LinkedHashMap::new, (map, result) -> map.put(result[0].toString(), Double.parseDouble(result[1].toString())), LinkedHashMap::putAll);
        }
    }

    @Override
    public void capNhatLuongThuong(String maLuong, double luongThuong) {
        try (var em = getEntityManager()) {
            LuongCongNhan luongCongNhan = em.find(LuongCongNhan.class, maLuong);
            if (luongCongNhan != null) {
                em.getTransaction().begin();
                luongCongNhan.setLuongThuong(luongThuong);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
