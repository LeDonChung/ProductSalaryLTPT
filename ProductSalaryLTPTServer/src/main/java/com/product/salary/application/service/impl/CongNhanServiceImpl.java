package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.CongNhanDAO;
import com.product.salary.application.dao.ToNhomDAO;
import com.product.salary.application.dao.impl.CongNhanDAOImpl;
import com.product.salary.application.dao.impl.ToNhomDAOImpl;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.service.CongNhanService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CongNhanServiceImpl implements CongNhanService {
	private CongNhanDAO congNhanDao;
	private ToNhomDAO toNhomDAO;

	public CongNhanServiceImpl() {
		this.congNhanDao = new CongNhanDAOImpl();
		this.toNhomDAO = new ToNhomDAOImpl();
	}

	@Override
	public List<CongNhan> timKiemTatCaCongNhan() {
		List<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
		try {

			dsCongNhan = congNhanDao.timKiemTatCaCongNhan();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return dsCongNhan;
	}

	@Override
	public synchronized CongNhan capNhatCongNhan(CongNhan congNhan) {
		try {
			CongNhan isExists = congNhanDao.timKiemBangMaCongNhan(congNhan.getMaCongNhan());
			if (isExists == null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.congNhanKhongTonTai"));
				return null;
			} else {
				if (!congNhan.getToNhom().getMaToNhom().equals(isExists.getToNhom().getMaToNhom())) {
					ToNhom toNhom = toNhomDAO.timKiemBangMaToNhom(congNhan.getToNhom().getMaToNhom());
					if (toNhom.isTrangThai() == false) {
						JOptionPane.showMessageDialog(null,
								SystemConstants.BUNDLE.getString("congNhan.toNhomKhongHoatDong"));
						return null;
					}

					toNhomDAO.capNhatSoLuongCongNhanBangMaToNhom(congNhan.getToNhom().getMaToNhom(),
							congNhan.getToNhom().getSoLuongCongNhan() + 1);

					toNhomDAO.capNhatSoLuongCongNhanBangMaToNhom(isExists.getToNhom().getMaToNhom(),
							isExists.getToNhom().getSoLuongCongNhan() - 1);
				}

				if (congNhan.isTrangThai() != isExists.isTrangThai()) {
					ToNhom tn = toNhomDAO.timKiemBangMaToNhom(congNhan.getToNhom().getMaToNhom());
					if (congNhan.isTrangThai())
						toNhomDAO.capNhatSoLuongCongNhanBangMaToNhom(tn.getMaToNhom(), tn.getSoLuongCongNhan() + 1);
					else
						toNhomDAO.capNhatSoLuongCongNhanBangMaToNhom(tn.getMaToNhom(), tn.getSoLuongCongNhan() - 1);
				}
				return congNhanDao.capNhatCongNhan(congNhan);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public synchronized boolean capNhatTrangThaiCongNhan(String maCongNhan, boolean trangThai) {
		try {
			CongNhan cn = (CongNhan) congNhanDao.timKiemBangMaCongNhan(maCongNhan);
			if (cn == null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.congNhanKhongTonTai"));
				return false;
			} else {
				toNhomDAO.capNhatSoLuongCongNhanBangMaToNhom(cn.getToNhom().getMaToNhom(),
						cn.getToNhom().getSoLuongCongNhan() - 1);
				return congNhanDao.capNhatTrangThaiCongNhan(maCongNhan, trangThai);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CongNhan> timKiemCongNhan(CongNhan congNhan) {
		List<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
		try {

			dsCongNhan = congNhanDao.timKiemCongNhan(congNhan);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return dsCongNhan;
	}

	@Override
	public synchronized CongNhan themCongNhan(CongNhan congNhan) {
		try {
			congNhan.setTrangThai(true);
			congNhan.setMaCongNhan(generateMaCongNhan(congNhan));

			CongNhan isExists = congNhanDao.timKiemBangMaCongNhan(congNhan.getMaCongNhan());
			if (isExists != null) {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.congNhanTonTai"));
				return null;
			} else {
				ToNhom toNhom = toNhomDAO.timKiemBangMaToNhom(congNhan.getToNhom().getMaToNhom());
				if (toNhom.isTrangThai() == false) {
					JOptionPane.showMessageDialog(null,
							SystemConstants.BUNDLE.getString("congNhan.toNhomKhongHoatDong"));
					return null;
				}
				CongNhan cccd = congNhanDao.timKiemBangCCCD(congNhan.getCccd());
				if (cccd != null) {
					JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.ccccTonTai"));
					return null;
				}
				CongNhan cn = congNhanDao.themCongNhan(congNhan);
				toNhomDAO.capNhatSoLuongCongNhanBangMaToNhom(congNhan.getToNhom().getMaToNhom(),
						congNhan.getToNhom().getSoLuongCongNhan() + 1);
				return cn;

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public synchronized String generateMaCongNhan(CongNhan congNhan) {
		// 20 là số nhận dạng là công nhân, XXXX năm vào làm của nhân viên,
		// XXXX là 4 số tự tăng trong năm vào làm của nv
		String maCongNhanCuoiCung = congNhanDao.layMaCongNhanCuoiCungCuaNam(congNhan.getNgayVaoLam().getYear());
		String maCongNhan = "";
		if (maCongNhanCuoiCung != null) {
			int soTuTang = Integer.parseInt(maCongNhanCuoiCung.substring(6));
			maCongNhan = String.format("20%04d%04d", congNhan.getNgayVaoLam().getYear(), soTuTang + 1);
		} else {
			int soTuTang = 0;
			maCongNhan = String.format("20%04d%04d", congNhan.getNgayVaoLam().getYear(), soTuTang + 1);
		}

		return maCongNhan;
	}

	@Override
	public boolean kiemTraCccd(String cccd) {
		try {
			CongNhan cn = (CongNhan) congNhanDao.timKiemBangCCCD(cccd);
			if (cn == null) {
				return false;
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CongNhan> timKiemCongNhanBangMaToNhom(String maToNhom) {
		List<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
		try {

			dsCongNhan = congNhanDao.timKiemCongNhanBangMaToNhom(maToNhom);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return dsCongNhan;
	}

	@Override
	public int tongSoLuongCongNhan() {
		int soLuong = 0;
		try {
			soLuong = this.congNhanDao.tongSoLuongCongNhan();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return soLuong;
	}

	@Override
	public CongNhan timKiemBangMaCongNhan(String maCongNhan) {
		try {
			CongNhan congNhan = congNhanDao.timKiemBangMaCongNhan(maCongNhan);
			if (congNhan != null)
				return congNhan;
			else {
				JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.congNhanKhongTonTai"));
				return null;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public synchronized List<CongNhan> themNhieuCongNhan(List<CongNhan> dsCongNhan) {
		List<CongNhan> dsCongNhanThem = new ArrayList<CongNhan>();
		try {
			for (CongNhan temp : dsCongNhan) {
//				String maCongNhan = generateMaCongNhan(temp);
//				temp.setMaCongNhan(maCongNhan);

				CongNhan congNhanDaThem = themCongNhan(temp);
				if (congNhanDaThem != null) {
					dsCongNhanThem.add(congNhanDaThem);
				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("congNhan.loiHeThong"));
			e.printStackTrace();
		}
		return dsCongNhanThem;
	}

}
