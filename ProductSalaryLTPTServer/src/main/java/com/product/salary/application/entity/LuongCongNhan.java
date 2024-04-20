package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "LuongCongNhan")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
		@NamedQuery(name = "LuongCongNhan.tinhTongTienCongNhanTheoMaCongNhanVaThangVaNam",
				query = "SELECT SUM(cdsp.giaCongDoan * cc.soLuongHoanThanh) " +
						"FROM ChamCongCongNhan cc " +
						"JOIN PhanCongCongNhan pc ON cc.phanCongCongNhan.maPhanCong = pc.maPhanCong " +
						"JOIN CongDoanSanPham cdsp ON pc.congDoanSanPham.maCongDoan = cdsp.maCongDoan " +
						"WHERE MONTH(cc.ngayChamCong) = :thang AND YEAR(cc.ngayChamCong) = :nam AND pc.congNhan.maCongNhan = :maCongNhan"),
		@NamedQuery(name = "LuongCongNhan.timTatCaLuongCongNhanTheoThangVaNam", query = "SELECT lcn FROM LuongCongNhan lcn WHERE lcn.thang = :thang AND lcn.nam = :nam"),
		@NamedQuery(name = "LuongCongNhan.timTatCaChiTietLuongTheoThangVaNam",
				query = "SELECT cc.maChamCong, cc.phanCongCongNhan.congNhan.maCongNhan, cc.phanCongCongNhan.congNhan.hoTen, " +
						"cdsp.sanPham.tenSanPham, cdsp.tenCongDoan, cc.ngayChamCong, cc.caLam.tenCa, cc.soLuongHoanThanh, " +
						"(cc.soLuongHoanThanh * cdsp.giaCongDoan) AS tongTien, cc.trangThai " +
						"FROM ChamCongCongNhan cc " +
						"JOIN PhanCongCongNhan pc ON cc.phanCongCongNhan.maPhanCong = pc.maPhanCong " +
						"JOIN CongDoanSanPham cdsp ON pc.congDoanSanPham.maCongDoan = cdsp.maCongDoan " +
						"WHERE MONTH(cc.ngayChamCong) = :thang AND YEAR(cc.ngayChamCong) = :nam AND pc.congNhan.maCongNhan = :maCongNhan"),
		@NamedQuery(name = "LuongCongNhan.thongKeLuongCongNhanTheoNam",
				query = "SELECT lcn.nam, SUM(lcn.luong) " +
						"FROM LuongCongNhan lcn " +
						"GROUP BY lcn.nam"),
		@NamedQuery(name = "LuongCongNhan.thongKeLuongCongNhanTheoThang",
				query = "SELECT lcn.thang, SUM(lcn.luong) " +
						"FROM LuongCongNhan lcn " +
						"WHERE lcn.nam = :nam " +
						"GROUP BY lcn.thang ORDER BY lcn.thang"),
})
public class LuongCongNhan implements Serializable {
	@Id
	@Column(name = "MaLuong", length = 16)
	private String maLuong;

	@Column(name = "Thang")
	private int thang;

	@Column(name = "Nam")
	private int nam;

	@Column(name = "NgayTinhLuong", columnDefinition = "DATETIME NOT NULL")
	private LocalDate ngayTinhLuong;

	@Column(name = "Luong", columnDefinition = "FLOAT NOT NULL")
	private double luong;

	@Column(name = "LuongThuong", columnDefinition = "FLOAT NOT NULL")
	private double luongThuong;


	@OneToMany(mappedBy = "luongCongNhan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ToString.Exclude
	private transient Set<ChamCongCongNhan> chamCongCongNhans;

	public LuongCongNhan(String maLuong, int thang, int nam, LocalDate ngayTinhLuong, double luong, double luongThuong)
			throws Exception {
		setMaLuong(maLuong);
		setThang(thang);
		setNam(nam);
		setNgayTinhLuong(ngayTinhLuong);
		setLuong(luong);
		setLuongThuong(luongThuong);
	}

	public void setThang(int thang) throws Exception {
		if (thang > 12 || thang < 1) {
			throw new Exception(" 1 <= Tháng <= 12");
		} else {
			this.thang = thang;
		}
	}

	public void setNam(int nam) throws Exception {
		if (nam > LocalDate.now().getYear() || nam < 1990) {
			throw new Exception("Năm phải <= năm hiện tại");
		} else {
			this.nam = nam;
		}
	}

	public void setNgayTinhLuong(LocalDate ngayTinhLuong) throws Exception {
		if (ngayTinhLuong.isAfter(LocalDate.now())) {
			throw new Exception("Ngày tính lương <= ngày hiện tại");
		} else {
			this.ngayTinhLuong = ngayTinhLuong;
		}
	}

	public void setLuong(double luong) throws Exception {
		if (luong < 0) {
			throw new Exception("Lương phải > 0");
		} else {
			this.luong = luong;
		}
	}

	public void setLuongThuong(double luongThuong) throws Exception {
		if (luongThuong < 0) {
			throw new Exception("Lương thưởng phải > 0");
		} else {
			this.luongThuong = luongThuong;
		}
	}


}
