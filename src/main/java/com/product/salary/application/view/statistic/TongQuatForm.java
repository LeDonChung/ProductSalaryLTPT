package com.product.salary.application.view.statistic;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.service.*;
import com.product.salary.application.service.impl.*;
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
import java.io.File;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

/**
 * 
 * @author Lê Đôn Chủng: Code giao diện
 *
 */
public class TongQuatForm extends JPanel {

	private JLabel lblSoLuongCongNhan;
	private JLabel lblSoLuongHopDong;
	private JLabel lblSoLuongSanPham;
	private JLabel lblSoLuongNhanVien;
	private ChartPanel pnlThongKeLuong;
	private NhanVienService nhanVienService;
	private CongNhanService congNhanService;
	private HopDongService hopDongService;
	private SanPhamService sanPhamService;
	private LuongCongNhanService luongCongNhanService;
	private LuongNhanVienService luongNhanVienService;
	private JButton btnExport;
	private JPanel pnlMain;
	private JPanel pnl1;
	private JPanel pnl3;

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

		pnlThongKeLuong = new ChartPanel(createCharLuong(createDatasetLuong()));
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
		// Danh sách tổng lương chi lương theo năm
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<String, Double> luongCongNhans = this.luongCongNhanService.thongKeLuongCongNhanTheoNam();
		Map<String, Double> luongNhanViens = this.luongNhanVienService.thongKeLuongNhanVienTheoNam();
		Set<String> nams = new HashSet<String>();
		nams.addAll(luongCongNhans.keySet());
		nams.addAll(luongNhanViens.keySet());

		Map<String, Double> result = new LinkedHashMap<String, Double>();
		nams.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				Double luongNamCongNhan = luongCongNhans.get(t);
				Double luongNamNhanVien = luongNhanViens.get(t);

				Double luong = (luongNamCongNhan == null ? 0 : luongNamCongNhan)
						+ (luongNamNhanVien == null ? 0 : luongNamNhanVien);
				result.put(t, luong);
			}
		});

		List<Entry<String, Double>> list = new ArrayList<>(result.entrySet());
		Collections.sort(list, Entry.comparingByKey(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		}));

		list.forEach(new Consumer<Entry<String, Double>>() {

			@Override
			public void accept(Entry<String, Double> t) {
				dataset.addValue(t.getValue(), "Tổng lương", t.getKey());

			}
		});
		return dataset;
	}

	private void event() {
		this.btnExport.addActionListener((e) -> {
			thucHienChucNangXuatThongKe();
		});
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
		this.congNhanService = new CongNhanServiceImpl();
		this.nhanVienService = new NhanVienServiceImpl();
		this.hopDongService = new HopDongServiceImpl();
		this.sanPhamService = new SanPhamServiceImpl();
		this.luongCongNhanService = new LuongCongNhanServiceImpl();
		this.luongNhanVienService = new LuongNhanVienServiceImpl();
		thucHienChucNangLoadThongKeSoLuong();
	}

	private void thucHienChucNangLoadThongKeSoLuong() {
		this.lblSoLuongCongNhan.setText(congNhanService.tongSoLuongCongNhan() + "");
		this.lblSoLuongNhanVien.setText(nhanVienService.tongSoLuongNhanVien() + "");
		this.lblSoLuongHopDong.setText(hopDongService.tongSoLuongHopDong() + "");
		this.lblSoLuongSanPham.setText(sanPhamService.tongSoLuongSanPham() + "");

	}

}
