package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.ChiTietHopDongDAO;
import com.product.salary.application.dao.HopDongDAO;
import com.product.salary.application.dao.SanPhamDAO;
import com.product.salary.application.dao.impl.ChiTietHopDongDAOImpl;
import com.product.salary.application.dao.impl.HopDongDAOImpl;
import com.product.salary.application.dao.impl.SanPhamDAOImpl;
import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.product.salary.application.service.HopDongService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HopDongServiceImpl implements HopDongService {
	private HopDongDAO hopDongDAO;
	private ChiTietHopDongDAO chiTietHopDongDAO;
	private SanPhamDAO sanPhamDAO;

	public HopDongServiceImpl() {
		this.hopDongDAO = new HopDongDAOImpl();
		this.chiTietHopDongDAO = new ChiTietHopDongDAOImpl();
		this.sanPhamDAO = new SanPhamDAOImpl();
	}

	@Override
	public List<HopDong> timTatCaHopDong() {
		List<HopDong> hopDongs = new ArrayList<HopDong>();
		try {
			return this.hopDongDAO.timTatCaHopDong();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return hopDongs;
	}

	@Override
	public HopDong themHopDong(HopDong hopDongNew, List<ChiTietHopDong> chiTietHopDongs) {
		try {
			// Kiểm tra hợp đồng tồn tại
			HopDong isExitst = this.hopDongDAO.timHopDongBangMaHopDong(hopDongNew.getMaHopDong());
			if (isExitst != null) {
				JOptionPane.showMessageDialog(null, "Hợp đồng đã tồn tại!");
				return null;
			}

			// Thêm hợp đồng
			hopDongNew.setTrangThai(false);

			return this.hopDongDAO.themHopDong(hopDongNew, chiTietHopDongs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean thanhLyHopDong(String maHopDong) {
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
			// Kiểm tra số lượng tồn của sản phẩm
			List<ChiTietHopDong> chiTietHopDongs = this.chiTietHopDongDAO
					.timTatCaChiTietHopDongBangMaHopDong(maHopDong);
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
	public HopDong timHopDongBangMaHopDong(String maHopDong) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateMaHopDong() {
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
	public HopDong capNhatHopDong(HopDong hopDong) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean capNhatTrangThaiHopDong(String maHopDong, boolean trangThai) {
		// TODO Auto-generated method stub
		return false;
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
}
