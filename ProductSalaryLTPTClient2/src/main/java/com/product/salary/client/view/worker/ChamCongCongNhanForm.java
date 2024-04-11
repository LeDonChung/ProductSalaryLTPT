package com.product.salary.client.view.worker;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.CaLam;
import com.product.salary.application.entity.ChamCongCongNhan;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.PhanCongCongNhan;
import com.product.salary.application.service.ChamCongCongNhanService;
import com.product.salary.application.service.CongDoanSanPhamService;
import com.product.salary.application.service.CongNhanService;
import com.product.salary.application.service.PhanCongCongViecService;
import com.product.salary.application.service.impl.ChamCongCongNhanServiceImpl;
import com.product.salary.application.service.impl.CongDoanSanPhamServiceImpl;
import com.product.salary.application.service.impl.CongNhanServiceImpl;
import com.product.salary.application.service.impl.PhanCongCongViecServiceImpl;
import com.product.salary.application.utils.DateConvertUtils;
import com.toedter.calendar.JDateChooser;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChamCongCongNhanForm extends JPanel {

	private JTextField txtMaCongNhan;
	private JTextField txtHoTen;
	private JTextField txtSanPham;
	private JTextField txtCongDoan;
	private JTextField txtMaChamCong;
	private JTextField txtSoLuongHoanThanh;
	private DefaultTableModel tblModelChamCong;
	private DefaultTableModel tblModelCongNhan;
	private DefaultTableModel tblModelPhanCong;
	private JTable tblDanhSachChamCong;
	private JDateChooser jcNgayChamCong;
	private JComboBox cmbCaLam;
	private JButton btnLayDanhSach;
	private JTable tblDanhSachCongNhan;
	private JButton btnChamCong;
	private JButton btnCapNhat;
	private JButton btnLamMoi;
	private JComboBox cmbTrangThai;
	private JTable tblDanhSachPhanCong;
	private CongNhanService congNhanService;
	private PhanCongCongViecService phanCongService;
	private ChamCongCongNhanService chamCongService;
	private CongDoanSanPhamService congDoanService;
	private List<CongNhan> dsCongNhan;
	private List<PhanCongCongNhan> dsPhanCong;
	private List<ChamCongCongNhan> dsChamCong;
	private JLabel lblLoiSoLuongHoanThanh;
	private JTextField txtSoLuongCanLam;
	private JButton btnChamTatCa;

	/**
	 * Create the panel.
	 */
	public ChamCongCongNhanForm() {

		setLayout(null);

		JPanel pnlMain = new JPanel();
		pnlMain.setBounds(10, 10, 1250, 825);
		pnlMain.setLayout(null);
		add(pnlMain);

		JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("chamCongCongNhan.title"));
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setBounds(0, 0, 1250, 80);
		pnlMain.add(lblTitle);

		JPanel pnl1 = new JPanel();
		pnl1.setLayout(null);
		pnl1.setBounds(10, 81, 700, 70);
		pnlMain.add(pnl1);

		jcNgayChamCong = new JDateChooser();
		jcNgayChamCong.getCalendarButton().setIcon(
				new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
		jcNgayChamCong.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
		jcNgayChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcNgayChamCong.setDateFormatString("yyyy-MM-dd");
		jcNgayChamCong.setBounds(101, 10, 158, 50);
		jcNgayChamCong.setDate(new Date());
		pnl1.add(jcNgayChamCong);

		JLabel lblNgayCham = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chamCongCongNhan.lbNgayCham")));
		lblNgayCham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayCham.setBounds(10, 10, 81, 50);
		pnl1.add(lblNgayCham);

		JLabel lblCaLam = new JLabel(SystemConstants.BUNDLE.getString("chamCongCongNhan.lbCaLam"));
		lblCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCaLam.setBounds(300, 10, 56, 50);
		pnl1.add(lblCaLam);

		cmbCaLam = new JComboBox();
		cmbCaLam.setModel(new DefaultComboBoxModel(new String[] { "Sáng", "Chiều", "Tối" }));
		cmbCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbCaLam.setBounds(377, 10, 121, 50);
		pnl1.add(cmbCaLam);

		btnLayDanhSach = new JButton(SystemConstants.BUNDLE.getString("chamCongCongNhan.btnLayDanhSach"));
		btnLayDanhSach
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_search.png"));
		btnLayDanhSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLayDanhSach.setBounds(532, 10, 158, 50);
		pnl1.add(btnLayDanhSach);

		JPanel pnl2 = new JPanel();
		pnl2.setLayout(null);
		pnl2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnl2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl2.setBounds(720, 104, 520, 437);
		pnlMain.add(pnl2);

		JPanel pnlMaCongNhan = new JPanel();
		pnlMaCongNhan.setLayout(null);
		pnlMaCongNhan.setBounds(10, 70, 500, 50);
		pnl2.add(pnlMaCongNhan);

		JLabel lblMaCongNhan = new JLabel(SystemConstants.BUNDLE.getString("chamCongCongNhan.lbMaCongNhan"));
		lblMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaCongNhan.setBounds(0, 0, 95, 50);
		pnlMaCongNhan.add(lblMaCongNhan);

		txtMaCongNhan = new JTextField();
		txtMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaCongNhan.setEditable(false);
		txtMaCongNhan.setColumns(10);
		txtMaCongNhan.setBounds(105, 0, 385, 50);
		pnlMaCongNhan.add(txtMaCongNhan);

		JPanel pnlHoTen = new JPanel();
		pnlHoTen.setLayout(null);
		pnlHoTen.setBounds(10, 130, 500, 50);
		pnl2.add(pnlHoTen);

		JLabel lblHoTen = new JLabel(SystemConstants.BUNDLE.getString("chamCongNhanVien.lbHoTen"));
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHoTen.setBounds(0, 0, 95, 50);
		pnlHoTen.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtHoTen.setEditable(false);
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(105, 0, 385, 50);
		pnlHoTen.add(txtHoTen);

		JPanel pnlSanPham = new JPanel();
		pnlSanPham.setLayout(null);
		pnlSanPham.setBounds(10, 190, 500, 50);
		pnl2.add(pnlSanPham);

		JLabel lblSanPham = new JLabel(SystemConstants.BUNDLE.getString("chamCongCongNhan.lbSanPham"));
		lblSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSanPham.setBounds(0, 0, 95, 50);
		pnlSanPham.add(lblSanPham);

		txtSanPham = new JTextField();
		txtSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSanPham.setEditable(false);
		txtSanPham.setColumns(10);
		txtSanPham.setBounds(105, 0, 385, 50);
		pnlSanPham.add(txtSanPham);

		JPanel pnlCongDoan = new JPanel();
		pnlCongDoan.setLayout(null);
		pnlCongDoan.setBounds(10, 250, 500, 50);
		pnl2.add(pnlCongDoan);

		JLabel lblCongDoan = new JLabel(SystemConstants.BUNDLE.getString("chamCongCongNhan.lbCongDoan"));
		lblCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCongDoan.setBounds(0, 0, 95, 50);
		pnlCongDoan.add(lblCongDoan);

		txtCongDoan = new JTextField();
		txtCongDoan.setEditable(false);
		txtCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCongDoan.setColumns(10);
		txtCongDoan.setBounds(105, 0, 148, 50);
		pnlCongDoan.add(txtCongDoan);

		JLabel lblSoLuongCanLam = new JLabel(SystemConstants.BUNDLE.getString("chamCongCongNhan.lbSoLuongCanLam"));
		lblSoLuongCanLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongCanLam.setBounds(263, 0, 122, 50);
		pnlCongDoan.add(lblSoLuongCanLam);

		txtSoLuongCanLam = new JTextField();
		txtSoLuongCanLam.setEditable(false);
		txtSoLuongCanLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoLuongCanLam.setColumns(10);
		txtSoLuongCanLam.setBounds(395, 0, 95, 50);
		pnlCongDoan.add(txtSoLuongCanLam);

		JPanel pnlSoLuongHoanThanh = new JPanel();
		pnlSoLuongHoanThanh.setLayout(null);
		pnlSoLuongHoanThanh.setBounds(10, 310, 500, 50);
		pnl2.add(pnlSoLuongHoanThanh);

		JLabel lblSoLuongHoanThanh = new JLabel(SystemConstants.BUNDLE.getString("chamCongCongNhan.lbSoLuong"));
		lblSoLuongHoanThanh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongHoanThanh.setBounds(0, 0, 134, 35);
		pnlSoLuongHoanThanh.add(lblSoLuongHoanThanh);

		txtSoLuongHoanThanh = new JTextField();
		txtSoLuongHoanThanh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoLuongHoanThanh.setColumns(10);
		txtSoLuongHoanThanh.setBounds(144, 0, 346, 35);
		pnlSoLuongHoanThanh.add(txtSoLuongHoanThanh);

		lblLoiSoLuongHoanThanh = new JLabel("");
		lblLoiSoLuongHoanThanh.setForeground(Color.RED);
		lblLoiSoLuongHoanThanh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiSoLuongHoanThanh.setBounds(144, 37, 346, 13);
		pnlSoLuongHoanThanh.add(lblLoiSoLuongHoanThanh);

		JPanel pnlMaChamCong = new JPanel();
		pnlMaChamCong.setLayout(null);
		pnlMaChamCong.setBounds(10, 10, 500, 50);
		pnl2.add(pnlMaChamCong);

		JLabel lblMaChamCong = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chamCongCongNhan.lbMaChamCong")));
		lblMaChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaChamCong.setBounds(0, 0, 95, 50);
		pnlMaChamCong.add(lblMaChamCong);

		txtMaChamCong = new JTextField();
		txtMaChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaChamCong.setEditable(false);
		txtMaChamCong.setColumns(10);
		txtMaChamCong.setBounds(105, 0, 385, 50);
		pnlMaChamCong.add(txtMaChamCong);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(null);
		pnlTrangThai.setBounds(10, 370, 500, 50);
		pnl2.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel(SystemConstants.BUNDLE.getString("chamCongCongNhan.lbTrangThai"));
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrangThai.setBounds(0, 0, 95, 50);
		pnlTrangThai.add(lblTrangThai);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Có mặt", "Đi trễ", "Nghỉ" }));
		cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTrangThai.setBounds(106, 0, 384, 50);
		pnlTrangThai.add(cmbTrangThai);

		JLabel lblThongTinChamCong = new JLabel(
				SystemConstants.BUNDLE.getString("chamCongCongNhan.lbThongTinChamCong"));
		lblThongTinChamCong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblThongTinChamCong.setBounds(720, 81, 522, 22);
		pnlMain.add(lblThongTinChamCong);

		JPanel pnl3 = new JPanel();
		pnl3.setLayout(null);
		pnl3.setBounds(10, 625, 1230, 190);
		pnlMain.add(pnl3);

		tblModelChamCong = new DefaultTableModel(
				new String[] { SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.stt"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.maChamCong"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.maCongNhan"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.hoTen"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.gioiTinh"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.soDienThoai"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.maSanPham"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.tenSanPham"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.maCongDoan"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.tenCongDoan"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.ngayCham"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.caLam"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.soLuongHoanThanh"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.trangThai") },
				0);
		tblDanhSachChamCong = new JTable(tblModelChamCong);
		tblDanhSachChamCong.setShowVerticalLines(true);
		tblDanhSachChamCong.setShowHorizontalLines(true);
		tblDanhSachChamCong.setRowHeight(25);
		tblDanhSachChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tblDanhSachChamCong.setBounds(0, 0, 1211, 325);
		JScrollPane scrDanhSachChamCong = new JScrollPane(tblDanhSachChamCong);
		scrDanhSachChamCong.setLocation(0, 0);
		scrDanhSachChamCong.setSize(1230, 232);
		pnl3.add(scrDanhSachChamCong);

		JLabel lblDanhSachChamCong = new JLabel(
				SystemConstants.BUNDLE.getString("chamCongCongNhan.lbDanhSachChamCong"));
		lblDanhSachChamCong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDanhSachChamCong.setBounds(10, 593, 522, 22);
		pnlMain.add(lblDanhSachChamCong);

		JPanel pnl4 = new JPanel();
		pnl4.setLayout(null);
		pnl4.setBounds(10, 368, 700, 173);
		pnlMain.add(pnl4);

		tblModelPhanCong = new DefaultTableModel(
				new String[] { SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachPhanCong.stt"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachPhanCong.maPhanCong"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachPhanCong.maCongDoan"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachPhanCong.tenCongDoan"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachPhanCong.trangThai"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachPhanCong.soLuongHoanThanh") },
				0);
		tblDanhSachPhanCong = new JTable(tblModelPhanCong) {
			public Class<?> getColumnClass(int column) {
				if (column == 6) {
					return Boolean.class;
				}
				return super.getColumnClass(column);
			}
		};

		tblDanhSachPhanCong.setShowVerticalLines(true);
		tblDanhSachPhanCong.setShowHorizontalLines(true);
		tblDanhSachPhanCong.setRowHeight(25);
		tblDanhSachPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		JScrollPane scrDanhSachPhanCong = new JScrollPane(tblDanhSachPhanCong);
		scrDanhSachPhanCong.setBounds(0, 0, 700, 173);
		pnl4.add(scrDanhSachPhanCong);

		JLabel lblDanhSachPhanCong = new JLabel(
				SystemConstants.BUNDLE.getString("chamCongCongNhan.lbDanhSachPhanCong"));
		lblDanhSachPhanCong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDanhSachPhanCong.setBounds(10, 344, 522, 22);
		pnlMain.add(lblDanhSachPhanCong);

		JPanel pnl5 = new JPanel();
		pnl5.setLayout(null);
		pnl5.setBounds(10, 185, 700, 155);
		pnlMain.add(pnl5);

		tblModelCongNhan = new DefaultTableModel(
				new String[] { SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachCongNhan.stt"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachCongNhan.maCongNhan"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachCongNhan.hoTen"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachCongNhan.gioiTinh"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachCongNhan.soDienThoai") },
				0);
		tblDanhSachCongNhan = new JTable(tblModelCongNhan);
		tblDanhSachCongNhan.setShowVerticalLines(true);
		tblDanhSachCongNhan.setShowHorizontalLines(true);
		tblDanhSachCongNhan.setRowHeight(25);

		JScrollPane scrDanhSachCongNhan = new JScrollPane(tblDanhSachCongNhan);
		scrDanhSachCongNhan.setBounds(0, 0, 700, 151);
		pnl5.add(scrDanhSachCongNhan);

		JLabel lblDanhSachCongNhan = new JLabel(
				SystemConstants.BUNDLE.getString("chamCongCongNhan.lbDanhSachCongNhan"));
		lblDanhSachCongNhan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDanhSachCongNhan.setBounds(10, 161, 522, 22);
		pnlMain.add(lblDanhSachCongNhan);

		JPanel pnl6 = new JPanel();
		pnl6.setLayout(null);
		pnl6.setBounds(574, 551, 669, 64);
		pnlMain.add(pnl6);

		btnChamCong = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chamCongCongNhan.btnChamCong")));
		btnChamCong
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_check.png"));
		btnChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnChamCong.setBounds(224, 7, 140, 50);
		pnl6.add(btnChamCong);

		btnCapNhat = new JButton(SystemConstants.BUNDLE.getString("chamCongCongNhan.btnCapNhat"));
		btnCapNhat
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCapNhat.setBounds(374, 7, 135, 50);
		pnl6.add(btnCapNhat);

		btnLamMoi = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chamCongCongNhan.btnLamMoi")));
		btnLamMoi
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLamMoi.setBounds(519, 7, 140, 50);
		pnl6.add(btnLamMoi);

		btnChamTatCa = new JButton("Chấm công nhiều");
		btnChamTatCa
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_check.png"));
		btnChamTatCa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnChamTatCa.setBounds(10, 7, 204, 50);
		pnl6.add(btnChamTatCa);

		init();
		event();
	}

	private void init() {
		this.chamCongService = new ChamCongCongNhanServiceImpl();
		this.congNhanService = new CongNhanServiceImpl();
		this.phanCongService = new PhanCongCongViecServiceImpl();
		this.congDoanService = new CongDoanSanPhamServiceImpl();

		this.dsChamCong = new ArrayList<ChamCongCongNhan>();
		this.dsCongNhan = new ArrayList<CongNhan>();
		this.dsPhanCong = new ArrayList<PhanCongCongNhan>();

	}

	private void event() {
		this.btnChamTatCa.addActionListener((e) -> {
			thucHienChamCongNhieuCongDoan();
		});
		this.btnLamMoi.addActionListener((e) -> {
			thucHienChucNangLamMoi();
		});
		this.btnLayDanhSach.addActionListener((e) -> {
			thucHienChucNangLayDanhSachChuaChamCong();
		});
		this.btnChamCong.addActionListener((e) -> {
			thucHienChucNangChamCongCongNhan();
		});
		this.btnCapNhat.addActionListener((e) -> {
			thucHienChucNangCapNhatCongNhan();
		});
		tblDanhSachCongNhan.addMouseListener(new MouseListener() {

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
				thucHienChucNangChonCongNhan();

			}
		});
		tblDanhSachPhanCong.addMouseListener(new MouseListener() {

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
				thucHienChucNangChonPhanCong();

			}
		});
		tblDanhSachChamCong.addMouseListener(new MouseListener() {

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
				thucHienChucNangClickChamCong();
			}
		});
	}

	private void thucHienChucNangCapNhatCongNhan() {
		int index = tblDanhSachChamCong.getSelectedRow();
		if (index >= 0) {

			ChamCongCongNhan chamCong = dsChamCong.get(index);
			int trangThai = cmbTrangThai.getSelectedItem().equals("Có mặt") ? 1
					: cmbTrangThai.getSelectedItem().equals("Nghỉ") ? 0 : 2;
			int soLuongHoanThanh = Integer.parseInt(txtSoLuongHoanThanh.getText().trim());
			boolean status = chamCongService.capNhatChamCongCongNhan(chamCong.getMaChamCong(), trangThai,
					soLuongHoanThanh);
			if (status) {
				LocalDate ngayChamCong = DateConvertUtils.asLocalDate(jcNgayChamCong.getDate(), ZoneId.systemDefault());
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("chamCongCongNhan.capNhatThanhCong"));
				// JOptionPane.showMessageDialog(this, "Cập nhật chấm công thành công!");
				loadTableChamCong(ngayChamCong);
				thucHienChucNangLamMoi();
				return;
			} else {
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("chamCongCongNhan.capNhatKhongThanhCong"));
				// JOptionPane.showMessageDialog(this, "Cập nhật chấm công không thành công!");
				thucHienChucNangLamMoi();
				return;
			}
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("chamCongCongNhan.chonChamCong"));
			// JOptionPane.showMessageDialog(this, "Vui lòng chọn chấm công cần cập nhật!");
			return;
		}
	}

	private boolean thucHienChucNangKiemTra() {
		String soLuongHoanThanh = txtSoLuongHoanThanh.getText().trim();
		String soLuongCanLam = txtSoLuongCanLam.getText();
		boolean status = true;
		if (ObjectUtils.isEmpty(txtSoLuongHoanThanh.getText())) {
			lblLoiSoLuongHoanThanh
					.setText(SystemConstants.BUNDLE.getString("chamCongCongNhan.loiSoLuongHoanThanhRong"));
			status = false;
		} else if (Integer.parseInt(soLuongHoanThanh) > Integer.parseInt(soLuongCanLam)) {
			lblLoiSoLuongHoanThanh.setText(SystemConstants.BUNDLE.getString("chamCongCongNhan.loiSoLuongHoanThanh"));
			status = false;
		}
		return status;
	}

	private void thucHienChucNangLamMoiLoi() {
		lblLoiSoLuongHoanThanh.setText("");
	}

	private void thucHienChamCongNhieuCongDoan() {
		int chonCongNhan = tblDanhSachCongNhan.getSelectedRow();
		int chonPhanCong = tblDanhSachPhanCong.getSelectedRow();
		if (chonCongNhan >= 0) {
			if (chonPhanCong >= 0) {
				// CongNhan congNhan = dsCongNhan.get(chonCongNhan);
				for (int i = 0; i < tblModelPhanCong.getRowCount(); i++) {
					Object soLuongHoanThanh = tblModelPhanCong.getValueAt(i, 5);
					if (soLuongHoanThanh != null) {
						String maCongDoan = (String) tblModelPhanCong.getValueAt(i, 2);
						int soLuong = Integer.valueOf(soLuongHoanThanh.toString());
						LocalDate ngayChamCong = DateConvertUtils.asLocalDate(jcNgayChamCong.getDate(),
								ZoneId.systemDefault());
						String tenCa = (String) cmbCaLam.getSelectedItem();
						String maCa = tenCa.equals("Sáng") ? "SA" : tenCa.equals("Chiều") ? "CH" : "TO";
						CaLam caLam = new CaLam(maCa, tenCa);
						String maChamCong = chamCongService.genertateMaChamCongCongNhan(ngayChamCong, caLam);

						PhanCongCongNhan phanCong = dsPhanCong.get(i);
						try {
							ChamCongCongNhan chamCong = new ChamCongCongNhan(maChamCong, phanCong, soLuong, caLam,
									ngayChamCong, 1, null);
							chamCong = chamCongService.themChamCongCongNhan(chamCong);
							if (chamCong != null) {
								congDoanService.capNhatSoLuongCanCuaCongDoan(maCongDoan, soLuong);
								thucHienChucNangLayDanhSachChuaChamCong();
								// loadTablePhanCong(maCongDoan);
								thucHienChucNangLamMoi();
								loadTableChamCong(ngayChamCong);
							} else {
								JOptionPane.showMessageDialog(this,
										SystemConstants.BUNDLE.getString("chamCongCongNhan.chamCongKhongThanhCong"));
								return;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				tblModelPhanCong.setRowCount(0);
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("chamCongCongNhan.chamCongThanhCong"));
			} else {
				JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("chamCongCongNhan.chonPhanCong"));
				// JOptionPane.showMessageDialog(this, "Vui lòng chọn phân công cần chấm công");
				return;
			}

		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("chamCongCongNhan.chonCongNhan"));
			return;
		}

	}

	private void thucHienChucNangChamCongCongNhan() {
		int chonCongNhan = tblDanhSachCongNhan.getSelectedRow();
		int chonPhanCong = tblDanhSachPhanCong.getSelectedRow();
		if (chonCongNhan >= 0) {
			if (chonPhanCong >= 0) {
				CongNhan congNhan = dsCongNhan.get(chonCongNhan);
				PhanCongCongNhan phanCong = dsPhanCong.get(chonPhanCong);
				LocalDate ngayChamCong = DateConvertUtils.asLocalDate(jcNgayChamCong.getDate(), ZoneId.systemDefault());
				String tenCa = (String) cmbCaLam.getSelectedItem();
				String maCa = tenCa.equals("Sáng") ? "SA" : tenCa.equals("Chiều") ? "CH" : "TO";
				CaLam caLam = new CaLam(maCa, tenCa);

				int trangThaiDiLam = cmbTrangThai.getSelectedItem().equals("Nghỉ") ? 0
						: cmbTrangThai.getSelectedItem().equals("Đi trễ") ? 2 : 1;

				String maChamCong = chamCongService.genertateMaChamCongCongNhan(ngayChamCong, caLam);

				String maCongDoan = phanCong.getCongDoanSanPham().getMaCongDoan();

				try {
					if (!thucHienChucNangKiemTra()) {
						return;
					}
					int soLuongHoanThanh = Integer.parseInt(txtSoLuongHoanThanh.getText().trim());
					ChamCongCongNhan chamCong = new ChamCongCongNhan(maChamCong, phanCong, soLuongHoanThanh, caLam,
							ngayChamCong, trangThaiDiLam, null);
					chamCong = chamCongService.themChamCongCongNhan(chamCong);
					if (chamCong != null) {
						congDoanService.capNhatSoLuongCanCuaCongDoan(maCongDoan, soLuongHoanThanh);
						JOptionPane.showMessageDialog(this,
								SystemConstants.BUNDLE.getString("chamCongCongNhan.chamCongThanhCong"));
						thucHienChucNangLamMoi();

						jcNgayChamCong.setDate(DateConvertUtils.asUtilDate(ngayChamCong, ZoneId.systemDefault()));
						cmbCaLam.setSelectedItem(tenCa);
						thucHienChucNangLayDanhSachChuaChamCong();
						// loadTablePhanCong(congNhan.getMaCongNhan());
						tblModelPhanCong.setRowCount(0);
						loadTableChamCong(ngayChamCong);
						return;
					} else {
						JOptionPane.showMessageDialog(this,
								SystemConstants.BUNDLE.getString("chamCongCongNhan.chamCongKhongThanhCong"));
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("chamCongCongNhan.chonPhanCong"));
				// JOptionPane.showMessageDialog(this, "Vui lòng chọn phân công cần chấm công");
				return;
			}
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("chamCongCongNhan.chonCongNhan"));
			// JOptionPane.showMessageDialog(this, "Vui lòng chọn công nhân cần chấm
			// công!");
			return;
		}
	}

	private void thucHienChucNangChonPhanCong() {
		int index = tblDanhSachPhanCong.getSelectedRow();
		if (index >= 0) {
			PhanCongCongNhan phanCong = dsPhanCong.get(index);
			txtSanPham.setText(phanCong.getCongDoanSanPham().getSanPham().getTenSanPham());
			txtCongDoan.setText(phanCong.getCongDoanSanPham().getTenCongDoan());
			txtSoLuongCanLam.setText(String.valueOf(phanCong.getCongDoanSanPham().getSoLuongCanLam()));
		}
	}

	private void thucHienChucNangChonCongNhan() {
		int index = tblDanhSachCongNhan.getSelectedRow();
		if (index >= 0) {
			CongNhan congNhan = dsCongNhan.get(index);
			txtMaCongNhan.setText(congNhan.getMaCongNhan());
			txtHoTen.setText(congNhan.getHoTen());
			loadTablePhanCong(congNhan.getMaCongNhan());
		} else {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("chamCongCongNhan.chonCongNhanXemPhanCong"));
			// JOptionPane.showMessageDialog(this, "Vui lòng chọn công nhân cần xem phân
			// công!");
		}
	}

	private void loadTablePhanCong(String maCongNhan) {
		tblModelPhanCong.setRowCount(0);
		this.dsPhanCong = this.phanCongService.timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(maCongNhan);

		int stt = 1;

		for (PhanCongCongNhan phanCong : dsPhanCong) {
			String trangThai = "Chưa hoàn thành";
			tblModelPhanCong.addRow(
					new Object[] { stt++, phanCong.getMaPhanCong(), phanCong.getCongDoanSanPham().getMaCongDoan(),
							phanCong.getCongDoanSanPham().getTenCongDoan(), trangThai });
		}
	}

	private void thucHienChucNangLayDanhSachChuaChamCong() {
		tblModelCongNhan.setRowCount(0);
		if (jcNgayChamCong.getDate() == null) {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("chamCongCongNhan.chonNgayChamCong"));
			// JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày chấm công!");
			return;
		}
		LocalDate ngayChamCong = DateConvertUtils.asLocalDate(jcNgayChamCong.getDate(), ZoneId.systemDefault());

		if (ngayChamCong.isAfter(LocalDate.now())) {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("chamCongCongNhan.chonNgayChamCongTruoc"));
			// JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày chấm công trước hoặc
			// là ngày hiện tại!");
			return;
		}

		String maCa = cmbCaLam.getSelectedItem().equals("Sáng") ? "SA"
				: cmbCaLam.getSelectedItem().equals("Chiều") ? "CH" : "TO";

		this.dsCongNhan = this.chamCongService.timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(ngayChamCong, maCa);
		int stt = 1;

		for (CongNhan congNhan : this.dsCongNhan) {
			String gioiTinh = congNhan.getGioiTinh() == 1 ? "Nam" : "Nữ";
			tblModelCongNhan.addRow(new Object[] { stt++, congNhan.getMaCongNhan(), congNhan.getHoTen(), gioiTinh,
					congNhan.getSoDienThoai() });
		}
		loadTableChamCong(ngayChamCong);
	}

	private void thucHienChucNangLamMoi() {
		thucHienChucNangLamMoiLoi();
		txtMaChamCong.setText("");
		txtMaCongNhan.setText("");
		txtHoTen.setText("");
		txtSanPham.setText("");
		txtCongDoan.setText("");
		txtSoLuongHoanThanh.setText("");
		cmbCaLam.setSelectedItem(0);
		// jcNgayChamCong.setDate(null);
		cmbTrangThai.setSelectedIndex(0);
		tblDanhSachChamCong.clearSelection();
		tblDanhSachCongNhan.clearSelection();
		tblDanhSachPhanCong.clearSelection();
		txtSoLuongCanLam.setText("");
//		tblModelCongNhan.setRowCount(0);
//		tblModelPhanCong.setRowCount(0);
	}

	private void thucHienChucNangClickChamCong() {
		int index = tblDanhSachChamCong.getSelectedRow();
		if (index >= 0) {
			ChamCongCongNhan chamCong = dsChamCong.get(index);

			txtMaChamCong.setText(chamCong.getMaChamCong());
			txtMaCongNhan.setText(chamCong.getPhanCongCongNhan().getCongNhan().getMaCongNhan());
			txtHoTen.setText(chamCong.getPhanCongCongNhan().getCongNhan().getHoTen());
			txtSanPham.setText(chamCong.getPhanCongCongNhan().getCongDoanSanPham().getSanPham().getTenSanPham());
			txtCongDoan.setText(chamCong.getPhanCongCongNhan().getCongDoanSanPham().getMaCongDoan());
			txtSoLuongHoanThanh.setText(String.valueOf(chamCong.getSoLuongHoanThanh()));
			String caLam = chamCong.getCaLam().getMaCa().equals("SA") ? "Sáng"
					: chamCong.getCaLam().getMaCa().equals("CH") ? "Chiều" : "Tối";
			cmbCaLam.setSelectedItem(caLam);
			String trangThaiDiLam = chamCong.getTrangThai() == 0 ? "Nghỉ"
					: chamCong.getTrangThai() == 1 ? "Có mặt" : "Đi trễ";
			cmbTrangThai.setSelectedItem(trangThaiDiLam);
			jcNgayChamCong.setDate(DateConvertUtils.asUtilDate(chamCong.getNgayChamCong(), ZoneId.systemDefault()));
		}
	}

	private void loadTableChamCong(LocalDate ngayChamCong) {
		tblModelChamCong.setRowCount(0);
		dsChamCong = chamCongService.timKiemTatCaChamCongCongNhan(ngayChamCong);
		int stt = 1;
		for (ChamCongCongNhan chamCongCongNhan : dsChamCong) {
			String trangThai = "";
			if (chamCongCongNhan.getTrangThai() == 1) {
				trangThai = "Có mặt";
			} else if (chamCongCongNhan.getTrangThai() == 0) {
				trangThai = "Nghỉ";
			} else {
				trangThai = "Đi trễ";
			}
			tblModelChamCong.addRow(new Object[] { stt++, chamCongCongNhan.getMaChamCong(),
					chamCongCongNhan.getPhanCongCongNhan().getCongNhan().getMaCongNhan(),
					chamCongCongNhan.getPhanCongCongNhan().getCongNhan().getHoTen(),
					chamCongCongNhan.getPhanCongCongNhan().getCongNhan().getGioiTinh(),
					chamCongCongNhan.getPhanCongCongNhan().getCongNhan().getSoDienThoai(),
					chamCongCongNhan.getPhanCongCongNhan().getCongDoanSanPham().getSanPham().getMaSanPham(),
					chamCongCongNhan.getPhanCongCongNhan().getCongDoanSanPham().getSanPham().getTenSanPham(),
					chamCongCongNhan.getPhanCongCongNhan().getCongDoanSanPham().getMaCongDoan(),
					chamCongCongNhan.getPhanCongCongNhan().getCongDoanSanPham().getTenCongDoan(),
					chamCongCongNhan.getNgayChamCong(), chamCongCongNhan.getCaLam().getTenCa(),
					chamCongCongNhan.getSoLuongHoanThanh(), trangThai });
		}
		thucHienChucNangLamMoi();
	}
}