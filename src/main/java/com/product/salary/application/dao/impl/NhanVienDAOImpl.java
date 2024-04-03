package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.entity.TrinhDo;
import com.product.salary.application.dao.NhanVienDAO;
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
        Connection connect = getConnection();
        PreparedStatement state = null;
        int status = 0;
        if (connect != null) {
            try {
                StringBuilder query = new StringBuilder(
                        "UPDATE NhanVien SET HoTen = ?, Email = ?, DiaChi = ?, MaChucVu = ?, Cccd = ?, SoDienThoai = ?, NgaySinh = ?, MaPhongBan = ?, NgayVaoLam = ?, LuongCoSo = ?, HeSoLuong = ?, TroCap = ?, HinhAnh = ?, MaTrinhDo = ?, TrangThai = ?");
                query.append(" WHERE MaNhanVien = ?");
                state = connect.prepareStatement(query.toString());

                state.setString(1, nhanVien.getHoTen());
                state.setString(2, nhanVien.getEmail());
                state.setString(3, nhanVien.getDiaChi());
                state.setString(4, nhanVien.getChucVu().getMaChucVu());
                state.setString(5, nhanVien.getCccd());
                state.setString(6, nhanVien.getSoDienThoai());
                state.setDate(7, Date.valueOf(nhanVien.getNgaySinh()));
                state.setString(8, nhanVien.getPhongBan().getMaPhongBan());
                state.setDate(9, Date.valueOf(nhanVien.getNgayVaoLam()));
                state.setDouble(10, nhanVien.getLuongCoSo());
                state.setDouble(11, nhanVien.getHeSoLuong());
                state.setDouble(12, nhanVien.getTroCap());
                state.setBytes(13, nhanVien.getHinhAnh());
                state.setString(14, nhanVien.getTrinhDo().getMaTrinhDo());
                state.setBoolean(15, nhanVien.isTrangThai());
                state.setString(16, nhanVien.getMaNhanVien());

                status = state.executeUpdate();
                if (status > 0)
                    return nhanVien;

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
        return null;
    }

    @Override
    public boolean capNhatTrangThaiNghiLamCuaNhanVien(String maNhanVien) {
        Connection connect = getConnection();
        PreparedStatement state = null;
        int status = 0;
        if (connect != null) {
            try {
                StringBuilder query = new StringBuilder("UPDATE NhanVien SET TrangThai = 0");
                query.append(" WHERE MaNhanVien = ?");
                state = connect.prepareStatement(query.toString());
                state.setString(1, maNhanVien);
                status = state.executeUpdate();

                if (status > 0)
                    return true;
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

        return false;
    }

    @Override
    public List<NhanVien> timKiemNhanVien(NhanVien nhanVien) {
        List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        Connection connect = getConnection();
        Statement state = null;
        ResultSet rs = null;
        if (connect != null) {
            try {
                String conditions = getConditions(nhanVien);
                StringBuilder query = new StringBuilder(
                        "SELECT NV.MaNhanVien, NV.HoTen, NV.Email, NV.DiaChi, NV.GioiTinh, NV.MaChucVu, ");
                query.append("CV.TenChucVu, NV.Cccd, NV.SoDienThoai, Nv.NgaySinh, NV.MaPhongBan, PB.TenPhongBan, ");
                query.append(
                        "NV.NgayVaoLam, NV.LuongCoSo, NV.HeSoLuong, NV.TroCap, NV.MaTrinhDo, TD.TenTrinhDo, NV.TrangThai, NV.HinhAnh ");
                query.append("FROM NhanVien AS NV\n");
                query.append("JOIN ChucVu AS CV ON NV.MaChucVu = CV.MaChucVu\n");
                query.append("JOIN TrinhDo AS TD ON NV.MaTrinhDo = TD.MaTrinhDo\n");
                query.append("JOIN PhongBan AS PB ON NV.MaPhongBan = PB.MaPhongBan");
                query.append(conditions);
                state = connect.createStatement();
                rs = state.executeQuery(query.toString());

                while (rs.next()) {
                    String maNhanVien = rs.getString("MaNhanVien");
                    String hoTen = rs.getString("HoTen");
                    String email = rs.getString("Email");
                    String diaChi = rs.getString("DiaChi");
                    int gioiTinh = rs.getInt("GioiTinh");

                    String maChucVu = rs.getString("MaChucVu");
                    String chucVu = rs.getString("TenChucVu");
                    ChucVu cv = new ChucVu(maChucVu, chucVu);

                    String cccd = rs.getString("Cccd");
                    String dienThoai = rs.getString("SoDienThoai");
                    LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();

                    String maPhongBan = rs.getString("MaPhongBan");
                    String phongBan = rs.getString("TenPhongBan");
                    PhongBan pb = new PhongBan(maPhongBan, phongBan, 0, true);

                    LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
                    double luongCoSo = rs.getDouble("LuongCoSo");
                    double heSoLuong = rs.getDouble("HeSoLuong");
                    double troCap = rs.getDouble("TroCap");

                    String maTrinhDo = rs.getString("MaTrinhDo");
                    String trinhDo = rs.getString("TenTrinhDo");
                    TrinhDo td = new TrinhDo(maTrinhDo, trinhDo);

                    byte[] hinhAnh = rs.getBytes("HinhAnh");
                    boolean trangThai = rs.getBoolean("TrangThai");

                    String heSoLuongFm = String.format("%.2f", heSoLuong);
                    NhanVien nv = new NhanVien(maNhanVien, hoTen, email, diaChi, gioiTinh, cv, cccd, dienThoai,
                            ngaySinh, pb, ngayVaoLam, luongCoSo, Double.parseDouble(heSoLuongFm), troCap, td, hinhAnh,
                            trangThai);
                    dsNhanVien.add(nv);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dsNhanVien;
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
        Connection connect = getConnection();
        Statement state = null;
        ResultSet rs = null;
        List<NhanVien> dsNhanVien = new ArrayList<>();
        if (connect != null) {
            try {
                StringBuilder query = new StringBuilder(
                        "SELECT NV.MaNhanVien, NV.HoTen, NV.Email, NV.DiaChi, NV.GioiTinh, NV.MaChucVu, ");
                query.append("CV.TenChucVu, NV.Cccd, NV.SoDienThoai, Nv.NgaySinh, NV.MaPhongBan, PB.TenPhongBan, ");
                query.append(
                        "NV.NgayVaoLam, NV.LuongCoSo, NV.HeSoLuong, NV.TroCap, NV.MaTrinhDo, TD.TenTrinhDo, NV.TrangThai, NV.HinhAnh ");
                query.append("FROM NhanVien AS NV\n");
                query.append("JOIN ChucVu AS CV ON NV.MaChucVu = CV.MaChucVu\n");
                query.append("JOIN TrinhDo AS TD ON NV.MaTrinhDo = TD.MaTrinhDo\n");
                query.append("JOIN PhongBan AS PB ON NV.MaPhongBan = PB.MaPhongBan\n");
                query.append("WHERE NV.TrangThai = 1 AND NV.MaPhongBan = '" + maPhongBan + "'");

                state = connect.createStatement();
                rs = state.executeQuery(query.toString());
                while (rs.next()) {
                    String maNhanVien = rs.getString("MaNhanVien");
                    String hoTen = rs.getString("HoTen");
                    String email = rs.getString("Email");
                    String diaChi = rs.getString("DiaChi");
                    int gioiTinh = rs.getInt("GioiTinh");

                    String maChucVu = rs.getString("MaChucVu");
                    String chucVu = rs.getString("TenChucVu");
                    ChucVu cv = new ChucVu(maChucVu, chucVu);

                    String cccd = rs.getString("Cccd");
                    String dienThoai = rs.getString("SoDienThoai");
                    LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();

                    String phongBan = rs.getString("TenPhongBan");
                    PhongBan pb = new PhongBan(maPhongBan, phongBan, 0, true);

                    LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
                    double luongCoSo = rs.getDouble("LuongCoSo");
                    double heSoLuong = rs.getDouble("HeSoLuong");
                    double troCap = rs.getDouble("TroCap");

                    String maTrinhDo = rs.getString("MaTrinhDo");
                    String trinhDo = rs.getString("TenTrinhDo");
                    TrinhDo td = new TrinhDo(maTrinhDo, trinhDo);

                    byte[] hinhAnh = rs.getBytes("HinhAnh");
                    boolean trangThai = rs.getBoolean("TrangThai");

                    String heSoLuongFm = String.format("%.2f", heSoLuong);
                    NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, email, diaChi, gioiTinh, cv, cccd, dienThoai,
                            ngaySinh, pb, ngayVaoLam, luongCoSo, Double.parseDouble(heSoLuongFm), troCap, td, hinhAnh,
                            trangThai);
                    dsNhanVien.add(nhanVien);
                }
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
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dsNhanVien;
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
        Connection conn = getConnection();
        Statement statement = null;
        ResultSet rs = null;
        int soLuong = 0;
        if (conn != null) {
            try {
                String query = "SELECT COUNT(*) FROM NhanVien";
                statement = conn.createStatement();
                rs = statement.executeQuery(query);

                while (rs.next()) {
                    soLuong = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return soLuong;
    }

    @Override
    public NhanVien timKiemBangMaNhanVienVaCccd(String maNhanVien, String cccd) {
        Connection connect = getConnection();
        Statement state = null;
        ResultSet rs = null;
        NhanVien nhanVien = null;
        if (connect != null) {
            try {
                StringBuilder query = new StringBuilder(
                        "SELECT NV.MaNhanVien, NV.HoTen, NV.Email, NV.DiaChi, NV.GioiTinh, NV.MaChucVu, ");
                query.append(
                        "CV.TenChucVu, NV.Cccd, NV.SoDienThoai, Nv.NgaySinh, NV.MaPhongBan, PB.TenPhongBan, PB.SoLuongNhanVien, ");
                query.append(
                        "NV.NgayVaoLam, NV.LuongCoSo, NV.HeSoLuong, NV.TroCap, NV.MaTrinhDo, TD.TenTrinhDo, NV.TrangThai, NV.HinhAnh ");
                query.append("FROM NhanVien AS NV\n");
                query.append("JOIN ChucVu AS CV ON NV.MaChucVu = CV.MaChucVu\n");
                query.append("JOIN TrinhDo AS TD ON NV.MaTrinhDo = TD.MaTrinhDo\n");
                query.append("JOIN PhongBan AS PB ON NV.MaPhongBan = PB.MaPhongBan \n");
                query.append("WHERE NV.MaNhanVien = '" + maNhanVien + "' \n");
                query.append("AND NV.Cccd = '" + cccd + "'");

                state = connect.createStatement();
                rs = state.executeQuery(query.toString());
                while (rs.next()) {
                    String maNV = rs.getString("MaNhanVien");
                    String hoTen = rs.getString("HoTen");
                    String email = rs.getString("Email");
                    String diaChi = rs.getString("DiaChi");
                    int gioiTinh = rs.getInt("GioiTinh");

                    String maChucVu = rs.getString("MaChucVu");
                    String chucVu = rs.getString("TenChucVu");
                    ChucVu cv = new ChucVu(maChucVu, chucVu);

                    String dienThoai = rs.getString("SoDienThoai");
                    LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();

                    String maPhongBan = rs.getString("MaPhongBan");
                    String phongBan = rs.getString("TenPhongBan");
                    int soLuongNhanVien = rs.getInt("SoLuongNhanVien");
                    boolean trangThaiS = rs.getBoolean("TrangThai");
                    PhongBan pb = new PhongBan(maPhongBan, phongBan, soLuongNhanVien, trangThaiS);

                    LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
                    double luongCoSo = rs.getDouble("LuongCoSo");
                    double heSoLuong = rs.getDouble("HeSoLuong");
                    double troCap = rs.getDouble("TroCap");

                    String maTrinhDo = rs.getString("MaTrinhDo");
                    String trinhDo = rs.getString("TenTrinhDo");
                    TrinhDo td = new TrinhDo(maTrinhDo, trinhDo);

                    byte[] hinhAnh = rs.getBytes("HinhAnh");
                    boolean trangThai = rs.getBoolean("TrangThai");
                    nhanVien = new NhanVien(maNV, hoTen, email, diaChi, gioiTinh, cv, cccd, dienThoai, ngaySinh, pb,
                            ngayVaoLam, luongCoSo, heSoLuong, troCap, td, hinhAnh, trangThai);
                }
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
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return nhanVien;
    }
}
