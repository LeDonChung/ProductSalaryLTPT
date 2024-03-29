package com.product.salary.application.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Objects;

public class NhanVien implements Serializable {
	private String maNhanVien;
	private String hoTen;
	private String email;
	private String diaChi;
	private Integer gioiTinh;
	private ChucVu chucVu;
	private String cccd;
	private String soDienThoai;
	private LocalDate ngaySinh;
	private PhongBan phongBan;
	private LocalDate ngayVaoLam;
	private double luongCoSo;
	private double heSoLuong;
	private double troCap;
	private TrinhDo trinhDo;
	private byte[] hinhAnh;
	private Boolean trangThai;

	public NhanVien(String maNhanVien, String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public NhanVien(String cccd) {
		this.cccd = cccd;
	}

	public NhanVien() {
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
		this.trangThai = trangThai;
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

	public String getMaNhanVien() {
		return maNhanVien;
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

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) throws Exception {
		if (hoTen.trim().equals(""))
			throw new Exception("Họ tên không được rỗng");
		else
			this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if (email.trim().equals(""))
			throw new Exception("Email không được rỗng");
		else if (!email.trim().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
			throw new Exception("Email không đúng cú pháp");
		else
			this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) throws Exception {
		if (diaChi.trim().equals(""))
			throw new Exception("Địa chỉ không được rỗng");
		else
			this.diaChi = diaChi;
	}

	public Integer getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Integer gioiTinh) {
		if (gioiTinh != 0 && gioiTinh != 1)
			this.gioiTinh = 0;
		else
			this.gioiTinh = gioiTinh;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) throws Exception {
		if (cccd.trim().equals(""))
			throw new Exception("Căn cước công dân không được rỗng");
		else if (!cccd.matches("^0[0-9]{11}$"))
			throw new Exception("Căn cước công dân phải bắt đầu từ  và phải đủ 12 số");
		else
			this.cccd = cccd;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) throws Exception {
		if (soDienThoai.trim().length() != 10)
			throw new Exception("Số điện thoại phải đủ 10 chữ số");
		else if (!soDienThoai.trim().matches("^0[0-9]{9}$"))
			throw new Exception("Số điện thoại phải bắt đầu là 0");
		else
			this.soDienThoai = soDienThoai;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) throws Exception {
		int tuoi = Period.between(ngaySinh, LocalDate.now()).getYears();
		if (tuoi < 18)
			throw new Exception(String.format("Nhân viên phải >= 18 tuổi. Tuổi hiện tại: %d", tuoi));
		else
			this.ngaySinh = ngaySinh;
	}

	public PhongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}

	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(LocalDate ngayVaoLam) throws Exception {
		if (ngayVaoLam.isAfter(LocalDate.now()))
			throw new Exception("Ngày vào làm phải trước hoặc bằng ngày hiện tại");
		else
			this.ngayVaoLam = ngayVaoLam;
	}

	public double getLuongCoSo() {
		return luongCoSo;
	}

	public void setLuongCoSo(double luongCoSo) throws Exception {
		if (luongCoSo <= 0)
			throw new Exception("Lương cơ sở phải lớn hơn 0");
		else
			this.luongCoSo = luongCoSo;
	}

	public double getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(double heSoLuong) throws Exception {
		if (heSoLuong >= 1.86 && heSoLuong <= 4.6)
			this.heSoLuong = heSoLuong;
		else
			throw new Exception("Hệ số lượng phải thuộc 1 trong các hệ số: 1,86; 2,26; 2,66; 3,06; 3,46; 3,86; 4,06");
	}

	public double getTroCap() {
		return troCap;
	}

	public void setTroCap(double troCap) throws Exception {
		if (troCap < 0) {
			troCap = 0;
			throw new Exception("Trợ cấp phải >= 0");
		}

		else
			this.troCap = troCap;
	}

	public TrinhDo getTrinhDo() {
		return trinhDo;
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

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public Boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
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

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", email=" + email + ", diaChi=" + diaChi
				+ ", gioiTinh=" + gioiTinh + ", chucVu=" + chucVu + ", cccd=" + cccd + ", soDienThoai=" + soDienThoai
				+ ", ngaySinh=" + ngaySinh + ", phongBan=" + phongBan + ", ngayVaoLam=" + ngayVaoLam + ", luongCoSo="
				+ luongCoSo + ", heSoLuong=" + heSoLuong + ", troCap=" + troCap + ", trinhDo=" + trinhDo + ", hinhAnh="
				+ Arrays.toString(hinhAnh) + ", trangThai=" + trangThai + "]";
	}

}
