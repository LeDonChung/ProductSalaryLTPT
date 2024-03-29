package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.LuongCongNhan;
import com.product.salary.application.dao.LuongCongNhanDAO;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuongCongNhanDAOImpl extends AbstractDAO implements LuongCongNhanDAO, Serializable {

	@Override
	public double tinhTongTienCongNhanTheoMaCongNhanVaThangVaNam(String maCongNhan, int thang, int nam) {
		double tongTien = 0;
		Connection connect = getConnection();
		ResultSet rs = null;
		Statement state = null;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT SUM(GiaCongDoan * SoLuongHoanThanh) AS TongTien")
						.append(String.format(
								" FROM (SELECT *FROM ChamCongCongNhan WHERE YEAR(NgayChamCong) = %d AND MONTH(NgayChamCong) = %d) AS CCCN",
								nam, thang))
						.append(" JOIN PhanCongCongNhan AS PCCN").append(" ON CCCN.MaPhanCong = PCCN.MaPhanCong")
						.append(" JOIN CongDoanSanPham AS CDSP").append(" ON CDSP.MaCongDoan = PCCN.MaCongDoan")
						.append(String.format(" WHERE MaCongNhan = %s", maCongNhan));

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					tongTien = rs.getDouble("TongTien");
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

		return tongTien;
	}

	@Override
	public LuongCongNhan capNhatLuongCongNhan(LuongCongNhan luongCongNhan) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(
						"UPDATE [dbo].[LuongCongNhan] SET [Thang] = ?, [Nam] = ?, [NgayTinhLuong] = ?, [Luong] = ?, [LuongThuong] = ? ");
				query.append(" WHERE MaLuong = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(6, luongCongNhan.getMaLuong());
				statement.setInt(1, luongCongNhan.getThang());
				statement.setInt(2, luongCongNhan.getNam());
				statement.setDate(3, Date.valueOf(luongCongNhan.getNgayTinhLuong()));
				statement.setDouble(4, luongCongNhan.getLuong());
				statement.setDouble(5, luongCongNhan.getLuongThuong());
				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật thành công
				if (status > 0) {
					return luongCongNhan;
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
	public LuongCongNhan themLuongCongNhan(LuongCongNhan luongCongNhan) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(
						"INSERT INTO [dbo].[LuongCongNhan] ([MaLuong], [Thang], [Nam], [NgayTinhLuong], [Luong], [LuongThuong])");
				query.append(" VALUES (?, ?, ?, ?, ?, ?)");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, luongCongNhan.getMaLuong());
				statement.setInt(2, luongCongNhan.getThang());
				statement.setInt(3, luongCongNhan.getNam());
				statement.setDate(4, Date.valueOf(luongCongNhan.getNgayTinhLuong()));
				statement.setDouble(5, luongCongNhan.getLuong());
				statement.setDouble(6, luongCongNhan.getLuongThuong());
				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì thêm thành công
				if (status > 0) {
					return luongCongNhan;
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
	public LuongCongNhan timKiemLuongCongNhanBangMaLuong(String maLuongS) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		LuongCongNhan luongCongNhan = null;
		if (conn != null) {
			try {
				String query = String.format("SELECT *FROM LuongCongNhan WHERE MaLuong = '%s'", maLuongS);

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maLuong = rs.getString("MaLuong");
					int thang = rs.getInt("Thang");
					int nam = rs.getInt("Nam");
					LocalDate ngayTinhLuong = rs.getDate("NgayTinhLuong").toLocalDate();
					double luong = rs.getDouble("Luong");
					double luongThuong = rs.getDouble("LuongThuong");
					luongCongNhan = new LuongCongNhan(maLuong, thang, nam, ngayTinhLuong, luong, luongThuong);
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
		return luongCongNhan;
	}

	@Override
	public List<LuongCongNhan> timTatCaLuongCongNhanTheoThangVaNam(int thangS, int namS) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<LuongCongNhan> luongCongNhans = new ArrayList<LuongCongNhan>();
		if (conn != null) {
			try {
				String query = String.format("SELECT * FROM LuongCongNhan WHERE Thang = %d AND Nam = %d", thangS, namS);

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maLuong = rs.getString("MaLuong");
					int thang = rs.getInt("Thang");
					int nam = rs.getInt("Nam");
					LocalDate ngayTinhLuong = rs.getDate("NgayTinhLuong").toLocalDate();
					double luong = rs.getDouble("Luong");
					double luongThuong = rs.getDouble("LuongThuong");
					LuongCongNhan luongCongNhan = new LuongCongNhan(maLuong, thang, nam, ngayTinhLuong, luong,
							luongThuong);
					luongCongNhans.add(luongCongNhan);
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
		return luongCongNhans;
	}

	@Override
	public List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maCongNhan, int thang, int nam) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<Map<String, Object>> chiTiets = new ArrayList<Map<String, Object>>();
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder(
						" SELECT *,  (CCCN.SoLuongHoanThanh * CDSP.GiaCongDoan) AS 'TongTien' FROM ChamCongCongNhan CCCN");
				query.append(" JOIN PhanCongCongNhan AS PCCN");
				query.append(" ON CCCN.MaPhanCong = PCCN.MaPhanCong");
				query.append(" JOIN CongDoanSanPham AS CDSP");
				query.append(" ON PCCN.MaCongDoan = CDSP.MaCongDoan");
				query.append(" JOIN CongNhan AS CN");
				query.append(" ON CN.MaCongNhan = PCCN.MaCongNhan");
				query.append(" JOIN CaLam AS CA");
				query.append(" ON CA.MaCa = CCCN.MaCa");
				query.append(" JOIN SanPham AS SP");
				query.append(" ON SP.MaSanPham = CDSP.MaSanPham");
				query.append(String.format(
						" WHERE MONTH(CCCN.NgayChamCong) = %d AND YEAR(CCCN.NgayChamCong) = %d AND CN.MaCongNhan = %s",
						thang, nam, maCongNhan));
				statement = conn.createStatement();
				rs = statement.executeQuery(query.toString());

				while (rs.next()) {
					Map<String, Object> chiTiet = new HashMap<String, Object>();
					chiTiet.put("MaChamCong", rs.getString("MaChamCong"));
					chiTiet.put("MaCongNhan", rs.getString("MaCongNhan"));
					chiTiet.put("HoTen", rs.getString("HoTen"));
					chiTiet.put("TenSanPham", rs.getString("TenSanPham"));
					chiTiet.put("TenCongDoan", rs.getString("TenCongDoan"));
					chiTiet.put("NgayCham", rs.getDate("NgayChamCong").toLocalDate());
					chiTiet.put("CaLam", rs.getString("TenCa"));
					chiTiet.put("SoLuongHoanThanh", rs.getInt("SoLuongHoanThanh"));
					chiTiet.put("TongTien", rs.getDouble("TongTien"));
					String trangThai = "";
					if (rs.getInt("TrangThai") == 0) {
						trangThai = "Nghỉ không phép";
					} else if (rs.getInt("TrangThai") == 1) {
						trangThai = "Có mặt";
					} else {
						trangThai = "Nghỉ có phép";
					}

					chiTiet.put("TrangThai", trangThai);
					chiTiets.add(chiTiet);
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
		return chiTiets;
	}

	@Override
	public Map<String, Double> thongKeLuongCongNhanTheoNam() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		Map<String, Double> results = new HashMap<String, Double>();
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder(
						"SELECT Nam, SUM(Luong) AS 'Luong' FROM LuongCongNhan GROUP BY Nam ORDER BY Nam");
				statement = conn.createStatement();
				rs = statement.executeQuery(query.toString());
				while (rs.next()) {
					results.put(String.format("%s", rs.getInt("Nam")), rs.getDouble("Luong"));
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
		return results;
	}

	@Override
	public Map<String, Double> thongKeLuongCongNhanTheoThang(int nam) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		Map<String, Double> results = new HashMap<String, Double>();
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder(String.format(
						"SELECT Nam, Thang, SUM(Luong) AS 'Luong' FROM LuongCongNhan WHERE Nam = %d GROUP BY Nam, Thang ORDER BY Nam, Thang",
						nam));
				statement = conn.createStatement();
				rs = statement.executeQuery(query.toString());
				while (rs.next()) {
					results.put(String.format("%s", rs.getInt("Thang")), rs.getDouble("Luong"));
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
		return results;
	}

	@Override
	public void capNhatLuongThuong(String maLuong, double luongThuong) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE [dbo].[LuongCongNhan] SET [LuongThuong] = ? ");
				query.append(" WHERE MaLuong = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setDouble(1, luongThuong);
				statement.setString(2, maLuong);
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
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
