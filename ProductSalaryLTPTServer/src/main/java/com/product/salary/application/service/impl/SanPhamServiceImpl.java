
package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.SanPhamDAO;
import com.product.salary.application.dao.impl.SanPhamDAOImpl;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.SanPhamService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamServiceImpl implements SanPhamService {
	private final SanPhamDAO sanPhamDao;

	public SanPhamServiceImpl() {
		this.sanPhamDao = new SanPhamDAOImpl();
	}

	@Override
	public List<SanPham> timKiemTatCaSanPham() {
		List<SanPham> sanPhams = new ArrayList<>();
		try {

			sanPhams = sanPhamDao.timKiemTatCaSanPham();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return sanPhams;
	}

	@Override
	public SanPham capNhatSanPham(SanPham sanPham) {
		try {
			SanPham isExists = sanPhamDao.timKiemBangMaSanPham(sanPham.getMaSanPham());
			if (isExists == null) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("sanPham.thongBao.sanPhamKhongTonTai"));
				return null;
			}
			isExists.setDonGia(sanPham.getDonGia());
			isExists.setTenSanPham(sanPham.getTenSanPham());
			isExists.setChatLieu(sanPham.getChatLieu());
			isExists.setTrangThai(sanPham.getTrangThai());
			isExists.setHinhAnh(sanPham.getHinhAnh());

			return sanPhamDao.capNhatSanPham(isExists);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean capNhatTrangThaiSanPham(String maSanPham, boolean trangThai) {
		try {
			SanPham isExists = sanPhamDao.timKiemBangMaSanPham(maSanPham);
			if (isExists == null) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("sanPham.thongBao.sanPhamDaTonTai"));
				return false;
			}

			return sanPhamDao.capNhatTrangThaiSanPham(maSanPham, trangThai);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<SanPham> timKiemSanPham(SanPham sanPham) {
		List<SanPham> sanPhams = new ArrayList<SanPham>();
		try {

			sanPhams = sanPhamDao.timKiemSanPham(sanPham);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return sanPhams;
	}

	@Override
	public SanPham themSanPham(SanPham sanPham) {
		try {
			SanPham isExists = sanPhamDao.timKiemBangMaSanPham(sanPham.getMaSanPham());
			if (isExists != null) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("sanPham.thongBao.sanPhamDaTonTai"));
				return null;
			}

			sanPham.setTrangThai(true);
			sanPham.setSoCongDoan(0);
			return sanPhamDao.themSanPham(sanPham);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String generateMaSanPham() {
		// 300001
		String rs = "300001";
		String maSanPham = sanPhamDao.timMaSanPhamCuoiCung();
		if (maSanPham != null) {
			String maCuoi = maSanPham.substring(2);
			int maMoi = Integer.parseInt(maCuoi);
			rs = String.format("30%04d", maMoi + 1);
		}
		return rs;
	}

	@Override
	public List<SanPham> timTatCaSanPhamDangSanXuat() {
		List<SanPham> sanPhams = new ArrayList<SanPham>();
		try {

			sanPhams = sanPhamDao.timTatCaSanPhamDangSanXuat();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return sanPhams;
	}

	@Override
	public int tongSoLuongSanPham() {
		int soLuong = 0;
		try {
			soLuong = this.sanPhamDao.tongSoLuongSanPham();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return soLuong;
	}

	@Override
	public List<SanPham> themNhieuSanPham(List<SanPham> sanPhams) {
		List<SanPham> sanPhamThems = new ArrayList<SanPham>();
		try {
			for (SanPham sanPham : sanPhams) {

				String maSanPham = generateMaSanPham();
				sanPham.setMaSanPham(maSanPham);

				SanPham sanPhamDaThem = themSanPham(sanPham);
				if (sanPhamDaThem != null) {
					sanPhamThems.add(sanPhamDaThem);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return sanPhamThems;
	}
}
