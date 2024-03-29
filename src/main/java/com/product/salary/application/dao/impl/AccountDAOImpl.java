package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.Account;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.dao.AccountDAO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl extends AbstractDAO implements AccountDAO, Serializable {

	@Override
	public Account timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau(String taiKhoanS, String matKhauS) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		Account account = null;
		if (conn != null) {
			try {
				String query = String.format(
						"SELECT *FROM Account WHERE TaiKhoan = '%s' AND MatKhau = '%s' AND TrangThai = %d", taiKhoanS,
						matKhauS, 1);

				statement = conn.createStatement();
				rs = statement.executeQuery(query);
				

				while (rs.next()) {
					String taiKhoan = rs.getString("TaiKhoan");
					String matKhau = rs.getString("MatKhau");
					String vaiTro = rs.getString("RoleName");

					NhanVien nhanVien = new NhanVien();
					nhanVien.setMaNhanVien(rs.getString("MaNhanVien"));

					boolean trangThai = rs.getBoolean("TrangThai");
					account = new Account(taiKhoan, matKhau, vaiTro, nhanVien, trangThai);
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
		return account;
	}

	@Override
	public List<Account> timKiemTatCaTaiKhoan() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<Account> accounts = new ArrayList<>();
		if (conn != null) {
			try {
				String query = "SELECT * FROM Account";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String taiKhoan = rs.getString("TaiKhoan");
					String matKhau = rs.getString("MatKhau");
					String vaiTro = rs.getString("RoleName");

					NhanVien nhanVien = new NhanVien();
					nhanVien.setMaNhanVien(rs.getString("MaNhanVien"));

					boolean trangThai = rs.getBoolean("TrangThai");
					Account account = new Account(taiKhoan, matKhau, vaiTro, nhanVien, trangThai);

					accounts.add(account);
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
		return accounts;
	}

	@Override
	public Account capNhatTaiKhoan(Account account) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(
						"UPDATE [dbo].[Account] SET [MatKhau] = ?, [TrangThai] = ?, [MaNhanVien] = ?, [RoleName] = ?");
				query.append(" WHERE [TaiKhoan] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, account.getMatKhau());
				statement.setBoolean(2, account.isTrangThai());
				statement.setString(3, account.getNhanVien().getMaNhanVien());
				statement.setString(4, account.getRoleName());
				statement.setString(5, account.getTaiKhoan());

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật thành công
				if (status > 0) {
					return account;
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
	public Account themTaiKhoan(Account account) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(
						"INSERT INTO [dbo].[Account] ([TaiKhoan], [MatKhau], [TrangThai], [MaNhanvien], [RoleName])");
				query.append(" VALUES (?, ?, ?, ?, ?");

				statement = conn.prepareStatement(query.toString());

				// set statement

				statement.setString(1, account.getTaiKhoan());
				statement.setString(2, account.getMatKhau());
				statement.setBoolean(3, account.isTrangThai());
				statement.setString(4, account.getNhanVien().getMaNhanVien());
				statement.setString(5, account.getRoleName());

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì thêm thành công
				if (status > 0) {
					return account;
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
	public boolean capNhatTrangThaiTaiKhoan(String taiKhoan, boolean trangThai) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE [dbo].[Account] SET [TrangThai] = ?");
				query.append(" WHERE [TaiKhoan] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setBoolean(1, trangThai);
				statement.setString(2, taiKhoan);

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
	public Account timTaiKhoanBangTaiKhoan(String taiKhoanS, String matKhauS) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		Account account = null;
		if (conn != null) {
			try {
				String query = String.format("SELECT *FROM Account WHERE TaiKhoan = %s AND MatKhau = %s", taiKhoanS,
						matKhauS);

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String taiKhoan = rs.getString("TaiKhoan");
					String matKhau = rs.getString("MatKhau");
					String vaiTro = rs.getString("RoleName");

					NhanVien nhanVien = new NhanVien();
					nhanVien.setMaNhanVien(rs.getString("MaNhanVien"));

					boolean trangThai = rs.getBoolean("TrangThai");
					account = new Account(taiKhoan, matKhau, vaiTro, nhanVien, trangThai);
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
		return account;
	}

	@Override
	public boolean capNhatMatKhau(String taiKhoan, String matKhau) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE [dbo].[Account] SET [MatKhau] = ?");
				query.append(" WHERE [TaiKhoan] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, matKhau);
				statement.setString(2, taiKhoan);

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
	public Account timKiemBangTaiKhoan(String taiKhoan) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		Account account = null;
		if (conn != null) {
			try {
				String query = String.format("SELECT *FROM Account WHERE TaiKhoan = %s", taiKhoan);

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String matKhau = rs.getString("MatKhau");
					String vaiTro = rs.getString("RoleName");

					NhanVien nhanVien = new NhanVien();
					nhanVien.setMaNhanVien(rs.getString("MaNhanVien"));

					boolean trangThai = rs.getBoolean("TrangThai");
					account = new Account(taiKhoan, matKhau, vaiTro, nhanVien, trangThai);
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
		return account;
	}

}
