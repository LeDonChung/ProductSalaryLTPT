package com.product.salary.client.view.statistic;

import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;
import com.product.salary.client.common.SystemConstants;
import com.product.salary.application.utils.PriceFormatterUtils;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class LuongNhanVienForm extends JPanel {
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
    private final JYearChooser chooseNam;
    private final JMonthChooser chooseThang;
    private final JButton btnTimKiem;
    private final JButton btnExcel;
    private final JLabel lblTienLuongThapNhatGiaTri;
    private final JLabel lblTienLuongCaoNhatGiaTri;
    private final JLabel lblTongSoNhanVienGiaTri;
    private final JLabel lblTongSoTienLuongGiaTri;
    private final JLabel lblNhanVienXuatSacGiaTri;
    private final DefaultTableModel tableModelDanhSachLuong;
    private Map<String, Object> thongTinThongKe;
    private  final ChartPanel pnlLuongNhanVien;
    private  final JPanel pnlMain;
    private  final JPanel pnl1;
    private  final JPanel pnlTienLuongThapNhat;
    private  final JPanel pnlTongSoNhanVien;
    private  final JPanel pnlNhanVienXuatSac;
    private  final JPanel pnlTienLuongCaoNhat;
    private  final JPanel pnlTongSoTienLuong;
    private  final JPanel pnl2;

    /**
     * Create the panel.
     */
    public LuongNhanVienForm() {

        init();

        setLayout(null);

        pnlMain = new JPanel();
        pnlMain.setBounds(10, 10, 1250, 825);
        pnlMain.setLayout(null);
        add(pnlMain);

        JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.title"));
        lblTitle.setBorder(null);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setBounds(0, 0, 1250, 80);
        pnlMain.add(lblTitle);

        pnl1 = new JPanel();
        pnl1.setBounds(10, 90, 1230, 178);
        pnlMain.add(pnl1);
        pnl1.setLayout(null);

        chooseNam = new JYearChooser();
        chooseNam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        chooseNam.getSpinner().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        chooseNam.setBounds(396, 10, 100, 40);
        pnl1.add(chooseNam);
        chooseThang = new JMonthChooser();
        chooseThang.getComboBox().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        chooseThang.getSpinner().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        chooseThang.setBounds(151, 10, 120, 40);
        pnl1.add(chooseThang);

        JLabel lblThang = new JLabel(SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbThang"));
        lblThang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblThang.setBounds(81, 10, 60, 40);
        pnl1.add(lblThang);

        JLabel lblNam = new JLabel(SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbNam"));
        lblNam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNam.setBounds(336, 10, 60, 40);
        pnl1.add(lblNam);

        btnTimKiem = new JButton(SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.btnThongKe"));
        btnTimKiem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_search.png"));
        btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnTimKiem.setBounds(562, 10, 137, 40);
        pnl1.add(btnTimKiem);

        btnExcel = new JButton(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("luongCongNhan.btnXuatBaoCao")));
        btnExcel.setIcon(new ImageIcon("src/main/resources/icon/png/ic_print.png"));
        btnExcel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnExcel.setBounds(762, 10, 167, 40);
        pnl1.add(btnExcel);

        pnlTienLuongThapNhat = new JPanel();
        pnlTienLuongThapNhat.setBounds(23, 75, 315, 30);
        pnl1.add(pnlTienLuongThapNhat);
        pnlTienLuongThapNhat.setLayout(null);

        JLabel lblTienLuongThapNhat = new JLabel(
                SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbTienLuongThapNhat"));
        lblTienLuongThapNhat.setBounds(0, 5, 139, 20);
        lblTienLuongThapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlTienLuongThapNhat.add(lblTienLuongThapNhat);

        lblTienLuongThapNhatGiaTri = new JLabel("??");
        lblTienLuongThapNhatGiaTri.setBounds(149, 5, 156, 20);
        lblTienLuongThapNhatGiaTri.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlTienLuongThapNhat.add(lblTienLuongThapNhatGiaTri);

        pnlTienLuongCaoNhat = new JPanel();
        pnlTienLuongCaoNhat.setLayout(null);
        pnlTienLuongCaoNhat.setBounds(23, 134, 315, 30);
        pnl1.add(pnlTienLuongCaoNhat);

        JLabel lblTienLuongCaoNhat = new JLabel(
                SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbTienLuongCaoNhat"));
        lblTienLuongCaoNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTienLuongCaoNhat.setBounds(0, 5, 139, 20);
        pnlTienLuongCaoNhat.add(lblTienLuongCaoNhat);

        lblTienLuongCaoNhatGiaTri = new JLabel("??");
        lblTienLuongCaoNhatGiaTri.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTienLuongCaoNhatGiaTri.setBounds(149, 5, 156, 20);
        pnlTienLuongCaoNhat.add(lblTienLuongCaoNhatGiaTri);

        pnlTongSoNhanVien = new JPanel();
        pnlTongSoNhanVien.setLayout(null);
        pnlTongSoNhanVien.setBounds(397, 75, 312, 30);
        pnl1.add(pnlTongSoNhanVien);

        JLabel lblTongSoNhanVien = new JLabel(
                SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbTongSoNhanVien"));
        lblTongSoNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTongSoNhanVien.setBounds(0, 5, 139, 20);
        pnlTongSoNhanVien.add(lblTongSoNhanVien);

        lblTongSoNhanVienGiaTri = new JLabel("??");
        lblTongSoNhanVienGiaTri.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTongSoNhanVienGiaTri.setBounds(149, 5, 153, 20);
        pnlTongSoNhanVien.add(lblTongSoNhanVienGiaTri);

        pnlTongSoTienLuong = new JPanel();
        pnlTongSoTienLuong.setLayout(null);
        pnlTongSoTienLuong.setBounds(396, 134, 312, 30);
        pnl1.add(pnlTongSoTienLuong);

        JLabel lblTongSoTienLuong = new JLabel(
                SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbTongSoTienLuong"));
        lblTongSoTienLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTongSoTienLuong.setBounds(0, 5, 139, 20);
        pnlTongSoTienLuong.add(lblTongSoTienLuong);

        lblTongSoTienLuongGiaTri = new JLabel("??");
        lblTongSoTienLuongGiaTri.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTongSoTienLuongGiaTri.setBounds(149, 5, 153, 20);
        pnlTongSoTienLuong.add(lblTongSoTienLuongGiaTri);

        pnlNhanVienXuatSac = new JPanel();
        pnlNhanVienXuatSac.setLayout(null);
        pnlNhanVienXuatSac.setBounds(738, 75, 390, 30);
        pnl1.add(pnlNhanVienXuatSac);

        JLabel lblNhanVienXuatSac = new JLabel(
                SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbNhanVienXuatSac"));
        lblNhanVienXuatSac.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNhanVienXuatSac.setBounds(0, 5, 139, 20);
        pnlNhanVienXuatSac.add(lblNhanVienXuatSac);

        lblNhanVienXuatSacGiaTri = new JLabel("??");
        lblNhanVienXuatSacGiaTri.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNhanVienXuatSacGiaTri.setBounds(149, 5, 207, 20);
        pnlNhanVienXuatSac.add(lblNhanVienXuatSacGiaTri);

        pnl2 = new JPanel();
        pnl2.setBounds(10, 340, 723, 475);
        pnlMain.add(pnl2);
        pnl2.setLayout(null);

        tableModelDanhSachLuong = new DefaultTableModel(
                new String[]{SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.stt"),
                        SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.maLuong"),
                        SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.maNhanVien"),
                        SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.tenNhanVien"),
                        SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.cccd"),
                        SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.luongThang"),
                        SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.ngayTinhLuong"),
                        SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.tongLuong")},
                0);
        JTable tblDanhSachLuong = new JTable();

        tblDanhSachLuong.setModel(tableModelDanhSachLuong);
        tblDanhSachLuong.setShowVerticalLines(true);
        tblDanhSachLuong.setShowHorizontalLines(true);
        tblDanhSachLuong.setRowHeight(25);
        tblDanhSachLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        JScrollPane jscLuongNhanVien = new JScrollPane(tblDanhSachLuong);
        jscLuongNhanVien.setBounds(10, 5, 703, 460);
        pnl2.add(jscLuongNhanVien);

        pnlLuongNhanVien = new ChartPanel(createCharLuong(null, chooseNam.getYear()));
        pnlLuongNhanVien.setBounds(743, 340, 500, 475);
        pnlMain.add(pnlLuongNhanVien);
        pnlLuongNhanVien.setZoomInFactor(2.0);
        event();
    }

    private JFreeChart createCharLuong(CategoryDataset dataset, int year) {
        return ChartFactory.createBarChart(
                String.format("%s: %s", SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbBangTongLuongNam"),
                        year),
                SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbThang"),
                SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.lbTongLuong"), dataset, PlotOrientation.VERTICAL,
                false, false, false);
    }

    private CategoryDataset createDatasetLuong(Map<String, Double> tongLuongNhanVienTheoThang) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= 12; i++) {
            dataset.addValue(
                    tongLuongNhanVienTheoThang.get(i + "") == null ? 0 : tongLuongNhanVienTheoThang.get(i + ""),
                    "Tổng lương", "T" + i);
        }
        return dataset;
    }

    private void event() {
        this.btnTimKiem.addActionListener((e) -> {
            thucHienChucNangThongKe();
        });
        this.btnExcel.addActionListener((e) -> {
            thucHienChucNangXuatThongKe();
        });
    }

    private void init() {
        this.thongTinThongKe = new HashMap<String, Object>();
    }

    private void thucHienChucNangThongKe() {
        new Thread(() -> {
            try(var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                var dos = new DataOutputStream(socket.getOutputStream());
                var dis = new DataInputStream(socket.getInputStream()))
            {
                int thang = this.chooseThang.getMonth() + 1;
                int nam = this.chooseNam.getYear();

                // send request
                RequestDTO request = RequestDTO.builder()
                        .requestType("LuongNhanVienForm")
                        .request("thongKeLuongNhanVienBangThangVaNam")
                        .data(Map.of("thang", thang, "nam", nam))
                        .build();

                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                // receive response
                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                this.thongTinThongKe = (Map<String, Object>) response.getData();

                int tongSoNhanVien = Integer.valueOf(this.thongTinThongKe.get("TongSoNhanVien").toString().replace(".0", ""));
                double tienLuongThapNhat = Double.valueOf(this.thongTinThongKe.get("TienLuongThapNhat").toString().replace(".0", ""));
                double tienLuongCaoNhat = Double.valueOf(this.thongTinThongKe.get("TienLuongCaoNhat").toString().replace(".0", ""));
                double tongSotienLuong = Double.valueOf(this.thongTinThongKe.get("TongSotienLuong").toString().replace(".0", ""));
                String nhanVienXuatSac = this.thongTinThongKe.get("NhanVienXuatSac").toString();

                List<Map<String, Object>> luongNhanViens = (List<Map<String, Object>>) this.thongTinThongKe
                        .get("LuongNhanViens");
                Map<String, Double> tongLuongNhanVienTheoThang = (Map<String, Double>) this.thongTinThongKe
                        .get("TongLuongNhanVienTheoThang");

                this.lblNhanVienXuatSacGiaTri.setText(nhanVienXuatSac + "");
                this.lblTienLuongCaoNhatGiaTri.setText(PriceFormatterUtils.format(tienLuongCaoNhat));
                this.lblTienLuongThapNhatGiaTri.setText(PriceFormatterUtils.format(tienLuongThapNhat));
                this.lblTongSoNhanVienGiaTri.setText(tongSoNhanVien + "");
                this.lblTongSoTienLuongGiaTri.setText(PriceFormatterUtils.format(tongSotienLuong));

                thucHienChucNangLoadLuongCongNhan(luongNhanViens);
                thucHienChucNangLoadThongKe(tongLuongNhanVienTheoThang, chooseNam.getYear());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void thucHienChucNangLoadLuongCongNhan(List<Map<String, Object>> luongNhanViens) {
        tableModelDanhSachLuong.setRowCount(0);
        int stt = 1;
        for (Map<String, Object> luongNhanVien : luongNhanViens) {
            tableModelDanhSachLuong.addRow(new Object[]{stt++, luongNhanVien.get("MaLuong"),
                    luongNhanVien.get("MaNhanVien"), luongNhanVien.get("TenNhanVien"), luongNhanVien.get("CCCD"),
                    luongNhanVien.get("LuongThang").toString().replace(".0", ""), luongNhanVien.get("NgayTinhLuong"),
                    PriceFormatterUtils.format(Double.valueOf(luongNhanVien.get("TongLuong").toString())),});
        }
    }

    private void thucHienChucNangLoadThongKe(Map<String, Double> tongLuongNhanVienTheoThang, int nam) {
        this.pnlLuongNhanVien.setChart(createCharLuong(createDatasetLuong(tongLuongNhanVienTheoThang), nam));
    }

    private void thucHienChucNangChuanBiXuat() {
        pnlMain.setBackground(Color.WHITE);
        pnl1.setBackground(Color.WHITE);
        pnlTienLuongThapNhat.setBackground(Color.WHITE);
        pnlTongSoNhanVien.setBackground(Color.WHITE);
        pnlNhanVienXuatSac.setBackground(Color.WHITE);
        pnlTienLuongCaoNhat.setBackground(Color.WHITE);
        pnlTongSoTienLuong.setBackground(Color.WHITE);
        pnl2.setBackground(Color.WHITE);
        chooseNam.setBackground(Color.WHITE);
        chooseThang.setBackground(Color.WHITE);
        btnTimKiem.setVisible(false);
        btnExcel.setVisible(false);
    }

    private void thucHienChucNangSauKhiXuat() {
        pnlMain.setBackground(null);
        pnl1.setBackground(null);
        pnlTienLuongThapNhat.setBackground(null);
        pnlTongSoNhanVien.setBackground(null);
        pnlNhanVienXuatSac.setBackground(null);
        pnlTienLuongCaoNhat.setBackground(null);
        pnlTongSoTienLuong.setBackground(null);
        pnl2.setBackground(null);
        chooseNam.setBackground(null);
        chooseThang.setBackground(null);
        btnTimKiem.setVisible(true);
        btnExcel.setVisible(true);
    }

    private void thucHienChucNangXuatThongKe() {
        try {
            thucHienChucNangChuanBiXuat();

            BufferedImage bi = new BufferedImage(pnlMain.getWidth(), pnlMain.getHeight(), BufferedImage.TYPE_INT_RGB);
            pnlMain.paint(bi.getGraphics());

            thucHienChucNangSauKhiXuat();
            String path = "./temp/thongkeluongnhanvien.jpg";
            ImageIO.write(bi, "jpg", new File(path));
            Desktop.getDesktop().print(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
