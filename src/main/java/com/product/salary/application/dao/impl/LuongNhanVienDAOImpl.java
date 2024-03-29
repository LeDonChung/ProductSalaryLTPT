package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.LuongNhanVien;
import com.product.salary.application.dao.LuongNhanVienDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuongNhanVienDAOImpl extends AbstractDAO implements LuongNhanVienDAO {

	@Override
	public Map<String, Double> thongKeLuongNhanVienTheoNam() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		Map<String, Double> results = new HashMap<String, Double>();
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder(
						"SELECT Nam, SUM(Luong) AS 'Luong' FROM LuongNhanVien GROUP BY Nam ORDER BY Nam");
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
	public int laySoLuongCaSangVaChieuKhongThuocNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam) {
		Connection connect = getConnection();
		Statement state = null;
		int soLuongCaLam = 0;
		ResultSet rs = null;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT 'SoLuongCaLam' = COUNT(*) FROM ChamCongNhanVien\n");
				query.append("WHERE MaNhanVien = '" + maNhanVien + "' \n");
				query.append("AND TrangThai != 0 \n");
				query.append("AND MONTH(NgayChamCong) = " + thang + " \n");
				query.append("AND YEAR(NgayChamCong) = " + nam + " \n");
				query.append("AND MaCa IN('SA', 'CH') \n");
				query.append("AND TrangThai IN (1, 2)");

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					soLuongCaLam = rs.getInt("SoLuongCaLam");
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
		return soLuongCaLam;
	}

	@Override
	public int laySoLuongCaToiKhongThuocNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam) {
		Connection connect = getConnection();
		Statement state = null;
		int soLuongCaLam = 0;
		ResultSet rs = null;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT 'SoLuongCaLam' = COUNT(*) FROM ChamCongNhanVien\n");
				query.append("WHERE MaNhanVien = '" + maNhanVien + "' \n");
				query.append("AND TrangThai != 0 \n");
				query.append("AND MONTH(NgayChamCong) = " + thang + " \n");
				query.append("AND YEAR(NgayChamCong) = " + nam + " \n");
				query.append("AND MaCa = 'TO' \n");
				query.append("AND TrangThai IN (1, 2)");

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					soLuongCaLam = rs.getInt("SoLuongCaLam");
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
		return soLuongCaLam;
	}

	@Override
	public int laySoLuongCaLamNgayChuNhatBangMaNhanVien(String maNhanVien, int thang, int nam) {
		Connection connect = getConnection();
		Statement state = null;
		int soLuongCaLam = 0;
		ResultSet rs = null;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT 'SoLuongCaLam' = COUNT(*) FROM ChamCongNhanVien\n");
				query.append("WHERE MaNhanVien = '" + maNhanVien + "' \n");
				query.append("AND TrangThai != 0 \n");
				query.append("AND MONTH(NgayChamCong) = " + thang + " \n");
				query.append("AND YEAR(NgayChamCong) = " + nam + " \n");
				query.append("AND DATEPART(dw, NgayChamCong) = 1 \n");
				query.append("AND TrangThai IN (1, 2)");

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					soLuongCaLam = rs.getInt("SoLuongCaLam");
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
		return soLuongCaLam;
	}

	@Override
	public List<LuongNhanVien> timKiemTatCaLuongNhanVienTheoThangVaNam(int thang, int nam) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<LuongNhanVien> dsLuongNhanVien = new ArrayList<LuongNhanVien>();
		if (connect != null) {
			try {
				String query = String.format("SELECT * FROM LuongNhanVien WHERE MaLuong LIKE '%02d%04d%%'", thang, nam);

				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					String maLuong = rs.getString("MaLuong");
					LocalDate ngayTinhLuong = rs.getDate("NgayTinhLuong").toLocalDate();
					double luong = rs.getDouble("Luong");
					double luongThuong = rs.getDouble("LuongThuong");
					LuongNhanVien luongNV = new LuongNhanVien(maLuong, thang, nam, ngayTinhLuong, luong, luongThuong);
					dsLuongNhanVien.add(luongNV);
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
		return dsLuongNhanVien;
	}

	@Override
	public LuongNhanVien timKiemBangMaLuong(String maLuong) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		LuongNhanVien luongNV = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM LuongNhanVien WHERE MaLuong = '" + maLuong + "'";

				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					LocalDate ngayTinhLuong = rs.getDate("NgayTinhLuong").toLocalDate();
					int thang = rs.getInt("Thang");
					int nam = rs.getInt("Nam");
					double luong = rs.getDouble("Luong");
					double luongThuong = rs.getDouble("LuongThuong");
					luongNV = new LuongNhanVien(maLuong, thang, nam, ngayTinhLuong, luong, luongThuong);
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
		return luongNV;
	}

	@Override
	public LuongNhanVien themLuongNhanVien(LuongNhanVien luongNV) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"INSERT INTO LuongNhanVien(MaLuong, Thang, Nam, NgayTinhLuong, Luong, LuongThuong) \n");
				query.append("VALUES(?, ?, ?, ?, ?, ?)");
				state = connect.prepareStatement(query.toString());

				state.setString(1, luongNV.getMaLuong());
				state.setInt(2, luongNV.getThang());
				state.setInt(3, luongNV.getNam());
				state.setDate(4, Date.valueOf(luongNV.getNgayTinhLuong()));
				state.setDouble(5, luongNV.getLuong());
				state.setDouble(6, luongNV.getLuongThuong());

				status = state.executeUpdate();
				if (status > 0)
					return luongNV; 

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
	public LuongNhanVien capNhatLuongNhanVien(LuongNhanVien luongNV) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"UPDATE LuongNhanVien SET Thang = ?, Nam = ?, NgayTinhLuong = ?,  Luong = ?, LuongThuong = ? \n");
				query.append("WHERE MaLuong = ?");
				state = connect.prepareStatement(query.toString());

				state.setString(6, luongNV.getMaLuong());
				state.setInt(1, luongNV.getThang());
				state.setInt(2, luongNV.getNam());
				state.setDate(3, Date.valueOf(luongNV.getNgayTinhLuong()));
				state.setDouble(4, luongNV.getLuong());
				state.setDouble(5, luongNV.getLuongThuong());

				status = state.executeUpdate();
				if (status > 0)
					return luongNV;

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
	public List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maNhanVien, int thang, int nam) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<Map<String, Object>> chiTiets = new ArrayList<Map<String, Object>>();
		if (conn != null) {
			try {
				// Câu truy vấn
				StringBuilder query = new StringBuilder(
						"SELECT CCNV.MaChamCong, CL.TenCa, CCNV.NgayChamCong, CCNV.TrangThai AS N'TrangThaiDiLam', NV.MaNhanVien ");
				query.append("FROM ChamCongNhanVien AS CCNV \n");
				query.append("JOIN CaLam AS CL ON CCNV.MaCa = CL.MaCa \n");
				query.append("JOIN NhanVien AS NV ON CCNV.MaNhanVien = NV.MaNhanVien \n");
				query.append("JOIN ChucVu AS CV ON NV.MaChucVu = CV.MaChucVu\n");
				query.append("JOIN TrinhDo AS TD ON NV.MaTrinhDo = TD.MaTrinhDo\n");
				query.append("JOIN PhongBan AS PB ON NV.MaPhongBan = PB.MaPhongBan\n");
				query.append("WHERE CCNV.MaNhanVien = '" + maNhanVien + "'");
				query.append(" AND MONTH(CCNV.NgayChamCong) = " + thang + " AND YEAR(CCNV.NgayChamCong) = " + nam);

				statement = conn.createStatement();
				rs = statement.executeQuery(query.toString());

				while (rs.next()) {
					Map<String, Object> chiTiet = new HashMap<String, Object>();
					chiTiet.put("MaChamCong", rs.getString("MaChamCong"));
					chiTiet.put("MaNhanVien", rs.getString("MaNhanVien"));
					chiTiet.put("NgayChamCong", rs.getDate("NgayChamCong").toLocalDate());
					chiTiet.put("CaLam", rs.getString("TenCa"));
					String trangThai = "";
					if (rs.getInt("TrangThaiDiLam") == 0) {
						trangThai = "Nghỉ";
					} else if (rs.getInt("TrangThaiDiLam") == 1) {
						trangThai = "Có mặt";
					} else {
						trangThai = "Đi trễ";
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
	public Map<String, Double> thongKeLuongNhanVienTheoThang(int nam) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		Map<String, Double> results = new HashMap<String, Double>();
		if (conn != null) {
			try {
				StringBuilder query = new StringBuilder(String.format(
						"SELECT Nam, Thang, SUM(Luong) AS 'Luong' FROM LuongNhanVien WHERE Nam = %d GROUP BY Nam, Thang ORDER BY Nam, Thang",
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
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE LuongNhanVien SET LuongThuong = ? ");
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
			}
		}
	}

	@Override
	public int demSoLuongDiLamTreCuaNhanVien(String maNhanVien, int thang, int nam) {
		Connection connect = getConnection();
		Statement state = null;
		int soLuongDiTre = 0;
		ResultSet rs = null;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT 'SoLuongDiTre' = COUNT(*) FROM ChamCongNhanVien\n");
				query.append("WHERE MaNhanVien = '" + maNhanVien + "' \n");
				query.append("AND TrangThai != 0 \n");
				query.append("AND MONTH(NgayChamCong) = " + thang + " \n");
				query.append("AND YEAR(NgayChamCong) = " + nam + " \n");
				query.append("AND MaCa IN('SA', 'CH', 'TO') \n");
				query.append("AND TrangThai = 2");

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					soLuongDiTre = rs.getInt("SoLuongDiTre");
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
		return soLuongDiTre;
	}

}
