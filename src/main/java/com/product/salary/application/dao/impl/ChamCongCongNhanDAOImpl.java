package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.*;
import com.product.salary.application.dao.ChamCongCongNhanDAO;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ChamCongCongNhanDAOImpl extends AbstractDAO implements ChamCongCongNhanDAO, Serializable {

	@Override
	public List<CongNhan> timDanhSachCongNhanDiLamBangThangVaNam(int thang, int nam) {
		List<CongNhan> congNhans = new ArrayList<CongNhan>();
		Connection connect = getConnection();
		ResultSet rs = null;
		Statement state = null;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder("SELECT DISTINCT PCCN.MaCongNhan").append(String.format(
						" FROM (SELECT *FROM ChamCongCongNhan WHERE YEAR(NgayChamCong) = %d AND MONTH(NgayChamCong) = %d) AS CCCN",
						nam, thang)).append(" JOIN PhanCongCongNhan AS PCCN")
						.append(" ON CCCN.MaPhanCong = PCCN.MaPhanCong");

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					CongNhan congNhan = new CongNhan();
					congNhan.setMaCongNhan(rs.getString("MaCongNhan"));
					congNhans.add(congNhan);
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

		return congNhans;
	}
//
//	@Override
//	public List<ChamCongCongNhan> timtatCaChamCongCongNhanBangThangVaNam(int thang, int nam) {
//		Connection conn = getConnection();
//		Statement statement = null;
//		ResultSet rs = null;
//		List<ChamCongCongNhan> chamCongCongNhans = new ArrayList<>();
//		if (conn != null) {
//			try {
//				StringBuilder query = new StringBuilder("SELECT *FROM ChamCongCongNhan AS CCCN");
//				query.append(" JOIN PhanCongCongNhan AS PCCN");
//				query.append(" ON CCCN.MaPhanCong = PCCN.MaPhanCong");
//				query.append(" JOIN CongDoanSanPham AS CDSP");
//				query.append(" ON CDSP.MaCongDoan = PCCN.MaCongDoan");
//				query.append(String.format(" WHERE YEAR(CCCN.NgayChamCong) = %d AND MONTH(CCCN.NgayChamCong) = %d", nam,
//						thang));
//
//				statement = conn.createStatement();
//				rs = statement.executeQuery(query.toString());
//
//				while (rs.next()) {
//
//					String maChamCong = rs.getString("MaChamCong");
//					String maPhanCong = rs.getString("MaPhanCong");
//					int soLuongHoanThanh = rs.getInt("SoLuongHoanThanh");
//					LocalDate ngayChamCong = rs.getDate("NgayChamCong").toLocalDate();
//					int trangThai = rs.getInt("TrangThai");
//					String maCa = rs.getString("MaCa");
//					String maLuong = rs.getString("MaLuong");
//					String maCongNhan = rs.getString("MaCongNhan");
//
//					String maCongDoan = rs.getString("MaCongDoan");
//					String tenCongDoan = rs.getString("TenCongDoan");
//					Double giaCongDoan = rs.getDouble("GiaCongDoan");
//
//					CongDoanSanPham congDoanSanPham = new CongDoanSanPham();
//					congDoanSanPham.setMaCongDoan(maCongDoan);
//					congDoanSanPham.setTenCongDoan(tenCongDoan);
//					congDoanSanPham.setGiaCongDoan(giaCongDoan);
//
//					PhanCongCongNhan phanCongCongNhan = new PhanCongCongNhan();
//					phanCongCongNhan.setMaPhanCong(maPhanCong);
//					CongNhan congNhan = new CongNhan();
//					congNhan.setMaCongNhan(maCongNhan);
//					phanCongCongNhan.setCongNhan(congNhan);
//					phanCongCongNhan.setCongDoanSanPham(congDoanSanPham);
//
//					CaLam caLam = new CaLam();
//					caLam.setMaCa(maCa);
//
//					LuongCongNhan luongCongNhan = new LuongCongNhan();
//					luongCongNhan.setMaLuong(maLuong);
//
//					ChamCongCongNhan chamCongCongNhan = new ChamCongCongNhan(maChamCong, phanCongCongNhan,
//							soLuongHoanThanh, caLam, ngayChamCong, trangThai, luongCongNhan);
//					chamCongCongNhans.add(chamCongCongNhan);
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if (conn != null) {
//					try {
//						conn.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//				if (statement != null) {
//					try {
//						statement.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		return chamCongCongNhans;
//	}

	@Override
	public boolean capNhatChamCongCongNhanBangMaCongNhanVaThangVaNam(String maCongNhan, int thang, int nam,
			String maLuong) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(String.format("UPDATE ChamCongCongNhan SET MaLuong = ?"));
				query.append(" WHERE MaChamCong IN");
				query.append(" ( ");
				query.append(
						" SELECT CCCN.MaChamCong FROM (SELECT *FROM ChamCongCongNhan WHERE YEAR(NgayChamCong) = ? AND MONTH(NgayChamCong) = ?) AS CCCN");
				query.append(" JOIN PhanCongCongNhan AS PCCN");
				query.append(" ON CCCN.MaPhanCong = PCCN.MaPhanCong");
				query.append(" WHERE MaCongNhan = ?");
				query.append(" ) ");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, maLuong);
				statement.setInt(2, nam);
				statement.setInt(3, thang);
				statement.setString(4, maCongNhan);
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
	public List<ChamCongCongNhan> timTatCaChamCongCongNhan(LocalDate ngayChamCong) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<ChamCongCongNhan> dsChamCong = new ArrayList<ChamCongCongNhan>();
		if (connect != null) {
			try {
				// Câu truy vấn
				StringBuilder query = 
						new StringBuilder("SELECT * FROM ChamCongCongNhan cc JOIN PhanCongCongNhan pc ON cc.MaPhanCong = pc.MaPhanCong JOIN CongNhan cn ON pc.MaCongNhan = cn.MaCongNhan JOIN CongDoanSanPham cd ON pc.MaCongDoan = cd.MaCongDoan JOIN SanPham sp ON cd.MaSanPham = sp.MaSanPham JOIN CaLam c ON c.MaCa = cc.MaCa WHERE NgayChamCong = '"+ ngayChamCong + "'\r\n"
								+ "");

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				while (rs.next()) {
					// Chấm công
					String maChamCong = rs.getString("MaChamCong");
					LocalDate ngayCham = rs.getDate("NgayChamCong").toLocalDate();
					int soLuongHoanThanh = rs.getInt("SoLuongHoanThanh");
					int trangThaiDiLam = rs.getInt("TrangThai");

					// Ca làm
					String maCa = rs.getString("MaCa");
					String tenCa = rs.getString("TenCa");
					CaLam caLam = new CaLam(maCa, tenCa);

					// Sản phẩm
					String maSanPham = rs.getString("MaSanPham");
					String tenSanPham = rs.getString("TenSanPham");
					SanPham sp = new SanPham(maSanPham, tenSanPham);

					// Công đoạn sản phẩm
					String maCongDoan = rs.getString("MaCongDoan");
					String tenCongDoan = rs.getString("TenCongDoan");
					CongDoanSanPham congDoan = new CongDoanSanPham(maCongDoan, tenCongDoan, sp);

					// Công nhân
					String maCongNhan = rs.getString("MaCongNhan");
					String tenCongNhan = rs.getString("HoTen");
					int gioiTinh = rs.getInt("GioiTinh");
					String soDienThoai = rs.getString("SoDienThoai");
					CongNhan congNhan = new CongNhan(maCongNhan, tenCongNhan, gioiTinh, soDienThoai);

					// Phân công
					String maPhanCong = rs.getString("MaPhanCong");
					PhanCongCongNhan phanCong = new PhanCongCongNhan(maPhanCong, congNhan, congDoan);

					ChamCongCongNhan chamCongCongNhan = new ChamCongCongNhan(maChamCong, phanCong, soLuongHoanThanh,
							caLam, ngayCham, trangThaiDiLam, null);
					dsChamCong.add(chamCongCongNhan);
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
		return dsChamCong;
	}

	@Override
	public List<CongNhan> timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(LocalDate ngayChamCong, String caLam) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<CongNhan> dsCongNhanChuaChamCong = new ArrayList<CongNhan>();
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"SELECT DISTINCT cn.MaCongNhan, cn.HoTen, GioiTinh, SoDienThoai  FROM CongNhan cn \r\n" + " ");
				query.append(" WHERE cn.TrangThai = 1  AND cn.MaCongNhan NOT IN ( ");
				query.append(
						" SELECT pc.MaCongNhan FROM ChamCongCongNhan cc JOIN PhanCongCongNhan pc ON cc.MaPhanCong = pc.MaPhanCong ");
				query.append(" WHERE NgayChamCong = '" + ngayChamCong + "'");
				query.append(" AND MaCa = '" + caLam + "' )");
				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				System.out.println(query);
				while (rs.next()) {
					String maCongNhan = rs.getString("MaCongNhan");
					String tenCongNhan = rs.getString("HoTen");
					int gioiTinh = rs.getInt("GioiTinh");
					String soDienThoai = rs.getString("SoDienThoai");

					CongNhan congNhan = new CongNhan(maCongNhan, tenCongNhan, gioiTinh, soDienThoai);
					dsCongNhanChuaChamCong.add(congNhan);
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
		return dsCongNhanChuaChamCong;
	}

	@Override
	public ChamCongCongNhan themChamCongCongNhan(ChamCongCongNhan chamCong) {
		Connection connect = getConnection();
		PreparedStatement state = null;
		int status = 0;
		if (connect != null) {
			try {
				StringBuilder query = new StringBuilder(
						"INSERT INTO [dbo].[ChamCongCongNhan]([MaChamCong],[MaPhanCong] ,[SoLuongHoanThanh],[NgayChamCong],[TrangThai],[MaCa],[MaLuong]) \n");
				query.append("VALUES(?, ?, ?, ?, ?, ?, null)");
				state = connect.prepareStatement(query.toString());

				state.setString(1, chamCong.getMaChamCong());
				state.setString(2, chamCong.getPhanCongCongNhan().getMaPhanCong());
				state.setInt(3, chamCong.getSoLuongHoanThanh());
				state.setDate(4, Date.valueOf(chamCong.getNgayChamCong()));
				state.setInt(5, chamCong.getTrangThai());
				state.setString(6, chamCong.getCaLam().getMaCa());

				status = state.executeUpdate();

				if (status > 0)
					return chamCong;
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
			}
		}
		return null;
	}

	@Override
	public ChamCongCongNhan timKiemBangMaChamCongCongNhan(String maChamCong) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		ChamCongCongNhan chamCong = null;
		if (connect != null) {
			try {
				// Câu truy vấn
				StringBuilder query = new StringBuilder(
						"SELECT * FROM ChamCongCongNhan cc JOIN PhanCongCongNhan pc ON cc.MaPhanCong = pc.MaPhanCong JOIN CongNhan cn ON pc.MaCongNhan = cn.MaCongNhan JOIN CongDoanSanPham cd ON pc.MaCongDoan = cd.MaCongDoan JOIN SanPham sp ON cd.MaSanPham = sp.MaSanPham JOIN CaLam c ON c.MaCa = cc.MaCa WHERE cc.MaChamCong = '"
								+ maChamCong + "'");

				state = connect.createStatement();
				rs = state.executeQuery(query.toString());
				//System.out.println("Tim kiem: " + query);
				while (rs.next()) {
					// Chấm công
					LocalDate ngayChamCong = rs.getDate("NgayChamCong").toLocalDate();
					int soLuongHoanThanh = rs.getInt("SoLuongHoanThanh");
					int trangThaiDiLam = rs.getInt("TrangThai");

					// Ca làm
					String maCa = rs.getString("MaCa");
					String tenCa = rs.getString("TenCa");
					CaLam caLam = new CaLam(maCa, tenCa);

					// Sản phẩm
					String maSanPham = rs.getString("MaSanPham");
					String tenSanPham = rs.getString("TenSanPham");
					SanPham sp = new SanPham(maSanPham, tenSanPham);

					// Công đoạn sản phẩm
					String maCongDoan = rs.getString("MaCongDoan");
					String tenCongDoan = rs.getString("TenCongDoan");
					int soLuongCanLam = rs.getInt("SoLuongCanLam");
					CongDoanSanPham congDoan = new CongDoanSanPham(maCongDoan, tenCongDoan, sp, soLuongCanLam);

					// Công nhân
					String maCongNhan = rs.getString("MaCongNhan");
					String tenCongNhan = rs.getString("HoTen");
					int gioiTinh = rs.getInt("GioiTinh");
					String soDienThoai = rs.getString("SoDienThoai");
					CongNhan congNhan = new CongNhan(maCongNhan, tenCongNhan, gioiTinh, soDienThoai);

					// Phân công
					String maPhanCong = rs.getString("MaPhanCong");
					PhanCongCongNhan phanCong = new PhanCongCongNhan(maPhanCong, congNhan, congDoan);

					chamCong = new ChamCongCongNhan(maChamCong, phanCong, soLuongHoanThanh, caLam, ngayChamCong,
							trangThaiDiLam, null);
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
		return chamCong;
	}

	@Override
	public String timKiemMaChamCongCongNhanCuoiCungTheoNgayVaCaLam(LocalDate ngayChamCong, String caLam) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		String maChamCongCuoiCung = null;
		if (connect != null) {
			try {
				String maCa = caLam.equals("Sáng") ? "SA" : caLam.equals("Chiều") ? "CH" : "TO";

				String query = "SELECT TOP 1 MaChamCong FROM ChamCongCongNhan WHERE NgayChamCong = ' " +ngayChamCong + "' AND MaCa = '"+ maCa + "' ORDER BY MaChamCong DESC";

				state = connect.createStatement();
				rs = state.executeQuery(query);
				while (rs.next()) {
					maChamCongCuoiCung = rs.getString("MaChamCong");
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
		return maChamCongCuoiCung;
	}

	@Override
	public boolean capNhatChamCongCongNhan(String maChamCong, int trangThai, int soLuongHoanThanh) {
		Connection connect = getConnection();
		Statement state = null;
		int status = 0;
		if (connect != null) {
			try {
				String query = "UPDATE [dbo].[ChamCongCongNhan] SET [SoLuongHoanThanh] = " +soLuongHoanThanh + " , TrangThai = " + trangThai + " WHERE MaChamCong = '"
						+ maChamCong + "'";
				state = connect.createStatement();
				status = state.executeUpdate(query);
				if (status > 0)
					return true;
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
			}
		}
		return false;
	}

	@Override
	public List<ChamCongCongNhan> timtatCaChamCongCongNhanBangThangVaNam(int thang, int nam) {
		// TODO Auto-generated method stub
		return null;
	}
		
}
