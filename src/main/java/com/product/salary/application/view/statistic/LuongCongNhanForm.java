package com.product.salary.application.view.statistic;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.service.LuongCongNhanService;
import com.product.salary.application.service.impl.LuongCongNhanServiceImpl;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuongCongNhanForm extends JPanel {

	private JYearChooser chooseNam;
	private JMonthChooser chooseThang;
	private JButton btnTimKiem;
	private JButton btnExcel;
	private JLabel lblTienLuongThapNhatGiaTri;
	private JLabel lblTienLuongCaoNhatGiaTri;
	private JLabel lblTongSoCongNhanGiaTri;
	private JLabel lblTongSoTienLuongGiaTri;
	private JLabel lblCongNhanXuatSacGiaTri;
	private DefaultTableModel tableModelDanhSachLuong;
	private LuongCongNhanService luongCongNhanService;
	private Map<String, Object> thongTinThongKe;
	private ChartPanel pnlLuongCongNhan;
	private JPanel pnlMain;
	private JPanel pnl1;
	private JPanel pnlTienLuongThapNhat;
	private JPanel pnlTienLuongCaoNhat;
	private JPanel pnlTongSoCongNhan;
	private JPanel pnlTongSoTienLuong;
	private JPanel pnlCongNhanXuatSac;
	private JPanel pnl2;

	/**
	 * Create the panel.
	 */
	public LuongCongNhanForm() {
		init();

		setLayout(null);

		pnlMain = new JPanel();
		pnlMain.setBounds(10, 10, 1250, 825);
		pnlMain.setLayout(null);
		add(pnlMain);

		JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("thongKeLuongCongNhan.title"));
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
		btnExcel.setBounds(762, 10, 153, 40);
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

		pnlTongSoCongNhan = new JPanel();
		pnlTongSoCongNhan.setLayout(null);
		pnlTongSoCongNhan.setBounds(397, 75, 312, 30);
		pnl1.add(pnlTongSoCongNhan);

		JLabel lblTongSoCongNhan = new JLabel(
				SystemConstants.BUNDLE.getString("thongKeLuongCongNhan.lbTongSoCongNhan"));
		lblTongSoCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTongSoCongNhan.setBounds(0, 5, 139, 20);
		pnlTongSoCongNhan.add(lblTongSoCongNhan);

		lblTongSoCongNhanGiaTri = new JLabel("??");
		lblTongSoCongNhanGiaTri.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTongSoCongNhanGiaTri.setBounds(149, 5, 153, 20);
		pnlTongSoCongNhan.add(lblTongSoCongNhanGiaTri);

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

		pnlCongNhanXuatSac = new JPanel();
		pnlCongNhanXuatSac.setLayout(null);
		pnlCongNhanXuatSac.setBounds(738, 75, 366, 30);
		pnl1.add(pnlCongNhanXuatSac);

		JLabel lblCongNhanXuatSac = new JLabel(
				SystemConstants.BUNDLE.getString("thongKeLuongCongNhan.lbCongNhanXuatSac"));
		lblCongNhanXuatSac.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCongNhanXuatSac.setBounds(0, 5, 139, 20);
		pnlCongNhanXuatSac.add(lblCongNhanXuatSac);

		lblCongNhanXuatSacGiaTri = new JLabel("??");
		lblCongNhanXuatSacGiaTri.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCongNhanXuatSacGiaTri.setBounds(149, 5, 207, 20);
		pnlCongNhanXuatSac.add(lblCongNhanXuatSacGiaTri);

		pnl2 = new JPanel();
		pnl2.setBounds(10, 340, 723, 475);
		pnlMain.add(pnl2);
		pnl2.setLayout(null);

		tableModelDanhSachLuong = new DefaultTableModel(
				new String[] { SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.stt"),
						SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.maLuong"),
						SystemConstants.BUNDLE.getString("thongKeLuongCongNhan.tableDanhSachLuong.maCongNhan"),
						SystemConstants.BUNDLE.getString("thongKeLuongCongNhan.tableDanhSachLuong.tenCongNhan"),
						SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.cccd"),
						SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.luongThang"),
						SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.ngayTinhLuong"),
						SystemConstants.BUNDLE.getString("thongKeLuongNhanVien.tableDanhSachLuong.tongLuong") },
				0);
		JTable tblDanhSachLuong = new JTable();

		tblDanhSachLuong.setModel(tableModelDanhSachLuong);
		tblDanhSachLuong.setShowVerticalLines(true);
		tblDanhSachLuong.setShowHorizontalLines(true);
		tblDanhSachLuong.setRowHeight(25);
		tblDanhSachLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblDanhSachLuong.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		JScrollPane jscLuongCongNhan = new JScrollPane(tblDanhSachLuong);
		jscLuongCongNhan.setBounds(10, 5, 703, 460);
		pnl2.add(jscLuongCongNhan);

		pnlLuongCongNhan = new ChartPanel(createCharLuong(null, chooseNam.getYear()));
		pnlLuongCongNhan.setBounds(743, 340, 500, 475);
		pnlMain.add(pnlLuongCongNhan);
		pnlLuongCongNhan.setZoomInFactor(2.0);
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

	private CategoryDataset createDatasetLuong(Map<String, Double> tongLuongCongNhanTheoThang) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i <= 12; i++) {
			dataset.addValue(
					tongLuongCongNhanTheoThang.get(i + "") == null ? 0 : tongLuongCongNhanTheoThang.get(i + ""),
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
		this.luongCongNhanService = new LuongCongNhanServiceImpl();
		this.thongTinThongKe = new HashMap<String, Object>();
	}

	private void thucHienChucNangThongKe() {
		int thang = this.chooseThang.getMonth() + 1;
		int nam = this.chooseNam.getYear();
		this.thongTinThongKe = this.luongCongNhanService.thongKeLuongCongNhanBangThangVaNam(thang, nam);
		int tongSoCongNhan = Integer.valueOf(this.thongTinThongKe.get("TongSoCongNhan").toString());
		double tienLuongThapNhat = Double.valueOf(this.thongTinThongKe.get("TienLuongThapNhat").toString());
		double tienLuongCaoNhat = Double.valueOf(this.thongTinThongKe.get("TienLuongCaoNhat").toString());
		double tongSotienLuong = Double.valueOf(this.thongTinThongKe.get("TongSotienLuong").toString());
		String congNhanXuatSac = this.thongTinThongKe.get("CongNhanXuatSac").toString();

		List<Map<String, Object>> luongCongNhans = (List<Map<String, Object>>) this.thongTinThongKe
				.get("LuongCongNhans");
		Map<String, Double> tongLuongCongNhanTheoThang = (Map<String, Double>) this.thongTinThongKe
				.get("TongLuongCongNhanTheoThang");

		this.lblCongNhanXuatSacGiaTri.setText(congNhanXuatSac + "");
		this.lblTienLuongCaoNhatGiaTri.setText(PriceFormatterUtils.format(tienLuongCaoNhat));
		this.lblTienLuongThapNhatGiaTri.setText(PriceFormatterUtils.format(tienLuongThapNhat));
		this.lblTongSoCongNhanGiaTri.setText(tongSoCongNhan + "");
		this.lblTongSoTienLuongGiaTri.setText(PriceFormatterUtils.format(tongSotienLuong));

		thucHienChucNangLoadLuongCongNhan(luongCongNhans);
		thucHienChucNangLoadThongKe(tongLuongCongNhanTheoThang, chooseNam.getYear());
	}

	private void thucHienChucNangLoadLuongCongNhan(List<Map<String, Object>> luongCongNhans) {
		tableModelDanhSachLuong.setRowCount(0);
		int stt = 1;
		for (Map<String, Object> luongCongNhan : luongCongNhans) {
			tableModelDanhSachLuong.addRow(new Object[] { stt++, luongCongNhan.get("MaLuong"),
					luongCongNhan.get("MaCongNhan"), luongCongNhan.get("TenCongNhan"), luongCongNhan.get("CCCD"),
					luongCongNhan.get("LuongThang"), luongCongNhan.get("NgayTinhLuong"),
					PriceFormatterUtils.format(Double.valueOf(luongCongNhan.get("TongLuong").toString()))});
		}
	}

	private void thucHienChucNangLoadThongKe(Map<String, Double> tongLuongCongNhanTheoThang, int nam) {
		this.pnlLuongCongNhan.setChart(createCharLuong(createDatasetLuong(tongLuongCongNhanTheoThang), nam));
	}

	private void thucHienChucNangChuanBiXuat() {
		pnlMain.setBackground(Color.WHITE);
		pnl1.setBackground(Color.WHITE);
		pnlTienLuongThapNhat.setBackground(Color.WHITE);
		pnlTongSoCongNhan.setBackground(Color.WHITE);
		pnlCongNhanXuatSac.setBackground(Color.WHITE);
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
		pnlTongSoCongNhan.setBackground(null);
		pnlCongNhanXuatSac.setBackground(null);
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
