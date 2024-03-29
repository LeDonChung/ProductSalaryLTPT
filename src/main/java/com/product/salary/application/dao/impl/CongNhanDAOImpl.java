package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.dao.CongNhanDAO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CongNhanDAOImpl extends AbstractDAO implements CongNhanDAO, Serializable {

	@Override
	public List<CongNhan> timKiemTatCaCongNhan() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<CongNhan> dsCongNhan = new ArrayList<>();
		if (conn != null) {
			try {
				String query = "SELECT * FROM CongNhan JOIN ToNhom ON CongNhan.MaToNhom = ToNhom.MaToNhom JOIN TayNghe ON CongNhan.MaTayNghe = TayNghe.MaTayNghe";
				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maCongNhan = rs.getString("MaCongNhan");
					String hoTen = rs.getString("HoTen");
					String email = rs.getString("Email");
					String diaChi = rs.getString("DiaChi");
					int gioiTinh = rs.getInt("GioiTinh");
					String cccd = rs.getString("Cccd");
					String soDienThoai = rs.getString("SoDienThoai");
					ToNhom toNhom = new ToNhom(rs.getString("MaToNhom"), rs.getString("TenToNhom"),
							rs.getInt("SoLuongCongNhan"), rs.getBoolean("TrangThai"));
					LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
					Double troCap = rs.getDouble("TroCap");
					boolean trangThai = rs.getBoolean("TrangThai");
					LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
					byte[] hinhAnh = rs.getBytes("HinhAnh");
					TayNghe tayNghe = new TayNghe(rs.getString("MaTayNghe"), rs.getString("TenTayNghe"));
					CongNhan congNhan = new CongNhan(maCongNhan, hoTen, email, diaChi, gioiTinh, cccd, soDienThoai,
							ngaySinh, toNhom, ngayVaoLam, troCap, hinhAnh, trangThai, tayNghe);
					dsCongNhan.add(congNhan);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return dsCongNhan;
	}

	@Override
	public CongNhan capNhatCongNhan(CongNhan congNhan) {
		Connection connect = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"UPDATE [CongNhan] SET [HoTen] = ?, [Email] = ?, [DiaChi] = ?, [GioiTinh] = ?, [Cccd] = ?, [SoDienThoai] = ?, [MaToNhom] = ?, [NgayVaoLam] = ?, [TroCap] = ?, [TrangThai] = ?, [NgaySinh] = ?, [HinhAnh] = ?, [MaTayNghe] = ?");
				query.append(" WHERE [MaCongNhan] = ?");
				statement = connect.prepareStatement(query.toString());

				statement.setString(1, congNhan.getHoTen());
				statement.setString(2, congNhan.getEmail());
				statement.setString(3, congNhan.getDiaChi());
				statement.setInt(4, congNhan.getGioiTinh());
				statement.setString(5, congNhan.getCccd());
				statement.setString(6, congNhan.getSoDienThoai());
				statement.setString(7, congNhan.getToNhom().getMaToNhom());
				statement.setDate(8, Date.valueOf(congNhan.getNgayVaoLam()));
				statement.setDouble(9, congNhan.getTroCap());
				statement.setBoolean(10, congNhan.isTrangThai());
				statement.setDate(11, Date.valueOf(congNhan.getNgaySinh()));
				statement.setBytes(12, congNhan.getHinhAnh());
				statement.setString(13, congNhan.getTayNghe().getMaTayNghe());
				statement.setString(14, congNhan.getMaCongNhan());
				System.out.println(query);
				status = statement.executeUpdate();
				if (status > 0)
					return congNhan;

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
				if (statement != null) {
					try {
						statement.close();
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
		return null;
	}

	@Override
	public boolean capNhatTrangThaiCongNhan(String maCongNhan, boolean trangThai) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE [dbo].[CongNhan] SET [TrangThai] = ?");
				query.append(" WHERE [MaCongNhan] = ?");

				System.out.println(query);
				statement = conn.prepareStatement(query.toString());

				statement.setBoolean(1, trangThai);
				statement.setString(2, maCongNhan);

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật trạng thái thành công
				if (status > 0) {
					return true;
				}

			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
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
		return false;
	}

	@Override
	public List<CongNhan> timKiemCongNhan(CongNhan congNhan) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<CongNhan> dsCongNhan = new ArrayList<>();
		if (conn != null) {
			try {
				String conditions = getConditions(congNhan);

				String query = "SELECT * FROM CongNhan JOIN ToNhom ON CongNhan.MaToNhom = ToNhom.MaToNhom JOIN TayNghe ON CongNhan.MaTayNghe = TayNghe.MaTayNghe"
						+ conditions;
				System.out.println(query);
				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {

					String maCongNhan = rs.getString("MaCongNhan");
					String hoTen = rs.getString("HoTen");
					String email = rs.getString("Email");
					String diaChi = rs.getString("DiaChi");
					int gioiTinh = rs.getInt("GioiTinh");
					String cccd = rs.getString("Cccd");
					String soDienThoai = rs.getString("SoDienThoai");
					ToNhom toNhom = new ToNhom(rs.getString("MaToNhom"), rs.getString("TenToNhom"),
							rs.getInt("SoLuongCongNhan"), rs.getBoolean("TrangThai"));
					LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
					Double troCap = rs.getDouble("TroCap");
					boolean trangThai = rs.getBoolean("TrangThai");
					LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
					byte[] hinhAnh = rs.getBytes("HinhAnh");
					TayNghe tayNghe = new TayNghe(rs.getString("MaTayNghe"), rs.getString("TenTayNghe"));
					CongNhan cn = new CongNhan(maCongNhan, hoTen, email, diaChi, gioiTinh, cccd, soDienThoai, ngaySinh,
							toNhom, ngayVaoLam, troCap, hinhAnh, trangThai, tayNghe);
					dsCongNhan.add(cn);
					System.out.println(dsCongNhan);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
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
		return dsCongNhan;
	}

	private String getConditions(CongNhan congNhan) {
		// Câu truy vấn
		StringBuilder query = new StringBuilder("");

		// Các điều kiện của câu truy vấn
		List<String> conditions = new ArrayList<String>();

		// Mã công nhân
		conditions.add(!StringUtils.isBlank(congNhan.getMaCongNhan())
				? String.format("MaCongNhan LIKE N'%%%s%%'", congNhan.getMaCongNhan())
				: "");

		// Họ tên công nhân
		conditions.add(
				!StringUtils.isBlank(congNhan.getHoTen()) ? String.format("HoTen LIKE N'%%%s%%'", congNhan.getHoTen())
						: "");

		// Số điện thoại
		conditions.add(!StringUtils.isBlank(congNhan.getSoDienThoai())
				? String.format("SoDienThoai LIKE N'%%%s%%'", congNhan.getSoDienThoai())
				: "");

		// CCCD
		conditions
				.add(!StringUtils.isBlank(congNhan.getCccd()) ? String.format("Cccd LIKE N'%%%s%%'", congNhan.getCccd())
						: "");

		// Email
		conditions.add(
				!StringUtils.isBlank(congNhan.getEmail()) ? String.format("Email LIKE N'%%%s%%'", congNhan.getEmail())
						: "");

		// Địa chỉ
		conditions.add(!StringUtils.isBlank(congNhan.getDiaChi())
				? String.format("DiaChi LIKE N'%%%s%%'", congNhan.getDiaChi())
				: "");

		// Trạng thái
		if (congNhan.isTrangThai() != null) {
			conditions.add(String.format("CongNhan.TrangThai = %d", congNhan.isTrangThai() ? 1 : 0));
		}

		// giới tính
		if (congNhan.getGioiTinh() != 2) {
			conditions.add(String.format("CongNhan.GioiTinh = %d", congNhan.getGioiTinh()));
		}

		// Ngày sinh
		if (congNhan.getNgaySinh() != null) {
			conditions.add(String.format("NgaySinh = '%s'", congNhan.getNgaySinh()));
		} else {
			conditions.add("");
		}

		// Ngày vào làm
		if (congNhan.getNgayVaoLam() != null) {
			conditions.add(String.format("NgayVaoLam = '%s'", congNhan.getNgayVaoLam()));
		} else {
			conditions.add("");
		}

		// Trợ cấp
		if (congNhan.getTroCap() != null) {
			conditions.add(congNhan.getTroCap() >= 0 ? String.format("TroCap >= %f", congNhan.getTroCap()) : "");
		}

		// Tổ nhóm
		if (congNhan.getToNhom() != null) {
			if (!congNhan.getToNhom().getMaToNhom().equals("XXXX")) {
				conditions.add(String.format("CongNhan.MaToNhom = '%s'", congNhan.getToNhom().getMaToNhom()));
			}
		}

		// Tay nghề
		if (congNhan.getTayNghe() != null) {
			if (!congNhan.getTayNghe().getMaTayNghe().equals("XXXX")) {
				conditions.add(String.format("CongNhan.MaTayNghe = '%s'", congNhan.getTayNghe().getMaTayNghe()));
			}
		}

		// Xóa các điều kiện rỗng
		conditions.removeIf((v) -> v.equals(""));

		if (!conditions.isEmpty()) {
			String conditionValue = StringUtils.join(conditions, " AND ");
			query.append(String.format(" WHERE %s", conditionValue));
		}

		return query.toString();
	}

	@Override
	public CongNhan themCongNhan(CongNhan congNhan) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"INSERT INTO CongNhan (MaCongNhan, HoTen, Email, DiaChi, GioiTinh, Cccd, SoDienThoai, MaToNhom, NgayVaoLam, TroCap, NgaySinh, HinhAnh, MaTayNghe)");
				query.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

				state = connect.prepareStatement(query.toString());

				state.setString(1, congNhan.getMaCongNhan());
				state.setString(2, congNhan.getHoTen());
				state.setString(3, congNhan.getEmail());
				state.setString(4, congNhan.getDiaChi());
				state.setInt(5, congNhan.getGioiTinh());
				state.setString(6, congNhan.getCccd());
				state.setString(7, congNhan.getSoDienThoai());
				state.setString(8, congNhan.getToNhom().getMaToNhom());
				state.setDate(9, Date.valueOf(congNhan.getNgayVaoLam()));
				state.setDouble(10, congNhan.getTroCap());
				state.setDate(11, Date.valueOf(congNhan.getNgaySinh()));
				state.setBytes(12, congNhan.getHinhAnh());
				state.setString(13, congNhan.getTayNghe().getMaTayNghe());

				status = state.executeUpdate();
				if (status > 0)
					return congNhan;

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
	public CongNhan timKiemBangMaCongNhan(String maCongNhan) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		CongNhan congNhan = null;
		if (conn != null) {
			try {
				String query = "SELECT * FROM CongNhan JOIN ToNhom ON CongNhan.MaToNhom = ToNhom.MaToNhom JOIN TayNghe ON CongNhan.MaTayNghe = TayNghe.MaTayNghe WHERE CongNhan.MaCongNhan = "
						+ maCongNhan;

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maCN = rs.getString("MaCongNhan");
					String hoTen = rs.getString("HoTen");
					String email = rs.getString("Email");
					String diaChi = rs.getString("DiaChi");
					int gioiTinh = rs.getInt("GioiTinh");
					String cccd = rs.getString("Cccd");
					String soDienThoai = rs.getString("SoDienThoai");
					ToNhom toNhom = new ToNhom(rs.getString("MaToNhom"), rs.getString("TenToNhom"),
							rs.getInt("SoLuongCongNhan"), rs.getBoolean("TrangThai"));
					LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
					Double troCap = rs.getDouble("TroCap");
					boolean trangThai = rs.getBoolean("TrangThai");
					LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
					byte[] hinhAnh = rs.getBytes("HinhAnh");
					TayNghe tayNghe = new TayNghe(rs.getString("MaTayNghe"), rs.getString("TenTayNghe"));
					congNhan = new CongNhan(maCN, hoTen, email, diaChi, gioiTinh, cccd, soDienThoai, ngaySinh, toNhom,
							ngayVaoLam, troCap, hinhAnh, trangThai, tayNghe);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
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
		return congNhan;
	}

	@Override
	public String layMaCongNhan() {
		return null;
	}

	@Override
	public CongNhan timKiemBangCCCD(String cccdS) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		CongNhan congNhan = null;
		if (conn != null) {
			try {
				String query = String.format("SELECT * FROM CongNhan JOIN ToNhom ON CongNhan.MaToNhom = ToNhom.MaToNhom JOIN TayNghe ON CongNhan.MaTayNghe = TayNghe.MaTayNghe WHERE Cccd = '%s'", cccdS);
				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maCN = rs.getString("MaCongNhan");
					String hoTen = rs.getString("HoTen");
					String email = rs.getString("Email");
					String diaChi = rs.getString("DiaChi");
					int gioiTinh = rs.getInt("GioiTinh");
					String cccd = rs.getString("Cccd");
					String soDienThoai = rs.getString("SoDienThoai");
					ToNhom toNhom = new ToNhom(rs.getString("MaToNhom"), rs.getString("TenToNhom"),
							rs.getInt("SoLuongCongNhan"), rs.getBoolean("TrangThai"));
					LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
					Double troCap = rs.getDouble("TroCap");
					boolean trangThai = rs.getBoolean("TrangThai");
					LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
					byte[] hinhAnh = rs.getBytes("HinhAnh");
					TayNghe tayNghe = new TayNghe(rs.getString("MaTayNghe"), rs.getString("TenTayNghe"));
					congNhan = new CongNhan(maCN, hoTen, email, diaChi, gioiTinh, cccd, soDienThoai, ngaySinh, toNhom,
							ngayVaoLam, troCap, hinhAnh, trangThai, tayNghe);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
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
		return congNhan;
	}

	@Override
	public List<CongNhan> timKiemCongNhanBangMaToNhom(String maToNhom) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<CongNhan> dsCongNhan = new ArrayList<>();
		if (conn != null) {
			try {
				String query = String.format(
						"SELECT * FROM CongNhan JOIN ToNhom ON CongNhan.MaToNhom = ToNhom.MaToNhom JOIN TayNghe ON CongNhan.MaTayNghe = TayNghe.MaTayNghe WHERE CongNhan.TrangThai = 1 AND CongNhan.MaToNhom = '%s'",
						maToNhom);
				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maCongNhan = rs.getString("MaCongNhan");
					String hoTen = rs.getString("HoTen");
					String email = rs.getString("Email");
					String diaChi = rs.getString("DiaChi");
					int gioiTinh = rs.getInt("GioiTinh");
					String cccd = rs.getString("Cccd");
					String soDienThoai = rs.getString("SoDienThoai");
					ToNhom toNhom = new ToNhom(rs.getString("MaToNhom"), rs.getString("TenToNhom"),
							rs.getInt("SoLuongCongNhan"), rs.getBoolean("TrangThai"));
					LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
					Double troCap = rs.getDouble("TroCap");
					boolean trangThai = rs.getBoolean("TrangThai");
					LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
					byte[] hinhAnh = rs.getBytes("HinhAnh");
					TayNghe tayNghe = new TayNghe(rs.getString("MaTayNghe"), rs.getString("TenTayNghe"));
					CongNhan congNhan = new CongNhan(maCongNhan, hoTen, email, diaChi, gioiTinh, cccd, soDienThoai,
							ngaySinh, toNhom, ngayVaoLam, troCap, hinhAnh, trangThai, tayNghe);
					dsCongNhan.add(congNhan);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return dsCongNhan;
	}

	@Override
	public String layMaCongNhanCuoiCungCuaNam(int nam) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		String maCongNhan = null;
		if (connect != null) {
			try {
				String query = "SELECT TOP 1 MaCongNhan FROM CongNhan WHERE YEAR(NgayVaoLam) = " + nam
						+ " ORDER BY MaCongNhan DESC";
				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					maCongNhan = rs.getString("MaCongNhan");
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
		return maCongNhan;
	}

	@Override
	public int tongSoLuongCongNhan() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		int soLuong = 0;
		if (conn != null) {
			try {
				String query = "SELECT COUNT(*) FROM CongNhan";
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
}
