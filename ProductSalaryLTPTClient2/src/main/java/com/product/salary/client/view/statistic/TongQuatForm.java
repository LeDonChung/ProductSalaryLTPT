package com.product.salary.client.view.statistic;

import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;
import com.product.salary.client.common.SystemConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Lê Đôn Chủng: Code giao diện
 */
public class TongQuatForm extends JPanel {
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
    private final JLabel lblSoLuongCongNhan;
    private final JLabel lblSoLuongHopDong;
    private final JLabel lblSoLuongSanPham;
    private final JLabel lblSoLuongNhanVien;
    private final JButton btnExport;
    private final JPanel pnlMain;
    private final JPanel pnl1;
    private final JPanel pnl3;

    /**
     * Create the panel.
     */
    public TongQuatForm() {

        setLayout(null);

        pnlMain = new JPanel();
        pnlMain.setForeground(Color.BLACK);
        pnlMain.setBounds(10, 10, 1250, 825);
        pnlMain.setLayout(null);
        add(pnlMain);

        JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("thongKeTongQuat.title"));
        lblTitle.setBorder(null);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setBounds(0, 0, 1250, 80);
        pnlMain.add(lblTitle);

        pnl1 = new JPanel();
        pnl1.setBounds(10, 144, 1230, 209);
        pnlMain.add(pnl1);
        pnl1.setLayout(null);

        JPanel pnlNhanVien = new JPanel();
        pnlNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlNhanVien.setBounds(30, 10, 180, 180);
        pnl1.add(pnlNhanVien);
        pnlNhanVien.setLayout(null);

        lblSoLuongNhanVien = new JLabel("32");
        lblSoLuongNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        lblSoLuongNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblSoLuongNhanVien.setBounds(10, 141, 160, 29);
        pnlNhanVien.add(lblSoLuongNhanVien);

        JLabel lblHinhAnhNhanVien = new JLabel("");
        lblHinhAnhNhanVien
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_employee_120.png"));
        lblHinhAnhNhanVien.setBounds(27, 10, 120, 120);
        pnlNhanVien.add(lblHinhAnhNhanVien);

        JPanel pnlCongNhan = new JPanel();
        pnlCongNhan.setLayout(null);
        pnlCongNhan.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlCongNhan.setBounds(366, 10, 180, 180);
        pnl1.add(pnlCongNhan);

        lblSoLuongCongNhan = new JLabel("32");
        lblSoLuongCongNhan.setHorizontalAlignment(SwingConstants.CENTER);
        lblSoLuongCongNhan.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblSoLuongCongNhan.setBounds(10, 141, 160, 29);
        pnlCongNhan.add(lblSoLuongCongNhan);

        JLabel lblHinhAnhCongNhan = new JLabel("");
        lblHinhAnhCongNhan
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_workers_120.png"));
        lblHinhAnhCongNhan.setBounds(27, 10, 120, 120);
        pnlCongNhan.add(lblHinhAnhCongNhan);

        JPanel pnlHopDong = new JPanel();
        pnlHopDong.setLayout(null);
        pnlHopDong.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlHopDong.setBounds(694, 10, 180, 180);
        pnl1.add(pnlHopDong);

        lblSoLuongHopDong = new JLabel("32");
        lblSoLuongHopDong.setHorizontalAlignment(SwingConstants.CENTER);
        lblSoLuongHopDong.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblSoLuongHopDong.setBounds(10, 141, 160, 29);
        pnlHopDong.add(lblSoLuongHopDong);

        JLabel lblHinhAnhHopDong = new JLabel("");
        lblHinhAnhHopDong
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_contract_120.png"));
        lblHinhAnhHopDong.setBounds(27, 10, 120, 120);
        pnlHopDong.add(lblHinhAnhHopDong);

        JPanel pnlSanPham = new JPanel();
        pnlSanPham.setLayout(null);
        pnlSanPham.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlSanPham.setBounds(1031, 10, 180, 180);
        pnl1.add(pnlSanPham);

        lblSoLuongSanPham = new JLabel("32");
        lblSoLuongSanPham.setHorizontalAlignment(SwingConstants.CENTER);
        lblSoLuongSanPham.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblSoLuongSanPham.setBounds(10, 141, 160, 29);
        pnlSanPham.add(lblSoLuongSanPham);

        JLabel lblHinhAnhSanPham = new JLabel("");
        lblHinhAnhSanPham
                .setIcon(new ImageIcon("src/main/resources/icon/png/ic_product_120.png"));
        lblHinhAnhSanPham.setBounds(27, 10, 120, 120);
        pnlSanPham.add(lblHinhAnhSanPham);

        init();

        ChartPanel pnlThongKeLuong = new ChartPanel(createCharLuong(createDatasetLuong()));
        pnlThongKeLuong.setZoomInFactor(2.0);
        pnlThongKeLuong.setBounds(0, 10, 1230, 388);

