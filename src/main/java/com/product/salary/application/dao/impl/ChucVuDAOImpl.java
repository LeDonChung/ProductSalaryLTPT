package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.dao.ChucVuDAO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChucVuDAOImpl extends AbstractDAO implements ChucVuDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<ChucVu> timKiemTatCaChucVu() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<ChucVu> chucVus = new ArrayList<>();
		if (conn != null) {
			try {
				String query = "SELECT * FROM ChucVu";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maChucVu = rs.getString("MaChucVu");
					String tenChucVu = rs.getString("TenChucVu");

					ChucVu chucVu = new ChucVu(maChucVu, tenChucVu);

					chucVus.add(chucVu);
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
		return chucVus;
	}

	@Override
	public ChucVu capNhatChucVu(ChucVu chucVu) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE [dbo].[ChucVu] SET [TenChucVu] = ?");
				query.append(" WHERE [MaChucVu] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, chucVu.getTenChucVu());
				statement.setString(2, chucVu.getMaChucVu());
				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật thành công
				if (status > 0) {
					return chucVu;
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
	public ChucVu themChucVu(ChucVu chucVu) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("INSERT INTO [dbo].[ChucVu] ([MaChucVu], [TenChucVu])");
				query.append(" VALUES (?, ?)");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, chucVu.getMaChucVu());
				statement.setString(2, chucVu.getTenChucVu());

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì thêm thành công
				if (status > 0) {
					return chucVu;
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
	public ChucVu timKiemBangMaChucVu(String maChucVuSearch) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		ChucVu chucVu = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM ChucVu WHERE MaChucVu = " + maChucVuSearch;

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maChucVu = rs.getString("MaChucVu");
					String tenChucVu = rs.getString("TenChucVu");

					chucVu = new ChucVu(maChucVu, tenChucVu);

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
		return chucVu;
	}

	@Override
	public String timMaChucVuCuoiCung() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		String maChucVu = null;
		if (conn != null) {
			try {
				String query = "SELECT TOP 1 MaChucVu FROM ChucVu ORDER BY MaChucVu DESC";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					maChucVu = rs.getString("MaChucVu");
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
		return maChucVu;
	}

	@Override
	public boolean xoaChucVuBangMa(String maChucVu) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				String query = "DELETE FROM ChucVu WHERE MaChucVu = " + maChucVu;

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

}
