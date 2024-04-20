/*
 *@author Trần Thị Thanh Tuyền code giao diện
 */

package com.product.salary.client.view.department;

import com.product.salary.client.common.SystemConstants;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PhongBanForm extends JPanel {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("app");

    private final JTextField txtMaPhongBan;
    private final JTextField txtTenPhongBan;
    private final JTextField txtSoLuongNhanVien;
    private final DefaultTableModel tblModelPhongBan;
    private final JTable tblPhongBan;
    private final JButton btnCapNhat;
    private final JButton btnXoa;
    private final JButton btnThem;
    private final JButton btnXoaTrang;
    private final DefaultTableModel tblModelNhanVienCongNhan;
    private final JTable tblDanhSachNhanVien;
    private List<PhongBan> danhSachPhongBan;
    private final JLabel lblLoiTenPhongBan;
    private final JComboBox cmbTrangThai;
    private List<NhanVien> danhSachNhanVienCuaPhongBan;
    private final JButton btnThemNhieu;

    /**
     * Create the panel.
     */
    public PhongBanForm() {

        setLayout(null);

        JPanel pnlChinh = new JPanel();
        pnlChinh.setBounds(10, 10, 1273, 821);
        add(pnlChinh);
        pnlChinh.setLayout(null);

        JLabel lblTitle = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.titlePhongBan")));
        lblTitle.setBounds(0, 0, 1250, 80);
        lblTitle.setBorder(null);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pnlChinh.add(lblTitle);

        JPanel pnlMaPB = new JPanel();
        pnlMaPB.setLayout(null);
        pnlMaPB.setBounds(684, 74, 566, 62);
        pnlChinh.add(pnlMaPB);

        JLabel lblMaPB = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.lbMaPhongBan")));
        lblMaPB.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblMaPB.setBounds(10, 11, 150, 40);
        pnlMaPB.add(lblMaPB);

        txtMaPhongBan = new JTextField();
        txtMaPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtMaPhongBan.setEnabled(false);
        txtMaPhongBan.setEditable(false);
        txtMaPhongBan.setColumns(10);
        txtMaPhongBan.setBounds(188, 11, 354, 40);
        pnlMaPB.add(txtMaPhongBan);

        JPanel pnlTenPhongBan = new JPanel();
        pnlTenPhongBan.setLayout(null);
        pnlTenPhongBan.setBounds(684, 146, 566, 94);
        pnlChinh.add(pnlTenPhongBan);

        JLabel lblTenPhongBan = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.lbTenPhongBan")));
        lblTenPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTenPhongBan.setBounds(10, 11, 150, 40);
        pnlTenPhongBan.add(lblTenPhongBan);

        txtTenPhongBan = new JTextField();
        txtTenPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtTenPhongBan.setColumns(10);
        txtTenPhongBan.setBounds(188, 11, 354, 40);
        pnlTenPhongBan.add(txtTenPhongBan);

        lblLoiTenPhongBan = new JLabel(" ");
        lblLoiTenPhongBan.setForeground(Color.RED);
        lblLoiTenPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiTenPhongBan.setBounds(188, 60, 354, 24);
        pnlTenPhongBan.add(lblLoiTenPhongBan);

        JPanel pnlSoLuongNhanVien = new JPanel();
        pnlSoLuongNhanVien.setLayout(null);
        pnlSoLuongNhanVien.setBounds(684, 250, 566, 62);
        pnlChinh.add(pnlSoLuongNhanVien);

        JLabel lblSoLuongNhanVien = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.lbSoLuongNhanVien")));
        lblSoLuongNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblSoLuongNhanVien.setBounds(10, 11, 150, 40);
        pnlSoLuongNhanVien.add(lblSoLuongNhanVien);

        txtSoLuongNhanVien = new JTextField();
        txtSoLuongNhanVien.setEnabled(false);
        txtSoLuongNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtSoLuongNhanVien.setColumns(10);
        txtSoLuongNhanVien.setBounds(188, 11, 354, 40);
        pnlSoLuongNhanVien.add(txtSoLuongNhanVien);

        btnXoaTrang = new JButton(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.btnXoaTrang")));
        btnXoaTrang.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
        btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnXoaTrang.setBounds(1073, 393, 190, 44);
        pnlChinh.add(btnXoaTrang);

        btnThem = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnThem")));
        btnThem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
        btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnThem.setBounds(657, 393, 190, 44);
        pnlChinh.add(btnThem);

        btnXoa = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnXoa")));
        btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
        btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnXoa.setBounds(990, 460, 190, 44);
        pnlChinh.add(btnXoa);

        btnCapNhat = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnCapNhat")));
        btnCapNhat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
        btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnCapNhat.setBounds(753, 460, 190, 44);
        pnlChinh.add(btnCapNhat);

        JLabel lblDanhSachPhongBan = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.lbDanhSachPhongBan")));
        lblDanhSachPhongBan.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblDanhSachPhongBan.setBounds(10, 79, 284, 36);
        pnlChinh.add(lblDanhSachPhongBan);

        JLabel lblDanhSachNhanVien = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.lbDanhSachNhanVienCongNhan")));
        lblDanhSachNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblDanhSachNhanVien.setBounds(10, 501, 350, 36);
        pnlChinh.add(lblDanhSachNhanVien);

        JLabel lblThongTinPhongBan = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.lbThongTinPhongBan")));
        lblThongTinPhongBan.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblThongTinPhongBan.setBounds(692, 79, 284, 36);
        pnlChinh.add(lblThongTinPhongBan);

        tblModelNhanVienCongNhan = new DefaultTableModel(new String[]{
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.stt")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.maSo")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.hoTen")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.CCCD")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.ngaySinh")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.ngayVaoLam")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.dienThoai")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.gioiTinh")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.email")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.diaChi")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tableDanhSachNhanVien.trangThai"))},
                10);

        tblDanhSachNhanVien = new JTable(tblModelNhanVienCongNhan);
        tblDanhSachNhanVien.setShowVerticalLines(true);
        tblDanhSachNhanVien.setShowHorizontalLines(true);
        tblDanhSachNhanVien.setRowHeight(25);

        JScrollPane scrDanhSachNhanVien = new JScrollPane(tblDanhSachNhanVien);
        scrDanhSachNhanVien.setSize(1273, 285);
        scrDanhSachNhanVien.setLocation(0, 536);
        tblDanhSachNhanVien.setBounds(0, 570, 1273, 263);
        pnlChinh.add(scrDanhSachNhanVien);

        tblModelPhongBan = new DefaultTableModel(new String[]{
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.stt")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.maPhongBan")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.tenPhongBan")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.soLuongNhanVien")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.trangThai"))},
                15);

        tblPhongBan = new JTable(tblModelPhongBan);
        tblPhongBan.setShowVerticalLines(true);
        tblPhongBan.setShowHorizontalLines(true);
        tblPhongBan.setRowHeight(25);

        JScrollPane scrPhongBan = new JScrollPane(tblPhongBan);
        scrPhongBan.setLocation(10, 117);
        scrPhongBan.setSize(578, 362);
        tblPhongBan.setBounds(0, 570, 1273, 263);
        pnlChinh.add(scrPhongBan);

        JPanel pnlTrangThai = new JPanel();
        pnlTrangThai.setLayout(null);
        pnlTrangThai.setBounds(684, 321, 566, 62);
        pnlChinh.add(pnlTrangThai);

        JLabel lblTrangThai = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.trangThai")));
        lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTrangThai.setBounds(10, 11, 150, 40);
        pnlTrangThai.add(lblTrangThai);

        cmbTrangThai = new JComboBox();
        cmbTrangThai.setModel(new DefaultComboBoxModel(new String[]{"Đang hoạt động", "Ngừng hoạt động"}));
        cmbTrangThai.setBounds(189, 10, 352, 41);
        pnlTrangThai.add(cmbTrangThai);

        btnThemNhieu = new JButton(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.btnThemNhieu")));
        btnThemNhieu.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
        btnThemNhieu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnThemNhieu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnThemNhieu.setBounds(865, 393, 190, 44);
        pnlChinh.add(btnThemNhieu);

        init();
        event();
    }

    private void init() {
        danhSachPhongBan = new ArrayList<PhongBan>();
        danhSachNhanVienCuaPhongBan = new ArrayList<NhanVien>();
        loadTablePhongBan();
    }

    private void event() {
        btnXoaTrang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienChucNangLamMoi();

            }
        });
        btnThem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienChucNangThem();
            }
        });
