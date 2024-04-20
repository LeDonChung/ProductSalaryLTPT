package com.product.salary.client.view.employee;

import com.product.salary.client.common.SystemConstants;
import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

public class ChucVuForm extends JPanel {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
    private final JTextField txtMaChucVu;
    private final JTextField txtenChucVu;
    private final DefaultTableModel tableModelChucVu;
    private final JTable tblChucVu;
    private final JButton btnCapNhat;
    private final JButton btnXoa;
    private final JButton btnThem;
    private final JButton btnLamMoi;
    private List<ChucVu> chucVus;
    private final JLabel lblLoiTenChucVu;

    /**
     * Create the panel.
     */
    public ChucVuForm() {

        setLayout(null);

        JPanel pnlChinh = new JPanel();
        pnlChinh.setBounds(10, 10, 1273, 821);
        add(pnlChinh);
        pnlChinh.setLayout(null);

        JLabel lblTitle = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.titleChucVu")));
        lblTitle.setBounds(0, 0, 1250, 80);
        lblTitle.setBorder(null);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pnlChinh.add(lblTitle);

        JPanel pnlMaChucVu = new JPanel();
        pnlMaChucVu.setLayout(null);
        pnlMaChucVu.setBounds(20, 118, 566, 62);
        pnlChinh.add(pnlMaChucVu);

        JLabel lblMaChucVu = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.maChucVu")));
        lblMaChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblMaChucVu.setBounds(10, 0, 150, 40);
        pnlMaChucVu.add(lblMaChucVu);

        txtMaChucVu = new JTextField();
        txtMaChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtMaChucVu.setEnabled(false);
        txtMaChucVu.setEditable(false);
        txtMaChucVu.setColumns(10);
        txtMaChucVu.setBounds(188, 0, 354, 40);
        pnlMaChucVu.add(txtMaChucVu);

        JPanel pnlTenChucVu = new JPanel();
        pnlTenChucVu.setLayout(null);
        pnlTenChucVu.setBounds(684, 118, 566, 62);
        pnlChinh.add(pnlTenChucVu);

        JLabel lblTenChucVu = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.tenChucVu")));
        lblTenChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTenChucVu.setBounds(10, 0, 150, 40);
        pnlTenChucVu.add(lblTenChucVu);

        txtenChucVu = new JTextField();
        txtenChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtenChucVu.setColumns(10);
        txtenChucVu.setBounds(188, 0, 354, 40);
        pnlTenChucVu.add(txtenChucVu);

        lblLoiTenChucVu = new JLabel("");
        lblLoiTenChucVu.setForeground(Color.RED);
        lblLoiTenChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblLoiTenChucVu.setBounds(188, 39, 354, 23);
        pnlTenChucVu.add(lblLoiTenChucVu);

        btnLamMoi = new JButton(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("quanLyPhongBan.btnXoaTrang")));
        btnLamMoi.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
        btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnLamMoi.setBounds(1060, 240, 190, 44);
        pnlChinh.add(btnLamMoi);

        btnThem = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnThem")));
        btnThem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
        btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnThem.setBounds(27, 240, 190, 44);
        pnlChinh.add(btnThem);

        btnXoa = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnXoa")));
        btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
        btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnXoa.setBounds(684, 240, 190, 44);
        pnlChinh.add(btnXoa);

        btnCapNhat = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnCapNhat")));
        btnCapNhat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
        btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnCapNhat.setBounds(396, 240, 190, 44);
        pnlChinh.add(btnCapNhat);

        JLabel lblDanhSachChucVu = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.danhSachChucVu")));
        lblDanhSachChucVu.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblDanhSachChucVu.setBounds(10, 386, 350, 36);
        pnlChinh.add(lblDanhSachChucVu);

        tableModelChucVu = new DefaultTableModel(new String[]{
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.tblChucVu.stt")),
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.tblChucVu.maChucVu")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("chucVu.tblChucVu.tenChucVu"))},
                15);

        tblChucVu = new JTable(tableModelChucVu);
        tblChucVu.setShowVerticalLines(true);
        tblChucVu.setShowHorizontalLines(true);
        tblChucVu.setRowHeight(25);

        JScrollPane scrChucVu = new JScrollPane(tblChucVu);
        scrChucVu.setLocation(10, 432);
        scrChucVu.setSize(1240, 379);
        tblChucVu.setBounds(0, 570, 1273, 263);
        pnlChinh.add(scrChucVu);

        init();
        event();
    }

    private void event() {
        tblChucVu.addMouseListener(new MouseListener() {

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
                thucHienChucNangClickChucVu();
            }
        });

        this.btnLamMoi.addActionListener((e) -> {
            thucHienChucNangLamMoi();
        });

