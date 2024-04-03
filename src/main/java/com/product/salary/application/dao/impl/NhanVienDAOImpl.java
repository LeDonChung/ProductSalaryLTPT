package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.entity.TrinhDo;
import com.product.salary.application.dao.NhanVienDAO;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAOImpl extends AbstractDAO implements NhanVienDAO, Serializable {

    @Override
    public List<NhanVien> timKiemTatCaNhanVien() {
        try (var em = getEntityManager()) {
            return em.createQuery("SELECT nv FROM NhanVien nv", NhanVien.class).getResultList();
        }
    }

    @Override
    public NhanVien capNhatNhanVien(NhanVien nhanVien) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();
            em.merge(nhanVien);
            em.getTransaction().commit();
            return nhanVien;
        }
    }

    @Override
    public boolean capNhatTrangThaiNghiLamCuaNhanVien(String maNhanVien) {
        try (var em = getEntityManager()) {
            NhanVien nhanVien = em.find(NhanVien.class, maNhanVien);
            if (nhanVien != null) {
                em.getTransaction().begin();
                nhanVien.setTrangThai(!nhanVien.isTrangThai());
                em.getTransaction().commit();
                return true;
            }
            return false;
        }
    }

    @Override
    public List<NhanVien> timKiemNhanVien(NhanVien nhanVien) {
        try (var em = getEntityManager()){
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<NhanVien> criteriaQuery = criteriaBuilder.createQuery(NhanVien.class);
            Root<NhanVien> nhanVienRoot = criteriaQuery.from(NhanVien.class);
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isBlank(nhanVien.getMaNhanVien())) {
                predicates.add(criteriaBuilder.like(nhanVienRoot.get("maNhanVien"), "%" + nhanVien.getMaNhanVien() + "%"));
            }
            if (!StringUtils.isBlank(nhanVien.getHoTen())) {
                predicates.add(criteriaBuilder.like(nhanVienRoot.get("hoTen"), "%" + nhanVien.getHoTen() + "%"));
            }
            if (!StringUtils.isBlank(nhanVien.getCccd())) {
                predicates.add(criteriaBuilder.like(nhanVienRoot.get("cccd"), "%" + nhanVien.getCccd() + "%"));
            }
            if (!StringUtils.isBlank(nhanVien.getEmail())) {
                predicates.add(criteriaBuilder.like(nhanVienRoot.get("email"), "%" + nhanVien.getEmail() + "%"));
            }
            if (!StringUtils.isBlank(nhanVien.getDiaChi())) {
                predicates.add(criteriaBuilder.like(nhanVienRoot.get("diaChi"), "%" + nhanVien.getDiaChi() + "%"));
            }
            if (!StringUtils.isBlank(nhanVien.getSoDienThoai())) {
                predicates.add(criteriaBuilder.like(nhanVienRoot.get("soDienThoai"), "%" + nhanVien.getSoDienThoai() + "%"));
            }
            if (nhanVien.getGioiTinh() != null) {
                predicates.add(criteriaBuilder.equal(nhanVienRoot.get("gioiTinh"), nhanVien.getGioiTinh()));
            }
            if (nhanVien.getNgaySinh() != null) {
                predicates.add(criteriaBuilder.equal(nhanVienRoot.get("ngaySinh"), nhanVien.getNgaySinh()));
            }
            if (nhanVien.getNgayVaoLam() != null) {
                predicates.add(criteriaBuilder.equal(nhanVienRoot.get("ngayVaoLam"), nhanVien.getNgayVaoLam()));
            }
            if (nhanVien.getLuongCoSo() != 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(nhanVienRoot.get("luongCoSo"), nhanVien.getLuongCoSo()));
            }
            if (nhanVien.getTroCap() != 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(nhanVienRoot.get("troCap"), nhanVien.getTroCap()));
            }
            if (nhanVien.getHeSoLuong() != 0) {
                predicates.add(criteriaBuilder.equal(nhanVienRoot.get("heSoLuong"), nhanVien.getHeSoLuong()));
            }
            if (nhanVien.isTrangThai() != null) {
                predicates.add(criteriaBuilder.equal(nhanVienRoot.get("trangThai"), nhanVien.isTrangThai()));
            }
            if (nhanVien.getPhongBan() != null) {
                if (!nhanVien.getPhongBan().getMaPhongBan().equals("xxxx")) {
                    predicates.add(criteriaBuilder.equal(nhanVienRoot.get("phongBan").get("maPhongBan"), nhanVien.getPhongBan().getMaPhongBan()));
                }
            }
            if (nhanVien.getChucVu() != null) {
                if (!nhanVien.getChucVu().getMaChucVu().equals("xxxx")) {
                    predicates.add(criteriaBuilder.equal(nhanVienRoot.get("chucVu").get("maChucVu"), nhanVien.getChucVu().getMaChucVu()));
                }
            }
            if (nhanVien.getTrinhDo() != null) {
                if (!nhanVien.getTrinhDo().getMaTrinhDo().equals("xxxx")) {
                    predicates.add(criteriaBuilder.equal(nhanVienRoot.get("trinhDo").get("maTrinhDo"), nhanVien.getTrinhDo().getMaTrinhDo()));
                }
            }
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            TypedQuery<NhanVien> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    private String getConditions(NhanVien search) {
        // Câu truy vấn tìm kiếm
        StringBuilder query = new StringBuilder("");

        // Danh sách cá điều kiện truy vấn
        List<String> conditions = new ArrayList<String>();

        // Mã nhân viên
        conditions.add(!StringUtils.isBlank(search.getMaNhanVien())
                ? String.format("NV.MaNhanVien LIKE N'%%%s%%'", search.getMaNhanVien())
                : "");

        // Phòng Ban
        if (search.getPhongBan() != null) {
            if (!search.getPhongBan().getMaPhongBan().equals("xxxx")) {
                conditions.add(!StringUtils.isBlank(search.getPhongBan().getMaPhongBan())
                        ? String.format("NV.MaPhongBan = '%s'", search.getPhongBan().getMaPhongBan())
                        : "");
            }
        }

        // Họ tên
        conditions.add(
                !StringUtils.isBlank(search.getHoTen()) ? String.format("NV.HoTen LIKE N'%%%s%%'", search.getHoTen())
                        : "");

        // Số điện thoại
        conditions.add(!StringUtils.isBlank(search.getSoDienThoai())
                ? String.format("NV.SoDienThoai LIKE N'%%%s%%'", search.getSoDienThoai())
                : "");

        // Địa chỉ
        conditions.add(
                !StringUtils.isBlank(search.getDiaChi()) ? String.format("NV.DiaChi LIKE N'%%%s%%'", search.getDiaChi())
                        : "");

        // CCCD
        conditions
                .add(!StringUtils.isBlank(search.getCccd()) ? String.format("NV.Cccd LIKE N'%%%s%%'", search.getCccd())
                        : "");

        // Email
        conditions.add(
                !StringUtils.isBlank(search.getEmail()) ? String.format("NV.Email LIKE N'%%%s%%'", search.getEmail())
                        : "");

        // Trình độ
        if (search.getTrinhDo() != null) {
            if (!search.getTrinhDo().getMaTrinhDo().equals("xxxx")) {
                conditions.add(!StringUtils.isBlank(search.getTrinhDo().getMaTrinhDo())
                        ? String.format("NV.MaTrinhDo = '%s'", search.getTrinhDo().getMaTrinhDo())
                        : "");
            }
        }

        // Chức vụ
        if (search.getChucVu() != null) {
            if (!search.getChucVu().getMaChucVu().equals("xxxx")) {
                conditions.add(!StringUtils.isBlank(search.getChucVu().getMaChucVu())
                        ? String.format("NV.MaChucVu = '%s'", search.getChucVu().getMaChucVu())
                        : "");
            }
        }

        // Giới tính
        if (search.getGioiTinh() != null) {
            conditions.add(String.format("NV.GioiTinh = %d", search.getGioiTinh()));
        } else
            conditions.add("");

        // Ngày sinh
        if (search.getNgaySinh() != null) {
            conditions.add(String.format("NV.NgaySinh = '%s'", search.getNgaySinh()));
        } else {
            conditions.add("");
        }

        // Ngày vào làm
        if (search.getNgayVaoLam() != null) {
            conditions.add(String.format("NV.NgayVaoLam = '%s'", search.getNgayVaoLam()));
        } else {
            conditions.add("");
        }

        // Lương cơ sở
        if (search.getLuongCoSo() != 0) {
            conditions.add(String.format("NV.LuongCoSo >= %f", search.getLuongCoSo()));
        } else
            conditions.add("");

        // Trợ cấp
        if (search.getTroCap() != 0) {
            conditions.add(String.format("NV.TroCap >= %f", search.getTroCap()));
        } else
            conditions.add("");

        // Hệ số lương
        if (search.getHeSoLuong() != 0) {
            conditions.add(String.format("NV.HeSoLuong = %f", search.getHeSoLuong()));
        } else
            conditions.add("");

        // Trạng thái
        if (search.isTrangThai() != null) {
            conditions.add(search.isTrangThai() ? "NV.TrangThai = 1" : "NV.TrangThai = 0");
        } else
            conditions.add("");

        // Xóa các điều kiện rỗng
        conditions.removeIf((v) -> v.equals(""));

        // Thêm AND vào giữa các điều kiện
        if (!conditions.isEmpty()) {
            String conditionValue = StringUtils.join(conditions, " AND ");
            query.append(String.format(" WHERE %s", conditionValue));
        }

        return query.toString();
    }

    @Override
    public NhanVien timKiemBangMaNhanVien(String maNhanVien) {
        try (var em = getEntityManager()) {
            return em.find(NhanVien.class, maNhanVien);
        }
    }

    @Override
    public NhanVien themNhanVien(NhanVien nhanVien) {
        try (var em = getEntityManager()) {
            em.getTransaction().begin();
            em.persist(nhanVien);
            em.getTransaction().commit();
            nhanVien = timKiemBangMaNhanVien(nhanVien.getMaNhanVien());
            return nhanVien;
        }
    }

    @Override
    public List<NhanVien> timKiemNhanVienBangMaPhongBan(String maPhongBan) {
        try (var em = getEntityManager()) {
            String query = "SELECT nv FROM NhanVien nv WHERE nv.phongBan.maPhongBan = :maPhongBan";
            return em.createQuery(query, NhanVien.class).setParameter("maPhongBan", maPhongBan).getResultList();
        }
    }

    @Override
    public String layMaNhanVienCuoiCungCuaNam(int nam) {
        try (var em = getEntityManager()) {
            String query = "SELECT nv FROM NhanVien nv WHERE year(nv.ngayVaoLam) = :nam order by nv.maNhanVien DESC ";
            List<NhanVien> nhanViens = em.createQuery(query, NhanVien.class).setParameter("nam", nam)
                    .setMaxResults(1).getResultList();
            return nhanViens.isEmpty() ? null : nhanViens.get(0).getMaNhanVien();
        }
    }

    @Override
    public int tongSoLuongNhanVien() {
        try (var em = getEntityManager()) {
            String query = "SELECT COUNT(nv) FROM NhanVien nv";
            return em.createQuery(query, Long.class).getSingleResult().intValue();
        }
    }

    @Override
    public NhanVien timKiemBangMaNhanVienVaCccd(String maNhanVien, String cccd) {
        try (var em = getEntityManager()){
            Query query = em.createQuery("SELECT nv FROM NhanVien  nv WHERE nv.maNhanVien = :maNhanVien AND nv.cccd = :cccd", NhanVien.class);
            query.setParameter("maNhanVien", maNhanVien);
            query.setParameter("cccd", cccd);
            return (NhanVien) query.getResultList().stream().findFirst().get();
        }
    }
}
