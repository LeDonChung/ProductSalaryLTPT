package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.ChamCongCongNhanDAO;
import com.product.salary.application.dao.impl.ChamCongCongNhanDAOImpl;
import com.product.salary.application.entity.CaLam;
import com.product.salary.application.entity.ChamCongCongNhan;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.service.ChamCongCongNhanService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChamCongCongNhanServiceImpl implements ChamCongCongNhanService {
	private ChamCongCongNhanDAO chamCongDAO;

	public ChamCongCongNhanServiceImpl() {
		this.chamCongDAO = new ChamCongCongNhanDAOImpl();
	}

	@Override
	public List<ChamCongCongNhan> timKiemTatCaChamCongCongNhan(LocalDate ngayChamCong) {
		List<ChamCongCongNhan> dsChamCong = new ArrayList<ChamCongCongNhan>();
		try {
			dsChamCong = chamCongDAO.timTatCaChamCongCongNhan(ngayChamCong);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return dsChamCong;
	}

	@Override
	public List<CongNhan> timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(LocalDate ngayChamCong, String caLam) {
		List<CongNhan> dsCongNhanChuaChamCong = new ArrayList<CongNhan>();
		try {
			dsCongNhanChuaChamCong = chamCongDAO.timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(ngayChamCong, caLam);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
		}
		return dsCongNhanChuaChamCong;
	}

	@Override
	public synchronized ChamCongCongNhan themChamCongCongNhan(ChamCongCongNhan chamCong) {
		try {
			String maPhanCong = genertateMaChamCongCongNhan(chamCong.getNgayChamCong(), chamCong.getCaLam());
			chamCong.setMaChamCong(maPhanCong);

			ChamCongCongNhan isExists = chamCongDAO.timKiemBangMaChamCongCongNhan(chamCong.getMaChamCong());
			if (isExists != null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.congNhanTonTai"));
				return null;
			}

            return chamCongDAO.themChamCongCongNhan(chamCong);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	public synchronized String genertateMaChamCongCongNhan(LocalDate ngayChamCong, CaLam caLam) {
		String maChamCong = "";
		String maChamCongCuoiCungTheoCa = chamCongDAO.timKiemMaChamCongCongNhanCuoiCungTheoNgayVaCaLam(ngayChamCong, caLam.getMaCa());
		int soTuTang = 0;
		String nam = String.format("%s", ngayChamCong.getYear());
		if (maChamCongCuoiCungTheoCa == null) {
			maChamCong = String.format("%s%02d%02d%s%04d", caLam.getMaCa(), ngayChamCong.getDayOfMonth(),
					ngayChamCong.getMonthValue(), nam.substring(2), soTuTang + 1);
		} else {
			soTuTang = Integer.parseInt(maChamCongCuoiCungTheoCa.substring(8));
			maChamCong = String.format("%s%02d%02d%s%04d", caLam.getMaCa(), ngayChamCong.getDayOfMonth(),
					ngayChamCong.getMonthValue(), nam.substring(2), soTuTang + 1);
		}

		return maChamCong;
	}

	@Override
	public synchronized boolean capNhatChamCongCongNhan(String maChamCong, int trangThai, int soLuongHoanThanh) {
		try {
			ChamCongCongNhan isExists = chamCongDAO.timKiemBangMaChamCongCongNhan(maChamCong);
			if (isExists == null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.congNhanKhongTonTai"));
				//JOptionPane.showMessageDialog(null, "Chấm công công nhân không tồn tại!");
				return false;
			} else
				return chamCongDAO.capNhatChamCongCongNhan(maChamCong, trangThai, soLuongHoanThanh);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return false;
		}
	}

}
