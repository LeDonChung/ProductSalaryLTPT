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

    @Column(name = "NgayChamCong", columnDefinition = "DATETIME CHECK (NgayChamCong <= GETDATE())")
    private LocalDate ngayChamCong;

    @Column(name = "TrangThai", columnDefinition = "INT NOT NULL DEFAULT 1")
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
