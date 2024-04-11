package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.CongDoanSanPhamDAO;
import com.product.salary.application.dao.SanPhamDAO;
import com.product.salary.application.dao.impl.CongDoanSanPhamDAOImpl;
import com.product.salary.application.dao.impl.SanPhamDAOImpl;
import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.CongDoanSanPhamService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CongDoanSanPhamServiceImpl implements CongDoanSanPhamService {
	private final CongDoanSanPhamDAO congDoanSanPhamDAO;
	private final SanPhamDAO sanPhamDAO;

	public CongDoanSanPhamServiceImpl() {
		this.congDoanSanPhamDAO = new CongDoanSanPhamDAOImpl();
		this.sanPhamDAO = new SanPhamDAOImpl();
	}

	@Override
	public List<CongDoanSanPham> timTatCaCongDoanSanPham(String maSanPham) {
		List<CongDoanSanPham> congDoanSanPhams = new ArrayList<CongDoanSanPham>();
		try {
			congDoanSanPhams = this.congDoanSanPhamDAO.timTatCaCongDoanSanPham(maSanPham);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return congDoanSanPhams;
	}

	@Override
	public List<CongDoanSanPham> timTatCaCongDoanSanPhamDangHoatDongBangMaSanPham(String maSanPham) {
		List<CongDoanSanPham> congDoanSanPhams = new ArrayList<CongDoanSanPham>();
		try {
			congDoanSanPhams = this.congDoanSanPhamDAO.timTatCaCongDoanSanPhamDangHoatDongBangMaSanPham(maSanPham);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return congDoanSanPhams;
	}

	@Override
	public CongDoanSanPham capNhatCongDoanSanPham(CongDoanSanPham congDoanSanPham) {
		try {
			CongDoanSanPham isExists = congDoanSanPhamDAO.timKiemBangMaCongDoan(congDoanSanPham.getMaCongDoan());
			if (isExists == null) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.congDoanKhongTontai"));
				return null;
			}
			congDoanSanPham = congDoanSanPhamDAO.capNhatCongDoanSanPham(congDoanSanPham);
			// Nếu trạng thái cũ khác với trạng thái mới
			// Nếu true -> tăng số lượng công đoạn
			// Nếu fale -> giảm số lượng công đoạn

			if (isExists.isTrangThai() != congDoanSanPham.isTrangThai()) {
				SanPham sanPham = this.sanPhamDAO.timKiemBangMaSanPham(congDoanSanPham.getSanPham().getMaSanPham());

				if (congDoanSanPham.isTrangThai()) {
					this.sanPhamDAO.capNhatSoLuongCongDoan(congDoanSanPham.getSanPham().getMaSanPham(),
							sanPham.getSoCongDoan() + 1);
				} else {
					this.sanPhamDAO.capNhatSoLuongCongDoan(congDoanSanPham.getSanPham().getMaSanPham(),
							sanPham.getSoCongDoan() - 1);
				}
			}
			return congDoanSanPham;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean capNhatTrangThaiCongDoanSanPham(String maCongDoanSanPham, boolean trangThai) {
		try {
			CongDoanSanPham isExists = congDoanSanPhamDAO.timKiemBangMaCongDoan(maCongDoanSanPham);
			if (isExists == null) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.congDoanKhongTontai"));
				return false;
			}

			boolean status = congDoanSanPhamDAO.capNhatTrangThaiCongDoanSanPham(maCongDoanSanPham, trangThai);

			if (!trangThai && status) {
				CongDoanSanPham congDoanSanPham = this.congDoanSanPhamDAO.timKiemBangMaCongDoan(maCongDoanSanPham);

				SanPham sanPham = this.sanPhamDAO.timKiemBangMaSanPham(congDoanSanPham.getSanPham().getMaSanPham());

				this.sanPhamDAO.capNhatSoLuongCongDoan(congDoanSanPham.getSanPham().getMaSanPham(),
						sanPham.getSoCongDoan() - 1);
			}
			return status;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CongDoanSanPham themCongDoanSanPham(CongDoanSanPham congDoanSanPham) {
		try {
			congDoanSanPham.setMaCongDoan(generateMaCongDoanSanPham(congDoanSanPham.getSanPham().getMaSanPham()));
			CongDoanSanPham isExists = congDoanSanPhamDAO.timKiemBangMaCongDoan(congDoanSanPham.getMaCongDoan());
			if (isExists != null) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.congDoanDaTontai"));
				return null;
			}

			congDoanSanPham = congDoanSanPhamDAO.themCongDoanSanPham(congDoanSanPham);
			if (congDoanSanPham != null) {
				SanPham sanPham = this.sanPhamDAO.timKiemBangMaSanPham(congDoanSanPham.getSanPham().getMaSanPham());
				this.sanPhamDAO.capNhatSoLuongCongDoan(sanPham.getMaSanPham(),
						sanPham.getSoCongDoan() + 1);
			}
			return congDoanSanPham;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String generateMaCongDoanSanPham(String maSanPham) {
		// 000101

		String rs = "000101";
		String maCongDoanCuoi = congDoanSanPhamDAO.timMaCongDoanSanPhamCuoiCungBangMaSanPham(maSanPham);
		maSanPham = maSanPham.substring(2);
		if (maCongDoanCuoi != null) {
			String maCongDoanCuoiSub = maCongDoanCuoi.substring(4);
			int maCongDoanMoi = Integer.parseInt(maCongDoanCuoiSub) + 1;
			rs = String.format("%s%02d", maSanPham, maCongDoanMoi);
		} else {
			rs = String.format("%s%02d", maSanPham, 1);
		}
		return rs;
	}

	@Override
	public List<CongDoanSanPham> themNhieuCongDoanSanPham(SanPham sanPham, List<CongDoanSanPham> congDoanSanPhams) {
		List<CongDoanSanPham> congDoanSanPhamThems = new ArrayList<CongDoanSanPham>();
		try {
			for (CongDoanSanPham congDoanSanPham : congDoanSanPhams) {

				String maCongDoan = generateMaCongDoanSanPham(sanPham.getMaSanPham());
				congDoanSanPham.setMaCongDoan(maCongDoan);
				congDoanSanPham.setSanPham(sanPham);
//				if(congDoanSanPham.getCongDoanLamTruoc().getMaCongDoan().trim().equals("")) {
//					congDoanSanPham.getCongDoanLamTruoc().setMaCongDoan(null);
//				}
				CongDoanSanPham congDoanSanPhamDaThem = themCongDoanSanPham(congDoanSanPham);
				if (congDoanSanPhamDaThem != null) {
					congDoanSanPhamThems.add(congDoanSanPhamDaThem);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return congDoanSanPhamThems;
	}

	@Override
	public void capNhatSoLuongCanCuaCongDoan(String maCongDoan, int soLuong) {
		try {
			// Kiểm tra có công đoạn làm sau đó hay không
			// Nếu có thì đẩy số lượng cho công đoạn sau
			CongDoanSanPham congDoanSanPhamSau = congDoanSanPhamDAO.timCongDoanlamSauBangMaCongDoan(maCongDoan);
			if (congDoanSanPhamSau != null) {
				congDoanSanPhamDAO.capNhatSoLuongCanCuaCongDoanSau(congDoanSanPhamSau.getMaCongDoan(), soLuong);
			}
			// Nếu không thì thôi
			congDoanSanPhamDAO.capNhatSoLuongCanCuaCongDoan(maCongDoan, soLuong);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
