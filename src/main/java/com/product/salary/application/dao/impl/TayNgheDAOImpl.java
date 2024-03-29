package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.dao.TayNgheDAO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TayNgheDAOImpl extends AbstractDAO implements TayNgheDAO, Serializable {

	@Override
	public List<TayNghe> timKiemTatCaTayNghe() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<TayNghe> tayNghes = new ArrayList<>();
		if (conn != null) {
			try {
				String query = "SELECT *FROM TayNghe";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maTayNghe = rs.getString("MaTayNghe");
					String tenTayNghe = rs.getString("TenTayNghe");

					TayNghe tayNghe = new TayNghe(maTayNghe, tenTayNghe);

					tayNghes.add(tayNghe);
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
		return tayNghes;
	}

	@Override
	public TayNghe capNhatTayNghe(TayNghe tayNghe) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE [dbo].[TayNghe] SET [TenTayNghe] = ?");
				query.append(" WHERE [MaTayNghe] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, tayNghe.getTenTayNghe());
				statement.setString(2, tayNghe.getMaTayNghe());
				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật thành công
				if (status > 0) {
					return tayNghe;
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
	public TayNghe themTayNghe(TayNghe tayNghe) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("INSERT INTO [dbo].[TayNghe] ([MaTayNghe], [TenTayNghe])");
				query.append(" VALUES (?, ?)");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, tayNghe.getMaTayNghe());
				statement.setString(2, tayNghe.getTenTayNghe());

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì thêm thành công
				if (status > 0) {
					return tayNghe;
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
	public TayNghe timKiemBangMaTayNghe(String maTayNgheSearch) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		TayNghe tayNghe = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM TayNghe WHERE MaTayNghe = " + maTayNgheSearch;

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maTayNghe = rs.getString("MaTayNghe");
					String tenTayNghe = rs.getString("TenTayNghe");

					tayNghe = new TayNghe(maTayNghe, tenTayNghe);

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
		return tayNghe;
	}

	@Override
	public String timMaTayNgheCuoiCung() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		String maTayNghe = null;
		if (conn != null) {
			try {
				String query = "SELECT TOP 1 MaTayNghe FROM TayNghe ORDER BY MaTayNghe DESC";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					maTayNghe = rs.getString("MaTayNghe");
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
		return maTayNghe;
	}

	@Override
	public boolean xoaTayNgheBangMa(String maTayNghe) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				String query = "DELETE FROM TayNghe WHERE MaTayNghe = " + maTayNghe;

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
