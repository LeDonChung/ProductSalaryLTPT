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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CongNhanForm extends JPanel {
	private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");

	private final JTextField txtMaCN;
	private final JTextField txtHoTen;
	private final JTextField txtCCCD;
	private final JTextField txtTroCap;
	private final JTextField txtDienThoai;
	private final JTextField txtEmail;
	private final JTextField txtDiaChi;
	private final JLabel lblHinhAnh;
	private final JButton btnXoa;
	private final JButton btnCapNhat;
	private final JButton btnThem;
	private final JButton btnLamMoi;
	private final DefaultTableModel tableModelCongNhan;
	private final JTable tblCongNhan;
	private final JDateChooser jcNgaySinh;
	private final JDateChooser jcNgayVaoLam;
	private final JButton btnChonAnh;
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
//	private final JButton btnThemNhiu;

	/**
	 * Create the panel.
	 */
	public CongNhanForm() {

		setLayout(null);

		JPanel pnlChinh = new JPanel();
		pnlChinh.setBounds(0, 14, 1273, 821);
		add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTitle = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.titleCongNhan")));
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
		pnlMaCN.setBounds(469, 509, 384, 62);
		pnlChinh.add(pnlMaCN);

		JLabel lblMaCN = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.lbMaCongNhan")));
		lblMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaCN.setBounds(10, 0, 96, 40);
		pnlMaCN.add(lblMaCN);

		txtMaCN = new JTextField();
		txtMaCN.setEditable(false);
		txtMaCN.setEnabled(false);
		txtMaCN.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaCN.setColumns(10);
		txtMaCN.setBounds(127, 0, 247, 40);
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
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Đang làm", "Đã nghỉ làm" }));
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
		pnlToNhom.setBounds(879, 378, 384, 62);
		pnlChinh.add(pnlToNhom);

		JLabel lblToNhom = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.lblToNhom")));
		lblToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblToNhom.setBounds(10, 0, 96, 40);
		pnlToNhom.add(lblToNhom);

		cbbModelToNhom = new DefaultComboBoxModel<ToNhom>();
		cmbToNhom = new JComboBox(cbbModelToNhom);
		cmbToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbToNhom.setBounds(128, 0, 246, 40);
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
		radNam.setBounds(127, 1, 85, 39);
		pnlGioiTinh.add(radNam);

		radNu = new JRadioButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.rbtnNu")));
		radNu.setSelected(true);
		radNu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radNu.setBounds(228, 1, 85, 39);
		pnlGioiTinh.add(radNu);

		ButtonGroup btnGioiTinh = new ButtonGroup();
		btnGioiTinh.add(radNu);
		btnGioiTinh.add(radNam);

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

		btnLamMoi = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.btnXoaTrang")));
		btnLamMoi.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLamMoi.setBounds(221, 342, 190, 44);
		pnlChinh.add(btnLamMoi);

		btnThem = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.btnThem")));
		btnThem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThem.setBounds(21, 342, 190, 44);
		pnlChinh.add(btnThem);

		btnCapNhat = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.btnCapNhat")));
		btnCapNhat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCapNhat.setBounds(20, 396, 190, 44);
		pnlChinh.add(btnCapNhat);

		btnXoa = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyCongNhan.btnXoa")));
		btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoa.setBounds(221, 396, 190, 44);
		pnlChinh.add(btnXoa);

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setIcon(new ImageIcon("src/main/resources/icon/png/ic_workers_120.png"));
		lblHinhAnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHinhAnh.setBounds(118, 92, 160, 160);
		pnlChinh.add(lblHinhAnh);

		JPanel pnlNgaySinh = new JPanel();
		pnlNgaySinh.setBounds(879, 450, 384, 62);
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

		btnChonAnh = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.btnChonAnh")));
		btnChonAnh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnChonAnh.setBounds(118, 272, 160, 44);
		pnlChinh.add(btnChonAnh);

		JLabel lblDSCongNhan = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("congNhan.lblDSCongNhan")));
		lblDSCongNhan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDSCongNhan.setBounds(20, 532, 184, 23);
		pnlChinh.add(lblDSCongNhan);

		this.jcNgaySinh.setDate(Date.valueOf(LocalDate.now()));
		this.jcNgayVaoLam.setDate(Date.valueOf(LocalDate.now()));

