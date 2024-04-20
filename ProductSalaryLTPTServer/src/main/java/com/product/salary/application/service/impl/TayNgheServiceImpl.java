package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.TayNgheDAO;
import com.product.salary.application.dao.impl.TayNgheDAOImpl;
import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.service.TayNgheService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TayNgheServiceImpl implements TayNgheService {
	private TayNgheDAO tayNgheDao;

	public TayNgheServiceImpl() {
		this.tayNgheDao = new TayNgheDAOImpl();
	}

	@Override
	public List<TayNghe> timKiemTatCaTayNghe() {
		List<TayNghe> tayNghes = new ArrayList<TayNghe>();
		try {

			tayNghes = tayNgheDao.timKiemTatCaTayNghe();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return tayNghes;
	}

	@Override
	public synchronized TayNghe capNhatTayNghe(TayNghe tayNghe) {
		try {
			TayNghe isExists = tayNgheDao.timKiemBangMaTayNghe(tayNghe.getMaTayNghe());
			if (isExists == null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("tayNghe.tayNgheKhongTonTai"));
				//JOptionPane.showMessageDialog(null, "Tay nghề không tồn tại.");
				return null;
			}

			return tayNgheDao.capNhatTayNghe(tayNghe);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public synchronized TayNghe themTayNghe(TayNghe tayNghe) {
		try {
			TayNghe isExists = tayNgheDao.timKiemBangMaTayNghe(tayNghe.getMaTayNghe());
			if (isExists != null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("tayNghe.tayNgheTonTai"));
				//JOptionPane.showMessageDialog(null, "Tay nghề đã tồn tại.");
				return null;
			}
			tayNghe.setMaTayNghe(generegateMaTayNghe());
			return tayNgheDao.themTayNghe(tayNghe);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TayNghe timKiemBangMaTayNghe(String maTayNghe) {
		try {
			return tayNgheDao.timKiemBangMaTayNghe(maTayNghe);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public synchronized String generegateMaTayNghe() {
		// 780001
		String rs = "780001";
		String maChuVu = tayNgheDao.timMaTayNgheCuoiCung();
		if (maChuVu != null) {
			String maCuoi = maChuVu.substring(2);
			int maMoi = Integer.parseInt(maCuoi);
			rs = String.format("78%04d", maMoi + 1);
		}
		return rs;
	}

	@Override
	public synchronized boolean xoaTayNgheBangMa(String maTayNghe) {
		try {
			return tayNgheDao.xoaTayNgheBangMa(maTayNghe);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return false;
		}
	}

}
