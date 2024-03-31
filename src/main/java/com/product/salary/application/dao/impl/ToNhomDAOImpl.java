package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.dao.ToNhomDAO;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToNhomDAOImpl extends AbstractDAO implements ToNhomDAO {

	@Override
	public List<ToNhom> timKiemTatCaToNhom() {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<ToNhom> dsToNhom = new ArrayList<ToNhom>();
		if (connect != null) {
			try {
				String query = "SELECT * FROM ToNhom";
				state = connect.createStatement();
				rs = state.executeQuery(query);

				while (rs.next()) {
					String maToNhom = rs.getString("MaToNhom");
					String tenToNhom = rs.getString("TenToNhom");
					int soLuongCongNhan = rs.getInt("SoLuongCongNhan");
					boolean trangThai = rs.getBoolean("TrangThai");

					ToNhom toNhom = new ToNhom(maToNhom, tenToNhom, soLuongCongNhan, trangThai);
					dsToNhom.add(toNhom);
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
		return dsToNhom;
	}

	@Override
	public List<ToNhom> timKiemTatCaToNhomDangHoatDong() {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<ToNhom> dsToNhom = new ArrayList<ToNhom>();
		if (connect != null) {
			try {
				String query = "SELECT * FROM ToNhom WHERE TrangThai = 1";
				state = connect.createStatement();
				rs = state.executeQuery(query);

				while (rs.next()) {
					String maToNhom = rs.getString("MaToNhom");
					String tenToNhom = rs.getString("TenToNhom");
					int soLuongCongNhan = rs.getInt("SoLuongCongNhan");
					boolean trangThai = rs.getBoolean("TrangThai");

					ToNhom toNhom = new ToNhom(maToNhom, tenToNhom, soLuongCongNhan, trangThai);
					dsToNhom.add(toNhom);
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
		return dsToNhom;
	}

	@Override
	public List<ToNhom> timKiemToNhom(ToNhom toNhom) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<ToNhom> dsToNhom = new ArrayList<ToNhom>();
		if (connect != null) {
			try {
				String conditions = getConditions(toNhom);
				String query = "SELECT * FROM ToNhom" + conditions;
				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					String maToNhom = rs.getString("MaToNhom");
					String tenToNhom = rs.getString("TenToNhom");
					int soLuongCongNhan = rs.getInt("SoLuongCongNhan");
					boolean trangThai = rs.getBoolean("TrangThai");

					ToNhom tNhom = new ToNhom(maToNhom, tenToNhom, soLuongCongNhan, trangThai);
					dsToNhom.add(tNhom);
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
		return dsToNhom;
	}

	private String getConditions(ToNhom toNhom) {
		StringBuilder query = new StringBuilder("");

		List<String> conditions = new ArrayList<String>();

		conditions.add(!StringUtils.isBlank(toNhom.getMaToNhom())
				? String.format("MaToNhom LIKE '%%%s%%'", toNhom.getMaToNhom())
				: "");
		conditions.add(!StringUtils.isBlank(toNhom.getTenToNhom())
				? String.format("TenToNhom LIKE N'%%%s%%'", toNhom.getTenToNhom())
				: "");
		if (toNhom.isTrangThai() != null) {
			conditions.add((toNhom.isTrangThai() == true) ? "TrangThai = 1" : "TrangThai = 0");
		}

		conditions.removeIf((v) -> v.equals(""));

		if (!conditions.isEmpty()) {
			String conditionsValue = StringUtils.join(conditions, " AND ");
			query.append(String.format(" WHERE %s", conditionsValue));
		}
		return query.toString();
	}

	@Override
	public ToNhom timKiemBangMaToNhom(String maToNhom) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		ToNhom toNhom = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM ToNhom WHERE MaToNhom = " + maToNhom;
				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					String tenToNhom = rs.getString("TenToNhom");
					int soLuongCongNhan = rs.getInt("SoLuongCongNhan");
					boolean trangThai = rs.getBoolean("TrangThai");

					toNhom = new ToNhom(maToNhom, tenToNhom, soLuongCongNhan, trangThai);

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
		return toNhom;
	}

	@Override
	public ToNhom capNhatToNhom(ToNhom toNhom) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("UPDATE ToNhom SET TenToNhom = ?, TrangThai = ?");
				query.append(" WHERE MaToNhom = ?");
				state = connect.prepareStatement(query.toString());

				state.setString(1, toNhom.getTenToNhom());
				state.setBoolean(2, toNhom.isTrangThai());
				state.setString(3, toNhom.getMaToNhom());

				status = state.executeUpdate();

				if (status > 0)
					return toNhom;
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
	public boolean capNhatTrangThaiToNhom(String maToNhom, boolean trangThai) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("UPDATE ToNhom SET TrangThai = ?");
				query.append(" WHERE MaToNhom = ?");
				state = connect.prepareStatement(query.toString());
				state.setBoolean(1, trangThai);
				state.setString(2, maToNhom);

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
	public ToNhom themToNhom(ToNhom toNhom) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"INSERT INTO ToNhom(MaToNhom, TenToNhom, SoLuongCongNhan, TrangThai)");
				query.append(" VALUES(?, ?, ?, ?)");
				state = connect.prepareStatement(query.toString());
				state.setString(1, toNhom.getMaToNhom());
				state.setString(2, toNhom.getTenToNhom());
				state.setInt(3, toNhom.getSoLuongCongNhan());
				state.setBoolean(4, toNhom.isTrangThai());

				status = state.executeUpdate();

				if (status > 0)
					return toNhom;
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
	public boolean capNhatSoLuongCongNhanBangMaToNhom(String maToNhom, int soLuong) {
		try(var em = getEntityManager()) {
			ToNhom toNhom = em.find(ToNhom.class, maToNhom);
			if(toNhom != null) {
				em.getTransaction().begin();
				toNhom.setSoLuongCongNhan(soLuong);
				em.merge(toNhom);
				em.getTransaction().commit();
				return true;
			}

		}
		return false;
	}

	@Override
	public String timMaToNhomCuoiCung() {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		String maToNhom = null;
		if (connect != null) {
			try {
				String query = "SELECT TOP 1 MaToNhom FROM ToNhom ORDER BY MaToNhom DESC";
				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					maToNhom = rs.getString("MaToNhom");
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
		return maToNhom;
	}

}
