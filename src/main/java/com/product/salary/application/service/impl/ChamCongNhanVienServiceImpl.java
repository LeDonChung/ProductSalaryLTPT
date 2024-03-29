package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.ChamCongNhanVienDAO;
import com.product.salary.application.dao.impl.ChamCongNhanVienDAOImpl;
import com.product.salary.application.entity.CaLam;
import com.product.salary.application.entity.ChamCongNhanVien;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.service.ChamCongNhanVienService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChamCongNhanVienServiceImpl implements ChamCongNhanVienService {
	private ChamCongNhanVienDAO chamCongNhanVienDAO;

	public ChamCongNhanVienServiceImpl() {
		this.chamCongNhanVienDAO = new ChamCongNhanVienDAOImpl();
	}

	@Override
	public List<ChamCongNhanVien> timKiemTatCaChamCongNhanVienTheoCaVaNgay(LocalDate ngayChamCong, String maCa) {
		List<ChamCongNhanVien> danhSachChamCongNhanVien = new ArrayList<ChamCongNhanVien>();
		try {
			danhSachChamCongNhanVien = chamCongNhanVienDAO.timKiemTatCaChamCongNhanVienTheoCaVaNgay(ngayChamCong, maCa);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
			e.printStackTrace();
		}
		return danhSachChamCongNhanVien;
	}

	@Override
	public List<NhanVien> timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong(LocalDate ngayChamCong, String caLam) {
		List<NhanVien> dsNhanVienChuaChamCong = new ArrayList<NhanVien>();
		try {
			dsNhanVienChuaChamCong = chamCongNhanVienDAO
					.timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong(ngayChamCong, caLam);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
			e.printStackTrace();
		}
		return dsNhanVienChuaChamCong;
	}

	@Override
	public ChamCongNhanVien themChamCongNhanVien(ChamCongNhanVien chamCongNV) {
		try {
			ChamCongNhanVien isExists = chamCongNhanVienDAO.timKiemBangMaChamCongNhanVien(chamCongNV.getMaChamCong());
			if (isExists != null) {
				JOptionPane.showMessageDialog(null, String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("chamCongNhanVien.thongBaoChamCongDaTonTai")));
				return null;
			} else {
				List<ChamCongNhanVien> dsChamCong = chamCongNhanVienDAO.timKiemTatCaChamCongNhanVienTheoCaVaNgay(
						chamCongNV.getNgayChamCong(), chamCongNV.getMaChamCong());
				int kiemTra = 0;
				for (ChamCongNhanVien chamCongNhanVien : dsChamCong) {
					if (chamCongNhanVien.getNgayChamCong() == chamCongNV.getNgayChamCong()
							&& chamCongNhanVien.getCaLam().getMaCa().equals(chamCongNV.getCaLam().getMaCa())
							&& chamCongNhanVien.getNhanVien().getMaNhanVien()
									.equals(chamCongNV.getNhanVien().getMaNhanVien()))
						kiemTra = 1;
				}

				if (kiemTra == 1) {
					if (SystemConstants.LANGUAGE == 1) {
						JOptionPane.showMessageDialog(null,
								"Employee " + chamCongNV.getNhanVien().getHoTen() + " was timekeeping for shift "
										+ chamCongNV.getCaLam().getTenCa() + " date " + chamCongNV.getNgayChamCong());
					} else {
						JOptionPane.showMessageDialog(null,
								"Nhân viên " + chamCongNV.getNhanVien().getHoTen() + " đã được chấm công ca "
										+ chamCongNV.getCaLam().getTenCa() + " ngày " + chamCongNV.getNgayChamCong());
					}
					return null;
				}

				chamCongNV.setMaChamCong(
						genertateMaChamCongNhanVien(chamCongNV.getNgayChamCong(), chamCongNV.getCaLam()));

				return chamCongNhanVienDAO.themChamCongNhanVien(chamCongNV);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
			e.printStackTrace();
			return null;
		}
	}

	private String genertateMaChamCongNhanVien(LocalDate ngayChamCong, CaLam caLam) {
		String maChamCong = "";
		String maChamCongCuoiCungTheoCa = chamCongNhanVienDAO
				.timKiemMaChamCongNhanVienCuoiCungTheoNgayVaCaLam(ngayChamCong, caLam.getTenCa());
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
	public ChamCongNhanVien timKiemBangMaChamCongNhanVien(String maChamCong) {
		try {
			ChamCongNhanVien chamCongNhanVien = chamCongNhanVienDAO.timKiemBangMaChamCongNhanVien(maChamCong);
			if (chamCongNhanVien != null)
				return chamCongNhanVien;
			else {
				JOptionPane.showMessageDialog(null, String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("chamCongNhanVien.thongBaoChamCongKhongTonTai")));
				return null;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean capNhatTrangThaiDiLamCuaNhanVien(String maChamCong, int trangThai) {
		try {
			ChamCongNhanVien isExists = chamCongNhanVienDAO.timKiemBangMaChamCongNhanVien(maChamCong);
			if (isExists == null) {
				JOptionPane.showMessageDialog(null, String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("chamCongNhanVien.thongBaoChamCongKhongTonTai")));
				return false;
			} else
				return chamCongNhanVienDAO.capNhatTrangThaiDiLamCuaNhanVien(maChamCong, trangThai);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
			e.printStackTrace();
			return false;
		}
	}
}
