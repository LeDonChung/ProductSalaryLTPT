package com.product.salary.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "NhanVien")
@ToString
@NoArgsConstructor
@Setter
@Getter
public class NhanVien implements Serializable {
	@Id
	@Column(name = "MaNhanVien", length = 15)
	private String maNhanVien;

	@Column(name = "HoTen", columnDefinition = "NVARCHAR(70) NOT NULL")
	private String hoTen;

	@Column(name = "Email", length = 70, nullable = false)
	private String email;

	@Column(name = "DiaChi", columnDefinition = "NVARCHAR(100) NOT NULL")
	private String diaChi;

	@Column(name = "GioiTinh", nullable = false)
	private Integer gioiTinh;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaChucVu")
	private ChucVu chucVu;

	@Column(name = "CCCD", length = 12, unique = true)
	private String cccd;

	@Column(name = "SoDienThoai", length = 10, nullable = false)
	private String soDienThoai;

	@Column(name = "NgaySinh", columnDefinition = "DATETIME NOT NULL")
	private LocalDate ngaySinh;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaPhongBan")
	private PhongBan phongBan;

	@Column(name = "NgayVaoLam", columnDefinition = "DATETIME NOT NULL")
	private LocalDate ngayVaoLam;

	@Column(name = "LuongCoSo", columnDefinition = "REAL NOT NULL")
	private double luongCoSo;

	@Column(name = "HeSoLuong", columnDefinition = "REAL NOT NULL")
	private double heSoLuong;

	@Column(name = "TroCap", columnDefinition = "REAL NOT NULL")
	private double troCap;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MaTrinhDo")
	private TrinhDo trinhDo;

	@Column(name = "HinhAnh")
	private byte[] hinhAnh;

	@Column(name = "TrangThai", nullable = false)
	private Boolean trangThai;

	public NhanVien(String maNhanVien, String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public NhanVien(String cccd) {
		this.cccd = cccd;
	}

	public NhanVien(String maNhanVien, String hoTen, String email, String diaChi, String cccd, String soDienThoai,
                    PhongBan phongBan, TrinhDo trinhDo) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.email = email;
		this.diaChi = diaChi;
		this.cccd = cccd;
		this.soDienThoai = soDienThoai;
		this.phongBan = phongBan;
		this.trinhDo = trinhDo;
	}

