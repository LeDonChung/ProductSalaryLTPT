package com.product.salary.client.view.employee;

/*
  @author Trần Thị Thanh Tuyề xư lý các chức năng
 */

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.CaLam;
import com.product.salary.application.entity.ChamCongNhanVien;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.service.ChamCongNhanVienService;
import com.product.salary.application.service.impl.ChamCongNhanVienServiceImpl;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.DateConvertUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ChamCongNhanVienForm extends JPanel {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
    private final JTextField txtMaNhanVien;
    private final JTextField txtHoTen;
    private final JTextField txtChucVu;
    private final JTextField txtSoDienThoai;
    private final JTable tblNhanVienChuaChamCong;
    private final JTable tblChamCong;
    private final JTextField txtMaChamCong;
    private final JButton btnChamCong;
    private final JButton btnCapNhat;
    private final JButton btnLamMoi;
    private final DefaultTableModel tblModelChamCong;
    private final JDateChooser jcNgayChamCong;
    private final JComboBox cmbCaLam;
    private final JButton btnLayDanhSach;
    private final DefaultTableModel tblModelNhanVienChuaChamCong;
    private final JComboBox cmbTrangThai;
    private ChamCongNhanVienService chamCongNhanVienService;
    private List<ChamCongNhanVien> danhSachChamCong;
    private List<NhanVien> danhSachNhanVienChuaChamCong;
    private final JButton btnChamCongTatCa;

    /**
     * Create the panel.
     */
    public ChamCongNhanVienForm() {

        setLayout(null);

        JPanel pnlMain = new JPanel();
        pnlMain.setBounds(10, 10, 1250, 825);
        pnlMain.setLayout(null);
        add(pnlMain);
        JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("chamCongNhanVien.title"));
        lblTitle.setBorder(null);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setBounds(0, 0, 1250, 80);
        pnlMain.add(lblTitle);

        JPanel pnlNgayChamVaCaLam = new JPanel();
        pnlNgayChamVaCaLam.setBounds(10, 81, 700, 70);
        pnlMain.add(pnlNgayChamVaCaLam);
        pnlNgayChamVaCaLam.setLayout(null);

        JPanel pnlThongTinChamCong = new JPanel();
        pnlThongTinChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlThongTinChamCong.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlThongTinChamCong.setBounds(720, 103, 520, 369);
        pnlMain.add(pnlThongTinChamCong);
        pnlThongTinChamCong.setLayout(null);

        JPanel pnlMaNhanVien = new JPanel();
        pnlMaNhanVien.setBounds(10, 70, 500, 50);
        pnlThongTinChamCong.add(pnlMaNhanVien);
        pnlMaNhanVien.setLayout(null);
        JLabel lblMaNhanVien = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("chamCongNhanVien.lbMaNhanVien")));
        lblMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblMaNhanVien.setBounds(0, 0, 95, 50);
        pnlMaNhanVien.add(lblMaNhanVien);

        txtMaNhanVien = new JTextField();
        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtMaNhanVien.setBounds(105, 0, 385, 50);
        pnlMaNhanVien.add(txtMaNhanVien);
        txtMaNhanVien.setColumns(10);

        JPanel pnlHoTen = new JPanel();
        pnlHoTen.setLayout(null);
        pnlHoTen.setBounds(10, 130, 500, 50);
        pnlThongTinChamCong.add(pnlHoTen);

        JLabel lblHoTen = new JLabel(SystemConstants.BUNDLE.getString("chamCongNhanVien.lbHoTen"));
        lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblHoTen.setBounds(0, 0, 95, 50);
        pnlHoTen.add(lblHoTen);

        txtHoTen = new JTextField();
        txtHoTen.setEditable(false);
        txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtHoTen.setColumns(10);
        txtHoTen.setBounds(105, 0, 385, 50);
        pnlHoTen.add(txtHoTen);

        JPanel pnlChucVu = new JPanel();
        pnlChucVu.setLayout(null);
        pnlChucVu.setBounds(10, 190, 500, 50);
        pnlThongTinChamCong.add(pnlChucVu);

        JLabel lblChucVu = new JLabel(SystemConstants.BUNDLE.getString("chamCongNhanVien.lbChucVu"));
        lblChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblChucVu.setBounds(0, 0, 95, 50);
        pnlChucVu.add(lblChucVu);

        txtChucVu = new JTextField();
        txtChucVu.setEditable(false);
        txtChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtChucVu.setColumns(10);
        txtChucVu.setBounds(105, 0, 385, 50);
        pnlChucVu.add(txtChucVu);

        JPanel pnlSoDienThoai = new JPanel();
        pnlSoDienThoai.setLayout(null);
        pnlSoDienThoai.setBounds(10, 250, 500, 50);
        pnlThongTinChamCong.add(pnlSoDienThoai);

        JLabel lblSoDienThoai = new JLabel(SystemConstants.BUNDLE.getString("chamCongNhanVien.lbSoDienThoai"));
        lblSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblSoDienThoai.setBounds(0, 0, 95, 50);
        pnlSoDienThoai.add(lblSoDienThoai);

        txtSoDienThoai = new JTextField();
        txtSoDienThoai.setEditable(false);
        txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtSoDienThoai.setColumns(10);
        txtSoDienThoai.setBounds(105, 0, 385, 50);
        pnlSoDienThoai.add(txtSoDienThoai);

        JPanel pnlTrangThai = new JPanel();
        pnlTrangThai.setLayout(null);
        pnlTrangThai.setBounds(10, 310, 500, 50);
        pnlThongTinChamCong.add(pnlTrangThai);

        JLabel lblTrangThai = new JLabel(SystemConstants.BUNDLE.getString("chamCongNhanVien.lbTrangThai"));
        lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTrangThai.setBounds(0, 0, 95, 50);
        pnlTrangThai.add(lblTrangThai);

        cmbTrangThai = new JComboBox();
        cmbTrangThai.setModel(new DefaultComboBoxModel(new String[]{"Có mặt", "Đi trễ", "Nghỉ"}));
        cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbTrangThai.setBounds(106, 0, 384, 50);
        pnlTrangThai.add(cmbTrangThai);

        JPanel pnlMaChamCong = new JPanel();
        pnlMaChamCong.setLayout(null);
        pnlMaChamCong.setBounds(10, 10, 500, 50);
        pnlThongTinChamCong.add(pnlMaChamCong);

        JLabel lblMaChamCong = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("chamCongNhanVien.lbMaChamCong")));
        lblMaChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblMaChamCong.setBounds(0, 0, 95, 50);
        pnlMaChamCong.add(lblMaChamCong);

        txtMaChamCong = new JTextField();
        txtMaChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtMaChamCong.setEditable(false);
        txtMaChamCong.setColumns(10);
        txtMaChamCong.setBounds(105, 0, 385, 50);
        pnlMaChamCong.add(txtMaChamCong);

        JPanel pnlDanhSachChamCong = new JPanel();
        pnlDanhSachChamCong.setBounds(10, 556, 1230, 259);
        pnlMain.add(pnlDanhSachChamCong);
        pnlDanhSachChamCong.setLayout(null);

        tblModelChamCong = new DefaultTableModel(
                new String[]{SystemConstants.BUNDLE.getString("chamCongNhanVien.table.stt"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.maChamCong"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.maNhanVien"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.hoTen"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.gioiTinh"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.soDienThoai"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.phongBan"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.chucVu"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.caLam"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.ngayCham"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.trangThai")},
                0);
        tblChamCong = new JTable(tblModelChamCong);
        tblChamCong.setShowVerticalLines(true);
        tblChamCong.setShowHorizontalLines(true);
        tblChamCong.setRowHeight(25);
        tblChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        tblChamCong.setBounds(0, 0, 681, 275);
        JScrollPane scrDanhSachChamCong = new JScrollPane(tblChamCong);
        scrDanhSachChamCong.setBounds(0, 0, 1230, 259);

        pnlDanhSachChamCong.add(scrDanhSachChamCong);
        JPanel pnlCacNutChucNang = new JPanel();
        pnlCacNutChucNang.setBounds(505, 482, 738, 64);
        pnlMain.add(pnlCacNutChucNang);
        pnlCacNutChucNang.setLayout(null);

        btnChamCong = new JButton(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("chamCongNhanVien.btnChamCong")));
        btnChamCong.setBounds(217, 7, 154, 50);
        btnChamCong
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_check.png"));
        btnChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlCacNutChucNang.add(btnChamCong);

        btnCapNhat = new JButton(SystemConstants.BUNDLE.getString("chamCongNhanVien.btnCapNhat"));
        btnCapNhat.setBounds(395, 7, 154, 50);
        btnCapNhat
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
        btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlCacNutChucNang.add(btnCapNhat);

        btnLamMoi = new JButton(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("chamCongNhanVien.btnLamMoi")));
        btnLamMoi.setBounds(574, 7, 154, 50);
        btnLamMoi
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
        btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlCacNutChucNang.add(btnLamMoi);

        btnChamCongTatCa = new JButton(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("chamCongNhanVien.btnChamCongTatCa")));
        btnChamCongTatCa
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_check.png"));
        btnChamCongTatCa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnChamCongTatCa.setBounds(12, 7, 181, 50);
        pnlCacNutChucNang.add(btnChamCongTatCa);

        JPanel pnlDanhSachNhanVienChamCong = new JPanel();
        pnlDanhSachNhanVienChamCong.setBounds(10, 184, 700, 286);
        pnlMain.add(pnlDanhSachNhanVienChamCong);
        pnlDanhSachNhanVienChamCong.setLayout(null);

        tblModelNhanVienChuaChamCong = new DefaultTableModel(
                new String[]{SystemConstants.BUNDLE.getString("chamCongNhanVien.table.stt"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.maNhanVien"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.hoTen"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.gioiTinh"),
                        SystemConstants.BUNDLE.getString("chamCongNhanVien.table.soDienThoai"), "Nghỉ", "Đi trễ"},
                0);

//		TableColumn columnNghi = tblNhanVienChuaChamCong.getColumnModel().getColumn(5);
//		JCheckBox checkTrangThaiNghiLam = new JCheckBox();
//		columnNghi.setCellEditor(new DefaultCellEditor(checkTrangThaiNghiLam));

        tblNhanVienChuaChamCong = new JTable(tblModelNhanVienChuaChamCong) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) {
                    return Boolean.class;
                }

                if (column == 6) {
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };
        tblNhanVienChuaChamCong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblNhanVienChuaChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        tblNhanVienChuaChamCong.setShowHorizontalLines(true);
        tblNhanVienChuaChamCong.setShowVerticalLines(true);
        tblNhanVienChuaChamCong.setRowHeight(25);
        JScrollPane scrDanhSachChuaChamCong = new JScrollPane(tblNhanVienChuaChamCong);
        scrDanhSachChuaChamCong.setBounds(0, 0, 700, 286);
        pnlDanhSachNhanVienChamCong.add(scrDanhSachChuaChamCong);

        jcNgayChamCong = new JDateChooser();
        jcNgayChamCong.getCalendarButton().setIcon(
                new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
        jcNgayChamCong.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
        jcNgayChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        jcNgayChamCong.setDateFormatString("yyyy-MM-dd");
        jcNgayChamCong.setLocation(101, 10);
        jcNgayChamCong.setSize(158, 50);
        jcNgayChamCong.setDate(DateConvertUtils.asUtilDate(LocalDate.now(), ZoneId.systemDefault()));
        pnlNgayChamVaCaLam.add(jcNgayChamCong);

        JLabel lblNgayChamCong = new JLabel(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("chamCongNhanVien.lbNgayCham")));
        lblNgayChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNgayChamCong.setBounds(10, 10, 81, 50);
        pnlNgayChamVaCaLam.add(lblNgayChamCong);

        JLabel lblCaLam = new JLabel(SystemConstants.BUNDLE.getString("chamCongNhanVien.lbCaLam"));
        lblCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblCaLam.setBounds(300, 10, 56, 50);
        pnlNgayChamVaCaLam.add(lblCaLam);

        cmbCaLam = new JComboBox();
        cmbCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbCaLam.setModel(new DefaultComboBoxModel(new String[]{"Sáng", "Chiều", "Tối"}));
        cmbCaLam.setBounds(377, 10, 121, 50);
        pnlNgayChamVaCaLam.add(cmbCaLam);

        btnLayDanhSach = new JButton(SystemConstants.BUNDLE.getString("chamCongNhanVien.btnLayDanhSach"));
        btnLayDanhSach
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_search.png"));
        btnLayDanhSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnLayDanhSach.setBounds(532, 10, 158, 50);
        pnlNgayChamVaCaLam.add(btnLayDanhSach);

        JLabel lblThongTinChamCong = new JLabel(
                SystemConstants.BUNDLE.getString("chamCongNhanVien.lbTitleThongTinChamCong"));
        lblThongTinChamCong.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblThongTinChamCong.setBounds(718, 81, 522, 22);
        pnlMain.add(lblThongTinChamCong);

        JLabel lblDanhSachChuaChamCong = new JLabel(
                SystemConstants.BUNDLE.getString("chamCongNhanVien.lbTitleDanhSachChuaChamCong"));
        lblDanhSachChuaChamCong.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblDanhSachChuaChamCong.setBounds(10, 160, 522, 22);
        pnlMain.add(lblDanhSachChuaChamCong);

        JLabel lblDanhSachChamCong = new JLabel(
                SystemConstants.BUNDLE.getString("chamCongNhanVien.lbTitleDanhSachChamCong"));
        lblDanhSachChamCong.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblDanhSachChamCong.setBounds(10, 530, 522, 22);
        pnlMain.add(lblDanhSachChamCong);

        init();
        event();
    }

    public void init() {
        this.chamCongNhanVienService = new ChamCongNhanVienServiceImpl();
        this.danhSachChamCong = new ArrayList<ChamCongNhanVien>();

        this.danhSachNhanVienChuaChamCong = new ArrayList<NhanVien>();
    }

    public void event() {
        tblChamCong.addMouseListener(new MouseListener() {

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
                thucHienChonTrenBangChamCongNhanVien();

            }
        });

        tblNhanVienChuaChamCong.addMouseListener(new MouseListener() {

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
                thucHienChucNangChonNhanVienTuBangNhanVienChuaChamCong();

            }
        });

        btnCapNhat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienChucNangCapNhat();

            }
        });

        btnChamCong.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienChucNangChamCong();

            }
        });

        btnLamMoi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienChucNangLamMoi();

            }
        });

        btnLayDanhSach.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienChucNangLayDanhSachChamCong();

            }
        });

        btnChamCongTatCa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienChucNangChamCongTatCa();

            }
        });
    }

    private void thucHienChucNangChamCongTatCa() {
        new Thread(() -> {
            try (var socket = new Socket(BUNDLE.getString("host"), Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())){
                if (danhSachNhanVienChuaChamCong.size() == 0) {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("chamCongNhanVien.thongBaoChonDanhSachNhanVienChuaChamCong")));
                    return;
                }

                if (jcNgayChamCong.getDate() == null) {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("chamCongNhanVien.thongBaoChonNgayChamCong")));
                    return;
                }
                LocalDate ngayChamCong = DateConvertUtils.asLocalDate(jcNgayChamCong.getDate(), ZoneId.systemDefault());
                String tenCa = (String) cmbCaLam.getSelectedItem();
                String maCa = tenCa.equals("Sáng") ? "SA" : tenCa.equals("Chiều") ? "CH" : "TO";
                CaLam caLam = new CaLam(maCa, tenCa);
                int soLuongChamCong = danhSachNhanVienChuaChamCong.size();
                List<ChamCongNhanVien> chamCongNhanViens = new ArrayList<ChamCongNhanVien>();
                for (int i = 0; i < soLuongChamCong; i++) {
                    ChamCongNhanVien chamCongNV;
//			tblNhanVienChuaChamCong.setRowSelectionInterval(i, i);
                    NhanVien nhanVien = danhSachNhanVienChuaChamCong.get(i);

                    int trangThaiDiLam = 1;
                    Boolean nghilam = (Boolean) tblNhanVienChuaChamCong.getValueAt(i, 5);
                    Boolean diTre = (Boolean) tblNhanVienChuaChamCong.getValueAt(i, 6);

                    if (nghilam != null && diTre != null) {
                        if (nghilam == true) {
                            trangThaiDiLam = 0;
                        } else if (nghilam == false && diTre == true) {
                            trangThaiDiLam = 2;
                        }
                    } else if (nghilam != null && diTre == null) {
                        if (nghilam == true) {
                            trangThaiDiLam = 0;
                        }
                    } else if (nghilam == null && diTre != null) {
                        if (diTre == true) {
                            trangThaiDiLam = 2;
                        }
                    }
                    try {
                        NhanVien nvcc = new NhanVien();
                        nvcc.setMaNhanVien(nhanVien.getMaNhanVien());
                        chamCongNV = new ChamCongNhanVien(null, nvcc, caLam, ngayChamCong, trangThaiDiLam);
                        chamCongNhanViens.add(chamCongNV);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                RequestDTO request = RequestDTO.builder()
                        .requestType("ChamCongNhanVienForm")
                        .request("chamCongTatCaNhanVien")
                        .data(chamCongNhanViens)
                        .build();
                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                boolean status = (boolean) response.getData();

                if (status == true) {
                    if (SystemConstants.LANGUAGE == 1) {
                        JOptionPane.showMessageDialog(this,
                                "Timekeeping for all employees of shift " + tenCa + " date " + ngayChamCong + " successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Chấm công tất cả nhân viên ca " + tenCa + " ngày " + ngayChamCong + " thành công!");
                    }

                    thucHienChucNangLamMoi();

                    jcNgayChamCong.setDate(DateConvertUtils.asUtilDate(ngayChamCong, ZoneId.systemDefault()));
                    cmbCaLam.setSelectedItem(tenCa);
                    thucHienChucNangLayDanhSachChamCong();
                    return;
                } else {
                    if (SystemConstants.LANGUAGE == 1) {
                        JOptionPane.showMessageDialog(this,
                                "Timekeeping for all employees of shift " + tenCa + " date " + ngayChamCong + " unsuccessful!");
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Chấm công tất cả nhân viên ca " + tenCa + " ngày " + ngayChamCong + " không thành công!");
                    }

                    return;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void thucHienChucNangCapNhat() {
        new Thread(() -> {
            try (var socket = new Socket(BUNDLE.getString("host"), Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())) {
                int select = tblChamCong.getSelectedRow();
                if (select >= 0) {
                    ChamCongNhanVien chamCongNV = danhSachChamCong.get(select);
                    int trangThai = cmbTrangThai.getSelectedItem().equals("Có mặt") ? 1
                            : cmbTrangThai.getSelectedItem().equals("Nghỉ") ? 0 : 2;

                    Map<String, Object> data = Map.of("maChamCong", chamCongNV.getMaChamCong(), "trangThai", trangThai);
                    RequestDTO request = RequestDTO.builder()
                            .requestType("ChamCongNhanVienForm")
                            .request("capNhatTrangThaiDiLamCuaNhanVien")
                            .data(data)
                            .build();
                    String json = AppUtils.GSON.toJson(request);
                    dos.writeUTF(json);
                    dos.flush();

                    json = new String(dis.readAllBytes());
                    ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                    boolean status = (boolean) response.getData();
                    if (status == true) {
                        if (SystemConstants.LANGUAGE == 1) {
                            JOptionPane.showMessageDialog(this, "Update employee's status timekeeping has timekeeping ID "
                                    + chamCongNV.getMaChamCong() + " successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Cập nhật trạng chấm công nhân viên có mã chấm công "
                                    + chamCongNV.getMaChamCong() + " thành công!");
                        }

                        thucHienChucNangLayDanhSachChamCong();
                        return;
                    } else {
                        if (SystemConstants.LANGUAGE == 1) {
                            JOptionPane.showMessageDialog(this, "Update employee's status timekeeping has timekeeping ID "
                                    + chamCongNV.getMaChamCong() + " unsuccessfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Cập nhật trạng chấm công nhân viên có mã chấm công "
                                    + chamCongNV.getMaChamCong() + " không thành công!");
                        }
                        thucHienChucNangLayDanhSachChamCong();
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("chamCongNhanVien.thongBaoChonChamCongCanCapNhat")));
                    return;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void thucHienChucNangChamCong() {
        new Thread(() -> {
            try (var socket = new Socket(BUNDLE.getString("host"), Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())) {

                int chonNhanVien = tblNhanVienChuaChamCong.getSelectedRow();
                if (chonNhanVien >= 0) {
                    NhanVien nhanVienChamCong = danhSachNhanVienChuaChamCong.get(chonNhanVien);

                    LocalDate ngayChamCong = DateConvertUtils.asLocalDate(jcNgayChamCong.getDate(), ZoneId.systemDefault());
                    String tenCa = (String) cmbCaLam.getSelectedItem();
                    String maCa = tenCa.equals("Sáng") ? "SA" : tenCa.equals("Chiều") ? "CH" : "TO";
                    CaLam caLam = new CaLam(maCa, tenCa);

                    // Trạng thái đi làm mặc định là có mặt, nếu check ở ô nghỉ làm thì trạng thái
                    // là 0, nếu check ở ô trễ thfi trạng thái là 2
                    // Nếu trường hợp check ở cả 2 ô thì trạng thái đi làm sẽ là nghỉ
                    int trangThaiDiLam = 1;
                    Boolean nghilam = (Boolean) tblNhanVienChuaChamCong.getValueAt(chonNhanVien, 5);
                    Boolean diTre = (Boolean) tblNhanVienChuaChamCong.getValueAt(chonNhanVien, 6);
                    if (nghilam != null && diTre != null) {
                        if (nghilam == true) {
                            trangThaiDiLam = 0;
                        } else if (nghilam == false && diTre == true) {
                            trangThaiDiLam = 2;
                        }
                    } else if (nghilam != null && diTre == null) {
                        if (nghilam == true) {
                            trangThaiDiLam = 0;
                        }
                    } else if (nghilam == null && diTre != null) {
                        if (diTre == true) {
                            trangThaiDiLam = 2;
                        }
                    }

                    if ((nghilam == null && diTre == null) || (nghilam == null && diTre == false)
                            || (nghilam == false && diTre == null) || (nghilam == false && diTre == false)) {
                        trangThaiDiLam = cmbTrangThai.getSelectedItem().equals("Nghỉ") ? 0
                                : cmbTrangThai.getSelectedItem().equals("Đi trễ") ? 2 : 1;
                    }

//                    System.out.println(nhanVienChamCong.getMaNhanVien());

                    NhanVien nvThem = new NhanVien();
                    nvThem.setMaNhanVien(nhanVienChamCong.getMaNhanVien());
                    try {
                        ChamCongNhanVien chamCongNhanVien = new ChamCongNhanVien("xxx", nvThem, caLam, ngayChamCong,
                                trangThaiDiLam);

                        RequestDTO request = RequestDTO.builder()
                                .requestType("ChamCongNhanVienForm")
                                .request("themChamCongNhanVien")
                                .data(chamCongNhanVien)
                                .build();
                        System.out.println(request);
                        String json = AppUtils.GSON.toJson(request);
                        dos.writeUTF(json);
                        dos.flush();

                        json = new String(dis.readAllBytes());
                        ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                        Map<String, Object> data = (Map<String, Object>) response.getData();
                        chamCongNhanVien = AppUtils.convert(data, ChamCongNhanVien.class);
                        System.out.println(chamCongNhanVien);
                        if (chamCongNhanVien != null) {
                            if (SystemConstants.LANGUAGE == 1) {
                                JOptionPane.showMessageDialog(this, "Timekeeping for employee has employee ID "
                                        + nhanVienChamCong.getMaNhanVien() + " successfully!");
                            } else {
                                JOptionPane.showMessageDialog(this,
                                        "Chấm công nhân viên có mã " + nhanVienChamCong.getMaNhanVien() + " thành công!");
                            }

                            thucHienChucNangLamMoi();

                            jcNgayChamCong.setDate(DateConvertUtils.asUtilDate(ngayChamCong, ZoneId.systemDefault()));
                            cmbCaLam.setSelectedItem(tenCa);
                            thucHienChucNangLayDanhSachChamCong();
                            return;
                        } else {
                            if (SystemConstants.LANGUAGE == 1) {
                                JOptionPane.showMessageDialog(this, "Timekeeping for employee has employee ID "
                                        + nhanVienChamCong.getMaNhanVien() + " unsuccessfully!");
                            } else {
                                JOptionPane.showMessageDialog(this,
                                        "Chấm công nhân viên có mã " + nhanVienChamCong.getMaNhanVien() + " không thành công!");
                            }
                            return;
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("chamCongNhanVien.thongBaoChonNhanVienCanChamCong")));
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void thucHienChucNangLamMoi() {
        jcNgayChamCong.setDate(DateConvertUtils.asUtilDate(LocalDate.now(), ZoneId.systemDefault()));
        cmbCaLam.setSelectedIndex(0);
        cmbTrangThai.setSelectedIndex(0);
        txtMaChamCong.setText("");
        txtMaNhanVien.setText("");
        txtHoTen.setText("");
        txtChucVu.setText("");
        txtSoDienThoai.setText("");

        tblModelChamCong.setRowCount(0);
        tblModelNhanVienChuaChamCong.setRowCount(0);
    }

    private void thucHienChucNangLayDanhSachChamCong() {
        new Thread(() -> {
            try (var socket = new Socket(BUNDLE.getString("host"), Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())) {
                tblModelNhanVienChuaChamCong.setRowCount(0);
                if (jcNgayChamCong.getDate() == null) {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("chamCongNhanVien.thongBaoChonNgayChamCong")));
                    return;
                }
                LocalDate ngayChamCong = DateConvertUtils.asLocalDate(jcNgayChamCong.getDate(), ZoneId.systemDefault());

                if (ngayChamCong.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("chamCongNhanVien.thongBaoChonNgayChamCongTruocHoacNgayHienTai")));
                    return;
                }

                String maCa = cmbCaLam.getSelectedItem().equals("Sáng") ? "SA"
                        : cmbCaLam.getSelectedItem().equals("Chiều") ? "CH" : "TO";

                ChamCongNhanVien cnnv = new ChamCongNhanVien();
                cnnv.setNgayChamCong(ngayChamCong);
                cnnv.setCaLam(new CaLam(maCa, ""));
                RequestDTO request = RequestDTO.builder()
                        .requestType("ChamCongNhanVienForm")
                        .request("timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong")
                        .data(cnnv)
                        .build();

                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();


                danhSachNhanVienChuaChamCong = data.stream().map((value) -> AppUtils.convert(value, NhanVien.class))
                        .collect(Collectors.toList());

                loadTableChamCongNhanVien(ngayChamCong, maCa);

                int stt = 1;
                for (NhanVien nhanVien : danhSachNhanVienChuaChamCong) {
                    tblModelNhanVienChuaChamCong.addRow(new Object[]{stt++, nhanVien.getMaNhanVien(), nhanVien.getHoTen(),
                            nhanVien.getGioiTinh() == 1 ? "Nam" : "Nữ", nhanVien.getSoDienThoai()});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void thucHienChucNangChonNhanVienTuBangNhanVienChuaChamCong() {
        int select = tblNhanVienChuaChamCong.getSelectedRow();
        if (select >= 0) {
            NhanVien nhanVien = danhSachNhanVienChuaChamCong.get(select);
            int trangThaiDiLam = 1;
            Boolean nghilam = (Boolean) tblNhanVienChuaChamCong.getValueAt(select, 5);
            Boolean diTre = (Boolean) tblNhanVienChuaChamCong.getValueAt(select, 6);
            cmbTrangThai.setSelectedItem("Có mặt");

            if (nghilam != null && diTre != null) {
                if (nghilam == true) {
                    cmbTrangThai.setSelectedItem("Nghỉ");
                } else if (nghilam == false && diTre == true) {
                    cmbTrangThai.setSelectedItem("Đi trễ");
                }
            } else if (nghilam != null && diTre == null) {
                if (nghilam == true) {
                    cmbTrangThai.setSelectedItem("Nghỉ");
                }
            } else if (nghilam == null && diTre != null) {
                if (diTre == true) {
                    cmbTrangThai.setSelectedItem("Đi trễ");
                }
            }

            txtMaChamCong.setText("");
            txtMaNhanVien.setText(nhanVien.getMaNhanVien());
            txtHoTen.setText(nhanVien.getHoTen());
            txtChucVu.setText(nhanVien.getChucVu().getTenChucVu());
            txtSoDienThoai.setText(nhanVien.getSoDienThoai());
        }

    }

    private void thucHienChonTrenBangChamCongNhanVien() {
        int select = tblChamCong.getSelectedRow();
        if (select >= 0) {
            ChamCongNhanVien chamCongNV = danhSachChamCong.get(select);

            txtMaChamCong.setText(chamCongNV.getMaChamCong());
            txtMaNhanVien.setText(chamCongNV.getNhanVien().getMaNhanVien());
            txtHoTen.setText(chamCongNV.getNhanVien().getHoTen());
            txtChucVu.setText(chamCongNV.getNhanVien().getChucVu().getTenChucVu());
            txtSoDienThoai.setText(chamCongNV.getNhanVien().getSoDienThoai());

            String trangThaiDiLam = chamCongNV.getTrangThai() == 0 ? "Nghỉ"
                    : chamCongNV.getTrangThai() == 1 ? "Có mặt" : "Đi trễ";
            cmbTrangThai.setSelectedItem(trangThaiDiLam);

            String caLam = chamCongNV.getCaLam().getMaCa().equals("SA") ? "Sáng"
                    : chamCongNV.getCaLam().getMaCa().equals("CH") ? "Chiều" : "Tối";
            cmbCaLam.setSelectedItem(caLam);

            jcNgayChamCong.setDate(DateConvertUtils.asUtilDate(chamCongNV.getNgayChamCong(), ZoneId.systemDefault()));

        }
    }

    private void loadTableChamCongNhanVien(LocalDate ngayChamCong, String maCa) {
        new Thread(() -> {
            try (var socket = new Socket(BUNDLE.getString("host"), Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())) {

                ChamCongNhanVien chamCongNhanVienTim = new ChamCongNhanVien();
                chamCongNhanVienTim.setNgayChamCong(ngayChamCong);
                chamCongNhanVienTim.setCaLam(new CaLam(maCa, ""));

                RequestDTO request = RequestDTO.builder()
                        .requestType("ChamCongNhanVienForm")
                        .request("timTatCaChamCongNhanVienTheoNgayVaCa")
                        .data(chamCongNhanVienTim)
                        .build();
                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();
                danhSachChamCong = data.stream().map((value) -> AppUtils.convert(value, ChamCongNhanVien.class))
                        .collect(Collectors.toList());

                tblModelChamCong.setRowCount(0);
                int stt = 1;
                for (ChamCongNhanVien chamCongNhanVien : danhSachChamCong) {
                    NhanVien nhanVien = chamCongNhanVien.getNhanVien();
                    tblModelChamCong.addRow(new Object[]{stt++, chamCongNhanVien.getMaChamCong(), nhanVien.getMaNhanVien(),
                            nhanVien.getHoTen(), nhanVien.getGioiTinh() == 1 ? "Nam" : "Nữ", nhanVien.getSoDienThoai(),
                            nhanVien.getPhongBan().getTenPhongBan(), nhanVien.getChucVu().getTenChucVu(),
                            chamCongNhanVien.getCaLam().getTenCa(), chamCongNhanVien.getNgayChamCong(),
                            chamCongNhanVien.getTrangThai() == 1 ? "Có mặt"
                                    : chamCongNhanVien.getTrangThai() == 0 ? "Nghỉ" : "Đi trễ"});
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
