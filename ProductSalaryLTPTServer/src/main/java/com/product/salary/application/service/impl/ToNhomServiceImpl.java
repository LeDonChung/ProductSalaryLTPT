package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.ToNhomDAO;
import com.product.salary.application.dao.impl.ToNhomDAOImpl;
import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.service.ToNhomService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ToNhomServiceImpl implements ToNhomService {

	ToNhomDAO toNhomDAO;

	public ToNhomServiceImpl() {
		this.toNhomDAO = new ToNhomDAOImpl();
	}

	@Override
	public List<ToNhom> timKiemTatCaToNhom() {
		List<ToNhom> dsToNhom = new ArrayList<ToNhom>();
		try {
			dsToNhom = toNhomDAO.timKiemTatCaToNhom();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return dsToNhom;
	}

	@Override
	public List<ToNhom> timKiemTatCaToNhomDangHoatDong() {
		List<ToNhom> dsToNhom = new ArrayList<ToNhom>();
		try {
			dsToNhom = toNhomDAO.timKiemTatCaToNhomDangHoatDong();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return dsToNhom;
	}

	@Override
	public List<ToNhom> timKiemToNhom(ToNhom toNhom) {
		List<ToNhom> dsToNhom = new ArrayList<ToNhom>();
		try {
			dsToNhom = toNhomDAO.timKiemToNhom(toNhom);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return dsToNhom;
	}

	@Override
	public ToNhom timKiemBangMaToNhom(String maToNhom) {
		ToNhom toNhom = null;
		try {
			toNhom = toNhomDAO.timKiemBangMaToNhom(maToNhom);
			if (toNhom == null) {
				JOptionPane.showMessageDialog(null,SystemConstants.BUNDLE.getString("congNhan.toNhomkhongTonTai"));
				//JOptionPane.showMessageDialog(null, "Tổ nhóm không tồn tại!");
				return null;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return toNhom;
	}

	@Override
	public synchronized ToNhom capNhatToNhom(ToNhom toNhom) {
		try {
			ToNhom exists = toNhomDAO.timKiemBangMaToNhom(toNhom.getMaToNhom());
			if (exists == null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.toNhomkhongTonTai"));
				return null;
			} else {
				boolean trangThaiMoi = toNhom.isTrangThai();
				boolean trangThaiCu = exists.isTrangThai();
				if (trangThaiCu != trangThaiMoi) {
					if (trangThaiCu == true && trangThaiMoi == false) {
						if (exists.getSoLuongCongNhan() != 0) {
							JOptionPane.showMessageDialog(null,
									SystemConstants.BUNDLE.getString("congNhan.capNhatTrangThai"));
							return null;
						}
					}
				}

				return toNhomDAO.capNhatToNhom(toNhom);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public synchronized boolean capNhatTrangThaiToNhom(String maToNhom, boolean trangThai) {
		try {
			ToNhom exists = toNhomDAO.timKiemBangMaToNhom(maToNhom);
			if (exists == null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.toNhomkhongTonTai"));
				return false;
			} else {
				if (exists.isTrangThai() == true && trangThai == false) {
					if (exists.getSoLuongCongNhan() != 0) {
						JOptionPane.showMessageDialog(null,
								SystemConstants.BUNDLE.getString("congNhan.capNhatTrangThai"));
						return false;
					}
				}

				return toNhomDAO.capNhatTrangThaiToNhom(maToNhom, trangThai);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public synchronized ToNhom themToNhom(ToNhom toNhom) {
		try {
			toNhom.setMaToNhom(generateMaToNhom());
			ToNhom exists = toNhomDAO.timKiemBangMaToNhom(toNhom.getMaToNhom());
			if (exists != null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.toNhomTonTai"));
				return null;
			} else {
				toNhom.setMaToNhom(generateMaToNhom());
				toNhom.setTrangThai(true);
				return toNhomDAO.themToNhom(toNhom);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public synchronized String generateMaToNhom() {
		String maToNhomCuoi = toNhomDAO.timMaToNhomCuoiCung();
		String maToNhomMoi = "";
		if (maToNhomCuoi == null) {
			maToNhomMoi = String.format("45%04d", Integer.parseInt("1"));
		} else {
			int maCuoi = Integer.parseInt(maToNhomCuoi.substring(3));
			maToNhomMoi = String.format("45%04d", maCuoi + 1);
		}

		return maToNhomMoi;
	}

	@Override
	public synchronized boolean capNhatSoLuongCongNhanBangMaToNhom(String maToNhom, int soLuong) {
		try {
			ToNhom exists = toNhomDAO.timKiemBangMaToNhom(maToNhom);
			if (exists != null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.toNhomTonTai"));
				return false;
			} else
				return toNhomDAO.capNhatSoLuongCongNhanBangMaToNhom(maToNhom, soLuong);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public synchronized List<ToNhom> themNhieuToNhom(List<ToNhom> dsToNhom) {
		List<ToNhom> dsToNhomThem = new ArrayList<ToNhom>();
		try {
			for (ToNhom toNhom : dsToNhom) {

				String maToNhom = generateMaToNhom();
				toNhom.setMaToNhom(maToNhom);

				ToNhom toNhomDaThem = themToNhom(toNhom);
				if (toNhomDaThem != null) {
					dsToNhomThem.add(toNhomDaThem);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return dsToNhomThem;
	}
}