        pnl3 = new JPanel();
        pnl3.setBounds(10, 363, 1230, 408);
        pnl3.setLayout(null);
        pnl3.add(pnlThongKeLuong);
        pnlMain.add(pnl3);

        btnExport = new JButton(String.format("<html><p>%s</p></html>",
                SystemConstants.BUNDLE.getString("luongCongNhan.btnXuatBaoCao")));
        btnExport.setIcon(new ImageIcon("src/main/resources/icon/png/ic_print.png"));
        btnExport.setBounds(1043, 90, 197, 44);
        pnlMain.add(btnExport);

        event();

    }

    private JFreeChart createCharLuong(CategoryDataset dataset) {
        return ChartFactory.createBarChart(SystemConstants.BUNDLE.getString("thongKeTongQuat.bieuDoLuongTheoNam"),
                SystemConstants.BUNDLE.getString("thongKeTongQuat.nam"),
                SystemConstants.BUNDLE.getString("thongKeTongQuat.tongLuong"), dataset, PlotOrientation.VERTICAL, false,
                false, false);
    }

    private CategoryDataset createDatasetLuong() {
        Callable<Map<String, Map<String, Object>>> callable = () -> {
            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())) {

                // send request
                RequestDTO request = RequestDTO
                        .builder()
                        .request("thongKeLuongTheoNam")
                        .requestType("TongQuatForm")
                        .build();

                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                // receive response
                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                return (Map<String, Map<String, Object>>) response.getData();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new HashMap<>();
        };
        FutureTask<Map<String, Map<String, Object>>> futureTask = new FutureTask<>(callable);
        Thread t = new Thread(futureTask);
        t.start();
        while (t.isAlive()) {
        }
        Map<String, Map<String, Object>> data = null;
        try {
            data = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        // Danh sách tổng lương chi lương theo năm
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Object> luongCongNhans = data.get("luongCongNhans");
        Map<String, Object> luongNhanViens = data.get("luongNhanViens");
        Set<String> nams = new HashSet<>();
        nams.addAll(luongCongNhans.keySet());
        nams.addAll(luongNhanViens.keySet());

        Map<String, Double> result = new LinkedHashMap<>();
        nams.forEach(tx -> {
            Double luongNamCongNhan = (Double) luongCongNhans.get(tx);
            Double luongNamNhanVien = (Double) luongNhanViens.get(tx);

            Double luong = (luongNamCongNhan == null ? 0 : luongNamCongNhan)
                    + (luongNamNhanVien == null ? 0 : luongNamNhanVien);
            result.put(tx, luong);
        });

        List<Entry<String, Double>> list = new ArrayList<>(result.entrySet());
        list.sort(Entry.comparingByKey(String::compareTo));

        list.forEach(tx -> dataset.addValue(tx.getValue(), "Tổng lương", tx.getKey()));
        return dataset;
    }

    private void event() {
        this.btnExport.addActionListener((e) -> thucHienChucNangXuatThongKe());
    }

    private void thucHienChucNangXuatThongKe() {
        try {
            thucHienChucNangChuanBiXuat();

            BufferedImage bi = new BufferedImage(pnlMain.getWidth(), pnlMain.getHeight(), BufferedImage.TYPE_INT_RGB);
            pnlMain.paint(bi.getGraphics());

            thucHienChucNangSauKhiXuat();
            String path = "./temp/thongketongquat.jpg";
            ImageIO.write(bi, "jpg", new File(path));
            Desktop.getDesktop().print(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void thucHienChucNangChuanBiXuat() {
        this.btnExport.setVisible(false);
        this.pnlMain.setBackground(Color.WHITE);
        this.pnlMain.setBackground(Color.WHITE);
        this.pnl1.setBackground(Color.WHITE);
        this.pnl3.setBackground(Color.WHITE);
    }

    private void thucHienChucNangSauKhiXuat() {
        this.btnExport.setVisible(true);
        this.pnlMain.setBackground(null);
        this.pnlMain.setBackground(null);
        this.pnl1.setBackground(null);
        this.pnl3.setBackground(null);
    }

    private void init() {
        thucHienChucNangLoadThongKeSoLuong();
    }

    private void thucHienChucNangLoadThongKeSoLuong() {
        new Thread(() -> {

            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())) {

                // send request
                RequestDTO request = RequestDTO
                        .builder()
                        .request("thucHienChucNangLoadThongKeSoLuong")
                        .requestType("TongQuatForm")
                        .build();
                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                // receive response
                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                Map<String, Object> map = (Map<String, Object>) response.getData();

                lblSoLuongNhanVien.setText(map.get("soLuongNhanVien").toString().replace(".0", ""));
                lblSoLuongCongNhan.setText(map.get("soLuongCongNhan").toString().replace(".0", ""));
                lblSoLuongHopDong.setText(map.get("soLuongHopDong").toString().replace(".0", ""));
                lblSoLuongSanPham.setText(map.get("soLuongSanPham").toString().replace(".0", ""));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
