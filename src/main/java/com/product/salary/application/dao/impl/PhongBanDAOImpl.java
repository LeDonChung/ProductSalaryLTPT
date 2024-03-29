package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.dao.PhongBanDAO;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongBanDAOImpl extends AbstractDAO implements PhongBanDAO {

	@Override
	public List<PhongBan> timKiemTatCaPhongBan() {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<PhongBan> dsPhongBan = new ArrayList<PhongBan>();
		if (connect != null) {
			try {
				String query = "SELECT * FROM PhongBan";
				state = connect.createStatement();
				rs = state.executeQuery(query);

				while (rs.next()) {
					String maPhongBan = rs.getString("MaPhongBan");
					String tenPhongBan = rs.getString("TenPhongBan");
					int soLuongNhanVien = rs.getInt("SoLuongNhanVien");
					boolean trangThai = rs.getBoolean("TrangThai");

					PhongBan pb = new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien, trangThai);
					dsPhongBan.add(pb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
		return dsPhongBan;
	}

	@Override
	public List<PhongBan> timKiemPhongBan(PhongBan phongBan) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<PhongBan> dsPhongBan = new ArrayList<PhongBan>();
		if (connect != null) {
			try {
				String conditions = getConditions(phongBan);
				String query = "SELECT * FROM PhongBan" + conditions;
				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					String maPhongBan = rs.getString("MaPhongBan");
					String tenPhongBan = rs.getString("TenPhongBan");
					int soLuongNhanVien = rs.getInt("SoLuongNhanVien");
					boolean trangThai = rs.getBoolean("TrangThai");

					PhongBan pb = new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien, trangThai);
					dsPhongBan.add(pb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
		return dsPhongBan;
	}

	private String getConditions(PhongBan pb) {
		StringBuilder query = new StringBuilder("");

		List<String> conditions = new ArrayList<String>();

		conditions.add(
				!StringUtils.isBlank(pb.getMaPhongBan()) ? String.format("MaPhongBan LIKE '%%%s%%'", pb.getMaPhongBan())
						: "");
		conditions.add(!StringUtils.isBlank(pb.getTenPhongBan())
				? String.format("TenPhongBan LIKE N'%%%s%%'", pb.getTenPhongBan())
				: "");
		if (pb.isTrangThai() != null) {
			conditions.add((pb.isTrangThai() == true) ? "TrangThai = 1" : "TrangThai = 0");
		}

		conditions.removeIf((v) -> v.equals(""));

		if (!conditions.isEmpty()) {
			String conditionsValue = StringUtils.join(conditions, " AND ");
			query.append(String.format(" WHERE %s", conditionsValue));
		}
		return query.toString();
	}

	@Override
	public PhongBan timKiemBangMaPhongBan(String maPhongBan) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		PhongBan phongBan = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM PhongBan WHERE MaPhongBan = " + maPhongBan;
				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					String tenPhongBan = rs.getString("TenPhongBan");
					int soLuongNhanVien = rs.getInt("SoLuongNhanVien");
					boolean trangThai = rs.getBoolean("TrangThai");

					phongBan = new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien, trangThai);
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
		return phongBan;
	}

	@Override
	public PhongBan capNhatPhongBan(PhongBan phongBan) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("UPDATE PhongBan SET TenPhongBan = ?, TrangThai = ?");
				query.append(" WHERE MaPhongBan = ?");
				state = connect.prepareStatement(query.toString());

				state.setString(1, phongBan.getTenPhongBan());
				state.setBoolean(2, phongBan.isTrangThai());
				state.setString(3, phongBan.getMaPhongBan());

				status = state.executeUpdate();

				if (status > 0)
					return phongBan;
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
	public boolean capNhatTrangThaiPhongBan(String maPhongBan, boolean trangThai) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("UPDATE PhongBan SET TrangThai = ?");
				query.append(" WHERE MaPhongBan = ?");
				state = connect.prepareStatement(query.toString());
				state.setBoolean(1, trangThai);
				state.setString(2, maPhongBan);

				status = state.executeUpdate();

				if (status > 0)
					return true;
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
		return false;
	}

	@Override
	public PhongBan themPhongBan(PhongBan phongBan) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"INSERT INTO PhongBan(MaPhongBan, TenPhongBan, SoLuongNhanVien, TrangThai)");
				query.append(" VALUES(?, ?, ?, ?)");
				state = connect.prepareStatement(query.toString());
				state.setString(1, phongBan.getMaPhongBan());
				state.setString(2, phongBan.getTenPhongBan());
				state.setInt(3, phongBan.getSoLuongNhanVien());
				state.setBoolean(4, phongBan.isTrangThai());

				status = state.executeUpdate();

				if (status > 0)
					return phongBan;
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
	public boolean capNhatSoLuongNhanVienBangMaPhongBan(String maPhongBan, int soLuong) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("UPDATE PhongBan SET SoLuongNhanVien = " + soLuong);
				query.append(" WHERE MaPhongBan = ?");
				state = connect.prepareStatement(query.toString());
				state.setString(1, maPhongBan);

				status = state.executeUpdate();

				if (status > 0)
					return true;
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
		return false;
	}

	@Override
	public String timMaPhongbanCuoiCung() {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		String maPhongBan = null;
		if (connect != null) {
			try {
				String query = "SELECT TOP 1 MaPhongBan FROM PhongBan ORDER BY MaPhongBan DESC";
				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					maPhongBan = rs.getString("MaPhongBan");
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
		return maPhongBan;
	}

	@Override
	public List<PhongBan> timKiemTatCaPhongBanDangHoatDong() {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<PhongBan> dsPhongBan = new ArrayList<PhongBan>();
		if (connect != null) {
			try {
				String query = "SELECT * FROM PhongBan WHERE TrangThai = 1";
				state = connect.createStatement();
				rs = state.executeQuery(query);

				while (rs.next()) {
					String maPhongBan = rs.getString("MaPhongBan");
					String tenPhongBan = rs.getString("TenPhongBan");
					int soLuongNhanVien = rs.getInt("SoLuongNhanVien");
					boolean trangThai = rs.getBoolean("TrangThai");

					PhongBan pb = new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien, trangThai);
					dsPhongBan.add(pb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
		return dsPhongBan;
	}
}
