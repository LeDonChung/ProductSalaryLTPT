package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.CongDoanSanPhamDAO;
import com.product.salary.application.dao.CongNhanDAO;
import com.product.salary.application.dao.PhanCongCongViecDAO;
import com.product.salary.application.dao.impl.CongDoanSanPhamDAOImpl;
import com.product.salary.application.dao.impl.CongNhanDAOImpl;
import com.product.salary.application.dao.impl.PhanCongCongViecDAOImpl;
import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.PhanCongCongNhan;
import com.product.salary.application.service.PhanCongCongViecService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PhanCongCongViecServiceImpl implements PhanCongCongViecService {
	private final CongNhanDAO congNhanDAO;
	private final PhanCongCongViecDAO phanCongDAO;
	private final CongDoanSanPhamDAO congDoanDAO;

	public PhanCongCongViecServiceImpl() {
		congNhanDAO = new CongNhanDAOImpl();
		phanCongDAO = new PhanCongCongViecDAOImpl();
		congDoanDAO = new CongDoanSanPhamDAOImpl();
	}


	@Override
	public PhanCongCongNhan capNhatPhanCongCongNhan(PhanCongCongNhan phanCongCongNhan) {
		try {
			PhanCongCongNhan isExists = phanCongDAO.timKiemBangMaPhanCong(phanCongCongNhan.getMaPhanCong());
			if (isExists == null) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("phanCongCongNhan.congDoanKhongTonTai"));
				// JOptionPane.showMessageDialog(null, "Công đoạn sản phẩm không tồn tại.");
				return null;
			}
			phanCongCongNhan = phanCongDAO.capNhatPhanCongCongNhan(phanCongCongNhan);
			return phanCongCongNhan;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean xoaPhanCongCongNhan(String maPhanCong) {
		try {
			return phanCongDAO.xoaPhanCongCongNhan(maPhanCong);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PhanCongCongNhan phanCongCongNhan(PhanCongCongNhan phanCongCongNhan) {
		try {
			PhanCongCongNhan isExists = phanCongDAO.timKiemBangMaPhanCong(phanCongCongNhan.getMaPhanCong());
			if (isExists != null) {
				JOptionPane.showMessageDialog(null,
						SystemConstants.BUNDLE.getString("phanCongCongNhan.phanCongTonTai"));
				// JOptionPane.showMessageDialog(null, "Phân công công nhân đã tồn tại.");
				return null;
			}

			phanCongCongNhan = phanCongDAO.phanCongCongNhan(phanCongCongNhan);
			return phanCongCongNhan;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PhanCongCongNhan> phanCongNhieuCongNhan(List<PhanCongCongNhan> phanCongCongNhans) {
		List<PhanCongCongNhan> phanCongCongNhanNews = new ArrayList<PhanCongCongNhan>();
		try {
			for (PhanCongCongNhan phanCongCongNhan : phanCongCongNhans) {
				phanCongCongNhanNews.add(phanCongCongNhan(phanCongCongNhan));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return phanCongCongNhanNews;
	}

	@Override
	public String generateMaPhanCongCongNhan(String maCongNhan, String maCongDoan) {
		String rs = "";
		CongNhan macn = congNhanDAO.timKiemBangMaCongNhan(maCongNhan);
		CongDoanSanPham congDoan = congDoanDAO.timKiemBangMaCongDoan(maCongDoan);
		String phanCong = phanCongDAO.timMaPhanCongCuoiCung();

		String maCuoiCongNhan = macn.getMaCongNhan().substring(2);
		String maCuoiCongDoan = congDoan.getMaCongDoan().substring(2);

		if (phanCong != null) {
			int maMoi = Integer.parseInt(phanCong.substring(12));
			rs = String.format("%s%s%03d", maCuoiCongNhan, maCuoiCongDoan, maMoi + 1);
		} else {
			rs = String.format("%s%s%03d", maCuoiCongNhan, maCuoiCongDoan, 1);
		}
		return rs;
	}

	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(String maCongNhan) {
		List<PhanCongCongNhan> dsPhanCong = new ArrayList<PhanCongCongNhan>();
		try {
			dsPhanCong = this.phanCongDAO.timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(maCongNhan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhanCong;
	}

	@Override
	public List<CongNhan> timTatCaCongNhanChuaPhanCongVaoCongDoan(String maSanPham, String maCongDoan) {
		List<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
		try {
			dsCongNhan = this.phanCongDAO.timTatCaCongNhanChuaPhanCongVaoCongDoan(maSanPham, maCongDoan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCongNhan;
	}

	@Override
	public List<PhanCongCongNhan> timTatCaPhanCongTheoMaCongDoan(String maCongDoan) {
		List<PhanCongCongNhan> dsPhanCong = new ArrayList<PhanCongCongNhan>();
		try {
			dsPhanCong = this.phanCongDAO.timTatCaPhanCongTheoMaCongDoan(maCongDoan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhanCong;
	}

}
