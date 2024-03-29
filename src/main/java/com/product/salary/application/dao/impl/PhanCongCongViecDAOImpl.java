package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.*;
import com.product.salary.application.dao.PhanCongCongViecDAO;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhanCongCongViecDAOImpl extends AbstractDAO implements PhanCongCongViecDAO, Serializable {

	@Override
	public List<PhanCongCongNhan> timTatCaPhanCong() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<PhanCongCongNhan> dsPhanCong = new ArrayList<>();
		if (conn != null) {
			try {
				String query = "SELECT * FROM PhanCongCongNhan p JOIN CongDoanSanPham c ON p.MaCongDoan = c.MaCongDoan JOIN SanPham s ON c.MaSanPham = s.MaSanPham JOIN CongNhan cn ON p.MaCongNhan = cn.MaCongNhan";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maPc = rs.getString("MaPhanCong");
					LocalDate ngayPhanCong = rs.getDate("NgayPhanCong").toLocalDate();
					boolean trangThai = rs.getBoolean("TrangThai");

					SanPham sp = new SanPham(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
					CongNhan congNhan = new CongNhan(rs.getString("MaCongNhan"), rs.getString("HoTen"));
					CongDoanSanPham congDoan = new CongDoanSanPham(rs.getString("MaCongDoan"),
							rs.getString("TenCongDoan"), sp);
					PhanCongCongNhan phanCong = new PhanCongCongNhan(maPc, congNhan, congDoan, ngayPhanCong, trangThai);
					dsPhanCong.add(phanCong);
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
		return dsPhanCong;
	}

	@Override
	public PhanCongCongNhan capNhatPhanCongCongNhan(PhanCongCongNhan phanCongCongNhan) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(
						"UPDATE [dbo].[PhanCongCongNhan] SET [SoLuongPhanCong] = ?, [TrangThai] = ?");
				query.append(" WHERE [MaPhanCong] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setBoolean(2, phanCongCongNhan.isTrangThai());
				statement.setString(3, phanCongCongNhan.getMaPhanCong());

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật thành công
				if (status > 0) {
					return phanCongCongNhan;
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
		return null;
	}

	@Override
	public PhanCongCongNhan timKiemBangMaPhanCong(String maPhanCong) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		PhanCongCongNhan phanCong = null;
		if (conn != null) {
			try {
				String query = "SELECT * FROM PhanCongCongNhan p JOIN CongDoanSanPham c ON p.MaCongDoan = c.MaCongDoan JOIN SanPham s ON c.MaSanPham = s.MaSanPham JOIN CongNhan cn ON p.MaCongNhan = cn.MaCongNhan WHERE MaPhanCong = "
						+ maPhanCong;

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {

					String maPc = rs.getString("MaPhanCong");
					LocalDate ngayPhanCong = rs.getDate("NgayPhanCong").toLocalDate();
					boolean trangThai = rs.getBoolean("TrangThai");

					SanPham sp = new SanPham(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
					CongNhan congNhan = new CongNhan(rs.getString("MaCongNhan"), rs.getString("HoTen"));
					CongDoanSanPham congDoan = new CongDoanSanPham(rs.getString("MaCongDoan"),
							rs.getString("TenCongDoan"), sp);
					phanCong = new PhanCongCongNhan(maPc, congNhan, congDoan, ngayPhanCong, trangThai);
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
		return phanCong;
	}

	@Override
	public boolean xoaPhanCongCongNhan(String maPhanCong) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				String query = "UPDATE PhanCongCongNhan SET TrangThai = 1 WHERE MaPhanCong = " + maPhanCong;
				statement = conn.createStatement();
				int rowCount = statement.executeUpdate(query);

				if (rowCount > 0) {
					return true;
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
		return false;
	}

	@Override
	public PhanCongCongNhan phanCongCongNhan(PhanCongCongNhan phanCongCongNhan) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"INSERT INTO PhanCongCongNhan ([MaPhanCong], [MaCongDoan], [MaCongNhan], [NgayPhanCong], [TrangThai])");
				query.append(" VALUES(?, ?, ?, ?, ?)");

				state = connect.prepareStatement(query.toString());

				state.setString(1, phanCongCongNhan.getMaPhanCong());
				state.setString(2, phanCongCongNhan.getCongDoanSanPham().getMaCongDoan());
				state.setString(3, phanCongCongNhan.getCongNhan().getMaCongNhan());
				state.setDate(4, Date.valueOf(phanCongCongNhan.getNgayPhanCong()));
				state.setBoolean(5, phanCongCongNhan.isTrangThai());

				status = state.executeUpdate();
				if (status > 0)
					return phanCongCongNhan;

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
	public String timMaPhanCongCuoiCung() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		String maPC = null;
		if (conn != null) {
			try {
				String query = String.format("SELECT TOP 1 MaPhanCong FROM PhanCongCongNhan ORDER BY MaPhanCong DESC");

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					maPC = rs.getString("MaPhanCong");
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
		return maPC;
	}

	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongNhan(String maCongNhan) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<PhanCongCongNhan> dsPhanCong = new ArrayList<PhanCongCongNhan>();
		if (conn != null) {
			try {
				String query = "SELECT * FROM PhanCongCongNhan p JOIN CongDoanSanPham c ON p.MaCongDoan = c.MaCongDoan JOIN SanPham s ON c.MaSanPham = s.MaSanPham JOIN CongNhan cn ON p.MaCongNhan = cn.MaCongNhan WHERE p.MaCongNhan = '"
						+ maCongNhan + "'";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maPc = rs.getString("MaPhanCong");
					LocalDate ngayPhanCong = rs.getDate("NgayPhanCong").toLocalDate();
					boolean trangThai = rs.getBoolean("TrangThai");

					SanPham sp = new SanPham(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
					CongNhan congNhan = new CongNhan(rs.getString("MaCongNhan"), rs.getString("HoTen"));
					CongDoanSanPham congDoan = new CongDoanSanPham(rs.getString("MaCongDoan"),
							rs.getString("TenCongDoan"), sp);
					PhanCongCongNhan phanCong = new PhanCongCongNhan(maPc, congNhan, congDoan, ngayPhanCong, trangThai);
					dsPhanCong.add(phanCong);
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
		return dsPhanCong;
	}

	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongNhanDaHoanThanh(String maCongNhan) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<PhanCongCongNhan> dsPhanCong = new ArrayList<PhanCongCongNhan>();
		if (conn != null) {
			try {
				String query = "SELECT * FROM PhanCongCongNhan p JOIN CongDoanSanPham c ON p.MaCongDoan = c.MaCongDoan JOIN SanPham s ON c.MaSanPham = s.MaSanPham JOIN CongNhan cn ON p.MaCongNhan = cn.MaCongNhan WHERE p.MaCongNhan = '"
						+ maCongNhan + "' AND p.TrangThai = 1";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maPc = rs.getString("MaPhanCong");
					LocalDate ngayPhanCong = rs.getDate("NgayPhanCong").toLocalDate();
					// boolean trangThai = rs.getBoolean("TrangThai");

					SanPham sp = new SanPham(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
					CongNhan congNhan = new CongNhan(rs.getString("MaCongNhan"), rs.getString("HoTen"));
					CongDoanSanPham congDoan = new CongDoanSanPham(rs.getString("MaCongDoan"),
							rs.getString("TenCongDoan"), sp);
					PhanCongCongNhan phanCong = new PhanCongCongNhan(maPc, congNhan, congDoan, ngayPhanCong);
					dsPhanCong.add(phanCong);
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
		return dsPhanCong;
	}

	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(String maCongNhan) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<PhanCongCongNhan> dsPhanCong = new ArrayList<PhanCongCongNhan>();
		if (conn != null) {
			try {
				String query = "SELECT * FROM PhanCongCongNhan p JOIN CongDoanSanPham c ON p.MaCongDoan = c.MaCongDoan JOIN SanPham s ON c.MaSanPham = s.MaSanPham JOIN CongNhan cn ON p.MaCongNhan = cn.MaCongNhan WHERE p.MaCongNhan = '"
						+ maCongNhan + "' AND p.TrangThai = 0";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maPc = rs.getString("MaPhanCong");
					LocalDate ngayPhanCong = rs.getDate("NgayPhanCong").toLocalDate();
					// boolean trangThai = rs.getBoolean("TrangThai");

					SanPham sp = new SanPham(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
					CongNhan congNhan = new CongNhan(rs.getString("MaCongNhan"), rs.getString("HoTen"));
					CongDoanSanPham congDoan = new CongDoanSanPham(rs.getString("MaCongDoan"),
							rs.getString("TenCongDoan"), sp, rs.getInt("SoLuongCanLam"));
					PhanCongCongNhan phanCong = new PhanCongCongNhan(maPc, congNhan, congDoan, ngayPhanCong);
					dsPhanCong.add(phanCong);
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
		return dsPhanCong;
	}

	@Override
	public void capNhatSoLuongCanLam(String maCongDoan) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(
						"UPDATE [dbo].CongDoanSanPham SET SoLuongCanLam = SoLuongCanLam -  ?");
				query.append(" WHERE [MaCongDoan] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(2, maCongDoan);

				statement.executeUpdate();

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
			}
		}

	}

	@Override
	public List<CongNhan> timTatCaCongNhanChuaPhanCongVaoCongDoan(String maSanPham, String maCongDoan) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
		if (conn != null) {
			try {
				String query = "SELECT *\r\n"
						+ "FROM CongNhan JOIN TayNghe ON TayNghe.MaTayNghe = CongNhan.MaTayNghe\r\n"
						+ "WHERE CongNhan.MaCongNhan NOT IN (\r\n"
						+ "    SELECT DISTINCT PhanCongCongNhan.MaCongNhan\r\n" + "    FROM PhanCongCongNhan\r\n"
						+ "    JOIN CongDoanSanPham ON PhanCongCongNhan.MaCongDoan = CongDoanSanPham.MaCongDoan\r\n"
						+ "    JOIN SanPham ON CongDoanSanPham.MaSanPham = SanPham.MaSanPham\r\n"
						+ "    WHERE SanPham.MaSanPham = '" + maSanPham + "' AND CongDoanSanPham.MaCongDoan = '"
						+ maCongDoan + "'\r\n AND PhanCongCongNhan.TrangThai = 0" + ")";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maCongNhan = rs.getString("MaCongNhan");
					String hoTen = rs.getString("HoTen");
					String soDienThoai = rs.getString("SoDienThoai");
					TayNghe tayNghe = new TayNghe(rs.getString("MaTayNghe"), rs.getString("TenTayNghe"));
					CongNhan congNhan = new CongNhan(maCongNhan, hoTen, soDienThoai, tayNghe);
					dsCongNhan.add(congNhan);
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

	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongDoan(String maCongDoan) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<PhanCongCongNhan> dsPhanCong = new ArrayList<PhanCongCongNhan>();
		if (conn != null) {
			try {
				String query = "SELECT * FROM PhanCongCongNhan p JOIN CongDoanSanPham c ON p.MaCongDoan = c.MaCongDoan JOIN SanPham s ON c.MaSanPham = s.MaSanPham JOIN CongNhan cn ON p.MaCongNhan = cn.MaCongNhan WHERE p.MaCongDoan = '"
						+ maCongDoan + "' AND p.TrangThai = 0";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maPc = rs.getString("MaPhanCong");
					LocalDate ngayPhanCong = rs.getDate("NgayPhanCong").toLocalDate();
					// boolean trangThai = rs.getBoolean("TrangThai");

					SanPham sp = new SanPham(rs.getString("MaSanPham"), rs.getString("TenSanPham"));
					CongNhan congNhan = new CongNhan(rs.getString("MaCongNhan"), rs.getString("HoTen"));
					CongDoanSanPham congDoan = new CongDoanSanPham(rs.getString("MaCongDoan"),
							rs.getString("TenCongDoan"), sp, rs.getInt("SoLuongCanLam"));
					PhanCongCongNhan phanCong = new PhanCongCongNhan(maPc, congNhan, congDoan, ngayPhanCong);
					dsPhanCong.add(phanCong);
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
		return dsPhanCong;
	}
}
