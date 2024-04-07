package com.product.salary.application.view.product;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.service.CongDoanSanPhamService;
import com.product.salary.application.service.SanPhamService;
import com.product.salary.application.service.TayNgheService;
import com.product.salary.application.service.impl.CongDoanSanPhamServiceImpl;
import com.product.salary.application.service.impl.SanPhamServiceImpl;
import com.product.salary.application.service.impl.TayNgheServiceImpl;
import com.product.salary.application.utils.DateConvertUtils;
import com.product.salary.application.utils.PriceFormatterUtils;
import com.product.salary.application.utils.excels.CongDoanSanPhamExcelUtils;
import com.toedter.calendar.JDateChooser;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class CongDoanSanPhamForm extends JPanel {
	private JTextField txtTenSanPham;
	private JTextField txtMaCongDoan;
	private JTextField txtTenCongDoan;
	private JTextField txtSoLuongCanLam;
	private JTextField txtGiaCongDoan;
	private JTextField txtMaSanPham;
	private JTable tblDanhSachCongDoan;
	private JLabel lblLoiTenCongDoan;
	private JLabel lblLoiSoLuongCanLam;
	private JLabel lblLoiGiaCongDoan;
	private JLabel lblLoiThoiHan;
	private JLabel lblLoiThuTuLam;

	private List<SanPham> sanPhams;
	private List<CongDoanSanPham> congDoanSanPhams;
	private SanPhamService sanPhamService;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private JTable tblDanhSachSanPham;
	private DefaultTableModel tableModelSanPham;
	private CongDoanSanPhamService congDoanSanPhamService;
	private TayNgheService tayNgheService;
	private List<TayNghe> tayNghes;
	private DefaultTableModel tableModelCongDoanSanPham;
	private JDateChooser jcThoiHan;
	private DefaultComboBoxModel<TayNghe> ccbModelTayNghe;
	private List<CongDoanSanPham> congDoanSanPhamLamTruoc;
	private JComboBox cbbTrangThai;
	private JButton btnThemNhieu;
	private JButton btnExport;
	private JCheckBox cbxCongDoanLamTruoc;
	private JComboBox cbbCongDoanSanPhamLamTruoc;
	private DefaultComboBoxModel<CongDoanSanPham> dfcbbCongDoanSanPhamLamTruoc;

	/**
	 * Create the panel.
	 */
	public CongDoanSanPhamForm() {

		setLayout(null);

		JPanel pnlMain = new JPanel();
		pnlMain.setBounds(10, 10, 1250, 825);
		pnlMain.setLayout(null);
		add(pnlMain);

		JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("congDoanSanPham.title"));
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setBounds(0, 0, 1250, 80);
		pnlMain.add(lblTitle);

		JPanel pnl1 = new JPanel();
		pnl1.setLayout(null);
		pnl1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnl1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl1.setBounds(718, 113, 520, 467);
		pnlMain.add(pnl1);

		JPanel pnlTenCongDoan = new JPanel();
		pnlTenCongDoan.setLayout(null);
		pnlTenCongDoan.setBounds(10, 74, 500, 61);
		pnl1.add(pnlTenCongDoan);

		JLabel lblTenCongDoan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("congDoanSanPham.lbTenCongDoan")));
		lblTenCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenCongDoan.setBounds(0, 0, 95, 40);
		pnlTenCongDoan.add(lblTenCongDoan);

		txtTenCongDoan = new JTextField();
		txtTenCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenCongDoan.setColumns(10);
		txtTenCongDoan.setBounds(105, 0, 385, 40);
		pnlTenCongDoan.add(txtTenCongDoan);

		lblLoiTenCongDoan = new JLabel("");
		lblLoiTenCongDoan.setForeground(Color.RED);
		lblLoiTenCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiTenCongDoan.setBounds(105, 41, 385, 20);
		pnlTenCongDoan.add(lblLoiTenCongDoan);

		JPanel pnlSoLuongCanLam = new JPanel();
		pnlSoLuongCanLam.setLayout(null);
		pnlSoLuongCanLam.setBounds(10, 134, 500, 61);
		pnl1.add(pnlSoLuongCanLam);

		JLabel lblSoLuongCanLam = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("congDoanSanPham.lbSoLuongCan")));
		lblSoLuongCanLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongCanLam.setBounds(0, 0, 95, 40);
		pnlSoLuongCanLam.add(lblSoLuongCanLam);

		txtSoLuongCanLam = new JTextField();
		txtSoLuongCanLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoLuongCanLam.setColumns(10);
		txtSoLuongCanLam.setBounds(105, 0, 385, 40);
		pnlSoLuongCanLam.add(txtSoLuongCanLam);

		lblLoiSoLuongCanLam = new JLabel("");
		lblLoiSoLuongCanLam.setForeground(Color.RED);
		lblLoiSoLuongCanLam.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiSoLuongCanLam.setBounds(105, 41, 385, 20);
		pnlSoLuongCanLam.add(lblLoiSoLuongCanLam);

		JPanel pnlGiaCongDoan = new JPanel();
		pnlGiaCongDoan.setLayout(null);
		pnlGiaCongDoan.setBounds(10, 194, 500, 61);
		pnl1.add(pnlGiaCongDoan);

		JLabel lblGiaCongDoan = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("congDoanSanPham.lbLuong")));
		lblGiaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGiaCongDoan.setBounds(0, 0, 95, 40);
		pnlGiaCongDoan.add(lblGiaCongDoan);

		txtGiaCongDoan = new JTextField();
		txtGiaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtGiaCongDoan.setColumns(10);
		txtGiaCongDoan.setBounds(105, 0, 385, 40);
		pnlGiaCongDoan.add(txtGiaCongDoan);

		lblLoiGiaCongDoan = new JLabel("");
		lblLoiGiaCongDoan.setForeground(Color.RED);
		lblLoiGiaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiGiaCongDoan.setBounds(105, 41, 385, 20);
		pnlGiaCongDoan.add(lblLoiGiaCongDoan);

		JPanel pnlThoiHan = new JPanel();
		pnlThoiHan.setLayout(null);
		pnlThoiHan.setBounds(10, 254, 500, 61);
		pnl1.add(pnlThoiHan);

		JLabel lblThoiHan = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("congDoanSanPham.lbThoiHan")));
		lblThoiHan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblThoiHan.setBounds(0, 0, 95, 40);
		pnlThoiHan.add(lblThoiHan);

		jcThoiHan = new JDateChooser();
		jcThoiHan.getCalendarButton()
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
		jcThoiHan.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
		jcThoiHan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcThoiHan.setDateFormatString("yyyy-MM-dd");
		jcThoiHan.setBounds(105, 0, 385, 40);
		pnlThoiHan.add(jcThoiHan);

		lblLoiThoiHan = new JLabel("");
		lblLoiThoiHan.setForeground(Color.RED);
		lblLoiThoiHan.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiThoiHan.setBounds(105, 40, 385, 20);
		pnlThoiHan.add(lblLoiThoiHan);

		JPanel pnlThuTuLam = new JPanel();
		pnlThuTuLam.setLayout(null);
		pnlThuTuLam.setBounds(10, 314, 500, 57);
		pnl1.add(pnlThuTuLam);

		JLabel lblYeuCauCongDoanLamTruoc = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("congDoanSanPham.lbThuTuLam")));
		lblYeuCauCongDoanLamTruoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblYeuCauCongDoanLamTruoc.setBounds(0, 0, 95, 40);
		pnlThuTuLam.add(lblYeuCauCongDoanLamTruoc);

		lblLoiThuTuLam = new JLabel("");
		lblLoiThuTuLam.setForeground(Color.RED);
		lblLoiThuTuLam.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiThuTuLam.setBounds(105, 38, 385, 20);
		pnlThuTuLam.add(lblLoiThuTuLam);

		cbxCongDoanLamTruoc = new JCheckBox("Công đoạn làm trước");
		cbxCongDoanLamTruoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbxCongDoanLamTruoc.setBounds(101, 0, 159, 40);
		pnlThuTuLam.add(cbxCongDoanLamTruoc);

		dfcbbCongDoanSanPhamLamTruoc = new DefaultComboBoxModel<CongDoanSanPham>();
		cbbCongDoanSanPhamLamTruoc = new JComboBox(dfcbbCongDoanSanPhamLamTruoc);
		cbbCongDoanSanPhamLamTruoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbbCongDoanSanPhamLamTruoc.setBounds(266, 0, 224, 40);
		pnlThuTuLam.add(cbbCongDoanSanPhamLamTruoc);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(null);
		pnlTrangThai.setBounds(10, 374, 500, 61);
		pnl1.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("congDoanSanPham.lbTrangThai")));
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrangThai.setBounds(0, 0, 95, 40);
		pnlTrangThai.add(lblTrangThai);

		JLabel lblLoiTrangThai = new JLabel("");
		lblLoiTrangThai.setForeground(Color.RED);
		lblLoiTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiTrangThai.setBounds(105, 40, 385, 20);
		pnlTrangThai.add(lblLoiTrangThai);

		cbbTrangThai = new JComboBox();
		cbbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Đang hoạt đông", "Đã ngưng hoạt động" }));
		cbbTrangThai.setBounds(105, 0, 385, 40);
		pnlTrangThai.add(cbbTrangThai);

		JPanel pnlMaCongDoan = new JPanel();
		pnlMaCongDoan.setBounds(10, 10, 500, 61);
		pnl1.add(pnlMaCongDoan);
		pnlMaCongDoan.setLayout(null);

		JLabel lblMaCongDoan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("congDoanSanPham.lbMaCongDoan")));
		lblMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaCongDoan.setBounds(0, 0, 95, 40);
		pnlMaCongDoan.add(lblMaCongDoan);

		txtMaCongDoan = new JTextField();
		txtMaCongDoan.setEnabled(false);
		txtMaCongDoan.setEditable(false);
		txtMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaCongDoan.setColumns(10);
		txtMaCongDoan.setBounds(105, 0, 385, 40);
		pnlMaCongDoan.add(txtMaCongDoan);

		ccbModelTayNghe = new DefaultComboBoxModel<TayNghe>();

		JLabel lblThongTinCongDoan = new JLabel(SystemConstants.BUNDLE.getString("congDoanSanPham.lbThongTinCongDoan"));
		lblThongTinCongDoan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThongTinCongDoan.setBounds(718, 90, 522, 22);
		pnlMain.add(lblThongTinCongDoan);

		JPanel pnl2 = new JPanel();
		pnl2.setLayout(null);
		pnl2.setBounds(10, 114, 700, 239);
		pnlMain.add(pnl2);

		tableModelSanPham = new DefaultTableModel(
				new String[] { SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachSanPham.stt"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachSanPham.maSanPham"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachSanPham.tenSanPham"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachSanPham.soLuongCongDoan") },
				0);

		tblDanhSachSanPham = new JTable(tableModelSanPham);
		tblDanhSachSanPham.setShowVerticalLines(true);
		tblDanhSachSanPham.setShowHorizontalLines(true);
		tblDanhSachSanPham.setRowHeight(25);
		JScrollPane scrDanhSachSanPham = new JScrollPane(tblDanhSachSanPham);
		scrDanhSachSanPham.setBounds(0, 0, 700, 232);
		pnl2.add(scrDanhSachSanPham);

		JLabel lblDanhSachSanPham = new JLabel(SystemConstants.BUNDLE.getString("congDoanSanPham.lbDanhSachSanPham"));
		lblDanhSachSanPham.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDanhSachSanPham.setBounds(10, 90, 522, 22);
		pnlMain.add(lblDanhSachSanPham);

		JLabel lblDanhSachCongDoan = new JLabel(SystemConstants.BUNDLE.getString("congDoanSanPham.lbCongDoanSanPham"));
		lblDanhSachCongDoan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDanhSachCongDoan.setBounds(10, 591, 522, 22);
		pnlMain.add(lblDanhSachCongDoan);

		JPanel pnl3 = new JPanel();
		pnl3.setLayout(null);
		pnl3.setBounds(10, 623, 1228, 192);
		pnlMain.add(pnl3);

		tableModelCongDoanSanPham = new DefaultTableModel(
				new Object[][] { { null }, { null }, { null }, { null }, { null }, { null }, },
				new String[] { SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachCongDoan.stt"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachCongDoan.maCongDoan"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachCongDoan.tenCongDoan"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachCongDoan.luong"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachCongDoan.thoiHan"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachCongDoan.congDoanLamTruoc"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachCongDoan.soLuongCan"),
						SystemConstants.BUNDLE.getString("congDoanSanPham.tableDanhSachCongDoan.trangThai"), });

		tblDanhSachCongDoan = new JTable(tableModelCongDoanSanPham);
		tblDanhSachCongDoan.setShowVerticalLines(true);
		tblDanhSachCongDoan.setShowHorizontalLines(true);
		tblDanhSachCongDoan.setRowHeight(25);
		tblDanhSachCongDoan.setBounds(0, 0, 698, 275);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblDanhSachCongDoan.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

		JScrollPane scrDanhSachCongDoan = new JScrollPane(tblDanhSachCongDoan);
		scrDanhSachCongDoan.setBounds(0, 0, 1218, 175);
		pnl3.add(scrDanhSachCongDoan);

		JPanel pnl4 = new JPanel();
		pnl4.setBounds(12, 363, 696, 128);
		pnlMain.add(pnl4);
		pnl4.setLayout(null);

		btnThem = new JButton(SystemConstants.BUNDLE.getString("congDoanSanPham.btnThem"));
		btnThem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThem.setBounds(188, 10, 135, 50);
		pnl4.add(btnThem);

		btnXoa = new JButton(SystemConstants.BUNDLE.getString("congDoanSanPham.btnXoa"));
		btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoa.setBounds(551, 10, 135, 50);
		pnl4.add(btnXoa);

		btnCapNhat = new JButton(SystemConstants.BUNDLE.getString("congDoanSanPham.btnCapNhat"));
		btnCapNhat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCapNhat.setBounds(373, 10, 135, 50);
		pnl4.add(btnCapNhat);

		btnXoaTrang = new JButton(SystemConstants.BUNDLE.getString("congDoanSanPham.btnLamMoi"));
		btnXoaTrang
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoaTrang.setBounds(10, 10, 135, 50);
		pnl4.add(btnXoaTrang);

		btnThemNhieu = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.btnThemNhieu")));
		btnThemNhieu.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThemNhieu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThemNhieu.setBounds(10, 70, 135, 50);
		pnl4.add(btnThemNhieu);

		btnExport = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.btnExport")));
		btnExport.setIcon(new ImageIcon("src/main/resources/icon/png/ic_exel_.png"));
		btnExport.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnExport.setBounds(188, 70, 135, 50);
		pnl4.add(btnExport);

		JPanel pnl5 = new JPanel();
		pnl5.setBounds(10, 491, 698, 90);
		pnlMain.add(pnl5);
		pnl5.setLayout(null);

		JPanel pnlMaSanPham = new JPanel();
		pnlMaSanPham.setBounds(10, 10, 331, 61);
		pnl5.add(pnlMaSanPham);
		pnlMaSanPham.setLayout(null);

		JLabel lblMaSanPham = new JLabel(SystemConstants.BUNDLE.getString("congDoanSanPham.lbMaSanPham"));
		lblMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaSanPham.setBounds(0, 0, 95, 40);
		pnlMaSanPham.add(lblMaSanPham);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setEnabled(false);
		txtMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaSanPham.setEditable(false);
		txtMaSanPham.setColumns(10);
		txtMaSanPham.setBounds(105, 0, 216, 40);
		pnlMaSanPham.add(txtMaSanPham);

		JPanel pnlTenSanPham = new JPanel();
		pnlTenSanPham.setBounds(357, 10, 331, 61);
		pnl5.add(pnlTenSanPham);
		pnlTenSanPham.setLayout(null);

		JLabel lblTenSanPham = new JLabel(SystemConstants.BUNDLE.getString("congDoanSanPham.lbTenSanPham"));
		lblTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenSanPham.setBounds(0, 0, 95, 40);
		pnlTenSanPham.add(lblTenSanPham);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setEnabled(false);
		txtTenSanPham.setEditable(false);
		txtTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenSanPham.setColumns(10);
		txtTenSanPham.setBounds(105, 0, 215, 40);
		pnlTenSanPham.add(txtTenSanPham);

		init();
		event();

	}

	private void init() {
		this.sanPhams = new ArrayList<>();
		this.tayNghes = new ArrayList<>();
		this.congDoanSanPhams = new ArrayList<>();
		this.congDoanSanPhamLamTruoc = new ArrayList<CongDoanSanPham>();
		this.tayNgheService = new TayNgheServiceImpl();
		this.sanPhamService = new SanPhamServiceImpl();
		this.congDoanSanPhamService = new CongDoanSanPhamServiceImpl();
		this.loadDataSanPham();

	}

	private void loadDataCongDoanSanPham(String maSanPham) {
		this.tableModelCongDoanSanPham.setRowCount(0);
		this.congDoanSanPhams = this.congDoanSanPhamService.timTatCaCongDoanSanPham(maSanPham);
		int stt = 1;
		for (CongDoanSanPham congDoanSanPham : this.congDoanSanPhams) {
			tableModelCongDoanSanPham
					.addRow(new Object[] { stt++, congDoanSanPham.getMaCongDoan(), congDoanSanPham.getTenCongDoan(),
							PriceFormatterUtils.format(congDoanSanPham.getGiaCongDoan()), congDoanSanPham.getThoiHan(),
							congDoanSanPham.getCongDoanLamTruoc() != null ? congDoanSanPham.getCongDoanLamTruoc().getTenCongDoan() : "", congDoanSanPham.getSoLuongCanLam(),
							congDoanSanPham.isTrangThai() ? "Đang hoạt động" : "Đã ngưng hoạt động" });
		}

		this.congDoanSanPhamLamTruoc = this.congDoanSanPhams;
		if (!this.congDoanSanPhamLamTruoc.isEmpty()) {
			this.dfcbbCongDoanSanPhamLamTruoc.removeAllElements();
			this.dfcbbCongDoanSanPhamLamTruoc.addAll(congDoanSanPhamLamTruoc);
			this.dfcbbCongDoanSanPhamLamTruoc.setSelectedItem(congDoanSanPhamLamTruoc.get(0));
		}

	}

	private void loadDataSanPham() {
		this.tableModelSanPham.setRowCount(0);
		this.sanPhams = this.sanPhamService.timTatCaSanPhamDangSanXuat();
		int stt = 1;
		for (SanPham sanPham : this.sanPhams) {
			tableModelSanPham.addRow(
					new Object[] { stt++, sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getCongDoanSanPhams().size() });

		}
	}

	private void event() {
		this.btnThemNhieu.addActionListener((e) -> {
			thucHienChucNangThemNhieu();
		});
		this.btnExport.addActionListener((e) -> {
			thucHienChucNangXuatDanhSach();
		});
		this.tblDanhSachSanPham.addMouseListener(new MouseListener() {

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
				thucHienChucNangChonSanPham();
			}

		});

		this.tblDanhSachCongDoan.addMouseListener(new MouseListener() {

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
				thucHienThucNangChonCongDoanSanPham();
			}
		});

		this.btnXoaTrang.addActionListener((e) -> {
			thucHienChucNangXoaTrang();
		});

		this.btnThem.addActionListener((e) -> {
			thucHienChucNangThemCongDoan();
		});

		this.btnCapNhat.addActionListener((e) -> {
			thucHienChucNangCapNhatCongDoan();
		});
		this.btnXoa.addActionListener((e) -> {
			thucHienChucNangThemXoaCongDoan();
		});
	}

	private void thucHienChucNangXuatDanhSach() {
		int index = this.tblDanhSachSanPham.getSelectedRow();
		if (index >= 0) {
			CongDoanSanPhamExcelUtils.exportExcelCongDoanSanPham(congDoanSanPhams);
		} else {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.vuiLongChonSanPham"));
		}
	}

	private void thucHienChucNangThemNhieu() {
		int index = this.tblDanhSachSanPham.getSelectedRow();
		if (index >= 0) {
			List<CongDoanSanPham> congDoanSanPhams = CongDoanSanPhamExcelUtils.importExcelSanPham();
			if (!congDoanSanPhams.isEmpty()) {
				SanPham sanPham = sanPhams.get(index);
				congDoanSanPhams = this.congDoanSanPhamService.themNhieuCongDoanSanPham(sanPham, congDoanSanPhams);
				String message = "";
				if (SystemConstants.LANGUAGE == 0) {
					message = String.format("Đã thêm %d công đoạn sản phẩm vào sản phẩm %s.", congDoanSanPhams.size(),
							sanPham.getTenSanPham());
				} else {
					message = String.format("Added %d of the product stage to the product %s.", congDoanSanPhams.size(),
							sanPham.getTenSanPham());
				}
				JOptionPane.showMessageDialog(this, message);
				thucHienChucNangXoaTrang();
			}
		} else {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.vuiLongChonSanPham"));
		}

	}

	private void thucHienChucNangXoaTrang() {
		thucHienThucLamLamMoiLoi();
		this.txtMaSanPham.setText("");
		this.txtTenSanPham.setText("");
		this.txtMaCongDoan.setText("");
		this.txtTenCongDoan.setText("");
		this.txtSoLuongCanLam.setText("");
		this.txtGiaCongDoan.setText("");
		this.jcThoiHan.setDate(null);
		this.loadDataSanPham();
		if (!this.sanPhams.isEmpty()) {
			this.loadDataCongDoanSanPham(this.sanPhams.get(0).getMaSanPham());
		}
	}

	private void thucHienChucNangLamMoi() {
		thucHienThucLamLamMoiLoi();
		this.txtMaSanPham.setText("");
		this.txtTenSanPham.setText("");
		this.txtMaCongDoan.setText("");
		this.txtTenCongDoan.setText("");
		this.txtSoLuongCanLam.setText("");
		this.txtGiaCongDoan.setText("");
		this.jcThoiHan.setDate(null);
		int select = this.tblDanhSachSanPham.getSelectedRow();
		this.loadDataSanPham();
		if (!this.sanPhams.isEmpty()) {
			this.tblDanhSachSanPham.setRowSelectionInterval(select, select);
			if (select >= 0) {
				this.loadDataCongDoanSanPham(this.sanPhams.get(select).getMaSanPham());
			} else {
				this.loadDataCongDoanSanPham(this.sanPhams.get(0).getMaSanPham());
			}
		}
	}

	private void thucHienThucNangChonCongDoanSanPham() {
		int index = tblDanhSachCongDoan.getSelectedRow();
		if (index >= 0) {
			CongDoanSanPham congDoanSanPham = this.congDoanSanPhams.get(index);
			this.txtMaCongDoan.setText(congDoanSanPham.getMaCongDoan());
			this.txtTenCongDoan.setText(congDoanSanPham.getTenCongDoan());
			this.txtSoLuongCanLam.setText(congDoanSanPham.getSoLuongCanLam() + "");
			this.cbxCongDoanLamTruoc
					.setSelected(congDoanSanPham.getCongDoanLamTruoc() != null ? true : false);
			this.dfcbbCongDoanSanPhamLamTruoc.setSelectedItem((congDoanSanPham.getCongDoanLamTruoc() != null) ? congDoanSanPham.getCongDoanLamTruoc() : null);
			this.txtGiaCongDoan.setText(PriceFormatterUtils.format(congDoanSanPham.getGiaCongDoan()));
			this.cbbTrangThai.setSelectedIndex(congDoanSanPham.isTrangThai() ? 0 : 1);
			this.jcThoiHan.setDate(DateConvertUtils.asUtilDate(congDoanSanPham.getThoiHan(), ZoneId.systemDefault()));
		} else {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.vuiLongChonSanPham"));
		}
	}

	private void thucHienChucNangChonSanPham() {
		int index = tblDanhSachSanPham.getSelectedRow();
		if (index >= 0) {
			SanPham sanPham = this.sanPhams.get(index);
			this.txtMaSanPham.setText(sanPham.getMaSanPham());
			this.txtTenSanPham.setText(sanPham.getTenSanPham());
			loadDataCongDoanSanPham(sanPham.getMaSanPham());
		} else {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.vuiLongChonSanPham"));
		}
	}

	private void thucHienChucNangThemCongDoan() {
		try {
			int posSanPham = this.tblDanhSachSanPham.getSelectedRow();
			if (posSanPham >= 0) {
				if (!thucHienChucNangKiemTra()) {
					return;
				}
				SanPham sanPham = this.sanPhams.get(posSanPham);
				String maCongDoan = this.congDoanSanPhamService.generateMaCongDoanSanPham(sanPham.getMaSanPham());

				String tenCongDoan = this.txtTenCongDoan.getText().trim();
				int soLuongCanLam = Integer.valueOf(this.txtSoLuongCanLam.getText().trim());
				double giaCongDoan = PriceFormatterUtils.parse(this.txtGiaCongDoan.getText().trim());
				LocalDate thoiHan = DateConvertUtils.asLocalDate(this.jcThoiHan.getDate(), ZoneId.systemDefault());

				boolean isSelectCongDoanTruoc = this.cbxCongDoanLamTruoc.isSelected();
				CongDoanSanPham congDoanSanPham = null;
				if (isSelectCongDoanTruoc) {
					int index = this.cbbCongDoanSanPhamLamTruoc.getSelectedIndex();
					congDoanSanPham = this.congDoanSanPhamLamTruoc.get(index);
				}
				boolean trangThai = this.cbbTrangThai.getSelectedIndex() == 0 ? true : false;

				CongDoanSanPham congDoanSanPhamUpdate = new CongDoanSanPham(maCongDoan, tenCongDoan, soLuongCanLam,
						giaCongDoan, thoiHan, congDoanSanPham, sanPham, trangThai);
				congDoanSanPhamUpdate = this.congDoanSanPhamService.themCongDoanSanPham(congDoanSanPhamUpdate);
				if (congDoanSanPhamUpdate != null) {
					JOptionPane.showMessageDialog(this,
							SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.themCongDoanThanhCong"));
					thucHienChucNangLamMoi();
				} else {
					JOptionPane.showMessageDialog(this,
							SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.themCongDoanKhongThanhCong"));
				}
			} else {
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.vuiLongChonSanPham"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void thucHienChucNangCapNhatCongDoan() {
		try {
			int posSanPham = this.tblDanhSachSanPham.getSelectedRow();
			if (posSanPham >= 0) {
				int posCongDoan = this.tblDanhSachCongDoan.getSelectedRow();
				if (posCongDoan >= 0) {
					if (!thucHienChucNangKiemTra()) {
						return;
					}

					int choose = JOptionPane.showConfirmDialog(this,
							SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.xacNhanCapNhatCongDoan"),
							SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.xacNhan"),
							JOptionPane.YES_NO_OPTION);
					if (choose == JOptionPane.YES_OPTION) {

						SanPham sanPham = this.sanPhams.get(posSanPham);

						String maCongDoan = this.txtMaCongDoan.getText().trim();
						String tenCongDoan = this.txtTenCongDoan.getText().trim();
						int soLuongCanLam = Integer.valueOf(this.txtSoLuongCanLam.getText().trim());
						double giaCongDoan = PriceFormatterUtils.parse(this.txtGiaCongDoan.getText().trim());
						LocalDate thoiHan = DateConvertUtils.asLocalDate(this.jcThoiHan.getDate(),
								ZoneId.systemDefault());

						boolean isSelectCongDoanTruoc = this.cbxCongDoanLamTruoc.isSelected();
						CongDoanSanPham congDoanSanPham = null;
						if (isSelectCongDoanTruoc) {
							int index = this.cbbCongDoanSanPhamLamTruoc.getSelectedIndex();
							congDoanSanPham = this.congDoanSanPhamLamTruoc.get(index);
						}
						boolean trangThai = this.cbbTrangThai.getSelectedIndex() == 0 ? true : false;

						CongDoanSanPham congDoanSanPhamUpdate = new CongDoanSanPham(maCongDoan, tenCongDoan,
								soLuongCanLam, giaCongDoan, thoiHan, congDoanSanPham, sanPham, trangThai);

						congDoanSanPhamUpdate = this.congDoanSanPhamService
								.capNhatCongDoanSanPham(congDoanSanPhamUpdate);

						if (congDoanSanPhamUpdate != null) {
							JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE
									.getString("congDoanSanPham.thongBao.capNhatCongDoanThanhCong"));
							thucHienChucNangLamMoi();
						} else {
							JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE
									.getString("congDoanSanPham.thongBao.capNhatCongDoanKhongThanhCong"));
						}
					}
				} else {
					JOptionPane.showMessageDialog(this,
							SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.vuiLongChonCongDoan"));
				}
			} else {
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.vuiLongChonSanPham"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void thucHienChucNangThemXoaCongDoan() {
		try {
			int posSanPham = this.tblDanhSachSanPham.getSelectedRow();
			if (posSanPham >= 0) {
				int posCongDoan = this.tblDanhSachCongDoan.getSelectedRow();
				if (posCongDoan >= 0) {
					CongDoanSanPham congDoanSanPham = this.congDoanSanPhams.get(posCongDoan);

					String message = "";
					String title = "";
					if (SystemConstants.LANGUAGE == 0) {
						message = "Bạn có muốn xóa công đoạn " + congDoanSanPham.getTenCongDoan() + " ?.";
						title = "Xác nhận";
					} else {
						message = "Do you want to delete product stage " + congDoanSanPham.getTenCongDoan() + " ?.";
						title = "Confirm";
					}

					int choose = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
					if (choose == JOptionPane.YES_OPTION) {
						boolean status = this.congDoanSanPhamService
								.capNhatTrangThaiCongDoanSanPham(congDoanSanPham.getMaCongDoan(), false);
						if (status) {
							JOptionPane.showMessageDialog(this,
									SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.xoaCongDoanThanhCong"));
							thucHienChucNangLamMoi();
						} else {
							JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE
									.getString("congDoanSanPham.thongBao.xoaCongDoanKhongThanhCong"));
						}
					}
				} else {
					JOptionPane.showMessageDialog(this,
							SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.vuiLongChonCongDoan"));
				}
			} else {
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.vuiLongChonSanPham"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean thucHienChucNangKiemTra() {
		thucHienThucLamLamMoiLoi();

		String tenCongDoan = this.txtTenCongDoan.getText().trim();
		String soLuongCanLam = this.txtSoLuongCanLam.getText().trim();
		String giaCongDoan = this.txtGiaCongDoan.getText().trim();
		LocalDate thoiHan = DateConvertUtils.asLocalDate(this.jcThoiHan.getDate(), ZoneId.systemDefault());

		boolean status = true;
		if (ObjectUtils.isEmpty(tenCongDoan)) {
			lblLoiTenCongDoan.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.tenCongDoan")));
			status = false;
		}

		if (ObjectUtils.isEmpty(soLuongCanLam)) {
			lblLoiSoLuongCanLam.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.soLuongCanLam")));
			status = false;
		} else {
			try {
				if (Integer.parseInt(soLuongCanLam) < 0) {
					lblLoiSoLuongCanLam.setText(String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.soLuongCanlamSoNguyen")));
					status = false;
				}
			} catch (Exception e) {
				lblLoiSoLuongCanLam.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.soLuongCanlamSoNguyen")));
				status = false;
			}
		}

		if (ObjectUtils.isEmpty(giaCongDoan)) {
			lblLoiGiaCongDoan.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.giaCongDoan")));
			status = false;
		} else {
			try {
				double giaCongDoanDouble = PriceFormatterUtils.parse(giaCongDoan);
				if (giaCongDoanDouble < 0) {
					lblLoiGiaCongDoan.setText(String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.giaCongDoanSoThuc")));
					status = false;
				}

				SanPham sanPham = this.sanPhams.get(tblDanhSachSanPham.getSelectedRow());
				if (sanPham.getDonGia() <= giaCongDoanDouble) {
					lblLoiGiaCongDoan.setText(String.format(
							String.format("<html><p>%s</p></html>",
									SystemConstants.BUNDLE
											.getString("congDoanSanPham.thongBao.giaCongDoanNhoHonGiaSanPham")),
							PriceFormatterUtils.format(sanPham.getDonGia())));
					status = false;
				}

			} catch (Exception e) {
				lblLoiGiaCongDoan.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.giaCongDoanSoThuc")));
				status = false;
			}
		}

		if (thoiHan == null) {
			lblLoiThoiHan.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.thoiHan")));
			status = false;
		} else {
			if (!thoiHan.isAfter(LocalDate.now())) {
				lblLoiThoiHan.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("congDoanSanPham.thongBao.thoiHanSauHomNay")));
				status = false;
			}
		}

		return status;
	}

	private void thucHienThucLamLamMoiLoi() {
		lblLoiTenCongDoan.setText("");
		lblLoiSoLuongCanLam.setText("");
		lblLoiGiaCongDoan.setText("");
		lblLoiThoiHan.setText("");
		lblLoiThuTuLam.setText("");
	}
}
