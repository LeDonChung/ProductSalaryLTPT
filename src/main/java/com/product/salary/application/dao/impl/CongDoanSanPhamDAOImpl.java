package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.dao.CongDoanSanPhamDAO;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CongDoanSanPhamDAOImpl extends AbstractDAO implements CongDoanSanPhamDAO, Serializable {

	@Override
	public List<CongDoanSanPham> timTatCaCongDoanSanPham(String maSanPham) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<CongDoanSanPham> congDoanSanPhams = new ArrayList<>();
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT * FROM CongDoanSanPham");
				query.append(String.format(" WHERE MaSanPham = %s  ORDER BY MaCongDoan ASC", maSanPham));

				statement = conn.createStatement();
				rs = statement.executeQuery(query.toString());

				while (rs.next()) {

					String maCongDoan = rs.getString("MaCongDoan");
					String tenCongDoan = rs.getString("TenCongDoan");
					int soLuongCanLam = rs.getInt("SoLuongCanLam");
					double giaCongDoan = rs.getDouble("GiaCongDoan");
					LocalDate thoiHan = rs.getDate("ThoiHan").toLocalDate();
					SanPham sanPham = new SanPham(maSanPham);
					boolean trangThai = rs.getBoolean("TrangThai");
					String maCongDoanlamTruoc = rs.getString("MaCongDoanLamTruoc");
					CongDoanSanPham congDoanLamTruoc = new CongDoanSanPham();
					if (maCongDoanlamTruoc != null) {
						congDoanLamTruoc = timKiemBangMaCongDoan(maCongDoanlamTruoc);
					}
					CongDoanSanPham congDoanSanPham = new CongDoanSanPham(maCongDoan, tenCongDoan, soLuongCanLam,
							giaCongDoan, thoiHan, congDoanLamTruoc, sanPham, trangThai);
					congDoanSanPhams.add(congDoanSanPham);
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
		return congDoanSanPhams;
	}

	@Override
	public List<CongDoanSanPham> timTatCaCongDoanSanPhamDangHoatDongBangMaSanPham(String maSanPham) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<CongDoanSanPham> congDoanSanPhams = new ArrayList<>();
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT * FROM CongDoanSanPham");
				query.append(String.format(" WHERE MaSanPham = %s ORDER BY MaCongDoan ASC", maSanPham));

				statement = conn.createStatement();
				rs = statement.executeQuery(query.toString());

				while (rs.next()) {

					String maCongDoan = rs.getString("MaCongDoan");
					String tenCongDoan = rs.getString("TenCongDoan");
					int soLuongCanLam = rs.getInt("SoLuongCanLam");
					double giaCongDoan = rs.getDouble("GiaCongDoan");
					LocalDate thoiHan = rs.getDate("ThoiHan").toLocalDate();
					SanPham sanPham = new SanPham(maSanPham);
					boolean trangThai = rs.getBoolean("TrangThai");
					String maCongDoanlamTruoc = rs.getString("MaCongDoanLamTruoc");
					CongDoanSanPham congDoanLamTruoc = new CongDoanSanPham();
					if (maCongDoanlamTruoc != null) {
						congDoanLamTruoc = timKiemBangMaCongDoan(maCongDoanlamTruoc);
					}
					CongDoanSanPham congDoanSanPham = new CongDoanSanPham(maCongDoan, tenCongDoan, soLuongCanLam,
							giaCongDoan, thoiHan, congDoanLamTruoc, sanPham, trangThai);

					congDoanSanPhams.add(congDoanSanPham);
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
		return congDoanSanPhams;
	}

	@Override
	public CongDoanSanPham capNhatCongDoanSanPham(CongDoanSanPham congDoanSanPham) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(
						"UPDATE [dbo].[CongDoanSanPham] SET [TenCongDoan] = ?, [SoLuongCanLam] = ?, [GiaCongDoan] = ?, [ThoiHan] = ?,[MaCongDoanLamTruoc] = ?, [TrangThai] = ?");
				query.append(" WHERE [MaCongDoan] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, congDoanSanPham.getTenCongDoan());
				statement.setInt(2, congDoanSanPham.getSoLuongCanLam());
				statement.setDouble(3, congDoanSanPham.getGiaCongDoan());
				statement.setDate(4, Date.valueOf(congDoanSanPham.getThoiHan()));
				statement.setString(5, congDoanSanPham.getCongDoanLamTruoc() == null ? null
						: congDoanSanPham.getCongDoanLamTruoc().getMaCongDoan());
				statement.setBoolean(6, congDoanSanPham.isTrangThai());
				statement.setString(7, congDoanSanPham.getMaCongDoan());

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật thành công
				if (status > 0) {
					return congDoanSanPham;
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
	public boolean capNhatTrangThaiCongDoanSanPham(String maCongDoanSanPham, boolean trangThai) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE [dbo].[CongDoanSanPham] SET [TrangThai] = ?");
				query.append(" WHERE [MaCongDoan] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setBoolean(1, trangThai);
				statement.setString(2, maCongDoanSanPham);

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật thành công
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
	public CongDoanSanPham themCongDoanSanPham(CongDoanSanPham congDoanSanPham) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"INSERT INTO CongDoanSanPham ([MaCongDoan], [TenCongDoan], [SoLuongCanLam], [GiaCongDoan], [ThoiHan], [MaCongDoanLamTruoc], [MaSanPham], [TrangThai])");
				query.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

				state = connect.prepareStatement(query.toString());

				state.setString(1, congDoanSanPham.getMaCongDoan());
				state.setString(2, congDoanSanPham.getTenCongDoan());
				state.setInt(3, congDoanSanPham.getSoLuongCanLam());
				state.setDouble(4, congDoanSanPham.getGiaCongDoan());
				state.setDate(5, Date.valueOf(congDoanSanPham.getThoiHan()));
				state.setString(6, congDoanSanPham.getCongDoanLamTruoc() == null ? null
						: congDoanSanPham.getCongDoanLamTruoc().getMaCongDoan());
				state.setString(7, congDoanSanPham.getSanPham().getMaSanPham());
				state.setBoolean(8, congDoanSanPham.isTrangThai());

				status = state.executeUpdate();
				if (status > 0)
					return congDoanSanPham;

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
	public CongDoanSanPham timKiemBangMaCongDoan(String maCongDoanSearch) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		CongDoanSanPham congDoanSanPham = null;
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT * FROM CongDoanSanPham ");
				query.append(" WHERE MaCongDoan = " + maCongDoanSearch);
				statement = conn.createStatement();
				rs = statement.executeQuery(query.toString());

				while (rs.next()) {

					String maCongDoan = rs.getString("MaCongDoan");
					String tenCongDoan = rs.getString("TenCongDoan");
					int soLuongCanLam = rs.getInt("SoLuongCanLam");
					double giaCongDoan = rs.getDouble("GiaCongDoan");
					LocalDate thoiHan = rs.getDate("ThoiHan").toLocalDate();
					SanPham sanPham = new SanPham(rs.getString("MaSanPham"));
					boolean trangThai = rs.getBoolean("TrangThai");
					CongDoanSanPham congDoanLamTruoc = new CongDoanSanPham();
					congDoanLamTruoc.setMaCongDoan(rs.getString("MaCongDoanLamTruoc"));
					congDoanSanPham = new CongDoanSanPham(maCongDoan, tenCongDoan, soLuongCanLam,
							giaCongDoan, thoiHan, congDoanLamTruoc, sanPham, trangThai);
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
		return congDoanSanPham;
	}

	@Override
	public String timMaCongDoanSanPhamCuoiCungBangMaSanPham(String maSanPham) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		String maCongDoan = null;
		if (conn != null) {
			try {
				String query = String.format(
						"SELECT TOP 1 MaCongDoan FROM CongDoanSanPham WHERE MaSanPham = %s ORDER BY MaCongDoan DESC",
						maSanPham);

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					maCongDoan = rs.getString("MaCongDoan");
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
		return maCongDoan;
	}

	@Override
	public void capNhatSoLuongCanCuaCongDoan(String maCongDoan, int soLuong) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE CongDoanSanPham SET SoLuongCanLam = SoLuongCanLam - ? ");
				query.append(" WHERE MaCongDoan = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setDouble(1, soLuong);
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
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}

	@Override
	public void capNhatSoLuongCanCuaCongDoanSau(String maCongDoan, int soLuong) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE CongDoanSanPham SET SoLuongCanLam = SoLuongCanLam + ? ");
				query.append(" WHERE MaCongDoan = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setDouble(1, soLuong);
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
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	@Override
	public CongDoanSanPham timCongDoanlamSauBangMaCongDoan(String maCongDoan) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		CongDoanSanPham congDoanSanPham = null;
		if (conn != null) {
			try {
				String query = String.format(
						"SELECT * FROM CongDoanSanPham WHERE MaCongDoanLamTruoc = %s",
						maCongDoan);

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maCongDoanSau = rs.getString("MaCongDoan");
					String tenCongDoan = rs.getString("TenCongDoan");
					int soLuongCanLam = rs.getInt("SoLuongCanLam");
					double giaCongDoan = rs.getDouble("GiaCongDoan");
					LocalDate thoiHan = rs.getDate("ThoiHan").toLocalDate();
					SanPham sanPham = new SanPham(rs.getString("MaSanPham"));
					boolean trangThai = rs.getBoolean("TrangThai");
					String maCongDoanlamTruoc = rs.getString("MaCongDoanLamTruoc");
					CongDoanSanPham congDoanLamTruoc = new CongDoanSanPham();
					if (maCongDoanlamTruoc != null) {
						congDoanLamTruoc = timKiemBangMaCongDoan(maCongDoanlamTruoc);
					}
					congDoanSanPham = new CongDoanSanPham(maCongDoanSau, tenCongDoan, soLuongCanLam,
							giaCongDoan, thoiHan, congDoanLamTruoc, sanPham, trangThai);
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
		return congDoanSanPham;
	}
}
