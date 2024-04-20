package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ChamCongNhanVien")
@NamedQueries({
        @NamedQuery(name = "ChamCongNhanVien.timKiemTatCaChamCongNhanVienTheoCaVaNgay",
                query = "SELECT ccnv FROM ChamCongNhanVien ccnv WHERE ccnv.ngayChamCong = :ngayChamCong AND ccnv.caLam.maCa = :maCa"),
        @NamedQuery(name = "ChamCongNhanVien.timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong",
                query = "SELECT nv FROM NhanVien nv WHERE nv.maNhanVien NOT IN " +
                        "(SELECT ccnv.nhanVien.maNhanVien FROM ChamCongNhanVien ccnv " +
                        " WHERE ccnv.ngayChamCong = :ngayChamCong AND ccnv.caLam.maCa = :maCa)"),
        @NamedQuery(name = "ChamCongNhanVien.timKiemMaChamCongNhanVienCuoiCungTheoNgayVaCaLam",
                query = "SELECT ccnv FROM ChamCongNhanVien ccnv WHERE ccnv.ngayChamCong = :ngayChamCong AND ccnv.caLam.maCa = :maCa ORDER BY ccnv.maChamCong DESC"),
        @NamedQuery(name = "ChamCongNhanVien.timKiemDanhSachNhanVienDiLamBangThangVaNam",
                query = "SELECT nv FROM NhanVien nv WHERE nv.maNhanVien IN " +
                        "(SELECT ccnv.nhanVien.maNhanVien FROM ChamCongNhanVien ccnv WHERE MONTH(ccnv.ngayChamCong) = :thang AND YEAR(ccnv.ngayChamCong) = :nam)"),
        @NamedQuery(name = "ChamCongNhanVien.capNhatMaLuongBangMaNhanVienVaThangVaNam",
                query = "UPDATE ChamCongNhanVien ccnv SET ccnv.luongNhanVien.maLuong = :maLuong " +
                        "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien AND MONTH(ccnv.ngayChamCong) = :thang AND YEAR(ccnv.ngayChamCong) = :nam"),
        @NamedQuery(name = "ChamCongNhanVien.timKiemChamCongNhanVienTheoMaNhanVienVaThangVaNam",
                query = "SELECT ccnv FROM ChamCongNhanVien ccnv " +
                        "WHERE ccnv.nhanVien.maNhanVien = :maNhanVien " +
                        "AND MONTH(ccnv.ngayChamCong) = :thang " +
                        "AND YEAR(ccnv.ngayChamCong) = :nam"),

})
public class ChamCongNhanVien implements Serializable {

    @Id
    @Column(name = "MaChamCong", length = 15)
    private String maChamCong;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaCa")
    private CaLam caLam;

    @Column(name = "NgayChamCong", columnDefinition = "DATETIME")
    private LocalDate ngayChamCong;

    @Column(name = "TrangThai", nullable = false)
    private int trangThai;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MaLuong")
    private LuongNhanVien luongNhanVien;

    public ChamCongNhanVien(String maChamCong, NhanVien nhanVien, CaLam caLam, LocalDate ngayChamCong, int trangThai) throws Exception {
        setMaChamCong(maChamCong);
        setNhanVien(nhanVien);
        setCaLam(caLam);
        setNgayChamCong(ngayChamCong);
        setTrangThai(trangThai);
    }

    /**
     * @param ngayChamCong the ngayChamCong to set
     * @throws Exception
     */
    public void setNgayChamCong(LocalDate ngayChamCong) throws Exception {
        if (ngayChamCong.isAfter(LocalDate.now()))
            throw new Exception("Ngày chấm công phải trước hoặc bằng ngày hiện tại");
        else
            this.ngayChamCong = ngayChamCong;
    }

    /**
     * @param trangThai the trangThai to set
     * @throws Exception
     */
    public void setTrangThai(int trangThai) throws Exception {
        if (trangThai != 0 && trangThai != 1 && trangThai != 2)
            throw new Exception("Trạng thái phải thuộc 0 hoặc 1 hoặc 2");
        else
            this.trangThai = trangThai;
    }
}
