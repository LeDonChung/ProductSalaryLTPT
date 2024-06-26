package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.HopDongDAO;
import com.product.salary.application.dao.SanPhamDAO;
import com.product.salary.application.dao.impl.HopDongDAOImpl;
import com.product.salary.application.dao.impl.SanPhamDAOImpl;
import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.HopDongService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class HopDongServiceImpl implements HopDongService {
	private final HopDongDAO hopDongDAO;
	private final SanPhamDAO sanPhamDAO;

	public HopDongServiceImpl() {
		this.hopDongDAO = new HopDongDAOImpl();
		this.sanPhamDAO = new SanPhamDAOImpl();
	}

	@Override
	public List<HopDong> timTatCaHopDong() {
		List<HopDong> hopDongs = new ArrayList<HopDong>();
		try {
			hopDongs = this.hopDongDAO.timTatCaHopDong();
			return hopDongs;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return hopDongs;
	}

	@Override
	public synchronized HopDong themHopDong(HopDong hopDongNew, List<ChiTietHopDong> chiTietHopDongs) {
		try {
			String maHopDong = generateMaHopDong();
			hopDongNew.setMaHopDong(maHopDong);
			// Kiểm tra hợp đồng tồn tại
			HopDong isExitst = this.hopDongDAO.timHopDongBangMaHopDong(hopDongNew.getMaHopDong());
			if (isExitst != null) {
				JOptionPane.showMessageDialog(null, "Hợp đồng đã tồn tại!");
				return null;
			}
			chiTietHopDongs = chiTietHopDongs.stream().map(chiTietHopDong -> {
				try {
					chiTietHopDong.setHopDong(new HopDong(maHopDong));
					return chiTietHopDong;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}).collect(Collectors.toList());

			// Thêm hợp đồng
			hopDongNew.setTrangThai(false);
			hopDongNew = this.hopDongDAO.themHopDong(hopDongNew);
			// Them chi tiet hop dong
			chiTietHopDongs.forEach(chiTietHopDong -> {
				this.hopDongDAO.themChiTietHopDong(chiTietHopDong);
			});
            return hopDongNew;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized boolean thanhLyHopDong(String maHopDong) {
		try {
			// Tìm hợp đồng xem có tồn tại không
			HopDong hopDong = this.hopDongDAO.timHopDongBangMaHopDong(maHopDong);
			if (hopDong == null) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("hopDong.thongBao.hopDongDaTonTai"));
				return false;
			}
			if (hopDong.isTrangThai()) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("hopDong.thongBao.hopDongDaThanhLy"));
				return false;
			}
			//  Kiểm tra số lượng tồn của sản phẩm
			List<ChiTietHopDong> chiTietHopDongs = timTatCaChiTietHopDongBangMaHopDong(maHopDong);

			for (ChiTietHopDong chiTietHopDong : chiTietHopDongs) {
				if (!this.sanPhamDAO.kiemTraTonKho(chiTietHopDong.getSanPham().getMaSanPham(),
						chiTietHopDong.getSoLuong())) {
					String message = "";
					if (SystemConstants.LANGUAGE == 0) {
						message = String.format("Số lượng sản phẩm %s không đủ.",
								chiTietHopDong.getSanPham().getTenSanPham());
					} else {
						message = String.format("The number of %s products is not enough.",
								chiTietHopDong.getSanPham().getTenSanPham());
					}
					JOptionPane.showMessageDialog(null, message);
					return false;
				}
			}
			// Thực hiện thanh lý
			boolean status = this.hopDongDAO.thanhLyHopDong(maHopDong);
			if (!status) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("hopDong.thongBao.thanhLyHopDongKhongThanhCong"));
				return false;
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public synchronized String generateMaHopDong() {
		StringBuilder maHopDong = new StringBuilder("50");
		int dayOfMonth = LocalDate.now().getDayOfMonth();
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		maHopDong.append(String.format("%02d", dayOfMonth));
		maHopDong.append(String.format("%02d", month));
		maHopDong.append(String.format("%02d", year % 100));

		String maHopDongCuoiCung = this.hopDongDAO.timMaHopDongCuoiCung();
		if (maHopDongCuoiCung != null) {
			int maHopDongCuoiCungValue = Integer.valueOf(maHopDongCuoiCung.substring(8));
			if (maHopDongCuoiCungValue == 99) {
				maHopDongCuoiCungValue = 1;
			} else {
				maHopDongCuoiCungValue++;
			}

			maHopDong.append(String.format("%02d", maHopDongCuoiCungValue));
		} else {
			maHopDong.append("01");
		}
		return maHopDong.toString();
	}

	@Override
	public int tongSoLuongHopDong() {
		int soLuong = 0;
		try {
			soLuong = this.hopDongDAO.tongSoLuongHopDong();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return soLuong;
	}

	@Override
	public List<ChiTietHopDong> timTatCaChiTietHopDongBangMaHopDong(String maHopDong) {
		List<ChiTietHopDong> chiTietHopDongs = new ArrayList<>();
		try {
			chiTietHopDongs = hopDongDAO.timTatCaChiTietHopDongBangMaHopDong(maHopDong);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return chiTietHopDongs;
	}
}