//		btnThemNhiu = new JButton(
//				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("congNhan.btnThemNhiu")));
//		btnThemNhiu.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
//		btnThemNhiu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
//		btnThemNhiu.setBounds(114, 468, 190, 44);
//		pnlChinh.add(btnThemNhiu);

		init();

		event();
	}

	private void event() {
//		this.btnThemNhiu.addActionListener((e) -> {
//			thucHienChucNangThemNhieu();
//		});
		this.btnChonAnh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon ic = ImageUtils.chooseImage(lblHinhAnh.getWidth(), lblHinhAnh.getHeight());
				lblHinhAnh.setIcon(ic);

			}
		});
		this.btnLamMoi.addActionListener((e) -> {
			this.thucHienChucNangLamMoi();
		});
		this.btnThem.addActionListener((e) -> {
			this.thucHienChucNangThemCongNhan();
		});
		this.btnXoa.addActionListener((e) -> {
			this.thucHienChucNangXoa();
		});
		this.btnCapNhat.addActionListener((e) -> {
			this.thucHienChucNangCapNhat();
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

	private void thucHienChucNangThemNhieu() {
		List<CongNhan> dsCN = CongNhanExcelUtils.importExcelCongNhan();
		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){

				if (!dsCN.isEmpty()) {

//					// Send Data
//					RequestDTO request = RequestDTO.builder()
//							.requestType("CongNhanForm")
//							.request("themNhieuCongNhan")
//							.data(dsCN)
//							.build();
//					//System.out.println("Sending request: " + request);
//					String json = AppUtils.GSON.toJson(request);
//					dos.writeUTF(json);
//					dos.flush();
//
//					// Receive Data
//					json = new String(dis.readAllBytes());
//					ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
//					//System.out.println("Receive response: " + response);
//					dsCN = (List<CongNhan>) response.getData();
//
//					dsCN = congNhanService.themNhieuCongNhan(dsCN);
//					if (dsCN != null) {
//						JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
//								SystemConstants.BUNDLE.getString("congNhan.themDanhSachCongNhan")));
//
//						// JOptionPane.showMessageDialog(this, "Thêm danh sách công nhân thành công");
//						loadTable();
//						thucHienChucNangLamMoi();
//					} else {
//						JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
//								SystemConstants.BUNDLE.getString("congNhan.themDanhSachCongNhanKhongThanh")));
//
//						// JOptionPane.showMessageDialog(this, "Thêm danh sách công nhân không thành
//						// công");
//						loadTable();
//						thucHienChucNangLamMoi();
//					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void thucHienChucNangCapNhat() {
		int index = tblCongNhan.getSelectedRow();
		if (index >= 0) {
			if (!thucHienChucNangKiemTra()) {
				return;
			}

			String maCongNhan = this.txtMaCN.getText().trim();
			String tenCongNhan = this.txtHoTen.getText().trim();
			String cccd = txtCCCD.getText().trim();
			String soDienThoai = txtDienThoai.getText().trim();
			String email = txtEmail.getText().trim();
			String diaChi = txtDiaChi.getText().trim();
			int gioiTinh;
			if (radNam.isSelected()) {
				gioiTinh = 1;
			} else {
				gioiTinh = 0;
			}
			LocalDate ngaySinh = DateConvertUtils.asLocalDate(jcNgaySinh.getDate(), ZoneId.systemDefault());
			LocalDate ngayVaoLam = DateConvertUtils.asLocalDate(jcNgayVaoLam.getDate(), ZoneId.systemDefault());
			boolean trangThai = cmbTrangThai.getSelectedIndex() == 0 ? true : false;
			double troCap = PriceFormatterUtils.parse(this.txtTroCap.getText().trim());
			byte[] hinhAnh = ImageUtils.convertToByteArray(lblHinhAnh.getIcon());

			ToNhom toNhom = (ToNhom) cbbModelToNhom.getSelectedItem();
			TayNghe tayNghe = (TayNghe) cbbModelTayNghe.getSelectedItem();

			try {
				int choose = JOptionPane.showConfirmDialog(this,
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("congNhan.capNhatCongNhan")),
								SystemConstants.BUNDLE.getString("congNhan.xacNhan"), JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.OK_OPTION) {
					new Thread(() -> {
						try (var socket = new Socket(
								BUNDLE.getString("host"),
								Integer.parseInt(BUNDLE.getString("server.port")));
							 var dos = new DataOutputStream(socket.getOutputStream());
							 var dis = new DataInputStream(socket.getInputStream())
						){
							CongNhan congNhan = new CongNhan(maCongNhan, tenCongNhan, email, diaChi, gioiTinh, cccd, soDienThoai,
									ngaySinh, toNhom, ngayVaoLam, troCap, hinhAnh, trangThai, tayNghe);
							// Send Data
							RequestDTO request = RequestDTO.builder()
									.requestType("CongNhanForm")
									.request("capNhatCongNhan")
									.data(congNhan)
									.build();
							//System.out.println("Sending request: " + request);
							String json = AppUtils.GSON.toJson(request);
							dos.writeUTF(json);
							dos.flush();

							// Receive Data
							json = new String(dis.readAllBytes());
							ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
							//System.out.println("Receive response: " + response);
							Map<String, Object> data = (Map<String, Object>) response.getData();

							if (data != null) {
								JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
										SystemConstants.BUNDLE.getString("congNhan.capNhatCongNhanThanh")));
								this.thucHienChucNangLamMoi();
								loadTable();
							} else {
								JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
										SystemConstants.BUNDLE.getString("congNhan.capNhatCongNhanKhongThanh")));
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}).start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this,
					String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("congNhan.chonCongNhan")));
		}
	}

	private void init() {
		this.dsCongNhan = new ArrayList<CongNhan>();
		this.dsTayNghe = new ArrayList<TayNghe>();
		this.dsToNhom = new ArrayList<ToNhom>();

		// thucHienChucNangLamMoi();
		loadComboBox();
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
				cbbModelTayNghe.addAll(dsTayNghe);
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
				cbbModelToNhom.addAll(dsToNhom);
				if (!dsToNhom.isEmpty()) {
					cmbToNhom.setSelectedIndex(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void thucHienChucNangThemCongNhan() {
		if (!thucHienChucNangKiemTra()) {
			return;
		}
		String maCongNhan = null;
		String tenCongNhan = this.txtHoTen.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String soDienThoai = txtDienThoai.getText().trim();
		String email = txtEmail.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		int gioiTinh;
		if (radNam.isSelected()) {
			gioiTinh = 1;
		} else {
			gioiTinh = 0;
		}
		LocalDate ngaySinh = DateConvertUtils.asLocalDate(jcNgaySinh.getDate(), ZoneId.systemDefault());
		LocalDate ngayVaoLam = DateConvertUtils.asLocalDate(jcNgayVaoLam.getDate(), ZoneId.systemDefault());
		// boolean trangThai = cmbTrangThai.getSelectedIndex() == 0 ? true : false;
		double troCap = PriceFormatterUtils.parse(this.txtTroCap.getText().trim());
		byte[] hinhAnh = ImageUtils.convertToByteArray(lblHinhAnh.getIcon());

		ToNhom toNhom = (ToNhom) cbbModelToNhom.getSelectedItem();
		TayNghe tayNghe = (TayNghe) cbbModelTayNghe.getSelectedItem();

		try {
			new Thread(() -> {
				try (var socket = new Socket(
						BUNDLE.getString("host"),
						Integer.parseInt(BUNDLE.getString("server.port")));
					 var dos = new DataOutputStream(socket.getOutputStream());
					 var dis = new DataInputStream(socket.getInputStream())
				){
					CongNhan congNhan = new CongNhan(maCongNhan, tenCongNhan, email, diaChi, gioiTinh, cccd, soDienThoai,
							ngaySinh, toNhom, ngayVaoLam, troCap, hinhAnh, tayNghe);
					// Send Data
					RequestDTO request = RequestDTO.builder()
							.requestType("CongNhanForm")
							.request("themCongNhan")
							.data(congNhan)
							.build();
					//System.out.println("Sending request: " + request);
					String json = AppUtils.GSON.toJson(request);
					dos.writeUTF(json);
					dos.flush();

					// Receive Data
					json = new String(dis.readAllBytes());
					ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
					//System.out.println("Receive response: " + response);
					Map<String, Object> themCongNhan = (Map<String, Object>) response.getData();
					if (themCongNhan != null) {
						JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("congNhan.themCongNhan")));

						this.thucHienChucNangLamMoi();
						loadTable();
					} else {
						JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("congNhan.themCongNhanKhongThanh")));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void thucHienChucNangXoa() {
		int index = this.tblCongNhan.getSelectedRow();
		if (index >= 0) {
			CongNhan cn = this.dsCongNhan.get(index);
			int choose = JOptionPane.showConfirmDialog(this,
					String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("congNhan.xoaCongNhan")),
					SystemConstants.BUNDLE.getString("congNhan.xacNhan"), JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.OK_OPTION) {
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
								.request("capNhatTrangThaiCongNhan")
								.data(cn.getMaCongNhan())
								.build();
						System.out.println("Sending request: " + request);
						String json = AppUtils.GSON.toJson(request);
						dos.writeUTF(json);
						dos.flush();

						// Receive Data
						json = new String(dis.readAllBytes());
						ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
						System.out.println("Receive response: " + response);
						boolean trangThai = (boolean) response.getData();
						if (trangThai) {
							JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
									SystemConstants.BUNDLE.getString("congNhan.xoaCongNhanThanh")));
							loadTable();
							this.thucHienChucNangLamMoi();
						} else {
							JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
									SystemConstants.BUNDLE.getString("congNhan.xoaCongNhanKhongThanh")));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).start();
			}
		} else {
			JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("congNhan.chonXoaCongNhan")));
		}
	}

	private void thucHienChucNangLamMoi() {
		thucHienChucNangLamMoiLoi();
		this.lblHinhAnh
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_workers_120.png"));
		this.txtMaCN.setText("");
		this.txtHoTen.setText("");
		this.txtCCCD.setText("");
		this.txtDienThoai.setText("");
		this.txtEmail.setText("");
		this.txtDiaChi.setText("");
		this.radNam.setSelected(true);
		this.jcNgayVaoLam.setDate(Date.valueOf(LocalDate.now()));
		if (dsTayNghe.size() != 0) {
			this.cmbTayNghe.setSelectedIndex(0);
		}
		this.cmbTrangThai.setSelectedIndex(0);
		if (dsToNhom.size() != 0) {
			this.cmbToNhom.setSelectedIndex(0);
		}
		this.txtTroCap.setText("");
		this.jcNgaySinh.setDate(Date.valueOf(LocalDate.now()));
		tblCongNhan.clearSelection();
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

		String hoTen = this.txtHoTen.getText().trim();
		String cccd = this.txtCCCD.getText().trim();
		String soDienThoai = this.txtDienThoai.getText().trim();
		String email = this.txtEmail.getText().trim();
		LocalDate ngaoVaoLam = jcNgayVaoLam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String troCap = this.txtTroCap.getText().trim();
		LocalDate ngaySinh = jcNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		boolean status = true;

		if (ObjectUtils.isEmpty(hoTen)) {
			this.lblLoiHoTen.setText(SystemConstants.BUNDLE.getString("congNhan.loiHoTen"));
			// this.lblLoiHoTen.setText("Họ tên không được rỗng");
			status = false;
		}
		if (ObjectUtils.isEmpty(cccd)) {
			this.lblLoiCCCD.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiCccd")));
			status = false;
		} else if (!cccd.matches("^0[0-9]{11}$") && cccd.length() != 12) {
			lblLoiCCCD.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiCccdRegex")));
			status = false;
		}

		if (ObjectUtils.isEmpty(soDienThoai)) {
			this.lblLoiSoDienThoai.setText(SystemConstants.BUNDLE.getString("congNhan.loiRongSoDienThoai"));
			status = false;
		} else if (soDienThoai.length() != 10) {
			this.lblLoiSoDienThoai.setText(SystemConstants.BUNDLE.getString("congNhan.loiSoSoDienThoai"));
			status = false;
		} else if (!soDienThoai.trim().matches("^0[0-9]{9}$")) {
			this.lblLoiSoDienThoai.setText(SystemConstants.BUNDLE.getString("congNhan.loiSoDienThoai0"));
			status = false;
		}

		if (ObjectUtils.isEmpty(email)) {
			this.lblLoiEmail.setText(SystemConstants.BUNDLE.getString("congNhan.loiRongEmail"));
			status = false;
		} else if (!email.trim().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
			this.lblLoiEmail.setText(SystemConstants.BUNDLE.getString("congNhan.loiCuPhapEmail"));
			status = false;
		}

		try {
			troCap = troCap.replace(",", "");
			double troCapV = Double.valueOf(troCap);
			if (troCapV < 0) {
				this.lblLoiTroCap.setText(SystemConstants.BUNDLE.getString("congNhan.loiTroCap"));
				status = false;
			}
		} catch (Exception e) {
			this.lblLoiTroCap.setText(SystemConstants.BUNDLE.getString("congNhan.loiSoThucTroCap"));
			status = false;
		}

		int tuoi = Period.between(ngaySinh, LocalDate.now()).getYears();
		if (tuoi < 18) {
			this.lblLoiNgaySinh.setText(SystemConstants.BUNDLE.getString("congNhan.loiNgaySinh"));
			status = false;
		}

		if (ngaoVaoLam.isAfter(LocalDate.now())) {
			this.lblLoiNgayVaoLam.setText(SystemConstants.BUNDLE.getString("congNhan.loiNgayVaoLam"));
			status = false;
		}

		return status;
	}

	private void thucHienChucNangClickCongNhan() {
		thucHienChucNangLamMoiLoi();
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
				this.cmbTrangThai.setSelectedIndex(0);
			} else {
				this.cmbTrangThai.setSelectedIndex(1);
			}
			if (cn.getHinhAnh() != null) {
				this.lblHinhAnh.setIcon(
						ImageUtils.convertToImageIcon(cn.getHinhAnh(), lblHinhAnh.getWidth(), lblHinhAnh.getHeight()));
			} else {
				this.lblHinhAnh.setIcon(
						new ImageIcon("src/main/resources/icon/png/ic_workers_150.png"));
			}
			this.txtTroCap.setText(PriceFormatterUtils.format(cn.getTroCap()));
			this.jcNgaySinh.setDate(DateConvertUtils.asUtilDate(cn.getNgaySinh(), ZoneId.systemDefault()));
			this.cbbModelTayNghe.setSelectedItem(cn.getTayNghe());
			this.cbbModelToNhom.setSelectedItem(cn.getToNhom());

		}
	}
}