//        btnThemNhieu.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                thucHienChucNangThemNhieu();
//
//            }
//        });
        btnXoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienChucNangXoa();

            }
        });
        btnCapNhat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienChucNangCapNhat();

            }
        });

        tblPhongBan.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                thucHienChucNangChonPhongBanTable();
            }
        });
        tblDanhSachNhanVien.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

//    private void thucHienChucNangThemNhieu() {
//        List<PhongBan> dsPhongBan = PhongBanExcelUtils.importExcelPhongBan();
//        if (!dsPhongBan.isEmpty()) {
//            danhSachPhongBan = phongBanService.themNhieuPhongBan(dsPhongBan);
//
//            thucHienChucNangLamMoi();
//        }
//    }

    private void thucHienChucNangCapNhat() {
        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream());
            ) {
                int select = tblPhongBan.getSelectedRow();
                if (select < 0) {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoChonPhongBanCapNhat")));
                    return;
                }
                String maPhongBan = txtMaPhongBan.getText().trim();
                String tenPhongBan = txtTenPhongBan.getText().trim();
                String trangThai = (String) cmbTrangThai.getSelectedItem();
                boolean isTrangThai = false;
                if (trangThai == "Đang hoạt động")
                    isTrangThai = true;
                int soLuongNhanVien = Integer.parseInt(txtSoLuongNhanVien.getText().trim());

                try {
                    PhongBan phongBan = new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien, isTrangThai);

                    //send data
                    ResponseDTO response = new ResponseDTO();
                    RequestDTO request = RequestDTO.builder()
                            .requestType("PhongBanForm")
                            .request("capNhatPhongBan")
                            .data(phongBan)
                            .build();
                    String json = AppUtils.GSON.toJson(request);
                    dos.writeUTF(json);
                    dos.flush();

                    //receive data
                    json = new String(dis.readAllBytes());
                    response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                    phongBan = AppUtils.convert((Map<String, Object>) response.getData(), PhongBan.class);
                    if (phongBan == null) {
                        JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoCapNhatKhongThanhCong")));
                    } else {
                        JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoCapNhatThanhCong")));
                        this.thucHienChucNangLamMoi();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void thucHienChucNangXoa() {
        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream());
            ) {
                int select = tblPhongBan.getSelectedRow();
                if (select < 0) {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoChonPhongBanXoa")));
                    return;
                }
                PhongBan phongBan = danhSachPhongBan.get(select);
                int choose = -1;
                if (SystemConstants.LANGUAGE == 1) {
                    choose = JOptionPane.showConfirmDialog(this,
                            "You want delete department " + phongBan.getTenPhongBan() + " ?.", "Confirm",
                            JOptionPane.YES_NO_OPTION);
                } else {
                    choose = JOptionPane.showConfirmDialog(this,
                            "Bạn có muốn xóa phòng ban " + phongBan.getTenPhongBan() + " ?.", "Xác nhận",
                            JOptionPane.YES_NO_OPTION);
                }

                if(choose == JOptionPane.YES_OPTION){
                    try {
                        RequestDTO request = RequestDTO.builder()
                                .requestType("PhongBanForm")
                                .request("xoaPhongBan")
                                .data(phongBan)
                                .build();

                        String json = AppUtils.GSON.toJson(request);
                        dos.writeUTF(json);
                        dos.flush();

                        //Receive data
                        json = new String(dis.readAllBytes());
                        ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                        phongBan = AppUtils.convert((Map<String, Object>) response.getData(), PhongBan.class);

                        if (phongBan == null) {
                            JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                    SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoXoaKhongThanhCong")));
                        } else {
                            JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                    SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoXoaThanhCong")));
                            thucHienChucNangLamMoi();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void thucHienChucNangChonPhongBanTable() {
//        int select = tblPhongBan.getSelectedRow();
//
//        if (select >= 0) {
//            PhongBan phongBan = danhSachPhongBan.get(select);
//
//            txtMaPhongBan.setText(phongBan.getMaPhongBan());
//            txtTenPhongBan.setText(phongBan.getTenPhongBan());
//            txtSoLuongNhanVien.setText(phongBan.getSoLuongNhanVien() + "");
//            if (phongBan.isTrangThai() == true)
//                cmbTrangThai.setSelectedItem("Đang hoạt động");
//            else
//                cmbTrangThai.setSelectedItem("Ngừng hoạt động");
//
//            danhSachNhanVienCuaPhongBan = nhanVienService.timKiemNhanVienBangMaPhongBan(phongBan.getMaPhongBan());
//            loadTableNhanVienCuaPhongBan(danhSachNhanVienCuaPhongBan);
//        }
    }

    private void loadTableNhanVienCuaPhongBan(List<NhanVien> dsNhanVien) {
        tblModelNhanVienCongNhan.setRowCount(0);
        int stt = 1;
        for (NhanVien nhanVien : dsNhanVien) {
            String gioiTinh = "";
            if (nhanVien.getGioiTinh() == 0)
                gioiTinh = "Nữ";
            else
                gioiTinh = "Nam";

            String trangThai = "";
            if (nhanVien.isTrangThai() == true)
                trangThai = "Đang làm";
            else
                trangThai = "Đã nghỉ làm";
            tblModelNhanVienCongNhan.addRow(new Object[]{stt++, nhanVien.getMaNhanVien(), nhanVien.getHoTen(),
                    nhanVien.getCccd(), nhanVien.getNgaySinh(), nhanVien.getNgayVaoLam(), nhanVien.getSoDienThoai(),
                    gioiTinh, nhanVien.getEmail(), nhanVien.getDiaChi(), trangThai});
        }
    }

    private void thucHienChucNangThem() {
        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream());
            ) {


                String tenPhongBan = txtTenPhongBan.getText().trim();
                String trangThai = (String) cmbTrangThai.getSelectedItem();
                boolean isTrangThai = false;
                if (trangThai == "Đang hoạt động")
                    isTrangThai = true;

                if (!thucHienChucNangKiemTra()) {
                    return;
                }

                try {
                    PhongBan phongBan = new PhongBan("xxx", tenPhongBan, 0, isTrangThai);

                    System.out.println(phongBan);

                    RequestDTO request = RequestDTO.builder()
                            .requestType("PhongBanForm")
                            .request("themPhongBan")
                            .data(phongBan)
                            .build();

                    System.out.println("Sending request: " + request);
                    String json = AppUtils.GSON.toJson(request);

                    dos.writeUTF(json);
                    dos.flush();

                    //Receive data
                    json = new String(dis.readAllBytes());
                    ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                    System.out.println("Receive response: " + response);

                    phongBan = AppUtils.convert((Map<String, Object>) response.getData(), PhongBan.class);


                    if (phongBan == null) {
                        JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoThemKhongThanhCong")));
                    } else {
                        JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoThemThanhCong")));
                        thucHienChucNangLamMoi();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean thucHienChucNangKiemTra() {
        lblLoiTenPhongBan.setText("");

        String tenPhongBan = txtTenPhongBan.getText().trim();
        boolean status = true;

        if (ObjectUtils.isEmpty(tenPhongBan)) {
            lblLoiTenPhongBan.setText(String.format("<html><p>%s</p></html>",
                    SystemConstants.BUNDLE.getString("quanLyPhongBan.thongBaoLoiTenPhongBan")));
            status = false;
        }
        return status;
    }

    private void thucHienChucNangLamMoi() {
        txtMaPhongBan.setText("");
        txtTenPhongBan.setText("");
        txtSoLuongNhanVien.setText("");
        cmbTrangThai.setSelectedIndex(0);
        tblModelNhanVienCongNhan.setRowCount(0);
        loadTablePhongBan();
    }

    private void loadTablePhongBan() {
        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream());
            ) {
                //Send data
                RequestDTO request = RequestDTO.builder()
                        .requestType("PhongBanForm")
                        .request("timKiemTatCaPhongBan")
                        .build();

//                System.out.println("Sending request: " + request);
                String json = AppUtils.GSON.toJson(request);

                dos.writeUTF(json);
                dos.flush();

                //Receive data
                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
//                System.out.println("Receive response: " + response);

                List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();

                danhSachPhongBan = data.stream().map((value) ->
                        AppUtils.convert(value, PhongBan.class)).collect(Collectors.toList());

                tblModelPhongBan.setRowCount(0);
                int stt = 1;
                for (PhongBan phongBan : danhSachPhongBan) {
                    String trangThai = "";
                    if (phongBan.isTrangThai() == true)
                        trangThai = "Đang hoạt động";
                    else
                        trangThai = "Ngừng hoạt động";
                    tblModelPhongBan.addRow(new Object[]{stt++, phongBan.getMaPhongBan(), phongBan.getTenPhongBan(),
                            phongBan.getSoLuongNhanVien(), trangThai});
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
