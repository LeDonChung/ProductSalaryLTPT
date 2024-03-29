package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.SanPham;
import com.product.salary.application.dao.SanPhamDAO;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAOImpl extends AbstractDAO implements SanPhamDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<SanPham> timKiemTatCaSanPham() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<SanPham> sanPhams = new ArrayList<>();
		if (conn != null) {
			try {
				String query = "SELECT *FROM SanPham";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maSanPham = rs.getString("MaSanPham");
					String tenSanPham = rs.getString("TenSanPham");
					int soLuongTon = rs.getInt("SoLuongTon");
					byte[] hinhAnh = rs.getBytes("HinhAnh");
					String chatLieu = rs.getString("ChatLieu");
					String donViTinh = rs.getString("DonViTinh");
					int soCongDoan = rs.getInt("SoCongDoan");
					double donGia = rs.getDouble("DonGia");
					boolean trangThai = rs.getBoolean("TrangThai");

					SanPham sanPham = new SanPham(maSanPham, tenSanPham, soLuongTon, hinhAnh, chatLieu, donViTinh,
							soCongDoan, donGia, trangThai);

					sanPhams.add(sanPham);
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
		return sanPhams;
	}

	@Override
	public SanPham capNhatSanPham(SanPham sanPham) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(
						"UPDATE [dbo].[SanPham] SET [TenSanPham] = ?, [SoLuongTon] = ?, [HinhAnh] = ?, [ChatLieu] = ?, [DonViTinh] = ?, [TrangThai] = ?, [DonGia] = ?");
				query.append(" WHERE [MaSanPham] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, sanPham.getTenSanPham());
				statement.setInt(2, sanPham.getSoLuongTon());
				statement.setBytes(3, sanPham.getHinhAnh());
				statement.setString(4, sanPham.getChatLieu());
				statement.setString(5, sanPham.getDonViTinh());
				statement.setBoolean(6, sanPham.isTrangThai());
				statement.setDouble(7, sanPham.getDonGia());
				statement.setString(8, sanPham.getMaSanPham());

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật thành công
				if (status > 0) {
					return sanPham;
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
	public boolean capNhatTrangThaiSanPham(String maSanPham, boolean trangThai) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE [dbo].[SanPham] SET [TrangThai] = ?");
				query.append(" WHERE [MaSanPham] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setBoolean(1, trangThai);
				statement.setString(2, maSanPham);

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì cập nhật trạng thái thành công
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
	public List<SanPham> timKiemSanPham(SanPham search) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<SanPham> sanPhams = new ArrayList<>();
		if (conn != null) {
			try {
				String conditions = getConditions(search);

				String query = "SELECT *FROM SanPham" + conditions;
				System.out.println(query);
				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {

					String maSanPham = rs.getString("MaSanPham");
					String tenSanPham = rs.getString("TenSanPham");
					int soLuongTon = rs.getInt("SoLuongTon");
					byte[] hinhAnh = rs.getBytes("HinhAnh");
					String chatLieu = rs.getString("ChatLieu");
					String donViTinh = rs.getString("DonViTinh");
					int soCongDoan = rs.getInt("SoCongDoan");
					double donGia = rs.getDouble("DonGia");
					boolean trangThai = rs.getBoolean("TrangThai");

					SanPham sanPham = new SanPham(maSanPham, tenSanPham, soLuongTon, hinhAnh, chatLieu, donViTinh,
							soCongDoan, donGia, trangThai);

					sanPhams.add(sanPham);
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
		return sanPhams;
	}

	private String getConditions(SanPham search) {
		// Câu truy vấn
		StringBuilder query = new StringBuilder("");

		// Các điều kiện của câu truy vấn
		List<String> conditions = new ArrayList<String>();

		// Mã sản phẩm
		conditions.add(!StringUtils.isBlank(search.getMaSanPham())
				? String.format("MaSanPham LIKE N'%%%s%%'", search.getMaSanPham())
				: "");

		// Đơn vị tính
		conditions.add(!StringUtils.isBlank(search.getDonViTinh())
				? String.format("DonViTinh LIKE N'%%%s%%'", search.getDonViTinh())
				: "");

		// Tên sản phẩm
		conditions.add(!StringUtils.isBlank(search.getTenSanPham())
				? String.format("TenSanPham LIKE N'%%%s%%'", search.getTenSanPham())
				: "");

		// Trạng thái
		if (search.isTrangThai() != null) {
			conditions.add(String.format("TrangThai = %d", search.isTrangThai() ? 1 : 0));
		}

		// Chất liệu
		conditions.add(!StringUtils.isBlank(search.getChatLieu())
				? String.format("ChatLieu LIKE N'%%%s%%'", search.getChatLieu())
				: "");

		// Đơn giá không null
		if (search.getDonGia() != null) {
			conditions.add(search.getDonGia() >= 0 ? String.format("DonGia >= %f", search.getDonGia()) : "");
		}

		// Xóa các điều kiện rỗng
		conditions.removeIf((v) -> v.equals(""));

		if (!conditions.isEmpty()) {
			String conditionValue = StringUtils.join(conditions, " AND ");
			query.append(String.format(" WHERE %s", conditionValue));
		}

		return query.toString();
	}

	@Override
	public SanPham themSanPham(SanPham sanPham) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder(
						"INSERT INTO [dbo].[SanPham] ([MaSanPham], [TenSanPham], [SoLuongTon], [HinhAnh], [ChatLieu], [DonViTinh], [TrangThai], [DonGia])");
				query.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setString(1, sanPham.getMaSanPham());
				statement.setString(2, sanPham.getTenSanPham());
				statement.setInt(3, sanPham.getSoLuongTon());
				statement.setBytes(4, sanPham.getHinhAnh());
				statement.setString(5, sanPham.getChatLieu());
				statement.setString(6, sanPham.getDonViTinh());
				statement.setBoolean(7, sanPham.isTrangThai());
				statement.setDouble(8, sanPham.getDonGia());

				int status = statement.executeUpdate();

				// Nếu số dòng lớn hơn 0 thì thêm thành công
				if (status > 0) {
					return sanPham;
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
	public SanPham timKiemBangMaSanPham(String maSanPhamSeach) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		SanPham sanPham = null;
		if (conn != null) {
			try {
				String query = "SELECT *FROM SanPham WHERE MaSanPham = " + maSanPhamSeach;

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maSanPham = rs.getString("MaSanPham");
					String tenSanPham = rs.getString("TenSanPham");
					int soLuongTon = rs.getInt("SoLuongTon");
					byte[] hinhAnh = rs.getBytes("HinhAnh");
					String chatLieu = rs.getString("ChatLieu");
					String donViTinh = rs.getString("DonViTinh");
					int soCongDoan = rs.getInt("SoCongDoan");
					double donGia = rs.getDouble("DonGia");
					boolean trangThai = rs.getBoolean("TrangThai");

					sanPham = new SanPham(maSanPham, tenSanPham, soLuongTon, hinhAnh, chatLieu, donViTinh, soCongDoan,
							donGia, trangThai);

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
		return sanPham;
	}

	@Override
	public String timMaSanPhamCuoiCung() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		String maSanPham = null;
		if (conn != null) {
			try {
				String query = "SELECT TOP 1 MaSanPham FROM SanPham ORDER BY MaSanPham DESC";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					maSanPham = rs.getString("MaSanPham");
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
		return maSanPham;
	}

	@Override
	public List<SanPham> timTatCaSanPhamDangSanXuat() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<SanPham> sanPhams = new ArrayList<>();
		if (conn != null) {
			try {
				String query = "SELECT *FROM SanPham WHERE TrangThai = 1";

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					String maSanPham = rs.getString("MaSanPham");
					String tenSanPham = rs.getString("TenSanPham");
					int soLuongTon = rs.getInt("SoLuongTon");
					byte[] hinhAnh = rs.getBytes("HinhAnh");
					String chatLieu = rs.getString("ChatLieu");
					String donViTinh = rs.getString("DonViTinh");
					int soCongDoan = rs.getInt("SoCongDoan");
					double donGia = rs.getDouble("DonGia");
					boolean trangThai = rs.getBoolean("TrangThai");

					SanPham sanPham = new SanPham(maSanPham, tenSanPham, soLuongTon, hinhAnh, chatLieu, donViTinh,
							soCongDoan, donGia, trangThai);

					sanPhams.add(sanPham);
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
		return sanPhams;
	}

	@Override
	public void capNhatSoLuongCongDoan(String maSanPham, int i) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {

				StringBuilder query = new StringBuilder("UPDATE [dbo].[SanPham] SET [SoCongDoan] = ?");
				query.append(" WHERE [MaSanPham] = ?");

				statement = conn.prepareStatement(query.toString());

				// set statement
				statement.setInt(1, i);
				statement.setString(2, maSanPham);

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

	@Override
	public boolean kiemTraTonKho(String maSanPham, int soLuongKiemTra) {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				String query = String.format("SELECT SoLuongTon FROM SanPham WHERE MaSanPham = '%s'", maSanPham);

				statement = conn.createStatement();
				rs = statement.executeQuery(query);

				while (rs.next()) {
					int soLuongTon = rs.getInt("SoLuongTon");
					if (soLuongKiemTra <= soLuongTon) {
						return true;
					}
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
	
	@Override
	public int tongSoLuongSanPham() {
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;
		int soLuong = 0;
		if (conn != null) {
			try {
				String query = "SELECT COUNT(*) FROM SanPham";
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
