package com.product.salary.client.view.worker;

import com.product.salary.client.common.SystemConstants;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.utils.*;
import com.product.salary.application.utils.excels.CongNhanExcelUtils;
import com.toedter.calendar.JDateChooser;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

public class TimKiemCongNhanForm extends JPanel {
	private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");

	private final JTextField txtMaCN;
	private final JTextField txtHoTen;
	private final JTextField txtCCCD;
	private final JTextField txtTroCap;
	private final JTextField txtDienThoai;
	private final JTextField txtEmail;
	private final JTextField txtDiaChi;
	private final JLabel lblHinhAnh;
	private final JButton btnTimKiem;
	private final DefaultTableModel tableModelCongNhan;
	private final JTable tblCongNhan;
	private final JDateChooser jcNgaySinh;
	private final JDateChooser jcNgayVaoLam;
	private List<CongNhan> dsCongNhan;
	private List<TayNghe> dsTayNghe;
	private List<ToNhom> dsToNhom;
	private final DefaultComboBoxModel<ToNhom> cbbModelToNhom;
	private final DefaultComboBoxModel<TayNghe> cbbModelTayNghe;
	private final JRadioButton radNam;
	private final JRadioButton radNu;
	private final JComboBox cmbToNhom;
	private final JComboBox cmbTrangThai;
	private final JComboBox cmbTayNghe;
	private final JLabel lblLoiTroCap;
	private final JLabel lblLoiNgayVaoLam;
	private final JLabel lblLoiNgaySinh;
	private final JLabel lblLoiDiaChi;
	private final JLabel lblLoiEmail;
	private final JLabel lblLoiSoDienThoai;
	private final JLabel lblLoiCCCD;
	private final JLabel lblLoiHoTen;
	private final JButton btnLamMoi;
	private final JRadioButton radTatCa;
	private final JButton btnExcel;

