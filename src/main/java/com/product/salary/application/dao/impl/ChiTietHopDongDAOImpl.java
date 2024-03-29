package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.dao.ChiTietHopDongDAO;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHopDongDAOImpl extends AbstractDAO implements ChiTietHopDongDAO, Serializable {

	@Override
	public List<ChiTietHopDong> themDanhSachChiTietHopDong(List<ChiTietHopDong> chiTietHopDongs) {
		Connection connect = getConnection();
		PreparedStatement state = null;

		if (connect != null) {
			try {
				for (ChiTietHopDong chiTietHopDong : chiTietHopDongs) {
					StringBuilder query = new StringBuilder(
							"INSERT INTO ChiTietHopDong (MaHopDong, MaSanPham, SoLuong, GiaDatLam)");
					query.append(" VALUES(?, ?, ?, ?)");

					state = connect.prepareStatement(query.toString());

					state.setString(1, chiTietHopDong.getHopDong().getMaHopDong());
					state.setString(2, chiTietHopDong.getSanPham().getMaSanPham());
					state.setInt(3, chiTietHopDong.getSoLuong());
					state.setDouble(4, chiTietHopDong.getGiaDatLam());

					state.executeUpdate();
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
			}
		}
		return chiTietHopDongs;
	}

	@Override
	public List<ChiTietHopDong> timTatCaChiTietHopDongBangMaHopDong(String maHopDong) {
		List<ChiTietHopDong> chiTietHopDongs = new ArrayList<ChiTietHopDong>();
		Connection connect = getConnection();
		ResultSet rs = null;
		Statement state = null;
		if (connect != null) {
			try {
				String query = String.format("SELECT *FROM ChiTietHopDong AS CT JOIN SanPham AS SP ON CT.MaSanPham = SP.MaSanPham WHERE MaHopDong = '%s'", maHopDong);

				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					SanPham sanPham = new SanPham(rs.getString("MaSanPham"), rs.getString("TenSanPham"), rs.getDouble("DonGia"));
					HopDong hopDong = new HopDong(rs.getString("MaHopDong"));
					int soLuong = rs.getInt("SoLuong");
					double giaDatLam = rs.getDouble("GiaDatLam");
					ChiTietHopDong chiTietHopDong = new ChiTietHopDong(hopDong, sanPham, soLuong, giaDatLam);
					chiTietHopDongs.add(chiTietHopDong);
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

		return chiTietHopDongs;
	}

}