	public NhanVien(String maNhanVien, String hoTen, String email, String diaChi, String cccd, String soDienThoai,
                    PhongBan phongBan, TrinhDo trinhDo, Boolean trangThai) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.email = email;
		this.diaChi = diaChi;
		this.cccd = cccd;
		this.soDienThoai = soDienThoai;
		this.phongBan = phongBan;
		this.trinhDo = trinhDo;
		this.trangThai = trangThai;
	}


	public NhanVien(String maNhanVien, String hoTen, String email, String diaChi, Integer gioiTinh, ChucVu chucVu,
                    String cccd, String soDienThoai, LocalDate ngaySinh, PhongBan phongBan, LocalDate ngayVaoLam,
                    double luongCoSo, double heSoLuong, double troCap, TrinhDo trinhDo, byte[] hinhAnh, Boolean trangThai) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.email = email;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.chucVu = chucVu;
		this.cccd = cccd;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.phongBan = phongBan;
		this.ngayVaoLam = ngayVaoLam;
		this.luongCoSo = luongCoSo;
		this.heSoLuong = heSoLuong;
		this.troCap = troCap;
		this.trinhDo = trinhDo;
		this.hinhAnh = hinhAnh;
		this.trangThai = trangThai;
	}



	public void setHoTen(String hoTen) throws Exception {
		if (hoTen.trim().equals(""))
			throw new Exception("Họ tên không được rỗng");
		else
			this.hoTen = hoTen;
	}


	public void setEmail(String email) throws Exception {
		if (email.trim().equals(""))
			throw new Exception("Email không được rỗng");
		else if (!email.trim().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
			throw new Exception("Email không đúng cú pháp");
		else
			this.email = email;
	}


	public void setDiaChi(String diaChi) throws Exception {
		if (diaChi.trim().equals(""))
			throw new Exception("Địa chỉ không được rỗng");
		else
			this.diaChi = diaChi;
	}


	public void setGioiTinh(Integer gioiTinh) {
		if (gioiTinh != 0 && gioiTinh != 1)
			this.gioiTinh = 0;
		else
			this.gioiTinh = gioiTinh;
	}




	public void setCccd(String cccd) throws Exception {
		if (cccd.trim().equals(""))
			throw new Exception("Căn cước công dân không được rỗng");
		else if (!cccd.matches("^0[0-9]{11}$"))
			throw new Exception("Căn cước công dân phải bắt đầu từ  và phải đủ 12 số");
		else
			this.cccd = cccd;
	}


	public void setSoDienThoai(String soDienThoai) throws Exception {
		if (soDienThoai.trim().length() != 10)
			throw new Exception("Số điện thoại phải đủ 10 chữ số");
		else if (!soDienThoai.trim().matches("^0[0-9]{9}$"))
			throw new Exception("Số điện thoại phải bắt đầu là 0");
		else
			this.soDienThoai = soDienThoai;
	}


	public void setNgaySinh(LocalDate ngaySinh) throws Exception {
		int tuoi = Period.between(ngaySinh, LocalDate.now()).getYears();
		if (tuoi < 18)
			throw new Exception(String.format("Nhân viên phải >= 18 tuổi. Tuổi hiện tại: %d", tuoi));
		else
			this.ngaySinh = ngaySinh;
	}

	public void setNgayVaoLam(LocalDate ngayVaoLam) throws Exception {
		if (ngayVaoLam.isAfter(LocalDate.now()))
			throw new Exception("Ngày vào làm phải trước hoặc bằng ngày hiện tại");
		else
			this.ngayVaoLam = ngayVaoLam;
	}


	public void setLuongCoSo(double luongCoSo) throws Exception {
		if (luongCoSo <= 0)
			throw new Exception("Lương cơ sở phải lớn hơn 0");
		else
			this.luongCoSo = luongCoSo;
	}


	public void setHeSoLuong(double heSoLuong) throws Exception {
		if (heSoLuong >= 1.86 && heSoLuong <= 4.6)
			this.heSoLuong = heSoLuong;
		else
			throw new Exception("Hệ số lượng phải thuộc 1 trong các hệ số: 1,86; 2,26; 2,66; 3,06; 3,46; 3,86; 4,06");
	}


	public void setTroCap(double troCap) throws Exception {
		if (troCap < 0) {
			troCap = 0;
			throw new Exception("Trợ cấp phải >= 0");
		}

		else
			this.troCap = troCap;
	}


	public void setTrinhDo(TrinhDo trinhDo) throws Exception {
		if (!trinhDo.getTenTrinhDo().equals("Tiến sĩ") && !trinhDo.getTenTrinhDo().equals("Thạc sĩ")
				&& !trinhDo.getTenTrinhDo().equals("Cử nhân") && !trinhDo.getTenTrinhDo().equals("Kỹ sư")
				&& !trinhDo.getTenTrinhDo().equals("Cao đẳng") && !trinhDo.getTenTrinhDo().equals("Sơ cấp")
				&& !trinhDo.getTenTrinhDo().equals("Trung cấp"))
			throw new Exception(
					"Trình độ phải thuộc 1 trong các cấp bậc: Tiến sĩ, Thạc sĩ, Cử nhân, Kỹ sư, Cao đẳng, Trung cấp, Sơ cấp");
		else
			this.trinhDo = trinhDo;
	}

	public Boolean isTrangThai() {
		return trangThai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cccd, maNhanVien, soDienThoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(cccd, other.cccd) && Objects.equals(maNhanVien, other.maNhanVien)
				&& Objects.equals(soDienThoai, other.soDienThoai);
	}
}
