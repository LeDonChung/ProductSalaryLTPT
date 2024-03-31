package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.dao.PhongBanDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongBanDAOImpl extends AbstractDAO implements PhongBanDAO {

    @Override
    public List<PhongBan> timKiemTatCaPhongBan() {
        try (var em = getEntityManager()) {
            return em.createQuery("SELECT pb FROM PhongBan pb", PhongBan.class).getResultList();
        }
    }

    @Override
    public List<PhongBan> timKiemPhongBan(PhongBan phongBan) {
        try (var em = getEntityManager()) {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<PhongBan> criteriaQuery = criteriaBuilder.createQuery(PhongBan.class);
            Root<PhongBan> root = criteriaQuery.from(PhongBan.class);

            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isBlank(phongBan.getMaPhongBan())) {
                predicates.add(criteriaBuilder.like(root.get("maPhongBan"), "%" + phongBan.getMaPhongBan() + "%"));
            }
            if (!StringUtils.isBlank(phongBan.getTenPhongBan())) {
                predicates.add(criteriaBuilder.like(root.get("tenPhongBan"), "%" + phongBan.getTenPhongBan() + "%"));
            }
            if (phongBan.isTrangThai() != null) {
                predicates.add(criteriaBuilder.equal(root.get("trangThai"), phongBan.isTrangThai()));
            }
            if (phongBan.getSoLuongNhanVien() != null && phongBan.getSoLuongNhanVien() >= 0) {
                predicates.add(criteriaBuilder.equal(root.get("soLuongNhanVien"), phongBan.getSoLuongNhanVien()));
            }

            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            TypedQuery<PhongBan> query = em.createQuery(criteriaQuery);

            return query.getResultList();
        }
    }


    @Override
    public PhongBan timKiemBangMaPhongBan(String maPhongBan) {
        try (var em = getEntityManager()) {
            return em.find(PhongBan.class, maPhongBan);
        }
    }

    @Override
    public PhongBan capNhatPhongBan(PhongBan phongBan) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();
            em.merge(phongBan);
            em.getTransaction().commit();
            return phongBan;
        }
    }

    @Override
    public boolean capNhatTrangThaiPhongBan(String maPhongBan, boolean trangThai) {
        Connection connect = getConnection();
        PreparedStatement state = null;
        int status = 0;
        if (connect != null) {
            try {
                StringBuilder query = new StringBuilder("UPDATE PhongBan SET TrangThai = ?");
                query.append(" WHERE MaPhongBan = ?");
                state = connect.prepareStatement(query.toString());
                state.setBoolean(1, trangThai);
                state.setString(2, maPhongBan);

                status = state.executeUpdate();

                if (status > 0)
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (state != null) {
                    try {
                        state.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public PhongBan themPhongBan(PhongBan phongBan) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();

            em.persist(phongBan);

            em.getTransaction().commit();

            phongBan = timKiemBangMaPhongBan(phongBan.getMaPhongBan());
            return phongBan;
        }
    }

    @Override
    public boolean capNhatSoLuongNhanVienBangMaPhongBan(String maPhongBan, int soLuong) {
        Connection connect = getConnection();
        PreparedStatement state = null;
        int status = 0;
        if (connect != null) {
            try {
                StringBuilder query = new StringBuilder("UPDATE PhongBan SET SoLuongNhanVien = " + soLuong);
                query.append(" WHERE MaPhongBan = ?");
                state = connect.prepareStatement(query.toString());
                state.setString(1, maPhongBan);

                status = state.executeUpdate();

                if (status > 0)
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (state != null) {
                    try {
                        state.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String timMaPhongbanCuoiCung() {
        try (EntityManager em = getEntityManager()) {
            String query = "SELECT pb FROM PhongBan pb ORDER BY pb.maPhongBan DESC";
            List<PhongBan> phongBans = em.createQuery(query, PhongBan.class).setMaxResults(1).getResultList();
            return phongBans.isEmpty() ? null : phongBans.get(0).getMaPhongBan();
        }
    }

    @Override
    public List<PhongBan> timKiemTatCaPhongBanDangHoatDong() {
        Connection connect = getConnection();
        Statement state = null;
        ResultSet rs = null;
        List<PhongBan> dsPhongBan = new ArrayList<PhongBan>();
        if (connect != null) {
            try {
                String query = "SELECT * FROM PhongBan WHERE TrangThai = 1";
                state = connect.createStatement();
                rs = state.executeQuery(query);

                while (rs.next()) {
                    String maPhongBan = rs.getString("MaPhongBan");
                    String tenPhongBan = rs.getString("TenPhongBan");
                    int soLuongNhanVien = rs.getInt("SoLuongNhanVien");
                    boolean trangThai = rs.getBoolean("TrangThai");

                    PhongBan pb = new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien, trangThai);
                    dsPhongBan.add(pb);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (state != null) {
                    try {
                        state.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dsPhongBan;
    }
}