        this.btnThem.addActionListener((e) -> {
            thucHienChucNangThem();
        });
        this.btnCapNhat.addActionListener((e) -> {
            thucHienThucNangCapNhat();
        });
        this.btnXoa.addActionListener((e) -> {
            thucHienChucNangXoa();
        });
    }

    private void init() {
        this.chucVus = new ArrayList<ChucVu>();
        this.loadTable();
    }

    private void loadTable() {
        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dis = new DataInputStream(socket.getInputStream());
                 var dos = new DataOutputStream(socket.getOutputStream());
            ) {

                //send data
                RequestDTO request = RequestDTO.builder()
                        .requestType("ChucVuForm")
                        .request("timKiemTatCaChucVu")
                        .build();
                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                //Receive data
                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();
                chucVus = data.stream().map(value -> AppUtils.convert(value, ChucVu.class)).toList();
                this.tableModelChucVu.setRowCount(0);
                int i = 1;
                for (ChucVu cv : this.chucVus) {
                    this.tableModelChucVu.addRow(new Object[]{i++, cv.getMaChucVu(), cv.getTenChucVu()});
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void thucHienChucNangClickChucVu() {
        int index = tblChucVu.getSelectedRow();
        if (index >= 0) {
            ChucVu cv = this.chucVus.get(index);
            this.txtenChucVu.setText(cv.getTenChucVu());
            this.txtMaChucVu.setText(cv.getMaChucVu());
        }
    }

    private void thucHienChucNangLamMoi() {
        this.txtenChucVu.setText("");
        this.txtMaChucVu.setText("");
        this.loadTable();
        thucHienThucLamLamMoiLoi();
    }

    private void thucHienThucLamLamMoiLoi() {
        this.lblLoiTenChucVu.setText("");
    }

    private void thucHienChucNangThem() {
        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dis = new DataInputStream(socket.getInputStream());
                 var dos = new DataOutputStream(socket.getOutputStream());
            ) {
                String tenChucVu = this.txtenChucVu.getText().trim();
                try {
                    ChucVu chucVu = new ChucVu();
                    chucVu.setTenChucVu(tenChucVu);

                    RequestDTO request = RequestDTO.builder()
                            .requestType("ChucVuForm")
                            .request("themChucVu")
                            .data(chucVu)
                            .build();

                    String json = AppUtils.GSON.toJson(request);
                    dos.writeUTF(json);
                    dos.flush();

                    //Receive data
                    json = new String(dis.readAllBytes());
                    ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                    chucVu = AppUtils.convert((Map<String, Object>) response.getData(), ChucVu.class);
                    if (chucVu != null) {
                        JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("chucVu.thongBaoThemThanhCong")));
                        this.thucHienChucNangLamMoi();
                    } else {
                        JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("chucVu.thongBaoThemKhongThanhCong")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private boolean thucHienChucNangKiemTra() {
        thucHienThucLamLamMoiLoi();

        String tenChucVu = this.txtenChucVu.getText().trim();

        boolean status = true;
        if (ObjectUtils.isEmpty(tenChucVu)) {
            lblLoiTenChucVu.setText(String.format("<html><p>%s</p></html>",
                    SystemConstants.BUNDLE.getString("chucVu.thongBaoLoiTenChucVu")));
            status = false;
        }

        return status;
    }

    private void thucHienThucNangCapNhat() {
        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dis = new DataInputStream(socket.getInputStream());
                 var dos = new DataOutputStream(socket.getOutputStream());
            ) {
                if (thucHienChucNangKiemTra()) {
                    int isSelected = this.tblChucVu.getSelectedRow();
                    if (isSelected < 0) {
                        JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("chucVu.thongBaoChonChucVuDeCapNhat")));
                        return;
                    }
                    if (!thucHienChucNangKiemTra()) {
                        return;
                    }
                    String tenChucVu = this.txtenChucVu.getText().trim();

                    try {
                        ChucVu chucVu = chucVus.get(isSelected);
                        chucVu.setTenChucVu(tenChucVu);

                        RequestDTO request = RequestDTO.builder()
                                .requestType("ChucVuForm")
                                .request("capNhatChucVu")
                                .data(chucVu)
                                .build();

                        String json = AppUtils.GSON.toJson(request);
                        dos.writeUTF(json);
                        dos.flush();

                        //Receive data
                        json = new String(dis.readAllBytes());
                        RequestDTO response = AppUtils.GSON.fromJson(json, RequestDTO.class);
                        chucVu = AppUtils.convert((Map<String, Object>) response.getData(), ChucVu.class);
                        System.out.println(chucVu);
                        if (chucVu != null) {
                            JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                    SystemConstants.BUNDLE.getString("chucVu.thongBaoCapNhatThanhCong")));
                            this.thucHienChucNangLamMoi();
                        } else {
                            JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                                    SystemConstants.BUNDLE.getString("chucVu.thongBaoCapNhatKhongThanhCong")));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void thucHienChucNangXoa() {
        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dis = new DataInputStream(socket.getInputStream());
                 var dos = new DataOutputStream(socket.getOutputStream());
            ) {
                int isSelected = this.tblChucVu.getSelectedRow();
                if (isSelected < 0) {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("chucVu.thongBaoChonChucVuDeXoa")));
                    return;
                }
                ChucVu chucVu = chucVus.get(isSelected);

                RequestDTO request = RequestDTO.builder()
                        .requestType("ChucVuForm")
                        .request("xoaChucVu")
                        .data(chucVu)
                        .build();

                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                //Receive data
                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                boolean result = (boolean) response.getData();
                if (result) {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("chucVu.thongBaoXoaThanhCong")));
                    this.thucHienChucNangLamMoi();
                } else {
                    JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
                            SystemConstants.BUNDLE.getString("chucVu.thongBaoXoaKhongThanhCong")));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