	/**
	 * Create the panel.
	 */
	public TimKiemCongNhanForm() {

		setLayout(null);

		JPanel pnlChinh = new JPanel();
		pnlChinh.setBounds(0, 14, 1273, 821);
		add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTitle = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.timKiemCongNhan")));
		lblTitle.setBounds(0, 0, 1250, 80);
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlChinh.add(lblTitle);

		tableModelCongNhan = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.stt")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.maCongNhan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.hoTen")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.CCCD")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.ngaySinh")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.diaChi")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.dienThoai")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.email")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.gioiTinh")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.phongBan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.ngayVaoLam")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.troCap")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.tayNghe")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.trangThai")) },
				10);

		tblCongNhan = new JTable(tableModelCongNhan);
		tblCongNhan.setShowVerticalLines(true);
		tblCongNhan.setShowHorizontalLines(true);
		tblCongNhan.setRowHeight(25);

		// Căn phải giá tiền
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblCongNhan.getColumnModel().getColumn(11).setCellRenderer(rightRenderer);

		JScrollPane scrCongNhan = new JScrollPane(tblCongNhan);
		scrCongNhan.setSize(1253, 256);
		scrCongNhan.setLocation(10, 565);
		tblCongNhan.setBounds(0, 570, 1273, 263);
		pnlChinh.add(scrCongNhan);

		JPanel pnlMaCN = new JPanel();
		pnlMaCN.setLayout(null);
		pnlMaCN.setBounds(20, 306, 391, 62);
		pnlChinh.add(pnlMaCN);

		JLabel lblMaCN = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.lbMaCongNhan")));
		lblMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaCN.setBounds(10, 0, 96, 40);
		pnlMaCN.add(lblMaCN);

		txtMaCN = new JTextField();
		txtMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaCN.setColumns(10);
		txtMaCN.setBounds(134, 0, 247, 40);
		pnlMaCN.add(txtMaCN);

		JPanel pnlHoTen = new JPanel();
		pnlHoTen.setLayout(null);
		pnlHoTen.setBounds(469, 90, 384, 62);
		pnlChinh.add(pnlHoTen);

		JLabel lblHoTen = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lbHoTen")));
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHoTen.setBounds(10, 0, 96, 40);
		pnlHoTen.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(127, 0, 247, 40);
		pnlHoTen.add(txtHoTen);

		lblLoiHoTen = new JLabel("");
		lblLoiHoTen.setForeground(Color.RED);
		lblLoiHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiHoTen.setBounds(127, 39, 247, 23);
		pnlHoTen.add(lblLoiHoTen);

		JPanel pnlCCCD = new JPanel();
		pnlCCCD.setLayout(null);
		pnlCCCD.setBounds(879, 90, 384, 62);
		pnlChinh.add(pnlCCCD);

		JLabel lblCCCD = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lbCCCD")));
		lblCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCCCD.setBounds(10, 0, 96, 40);
		pnlCCCD.add(lblCCCD);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(127, 0, 247, 40);
		pnlCCCD.add(txtCCCD);

		lblLoiCCCD = new JLabel("");
		lblLoiCCCD.setForeground(Color.RED);
		lblLoiCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiCCCD.setBounds(127, 39, 247, 23);
		pnlCCCD.add(lblLoiCCCD);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(null);
		pnlTrangThai.setBounds(469, 378, 384, 62);
		pnlChinh.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.lbTrangThai")));
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrangThai.setBounds(10, 0, 96, 40);
		pnlTrangThai.add(lblTrangThai);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {
				SystemConstants.BUNDLE.getString("quanLyCongNhan.timTatCa"), "Đang làm", "Đã nghỉ làm" }));
		cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTrangThai.setBounds(128, 0, 246, 40);
		pnlTrangThai.add(cmbTrangThai);

		JPanel pnlDienThoai = new JPanel();
		pnlDienThoai.setLayout(null);
		pnlDienThoai.setBounds(469, 162, 384, 62);
		pnlChinh.add(pnlDienThoai);

		JLabel lblDienThoai = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.lbDienThoai")));
		lblDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDienThoai.setBounds(10, 0, 96, 40);
		pnlDienThoai.add(lblDienThoai);

		txtDienThoai = new JTextField();
		txtDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(127, 0, 247, 40);
		pnlDienThoai.add(txtDienThoai);

		lblLoiSoDienThoai = new JLabel("");
		lblLoiSoDienThoai.setForeground(Color.RED);
		lblLoiSoDienThoai.setBounds(127, 39, 247, 23);
		pnlDienThoai.add(lblLoiSoDienThoai);
		lblLoiSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 12));

		JPanel pnlToNhom = new JPanel();
		pnlToNhom.setLayout(null);
		pnlToNhom.setBounds(20, 378, 391, 62);
		pnlChinh.add(pnlToNhom);

		JLabel lblToNhom = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lblToNhom")));
		lblToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblToNhom.setBounds(10, 0, 96, 40);
		pnlToNhom.add(lblToNhom);

		cbbModelToNhom = new DefaultComboBoxModel<ToNhom>();
		cmbToNhom = new JComboBox(cbbModelToNhom);
		cmbToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbToNhom.setBounds(137, 0, 237, 40);
		pnlToNhom.add(cmbToNhom);

		JPanel pnlEmail = new JPanel();
		pnlEmail.setLayout(null);
		pnlEmail.setBounds(879, 162, 384, 62);
		pnlChinh.add(pnlEmail);

		JLabel lblEmail = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lbEmail")));
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEmail.setBounds(10, 0, 96, 40);
		pnlEmail.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(127, 0, 247, 40);
		pnlEmail.add(txtEmail);

		lblLoiEmail = new JLabel("");
		lblLoiEmail.setForeground(Color.RED);
		lblLoiEmail.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiEmail.setBounds(127, 39, 247, 23);
		pnlEmail.add(lblLoiEmail);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setLayout(null);
		pnlGioiTinh.setBounds(879, 234, 384, 62);
		pnlChinh.add(pnlGioiTinh);

		JLabel lblGioiTinh = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lbGioiTinh")));
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGioiTinh.setBounds(10, 0, 96, 40);
		pnlGioiTinh.add(lblGioiTinh);

		radNam = new JRadioButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.rbtnNam")));
		radNam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radNam.setBounds(127, 1, 70, 39);
		pnlGioiTinh.add(radNam);

		radNu = new JRadioButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.rbtnNu")));
		radNu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radNu.setBounds(211, 1, 70, 39);
		pnlGioiTinh.add(radNu);

		ButtonGroup btnGioiTinh = new ButtonGroup();
		btnGioiTinh.add(radNu);
		btnGioiTinh.add(radNam);

		radTatCa = new JRadioButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.radTatCa")));
		radTatCa.setSelected(true);
		radTatCa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radTatCa.setBounds(297, 1, 70, 39);
		pnlGioiTinh.add(radTatCa);
		btnGioiTinh.add(radTatCa);

		JPanel pnlDiaChi = new JPanel();
		pnlDiaChi.setLayout(null);
		pnlDiaChi.setBounds(469, 234, 384, 62);
		pnlChinh.add(pnlDiaChi);

		JLabel lblDiaChi = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lbDiaChi")));
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDiaChi.setBounds(10, 0, 96, 40);
		pnlDiaChi.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(127, 0, 247, 40);
		pnlDiaChi.add(txtDiaChi);

		lblLoiDiaChi = new JLabel("");
		lblLoiDiaChi.setForeground(Color.RED);
		lblLoiDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiDiaChi.setBounds(127, 39, 247, 23);
		pnlDiaChi.add(lblLoiDiaChi);

		JPanel pnlTayNghe = new JPanel();
		pnlTayNghe.setLayout(null);
		pnlTayNghe.setBounds(879, 306, 384, 62);
		pnlChinh.add(pnlTayNghe);

		JLabel lblTayNghe = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lbTayNghe")));
		lblTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTayNghe.setBounds(10, 0, 107, 40);
		pnlTayNghe.add(lblTayNghe);

		cbbModelTayNghe = new DefaultComboBoxModel<TayNghe>();
		cmbTayNghe = new JComboBox(cbbModelTayNghe);
		cmbTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTayNghe.setBounds(127, 0, 246, 40);
		pnlTayNghe.add(cmbTayNghe);

		btnTimKiem = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.btnTimKiem")));
		btnTimKiem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_search.png"));
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTimKiem.setBounds(20, 450, 184, 62);
		pnlChinh.add(btnTimKiem);

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setIcon(new ImageIcon("src/main/resources/icon/png/ic_workers_120.png"));
		lblHinhAnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHinhAnh.setBounds(118, 92, 160, 160);
		pnlChinh.add(lblHinhAnh);

		JPanel pnlNgaySinh = new JPanel();
		pnlNgaySinh.setBounds(879, 378, 384, 62);
		pnlChinh.add(pnlNgaySinh);
		pnlNgaySinh.setLayout(null);

		JLabel lblNgaySinh = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lbNgaySinh")));
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgaySinh.setBounds(10, 0, 96, 40);
		pnlNgaySinh.add(lblNgaySinh);

		jcNgaySinh = new JDateChooser();
		jcNgaySinh.getCalendarButton()
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
		jcNgaySinh.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
		jcNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcNgaySinh.setDateFormatString("yyyy-MM-dd");
		jcNgaySinh.setBounds(127, 0, 247, 41);
		pnlNgaySinh.add(jcNgaySinh);

		lblLoiNgaySinh = new JLabel("");
		lblLoiNgaySinh.setForeground(Color.RED);
		lblLoiNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiNgaySinh.setBounds(127, 39, 247, 23);
		pnlNgaySinh.add(lblLoiNgaySinh);

		JPanel pnlNgayVaoLam = new JPanel();
		pnlNgayVaoLam.setBounds(469, 306, 384, 62);
		pnlChinh.add(pnlNgayVaoLam);
		pnlNgayVaoLam.setLayout(null);

		JLabel lblNgayVaoLam = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.lbNgayVaoLam")));
		lblNgayVaoLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayVaoLam.setBounds(10, 0, 96, 40);
		pnlNgayVaoLam.add(lblNgayVaoLam);

		jcNgayVaoLam = new JDateChooser();
		jcNgayVaoLam.getCalendarButton()
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
		jcNgayVaoLam.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
		jcNgayVaoLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcNgayVaoLam.setDateFormatString("yyyy-MM-dd");
		jcNgayVaoLam.setBounds(127, 0, 247, 41);
		pnlNgayVaoLam.add(jcNgayVaoLam);

		lblLoiNgayVaoLam = new JLabel("");
		lblLoiNgayVaoLam.setForeground(Color.RED);
		lblLoiNgayVaoLam.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiNgayVaoLam.setBounds(127, 39, 247, 23);
		pnlNgayVaoLam.add(lblLoiNgayVaoLam);

		JPanel pnlTroCap = new JPanel();
		pnlTroCap.setBounds(469, 450, 384, 62);
		pnlChinh.add(pnlTroCap);
		pnlTroCap.setLayout(null);

		JLabel lblTroCap = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lbTroCap")));
		lblTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTroCap.setBounds(10, 0, 96, 40);
		pnlTroCap.add(lblTroCap);

		txtTroCap = new JTextField();
		txtTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTroCap.setColumns(10);
		txtTroCap.setBounds(127, 0, 247, 40);
		pnlTroCap.add(txtTroCap);

		lblLoiTroCap = new JLabel("");
		lblLoiTroCap.setForeground(Color.RED);
		lblLoiTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiTroCap.setBounds(127, 39, 247, 23);
		pnlTroCap.add(lblLoiTroCap);

		JLabel lblDSCongNhan = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("congNhan.lblDSCongNhan")));
		lblDSCongNhan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDSCongNhan.setBounds(20, 532, 184, 23);
		pnlChinh.add(lblDSCongNhan);

		btnLamMoi = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.btnXoaTrang")));
		btnLamMoi.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLamMoi.setBounds(227, 450, 184, 62);
		pnlChinh.add(btnLamMoi);

		btnExcel = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.btnExcel")));
		btnExcel.setIcon(new ImageIcon("src/main/resources/icon/png/ic_exel_.png"));
		btnExcel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnExcel.setBounds(1066, 481, 184, 62);
		pnlChinh.add(btnExcel);

		init();

		event();
	}

	private void event() {
		this.btnExcel.addActionListener((e) -> {
			thucHienChucNangXuatDanhSach();
		});
		this.btnTimKiem.addActionListener((e) -> {
			this.thucHienChucNangTimKiem();
		});
		this.btnLamMoi.addActionListener((e) -> {
			this.thucHienChucNangLamMoi();
		});
		this.tblCongNhan.addMouseListener(new MouseListener() {

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
				thucHienChucNangClickCongNhan();

			}
		});
	}

	private void thucHienChucNangXuatDanhSach() {
		int index = tblCongNhan.getSelectedRow();
		if (index >= 0) {
			CongNhan congNhan = dsCongNhan.get(index);
			List<CongNhan> cn = new ArrayList<CongNhan>();
			cn.add(congNhan);
			CongNhanExcelUtils.exportExcelCongNhan(cn);
		} else {
			CongNhanExcelUtils.exportExcelCongNhan(dsCongNhan);
		}

	}

	private void thucHienChucNangTimKiem() {
		try {
			if (!thucHienChucNangKiemTra()) {
				return;
			}

			String maCN = txtMaCN.getText().trim();
			String hoTen = txtHoTen.getText().trim();
			String soDienThoai = txtDienThoai.getText().trim();
			String cccd = txtCCCD.getText().trim();
			String email = txtEmail.getText().trim();
			String diaChi = txtDiaChi.getText().trim();
			Boolean tt;
			if (cmbTrangThai.getSelectedIndex() != 0) {
				tt = this.cmbTrangThai.getSelectedIndex() == 1 ? true : false;
			} else {
                tt = null;
            }
            LocalDate ngaySinh = DateConvertUtils.asLocalDate(jcNgaySinh.getDate(), ZoneId.systemDefault());
			LocalDate ngayVaoLam = DateConvertUtils.asLocalDate(jcNgayVaoLam.getDate(), ZoneId.systemDefault());
			Double troCap;

			if (!ObjectUtils.isEmpty(this.txtTroCap.getText().trim())) {
				troCap = PriceFormatterUtils.parse(this.txtTroCap.getText().trim());
			} else {
                troCap = null;
            }
            if (!thucHienChucNangKiemTra()) {
				return;
			}

			int gt;
			if (radNam.isSelected()) {
				gt = 1;
			} else if (radNu.isSelected()) {
				gt = 0;
			} else {
				gt = 2;
			}
			ToNhom toNhom = (ToNhom) cbbModelToNhom.getSelectedItem();
			TayNghe tayNghe = (TayNghe) cbbModelTayNghe.getSelectedItem();
			new Thread(() -> {
				try (var socket = new Socket(
						BUNDLE.getString("host"),
						Integer.parseInt(BUNDLE.getString("server.port")));
					 var dos = new DataOutputStream(socket.getOutputStream());
					 var dis = new DataInputStream(socket.getInputStream())
				){
					CongNhan cn = new CongNhan(maCN, hoTen, email, diaChi, cccd, soDienThoai, tt, gt, ngaySinh, ngayVaoLam,
							troCap, toNhom, tayNghe);
					// Send Data
					RequestDTO request = RequestDTO.builder()
							.requestType("TimKiemCongNhanForm")
							.request("timKiemCongNhan")
							.data(cn)
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

					dsCongNhan = data.stream().map((value) -> AppUtils.convert(value, CongNhan.class)).collect(Collectors.toList());
					tableModelCongNhan.setRowCount(0);

					int stt = 1;
					for (CongNhan congNhan : this.dsCongNhan) {
						String gioiTinh = congNhan.getGioiTinh() == 1 ? "Nam" : (congNhan.getGioiTinh() == 0 ? "Nữ" : "Khác");
						String trangThai = congNhan.isTrangThai() ? "Đang làm" : "Đã nghỉ";

						tableModelCongNhan.addRow(new Object[] { stt++, congNhan.getMaCongNhan(), congNhan.getHoTen(),
								congNhan.getCccd(), congNhan.getNgaySinh(), congNhan.getDiaChi(), congNhan.getSoDienThoai(),
								congNhan.getEmail(), gioiTinh, congNhan.getToNhom(), congNhan.getNgayVaoLam(),
								PriceFormatterUtils.format(congNhan.getTroCap()), congNhan.getTayNghe(), trangThai });
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {
		this.dsCongNhan = new ArrayList<CongNhan>();
		this.dsTayNghe = new ArrayList<TayNghe>();
		this.dsToNhom = new ArrayList<ToNhom>();

		loadComboBox();
		// thucHienChucNangLamMoi();
		loadTable();
	}

	private void loadTable() {
		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){
				// Send Data
				RequestDTO request = RequestDTO.builder()
						.requestType("CongNhanForm")
						.request("timKiemTatCaCongNhan")
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

				dsCongNhan = data.stream().map((value) -> AppUtils.convert(value, CongNhan.class)).collect(Collectors.toList());
				tableModelCongNhan.setRowCount(0);
				int stt = 1;
				for (CongNhan congNhan : this.dsCongNhan) {
					String gioiTinh = congNhan.getGioiTinh() == 1 ? "Nam" : (congNhan.getGioiTinh() == 0 ? "Nữ" : "Khác");
					String trangThai = congNhan.isTrangThai() ? "Đang làm" : "Đã nghỉ";

					tableModelCongNhan.addRow(new Object[] { stt++, congNhan.getMaCongNhan(), congNhan.getHoTen(),
							congNhan.getCccd(), congNhan.getNgaySinh(), congNhan.getDiaChi(), congNhan.getSoDienThoai(),
							congNhan.getEmail(), gioiTinh, congNhan.getToNhom(), congNhan.getNgayVaoLam(),
							PriceFormatterUtils.format(congNhan.getTroCap()), congNhan.getTayNghe(), trangThai });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void loadComboBox() {
		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){
				// Send Data
				RequestDTO request = RequestDTO.builder()
						.requestType("TayNgheForm")
						.request("timKiemTatCaTayNghe")
						.data("")
						.build();
				System.out.println("Sending request: " + request);
				String json = AppUtils.GSON.toJson(request);
				dos.writeUTF(json);
				dos.flush();

				// Receive Data
				json = new String(dis.readAllBytes());
				ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
				//System.out.println("Receive response: " + response);
				List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();

				dsTayNghe = data.stream().map((value) -> AppUtils.convert(value, TayNghe.class)).collect(Collectors.toList());
				dsTayNghe.add(0, new TayNghe("XXXX", "Tất cả"));
				cbbModelTayNghe.addAll(dsTayNghe);
				cbbModelTayNghe.setSelectedItem("Tất cả");
				if (!dsTayNghe.isEmpty()) {
					cmbTayNghe.setSelectedIndex(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){
				// Send Data
				RequestDTO request = RequestDTO.builder()
						.requestType("ToNhomForm")
						.request("timKiemTatCaToNhom")
						.data("")
						.build();
				System.out.println("Sending request: " + request);
				String json = AppUtils.GSON.toJson(request);
				dos.writeUTF(json);
				dos.flush();

				// Receive Data
				json = new String(dis.readAllBytes());
				ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
				//System.out.println("Receive response: " + response);
				List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();

				dsToNhom = data.stream().map((value) -> AppUtils.convert(value, ToNhom.class)).collect(Collectors.toList());
				dsToNhom.add(0, new ToNhom("XXXX", "Tất cả"));
				cbbModelToNhom.addAll(dsToNhom);
				if (!dsToNhom.isEmpty()) {
					cmbToNhom.setSelectedIndex(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void thucHienChucNangLamMoi() {
		thucHienChucNangLamMoiLoi();
		loadTable();
		this.lblHinhAnh
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_workers_120.png"));
		this.txtMaCN.setText("");
		this.txtHoTen.setText("");
		this.txtCCCD.setText("");
		this.txtDienThoai.setText("");
		this.txtEmail.setText("");
		this.txtDiaChi.setText("");
		this.radTatCa.setSelected(true);
		this.jcNgayVaoLam.setDate(null);
		this.cmbTayNghe.setSelectedIndex(0);
		this.cmbTrangThai.setSelectedIndex(0);
		this.cmbToNhom.setSelectedIndex(0);
		this.txtTroCap.setText("");
		this.jcNgaySinh.setDate(null);
	}

	private void thucHienChucNangLamMoiLoi() {
		this.lblLoiHoTen.setText("");
		this.lblLoiCCCD.setText("");
		this.lblLoiSoDienThoai.setText("");
		this.lblLoiEmail.setText("");
		this.lblLoiDiaChi.setText("");
		this.lblLoiNgayVaoLam.setText("");
		this.lblLoiTroCap.setText("");
		this.lblLoiNgaySinh.setText("");
	}

	private boolean thucHienChucNangKiemTra() {
		thucHienChucNangLamMoiLoi();

		String troCap = this.txtTroCap.getText().trim();

		boolean status = true;

		if (!ObjectUtils.isEmpty(troCap)) {
			try {
				troCap = troCap.replace(",", "");
				double troCapV = Double.valueOf(troCap);
			} catch (Exception e) {
				lblLoiTroCap.setText(SystemConstants.BUNDLE.getString("congNhan.loiSoThucTroCap"));
				status = false;
			}
		}

		return status;
	}

	private void thucHienChucNangClickCongNhan() {
		int index = tblCongNhan.getSelectedRow();
		if (index >= 0) {
			CongNhan cn = this.dsCongNhan.get(index);
			this.txtMaCN.setText(cn.getMaCongNhan());
			this.txtHoTen.setText(cn.getHoTen());
			this.txtCCCD.setText(cn.getCccd());
			this.txtDienThoai.setText(cn.getSoDienThoai());
			this.txtEmail.setText(cn.getEmail());
			this.txtDiaChi.setText(cn.getDiaChi());
			this.jcNgayVaoLam.setDate(DateConvertUtils.asUtilDate(cn.getNgayVaoLam(), ZoneId.systemDefault()));
			if (cn.getGioiTinh() == 1) {
				radNam.setSelected(true);
			} else {
				radNu.setSelected(true);
			}
			if (cn.isTrangThai()) {
				this.cmbTrangThai.setSelectedIndex(1);
			} else {
				this.cmbTrangThai.setSelectedIndex(2);
			}
			if (cn.getHinhAnh() != null) {
				this.lblHinhAnh.setIcon(
						ImageUtils.convertToImageIcon(cn.getHinhAnh(), lblHinhAnh.getWidth(), lblHinhAnh.getHeight()));
			} else {
				this.lblHinhAnh.setIcon(
						new ImageIcon("src/main/resources/icon/png/ic_product_120.png"));
			}
			this.txtTroCap.setText(PriceFormatterUtils.format(cn.getTroCap()));
			this.jcNgaySinh.setDate(DateConvertUtils.asUtilDate(cn.getNgaySinh(), ZoneId.systemDefault()));
			this.cbbModelTayNghe.setSelectedItem(cn.getTayNghe());
			this.cbbModelToNhom.setSelectedItem(cn.getToNhom());
		}
	}
}
