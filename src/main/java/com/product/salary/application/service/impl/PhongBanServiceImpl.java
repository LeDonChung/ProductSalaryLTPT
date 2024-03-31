package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.PhongBanDAO;
import com.product.salary.application.dao.impl.PhongBanDAOImpl;
import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.service.PhongBanService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PhongBanServiceImpl implements PhongBanService {
    PhongBanDAO phongBanDAO;

    public PhongBanServiceImpl() {
        this.phongBanDAO = new PhongBanDAOImpl();
    }

    @Override
    public List<PhongBan> timKiemTatCaPhongBan() {
        List<PhongBan> dsPhongBan = new ArrayList<PhongBan>();
        try {
            dsPhongBan = phongBanDAO.timKiemTatCaPhongBan();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
        }
        return dsPhongBan;
    }

    @Override
    public List<PhongBan> timKiemPhongBan(PhongBan phongBan) {
        List<PhongBan> dsPhongBan = new ArrayList<>();
        try {
            dsPhongBan = phongBanDAO.timKiemPhongBan(phongBan);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
        }
        return dsPhongBan;
    }

    @Override
    public PhongBan timKiemBangMaPhongBan(String maPhongBan) {
        PhongBan phongBan = null;
        try {
            phongBan = phongBanDAO.timKiemBangMaPhongBan(maPhongBan);
            if (phongBan == null) {
                JOptionPane.showMessageDialog(null, String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoKhongTonTai")));
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
        }
        return phongBan;
    }

    @Override
    public PhongBan capNhatPhongBan(PhongBan phongBan) {

        try {
            PhongBan exists = phongBanDAO.timKiemBangMaPhongBan(phongBan.getMaPhongBan());
            if (exists == null) {
                JOptionPane.showMessageDialog(null,
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoKhongTonTai"));
                return null;
            } else {
                boolean trangThaiMoi = phongBan.isTrangThai();
                boolean trangThaiCu = exists.isTrangThai();
                if (trangThaiCu != trangThaiMoi) {
                    if (trangThaiCu == true && trangThaiMoi == false) {
                        if (exists.getSoLuongNhanVien() != 0) {
                            JOptionPane.showMessageDialog(null,
                                    SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoKhongTheTatTrangThaiHoatDong"));
                            return null;
                        }
                    }
                }

                exists.setTenPhongBan(phongBan.getTenPhongBan());

                return phongBanDAO.capNhatPhongBan(exists);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean capNhatTrangThaiPhongBan(String maPhongBan, boolean trangThai) {
        try {
            PhongBan exists = phongBanDAO.timKiemBangMaPhongBan(maPhongBan);
            if (exists == null) {
                JOptionPane.showMessageDialog(null,
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoKhongTonTai"));
                return false;
            } else {
                if (exists.isTrangThai() && trangThai == false) {
                    if (exists.getSoLuongNhanVien() != 0) {
                        JOptionPane.showMessageDialog(null,
                                SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoKhongTheTatTrangThaiHoatDong"));
                        return false;
                    }
                }

                return phongBanDAO.capNhatTrangThaiPhongBan(maPhongBan, trangThai);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PhongBan themPhongBan(PhongBan phongBan) {
        try {
            phongBan.setMaPhongBan(generateMaPhongBan());
            PhongBan exists = phongBanDAO.timKiemBangMaPhongBan(phongBan.getMaPhongBan());
            if (exists != null) {
                JOptionPane.showMessageDialog(null,
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoDaTonTai"));
                return null;
            } else {
                phongBan.setTrangThai(true);
                phongBan.setSoLuongNhanVien(0);
                return phongBanDAO.themPhongBan(phongBan);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean capNhatSoLuongNhanVienBangMaPhongBan(String maPhongBan, int soLuong) {
        try {
            PhongBan exists = phongBanDAO.timKiemBangMaPhongBan(maPhongBan);
            if (exists == null) {
                JOptionPane.showMessageDialog(null,
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoKhongTonTai"));
                return false;
            } else
                return phongBanDAO.capNhatSoLuongNhanVienBangMaPhongBan(maPhongBan, soLuong);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String generateMaPhongBan() {
        String maPhongBanCuoi = phongBanDAO.timMaPhongbanCuoiCung();
        String maPhongBanMoi = "";
        if (maPhongBanCuoi == null) {
            maPhongBanMoi = String.format("40%04d", Integer.parseInt("1"));
//			System.out.println(maPhongBanMoi);
        } else {
            int maCuoi = Integer.parseInt(maPhongBanCuoi.substring(2));
            System.out.println(maCuoi);
            maPhongBanMoi = String.format("40%04d", maCuoi + 1);
//			System.out.println(maPhongBanMoi);
        }

        return maPhongBanMoi;
    }

    @Override
    public List<PhongBan> timKiemTatCaPhongBanDangHoatDong() {
        List<PhongBan> dsPhongBan = new ArrayList<PhongBan>();
        try {
            dsPhongBan = phongBanDAO.timKiemTatCaPhongBanDangHoatDong();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
        }
        return dsPhongBan;
    }

    @Override
    public List<PhongBan> themNhieuPhongBan(List<PhongBan> dsPhongBan) {
        List<PhongBan> dsPhongBanThem = new ArrayList<PhongBan>();
        try {
            for (PhongBan phongBan : dsPhongBan) {
                String maPhongBan = generateMaPhongBan();
                phongBan.setMaPhongBan(maPhongBan);

                PhongBan phongBanDaThem = themPhongBan(phongBan);
                if (phongBanDaThem != null)
                    dsPhongBanThem.add(phongBanDaThem);
            }

            if (dsPhongBanThem.size() > 0)
                if (SystemConstants.LANGUAGE == 1) {
                    JOptionPane.showMessageDialog(null, String.format("Add %d departments successfully.", dsPhongBanThem.size()));
                } else {
                    JOptionPane.showMessageDialog(null, String.format("Đã thêm %d phòng ban thành công.", dsPhongBanThem.size()));
                }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
        }
        return dsPhongBanThem;
    }

}
