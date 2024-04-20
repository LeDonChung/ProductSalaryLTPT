package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ChamCongCongNhan")
@NamedQueries({
        @NamedQuery(name = "ChamCongCongNhan.timDanhSachCongNhanDiLamBangThangVaNam",
                query = "SELECT cn FROM CongNhan cn WHERE cn.maCongNhan IN " +
                        "(" +
                        "SELECT cn.maCongNhan FROM ChamCongCongNhan cc " +
                        "JOIN PhanCongCongNhan pc ON cc.phanCongCongNhan.maPhanCong = pc.maPhanCong " +
                        "JOIN CongNhan cn ON pc.congNhan.maCongNhan = cn.maCongNhan " +
                        "WHERE MONTH(cc.ngayChamCong) = :thang AND YEAR(cc.ngayChamCong) = :nam " +
                        ")"),
        @NamedQuery(name = "ChamCongCongNhan.capNhatChamCongCongNhanBangMaCongNhanVaThangVaNam",
                query = "UPDATE ChamCongCongNhan cc SET cc.luongCongNhan.maLuong = :maLuong " +
                        "WHERE cc.maChamCong IN " +
                        "(SELECT cc.maChamCong FROM ChamCongCongNhan cc " +
                        "JOIN PhanCongCongNhan pc ON cc.phanCongCongNhan.maPhanCong = pc.maPhanCong " +
                        "WHERE YEAR(cc.ngayChamCong) = :nam AND MONTH(cc.ngayChamCong) = :thang AND pc.congNhan.maCongNhan = :maCongNhan)"),
        @NamedQuery(name = "ChamCongCongNhan.timTatCaChamCongCongNhan",
                query = "SELECT cc FROM ChamCongCongNhan cc WHERE cc.ngayChamCong = :ngayChamCong"),
		@NamedQuery(name = "ChamCongCongNhan.timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong",
                query = "SELECT cn FROM CongNhan cn " +
                        "WHERE cn.trangThai = true AND cn.maCongNhan NOT IN " +
                        "(SELECT pc.congNhan.maCongNhan FROM ChamCongCongNhan cc JOIN PhanCongCongNhan pc ON cc.phanCongCongNhan.maPhanCong = pc.maPhanCong " +
                        "WHERE cc.ngayChamCong = :ngayChamCong AND cc.caLam.maCa = :caLam)"),
        @NamedQuery(name = "ChamCongCongNhan.timKiemMaChamCongCongNhanCuoiCungTheoNgayVaCaLam",
                query = "SELECT cc.maChamCong FROM ChamCongCongNhan cc WHERE cc.ngayChamCong = :ngayChamCong AND cc.caLam.maCa = :caLam ORDER BY cc.maChamCong DESC")
})
public class ChamCongCongNhan implements Serializable {

    @Id
    @Column(name = "MaChamCong", length = 15)
    private String maChamCong;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaPhanCong")
    private PhanCongCongNhan phanCongCongNhan;

    @Column(name = "SoLuongHoanThanh", nullable = false)
    private int soLuongHoanThanh;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaCa")
    private CaLam caLam;

    @Column(name = "NgayChamCong", columnDefinition = "DATETIME")
    private LocalDate ngayChamCong;

    @Column(name = "TrangThai", nullable = false)
    private int trangThai;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MaLuong")
    private LuongCongNhan luongCongNhan;

    public ChamCongCongNhan(String maChamCong, PhanCongCongNhan phanCongCongNhan, int soLuongHoanThanh, CaLam caLam,
                            LocalDate ngayChamCong, int trangThai, LuongCongNhan luongCongNhan) throws Exception {
        setMaChamCong(maChamCong);
        setPhanCongCongNhan(phanCongCongNhan);
        setSoLuongHoanThanh(soLuongHoanThanh);
        setCaLam(caLam);
        setNgayChamCong(ngayChamCong);
        setTrangThai(trangThai);
        setLuongCongNhan(luongCongNhan);
    }

    public void setSoLuongHoanThanh(int soLuongHoanThanh) throws Exception {
        if (soLuongHoanThanh < 0) {
            throw new Exception("Số lượng hoàn thành >= 0");
        } else {
            this.soLuongHoanThanh = soLuongHoanThanh;
        }
    }

    public void setNgayChamCong(LocalDate ngayChamCong) throws Exception {
        if (ngayChamCong.isAfter(LocalDate.now())) {
            throw new Exception("Ngày chấm công phải <= ngày hiện tại");
        } else {
            this.ngayChamCong = ngayChamCong;
        }
    }
}
