package com.product.salary.client.view.contract;

/*
  @author Trần Tuấn Kiệt: Code giao diện
 * @author Lê Đôn Chủng: Xử lý code
 */

import com.product.salary.application.entity.SanPham;
import com.product.salary.application.utils.*;
import com.product.salary.client.common.SystemConstants;
import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.product.salary.application.utils.pdf.HopDongPdfUtils;
import com.toedter.calendar.JDateChooser;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class HopDongForm extends JPanel {
    private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");

    private final JTextField txtMaHopDong;
    private final JTextField txtTenKhachHang;
    private final JTextField txtTenHopDong;
    private final JTextField txtTongTien;
    private final JTextField txtSoTienCoc;
    private final JTable tblChiTietHopDong;
    private final JDateChooser jcNgayKetThuc;
    private final JDateChooser jcNgayBatDau;
    private final JTextArea txaYeuCau;
    private final JComboBox cmbTrangThai;
    private final DefaultTableModel tableModelHopDong;
    private final JButton btnThem;
    private final JButton btnThanhLy;
    private final JButton btnThemSanPham;
    private final JButton btnXoa;
    private final JButton btnCapNhat;
    private final JButton btnXoaTrang;
    private final JLabel lblLoiTenHopDong;
    private final JLabel lblLoiTongTien;
    private final JLabel lblLoiSoTienCoc;
    private final JLabel lblLoiNgayKetThuc;
    private final JLabel lblLoiNgayBatDau;
    private final JLabel lblLoiTrangThai;
    private List<HopDong> hopDongs;
    private List<ChiTietHopDong> chiTietHopDongs;
    private final JTable tblHopDong;
    private final DefaultTableModel tableModelChiTietHopDong;
    private final JLabel lblLoiTenKhachHang;
    private final JButton btnXuatHopDong;

    /**
     * Create the panel.
     */
    public HopDongForm() {

        setLayout(null);

        JPanel pnlMain = new JPanel();
        pnlMain.setBounds(10, 10, 1213, 821);
        pnlMain.setLayout(null);
        add(pnlMain);

        JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("hopDong.title"));
        lblTitle.setBorder(null);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setBounds(0, 0, 1250, 80);
        pnlMain.add(lblTitle);

        JPanel pnlMaHopDong = new JPanel();
        pnlMaHopDong.setLayout(null);
        pnlMaHopDong.setBounds(10, 90, 384, 62);
        pnlMain.add(pnlMaHopDong);

        JLabel lblMaHopDong = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.maHopDong")));
        lblMaHopDong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblMaHopDong.setBounds(10, 0, 96, 40);
        pnlMaHopDong.add(lblMaHopDong);

        txtMaHopDong = new JTextField();
        txtMaHopDong.setEnabled(false);
        txtMaHopDong.setEditable(false);
        txtMaHopDong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtMaHopDong.setColumns(10);
        txtMaHopDong.setBounds(127, 0, 247, 40);
        pnlMaHopDong.add(txtMaHopDong);

        JLabel lblLoiMaHopDong = new JLabel("");
        lblLoiMaHopDong.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiMaHopDong.setForeground(new Color(255, 0, 0));
        lblLoiMaHopDong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiMaHopDong.setBounds(126, 39, 248, 23);
        pnlMaHopDong.add(lblLoiMaHopDong);

        JPanel pnlTenKhachHang = new JPanel();
        pnlTenKhachHang.setLayout(null);
        pnlTenKhachHang.setBounds(404, 90, 429, 62);
        pnlMain.add(pnlTenKhachHang);

        JLabel lblTenKhachHang = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.tenKH")));
        lblTenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTenKhachHang.setBounds(10, 0, 152, 40);
        pnlTenKhachHang.add(lblTenKhachHang);

        txtTenKhachHang = new JTextField();
        txtTenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtTenKhachHang.setColumns(10);
        txtTenKhachHang.setBounds(172, 0, 247, 40);
        pnlTenKhachHang.add(txtTenKhachHang);

        lblLoiTenKhachHang = new JLabel("");
        lblLoiTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiTenKhachHang.setForeground(Color.RED);
        lblLoiTenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiTenKhachHang.setBounds(171, 39, 248, 23);
        pnlTenKhachHang.add(lblLoiTenKhachHang);

        JPanel pnlTenHopDong = new JPanel();
        pnlTenHopDong.setLayout(null);
        pnlTenHopDong.setBounds(10, 151, 384, 62);
        pnlMain.add(pnlTenHopDong);

        JLabel lblTenHopDong = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.tenHopDong")));
        lblTenHopDong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTenHopDong.setBounds(10, 0, 96, 40);
        pnlTenHopDong.add(lblTenHopDong);

        txtTenHopDong = new JTextField();
        txtTenHopDong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtTenHopDong.setColumns(10);
        txtTenHopDong.setBounds(127, 0, 247, 40);
        pnlTenHopDong.add(txtTenHopDong);

        lblLoiTenHopDong = new JLabel("");
        lblLoiTenHopDong.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiTenHopDong.setForeground(Color.RED);
        lblLoiTenHopDong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiTenHopDong.setBounds(126, 39, 248, 23);
        pnlTenHopDong.add(lblLoiTenHopDong);

        JPanel pnlTongTien = new JPanel();
        pnlTongTien.setLayout(null);
        pnlTongTien.setBounds(404, 151, 429, 62);
        pnlMain.add(pnlTongTien);

        JLabel lblTongTien = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.tongTien")));
        lblTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTongTien.setBounds(10, 0, 152, 40);
        pnlTongTien.add(lblTongTien);

        txtTongTien = new JTextField();
        txtTongTien.setEnabled(false);
        txtTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtTongTien.setColumns(10);
        txtTongTien.setBounds(172, 0, 247, 40);
        pnlTongTien.add(txtTongTien);

        lblLoiTongTien = new JLabel("");
        lblLoiTongTien.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiTongTien.setForeground(Color.RED);
        lblLoiTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiTongTien.setBounds(171, 39, 248, 23);
        pnlTongTien.add(lblLoiTongTien);

        JPanel pnlSoTienCoc = new JPanel();
        pnlSoTienCoc.setLayout(null);
        pnlSoTienCoc.setBounds(10, 216, 384, 62);
        pnlMain.add(pnlSoTienCoc);

        JLabel lblSoTienCoc = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.soTienCoc")));
        lblSoTienCoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblSoTienCoc.setBounds(10, 0, 96, 40);
        pnlSoTienCoc.add(lblSoTienCoc);

        txtSoTienCoc = new JTextField();
        txtSoTienCoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtSoTienCoc.setColumns(10);
        txtSoTienCoc.setBounds(127, 0, 247, 40);
        pnlSoTienCoc.add(txtSoTienCoc);

        lblLoiSoTienCoc = new JLabel("");
        lblLoiSoTienCoc.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiSoTienCoc.setForeground(Color.RED);
        lblLoiSoTienCoc.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiSoTienCoc.setBounds(126, 39, 248, 23);
        pnlSoTienCoc.add(lblLoiSoTienCoc);

        JPanel pnlNgayKetThuc = new JPanel();
        pnlNgayKetThuc.setLayout(null);
        pnlNgayKetThuc.setBounds(404, 216, 429, 62);
        pnlMain.add(pnlNgayKetThuc);

        jcNgayKetThuc = new JDateChooser();
        jcNgayKetThuc.getCalendarButton()
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
        jcNgayKetThuc.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
        jcNgayKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        jcNgayKetThuc.setDateFormatString("yyyy-MM-dd");
        jcNgayKetThuc.setBounds(172, 0, 247, 40);
        pnlNgayKetThuc.add(jcNgayKetThuc);

        JLabel lblNgayKetThuc = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.ngayKetThuc")));
        lblNgayKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNgayKetThuc.setBounds(10, 0, 152, 45);
        pnlNgayKetThuc.add(lblNgayKetThuc);

        lblLoiNgayKetThuc = new JLabel("");
        lblLoiNgayKetThuc.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiNgayKetThuc.setForeground(Color.RED);
        lblLoiNgayKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiNgayKetThuc.setBounds(172, 39, 247, 23);
        pnlNgayKetThuc.add(lblLoiNgayKetThuc);

        JPanel pnlNgayBatDau = new JPanel();
        pnlNgayBatDau.setLayout(null);
        pnlNgayBatDau.setBounds(10, 281, 384, 62);
        pnlMain.add(pnlNgayBatDau);

        jcNgayBatDau = new JDateChooser();
        jcNgayBatDau.getCalendarButton()
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
        jcNgayBatDau.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
        jcNgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        jcNgayBatDau.setDateFormatString("yyyy-MM-dd");
        jcNgayBatDau.setBounds(127, 0, 247, 40);
        pnlNgayBatDau.add(jcNgayBatDau);

        JLabel lblNgayBatDau = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.ngayBatDau")));
        lblNgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNgayBatDau.setBounds(10, 0, 120, 45);
        pnlNgayBatDau.add(lblNgayBatDau);

        lblLoiNgayBatDau = new JLabel("");
        lblLoiNgayBatDau.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiNgayBatDau.setForeground(Color.RED);
        lblLoiNgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiNgayBatDau.setBounds(127, 39, 248, 23);
        pnlNgayBatDau.add(lblLoiNgayBatDau);

        JPanel pnlYeuCau = new JPanel();
        pnlYeuCau.setBounds(10, 353, 823, 140);
        pnlMain.add(pnlYeuCau);
        pnlYeuCau.setLayout(null);

        JLabel lblYeuCau = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.yeuCau")));
        lblYeuCau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblYeuCau.setBounds(10, 40, 106, 45);
        pnlYeuCau.add(lblYeuCau);

        txaYeuCau = new JTextArea();
        JScrollPane scr = new JScrollPane(txaYeuCau);
        scr.setLocation(126, 0);
        scr.setSize(687, 113);
        pnlYeuCau.add(scr);

        JLabel lblLoiYeuCau = new JLabel("");
        lblLoiYeuCau.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiYeuCau.setForeground(Color.RED);
        lblLoiYeuCau.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiYeuCau.setBounds(126, 117, 687, 23);
        pnlYeuCau.add(lblLoiYeuCau);

        JPanel pnlTrangThai = new JPanel();
        pnlTrangThai.setLayout(null);
        pnlTrangThai.setBounds(404, 281, 429, 62);
        pnlMain.add(pnlTrangThai);

        JLabel lblTrangThai = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.trangThai")));
        lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTrangThai.setBounds(10, 0, 157, 39);
        pnlTrangThai.add(lblTrangThai);

        cmbTrangThai = new JComboBox();
        cmbTrangThai.setModel(new DefaultComboBoxModel(new String[]{"Đã thanh lý", "Chưa thanh lý"}));
        cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbTrangThai.setBounds(172, 0, 242, 40);
        pnlTrangThai.add(cmbTrangThai);

        lblLoiTrangThai = new JLabel("");
        lblLoiTrangThai.setHorizontalAlignment(SwingConstants.LEFT);
        lblLoiTrangThai.setForeground(Color.RED);
        lblLoiTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblLoiTrangThai.setBounds(172, 39, 242, 23);
        pnlTrangThai.add(lblLoiTrangThai);

        tableModelHopDong = new DefaultTableModel(new String[]{
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.tableHopDong.STT")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("hopDong.tableHopDong.maHopDong")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("hopDong.tableHopDong.tenHopDong")),
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.tableHopDong.tenKH")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("hopDong.tableHopDong.tongTien")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("hopDong.tableHopDong.soTienCoc")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("hopDong.tableHopDong.ngayBatDau")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("hopDong.tableHopDong.ngayKetThuc")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("hopDong.tableHopDong.yeuCau")),
                String.format("<html><p>%s</p></html>",
                        SystemConstants.BUNDLE.getString("hopDong.tableHopDong.trangThai")),},
                10);

        tblHopDong = new JTable(tableModelHopDong);
        tblHopDong.setShowVerticalLines(true);
        tblHopDong.setShowHorizontalLines(true);
        tblHopDong.setRowHeight(25);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        tblHopDong.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        tblHopDong.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        JScrollPane scrHopDong = new JScrollPane(tblHopDong);
        scrHopDong.setBounds(10, 589, 1193, 244);
        pnlMain.add(scrHopDong);

        btnThem = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.btnThem")));
        btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnThem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
        btnThem.setBounds(226, 503, 180, 44);
        pnlMain.add(btnThem);

        btnThanhLy = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.btnThanhLy")));
        btnThanhLy.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnThanhLy.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
        btnThanhLy.setBounds(10, 502, 180, 44);
        pnlMain.add(btnThanhLy);

        btnThemSanPham = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.btnThemSanPham")));
        btnThemSanPham.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
        btnThemSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnThemSanPham.setBounds(919, 403, 222, 44);
        pnlMain.add(btnThemSanPham);

        tableModelChiTietHopDong = new DefaultTableModel(
                new Object[][]{{null, null, null, null}, {null, null, null, null}, {null, null, null, null},
                        {null, null, null, null}, {null, null, null, null}, {null, null, null, null},
                        {null, null, null, null},},
                new String[]{
                        String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("hopDong.tableHopDong.STT")),
                        String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("hopDong.tableSanPham.maSanPham")),
                        String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("hopDong.tableSanPham.tenSanPham")),
                        String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("hopDong.tableSanPham.soLuongTon")),
                        String.format("<html><p>%s</p></html>",
                                SystemConstants.BUNDLE.getString("hopDong.tableSanPham.donGia"))});

        tblChiTietHopDong = new JTable(tableModelChiTietHopDong);
        tblChiTietHopDong.setShowVerticalLines(true);
        tblChiTietHopDong.setShowHorizontalLines(true);
        tblChiTietHopDong.setRowHeight(25);
        tblChiTietHopDong.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        JScrollPane srcChiTietHopDong = new JScrollPane(tblChiTietHopDong);
        srcChiTietHopDong.setBounds(0, 5, 360, 180);
        JPanel pnlDanhSachChiTietHopDong = new JPanel();
        pnlDanhSachChiTietHopDong.setBounds(843, 118, 360, 187);
        pnlMain.add(pnlDanhSachChiTietHopDong);
        pnlDanhSachChiTietHopDong.setLayout(null);

        pnlDanhSachChiTietHopDong.add(srcChiTietHopDong);

        JLabel lblDanhSachSanPham = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.lbDanhSachSanPham")));
        lblDanhSachSanPham.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblDanhSachSanPham.setBounds(844, 90, 345, 23);
        pnlMain.add(lblDanhSachSanPham);

        btnXoa = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.btnXoa")));
        btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
        btnXoa.setBounds(843, 353, 181, 40);
        pnlMain.add(btnXoa);

        btnCapNhat = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.btnCapNhat")));
        btnCapNhat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
        btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnCapNhat.setBounds(1034, 353, 169, 40);
        pnlMain.add(btnCapNhat);

        btnXoaTrang = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.btnLamMoi")));
        btnXoaTrang.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
        btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnXoaTrang.setBounds(653, 503, 180, 40);
        pnlMain.add(btnXoaTrang);

        JLabel lblDanhSachHopDong = new JLabel(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.lbDanhSachHopDong")));
        lblDanhSachHopDong.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblDanhSachHopDong.setBounds(10, 557, 345, 23);
        pnlMain.add(lblDanhSachHopDong);
        jcNgayBatDau.setDate(new Date());

        btnXuatHopDong = new JButton(
                String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("hopDong.btnXuatHopDong")));
        btnXuatHopDong.setIcon(new ImageIcon("src/main/resources/icon/png/ic_pdf.png"));
        btnXuatHopDong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnXuatHopDong.setBounds(438, 503, 180, 44);
        pnlMain.add(btnXuatHopDong);
        init();
        event();

    }

    private void event() {
        this.btnXoaTrang.addActionListener((e) -> thucHienChucNangLamMoi());
        this.btnThanhLy.addActionListener((e) -> thucHienChucNangThanhLy());
        this.btnXuatHopDong.addActionListener((e) -> thucHienChucNangXuatHopDong());
        this.tblHopDong.addMouseListener(new MouseListener() {

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
                thucHienChucNangChonHopDong();
            }

        });
        this.btnThemSanPham.addActionListener((e) -> thucHienChucNangThemSanPham());
        this.btnXoa.addActionListener((e) -> thucHienChucNangXoaSanPham());
        this.btnCapNhat.addActionListener((e) -> thucHienChucNangCapNhatSanPham());
        this.btnThem.addActionListener((e) -> thucHienChucNangThemHopDong());
    }

    private void thucHienChucNangXuatHopDong() {
        int index = tblHopDong.getSelectedRow();
        if (index >= 0) {
            HopDongPdfUtils.writeHopDong(hopDongs.get(index), chiTietHopDongs);
        } else {
            JOptionPane.showMessageDialog(this,
                    SystemConstants.BUNDLE.getString("hopDong.thongBao.vuiLongChonHopDongDeXuat"));
        }
    }

    private void thucHienChucNangThemHopDong() {
        if (!kiemTraHopLe()) {
            return;
        }

        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())
            ) {

                String maHopDong = "HDXX";
                String tenKhachHang = this.txtTenKhachHang.getText().trim();
                String tenHopDong = this.txtTenHopDong.getText().trim();
                String soTienCoc = this.txtSoTienCoc.getText().trim();
                String tongTien = this.txtTongTien.getText().trim();
                LocalDate ngayBatDau = DateConvertUtils.asLocalDate(this.jcNgayBatDau.getDate(), ZoneId.systemDefault());
                LocalDate ngayKetThuc = DateConvertUtils.asLocalDate(this.jcNgayKetThuc.getDate(), ZoneId.systemDefault());
                String yeuCau = this.txaYeuCau.getText().trim();
                HopDong hopDong = new HopDong(maHopDong, tenHopDong, tenKhachHang, PriceFormatterUtils.parse(tongTien),
                        PriceFormatterUtils.parse(soTienCoc), ngayBatDau, ngayKetThuc, yeuCau, false);

                chiTietHopDongs = chiTietHopDongs.stream().map(chiTietHopDong -> {
                    try {
                        chiTietHopDong.setSanPham(new SanPham(chiTietHopDong.getSanPham().getMaSanPham()));
                        return chiTietHopDong;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());

                hopDong.setChiTietHopDongs(chiTietHopDongs);
                Map<String, Object> send = new HashMap<>();
                send.put("hopDong", hopDong);
                send.put("chiTietHopDongs", chiTietHopDongs);
                // Send data to server
                RequestDTO request = RequestDTO.builder()
                        .request("themHopDong")
                        .requestType("HopDongForm")
                        .data(send)
                        .build();
                System.out.println("Sending request: " + request);
                String json = AppUtils.GSON.toJson(request);

                dos.writeUTF(json);
                dos.flush();

                // Receive data from server
                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);

                System.out.println("Receive response: " + response);
                Map<String, Object> data = (Map<String, Object>) response.getData();

                hopDong = AppUtils.convert(data, HopDong.class);

                if (hopDong != null) {
                    JOptionPane.showMessageDialog(this,
                            SystemConstants.BUNDLE.getString("hopDong.thongBao.themHopDongThanhCong"));
                    this.thucHienChucNangLamMoi();
                } else {
                    JOptionPane.showMessageDialog(this,
                            SystemConstants.BUNDLE.getString("hopDong.thongBao.themHopDongKhongThanhCong"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ).start();

    }

    private boolean kiemTraHopLe() {
        thucHienChucNangLamMoiLoi();
        String tenKhachHang = this.txtTenKhachHang.getText().trim();
        String tenHopDong = this.txtTenHopDong.getText().trim();
        String soTienCoc = this.txtSoTienCoc.getText().trim();
        String tongTien = this.txtTongTien.getText().trim();
        LocalDate ngayBatDau = DateConvertUtils.asLocalDate(this.jcNgayBatDau.getDate(), ZoneId.systemDefault());

        boolean status = true;
        if (ObjectUtils.isEmpty(tenHopDong)) {
            this.lblLoiTenHopDong.setText(SystemConstants.BUNDLE.getString("hopDong.thongBao.tenHopDongKhongDuocRong"));
            status = false;
        }
        if (ObjectUtils.isEmpty(tenKhachHang)) {
            this.lblLoiTenKhachHang
                    .setText(SystemConstants.BUNDLE.getString("hopDong.thongBao.tenKhachHangKhongDuocRong"));
            status = false;
        }
        if (ObjectUtils.isEmpty(soTienCoc)) {
            this.lblLoiSoTienCoc.setText(SystemConstants.BUNDLE.getString("hopDong.thongBao.tienCocKhongDuocRong"));
            status = false;
        } else {
            try {
                if (PriceFormatterUtils.parse(soTienCoc) < 0) {
                    lblLoiSoTienCoc.setText(SystemConstants.BUNDLE.getString("hopDong.thongBao.tienCocPhaiLaSoThuc"));
                    status = false;
                } else {
                    if (PriceFormatterUtils.parse(soTienCoc) > PriceFormatterUtils.parse(tongTien)) {
                        lblLoiSoTienCoc.setText(String
                                .format(SystemConstants.BUNDLE.getString("hopDong.thongBao.tienCocPhaiBeHonTongTien")));
                        status = false;
                    }
                }
            } catch (Exception e) {
                lblLoiSoTienCoc.setText(SystemConstants.BUNDLE.getString("hopDong.thongBao.tienCocPhaiLaSoThuc"));
                status = false;
            }
        }

        if (ObjectUtils.isEmpty(ngayBatDau)) {
            this.lblLoiNgayBatDau.setText(SystemConstants.BUNDLE.getString("hopDong.thongBao.ngayBatDauPhaiChon"));
            status = false;
        } else {
            if (ngayBatDau.isBefore(LocalDate.now())) {
                this.lblLoiTenKhachHang
                        .setText(SystemConstants.BUNDLE.getString("hopDong.thongBao.ngayBatDauLonHonNgayHienTai"));
                status = false;
            }
        }
        return status;
    }

    private void thucHienChucNangCapNhatSanPham() {
        int index = this.tblChiTietHopDong.getSelectedRow();
        if (index >= 0) {
            try {

                ChiTietHopDong chiTietHopDong = chiTietHopDongs.get(index);
                String soLuong = JOptionPane.showInputDialog(
                        SystemConstants.BUNDLE.getString("hopDong.thongBao.nhapSoLuong"),
                        chiTietHopDongs.get(index).getSoLuong());
                String giaDat = JOptionPane.showInputDialog(
                        SystemConstants.BUNDLE.getString("hopDong.thongBao.nhapGiaDat"),
                        chiTietHopDongs.get(index).getGiaDatLam());

                if (Integer.valueOf(soLuong) <= 0) {
                    JOptionPane.showMessageDialog(null,
                            SystemConstants.BUNDLE.getString("hopDong.thongBao.soLuongSanPham"));
                    return;
                }

                if (Double.valueOf(giaDat) < chiTietHopDong.getSanPham().getDonGia()) {
                    JOptionPane.showMessageDialog(null, SystemConstants.BUNDLE.getString("hopDong.thongBao.giaDat"));
                    return;
                }

                chiTietHopDongs.get(index).setSoLuong(Integer.valueOf(soLuong));
                chiTietHopDongs.get(index).setGiaDatLam(Double.valueOf(giaDat));

                this.loadTableSanPham();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        SystemConstants.BUNDLE.getString("hopDong.thongBao.nhapKhongDungDinhDang"));
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    SystemConstants.BUNDLE.getString("hopDong.thongBao.nhapChiTietHopDongDeCapNhat"));
        }
    }

    private void thucHienChucNangXoaSanPham() {
        int index = this.tblChiTietHopDong.getSelectedRow();
        if (index >= 0) {
            this.chiTietHopDongs.remove(index);
            this.loadTableSanPham();
        } else {
            JOptionPane.showMessageDialog(this,
                    SystemConstants.BUNDLE.getString("hopDong.thongBao.vuiLongChonChiTietHopDongDeXoa"));
        }
    }

    private void thucHienChucNangThemSanPham() {
        // Nếu một hợp đồng nào đó đang được chọn thì phải làm mới
        int isSelecthopDong = this.tblHopDong.getSelectedRow();
        if (isSelecthopDong >= 0) {
            JOptionPane.showMessageDialog(this,
                    SystemConstants.BUNDLE.getString("hopDong.thongBao.messageIsSelectHopDong"));
            return;
        }
        // Thực hiện chọn sản phẩm
        new CreateChiTietHopDongForm(chiTietHopDong -> {
            for (ChiTietHopDong chiTietHopDongS : chiTietHopDongs) {
                if (chiTietHopDongS.getSanPham().equals(chiTietHopDong.getSanPham())) {
                    JOptionPane.showMessageDialog(null,
                            SystemConstants.BUNDLE.getString("hopDong.thongBao.messageIsSelectSanPham"));
                    return;
                }
            }
            chiTietHopDongs.add(chiTietHopDong);
            loadTableSanPham();
        }).setVisible(true);

    }

    protected void loadTableSanPham() {
        tableModelChiTietHopDong.setRowCount(0);

        int stt = 1;
        double tongTien = 0;
        for (ChiTietHopDong chiTietHopDong : chiTietHopDongs) {
            tongTien += chiTietHopDong.getSoLuong() * chiTietHopDong.getGiaDatLam();
            tableModelChiTietHopDong.addRow(new Object[]{stt++, chiTietHopDong.getSanPham().getMaSanPham(),
                    chiTietHopDong.getSanPham().getTenSanPham(), chiTietHopDong.getSoLuong(),
                    PriceFormatterUtils.format(chiTietHopDong.getGiaDatLam())});
        }
        this.txtTongTien.setText(PriceFormatterUtils.format(tongTien));
    }

    private void thucHienChucNangThanhLy() {
        new Thread(() -> {
            try (
                    var socket = new Socket(
                            BUNDLE.getString("host"),
                            Integer.parseInt(BUNDLE.getString("server.port")));
                    var dos = new DataOutputStream(socket.getOutputStream());
                    var dis = new DataInputStream(socket.getInputStream())
            ) {
                int select = tblHopDong.getSelectedRow();
                int language = SystemConstants.LANGUAGE;
                String message = "";
                if (select >= 0) {

                    HopDong hopDong = this.hopDongs.get(select);

                    String title = "";
                    if (language == 0) {
                        message = String.format("Bạn có muốn thanh lý hợp đồng %s không?", hopDong.getTenHopDong());
                        title = "Thanh lý hợp đồng";
                    } else {
                        message = String.format("Do you want to liquidate contract %s?", hopDong.getTenHopDong());
                        title = "Contract liquidation";
                    }

                    int choose = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.OK_CANCEL_OPTION);

                    if (choose == JOptionPane.OK_OPTION) {

                        // Send data to server
                        RequestDTO request = RequestDTO.builder()
                                .request("thanhLyHopDong")
                                .requestType("HopDongForm")
                                .data(hopDong.getMaHopDong())
                                .build();
                        System.out.println("Sending request: " + request);
                        String json = AppUtils.GSON.toJson(request);
                        dos.writeUTF(json);
                        dos.flush();

                        // Receive data from server
                        json = new String(dis.readAllBytes());
                        ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                        System.out.println("Receive response: " + response);
                        boolean trangThai = (boolean) response.getData();

                        if (trangThai) {

                            String result = "";
                            if (language == 0) {
                                result = String.format("Thanh lý hợp đồng %s thành công.", hopDong.getTenHopDong());
                            } else {
                                result = String.format("Contract liquidation %s successfully.", hopDong.getTenHopDong());
                            }
                            JOptionPane.showMessageDialog(this, result);

                            if (language == 0) {
                                result = String.format("Bạn có muốn xuất thông tin hợp đồng vừa thanh lý?");
                                title = "Thanh lý";
                            } else {
                                result = String
                                        .format("Do you want to export contract information that has just been liquidated?");
                                title = "Liquidation";
                            }
                            int option = JOptionPane.showConfirmDialog(this, result, title, JOptionPane.OK_CANCEL_OPTION);

                            if (option == JOptionPane.OK_OPTION) {

                                try {
                                    hopDongs.get(select).setNgayKetThuc(LocalDate.now());
                                    hopDongs.get(select).setTrangThai(trangThai);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                thucHienChucNangXuatHopDong();
                            }
                            thucHienChucNangLamMoi();

                        } else {
                            if (language == 0) {
                                message = String.format("Thanh lý hợp đồng %s không thành công.", hopDong.getTenHopDong());
                            } else {
                                message = String.format("Liquidation of %s contract failed.", hopDong.getTenHopDong());
                            }
                            JOptionPane.showMessageDialog(this, message);

                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            SystemConstants.BUNDLE.getString("hopDong.thongBao.chonHopDongDeThanhLy"));
                }
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void init() {
        this.hopDongs = new ArrayList<>();
        this.chiTietHopDongs = new ArrayList<>();
        loadTableHopDong();
    }

    private void loadTableHopDong() {
        new Thread(() -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())
            ) {

                // Send Data
                RequestDTO request = RequestDTO.builder()
                        .requestType("HopDongForm")
                        .request("timTatCaHopDong")
                        .data("")
                        .build();
                System.out.println("Sending request: " + request);
                String json = AppUtils.GSON.toJson(request);


                dos.writeUTF(json);
                dos.flush();

                // Receive Data
                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                System.out.println("Receive response: " + response);
                List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();

                hopDongs = data.stream().map((value) -> AppUtils.convert(value, HopDong.class)).collect(Collectors.toList());

                tableModelHopDong.setRowCount(0);
                int stt = 1;
                for (HopDong hopDong : hopDongs) {

                    tableModelHopDong.addRow(new Object[]{stt++, hopDong.getMaHopDong(), hopDong.getTenHopDong(),
                            hopDong.getTenKhachHang(), PriceFormatterUtils.format(hopDong.getTongTien()),
                            PriceFormatterUtils.format(hopDong.getSoTienCoc()), hopDong.getNgayBatDau(),
                            hopDong.getNgayKetThuc(), hopDong.getYeuCau(),
                            hopDong.isTrangThai() ? "Đã thanh lý" : "Chưa thanh lý"});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ).start();
    }

    private void thucHienChucNangChonHopDong() {
        int select = tblHopDong.getSelectedRow();

        if (select >= 0) {
            HopDong hopDong = this.hopDongs.get(select);

            this.txtMaHopDong.setText(hopDong.getMaHopDong());
            this.txtSoTienCoc.setText(PriceFormatterUtils.format(hopDong.getSoTienCoc()));
            this.txtTenHopDong.setText(hopDong.getTenHopDong());
            this.txtTenKhachHang.setText(hopDong.getTenKhachHang());
            this.txtTongTien.setText(PriceFormatterUtils.format(hopDong.getTongTien()));
            this.txaYeuCau.setText(hopDong.getYeuCau());
            this.cmbTrangThai.setSelectedIndex(hopDong.isTrangThai() ? 0 : 1);
            this.jcNgayBatDau.setDate(DateConvertUtils.asUtilDate(hopDong.getNgayBatDau(), ZoneId.systemDefault()));
            this.jcNgayKetThuc.setDate(DateConvertUtils.asUtilDate(hopDong.getNgayKetThuc(), ZoneId.systemDefault()));
            loadTableSanPham(hopDong.getMaHopDong());
        }
    }

    private void loadTableSanPham(String maHopDong) {
        new Thread(() -> {
            try (
                    var socket = new Socket(
                            BUNDLE.getString("host"),
                            Integer.parseInt(BUNDLE.getString("server.port")));
                    var dos = new DataOutputStream(socket.getOutputStream());
                    var dis = new DataInputStream(socket.getInputStream())
            ) {

                tableModelChiTietHopDong.setRowCount(0);

                // Send Data
                RequestDTO request = RequestDTO.builder()
                        .requestType("HopDongForm")
                        .request("timTatCaChiTietHopDongBangMaHopDong")
                        .data(maHopDong)
                        .build();

                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                // Receive Data
                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();

                chiTietHopDongs = data.stream().map((value) -> AppUtils.convert(value, ChiTietHopDong.class)).collect(Collectors.toList());


                int stt = 1;
                for (ChiTietHopDong chiTietHopDong : chiTietHopDongs) {

                    tableModelChiTietHopDong.addRow(new Object[]{stt++, chiTietHopDong.getSanPham().getMaSanPham(),
                            chiTietHopDong.getSanPham().getTenSanPham(), chiTietHopDong.getSoLuong(),
                            PriceFormatterUtils.format(chiTietHopDong.getGiaDatLam())});
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

    private void thucHienChucNangLamMoi() {
        this.txtMaHopDong.setText("");
        this.txtSoTienCoc.setText("");
        this.txtTenHopDong.setText("");
        this.txtTenKhachHang.setText("");
        this.txtTongTien.setText("");
        this.txaYeuCau.setText("");
        this.cmbTrangThai.setSelectedIndex(0);
        this.jcNgayBatDau.setDate(null);
        this.jcNgayKetThuc.setDate(null);
        loadTableHopDong();

        this.chiTietHopDongs = new ArrayList<>();
        tableModelChiTietHopDong.setRowCount(0);
        thucHienChucNangLamMoiLoi();

    }

    private void thucHienChucNangLamMoiLoi() {
        this.lblLoiTenHopDong.setText("");
        this.lblLoiNgayBatDau.setText("");
        this.lblLoiSoTienCoc.setText("");
        this.lblLoiNgayKetThuc.setText("");
        this.lblLoiTongTien.setText("");
        this.lblLoiTrangThai.setText("");
        this.lblLoiTenKhachHang.setText("");
    }
}
