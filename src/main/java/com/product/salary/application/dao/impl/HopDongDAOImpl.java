package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.product.salary.application.dao.ChiTietHopDongDAO;
import com.product.salary.application.dao.HopDongDAO;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HopDongDAOImpl extends AbstractDAO implements HopDongDAO, Serializable {

	private ChiTietHopDongDAO chiTietHopDongDAO;

	public HopDongDAOImpl() {
		chiTietHopDongDAO = new ChiTietHopDongDAOImpl();
	}

	@Override
	public List<HopDong> timTatCaHopDong() {
		List<HopDong> hopDongs = new ArrayList<HopDong>();
		Connection connect = getConnection();
		ResultSet rs = null;
		Statement state = null;
		if (connect != null) {
			try {
				String query = "SELECT *FROM HopDong";

				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					String maHopDong = rs.getString("MaHopDong");
					String tenHopDong = rs.getString("TenHopDong");
					String tenKhachHang = rs.getString("TenKhachHang");
					double tongTien = rs.getDouble("TongTien");
					double soTienCoc = rs.getDouble("SoTienCoc");
					LocalDate ngayBatDau = rs.getDate("NgayBatDau") == null ? null
							: rs.getDate("NgayBatDau").toLocalDate();
					LocalDate ngayKetThuc = rs.getDate("NgayKetThuc") == null ? null
							: rs.getDate("NgayKetThuc").toLocalDate();
					String yeuCau = rs.getString("YeuCau");
					boolean trangThai = rs.getBoolean("TrangThai");
					HopDong hopDong = new HopDong(maHopDong, tenHopDong, tenKhachHang, tongTien, soTienCoc, ngayBatDau,
							ngayKetThuc, yeuCau, trangThai);

					hopDongs.add(hopDong);
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

		return hopDongs;
	}

	@Override
	public HopDong themHopDong(HopDong hopDong, List<ChiTietHopDong> chiTietHopDongs) {
		Connection connect = getConnection();
		PreparedStatement stateHopDong = null;
		PreparedStatement stateChiTietHopDong = null;
		if (connect != null) {
			try {

				connect.setAutoCommit(false);

				// INSERT HopDong
				StringBuilder queryHopDong = new StringBuilder(
						"INSERT INTO HopDong ([MaHopDong], [TenHopDong], [TenKhachHang], [TongTien], [SoTienCoc], [NgayBatDau], [NgayKetThuc], [YeuCau], [TrangThai])");
				queryHopDong.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

				stateHopDong = connect.prepareStatement(queryHopDong.toString());

				stateHopDong.setString(1, hopDong.getMaHopDong());
				stateHopDong.setString(2, hopDong.getTenHopDong());
				stateHopDong.setString(3, hopDong.getTenKhachHang());
				stateHopDong.setDouble(4, hopDong.getTongTien());
				stateHopDong.setDouble(5, hopDong.getSoTienCoc());
				stateHopDong.setDate(6, Date.valueOf(hopDong.getNgayBatDau()));
				stateHopDong.setDate(7, null);
				stateHopDong.setString(8, hopDong.getYeuCau());
				stateHopDong.setBoolean(9, hopDong.isTrangThai());

				stateHopDong.executeUpdate();

				// INSERT ChiTietHopDong
				for (ChiTietHopDong chiTietHopDong : chiTietHopDongs) {
					StringBuilder queryChiTietHopDong = new StringBuilder(
							"INSERT INTO ChiTietHopDong (MaHopDong, MaSanPham, SoLuong, GiaDatLam)");
					queryChiTietHopDong.append(" VALUES(?, ?, ?, ?)");

					stateChiTietHopDong = connect.prepareStatement(queryChiTietHopDong.toString());

					stateChiTietHopDong.setString(1, hopDong.getMaHopDong());
					stateChiTietHopDong.setString(2, chiTietHopDong.getSanPham().getMaSanPham());
					stateChiTietHopDong.setInt(3, chiTietHopDong.getSoLuong());
					stateChiTietHopDong.setDouble(4, chiTietHopDong.getGiaDatLam());

					stateChiTietHopDong.executeUpdate();
				}

				// Commit
				connect.commit();
				return hopDong;

			} catch (SQLException e) {
				try {
					// Rollback
					connect.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					// Rollback
					connect.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stateHopDong != null) {
					try {
						stateHopDong.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (stateChiTietHopDong != null) {
					try {
						stateChiTietHopDong.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean thanhLyHopDong(String maHopDong) {
		Connection conn = getConnection();
		PreparedStatement statementHopDong = null;
		PreparedStatement statementSanPham = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				conn.setAutoCommit(false);

				List<ChiTietHopDong> chiTietHopDongs = this.chiTietHopDongDAO
						.timTatCaChiTietHopDongBangMaHopDong(maHopDong);

				// Thực hiện cập nhật số lượng trong kho sản phẩm
				for (ChiTietHopDong chiTietHopDong : chiTietHopDongs) {
					// Cập nhật số lượng sản phẩm
					StringBuilder query = new StringBuilder(
							"UPDATE [dbo].[SanPham] SET [SoLuongTon] = [SoLuongTon] - ?");
					query.append(" WHERE [MaSanPham] = ?");

					statementSanPham = conn.prepareStatement(query.toString());

					// set statement
					statementSanPham.setInt(1, chiTietHopDong.getSoLuong());
					statementSanPham.setString(2, chiTietHopDong.getSanPham().getMaSanPham());

					statementSanPham.executeUpdate();
				}

				// Cập nhật trạng thái và ngày kết thúc của hợp đồng
				StringBuilder query = new StringBuilder(
						"UPDATE [dbo].[HopDong] SET [TrangThai] = 1, [NgayKetThuc] = GETDATE()");
				query.append(" WHERE [MaHopDong] = ?");

				statementHopDong = conn.prepareStatement(query.toString());

				// set statement
				statementHopDong.setString(1, maHopDong);

				int status = statementHopDong.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật thành công
				if (status > 0) {
					conn.commit();
					return true;
				} else {
					conn.rollback();
				}

			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statementHopDong != null) {
					try {
						statementHopDong.close();
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
	public HopDong timHopDongBangMaHopDong(String maHopDongS) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		HopDong hopDong = null;
		if (connect != null) {
			try {
				String query = String.format("SELECT * FROM HopDong WHERE MaHopDong = '%s'", maHopDongS);
				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					String maHopDong = rs.getString("MaHopDong");
					String tenHopDong = rs.getString("TenHopDong");
					String tenKhachHang = rs.getString("TenKhachHang");
					double tongTien = rs.getDouble("TongTien");
					double soTienCoc = rs.getDouble("SoTienCoc");
					LocalDate ngayBatDau = rs.getDate("NgayBatDau") == null ? null
							: rs.getDate("NgayBatDau").toLocalDate();
					LocalDate ngayKetThuc = rs.getDate("NgayKetThuc") == null ? null
							: rs.getDate("NgayKetThuc").toLocalDate();
					String yeuCau = rs.getString("YeuCau");
					boolean trangThai = rs.getBoolean("TrangThai");
					hopDong = new HopDong(maHopDong, tenHopDong, tenKhachHang, tongTien, soTienCoc, ngayBatDau,
							ngayKetThuc, yeuCau, trangThai);
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
		return hopDong;

	}

	@Override
	public String timMaHopDongCuoiCung() {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		if (connect != null) {
			try {
				String query = String.format("SELECT TOP 1 MaHopDong FROM HopDong ORDER BY MaHopDong DESC");
				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					return rs.getString("MaHopDong");
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
		return null;
	}

	@Override
	public int tongSoLuongHopDong() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		int soLuong = 0;
		if (conn != null) {
			try {
				String query = "SELECT COUNT(*) FROM HopDong";
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
