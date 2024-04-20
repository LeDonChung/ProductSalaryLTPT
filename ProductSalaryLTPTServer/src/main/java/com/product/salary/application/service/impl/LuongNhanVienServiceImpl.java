package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.ChamCongNhanVienDAO;
import com.product.salary.application.dao.LuongNhanVienDAO;
import com.product.salary.application.dao.NhanVienDAO;
import com.product.salary.application.dao.impl.ChamCongNhanVienDAOImpl;
import com.product.salary.application.dao.impl.LuongNhanVienDAOImpl;
import com.product.salary.application.dao.impl.NhanVienDAOImpl;
import com.product.salary.application.entity.LuongNhanVien;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.service.LuongNhanVienService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class LuongNhanVienServiceImpl implements LuongNhanVienService {
	private LuongNhanVienDAO luongNhanVienDAO;
	private NhanVienDAO nhanVienDAO;
	private ChamCongNhanVienDAO chamCongNhanVienDAO;

	public LuongNhanVienServiceImpl() {
		this.luongNhanVienDAO = new LuongNhanVienDAOImpl();
		this.nhanVienDAO = new NhanVienDAOImpl();
		this.chamCongNhanVienDAO = new ChamCongNhanVienDAOImpl();
	}

	@Override
	public Map<String, Double> thongKeLuongNhanVienTheoNam() {
		Map<String, Double> results = new HashMap<String, Double>();
		try {
			return this.luongNhanVienDAO.thongKeLuongNhanVienTheoNam();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<Map<String, Object>> timKiemTatCaLuongNhanVienTheoThangVaNam(int thang, int nam) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		try {
			List<LuongNhanVien> dsLuongNhanVien = luongNhanVienDAO.timKiemTatCaLuongNhanVienTheoThangVaNam(thang, nam);

			for (LuongNhanVien luongNhanVien : dsLuongNhanVien) {
				String maNhanVien = luongNhanVien.getMaLuong().substring(6, 16);
				Map<String, Object> result = new HashMap<String, Object>();
				NhanVien nhanVien = nhanVienDAO.timKiemBangMaNhanVien(maNhanVien);

				result.put("MaLuong", luongNhanVien.getMaLuong());
				result.put("MaNhanVien", nhanVien.getMaNhanVien());
				result.put("TenNhanVien", nhanVien.getHoTen());
				result.put("GioiTinh", nhanVien.getGioiTinh() == 1 ? "Nam" : "Nữ");
				result.put("SoDienThoai", nhanVien.getSoDienThoai());
				result.put("LuongThang", luongNhanVien.getThang());
				result.put("TroCap", nhanVien.getTroCap());
				result.put("LuongThuong", luongNhanVien.getLuongThuong());
				result.put("TongLuong", luongNhanVien.getLuong());
				result.put("NgayTinhLuong", luongNhanVien.getNgayTinhLuong());

				results.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public synchronized boolean tinhLuongNhanVien(int thang, int nam) {
		try {
			// Danh sách nhân viên đi làm tháng, năm
			List<NhanVien> dsNhanVien = chamCongNhanVienDAO.timKiemDanhSachNhanVienDiLamBangThangVaNam(thang, nam);
			if (dsNhanVien.isEmpty()) {
				if (SystemConstants.LANGUAGE == 1) {
					JOptionPane.showMessageDialog(null, "No one employees at work in " + thang + "/" + nam);
				} else {
					JOptionPane.showMessageDialog(null, "Không có nhân viên nào đi làm vào tháng " + thang + "/" + nam);
				}

				return false;
			}
			// Tính luong theo từng nhân viên
			for (NhanVien nhanVien : dsNhanVien) {

				// Phát sinh mã tính lương cho luong
				String maLuong = generateMaLuong(nhanVien.getMaNhanVien(), thang, nam);

				// Kiem tra ton tai cua luong NV
				LuongNhanVien luongNhanVienExists = luongNhanVienDAO.timKiemBangMaLuong(maLuong);

				// Lay so ca lam da cham cong
				Long soCaLamSangVaChieu = luongNhanVienDAO.laySoLuongCaSangVaChieuKhongThuocNgayChuNhatBangMaNhanVien(
						nhanVien.getMaNhanVien(), thang, nam);
				Long soCaLamToi = luongNhanVienDAO
						.laySoLuongCaToiKhongThuocNgayChuNhatBangMaNhanVien(nhanVien.getMaNhanVien(), thang, nam);
				Long soCaLamThuocNgayChuNhat = luongNhanVienDAO
						.laySoLuongCaLamNgayChuNhatBangMaNhanVien(nhanVien.getMaNhanVien(), thang, nam);

				// lấy số lần đi trễ của nv
				Long soLanDitre = luongNhanVienDAO.demSoLuongDiLamTreCuaNhanVien(nhanVien.getMaNhanVien(), thang, nam);

				// tinh luong the ca lam, trung bình mỗi nv chấm công ít nhất 1 ca / ngày
				double luongNVMotCa = (nhanVien.getHeSoLuong() * nhanVien.getLuongCoSo()) / 26;
				double luongNVCaToiVaChuNhat = luongNVMotCa * 1.5;

				// lấy lương thưởng cũ nếu tồn tại
				double luongThuong = 0;
				if (luongNhanVienExists != null) {
					luongThuong = luongNhanVienExists.getLuongThuong();
				}

				// Lương tổng quát
				double tongLuong = luongNVMotCa * soCaLamSangVaChieu
						+ (soCaLamThuocNgayChuNhat + soCaLamToi) * luongNVCaToiVaChuNhat + nhanVien.getTroCap()
						+ luongThuong + (soLanDitre * 50000);

				LuongNhanVien luongNV = new LuongNhanVien(maLuong, thang, nam, LocalDate.now(), tongLuong, luongThuong);

				if (luongNhanVienExists == null) {
					luongNhanVienDAO.themLuongNhanVien(luongNV);
				} else {
					luongNhanVienDAO.capNhatLuongNhanVien(luongNV);
				}

				// Cập nhật ma lương cho các chấm công
				chamCongNhanVienDAO.capNhatMaLuongBangMaNhanVienVaThangVaNam(nhanVien.getMaNhanVien(), thang, nam,
						maLuong);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public synchronized String generateMaLuong(String maNhanVien, int thang, int nam) {
		String maLuong = String.format("%02d%04d%s", thang, nam, maNhanVien);
		return maLuong;
	}

	@Override
	public List<Map<String, Object>> timTatCaChiTietLuongTheoThangVaNam(String maNhanVien, int thang, int nam) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		try {
			return luongNhanVienDAO.timTatCaChiTietLuongTheoThangVaNam(maNhanVien, thang, nam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public Map<String, Object> thongKeLuongNhanVienBangThangVaNam(int thang, int nam) {
		Map<String, Object> results = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> luongNhanViens = getLuongNhanVienTheoThangVaNam(thang, nam);
			int tongSoNhanVien = luongNhanViens.size();
			double tienLuongThapNhat = getTienLuongThapNhat(luongNhanViens);
			double tienLuongCaoNhat = getTienLuongCaoNhat(luongNhanViens);
			double tongSotienLuong = getTongSoTienLuong(luongNhanViens);
			String nhanVienXuatSac = getNhanVienXuatSacNhat(luongNhanViens);
			Map<String, Double> tongLuongNhanVienTheoThang = this.luongNhanVienDAO.thongKeLuongNhanVienTheoThang(nam);
			results.put("TongSoNhanVien", tongSoNhanVien);
			results.put("TienLuongThapNhat", tienLuongThapNhat);
			results.put("TienLuongCaoNhat", tienLuongCaoNhat);
			results.put("TongSotienLuong", tongSotienLuong);
			results.put("NhanVienXuatSac", nhanVienXuatSac);
			results.put("LuongNhanViens", luongNhanViens);
			results.put("TongLuongNhanVienTheoThang", tongLuongNhanVienTheoThang);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	private String getNhanVienXuatSacNhat(List<Map<String, Object>> luongNhanViens) {
		if (luongNhanViens.isEmpty()) {
			return "";
		}
		luongNhanViens.sort(new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return (int) (Double.valueOf(o2.get("TongLuong").toString())
						- Double.valueOf(o1.get("TongLuong").toString()));
			}
		});
		return luongNhanViens.get(0).get("TenNhanVien") + " - " + luongNhanViens.get(0).get("MaNhanVien");
	}

	private double getTongSoTienLuong(List<Map<String, Object>> luongNhanViens) {
		Double tongSoTienLuong = 0.0;
		for (Map<String, Object> t : luongNhanViens) {
			tongSoTienLuong += Double.valueOf(t.get("TongLuong").toString());
		}
		return tongSoTienLuong;
	}

	private double getTienLuongCaoNhat(List<Map<String, Object>> luongNhanViens) {
		if (luongNhanViens.isEmpty()) {
			return 0;
		}
		luongNhanViens.sort(new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return (int) (Double.valueOf(o2.get("TongLuong").toString())
						- Double.valueOf(o1.get("TongLuong").toString()));
			}
		});
		return Double.valueOf(luongNhanViens.get(0).get("TongLuong").toString());
	}

	private double getTienLuongThapNhat(List<Map<String, Object>> luongNhanViens) {
		if (luongNhanViens.isEmpty()) {
			return 0;
		}
		luongNhanViens.sort(new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return (int) (Double.valueOf(o1.get("TongLuong").toString())
						- Double.valueOf(o2.get("TongLuong").toString()));
			}
		});
		return Double.valueOf(luongNhanViens.get(0).get("TongLuong").toString());
	}

	private List<Map<String, Object>> getLuongNhanVienTheoThangVaNam(int thang, int nam) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		List<LuongNhanVien> luongNhanViens = this.luongNhanVienDAO.timKiemTatCaLuongNhanVienTheoThangVaNam(thang, nam);
		for (LuongNhanVien luongNhanVien : luongNhanViens) {
			Map<String, Object> result = new HashMap<String, Object>();
			String maNhanvien = luongNhanVien.getMaLuong().substring(6, 16);
			NhanVien nhanVien = this.nhanVienDAO.timKiemBangMaNhanVien(maNhanvien);
			result.put("MaLuong", luongNhanVien.getMaLuong());
			result.put("MaNhanVien", nhanVien.getMaNhanVien());
			result.put("TenNhanVien", nhanVien.getHoTen());
			result.put("CCCD", nhanVien.getCccd());
			result.put("LuongThang", luongNhanVien.getThang());
			result.put("NgayTinhLuong", luongNhanVien.getNgayTinhLuong());
			result.put("TongLuong", luongNhanVien.getLuong());
			results.add(result);
		}
		return results;
	}

	@Override
	public synchronized void capNhatLuongThuong(String maLuong, double luongThuong) {
		try {
			luongNhanVienDAO.capNhatLuongThuong(maLuong, luongThuong);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
