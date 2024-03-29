package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.*;
import com.product.salary.application.dao.ChamCongNhanVienDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChamCongNhanVienDAOImpl extends AbstractDAO implements ChamCongNhanVienDAO {

	@Override
	public List<ChamCongNhanVien> timKiemTatCaChamCongNhanVienTheoCaVaNgay(LocalDate ngayChamCong, String maCa) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<ChamCongNhanVien> dsChamCong = new ArrayList<ChamCongNhanVien>();
		if (connect != null) {
			try {
				// Câu truy vấn
				StringBuilder query = new StringBuilder(
						"SELECT CCNV.MaChamCong, CCNV.MaCa, CL.TenCa, CCNV.NgayChamCong, CCNV.TrangThai AS N'TrangThaiDiLam', NV.MaNhanVien, NV.HoTen, NV.Email, NV.DiaChi, NV.GioiTinh, NV.MaChucVu,  ");
				query.append("CV.TenChucVu, NV.Cccd, NV.SoDienThoai, Nv.NgaySinh, NV.MaPhongBan, PB.TenPhongBan, ");
				query.append(
						"NV.NgayVaoLam, NV.LuongCoSo, NV.HeSoLuong, NV.TroCap, NV.MaTrinhDo, TD.TenTrinhDo, NV.TrangThai, NV.HinhAnh ");
				query.append(" FROM ChamCongNhanVien AS CCNV \n");
				query.append("JOIN CaLam AS CL ON CCNV.MaCa = CL.MaCa \n");
				query.append("JOIN NhanVien AS NV ON CCNV.MaNhanVien = NV.MaNhanVien \n");
				query.append("JOIN ChucVu AS CV ON NV.MaChucVu = CV.MaChucVu\n");
				query.append("JOIN TrinhDo AS TD ON NV.MaTrinhDo = TD.MaTrinhDo\n");
				query.append("JOIN PhongBan AS PB ON NV.MaPhongBan = PB.MaPhongBan\n");
				query.append("WHERE CCNV.NgayChamCong = '" + ngayChamCong + "' AND CCNV.MaCa = '" + maCa + "'");

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					// Thông tin nhân viên
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
					NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, email, diaChi, gioiTinh, cv, cccd, dienThoai,
							ngaySinh, pb, ngayVaoLam, luongCoSo, Double.parseDouble(heSoLuongFm), troCap, td, hinhAnh,
							trangThai);

					// Thông tin chấm công
					String maChamCong = rs.getString("MaChamCong");

					// Ca làm
					String tenCa = rs.getString("TenCa");
					CaLam caLam = new CaLam(maCa, tenCa);

					int trangThaiChamCong = rs.getInt("TrangThaiDiLam");

					ChamCongNhanVien chamCongNhanVien = new ChamCongNhanVien(maChamCong, nhanVien, caLam, ngayChamCong,
							trangThaiChamCong);

					dsChamCong.add(chamCongNhanVien);
				}
			} catch (SQLException e) {
				e.getStackTrace();
			} catch (Exception e) {
				e.getStackTrace();
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
		return dsChamCong;
	}

	@Override
	public List<NhanVien> timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong(LocalDate ngayChamCong, String caLam) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<NhanVien> dsNhanVienChuaChamCong = new ArrayList<NhanVien>();
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
				query.append("JOIN PhongBan AS PB ON NV.MaPhongBan = PB.MaPhongBan \n");
				query.append("WHERE NV.TrangThai = 1 AND NV.MaNhanVien NOT IN ( ");
				query.append("SELECT MaNhanVien From ChamCongNhanVien ");
				query.append("WHERE NgayChamCong = '" + ngayChamCong + "'");
				query.append(" AND MaCa = '" + caLam + "' )");
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
					NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, email, diaChi, gioiTinh, cv, cccd, dienThoai,
							ngaySinh, pb, ngayVaoLam, luongCoSo, Double.parseDouble(heSoLuongFm), troCap, td, hinhAnh,
							trangThai);

					dsNhanVienChuaChamCong.add(nhanVien);
				}
			} catch (SQLException e) {
				e.getStackTrace();
			} catch (Exception e) {
				e.getStackTrace();
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
		return dsNhanVienChuaChamCong;
	}

	@Override
	public ChamCongNhanVien themChamCongNhanVien(ChamCongNhanVien chamCongNV) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"INSERT INTO ChamCongNhanVien(MaChamCong, MaNhanVien, NgayChamCong, TrangThai, MaCa) \n");
				query.append("VALUES(?, ?, ?, ?, ?)");
				state = connect.prepareStatement(query.toString());

				state.setString(1, chamCongNV.getMaChamCong());
				state.setString(2, chamCongNV.getNhanVien().getMaNhanVien());
				state.setDate(3, Date.valueOf(chamCongNV.getNgayChamCong()));
				state.setInt(4, chamCongNV.getTrangThai());
				state.setString(5, chamCongNV.getCaLam().getMaCa());

				status = state.executeUpdate();

				if (status > 0)
					return chamCongNV;
			} catch (SQLException e) {
				e.getStackTrace();
			} catch (Exception e) {
				e.getStackTrace();
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
	public ChamCongNhanVien timKiemBangMaChamCongNhanVien(String maChamCong) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		ChamCongNhanVien chamCongNhanVien = null;
		if (connect != null) {
			try {
				// Câu truy vấn
				StringBuilder query = new StringBuilder(
						"SELECT CCNV.MaChamCong, CCNV.MaCa, CL.TenCa, CCNV.NgayChamCong, CCNV.TrangThai AS N'TrangThaiDiLam', NV.MaNhanVien, NV.HoTen, NV.Email, NV.DiaChi, NV.GioiTinh, NV.MaChucVu,  ");
				query.append("CV.TenChucVu, NV.Cccd, NV.SoDienThoai, Nv.NgaySinh, NV.MaPhongBan, PB.TenPhongBan, ");
				query.append(
						"NV.NgayVaoLam, NV.LuongCoSo, NV.HeSoLuong, NV.TroCap, NV.MaTrinhDo, TD.TenTrinhDo, NV.TrangThai, NV.HinhAnh ");
				query.append(" FROM ChamCongNhanVien AS CCNV \n");
				query.append("JOIN CaLam AS CL ON CCNV.MaCa = CL.MaCa \n");
				query.append("JOIN NhanVien AS NV ON CCNV.MaNhanVien = NV.MaNhanVien \n");
				query.append("JOIN ChucVu AS CV ON NV.MaChucVu = CV.MaChucVu\n");
				query.append("JOIN TrinhDo AS TD ON NV.MaTrinhDo = TD.MaTrinhDo\n");
				query.append("JOIN PhongBan AS PB ON NV.MaPhongBan = PB.MaPhongBan\n");
				query.append("WHERE CCNV.MaChamCong = '" + maChamCong + "'");

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					// Thông tin nhân viên
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
					NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, email, diaChi, gioiTinh, cv, cccd, dienThoai,
							ngaySinh, pb, ngayVaoLam, luongCoSo, Double.parseDouble(heSoLuongFm), troCap, td, hinhAnh,
							trangThai);

					// Thông tin chấm công

					// Ca làm
					String maCa = rs.getString("MaCa");
					String tenCa = rs.getString("TenCa");
					CaLam caLam = new CaLam(maCa, tenCa);

					LocalDate ngayChamCong = rs.getDate("NgayChamCong").toLocalDate();
					int trangThaiChamCong = rs.getInt("TrangThaiDiLam");

					chamCongNhanVien = new ChamCongNhanVien(maChamCong, nhanVien, caLam, ngayChamCong,
							trangThaiChamCong);
				}
			} catch (SQLException e) {
				e.getStackTrace();
			} catch (Exception e) {
				e.getStackTrace();
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
		return chamCongNhanVien;
	}

	@Override
	public String timKiemMaChamCongNhanVienCuoiCungTheoNgayVaCaLam(LocalDate ngayChamCong, String caLam) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		String maChamCongCuoiCung = null;
		if (connect != null) {
			try {
				String maCa = caLam.equals("Sáng") ? "SA" : caLam.equals("Chiều") ? "CH" : "TO";
				String query = "SELECT TOP 1 MaChamCong FROM ChamCongNhanVien WHERE NgayChamCong = '" + ngayChamCong
						+ "' AND MaCa = '" + maCa + "' ORDER BY MaChamCong DESC";

				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					maChamCongCuoiCung = rs.getString("MaChamCong");
				}
			} catch (SQLException e) {
				e.getStackTrace();
			} catch (Exception e) {
				e.getStackTrace();
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
		return maChamCongCuoiCung;
	}

	@Override
	public boolean capNhatTrangThaiDiLamCuaNhanVien(String maChamCong, int trangThai) {
		Connection connect = getConnection();
		Statement state = null;
		int status = 0;
		if (connect != null) {
			try {
				String query = "UPDATE ChamCongNhanVien SET TrangThai = " + trangThai + " WHERE MaChamCong = '"
						+ maChamCong + "'";
				state = connect.createStatement();
				status = state.executeUpdate(query);
				if (status > 0)
					return true;
			} catch (SQLException e) {
				e.getStackTrace();
			} catch (Exception e) {
				e.getStackTrace();
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
	public List<NhanVien> timKiemDanhSachNhanVienDiLamBangThangVaNam(int thang, int nam) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
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
				query.append("JOIN PhongBan AS PB ON NV.MaPhongBan = PB.MaPhongBan \n");
				query.append("WHERE NV.TrangThai = 1 AND NV.MaNhanVien  IN ( ");
				query.append("SELECT MaNhanVien From ChamCongNhanVien ");
				query.append("WHERE MONTH(NgayChamCong) = " + thang);
				query.append(" AND YEAR(NgayChamCong) = " + nam + ")");
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
					NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, email, diaChi, gioiTinh, cv, cccd, dienThoai,
							ngaySinh, pb, ngayVaoLam, luongCoSo, Double.parseDouble(heSoLuongFm), troCap, td, hinhAnh,
							trangThai);

					dsNhanVien.add(nhanVien);
				}
			} catch (SQLException e) {
				e.getStackTrace();
			} catch (Exception e) {
				e.getStackTrace();
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
	public boolean capNhatMaLuongBangMaNhanVienVaThangVaNam(String maNhanVien, int thang, int nam, String maLuong) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("UPDATE ChamCongNhanVien SET MaLuong = '" + maLuong + "' \n");
				query.append("WHERE MaNhanVien = '" + maNhanVien + "'");
				query.append(" AND MONTH(NgayChamCong) = " + thang);
				query.append(" AND YEAR(NgayChamCong) = " + nam);

				state = connect.prepareStatement(query.toString());

				status = state.executeUpdate();

				if (status > 0)
					return true;
			} catch (SQLException e) {
				e.getStackTrace();
			} catch (Exception e) {
				e.getStackTrace();
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
	public List<ChamCongNhanVien> timKiemChamCongNhanVienTheoMaNhanVienVaThangVaNam(String maNhanVien, int thang,
			int nam) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<ChamCongNhanVien> dsChamCong = new ArrayList<ChamCongNhanVien>();
		if (connect != null) {
			try {
				// Câu truy vấn
				StringBuilder query = new StringBuilder(
						"SELECT CCNV.MaChamCong, CCNV.MaCa, CL.TenCa, CCNV.NgayChamCong, CCNV.TrangThai AS N'TrangThaiDiLam', NV.MaNhanVien, NV.HoTen, NV.Email, NV.DiaChi, NV.GioiTinh, NV.MaChucVu,  ");
				query.append("CV.TenChucVu, NV.Cccd, NV.SoDienThoai, Nv.NgaySinh, NV.MaPhongBan, PB.TenPhongBan, ");
				query.append(
						"NV.NgayVaoLam, NV.LuongCoSo, NV.HeSoLuong, NV.TroCap, NV.MaTrinhDo, TD.TenTrinhDo, NV.TrangThai, NV.HinhAnh ");
				query.append(" FROM ChamCongNhanVien AS CCNV \n");
				query.append("JOIN CaLam AS CL ON CCNV.MaCa = CL.MaCa \n");
				query.append("JOIN NhanVien AS NV ON CCNV.MaNhanVien = NV.MaNhanVien \n");
				query.append("JOIN ChucVu AS CV ON NV.MaChucVu = CV.MaChucVu\n");
				query.append("JOIN TrinhDo AS TD ON NV.MaTrinhDo = TD.MaTrinhDo\n");
				query.append("JOIN PhongBan AS PB ON NV.MaPhongBan = PB.MaPhongBan\n");
				query.append("WHERE CCNV = '" + maNhanVien + "'");
				query.append(" AND MONTH(CCNV.NgayChamCong) = " + thang + " AND YEAR(CCNV.NgayChamCong) = " + nam);

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					// Thông tin nhân viên
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
					NhanVien nhanVien = new NhanVien(maNhanVien, hoTen, email, diaChi, gioiTinh, cv, cccd, dienThoai,
							ngaySinh, pb, ngayVaoLam, luongCoSo, Double.parseDouble(heSoLuongFm), troCap, td, hinhAnh,
							trangThai);

					// Thông tin chấm công
					String maChamCong = rs.getString("MaChamCong");

					// Ca làm
					String maCa = rs.getString("MaCa");
					String tenCa = rs.getString("TenCa");
					CaLam caLam = new CaLam(maCa, tenCa);

					LocalDate ngayChamCong = rs.getDate("NgayChamCong").toLocalDate();
					int trangThaiChamCong = rs.getInt("TrangThaiDiLam");

					ChamCongNhanVien chamCongNhanVien = new ChamCongNhanVien(maChamCong, nhanVien, caLam, ngayChamCong,
							trangThaiChamCong);

					dsChamCong.add(chamCongNhanVien);
				}
			} catch (SQLException e) {
				e.getStackTrace();
			} catch (Exception e) {
				e.getStackTrace();
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
		return dsChamCong;
	}
}
