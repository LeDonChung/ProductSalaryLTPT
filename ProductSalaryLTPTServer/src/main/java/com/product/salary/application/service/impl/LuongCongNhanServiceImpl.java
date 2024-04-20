package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.ChamCongCongNhanDAO;
import com.product.salary.application.dao.CongNhanDAO;
import com.product.salary.application.dao.LuongCongNhanDAO;
import com.product.salary.application.dao.impl.ChamCongCongNhanDAOImpl;
import com.product.salary.application.dao.impl.CongNhanDAOImpl;
import com.product.salary.application.dao.impl.LuongCongNhanDAOImpl;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.LuongCongNhan;
import com.product.salary.application.service.LuongCongNhanService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class LuongCongNhanServiceImpl implements LuongCongNhanService {
	private final LuongCongNhanDAO luongCongNhanDAO;
	private final ChamCongCongNhanDAO chamCongCongNhanDAO;
	private final CongNhanDAO congNhanDAO;

	public LuongCongNhanServiceImpl() {
		this.luongCongNhanDAO = new LuongCongNhanDAOImpl();
		this.chamCongCongNhanDAO = new ChamCongCongNhanDAOImpl();
		this.congNhanDAO = new CongNhanDAOImpl();
	}

	@Override
	public synchronized boolean tinhLuongCongNhan(int thang, int nam) {
		try {
			// Tìm công nhân làm vào tháng và năm
			List<CongNhan> congNhans = chamCongCongNhanDAO.timDanhSachCongNhanDiLamBangThangVaNam(thang, nam);
			if (congNhans.isEmpty()) {
				String message;
				if (SystemConstants.LANGUAGE == 1) {
					message = String.format("There are no workers working on %02d/%04d", thang, nam);
				} else {
					message = String.format("Không có công nhân nào làm việc vào %02d/%04d", thang, nam);
				}
				JOptionPane.showMessageDialog(null, message);
				return false;
			}
			// Tính lương theo từng công nhân
			for (CongNhan congNhan : congNhans) {
				String maLuong = generateMaLuong(congNhan.getMaCongNhan(), thang, nam);

				// Kiểm tra đã tồn tại chưa
				LuongCongNhan luongCongNhanExists = luongCongNhanDAO.timKiemLuongCongNhanBangMaLuong(maLuong);

				// Mặc định thưởng = 0 khi mới tính
				// Nếu đã tồn tại thì lấy lại lương thưởng cũ
				double luongThuong = 0;
				if (luongCongNhanExists != null) {
					luongThuong = luongCongNhanExists.getLuongThuong();
				}
				double troCap = congNhanDAO.timKiemBangMaCongNhan(congNhan.getMaCongNhan()).getTroCap();
				double tongLuong = luongCongNhanDAO.tinhTongTienCongNhanTheoMaCongNhanVaThangVaNam(
						congNhan.getMaCongNhan(), thang, nam) + troCap + luongThuong;

				LuongCongNhan luongCongNhan = new LuongCongNhan(maLuong, thang, nam, LocalDate.now(), tongLuong,
						luongThuong);

				if (luongCongNhanExists != null) {
					luongCongNhanDAO.capNhatLuongCongNhan(luongCongNhan);
				} else {
					luongCongNhanDAO.themLuongCongNhan(luongCongNhan);
				}

				// Cập nhật mã lương danh trong sách chấm công theo mã công nhân, tháng, năm
				chamCongCongNhanDAO.capNhatChamCongCongNhanBangMaCongNhanVaThangVaNam(congNhan.getMaCongNhan(), thang,
						nam, luongCongNhan.getMaLuong());
			}
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public synchronized String generateMaLuong(String maCongNhan, int thang, int nam) {
        return String.format("%02d%04d%s", thang, nam, maCongNhan);
	}

	@Override
	public List<Map<String, Object>> timTatCaLuongCongNhanTheoThangVaNam(int thang, int nam) {
		List<Map<String, Object>> results = new ArrayList<>();
		try {
			List<LuongCongNhan> luongCongNhans = this.luongCongNhanDAO.timTatCaLuongCongNhanTheoThangVaNam(thang, nam);
			for (LuongCongNhan luongCongNhan : luongCongNhans) {
				Map<String, Object> result = new HashMap<>();
				String maCongNhan = luongCongNhan.getMaLuong().substring(6, 16);
				CongNhan congNhan = this.congNhanDAO.timKiemBangMaCongNhan(maCongNhan);
				result.put("MaLuong", luongCongNhan.getMaLuong());
				result.put("MaCongNhan", congNhan.getMaCongNhan());
				result.put("TenCongNhan", congNhan.getHoTen());
				result.put("GioiTinh", congNhan.getGioiTinh() == 1 ? "Nam" : "Nữ");
				result.put("SoDienThoai", congNhan.getSoDienThoai());
				result.put("LuongThang", luongCongNhan.getThang());
				result.put("TroCap", congNhan.getTroCap());
				result.put("LuongThuong", luongCongNhan.getLuongThuong());
				result.put("TongLuong", luongCongNhan.getLuong());
				result.put("NgayTinhLuong", luongCongNhan.getNgayTinhLuong());
				results.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maCongNhan, int thang, int nam) {
		List<Map<String, Object>> results = new ArrayList<>();
		try {
			return this.luongCongNhanDAO.timTatCaChiTietLuongTheoThangVaNam(maCongNhan, thang, nam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public Map<String, Double> thongKeLuongCongNhanTheoNam() {
		Map<String, Double> results = new HashMap<>();
		try {
			return this.luongCongNhanDAO.thongKeLuongCongNhanTheoNam();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public Map<String, Object> thongKeLuongCongNhanBangThangVaNam(int thang, int nam) {
		Map<String, Object> results = new HashMap<>();
		try {
			List<Map<String, Object>> luongCongNhans = getLuongCongNhanTheoThangVaNam(thang, nam);
			int tongSoCongNhan = luongCongNhans.size();
			double tienLuongThapNhat = getTienLuongThapNhat(luongCongNhans);
			double tienLuongCaoNhat = getTienLuongCaoNhat(luongCongNhans);
			double tongSotienLuong = getTongSoTienLuong(luongCongNhans);
			String congNhanXuatSac = getCongNhanXuatSacNhat(luongCongNhans);
			Map<String, Double> tongLuongCongNhanTheoThang = this.luongCongNhanDAO.thongKeLuongCongNhanTheoThang(nam);
			results.put("TongSoCongNhan", tongSoCongNhan);
			results.put("TienLuongThapNhat", tienLuongThapNhat);
			results.put("TienLuongCaoNhat", tienLuongCaoNhat);
			results.put("TongSotienLuong", tongSotienLuong);
			results.put("CongNhanXuatSac", congNhanXuatSac);
			results.put("LuongCongNhans", luongCongNhans);
			results.put("TongLuongCongNhanTheoThang", tongLuongCongNhanTheoThang);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	private String getCongNhanXuatSacNhat(List<Map<String, Object>> luongCongNhans) {
		if (luongCongNhans.isEmpty()) {
			return "";
		}
		luongCongNhans.sort((o1, o2) -> (int) (Double.parseDouble(o2.get("TongLuong").toString())
                - Double.parseDouble(o1.get("TongLuong").toString())));
		return luongCongNhans.get(0).get("TenCongNhan") + " - " + luongCongNhans.get(0).get("MaCongNhan");
	}

	private double getTongSoTienLuong(List<Map<String, Object>> luongCongNhans) {
		double tongSoTienLuong = 0.0;
		for (Map<String, Object> t : luongCongNhans) {
			tongSoTienLuong += Double.parseDouble(t.get("TongLuong").toString());
		}
		return tongSoTienLuong;
	}

	private double getTienLuongCaoNhat(List<Map<String, Object>> luongCongNhans) {
		if (luongCongNhans.isEmpty()) {
			return 0;
		}
		luongCongNhans.sort((o1, o2) -> (int) (Double.parseDouble(o2.get("TongLuong").toString())
                - Double.parseDouble(o1.get("TongLuong").toString())));
		return Double.parseDouble(luongCongNhans.get(0).get("TongLuong").toString());
	}

	private double getTienLuongThapNhat(List<Map<String, Object>> luongCongNhans) {
		if (luongCongNhans.isEmpty()) {
			return 0;
		}
		luongCongNhans.sort((o1, o2) -> (int) (Double.parseDouble(o1.get("TongLuong").toString())
                - Double.parseDouble(o2.get("TongLuong").toString())));
		return Double.parseDouble(luongCongNhans.get(0).get("TongLuong").toString());
	}

	private List<Map<String, Object>> getLuongCongNhanTheoThangVaNam(int thang, int nam) {
		List<Map<String, Object>> results = new ArrayList<>();
		List<LuongCongNhan> luongCongNhans = this.luongCongNhanDAO.timTatCaLuongCongNhanTheoThangVaNam(thang, nam);
		for (LuongCongNhan luongCongNhan : luongCongNhans) {
			Map<String, Object> result = new HashMap<>();
			String maCongNhan = luongCongNhan.getMaLuong().substring(6, 16);
			CongNhan congNhan = this.congNhanDAO.timKiemBangMaCongNhan(maCongNhan);
			result.put("MaLuong", luongCongNhan.getMaLuong());
			result.put("MaCongNhan", congNhan.getMaCongNhan());
			result.put("TenCongNhan", congNhan.getHoTen());
			result.put("CCCD", congNhan.getCccd());
			result.put("LuongThang", luongCongNhan.getThang());
			result.put("NgayTinhLuong", luongCongNhan.getNgayTinhLuong());
			result.put("TongLuong", luongCongNhan.getLuong());
			results.add(result);
		}
		return results;
	}

	@Override
	public synchronized void capNhatLuongThuong(String maLuong, double luongThuong) {
		try {
			this.luongCongNhanDAO.capNhatLuongThuong(maLuong, luongThuong);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
