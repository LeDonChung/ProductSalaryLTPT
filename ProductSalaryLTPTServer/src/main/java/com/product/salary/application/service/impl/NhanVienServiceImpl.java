package com.product.salary.application.service.impl;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.dao.NhanVienDAO;
import com.product.salary.application.dao.PhongBanDAO;
import com.product.salary.application.dao.impl.NhanVienDAOImpl;
import com.product.salary.application.dao.impl.PhongBanDAOImpl;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.service.NhanVienService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienServiceImpl implements NhanVienService {
    private NhanVienDAO nhanVienDAO;
    private PhongBanDAO phongBanDAO;

    public NhanVienServiceImpl() {
        this.nhanVienDAO = new NhanVienDAOImpl();
        this.phongBanDAO = new PhongBanDAOImpl();
    }

    @Override
    public List<NhanVien> timKiemTatCaNhanVien() {
        List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try {
            dsNhanVien = nhanVienDAO.timKiemTatCaNhanVien();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    @Override
    public synchronized NhanVien capNhatNhanVien(NhanVien nhanVien) {
        try {

            NhanVien isExists = nhanVienDAO.timKiemBangMaNhanVien(nhanVien.getMaNhanVien());
            if (isExists == null) {
                JOptionPane.showMessageDialog(null, String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyNhanVien.thongBaoNhanVienKhongTonTai")));
                return null;
            }

            if (!nhanVien.getPhongBan().getMaPhongBan().equals(isExists.getPhongBan().getMaPhongBan())) {
                PhongBan pb = phongBanDAO.timKiemBangMaPhongBan(nhanVien.getPhongBan().getMaPhongBan());
                if (pb.isTrangThai() == false) {
                    JOptionPane.showMessageDialog(null, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("quanLyNhanVien.thongBaoPhongBanKhongHoatDong")));
                    return null;
                }

                phongBanDAO.capNhatSoLuongNhanVienBangMaPhongBan(nhanVien.getPhongBan().getMaPhongBan(),
                        nhanVien.getPhongBan().getSoLuongNhanVien() + 1);

                phongBanDAO.capNhatSoLuongNhanVienBangMaPhongBan(isExists.getPhongBan().getMaPhongBan(),
                        isExists.getPhongBan().getSoLuongNhanVien() - 1);
            }

            if (nhanVien.isTrangThai() != isExists.isTrangThai()) {
                PhongBan phBan = phongBanDAO.timKiemBangMaPhongBan(nhanVien.getPhongBan().getMaPhongBan());
                if (nhanVien.isTrangThai())
                    phongBanDAO.capNhatSoLuongNhanVienBangMaPhongBan(phBan.getMaPhongBan(),
                            phBan.getSoLuongNhanVien() + 1);
                else
                    phongBanDAO.capNhatSoLuongNhanVienBangMaPhongBan(phBan.getMaPhongBan(),
                            phBan.getSoLuongNhanVien() - 1);
            }

            isExists.setNgayVaoLam(nhanVien.getNgayVaoLam());
            isExists.setHeSoLuong(nhanVien.getHeSoLuong());
            isExists.setLuongCoSo(nhanVien.getLuongCoSo());
            isExists.setPhongBan(nhanVien.getPhongBan());
            isExists.setTrinhDo(nhanVien.getTrinhDo());
            isExists.setChucVu(nhanVien.getChucVu());
            isExists.setTrangThai(nhanVien.isTrangThai());
            isExists.setSoDienThoai(nhanVien.getSoDienThoai());
            isExists.setDiaChi(nhanVien.getDiaChi());
            isExists.setTroCap(nhanVien.getTroCap());
            isExists.setCccd(nhanVien.getCccd());
            isExists.setEmail(nhanVien.getEmail());
            isExists.setHinhAnh(nhanVien.getHinhAnh());
            isExists.setHoTen(nhanVien.getHoTen());

            return nhanVienDAO.capNhatNhanVien(isExists);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized boolean capNhatTrangThaiNghiLamCuaNhanVien(String maNhanVien) {
        try {
            NhanVien isExists = nhanVienDAO.timKiemBangMaNhanVien(maNhanVien);
            if (isExists == null) {
                JOptionPane.showMessageDialog(null, String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyNhanVien.thongBaoNhanVienKhongTonTai")));
                return false;
            } else {
                phongBanDAO.capNhatSoLuongNhanVienBangMaPhongBan(isExists.getPhongBan().getMaPhongBan(),
                        isExists.getPhongBan().getSoLuongNhanVien() - 1);
                return nhanVienDAO.capNhatTrangThaiNghiLamCuaNhanVien(maNhanVien);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<NhanVien> timKiemNhanVien(NhanVien nhanVien) {
        List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try {
            dsNhanVien = nhanVienDAO.timKiemNhanVien(nhanVien);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    @Override
    public NhanVien timKiemBangMaNhanVien(String maNhanVien) {
        try {
            NhanVien nhanVien = nhanVienDAO.timKiemBangMaNhanVien(maNhanVien);
            if (nhanVien != null)
                return nhanVien;
            else {
                JOptionPane.showMessageDialog(null, String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyNhanVien.thongBaoNhanVienKhongTonTai")));
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized NhanVien themNhanVien(NhanVien nhanVien) {
        try {
            nhanVien.setMaNhanVien(generateMaNhanVien(nhanVien));
            nhanVien.setTrangThai(true);

            NhanVien isExists = nhanVienDAO.timKiemBangMaNhanVien(nhanVien.getMaNhanVien());
            if (isExists != null) {
                JOptionPane.showMessageDialog(null,
                        SystemConstants.BUNDLE.getString("quanLyNhanVien.thongBaoNhanVienDaTonTai"));
                return null;
            } else {
                PhongBan pb = phongBanDAO.timKiemBangMaPhongBan(nhanVien.getPhongBan().getMaPhongBan());
                if (pb.isTrangThai() == false) {
                    JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE
                            .getString("quanLyNhanVien.thongBaoPhongBanKhongHoatDongKhongTheThemNhanVien"));
                    return null;
                }

                List<NhanVien> dsNhanVien = nhanVienDAO.timKiemTatCaNhanVien();
                int kiemTraCccd = 0;
                for (NhanVien nhanVien2 : dsNhanVien) {
                    if (nhanVien2.getCccd().equals(nhanVien.getCccd()))
                        kiemTraCccd = 1;
                }
                if (kiemTraCccd == 1) {
                    if (SystemConstants.LANGUAGE == 1) {
                        JOptionPane.showMessageDialog(null, "ID Card '" + nhanVien.getCccd() + "' of employee "
                                + nhanVien.getHoTen() + " is exists!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Căn cước công dân '" + nhanVien.getCccd()
                                + "' của nhân viên " + nhanVien.getHoTen() + " đã tồn tại!");
                    }

                    return null;
                }

                NhanVien nv = nhanVienDAO.themNhanVien(nhanVien);
                if (nv.isTrangThai() == true) {
                    phongBanDAO.capNhatSoLuongNhanVienBangMaPhongBan(pb.getMaPhongBan(),
                            pb.getSoLuongNhanVien() + 1);
                }
                return nv;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized String generateMaNhanVien(NhanVien nhanVien) {
        // 10 là số nhận dạng là nhân viên, XXXX năm vào làm của nhân viên, XXXX là 4 số
        // tự tăng trong năm vào làm của nv

        String maNhanVienCuoiCung = nhanVienDAO.layMaNhanVienCuoiCungCuaNam(nhanVien.getNgayVaoLam().getYear());
        String maNhanVien = "";
        if (maNhanVienCuoiCung != null) {
            int soTuTang = Integer.parseInt(maNhanVienCuoiCung.substring(6));
            maNhanVien = String.format("10%04d%04d", nhanVien.getNgayVaoLam().getYear(), soTuTang + 1);
        } else {
            int soTuTang = 0;
            maNhanVien = String.format("10%04d%04d", nhanVien.getNgayVaoLam().getYear(), soTuTang + 1);
        }

        return maNhanVien;
    }

    @Override
    public List<NhanVien> timKiemNhanVienBangMaPhongBan(String maPhongBan) {
        List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try {
            dsNhanVien = nhanVienDAO.timKiemNhanVienBangMaPhongBan(maPhongBan);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    @Override
    public int tongSoLuongNhanVien() {
        int soLuong = 0;
        try {
            soLuong = this.nhanVienDAO.tongSoLuongNhanVien();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
            e.printStackTrace();
        }
        return soLuong;
    }

    @Override
    public synchronized List<NhanVien> themNhieuNhanVien(List<NhanVien> dsNhanVien) {
        List<NhanVien> danhSachNhanVienThem = new ArrayList<NhanVien>();
        try {
            for (NhanVien nhanVien : dsNhanVien) {
//				String maNhanVien = generateMaNhanVien(nhanVien);
//				nhanVien.setMaNhanVien(maNhanVien);

                NhanVien nhanVienThem = themNhanVien(nhanVien);
                if (nhanVienThem != null)
                    danhSachNhanVienThem.add(nhanVienThem);
            }

            if (danhSachNhanVienThem.size() != 0) {
                if (SystemConstants.LANGUAGE == 1) {
                    JOptionPane.showMessageDialog(null,
                            String.format("Add %d employees successfilly.", danhSachNhanVienThem.size()));
                } else {
                    JOptionPane.showMessageDialog(null,
                            String.format("Thêm thành công %d nhân viên.", danhSachNhanVienThem.size()));
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi.");
            e.printStackTrace();
        }
        return danhSachNhanVienThem;
    }

    @Override
    public synchronized NhanVien timKiemBangMaNhanVienVaCccd(String maNhanVien, String cccd) {
        try {
            NhanVien nhanVien = nhanVienDAO.timKiemBangMaNhanVienVaCccd(maNhanVien, cccd);
            if (nhanVien != null)
                return nhanVien;
            else {
                JOptionPane.showMessageDialog(null,
                        SystemConstants.BUNDLE.getString("quanLyNhanVien.thongBaoNhanVienKhongTonTai"));
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
            e.printStackTrace();
            return null;
        }
    }
}
